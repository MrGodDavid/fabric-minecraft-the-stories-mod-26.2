package net.mrgoddavid.minecraftthestoriesmod.test;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;

import java.util.function.Predicate;

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
        Builder.fill(2, 2, 2, 5, 5, 5, MtsBlocks.STRONG_AMETHYST_BLOCK);
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

        public static void fillReplace(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, BlockState blockState, Block replace) {
            fillReplaceWhere(minX, minY, minZ, maxX, maxY, maxZ, blockState, blockState1 -> blockState1.equals(replace.defaultBlockState()));
        }

        public static void fillReplace(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, BlockState blockState, BlockState replace) {
            fillReplaceWhere(minX, minY, minZ, maxX, maxY, maxZ, blockState, blockState1 -> blockState1.equals(replace));
        }

        public static void fillReplaceWhere(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, BlockState blockState, Predicate<BlockState> predicate) {
            BlockPos.betweenClosedStream(pos(minX, minY, minZ), pos(maxX, maxY, maxZ)).forEach(pos -> {
                if (predicate.test(MtsTestWorldContext.overworld().getBlockState(pos))) {
                    MtsTestWorldContext.overworld().setBlockAndUpdate(pos, blockState);
                }
            });
        }
    }
}
