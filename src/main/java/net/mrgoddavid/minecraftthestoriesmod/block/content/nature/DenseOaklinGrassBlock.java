package net.mrgoddavid.minecraftthestoriesmod.block.content.nature;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;

/**
 * @author Mr. GodDavid
 * @since 11/26/2026
 */
@SuppressWarnings("NullableProblems")
public class DenseOaklinGrassBlock extends GrassBlock {

    public DenseOaklinGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        if (level.getBlockState(pos).is(Blocks.DIRT)) {
            level.setBlockAndUpdate(pos, MtsBlocks.DENSE_OAKLIN_DIRT.defaultBlockState());
        }
    }
}
