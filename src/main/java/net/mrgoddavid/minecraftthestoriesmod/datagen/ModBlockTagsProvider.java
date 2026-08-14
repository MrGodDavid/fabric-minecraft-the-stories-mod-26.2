package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.mrgoddavid.minecraftthestoriesmod.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

import static net.mrgoddavid.minecraftthestoriesmod.block.ModBlocks.*;

/**
 * Adapts the {@link BlockTagsProvider} class. Generates the JSON files tags of mod blocks. A tag is a collection of
 * blocks, items, or entities. Basically, a tag is whatever type of thing that we're adding. Tages give us a common
 * purpose. For example, you have all kinds of fenses in a fense tag, or all the minable blocks of
 * {@code Iron Pickaxe} in a {@code Needs_Iron_Pickaxe} (or equivlanet) tag.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    /**
     * Implement this method and then use {@link FabricTagsProvider#builder} to get and register new tag builders.
     *
     * @param registries registries.
     */
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getResourceKey(STONE_AMETHYST_ORE))
                .add(ModBlocks.getResourceKey(DEEPSLATE_AMETHYST_ORE))
                .add(ModBlocks.getResourceKey(RAW_AMETHYST_BLOCK))
                .add(ModBlocks.getResourceKey(STRONG_AMETHYST_BLOCK))
                .add(ModBlocks.getResourceKey(RAW_DIAMOND_BLOCK))
                .add(ModBlocks.getResourceKey(STRONG_DIAMOND_BLOCK))
                .add(ModBlocks.getResourceKey(RAW_EMERALD_BLOCK))
                .add(ModBlocks.getResourceKey(STRONG_EMERALD_BLOCK))
                .add(ModBlocks.getResourceKey(STONE_TOPAZ_ORE))
                .add(ModBlocks.getResourceKey(DEEPSLATE_TOPAZ_ORE))
                .add(ModBlocks.getResourceKey(RAW_TOPAZ_BLOCK))
                .add(ModBlocks.getResourceKey(STRONG_TOPAZ_BLOCK))
                .add(ModBlocks.getResourceKey(STONE_RUBY_ORE))
                .add(ModBlocks.getResourceKey(DEEPSLATE_RUBY_ORE))
                .add(ModBlocks.getResourceKey(RAW_RUBY_BLOCK))
                .add(ModBlocks.getResourceKey(STRONG_RUBY_BLOCK))
                .add(ModBlocks.getResourceKey(STONE_AMETHYST_ORE))
                .add(ModBlocks.getResourceKey(DEEPSLATE_AMETHYST_ORE))
                .add(ModBlocks.getResourceKey(RAW_AMETHYST_BLOCK))
                .add(ModBlocks.getResourceKey(STRONG_AMETHYST_BLOCK));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.getResourceKey(RAW_EMERALD_BLOCK))
                .add(ModBlocks.getResourceKey(STRONG_EMERALD_BLOCK));

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.getResourceKey(STONE_TOPAZ_ORE))
                .add(ModBlocks.getResourceKey(DEEPSLATE_TOPAZ_ORE));

    }
}
