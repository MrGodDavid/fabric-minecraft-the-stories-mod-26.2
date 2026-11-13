package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.mrgoddavid.minecraftthestoriesmod.effect.MtsEffects;
import net.mrgoddavid.minecraftthestoriesmod.utils.list.MtsElementSets;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Timer;
import org.jspecify.annotations.Nullable;

import java.util.*;

/**
 * @author Mr. GodDavid
 * @since 11/2/2026
 */
@SuppressWarnings("NullableProblems")
public abstract class AbstractWoodlinEntity extends TamableAnimal {

    protected static final EntityDataAccessor<Boolean> DATA_USING_ABILITY = SynchedEntityData.defineId(AbstractWoodlinEntity.class, EntityDataSerializers.BOOLEAN); // communicate between server and client.
    protected static final EntityDataAccessor<Boolean> DATA_DEFENDING = SynchedEntityData.defineId(AbstractWoodlinEntity.class, EntityDataSerializers.BOOLEAN);
    protected final byte STAND_EVENT = 99;
    private final MtsElementSets.Trio<Item, Item, Item> woodlinInteractableItems;
    private final WoodlinContext woodlinContext;
    protected final Set<UUID> cursedEntities = new HashSet<>();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState enchantingAnimationState = new AnimationState();
    public final AnimationState disenchantingAnimationState = new AnimationState();
    public final AnimationState tameAnimationState = new AnimationState();

    protected AbstractWoodlinEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        this.woodlinContext = this.registerWoodlinContexts();
        this.woodlinInteractableItems = this.registerWoodlinInteractableItems();
    }

    /**
     * First one is item for taming woodlines; the second is used for healing; the third one is used for
     * enchanting/disenchanting.
     */
    protected abstract MtsElementSets.Trio<Item, Item, Item> registerWoodlinInteractableItems();

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_USING_ABILITY, false);
        entityData.define(DATA_DEFENDING, false);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("enemies_are_cursed", Codec.list(Codec.STRING), this.cursedEntities.stream().map(UUID::toString).toList());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.cursedEntities.clear();
        List<String> savedUUIDs = input.read("enemies_are_cursed", Codec.list(Codec.STRING)).orElse(List.of());
        for (String uuid : savedUUIDs) {
            try {
                this.cursedEntities.add(UUID.fromString(uuid));
            } catch (IllegalArgumentException _) {
            }
        }
        this.setAlerting(!this.cursedEntities.isEmpty());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        WoodlinContext woodlinContext = this.registerWoodlinContexts();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TamableAnimalPanicGoal(
                woodlinContext.getDoubleOrThrow(WoodlinContext.TAMED_ANIMAL_PANIC_SPEED_MODIFIER), DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES)
        );
        this.goalSelector.addGoal(3, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this,
                woodlinContext.getDoubleOrThrow(WoodlinContext.TAMED_ANIMAL_FOLLOW_OWNER_SPEED_MODIFIER),
                woodlinContext.getFloatOrThrow(WoodlinContext.TAMED_ANIMAL_FOLLOW_OWNER_START_DISTANCE),
                woodlinContext.getFloatOrThrow(WoodlinContext.TAMED_ANIMAL_FOLLOW_OWNER_STOP_DISTANCE)
        ));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class,
                woodlinContext.getFloatOrThrow(WoodlinContext.ENTITY_RANDOMLY_LOOK_DISTANCE)
        ));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.registerBehavioralGoals();
    }

    /**
     * Be aware of registering the goals.
     * <pre>{@code
     * GOAL_NAME                        PRIORITY
     * FloatGoal                            0
     * TamableAnimalPanicGoal               1
     * SitWhenOrderedToGoal                 3
     * FollowOwnerGoal                      4
     * LookAtPlayerGoal                     7
     * RandomLookAroundGoal                 7
     * }
     * </pre>
     */
    protected abstract void registerBehavioralGoals();

    protected abstract WoodlinContext registerWoodlinContexts();

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        boolean isTameItemCorrect = itemStack.is(this.woodlinInteractableItems.first());

        if (!this.level().isClientSide()) {
            if (!isBaby()) {
                // taming
                if (!isTame() && isTameItemCorrect) {
                    itemStack.consume(1, player);
                    this.tryToTame(player);
                    return InteractionResult.SUCCESS;
                }

                // performing actions once it is tamed by player.
                if (isTame() && isOwnedBy(player)) {
                    // healing
                    if (itemStack.is(this.woodlinInteractableItems.second()) && this.getHealth() < this.getMaxHealth()) {
                        this.feed(player, hand, itemStack, 2.0F, 2.0F);
                        this.spawnHealingParticles();
                        return InteractionResult.SUCCESS;
                    }

                    // enchant & disenchant
                    if (itemStack.is(this.woodlinInteractableItems.third())) {
                        boolean shouldEnchant = !this.isEnchanting();
                        this.setOrderedToSit(shouldEnchant);
                        this.setGlowingTag(shouldEnchant);
                        return InteractionResult.SUCCESS;
                    }

                    InteractionResult result = super.mobInteract(player, hand);
                    if (result.consumesAction()) {
                        return result;
                    }
                }
            }
        }

        return isOwnedBy(player) || isTame() || isTameItemCorrect ? InteractionResult.CONSUME : InteractionResult.PASS;
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        if (source.is(DamageTypes.SWEET_BERRY_BUSH)) {
            return false;
        }
        return super.hurtServer(level, source, damage);
    }

    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        this.setInSittingPose(orderedToSit);

        this.jumping = false;
        this.navigation.stop();
        this.setTarget(null);

        if (!orderedToSit && !this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, STAND_EVENT);
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        super.onSyncedDataUpdated(accessor);
        if (DATA_FLAGS_ID.equals(accessor)) {
            if (this.level().isClientSide()) {
                this.updateEnchantingAnimation();
            }
        }
        this.handleAbilityUsageAnimation(accessor);
    }

    protected abstract void handleAbilityUsageAnimation(EntityDataAccessor<?> accessor);

    @Override
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);
        if (id == EntityEvent.TAMING_SUCCEEDED) {
            this.tameAnimationState.start(this.tickCount);
            this.updateEnchantingAnimation();
        }
        if (id == STAND_EVENT) {
            this.disenchantingAnimationState.start(this.tickCount);
        }
    }

    @Override
    public void tick() {
        super.tick();
        // Client side.
        if (this.level().isClientSide()) {
            this.updateIdleAnimation();
            this.updateTameAnimation();
            this.updateEnchantingAnimation();
            this.updateDisenchantingAnimation();

            this.additionalEntityClientSideTick();
        }
        // Server side.
        else {
            ServerLevel level = (ServerLevel) this.level();
            this.grantsPlayersInEnchantingRegionEffects(level);
            this.grantsEntitiesInEnchantingRegionEffects(level);
            this.checkIfAlerting(level);

            this.addAdditionalEntityServerSideTick(level);
        }
    }

    private void checkIfAlerting(ServerLevel level) {
        Iterator<UUID> iterator = this.cursedEntities.iterator();
        while (iterator.hasNext()) {
            UUID uuid = iterator.next();
            Entity entity = level.getEntity(uuid);
            if (entity != null) {
                if (!entity.isAlive()) {
                    iterator.remove();
                } else {
                    Optional.ofNullable(entity.asLivingEntity()).ifPresent(livingEntity -> {
                        if (this.livingEntityNotCursedByWoodlins(livingEntity)) {
                            iterator.remove();
                        }
                    });
                }
            }
        }
        this.setAlerting(!this.cursedEntities.isEmpty());
    }

    // todo add special effects for other woodlins.
    private boolean livingEntityNotCursedByWoodlins(LivingEntity livingEntity) {
        return !livingEntity.hasEffect(MtsEffects.LEECH_OF_OAKIE);
    }

    protected final void tryToTame(Player player) {
        if (this.random.nextInt(10) == 0) {
            this.tame(player);
            this.setOrderedToSit(true);
            this.level().broadcastEntityEvent(this, EntityEvent.TAMING_SUCCEEDED);
        } else {
            this.spawnTamingFailedParticles();
        }
    }

    private void grantsEntitiesInEnchantingRegionEffects(ServerLevel level) {
        List<LivingEntity> entitiesInEnchantingRegion = Lists.newArrayList();
        level.getEntities(EntityTypeTest.forClass(LivingEntity.class), this::selectEntities, entitiesInEnchantingRegion);
        entitiesInEnchantingRegion.forEach(this::grantsEffectsToLivingEntitiesInEnchantingRegion);
    }

    protected abstract void grantsEffectsToLivingEntitiesInEnchantingRegion(LivingEntity entity);

    private void grantsPlayersInEnchantingRegionEffects(ServerLevel level) {
        level.getPlayers(this::selectPlayerInEnchantingRegion).forEach(this::grantsEffectToPlayersInEnchantingRegion);
    }

    protected abstract void grantsEffectToPlayersInEnchantingRegion(ServerPlayer player);

    private void updateDisenchantingAnimation() {
        if (this.disenchantingAnimationState.isStarted() && this.disenchantingAnimationState.getTimeInMillis(this.tickCount) > 1000L) {
            this.disenchantingAnimationState.stop();
        }
    }

    private void updateIdleAnimation() {
        this.idleAnimationState.animateWhen(!isInWater() && !this.walkAnimation.isMoving(), this.tickCount);
    }

    private void updateTameAnimation() {
        if (this.tameAnimationState.isStarted() && this.tameAnimationState.getTimeInMillis(this.tickCount) > 1000L) {
            this.tameAnimationState.stop();
        }
    }

    private void updateEnchantingAnimation() {
        boolean shouldEnchant = this.isEnchanting() && !this.tameAnimationState.isStarted();
        if (shouldEnchant) {
            this.enchantingAnimationState.startIfStopped(tickCount);
        } else {
            this.enchantingAnimationState.stop();
        }
    }

    protected abstract void additionalEntityClientSideTick();

    protected abstract void addAdditionalEntityServerSideTick(ServerLevel level);

    protected final boolean isUsingAbility() {
        return this.isTame() && this.entityData.get(DATA_USING_ABILITY);
    }

    protected final void setUsingAbility(boolean ability) {
        this.entityData.set(DATA_USING_ABILITY, ability);
    }

    protected final boolean isDefending() {
        return this.entityData.get(DATA_DEFENDING);
    }

    public boolean isWarning() {
        return this.isDefending() && !this.isTame();
    }

    protected final void setAlerting(boolean defending) {
        this.entityData.set(DATA_DEFENDING, defending);
    }

    protected final void spawnHealingParticles() {
        ((ServerLevel) this.level()).sendParticles(ParticleTypes.HEART, this.getX(), this.getY() + this.getBbHeight() + 0.5d, this.getZ(), 2, 0.3d, 0.3d, 0.3d, 0.0);
    }

    protected final void spawnTamingFailedParticles() {
        ((ServerLevel) this.level()).sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + this.getBbHeight() + 0.5d, this.getZ(), 2, 0.3d, 0.3d, 0.3d, 0.0);
    }

    public final boolean isEnchanting() {
        return this.isTame() && this.isInSittingPose();
    }

    public final boolean isOwnerInEnchantingRange(ServerPlayer player) {
        return this.isEnchanting() && this.isOwnedBy(player) && this.selectPlayerInEnchantingRegion(player);
    }

    protected final boolean selectPlayerInEnchantingRegion(ServerPlayer player) {
        return this.selectEntities(player);
    }

    protected final boolean selectEntities(LivingEntity entity) {
        double dy = Math.abs(entity.getY() - this.getY());
        if (dy < 0.0F || dy > 1.0F) return false;

        double dx = entity.getX() - this.getX();
        double dz = entity.getZ() - this.getZ();
        double distanceSquared = dx * dx + dz * dz;
        return distanceSquared <= this.woodlinContext.getDoubleOrThrow(WoodlinContext.WOODLIN_ABILITY_USE_RADIUS_SQ);
    }

    /**
     * @author Mr. GodDavid
     * @since 11/3/2026
     */
    public abstract class WoodlinUseAbilityGoal extends Goal {

        private final float minNoticeDistance;
        private final float maxNoticeDistance;
        private final Timer abilityCooldownTimer = new Timer(60);
        protected @Nullable LivingEntity target;

        public WoodlinUseAbilityGoal(float noticeDistance, float maxNoticeDistance) {
            this.minNoticeDistance = noticeDistance;
            this.maxNoticeDistance = maxNoticeDistance;
        }

        @Override
        public void start() {
            AbstractWoodlinEntity.this.setUsingAbility(true);
        }

        @Override
        public void stop() {
            AbstractWoodlinEntity.this.setUsingAbility(false);
            this.target = null;
            this.abilityCooldownTimer.reset();
        }

        @Override
        public void tick() {
            if (this.abilityCooldownTimer.pass(() -> (this.target != null && this.isOwnerInNoticeRange(this.target)))) {
                this.abilityCooldownTimer.reset();
                this.registerAbilityUsageBehavior();
            }
        }

        protected abstract void registerAbilityUsageBehavior();

        @Override
        public boolean canUse() {
            this.findOwner();
            return AbstractWoodlinEntity.this.isTame() && this.metAbilityUsageBaseCriteria();
        }

        private void findOwner() {
            LivingEntity owner = AbstractWoodlinEntity.this.getOwner();
            this.target = (owner != null && this.isOwnerInNoticeRange(owner)) ? owner : null;
        }

        @Override
        public boolean canContinueToUse() {
            // note: at this point, the target is referencing to the target.
            return this.metAbilityUsageBaseCriteria() && this.isOwnerInNoticeRange(this.target) && AbstractWoodlinEntity.this.getOwner() == this.target;
        }

        private boolean metAbilityUsageBaseCriteria() {
            return this.target != null && this.target.isAlive() && this.customBaseCriteria();
        }

        protected abstract boolean customBaseCriteria();

        private boolean isOwnerInNoticeRange(LivingEntity owner) {
            double distanceSq = this.distanceBetweenWoodlinAndOwner(owner);
            return distanceSq >= this.minNoticeDistance * this.minNoticeDistance
                    && distanceSq <= this.maxNoticeDistance * this.maxNoticeDistance;
        }

        private double distanceBetweenWoodlinAndOwner(LivingEntity owner) {
            return AbstractWoodlinEntity.this.distanceToSqr(owner);
        }
    }

    // ================================================[DETRACTED METHODS]================================================

    @Deprecated
    protected final boolean isHealing() {
        return this.isTame() && this.entityData.get(DATA_USING_ABILITY);
    }

    @Deprecated
    protected final void setHealing(boolean ability) {
        this.entityData.set(DATA_USING_ABILITY, ability);
    }
}
