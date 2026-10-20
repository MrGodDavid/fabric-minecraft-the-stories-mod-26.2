package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;
import net.mrgoddavid.minecraftthestoriesmod.effect.MtsEffects;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import net.mrgoddavid.minecraftthestoriesmod.utils.Timer;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr. GodDavid
 * @since 10/16/2026
 */
public class OakieEntity extends TamableAnimal {

    public static final int HEALING_RADIUS = 5;
    public static final int HEALING_RADIUS_SQUARED = HEALING_RADIUS * HEALING_RADIUS;
    private static final EntityDataAccessor<Boolean> DATA_ENCHANTING = SynchedEntityData.defineId(OakieEntity.class, EntityDataSerializers.BOOLEAN); // communicate between server and client.
    private final byte STAND_EVENT = 99;
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState healAnimationState = new AnimationState();
    public final AnimationState enchantingAnimationState = new AnimationState();
    public final AnimationState disenchantingAnimationState = new AnimationState();
    public final AnimationState tameAnimationState = new AnimationState();
    private boolean pendingSitAnimation = false;

    public OakieEntity(EntityType<? extends OakieEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_ENCHANTING, false);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TamableAnimalPanicGoal(1.25, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new OakieGrowCropsGoal());
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
//        this.targetSelector.addGoal(1, new OakieHealOwnerGoal(this, 4.5f, 10f, true, true));
    }

    public static AttributeSupplier.Builder createCubeAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.FOLLOW_RANGE, 32);
    }

    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        this.setInSittingPose(orderedToSit);

        this.jumping = false;
        this.navigation.stop();
        setTarget(null);

        if (!orderedToSit && !this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, STAND_EVENT);
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        if (DATA_FLAGS_ID.equals(accessor)) {
            if (isInSittingPose()) {
                if (this.tameAnimationState.isStarted()) {
                    this.pendingSitAnimation = true;
                    this.enchantingAnimationState.stop();
                } else {
                    this.enchantingAnimationState.startIfStopped(tickCount);
                }
            } else {
                this.pendingSitAnimation = false;
                this.enchantingAnimationState.stop();
            }
        }
        super.onSyncedDataUpdated(accessor);
    }

    @Override
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);

        if (id == EntityEvent.TAMING_SUCCEEDED) {
            this.tameAnimationState.start(this.tickCount);
        }
        if (id == STAND_EVENT) {
            this.disenchantingAnimationState.start(this.tickCount);
        }
    }

    @Override
    public void tick() {
        super.tick();

        // Client side.
        if (level().isClientSide()) {
            this.idleAnimationState.animateWhen(!isInWater() && !this.walkAnimation.isMoving(), this.tickCount);

            if (this.tameAnimationState.isStarted() && this.tameAnimationState.getTimeInMillis(this.tickCount) > 1000L) {
                this.tameAnimationState.stop();
                if (pendingSitAnimation) {
                    this.enchantingAnimationState.startIfStopped(this.tickCount);
                    this.pendingSitAnimation = false;
                }
            }

            if (this.enchantingAnimationState.isStarted()
                    && this.enchantingAnimationState.getTimeInMillis(this.tickCount) > 1000L) {
                this.enchantingAnimationState.stop();
            }

            if (this.disenchantingAnimationState.isStarted() && this.disenchantingAnimationState.getTimeInMillis(this.tickCount) > 1000L) {
                this.disenchantingAnimationState.stop();
            }
        }
        // Server side.
        else {
            ServerLevel level = (ServerLevel) this.level();
            grantsPlayersInEnchantingRegionEffects(level);
            grantsEntitiesInEnchantingRegionEffects(level);
        }
    }

    private void grantsEntitiesInEnchantingRegionEffects(ServerLevel level) {
        List<LivingEntity> entitiesInEnchantingRegion = Lists.newArrayList();
        level.getEntities(EntityTypeTest.forClass(LivingEntity.class), this::selectEntities, entitiesInEnchantingRegion);
        for (LivingEntity entity : entitiesInEnchantingRegion) {
            if (entity instanceof OakieEntity oakie) {
                oakie.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0, false, true, true));
            } else if (entity instanceof Monster monster) {
                monster.addEffect(new MobEffectInstance(MtsEffects.LEECH_OF_OAKIE, 100, 0, false, true, true));
            } else if (entity instanceof Enemy) {
                entity.addEffect(new MobEffectInstance(MtsEffects.LEECH_OF_OAKIE, 100, 0, false, true, true));
            }
        }
    }

    private void grantsPlayersInEnchantingRegionEffects(ServerLevel level) {
        List<ServerPlayer> playersInEnchantingRegion = level.getPlayers(this::selectPlayerInEnchantingRegion);
        for (ServerPlayer player : playersInEnchantingRegion) {
            if (this.isOwnedBy(player) && this.isEnchanting()) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1, false, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 600, 0, false, true, true));
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0, false, true, true));
            }
        }
    }

    private boolean selectPlayerInEnchantingRegion(ServerPlayer player) {
        return this.selectEntities(player);
    }

    private boolean selectEntities(LivingEntity entity) {
        double dy = Math.abs(entity.getY() - this.getY());
        if (dy < 0.0F || dy > 1.0F) return false;

        double dx = entity.getX() - this.getX();
        double dz = entity.getZ() - this.getZ();
        double distanceSquared = dx * dx + dz * dz;
        return distanceSquared <= HEALING_RADIUS_SQUARED;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        boolean isTameItemCorrect = itemStack.is(Items.OAK_SAPLING);

        if (!this.level().isClientSide()) {
            if (!isBaby()) {
                // taming
                if (!isTame() && isTameItemCorrect) {
                    itemStack.consume(1, player);
                    tryToTame(player);
                    return InteractionResult.SUCCESS;
                }

                // performing actions once it is tamed by player.
                if (isTame() && isOwnedBy(player)) {
                    // healing
                    if (itemStack.is(Items.OAK_LEAVES) && this.getHealth() < this.getMaxHealth()) {
                        this.feed(player, hand, itemStack, 2.0F, 2.0F);
                        this.spawnHealingParticles();
                        return InteractionResult.SUCCESS;
                    }

                    // enchant & disenchant
                    if (itemStack.is(Items.STICK)) {
                        boolean shouldEnchant = !this.isEnchanting();
                        this.setEnchanting(shouldEnchant);
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

    private void tryToTame(Player player) {
        if (this.random.nextInt(10) == 0) {
            tame(player);
            setOrderedToSit(true);
            level().broadcastEntityEvent(this, EntityEvent.TAMING_SUCCEEDED);
        } else {
            this.spawnTamingFailedParticles();
        }
    }

    private void spawnHealingParticles() {
        ((ServerLevel) this.level()).sendParticles(ParticleTypes.HEART, this.getX(), this.getY() + this.getBbHeight() + 0.5d, this.getZ(), 2, 0.3d, 0.3d, 0.3d, 0.0);
    }

    private void spawnTamingFailedParticles() {
        ((ServerLevel) this.level()).sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + this.getBbHeight() + 0.5d, this.getZ(), 2, 0.3d, 0.3d, 0.3d, 0.0);
    }

    public boolean isEnchanting() {
        return this.isTame() && this.entityData.get(DATA_ENCHANTING);
    }

    private void setEnchanting(boolean enchanting) {
        this.entityData.set(DATA_ENCHANTING, enchanting);
    }

//    /**
//     * @author Mr. GodDavid
//     * @since 10/20/2026
//     */
//    private class OakieHealOwnerGoal extends TargetGoal {
//
//        private static final int HEAL_AMOUNT = 1;
//        private final float minNoticeDistance;
//        private final float maxNoticeDistance;
//        private final Timer healCoodDownTimer = new Timer(60);
//        protected @Nullable LivingEntity target;
//
//        public OakieHealOwnerGoal(Mob mob, float minNoticeDistance, float maxNoticeDistance, boolean mustSee, boolean mustReach) {
//            super(mob, mustSee, mustReach);
//            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.TARGET));
//            this.minNoticeDistance = minNoticeDistance;
//            this.maxNoticeDistance = maxNoticeDistance;
//        }
//
//        @Override
//        public void start() {
//            this.mob.setTarget(this.target);
//            super.start();
//            OakieEntity.this.healAnimationState.start(OakieEntity.this.tickCount);
//        }
//
//        @Override
//        public void stop() {
//            super.stop();
//            OakieEntity.this.healAnimationState.stop();
//        }
//
//        @Override
//        public void tick() {
//            if (this.target == null) return;
//
//            if (healCoodDownTimer.pass(this::checkWhetherOwnerInEnchantingRange)) {
//                healCoodDownTimer.reset();
//                this.heal();
//            }
//        }
//
//        private void heal() {
//            this.target.setHealth(this.target.getHealth() + HEAL_AMOUNT);
//        }
//
//        private boolean checkWhetherOwnerInEnchantingRange() {
//            double distanceSq = this.distanceBetweenOakieAndOwner();
//            return distanceSq <= HEALING_RADIUS_SQUARED;
//        }
//
//        @Override
//        public boolean canUse() {
//            this.findOwner();
//            return OakieEntity.this.isTame() && this.checkOwnerConditions() && this.target != null;
//        }
//
//        private void findOwner() {
//            this.target = null;
//            if (this.isOwnerInNoticeRange()) {
//                this.target = OakieEntity.this.getOwner();
//            }
//        }
//
//        private boolean isOwnerInNoticeRange() {
//            if (OakieEntity.this.getOwner() == null) return false;
//            double distanceSq = this.distanceBetweenOakieAndOwner();
//            return distanceSq >= this.minNoticeDistance * this.minNoticeDistance && distanceSq <= this.maxNoticeDistance * this.maxNoticeDistance;
//        }
//
//        private double distanceBetweenOakieAndOwner() {
//            BlockPos ownerBlockPos = OakieEntity.this.getOwner().blockPosition();
//            BlockPos oakiePos = OakieEntity.this.blockPosition();
//            double dx = ownerBlockPos.getX() - oakiePos.getX();
//            double dz = ownerBlockPos.getZ() - oakiePos.getZ();
//            return dx * dx + dz * dz;
//        }
//
//        private boolean checkOwnerConditions() {
//            return OakieEntity.this.getOwner() != null && OakieEntity.this.getOwner().isAlive() && this.ownerNeedsToHeal();
//        }
//
//        private boolean ownerNeedsToHeal() {
//            double ownerHealth = OakieEntity.this.getOwner().getHealth();
//            double ownerMaxHealth = OakieEntity.this.getOwner().getMaxHealth();
//            return ownerHealth <= ownerMaxHealth * 0.8;
//        }
//    }

    /**
     * @author Mr. GodDavid
     * @since 10/19/2026
     */
    private class OakieGrowCropsGoal extends Goal {

        public static final int SPAWN_HAPPY_VILLAGER_PARTICLE_EVENT = 2011;

        @Override
        public boolean canUse() {
            return OakieEntity.this.isEnchanting();
        }

        @Override
        public void tick() {
            if (OakieEntity.this.random.nextInt(this.adjustedTickDelay(30)) == 0) {
                for (BlockPos candidateBlockPos : getBlockPosInCircle()) {
                    if (this.hasChanceToGrow()) {
                        BlockState state = OakieEntity.this.level().getBlockState(candidateBlockPos);
                        BlockState growState = getCropStateFromPos(candidateBlockPos, state);
                        this.applyGrowCrops(growState, candidateBlockPos);
                    }

                    for (int i = 1; i <= 2; i++) {
                        if (this.hasChanceToGrow()) {
                            // below two blocks
                            BlockPos belowPos = candidateBlockPos.below(i);
                            BlockState belowState = OakieEntity.this.level().getBlockState(belowPos);
                            BlockState growBelowState = getCropStateFromPos(belowPos, belowState);
                            this.applyGrowCrops(growBelowState, belowPos);
                            // above two blocks
                            BlockPos abovePos = candidateBlockPos.above(i);
                            BlockState aboveState = OakieEntity.this.level().getBlockState(abovePos);
                            BlockState growAboveState = getCropStateFromPos(abovePos, aboveState);
                            this.applyGrowCrops(growAboveState, abovePos);
                        }
                    }
                }
            }
        }

        private boolean hasChanceToGrow() {
            return OakieEntity.this.random.nextInt(20) == 0; // 5% of change growing crops around it
        }

        private List<BlockPos> getBlockPosInCircle() {
            BlockPos center = OakieEntity.this.blockPosition();
            BlockPos minCorner = center.offset(-HEALING_RADIUS, 0, -HEALING_RADIUS);
            BlockPos maxCorner = center.offset(HEALING_RADIUS, 0, HEALING_RADIUS);
            return BlockPos.betweenClosedStream(minCorner, maxCorner)
                    .filter(pos -> {
                        double dx = pos.getX() - center.getX();
                        double dz = pos.getZ() - center.getZ();
                        double distanceSquared = dx * dx + dz * dz;
                        return distanceSquared <= HEALING_RADIUS_SQUARED;
                    })
                    .map(BlockPos::immutable)
                    .collect(Collectors.toList());
        }

        private void applyGrowCrops(BlockState cropState, BlockPos blockPos) {
            if (cropState != null) {
                OakieEntity.this.level().levelEvent(SPAWN_HAPPY_VILLAGER_PARTICLE_EVENT, blockPos, 15);
                OakieEntity.this.level().setBlockAndUpdate(blockPos, cropState);
            }
        }

        /**
         * I copied this from vanilla bee's {@code BeeGrowCropGoal} class.
         *
         * @param blockPos   position of crop below/above the oakie, as well as in the oakie's effectiveness region.
         * @param blockState state of the crop below/above the oakie, as well as in the oakie's effectiveness region.
         * @return the grown state of the crop below/above the oakie, as well as in the oakie's effectiveness region.
         */
        private BlockState getCropStateFromPos(BlockPos blockPos, BlockState blockState) {
            BlockState growState = null;
            Block block = blockState.getBlock();
            if (blockState.is(MtsTags.Entities.OAKIE_GROWABLES)) {
                if (block instanceof CropBlock cropBlockBelow) {
                    if (!cropBlockBelow.isMaxAge(blockState)) {
                        growState = cropBlockBelow.getStateForAge(cropBlockBelow.getAge(blockState) + 1);
                    }
                } else if (block instanceof StemBlock) {
                    int age = blockState.getValue(StemBlock.AGE);
                    if (age < 7) {
                        growState = blockState.setValue(StemBlock.AGE, age + 1);
                    }
                } else if (blockState.is(Blocks.SWEET_BERRY_BUSH) || blockState.is(MtsBlocks.BLUEBERRY_BUSH)) {
                    int age = blockState.getValue(SweetBerryBushBlock.AGE);
                    if (age < 3) {
                        growState = blockState.setValue(SweetBerryBushBlock.AGE, age + 1);
                    }
                } else if (blockState.is(Blocks.CAVE_VINES) || blockState.is(Blocks.CAVE_VINES_PLANT)) {
                    BonemealableBlock bonemealableBlock = (BonemealableBlock) blockState.getBlock();
                    if (bonemealableBlock.isValidBonemealTarget(OakieEntity.this.level(), blockPos, blockState)) {
                        bonemealableBlock.performBonemeal((ServerLevel) OakieEntity.this.level(), OakieEntity.this.random, blockPos, blockState);
                        growState = OakieEntity.this.level().getBlockState(blockPos);
                    }
                }
            }
            return growState;
        }
    }
}
