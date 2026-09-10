package net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
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
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.entity.MtsEntityTypes;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

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
    private static final byte STAND_EVENT = 99;
    private static final EntityDataAccessor<Boolean> DATA_CHARGING = SynchedEntityData.defineId(BrownBearEntity.class, EntityDataSerializers.BOOLEAN);
    public boolean animateWalkingAnimationWhenRiding;

    public BrownBearEntity(EntityType<? extends BrownBearEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_CHARGING, false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TamableAnimalPanicGoal(1.25, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new BrownBearEntity.BrownBearAttackGoal(this, this.chargeSpeedModifier(), true));
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
                tryToTame(player);
                return InteractionResult.SUCCESS;

            }
            if (isTame() && isOwnedBy(player)) {
                if (this.getPassengers().isEmpty()) {
                    this.setOrderedToSit(false);
                    player.startRiding(this);
                    return InteractionResult.SUCCESS;
                }

                InteractionResult result = super.mobInteract(player, hand);
                if (result.consumesAction()) {
                    return result;
                }

                setOrderedToSit(!isOrderedToSit());
                return InteractionResult.SUCCESS;
            }
        }

        return isOwnedBy(player) || isTame() || isItemCorrect ? InteractionResult.CONSUME : InteractionResult.PASS;
    }

    @Override
    protected boolean isLocalClientAuthoritative() {
        return this.getControllingPassenger() instanceof Player;
    }

    @Override
    public @Nullable LivingEntity getControllingPassenger() {
        return this.getFirstPassenger() instanceof Player player ? player : null;
    }

    @Override
    protected void tickRidden(Player controller, Vec3 riddenInput) {
        super.tickRidden(controller, riddenInput);

        Vec2 rotation = this.getRiddenRotation(controller);
        this.setRot(rotation.y, rotation.x);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction moveFunction) {
        super.positionRider(passenger, moveFunction);
        double backwardsOffset = 0.25d;
        double radians = Math.toRadians(this.getYRot());

        double x = passenger.getX() + Math.sin(radians) * backwardsOffset;
        double z = passenger.getZ() - Math.cos(radians) * backwardsOffset;

        passenger.setPos(x, this.getY() + this.getBbHeight() * 0.9D, z);
    }

    @Override
    protected Vec3 getRiddenInput(Player controller, Vec3 selfInput) {
        float sideways = controller.xxa * 0.5F;
        float forward = controller.zza;
        if (forward <= 0.0F) {
            forward *= 0.25F;
        }
        return new Vec3(sideways, 0.0, forward);
    }

    @Override
    protected float getRiddenSpeed(Player controller) {
        return (float) this.getAttribute(Attributes.MOVEMENT_SPEED).getValue();
    }

    private Vec2 getRiddenRotation(LivingEntity controller) {
        return new Vec2(controller.getXRot() * 0.5F, controller.getYRot());
    }

    @Override
    public void travel(Vec3 input) {
        if (this.isAlive() && this.isVehicle() && this.getControllingPassenger() instanceof Player player) {
            Vec3 riddenInput = this.getRiddenInput(player, input);
            this.setSpeed(this.getRiddenSpeed(player));
            super.travel(riddenInput);
        } else {
            super.travel(input);
        }
    }

    private void tryToTame(Player player) {
        if (this.random.nextInt(5) == 0) {
            tame(player);
            setOrderedToSit(true);
            level().broadcastEntityEvent(this, EntityEvent.TAMING_SUCCEEDED);
        }
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
    public float chargeSpeedModifier() {
        return 2.0F;
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide()) {
            entityData.set(DATA_CHARGING, isCharging());
        }

        if (level().isClientSide()) {
            this.idleAnimationState.animateWhen(!isInWater() && !this.walkAnimation.isMoving(), this.tickCount);

            animateWalkingAnimationWhenRiding = this.isVehicle() && this.getDeltaMovement().horizontalDistanceSqr() > 0.0001;

            if (this.entityData.get(DATA_CHARGING) || animateWalkingAnimationWhenRiding) {
                this.runAnimationState.startIfStopped(this.tickCount);
            } else {
                this.runAnimationState.stop();
            }

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

            if (this.standAnimationState.isStarted() && this.standAnimationState.getTimeInMillis(this.tickCount) > 1000L) {
                this.standAnimationState.stop();
            }
        }
    }

    private boolean isCharging() {
        return !this.isVehicle() && this.getTarget() != null && this.getTarget().isAlive();
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

        if (id == STAND_EVENT) {
            System.out.println("here");
            this.standAnimationState.start(this.tickCount);
        }
    }

    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        setInSittingPose(orderedToSit);

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

    private class BrownBearAttackGoal extends MeleeAttackGoal {

        public BrownBearAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
            super(mob, speedModifier, followingTargetEvenIfNotSeen);
        }

        @Override
        public boolean canUse() {
            return this.mob.getPassengers().isEmpty() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return this.mob.getPassengers().isEmpty() && super.canContinueToUse();
        }
    }
}
