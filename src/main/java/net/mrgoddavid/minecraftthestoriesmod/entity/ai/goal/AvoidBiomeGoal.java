package net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.client.EntityUtils;

import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 11/22/2026
 */
public class AvoidBiomeGoal extends AbstractMtsAvoidGameElementGoal {

    private final PathfinderMob mob;
    private final List<ResourceKey<Biome>> forbiddenBiomes;
    private static final int[] SEARCH_RADII = new int[]{16, 32, 48};

    public AvoidBiomeGoal(
            final PathfinderMob mob,
            final ResourceKey<Biome> forbiddenBiome,
            final float maxDis,
            final double walkSpeedMultiplier,
            final double sprintSpeedMultiplier
    ) {
        this(mob, List.of(forbiddenBiome), maxDis, walkSpeedMultiplier, sprintSpeedMultiplier);
    }

    public AvoidBiomeGoal(
            final PathfinderMob mob,
            final List<ResourceKey<Biome>> forbiddenBiomes,
            final float maxDis,
            final double walkSpeedMultiplier,
            final double sprintSpeedMultiplier
    ) {
        super(mob, maxDis, walkSpeedMultiplier, sprintSpeedMultiplier);
        this.mob = mob;
        this.forbiddenBiomes = forbiddenBiomes;
    }

    @Override
    public boolean canUse() {
        this.path = null;
        BlockPos currentPos = this.mob.blockPosition();
        this.avoidBlockPos = EntityUtils.inAnyOfForbiddenBiomes(this.mob, this.forbiddenBiomes, currentPos)
                ? currentPos : null;
        if (this.avoidBlockPos == null) return false;

        Vec3 avoidBlockPos = Vec3.atCenterOf(this.avoidBlockPos);
        Vec3 fleePos = attemptFindFleePos(avoidBlockPos);
        if (fleePos == null) return false;

        this.path = this.pathNavigation.createPath(fleePos.x(), fleePos.y(), fleePos.z(), 0);
        return this.path != null;
    }

    private Vec3 attemptFindFleePos(Vec3 avoidBlockPos) {
        for (int radius : SEARCH_RADII) {
            for (int attempt = 0; attempt < 15; attempt++) {
                Vec3 candidate = LandRandomPos.getPosAway(this.mob, radius, 8, avoidBlockPos);
                if (candidate != null && !EntityUtils.inAnyOfForbiddenBiomes(this.mob, this.forbiddenBiomes, BlockPos.containing(candidate))) {
                    return candidate;
                }
            }
        }
        return null;
    }
}
