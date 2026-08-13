package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.level.block.Block;

import static net.mrgoddavid.minecraftthestoriesmod.block.ModBlocks.*;
import static net.mrgoddavid.minecraftthestoriesmod.item.ModItems.*;

/**
 * Adapts the {@link FabricModelProvider} class. Generates the JSON files for mod items/blocks.
 *
 * @author Mr. GodDavid
 * @since 8/12/2026
 */
public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

        blockModelGenerators.createTrivialCube(DEEPSLATE_AMETHYST_ORE);
        blockModelGenerators.createTrivialCube(DEEPSLATE_RUBY_ORE);
        blockModelGenerators.createTrivialCube(DEEPSLATE_TOPAZ_ORE);
        blockModelGenerators.createTrivialCube(RAW_AMETHYST_BLOCK);
        blockModelGenerators.createTrivialCube(RAW_DIAMOND_BLOCK);
        blockModelGenerators.createTrivialCube(RAW_EMERALD_BLOCK);
        blockModelGenerators.createTrivialCube(RAW_RUBY_BLOCK);
        blockModelGenerators.createTrivialCube(RAW_TOPAZ_BLOCK);
        blockModelGenerators.createTrivialCube(STONE_AMETHYST_ORE);
        blockModelGenerators.createTrivialCube(STONE_RUBY_ORE);
        blockModelGenerators.createTrivialCube(STONE_TOPAZ_ORE);
        blockModelGenerators.createTrivialCube(STRONG_AMETHYST_BLOCK);
        blockModelGenerators.createTrivialCube(STRONG_DIAMOND_BLOCK);
        blockModelGenerators.createTrivialCube(STRONG_EMERALD_BLOCK);
        blockModelGenerators.createTrivialCube(STRONG_RUBY_BLOCK);
        blockModelGenerators.createTrivialCube(STRONG_TOPAZ_BLOCK);
        blockModelGenerators.createTrivialCube(STRONG_IRON_BLOCK);
        blockModelGenerators.createTrivialCube(STRONG_GOLD_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        final ModelTemplate template = ModelTemplates.FLAT_ITEM;

        itemModelGenerators.generateFlatItem(RAW_AMETHYST, template);
        itemModelGenerators.generateFlatItem(RAW_DIAMOND, template);
        itemModelGenerators.generateFlatItem(RAW_EMERALD, template);
        itemModelGenerators.generateFlatItem(RAW_RUBY, template);
        itemModelGenerators.generateFlatItem(RAW_TOPAZ, template);
        itemModelGenerators.generateFlatItem(STRONG_AMETHYST, template);
        itemModelGenerators.generateFlatItem(STRONG_AMETHYST_INGOT, template);
        itemModelGenerators.generateFlatItem(STRONG_DIAMOND, template);
        itemModelGenerators.generateFlatItem(STRONG_DIAMOND_INGOT, template);
        itemModelGenerators.generateFlatItem(STRONG_EMERALD, template);
        itemModelGenerators.generateFlatItem(STRONG_EMERALD_INGOT, template);
        itemModelGenerators.generateFlatItem(STRONG_GOLD, template);
        itemModelGenerators.generateFlatItem(STRONG_GOLD_INGOT, template);
        itemModelGenerators.generateFlatItem(STRONG_IRON, template);
        itemModelGenerators.generateFlatItem(STRONG_IRON_INGOT, template);
        itemModelGenerators.generateFlatItem(STRONG_RUBY, template);
        itemModelGenerators.generateFlatItem(STRONG_RUBY_INGOT, template);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ, template);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ_INGOT, template);
    }
}
