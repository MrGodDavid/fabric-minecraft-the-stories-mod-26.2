package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

import static net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks.*;

/**
 * @author Mr. GodDavid
 * @since 9/25/2026
 */
public class MtsStrippableBlocks {

    public static void register() {
        MtsLogger.info("Custom Strippable Blocks");

        StrippableBlockRegistry.register(COMPRESSED_WOOD, STRIPPED_COMPRESSED_WOOD);
        StrippableBlockRegistry.register(COMPRESSED_WOOD_LOG, STRIPPED_COMPRESSED_WOOD_LOG);

        StrippableBlockRegistry.register(LEMON_TREE_WOOD, STRIPPED_LEMON_TREE_WOOD);
        StrippableBlockRegistry.register(LEMON_TREE_LOG, STRIPPED_LEMON_TREE_LOG);
    }
}
