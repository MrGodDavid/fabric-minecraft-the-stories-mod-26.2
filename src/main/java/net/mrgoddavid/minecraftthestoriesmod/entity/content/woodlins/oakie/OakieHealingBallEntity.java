package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal.FollowSpecificMobGoal;
import org.jspecify.annotations.Nullable;

import java.util.Comparator;

/**
 * @author Mr. GodDavid
 * @since 10/25/2026
 */
@SuppressWarnings("NullableProblems")
public class OakieHealingBallEntity extends PathfinderMob {

    private static final EntityDataAccessor<Byte> DATA_LIFECYCLE = SynchedEntityData.defineId(OakieHealingBallEntity.class, EntityDataSerializers.BYTE);
    public final AnimationState spawnAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();
    public final AnimationState healAnimationState = new AnimationState();
    private final float HEAL_AMOUNT = 3.0F;
    private final double HEAL_DISTANCE = 4.0D;
    private final int EMERGING_DURATION_TICKS = 20;
    private final int DYING_DURATION_TICKS = 20;
    private int lifeCycleTicks;
    private LifeCycle lastClientLifeCycle;

    public OakieHealingBallEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl<OakieHealingBallEntity>(this, 20, true);
        this.setNoGravity(true);
        this.setNoAi(true);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_LIFECYCLE, LifeCycle.EMERGING.getId());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FollowSpecificMobGoal<>(this, Player.class, 1.01d, 40.0f, 0.1f, this::selectValidPlayer));
    }

    private boolean selectValidPlayer(Player player) {
        return this.isFlying() && !player.isSpectator() && player.isAlive();
    }

    public static AttributeSupplier createCubeAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.FLYING_SPEED, 0.4)
                .build();
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, level);
        flyingPathNavigation.setCanOpenDoors(false);
        flyingPathNavigation.setCanFloat(true);
        flyingPathNavigation.setRequiredPathLength(24.0F);
        return flyingPathNavigation;
    }

    @Override
    public void tick() {
        super.tick();

        // CLIENT SIDE.
        if (level().isClientSide()) {
            this.updateAnimationStates();
        }
        // SERVER SIDE.
        else {
            this.tickLifeCycle();
            if (this.isFlying()) {
                this.tryHealNearbyPlayer();
            }
        }
    }

    private void tryHealNearbyPlayer() {
        Player player = this.level()
                .getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(HEAL_DISTANCE), this::selectAroundPlayer)
                .stream().min(Comparator.comparingDouble(Player::getHealth)).orElse(null);
        if (player != null) {
            player.heal(HEAL_AMOUNT);
            this.beginDying();
        }
    }

    private boolean selectAroundPlayer(Player player) {
        return !player.isSpectator() && player.isAlive() && this.distanceToSqr(player) < HEAL_DISTANCE * HEAL_DISTANCE && player.getHealth() < player.getMaxHealth();
    }

    private void tickLifeCycle() {
        switch (this.getLifeCycle()) {
            case EMERGING:
                if (++this.lifeCycleTicks >= EMERGING_DURATION_TICKS) {
                    this.setLifeCycle(LifeCycle.FLYING);
                }
                break;
            case FLYING:
                break;
            case DYING:
                if (++this.lifeCycleTicks >= DYING_DURATION_TICKS) {
                    this.discard();
                }
                break;
        }
    }

    private void updateAnimationStates() {
        LifeCycle lifeCycle = this.getLifeCycle();
        if (lifeCycle == this.lastClientLifeCycle) return;

        this.spawnAnimationState.stop();
        this.flyingAnimationState.stop();
        this.healAnimationState.stop();

        switch (lifeCycle) {
            case EMERGING -> this.spawnAnimationState.start(this.tickCount);
            case FLYING -> this.flyingAnimationState.start(this.tickCount);
            case DYING -> this.healAnimationState.start(this.tickCount);
        }
        this.lastClientLifeCycle = lifeCycle;
    }

    private LifeCycle getLifeCycle() {
        return LifeCycle.fromId(this.entityData.get(DATA_LIFECYCLE));
    }

    private void setLifeCycle(LifeCycle lifeCycle) {
        this.entityData.set(DATA_LIFECYCLE, lifeCycle.getId());
        this.lifeCycleTicks = 0;

        boolean isFlying = lifeCycle == LifeCycle.FLYING;
        this.setNoAi(!isFlying);
        if (!isFlying) {
            this.navigation.stop();
            this.setDeltaMovement(Vec3.ZERO);
        }
    }

    private boolean isFlying() {
        return this.getLifeCycle() == LifeCycle.FLYING;
    }

    private boolean isDying() {
        return this.getLifeCycle() == LifeCycle.DYING;
    }

    private void beginDying() {
        if (this.level().isClientSide() || this.isDying()) return;
        this.setLifeCycle(LifeCycle.DYING);
    }

    /**
     * @author Mr. GodDavid
     * @since 10/28/2026
     */
    private enum LifeCycle {

        EMERGING((byte) 0),
        FLYING((byte) 1),
        DYING((byte) 2);

        private final byte id;

        LifeCycle(byte id) {
            this.id = id;
        }

        private byte getId() {
            return this.id;
        }

        private static @Nullable LifeCycle fromId(byte id) {
            for (LifeCycle lifeCycle : LifeCycle.values()) {
                if (lifeCycle.getId() == id) {
                    return lifeCycle;
                }
            }
            return null;
        }
    }
}
