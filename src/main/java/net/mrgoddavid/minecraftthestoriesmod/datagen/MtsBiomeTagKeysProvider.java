package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

import java.util.concurrent.CompletableFuture;

/**
 * @author Mr. GodDavid
 * @since 11/17/2026
 */
public class MtsBiomeTagKeysProvider extends BiomeTagsProvider {

    public MtsBiomeTagKeysProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
        MtsLogger.info("Custom Biome Tags");
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {

        this.tag(MtsTags.Biomes.HAS_ABANDONED_HAY_WAGON)
                .add(Biomes.PLAINS);
    }
}
