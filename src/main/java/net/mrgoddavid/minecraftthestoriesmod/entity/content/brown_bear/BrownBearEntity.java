package net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.entity.MtsEntityTypes;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 9/7/2026
 */
@SuppressWarnings("resource")
public class BrownBearEntity extends TamableAnimal implements NeutralMob {

    public static final TargetingConditions.Selector PREY_SELECTOR = selectPrey();
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(15, 29);
    private long persistentAngerEndTime;
    private @NonNull EntityReference<LivingEntity> persistentAngerTarget;
    private @Nullable EntityReference<LivingEntity> owner;
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState runAnimationState = new AnimationState();
    public final AnimationState tameAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState standAnimationState = new AnimationState();
    private boolean pendingSitAnimation;
    private static final byte ATTACK_EVENT = 100;

    public BrownBearEntity(EntityType<? extends BrownBearEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TamableAnimalPanicGoal(1.25, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
        this.goalSelector.addGoal(6, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 20, true, true, this::playerDoesBadThings));
        this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Bee.class, 10, true, false, (bee, level) -> !this.isBaby() && bee.isBaby()));
        this.targetSelector.addGoal(7, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    private boolean playerDoesBadThings(LivingEntity livingEntity, ServerLevel serverLevel) {
        if (livingEntity instanceof Player player) {
            ItemStack item = player.getMainHandItem();
            return item.is(MtsTags.Entities.AGRO_BROWN_BEAR_ITEMS) && this.isAngryAt(player, serverLevel);
        }
        return false;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        boolean isItemCorrect = itemStack.is(Items.COD);
        if (!this.level().isClientSide()) {
            if (!isTame() && !isBaby() && isItemCorrect) {
                itemStack.consume(1, player);

                tame(player);
                setOrderedToSit(true);
                level().broadcastEntityEvent(this, EntityEvent.TAMING_SUCCEEDED);
                return InteractionResult.SUCCESS;
            } else if (isTame()) {
                InteractionResult result = super.mobInteract(player, hand);
                if (result.consumesAction() || !isOwnedBy(player)) {
                    return result;
                }

                setOrderedToSit(!isOrderedToSit());
                return InteractionResult.SUCCESS;
            }
        }

        return isOwnedBy(player) || isTame() || isItemCorrect ? InteractionResult.CONSUME : InteractionResult.PASS;
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        boolean success = super.doHurtTarget(level, target);
        if (success) {
            level().broadcastEntityEvent(this, ATTACK_EVENT);
        }
        return success;
    }

    @Override
    public void tick() {
        if (level().isClientSide()) {
            this.idleAnimationState.animateWhen(!isInWater() && !this.walkAnimation.isMoving(), this.tickCount);
            this.runAnimationState.animateWhen(!isInWater() && this.walkAnimation.isMoving() && this.getTarget() != null && this.getTarget().isAlive() && this.navigation.isInProgress(), this.tickCount);

            if (this.attackAnimationState.isStarted() && this.attackAnimationState.getTimeInMillis(this.tickCount) > 999L) {
                LivingEntity target = this.getTarget();
                if (target == null || !target.isAlive()) {
                    this.attackAnimationState.stop();
                }
            }

            if (this.tameAnimationState.isStarted() && this.tameAnimationState.getTimeInMillis(this.tickCount) > 1000L) {
                this.tameAnimationState.stop();
                if (pendingSitAnimation) {
                    this.sitAnimationState.startIfStopped(this.tickCount);
                    this.pendingSitAnimation = false;
                }
            }
        }

        super.tick();
    }

    @Override
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);

        if (id == ATTACK_EVENT) {
            this.attackAnimationState.start(this.tickCount);
        }
        if (id == EntityEvent.TAMING_SUCCEEDED) {
            this.tameAnimationState.start(this.tickCount);
        }
    }

    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        setInSittingPose(orderedToSit);

        this.jumping = false;
        this.navigation.stop();
        setTarget(null);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        if (DATA_FLAGS_ID.equals(accessor)) {
            if (isInSittingPose()) {
                if (this.tameAnimationState.isStarted()) {
                    this.pendingSitAnimation = true;
                    this.sitAnimationState.stop();
                } else {
                    this.sitAnimationState.startIfStopped(this.tickCount);
                }
            } else {
                this.pendingSitAnimation = false;
                this.sitAnimationState.stop();
            }
        }

        super.onSyncedDataUpdated(accessor);
    }

    @Override
    protected void applyTamingSideEffects() {
        super.applyTamingSideEffects();

        if (!this.level().isClientSide()) {
            EntityTypes.PIG.spawn((ServerLevel) level(), blockPosition(), EntitySpawnReason.SPAWN_ITEM_USE);
        }
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(MtsTags.Entities.BREED_BROWN_BEAR_ITEMS);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return new BrownBearEntity(MtsEntityTypes.BROWN_BEAR, level);
    }

    public static AttributeSupplier.Builder createCubeAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.ATTACK_DAMAGE, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 20.0);
    }

    @Override
    public long getPersistentAngerEndTime() {
        return this.persistentAngerEndTime;
    }

    @Override
    public void setPersistentAngerEndTime(long endTime) {
        this.persistentAngerEndTime = endTime;
    }

    @Override
    public @Nullable EntityReference<LivingEntity> getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable EntityReference<LivingEntity> persistentAngerTarget) {
        this.persistentAngerTarget = persistentAngerTarget;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setTimeToRemainAngry(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    private static TargetingConditions.@NonNull Selector selectPrey() {
        return (target, level) -> target.is(EntityTypes.SHEEP)
                || target.is(EntityTypes.COW) || target.is(EntityTypes.PIG) || target.is(EntityTypes.CHICKEN) || target.is(EntityTypes.FOX) || target.is(EntityTypes.WOLF);
    }
}
