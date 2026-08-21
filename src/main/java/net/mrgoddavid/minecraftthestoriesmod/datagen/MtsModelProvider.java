package net.mrgoddavid.minecraftthestoriesmod.datagen;

import com.mojang.math.Quadrant;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.block.custom.enricher.EnricherBlock;
import net.mrgoddavid.minecraftthestoriesmod.block.custom.super_crafter.SuperCrafterBlock;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItemModelTemplates;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

import static net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks.*;
import static net.mrgoddavid.minecraftthestoriesmod.item.MtsItems.*;

/**
 * Adapts the {@link FabricModelProvider} class. Generates the JSON files for mod items/blocks.
 *
 * @author Mr. GodDavid
 * @since 8/12/2026
 */
public class MtsModelProvider extends FabricModelProvider {

    public MtsModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

        blockModelGenerators.createTrivialCube(DEEPSLATE_AMETHYST_ORE);
        blockModelGenerators.createTrivialCube(DEEPSLATE_RUBY_ORE);
        blockModelGenerators.createTrivialCube(DEEPSLATE_TOPAZ_ORE);
        blockModelGenerators.family(RAW_STRONG_AMETHYST_BLOCK)
                .wall(RAW_STRONG_AMETHYST_WALL);
        blockModelGenerators.createTrivialCube(RAW_STRONG_DIAMOND_BLOCK);
        blockModelGenerators.createTrivialCube(RAW_STRONG_EMERALD_BLOCK);
        blockModelGenerators.createTrivialCube(RAW_STRONG_RUBY_BLOCK);
        blockModelGenerators.createTrivialCube(RAW_STRONG_TOPAZ_BLOCK);
        blockModelGenerators.createTrivialCube(STONE_AMETHYST_ORE);
        blockModelGenerators.createTrivialCube(STONE_RUBY_ORE);
        blockModelGenerators.createTrivialCube(STONE_TOPAZ_ORE);
        blockModelGenerators.family(STRONG_AMETHYST_BLOCK)
                .fence(STRONG_AMETHYST_FENCE)
                .fenceGate(STRONG_AMETHYST_FENCE_GATE)
                .wall(STRONG_AMETHYST_WALL);
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
        blockModelGenerators.family(COMPRESSED_WOOD_PLANKS)
                .fence(COMPRESSED_WOOD_FENCE)
                .fenceGate(COMPRESSED_WOOD_FENCE_GATE);

        this.generateFacingModels(blockModelGenerators, SUPER_CRAFTER_BLOCK,
                SuperCrafterBlock.STATE, SuperCrafterBlock.FACING,
                (type -> Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, type.path()))
        );
        this.generateFacingModels(blockModelGenerators, ENRICHER,
                EnricherBlock.STATE, EnricherBlock.FACING,
                (type -> Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, type.path()))
        );

        blockModelGenerators.createNonTemplateModelBlock(ENDER_EXALTER);

//        Identifier defaultID = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, SuperCrafterBlock.TYPE.DEFAULT.path());
//        Identifier withHammerID = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, SuperCrafterBlock.TYPE.WITH_HAMMER.path());
//        Identifier withBlueprintID = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, SuperCrafterBlock.TYPE.WITH_BLUEPRINT.path());
//        Identifier withHammerAndWithBlueprintID = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, SuperCrafterBlock.TYPE.WITH_HAMMER_WITH_BLUEPRINT.path());
//        blockModelGenerators.blockStateOutput.accept(
//                MultiVariantGenerator.dispatch(SUPER_CRAFTER_BLOCK).with(
//                        PropertyDispatch.initial(SuperCrafterBlock.STATE, SuperCrafterBlock.FACING)
//                                .select(SuperCrafterBlock.TYPE.DEFAULT, Direction.NORTH, BlockModelGenerators.plainVariant(defaultID))
//                                .select(SuperCrafterBlock.TYPE.DEFAULT, Direction.EAST, BlockModelGenerators.plainVariant(defaultID).with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
//                                .select(SuperCrafterBlock.TYPE.DEFAULT, Direction.SOUTH, BlockModelGenerators.plainVariant(defaultID).with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
//                                .select(SuperCrafterBlock.TYPE.DEFAULT, Direction.WEST, BlockModelGenerators.plainVariant(defaultID).with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
//                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER, Direction.NORTH, BlockModelGenerators.plainVariant(withHammerID))
//                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER, Direction.EAST, BlockModelGenerators.plainVariant(withHammerID).with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
//                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER, Direction.SOUTH, BlockModelGenerators.plainVariant(withHammerID).with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
//                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER, Direction.WEST, BlockModelGenerators.plainVariant(withHammerID).with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
//                                .select(SuperCrafterBlock.TYPE.WITH_BLUEPRINT, Direction.NORTH, BlockModelGenerators.plainVariant(withBlueprintID))
//                                .select(SuperCrafterBlock.TYPE.WITH_BLUEPRINT, Direction.EAST, BlockModelGenerators.plainVariant(withBlueprintID).with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
//                                .select(SuperCrafterBlock.TYPE.WITH_BLUEPRINT, Direction.SOUTH, BlockModelGenerators.plainVariant(withBlueprintID).with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
//                                .select(SuperCrafterBlock.TYPE.WITH_BLUEPRINT, Direction.WEST, BlockModelGenerators.plainVariant(withBlueprintID).with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
//                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER_WITH_BLUEPRINT, Direction.NORTH, BlockModelGenerators.plainVariant(withHammerAndWithBlueprintID))
//                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER_WITH_BLUEPRINT, Direction.EAST, BlockModelGenerators.plainVariant(withHammerAndWithBlueprintID).with(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
//                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER_WITH_BLUEPRINT, Direction.SOUTH, BlockModelGenerators.plainVariant(withHammerAndWithBlueprintID).with(VariantMutator.Y_ROT.withValue(Quadrant.R180)))
//                                .select(SuperCrafterBlock.TYPE.WITH_HAMMER_WITH_BLUEPRINT, Direction.WEST, BlockModelGenerators.plainVariant(withHammerAndWithBlueprintID).with(VariantMutator.Y_ROT.withValue(Quadrant.R270)))
//                )
//        );
    }

    public <T extends Enum<T> & StringRepresentable> void generateFacingModels(
            BlockModelGenerators blockModelGenerators,
            Block block,
            EnumProperty<T> stateProperty,
            Property<Direction> facingProperty,
            Function<T, Identifier> modelProvider
    ) {
        PropertyDispatch.C2<MultiVariant, @NonNull T, Direction> dispatch = PropertyDispatch.initial(stateProperty, facingProperty);
        for (T state : stateProperty.getPossibleValues()) {
            Identifier modelId = modelProvider.apply(state);
            dispatch.select(state, Direction.NORTH, BlockModelGenerators.plainVariant(modelId));
            dispatch.select(state, Direction.EAST, BlockModelGenerators.plainVariant(modelId).with(VariantMutator.Y_ROT.withValue(Quadrant.R90)));
            dispatch.select(state, Direction.SOUTH, BlockModelGenerators.plainVariant(modelId).with(VariantMutator.Y_ROT.withValue(Quadrant.R180)));
            dispatch.select(state, Direction.WEST, BlockModelGenerators.plainVariant(modelId).with(VariantMutator.Y_ROT.withValue(Quadrant.R270)));
        }
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(dispatch));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        final ModelTemplate flatItemTemplate = ModelTemplates.FLAT_ITEM;
        final ModelTemplate flatHandledItemTemplate = ModelTemplates.FLAT_HANDHELD_ITEM;

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

        itemModelGenerators.generateFlatItem(STRONG_AMETHYST_AXE, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_AMETHYST_HOE, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_AMETHYST_PICKAXE, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_AMETHYST_SHOVEL, flatHandledItemTemplate);
        itemModelGenerators.generateSpear(STRONG_AMETHYST_SPEAR);
        itemModelGenerators.generateFlatItem(STRONG_AMETHYST_SWORD, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(EMERALD_AXE, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(EMERALD_HOE, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(EMERALD_PICKAXE, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(EMERALD_SHOVEL, flatHandledItemTemplate);
        itemModelGenerators.generateSpear(EMERALD_SPEAR);
        itemModelGenerators.generateFlatItem(EMERALD_SWORD, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_RUBY_AXE, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_RUBY_HOE, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_RUBY_PICKAXE, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_RUBY_SHOVEL, flatHandledItemTemplate);
        itemModelGenerators.generateSpear(STRONG_RUBY_SPEAR);
        itemModelGenerators.generateFlatItem(STRONG_RUBY_SWORD, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ_AXE, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ_HOE, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ_PICKAXE, flatHandledItemTemplate);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ_SHOVEL, flatHandledItemTemplate);
        itemModelGenerators.generateSpear(STRONG_TOPAZ_SPEAR);
        itemModelGenerators.generateFlatItem(STRONG_TOPAZ_SWORD, flatHandledItemTemplate);

        itemModelGenerators.itemModelOutput.accept(DIAMOND_BATTLE_AXE, ItemModelUtils.plainModel(getScale2xModelId(itemModelGenerators, DIAMOND_BATTLE_AXE)));
    }

    public Identifier getScale2xModelId(ItemModelGenerators itemModelGenerators, Item item) {
        return MtsItemModelTemplates.SCALE_2X.create(
                item, TextureMapping.singleSlot(TextureSlot.LAYER0, new Material(ModelLocationUtils.getModelLocation(item))),
                itemModelGenerators.modelOutput
        );
    }
}
