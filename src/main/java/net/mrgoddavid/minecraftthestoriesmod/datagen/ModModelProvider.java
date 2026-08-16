package net.mrgoddavid.minecraftthestoriesmod.datagen;

import com.mojang.math.Quadrant;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.block.custom.SuperCrafterBlock;

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
        blockModelGenerators.createTrivialCube(RAW_STRONG_AMETHYST_BLOCK);
        blockModelGenerators.createTrivialCube(RAW_STRONG_DIAMOND_BLOCK);
        blockModelGenerators.createTrivialCube(RAW_STRONG_EMERALD_BLOCK);
        blockModelGenerators.createTrivialCube(RAW_STRONG_RUBY_BLOCK);
        blockModelGenerators.createTrivialCube(RAW_STRONG_TOPAZ_BLOCK);
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

        blockModelGenerators.createAxisAlignedPillarBlock(COMPRESSED_WOOD, TexturedModel.CUBE);
        blockModelGenerators.createAxisAlignedPillarBlock(STRIPPED_COMPRESSED_WOOD, TexturedModel.CUBE);
        blockModelGenerators.woodProvider(COMPRESSED_WOOD_LOG).log(COMPRESSED_WOOD_LOG);
        blockModelGenerators.woodProvider(STRIPPED_COMPRESSED_WOOD_LOG).log(STRIPPED_COMPRESSED_WOOD_LOG);
        blockModelGenerators.createTrivialCube(COMPRESSED_WOOD_PLANKS);

        Identifier defaultID = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, SuperCrafterBlock.TYPE.DEFAULT.path());
        Identifier withHammerID = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, SuperCrafterBlock.TYPE.WITH_HAMMER.path());
        Identifier withBlueprintID = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, SuperCrafterBlock.TYPE.WITH_BLUEPRINT.path());
        Identifier withHammerAndWithBlueprintID = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, SuperCrafterBlock.TYPE.WITH_HAMMER_WITH_BLUEPRINT.path());
        blockModelGenerators.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(SUPER_CRAFTER_BLOCK).with(
                        PropertyDispatch.initial(SuperCrafterBlock.STATE, SuperCrafterBlock.FACING)
                                .select(SuperCrafterBlock.TYPE.DEFAULT, Direction.NORTH, BlockModelGenerators.plainVariant(defaultID))
                                .select(SuperCrafterBlock.TYPE.DEFAULT, Direction.EAST, BlockModelGenerators.plainVariant(defaultID).with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
                                .select(SuperCrafterBlock.TYPE.DEFAULT, Direction.SOUTH, BlockModelGenerators.plainVariant(defaultID).with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
                                .select(SuperCrafterBlock.TYPE.DEFAULT, Direction.WEST, BlockModelGenerators.plainVariant(defaultID).with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER, Direction.NORTH, BlockModelGenerators.plainVariant(withHammerID))
                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER, Direction.EAST, BlockModelGenerators.plainVariant(withHammerID).with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER, Direction.SOUTH, BlockModelGenerators.plainVariant(withHammerID).with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER, Direction.WEST, BlockModelGenerators.plainVariant(withHammerID).with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
                                .select(SuperCrafterBlock.TYPE.WITH_BLUEPRINT, Direction.NORTH, BlockModelGenerators.plainVariant(withBlueprintID))
                                .select(SuperCrafterBlock.TYPE.WITH_BLUEPRINT, Direction.EAST, BlockModelGenerators.plainVariant(withBlueprintID).with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
                                .select(SuperCrafterBlock.TYPE.WITH_BLUEPRINT, Direction.SOUTH, BlockModelGenerators.plainVariant(withBlueprintID).with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
                                .select(SuperCrafterBlock.TYPE.WITH_BLUEPRINT, Direction.WEST, BlockModelGenerators.plainVariant(withBlueprintID).with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER_WITH_BLUEPRINT, Direction.NORTH, BlockModelGenerators.plainVariant(withHammerAndWithBlueprintID))
                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER_WITH_BLUEPRINT, Direction.EAST, BlockModelGenerators.plainVariant(withHammerAndWithBlueprintID).with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER_WITH_BLUEPRINT, Direction.SOUTH, BlockModelGenerators.plainVariant(withHammerAndWithBlueprintID).with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER_WITH_BLUEPRINT, Direction.WEST, BlockModelGenerators.plainVariant(withHammerAndWithBlueprintID).with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
                )
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        final ModelTemplate flatItemTemplate = ModelTemplates.FLAT_ITEM;

        itemModelGenerators.generateFlatItem(RAW_STRONG_AMETHYST, flatItemTemplate);
        itemModelGenerators.generateFlatItem(RAW_STRONG_DIAMOND, flatItemTemplate);
        itemModelGenerators.generateFlatItem(RAW_STRONG_EMERALD, flatItemTemplate);
        itemModelGenerators.generateFlatItem(RAW_STRONG_RUBY, flatItemTemplate);
        itemModelGenerators.generateFlatItem(RAW_STRONG_TOPAZ, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_AMETHYST, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_AMETHYST_INGOT, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_DIAMOND, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_DIAMOND_INGOT, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_EMERALD, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_EMERALD_INGOT, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_GOLD, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_GOLD_INGOT, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_IRON, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_IRON_INGOT, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_RUBY, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_RUBY_INGOT, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ_INGOT, flatItemTemplate);

        itemModelGenerators.generateFlatItem(STRONG_AMETHYST_AXE, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_AMETHYST_HOE, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_AMETHYST_PICKAXE, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_AMETHYST_SHOVEL, flatItemTemplate);
        itemModelGenerators.generateSpear(STRONG_AMETHYST_SPEAR);
        itemModelGenerators.generateFlatItem(STRONG_AMETHYST_SWORD, flatItemTemplate);
        itemModelGenerators.generateFlatItem(EMERALD_AXE, flatItemTemplate);
        itemModelGenerators.generateFlatItem(EMERALD_HOE, flatItemTemplate);
        itemModelGenerators.generateFlatItem(EMERALD_PICKAXE, flatItemTemplate);
        itemModelGenerators.generateFlatItem(EMERALD_SHOVEL, flatItemTemplate);
        itemModelGenerators.generateSpear(EMERALD_SPEAR);
        itemModelGenerators.generateFlatItem(EMERALD_SWORD, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_RUBY_AXE, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_RUBY_HOE, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_RUBY_PICKAXE, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_RUBY_SHOVEL, flatItemTemplate);
        itemModelGenerators.generateSpear(STRONG_RUBY_SPEAR);
        itemModelGenerators.generateFlatItem(STRONG_RUBY_SWORD, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ_AXE, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ_HOE, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ_PICKAXE, flatItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ_SHOVEL, flatItemTemplate);
        itemModelGenerators.generateSpear(STRONG_TOPAZ_SPEAR);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ_SWORD, flatItemTemplate);
    }
}
