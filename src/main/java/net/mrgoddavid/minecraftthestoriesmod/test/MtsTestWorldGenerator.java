package net.mrgoddavid.minecraftthestoriesmod.test;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;

/**
 * MTS Test World generator.
 *
 * @author Mr. GodDavid
 * @since 8/24/2026
 */
public class MtsTestWorldGenerator {

    private MtsTestWorldGenerator() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!!");
    }

    public static void generate() {
        Builder.setBlock(0, 0, 1, MtsBlocks.STRONG_AMETHYST_BLOCK);
    }

    /**
     * MTS Test World builder.
     *
     * @author Mr. GodDavid
     * @since 8/24/2026
     */
    public static final class Builder {

        public static BlockPos pos(int x, int y, int z) {
            return MtsTestWorldContext.origin().offset(x, y, z);
        }

        public static void setBlock(int x, int y, int z, Block block) {
            setBlock(x, y, z, block.defaultBlockState());
        }

        public static void setBlock(int x, int y, int z, BlockState blockState) {
            MtsTestWorldContext.overworld().setBlockAndUpdate(pos(x, y, z), blockState);
        }

        public static void removeBlock(int x, int y, int z) {
            MtsTestWorldContext.overworld().removeBlock(pos(x, y, z), false);
        }

        public static void fill(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, Block block) {
            fill(minX, minY, minZ, maxX, maxY, maxZ, block.defaultBlockState());
        }

        public static void fill(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, BlockState blockState) {
            BlockPos.betweenClosedStream(pos(minX, minY, minZ), pos(maxX, maxY, maxZ)).forEach(pos -> MtsTestWorldContext.overworld().setBlockAndUpdate(pos, blockState));
        }
    }
}
