package net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.block.state.BlockState;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.client.EntityUtils;

import java.util.function.Predicate;

/**
 * @author Mr. GodDavid
 * @since 10/21/2026
 */
public class AvoidBlockGoal extends AbstractMtsAvoidGameElementGoal {

    private final int searchRadius;
    private final Predicate<BlockState> blockSelector;

    public AvoidBlockGoal(
            final PathfinderMob mob,
            final Predicate<BlockState> blockSelector,
            final float maxDis,
            final double walkSpeedMultiplier,
            final double sprintSpeedMultiplier,
            final int searchRadius
    ) {
        super(mob, maxDis, walkSpeedMultiplier, sprintSpeedMultiplier);
        this.blockSelector = blockSelector;
        this.searchRadius = searchRadius;
    }

    @Override
    public boolean canUse() {
        this.path = null;
        this.avoidBlockPos = EntityUtils.getNearestBlockPos(this.mob, this.blockSelector, this.searchRadius, this.searchRadius);
       return super.canUse();
    }
}
