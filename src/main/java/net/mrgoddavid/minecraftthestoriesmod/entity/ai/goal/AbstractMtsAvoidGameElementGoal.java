package net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.client.EntityUtils;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;

/**
 * @author Mr. GodDavid
 * @since 11/22/2026
 */
public abstract class AbstractMtsAvoidGameElementGoal extends Goal {

    protected final PathfinderMob mob;
    private final double walkSpeedMultiplier;
    private final double sprintSpeedMultiplier;
    protected final PathNavigation pathNavigation;
    protected final float maxDist;
    protected @Nullable BlockPos avoidBlockPos; // the block position of the avoid target block.
    protected @Nullable Path path;

    public AbstractMtsAvoidGameElementGoal(
            final PathfinderMob mob,
            final float maxDis,
            final double walkSpeedMultiplier,
            final double sprintSpeedMultiplier
    ) {
        this.mob = mob;
        this.maxDist = maxDis;
        this.walkSpeedMultiplier = walkSpeedMultiplier;
        this.sprintSpeedMultiplier = sprintSpeedMultiplier;
        this.pathNavigation = mob.getNavigation();
        this.setFlags(EnumSet.of((Flag.MOVE)));
    }

    @Override
    public boolean canUse() {
        if (this.avoidBlockPos == null) return false;

        Vec3 avoidBlockPos = Vec3.atCenterOf(this.avoidBlockPos);
        Vec3 fleePos = DefaultRandomPos.getPosAway(this.mob, 16, 7, avoidBlockPos);
        if (fleePos == null) return false;

        this.path = this.pathNavigation.createPath(fleePos.x(), fleePos.y(), fleePos.z(), 0);
        return this.path != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.avoidBlockPos != null && !this.pathNavigation.isDone();
    }

    @Override
    public void start() {
        if (this.path == null) return;

        this.pathNavigation.moveTo(this.path, this.walkSpeedMultiplier);
    }

    @Override
    public void stop() {
        this.avoidBlockPos = null;
        this.pathNavigation.stop();
        this.path = null;
    }

    @Override
    public void tick() {
        if (this.avoidBlockPos == null) return;

        double distanceSq = EntityUtils.distanceSqBetweenMobAndBlockPos(this.mob, this.avoidBlockPos);
        if (distanceSq < maxDist * maxDist * 0.5) {
            this.mob.getNavigation().setSpeedModifier(this.sprintSpeedMultiplier);
        } else {
            this.mob.getNavigation().setSpeedModifier(this.walkSpeedMultiplier);
        }
    }
}
