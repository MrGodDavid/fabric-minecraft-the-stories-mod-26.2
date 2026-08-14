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
        DEEPSLATE_AMETHYST_ORE = registerBlock("deepslate_amethyst_ore", properties -> new Block(properties.strength(6f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
        DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore", properties -> new Block(properties.strength(6f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
        DEEPSLATE_TOPAZ_ORE = registerBlock("deepslate_topaz_ore", properties -> new Block(properties.strength(6f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
        RAW_AMETHYST_BLOCK = registerBlock("raw_amethyst_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
        RAW_DIAMOND_BLOCK = registerBlock("raw_diamond_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        RAW_EMERALD_BLOCK = registerBlock("raw_emerald_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        RAW_RUBY_BLOCK = registerBlock("raw_ruby_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        RAW_TOPAZ_BLOCK = registerBlock("raw_topaz_block", properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
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
     * call the {@link ModBlocks#registerModBlockItem(String, Block)} method to finish the block-item registration.
     * Finally, we return the registered block by calling {@link Registry#register(Registry, Identifier, Object)}, which
     * we have seen in {@link ModBlocks#registerModBlockItem(String, Block)} method. The only difference is we pass
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
    private ModBlocks() {
        throw new IllegalStateException("You can't instantiate ModItems class!");
    }
}
