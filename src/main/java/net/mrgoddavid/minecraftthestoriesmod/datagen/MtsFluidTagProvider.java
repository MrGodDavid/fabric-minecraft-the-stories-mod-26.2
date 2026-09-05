package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.fluid.MtsFluids;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;

import java.util.concurrent.CompletableFuture;

import static net.mrgoddavid.minecraftthestoriesmod.fluid.MtsFluids.*;

public class MtsFluidTagProvider extends FabricTagsProvider.FluidTagsProvider {

    public MtsFluidTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
        MinecraftTheStoriesMod.LOGGER.info("Providing data of MTS Fluid Tags for: " + MinecraftTheStoriesMod.MOD_ID);
    }

    /**
     * Implement this method and then use {@link FabricTagsProvider#builder} to get and register new tag builders.
     *
     * @param registries
     */
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(MtsTags.Fluids.ENRICHER_WASTE)
                .add(MtsFluids.getResourceKey(ENRICHER_WASTE_STILL))
                .add(MtsFluids.getResourceKey(ENRICHER_WASTE_FLOWING));
        builder(MtsTags.Fluids.BLUE_FUEL)
                .add(MtsFluids.getResourceKey(BLUE_FUEL_STILL))
                .add(MtsFluids.getResourceKey(BLUE_FUEL_FLOWING));

//        builder(FluidTags.WATER)
//                .addTag(MtsFluidTags.ENRICHER_WASTE)
//                .addTag(MtsFluidTags.BLUE_FUEL);
    }
}
