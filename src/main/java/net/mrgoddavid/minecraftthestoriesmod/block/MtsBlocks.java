package net.mrgoddavid.minecraftthestoriesmod.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ender_exalter.EnderExalterBlock;
import net.mrgoddavid.minecraftthestoriesmod.block.content.enricher.EnricherBlock;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor.OreCompressorBlock;
import net.mrgoddavid.minecraftthestoriesmod.block.content.super_crafter.SuperCrafterBlock;
import net.mrgoddavid.minecraftthestoriesmod.fluid.MtsFluids;

import java.util.function.Function;

/**
 * Holds all custom mod blocks.
 *
 * @author Mr. GodDavid
 * @since 8/13/2026
 */
public class MtsBlocks {

    public static final Block DEEPSLATE_AMETHYST_ORE;
    public static final Block DEEPSLATE_RUBY_ORE;
    public static final Block DEEPSLATE_TOPAZ_ORE;
    public static final Block RAW_STRONG_AMETHYST_BLOCK;
    public static final Block RAW_STRONG_DIAMOND_BLOCK;
    public static final Block RAW_STRONG_EMERALD_BLOCK;
    public static final Block RAW_STRONG_RUBY_BLOCK;
    public static final Block RAW_STRONG_TOPAZ_BLOCK;
    public static final Block STONE_AMETHYST_ORE;
    public static final Block STONE_RUBY_ORE;
    public static final Block STONE_TOPAZ_ORE;
    public static final Block STRONG_AMETHYST_BLOCK;
    public static final Block STRONG_DIAMOND_BLOCK;
    public static final Block STRONG_EMERALD_BLOCK;
    public static final Block STRONG_RUBY_BLOCK;
    public static final Block STRONG_TOPAZ_BLOCK;
    public static final Block STRONG_IRON_BLOCK;
    public static final Block STRONG_GOLD_BLOCK;

    public static final Block COMPRESSED_WOOD;
    public static final Block COMPRESSED_WOOD_LOG;
    public static final Block COMPRESSED_WOOD_PLANKS;
    public static final Block STRIPPED_COMPRESSED_WOOD;
    public static final Block STRIPPED_COMPRESSED_WOOD_LOG;
    public static final Block COMPRESSED_WOOD_FENCE;
    public static final Block COMPRESSED_WOOD_FENCE_GATE;

    public static final Block STRONG_AMETHYST_FENCE;
    public static final Block STRONG_AMETHYST_FENCE_GATE;
    public static final Block STRONG_AMETHYST_WALL;
    public static final Block RAW_STRONG_AMETHYST_WALL;

    public static final Block SUPER_CRAFTER_BLOCK;
    public static final Block ENRICHER;
    public static final Block ENDER_EXALTER;
    public static final Block ORE_COMPRESSOR;

    public static final Block ENRICHER_WASTE_FLUID;

    static {
        DEEPSLATE_AMETHYST_ORE = registerBlock("deepslate_amethyst_ore", properties -> new Block(properties.strength(6f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
        DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore", properties -> new Block(properties.strength(6f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
        DEEPSLATE_TOPAZ_ORE = registerBlock("deepslate_topaz_ore", properties -> new Block(properties.strength(6f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
        RAW_STRONG_AMETHYST_BLOCK = registerBlock("raw_strong_amethyst_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
        RAW_STRONG_DIAMOND_BLOCK = registerBlock("raw_strong_diamond_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        RAW_STRONG_EMERALD_BLOCK = registerBlock("raw_strong_emerald_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        RAW_STRONG_RUBY_BLOCK = registerBlock("raw_strong_ruby_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        RAW_STRONG_TOPAZ_BLOCK = registerBlock("raw_strong_topaz_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        STONE_AMETHYST_ORE = registerBlock("stone_amethyst_ore", properties -> new Block(properties.strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
        STONE_RUBY_ORE = registerBlock("stone_ruby_ore", properties -> new Block(properties.strength(3.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
        STONE_TOPAZ_ORE = registerBlock("stone_topaz_ore", properties -> new Block(properties.strength(3.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
        STRONG_AMETHYST_BLOCK = registerBlock("strong_amethyst_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
        STRONG_DIAMOND_BLOCK = registerBlock("strong_diamond_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        STRONG_EMERALD_BLOCK = registerBlock("strong_emerald_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        STRONG_RUBY_BLOCK = registerBlock("strong_ruby_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        STRONG_TOPAZ_BLOCK = registerBlock("strong_topaz_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        STRONG_IRON_BLOCK = registerBlock("strong_iron_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.IRON)));
        STRONG_GOLD_BLOCK = registerBlock("strong_gold_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

        STRONG_AMETHYST_FENCE = registerBlock("strong_amethyst_fence", properties -> new FenceBlock(properties.strength(6.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        STRONG_AMETHYST_FENCE_GATE = registerBlock("strong_amethyst_fence_gate", properties -> new FenceGateBlock(WoodType.ACACIA, properties.strength(6.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        STRONG_AMETHYST_WALL = registerBlock("strong_amethyst_wall", properties -> new WallBlock(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        RAW_STRONG_AMETHYST_WALL = registerBlock("raw_strong_amethyst_wall", properties -> new WallBlock(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

        COMPRESSED_WOOD = registerBlock("compressed_wood", properties -> new RotatedPillarBlock(properties.strength(3.5f).requiresCorrectToolForDrops().sound(SoundType.WOOD).ignitedByLava()));
        COMPRESSED_WOOD_LOG = registerBlock("compressed_wood_log", properties -> new RotatedPillarBlock(properties.strength(3.0f).requiresCorrectToolForDrops().sound(SoundType.WOOD).ignitedByLava()));
        COMPRESSED_WOOD_PLANKS = registerBlock("compressed_wood_planks", properties -> new RotatedPillarBlock(properties.strength(3.0f).requiresCorrectToolForDrops().sound(SoundType.WOOD).ignitedByLava()));
        STRIPPED_COMPRESSED_WOOD = registerBlock("stripped_compressed_wood", properties -> new RotatedPillarBlock(properties.strength(3.5f).requiresCorrectToolForDrops().sound(SoundType.WOOD).ignitedByLava()));
        STRIPPED_COMPRESSED_WOOD_LOG = registerBlock("stripped_compressed_wood_log", properties -> new RotatedPillarBlock(properties.strength(3.0f).requiresCorrectToolForDrops().sound(SoundType.WOOD).ignitedByLava()));
        COMPRESSED_WOOD_FENCE = registerBlock("compressed_wood_fence", properties -> new FenceBlock(properties.strength(3.0f).requiresCorrectToolForDrops().sound(SoundType.WOOD).ignitedByLava()));
        COMPRESSED_WOOD_FENCE_GATE = registerBlock("compressed_wood_fence_gate", properties -> new FenceGateBlock(WoodType.ACACIA, properties.strength(3.0f).requiresCorrectToolForDrops().sound(SoundType.WOOD).ignitedByLava()));

        SUPER_CRAFTER_BLOCK = registerBlock("super_crafter_default", properties -> new SuperCrafterBlock(properties.strength(3.0f).requiresCorrectToolForDrops().sound(SoundType.WOOD)));
        ENRICHER = registerBlock("enricher_default", properties -> new EnricherBlock(properties.strength(6.0f).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> state.getValue(EnricherBlock.LIT) ? 15 : 0)));
        ENDER_EXALTER = registerBlock("ender_exalter", properties -> new EnderExalterBlock(properties.strength(4.5f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
        ORE_COMPRESSOR = registerBlock("ore_compressor_default", properties -> new OreCompressorBlock(properties.strength(6.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

        ENRICHER_WASTE_FLUID = registerBlock("enricher_waste_fluid", properties -> new LiquidBlock(MtsFluids.ENRICHER_WASTE_STILL, properties.replaceable().noCollision().noOcclusion().liquid()));
    }

    /**
     * Gets the resource key of the mod block.
     *
     * @param block mod block.
     * @return the resource key of the mod block.
     */
    public static ResourceKey<Block> getResourceKey(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    /**
     * Registers the block (3d object/model) into vanilla Minecraft. This method first create the {@link Identifier} by
     * utilizing the id of this mod and the name of the block. We have seen many usages. Then, we create a resource
     * key,  with the type parameter of {@link Block}, by calling the
     * {@link ResourceKey#create(ResourceKey, Identifier)}  method. {@link ResourceKey#create(ResourceKey, Identifier)}
     * has two parameters. The first one is the built-in registries called {@link Registries#BLOCK}, and the second one
     * is the identifier, which is the same we created in the first step. Next, we create an instance of {@link Block}.
     * We first use the {@link Function} API to apply the property of the {@link BlockBehaviour} to the block. We then
     * call the {@link MtsBlocks#registerModBlockItem(String, Block)} method to finish the block-item registration.
     * Finally, we return the registered block by calling {@link Registry#register(Registry, Identifier, Object)}, which
     * we have seen in {@link MtsBlocks#registerModBlockItem(String, Block)} method. The only difference is we pass
     * {@code BuiltInRegistries.BLOCK} as the register of block registration.
     *
     * @param name     of the block.
     * @param function from functional API in Java. Makes code more readable.
     * @return the registered block, after finished registered the block item.
     */
    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Identifier blockId = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name);
        ResourceKey<Block> blockResourceKey = ResourceKey.create(Registries.BLOCK, blockId);
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(blockResourceKey));
        registerModBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, blockId, toRegister);
    }

    /**
     * Registers the mod block item into vanilla Minecraft. This method first creates an {@link Identifier} by calling
     * the {@link Identifier#fromNamespaceAndPath(String, String)}, which combines the mod id and the name of the block
     * that is registered. We then call the {@link Registry#register(Registry, Identifier, Object)}. The type parameter
     * of the {@link Registry} is {@link Item}, and the type parameter of the value is the subclass of the type
     * parameter of the registry. In this case, the type parameter of the value is {@link BlockItem}, which is a
     * subclass of {@link Item}. For the {@link BlockItem}, we call the constructor by sending the block that we want
     * to register and the property of the block. The property of the block is initialized by calling the
     * {@link Item.Properties#useBlockDescriptionPrefix()}, which set the initial "block", and then the set the id by
     * using a resource key which is similar to when we register mod items. The prefix of the block will be used when
     * reading JSON files of block's model and texture files.
     *
     * @param name  of the mod block item, which is the same as the block's name.
     * @param block the instance of mod block.
     */
    private static void registerModBlockItem(String name, Block block) {
        Identifier id = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name);
        Registry.register(BuiltInRegistries.ITEM, id,
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, id))));
    }

    /**
     * The initializer method of this class.
     */
    public static void registerModBlocks() {
        MinecraftTheStoriesMod.LOGGER.info("Registering Mod Blocks for: " + MinecraftTheStoriesMod.MOD_ID);
    }

    /**
     * Private constructor.
     */
    private MtsBlocks() {
        throw new IllegalStateException("You can't instantiate ModItems class!");
    }
}
