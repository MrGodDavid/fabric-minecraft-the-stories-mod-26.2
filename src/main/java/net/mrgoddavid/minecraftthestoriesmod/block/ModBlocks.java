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

    public static final Block STONE_AMETHYST_ORE = registerBlock("stone_amethyst_ore", properties ->
            new Block(properties.strength(4F).requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block DEEPSLATE_AMETHYST_ORE = registerBlock("deepslate_amethyst_ore", properties ->
            new Block(properties.strength(6F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE))
    );

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Identifier blockId = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name);
        ResourceKey<Block> blockResourceKey = ResourceKey.create(Registries.BLOCK, blockId);
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(blockResourceKey));
        registerModBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, blockId, toRegister);
    }

    /**
     * Registers the mod block item into vanilla Minecraft.
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
