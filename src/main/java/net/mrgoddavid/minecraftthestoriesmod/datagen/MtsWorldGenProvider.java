package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

/**
 * World gen data provider.
 *
 * @author Mr. GodDavid
 * @since 8/18/2026
 */
public class MtsWorldGenProvider extends FabricDynamicRegistryProvider {

    public MtsWorldGenProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
        MtsLogger.info("Custom World Gen");
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
    }

    @Override
    public @NonNull String getName() {
        return "Mts World Gen Provider";
    }
}
