package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

import static net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks.*;

/**
 * @author Mr. GodDavid
 * @since 9/25/2026
 */
public final class MtsFlammableBlocks {

    public static void register() {
        MtsLogger.info("Custom Flammable Blocks");

        FlammableBlockRegistry.getDefaultInstance().add(COMPRESSED_WOOD, 2, 1);
        FlammableBlockRegistry.getDefaultInstance().add(COMPRESSED_WOOD_LOG, 2, 1);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_COMPRESSED_WOOD, 2, 1);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_COMPRESSED_WOOD_LOG, 2, 1);
        FlammableBlockRegistry.getDefaultInstance().add(COMPRESSED_WOOD_PLANKS, 2, 1);
        FlammableBlockRegistry.getDefaultInstance().add(COMPRESSED_WOOD_FENCE, 2, 1);
        FlammableBlockRegistry.getDefaultInstance().add(COMPRESSED_WOOD_FENCE_GATE, 2, 1);

        FlammableBlockRegistry.getDefaultInstance().add(LEMON_TREE_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(LEMON_TREE_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_LEMON_TREE_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_LEMON_TREE_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(LEMON_TREE_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(LEMON_TREE_LEAVES, 40, 60);
    }
}
