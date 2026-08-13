package net.mrgoddavid.minecraftthestoriesmod.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

import java.util.function.Function;

/**
 * Holds all custom mod blocks.
 *
 * @author Mr. GodDavid
 * @since 8/13/2026
 */
public class ModBlocks {

    public static final Block DEEPSLATE_AMETHYST_ORE;
    public static final Block DEEPSLATE_RUBY_ORE;
    public static final Block DEEPSLATE_TOPAZ_ORE;
    public static final Block RAW_AMETHYST_BLOCK;
    public static final Block RAW_DIAMOND_BLOCK;
    public static final Block RAW_EMERALD_BLOCK;
    public static final Block RAW_RUBY_BLOCK;
    public static final Block RAW_TOPAZ_BLOCK;
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

    static {
        DEEPSLATE_AMETHYST_ORE = registerBlock("deepslate_amethyst_ore", properties -> new Block(properties.strength(6F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
        DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore", properties -> new Block(properties.strength(6F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
        DEEPSLATE_TOPAZ_ORE = registerBlock("deepslate_topaz_ore", properties -> new Block(properties.strength(6F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
        RAW_AMETHYST_BLOCK = registerBlock("raw_amethyst_block", properties -> new Block(properties.strength(5F).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
        RAW_DIAMOND_BLOCK = registerBlock("raw_diamond_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        RAW_EMERALD_BLOCK = registerBlock("raw_emerald_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        RAW_RUBY_BLOCK = registerBlock("raw_ruby_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        RAW_TOPAZ_BLOCK = registerBlock("raw_topaz_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        STONE_AMETHYST_ORE = registerBlock("stone_amethyst_ore", properties -> new Block(properties.strength(4F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
        STONE_RUBY_ORE = registerBlock("stone_ruby_ore", properties -> new Block(properties.strength(4F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
        STONE_TOPAZ_ORE = registerBlock("stone_topaz_ore", properties -> new Block(properties.strength(4F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
        STRONG_AMETHYST_BLOCK = registerBlock("strong_amethyst_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
        STRONG_DIAMOND_BLOCK = registerBlock("strong_diamond_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        STRONG_EMERALD_BLOCK = registerBlock("strong_emerald_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        STRONG_RUBY_BLOCK = registerBlock("strong_ruby_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        STRONG_TOPAZ_BLOCK = registerBlock("strong_topaz_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        STRONG_IRON_BLOCK = registerBlock("strong_iron_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.IRON)));
        STRONG_GOLD_BLOCK = registerBlock("strong_gold_block", properties -> new Block(properties.strength(7.5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    }


    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Identifier blockId = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name);
        ResourceKey<Block> blockResourceKey = ResourceKey.create(Registries.BLOCK, blockId);
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(blockResourceKey));
        registerModBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, blockId, toRegister);
    }

    /**
     * Registers the mod block item into vanilla Minecraft.
     *
     * @param name
     * @param block
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
    private ModBlocks() {
        throw new IllegalStateException("You can't instantiate ModItems class!");
    }
}
