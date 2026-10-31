package net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.client.EntityUtils;
import net.mrgoddavid.minecraftthestoriesmod.utils.math.MathUtils;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.Predicate;

/**
 * @author Mr. GodDavid
 * @since 10/21/2026
 */
public class AvoidBlockGoal extends Goal {

    private final float maxDist;
    private final double walkSpeedMultiplier;
    private final double sprintSpeedMultiplier;
    private final int searchRadius;
    private final Predicate<BlockState> blockSelector;
    protected final PathNavigation pathNavigation;
    protected final PathfinderMob mob;
    protected @Nullable BlockPos avoidBlockPos; // the block position of the avoid target block.
    protected @Nullable Path path;

    public AvoidBlockGoal(final PathfinderMob mob, Predicate<BlockState> blockSelector, final float maxDis, final double walkSpeedMultiplier, final double sprintSpeedMultiplier, final int searchRadius) {
        this.mob = mob;
        this.blockSelector = blockSelector;
        this.maxDist = maxDis;
        this.walkSpeedMultiplier = walkSpeedMultiplier;
        this.sprintSpeedMultiplier = sprintSpeedMultiplier;
        this.searchRadius = searchRadius;
        this.pathNavigation = mob.getNavigation();
        this.setFlags(EnumSet.of((Flag.MOVE)));
    }

    @Override
    public boolean canUse() {
        this.path = null;
        this.avoidBlockPos = EntityUtils.getNearestBlockPos(this.mob, this.blockSelector, this.searchRadius, this.searchRadius);
        if (avoidBlockPos == null) return false;

        Vec3 avoidBlockVec = Vec3.atCenterOf(avoidBlockPos);
        Vec3 fleePos = DefaultRandomPos.getPosAway(this.mob, 16, 7, avoidBlockVec);
        if (fleePos == null) return false;

        this.path = this.pathNavigation.createPath(fleePos.x, fleePos.y, fleePos.z, 0);
        return this.path != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.avoidBlockPos != null && !this.pathNavigation.isDone();
    }

    @Override
    public void tick() {
        if (this.avoidBlockPos == null) return;

        double distanceSq = MathUtils.distanceSqBetweenMobAndBlockPos(this.mob, this.avoidBlockPos);
        if (distanceSq < maxDist * maxDist * 0.5) {
            this.mob.getNavigation().setSpeedModifier(this.sprintSpeedMultiplier);
        } else {
            this.mob.getNavigation().setSpeedModifier(this.walkSpeedMultiplier);
        }
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
}
