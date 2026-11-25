package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 11/25/2026
 */
@SuppressWarnings("NullableProblems")
public final class MtsBlockColorRegistries {

    private static final MtsBlockColorRegisterer MTS_LEAVES_TINTED_LAYERS = new MtsBlockColorRegisterer()
            .addBlock(new BlockTintedLayer(MtsBlocks.LEMON_TREE_LEAVES)
                    .addTintLayer(0x819604))
            .addBlock(new BlockTintedLayer(MtsBlocks.ANCIENT_OAK_LEAVES)
                    .addTintLayer(0x2D6464));

    public static void register() {
        MtsLogger.info("Custom Block Colors");

//        BlockColorRegistry.register(List.of(new BlockTintSource() {
//
//            @Override
//            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
//                return BiomeColors.getAverageFoliageColor(level, pos);
//            }
//
//            @Override
//            public int color(BlockState state) {
//                return 0x819604;
//            }
//        }), MtsBlocks.LEMON_TREE_LEAVES);

        registerAll(MTS_LEAVES_TINTED_LAYERS);
    }

    private static void registerAll(MtsBlockColorRegisterer registerer) {
        for (BlockTintedLayer blockTintedLayer : registerer.getRegisteredBlocks()) {
            BlockColorRegistry.register(blockTintedLayer.blockTintSources(), blockTintedLayer.tintedBlock());
        }

    }

    /**
     * @author Mr. GodDavid
     * @since 11/25/2026
     */
    private static class MtsBlockColorRegisterer {
        private final List<BlockTintedLayer> registeredBlocks = Lists.newArrayList();

        private MtsBlockColorRegisterer addBlock(BlockTintedLayer blockTintedLayer) {
            registeredBlocks.add(blockTintedLayer);
            return this;
        }

        public List<BlockTintedLayer> getRegisteredBlocks() {
            return this.registeredBlocks;
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 11/25/2026
     */
    private record BlockTintedLayer(Block tintedBlock, List<BlockTintSource> blockTintSources) {

        public BlockTintedLayer(Block tintedBlock) {
            this(tintedBlock, Lists.newArrayList());
        }

        public BlockTintedLayer addTintLayer(final int tint$rgb) {
            this.blockTintSources.add(new BlockTintSource() {

                @Override
                public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                    return BiomeColors.getAverageFoliageColor(level, pos);
                }

                @Override
                public int color(BlockState state) {
                    return tint$rgb;
                }
            });
            return this;
        }
    }

    private MtsBlockColorRegistries() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
