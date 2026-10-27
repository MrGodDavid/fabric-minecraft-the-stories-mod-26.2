package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;

/**
 * @author Mr. GodDavid
 * @since 10/25/2026
 */
@SuppressWarnings("NullableProblems")
public class OakieHealingBallEntity extends PathfinderMob {

    public final AnimationState spawnAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();
    public final AnimationState healAnimationState = new AnimationState();

    public OakieHealingBallEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier createCubeAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.FLYING_SPEED, 0.4 )
                .build();
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, level);
        flyingPathNavigation.setCanOpenDoors(false);
        flyingPathNavigation.setCanFloat(true);
        flyingPathNavigation.setRequiredPathLength(48.0F);
        return flyingPathNavigation;
    }

    @Override
    public void tick() {
        super.tick();

        if (level().isClientSide()) {
            this.flyingAnimationState.animateWhen(!this.walkAnimation.isMoving(), this.tickCount);
        }
    }
}
