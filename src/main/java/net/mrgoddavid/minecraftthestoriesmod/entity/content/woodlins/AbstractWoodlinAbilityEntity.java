package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal.FollowSpecificMobGoal;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie.OakieHealingBallEntity;
import org.jspecify.annotations.Nullable;

/**
 * @author Mr. GodDavid
 * @since 11/5/2026
 */
public abstract class AbstractWoodlinAbilityEntity extends PathfinderMob {

    private static final int FOLLOW_DISTANCE = 20;
    private static final int EMERGING_DURATION_TICKS = 20;
    private static final int DYING_DURATION_TICKS = 20;
    private static final EntityDataAccessor<Byte> DATA_LIFECYCLE = SynchedEntityData.defineId(AbstractWoodlinAbilityEntity.class, EntityDataSerializers.BYTE);
    public final AnimationState spawnAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();
    public final AnimationState healAnimationState = new AnimationState();
    private int lifeCycleTicks;
    private LifeCycle lastClientLifeCycle;

    protected AbstractWoodlinAbilityEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl<>(this, 20, true);
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
        this.goalSelector.addGoal(1, new FollowSpecificMobGoal<>(this, Player.class, 1.01d, FOLLOW_DISTANCE, 0.1f, this::selectValidPlayer));
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
                this.tryUseAbilityToNearbyPlayer();
            }
        }
    }

    protected abstract void tryUseAbilityToNearbyPlayer();

    private boolean selectValidPlayer(Player player) {
        return this.isFlying() && this.validPlayerBaseCriteria(player);
    }

    protected final boolean validPlayerBaseCriteria(Player player) {
        return !player.isCreative() && !player.isSpectator() && player.isAlive();
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

    protected boolean isFlying() {
        return this.getLifeCycle() == LifeCycle.FLYING;
    }

    protected boolean isDying() {
        return this.getLifeCycle() == LifeCycle.DYING;
    }

    protected void beginDying() {
        if (this.level().isClientSide() || this.isDying()) return;
        this.setLifeCycle(LifeCycle.DYING);
    }

    protected void setLifeCycle(LifeCycle lifeCycle) {
        this.entityData.set(DATA_LIFECYCLE, lifeCycle.getId());
        this.lifeCycleTicks = 0;
        boolean isFlying = lifeCycle == LifeCycle.FLYING;
        this.setNoAi(!isFlying);
        if (!isFlying) {
            this.navigation.stop();
            this.setDeltaMovement(Vec3.ZERO);
        }
    }

    protected LifeCycle getLifeCycle() {
        return LifeCycle.fromId(this.entityData.get(DATA_LIFECYCLE));
    }

    /**
     * @author Mr. GodDavid
     * @since 10/28/2026 (originally created inside {@link OakieHealingBallEntity}.
     * @since 11/5/2026 (moved to this class.)
     */
    protected enum LifeCycle {

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
