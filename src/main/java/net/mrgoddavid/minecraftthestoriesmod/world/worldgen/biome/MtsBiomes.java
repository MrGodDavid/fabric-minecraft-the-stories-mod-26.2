package net.mrgoddavid.minecraftthestoriesmod.world.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;

/**
 * WIP
 *
 * @author Mr. GodDavid
 * @since 11/22/2026
 */
@SuppressWarnings("SpellCheckingInspection")
public class MtsBiomes {

    public static final ResourceKey<Biome> DENSE_OAKLIN = registerBiomeRK("dense_oakilin");

    public static void bootstrap(BootstrapContext<Biome> context) {

    }

    public static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    private static ResourceKey<Biome> registerBiomeRK(String biomeName) {
        return ResourceKey.create(Registries.BIOME, Constants.modId(biomeName));
    }

    public static void register() {
        MtsLogger.info("Custom Biomes");
    }
}
