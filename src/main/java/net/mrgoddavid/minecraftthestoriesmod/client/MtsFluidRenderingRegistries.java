package net.mrgoddavid.minecraftthestoriesmod.client;

import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;
import net.mrgoddavid.minecraftthestoriesmod.fluid.MtsFluids;

/**
 * Rendering registries of MTS mod.
 *
 * @author Mr. GodDavid
 * @since 8/26/2026
 */
public class MtsFluidRenderingRegistries {

    public static void register() {
        MinecraftTheStoriesMod.LOGGER.info("Registering Fluid Rendering Registries for " + MinecraftTheStoriesMod.MOD_ID);

        FluidRenderingRegistry.register(
                MtsFluids.ENRICHER_WASTE_STILL,
                MtsFluids.ENRICHER_WASTE_FLOWING,
                new FluidModel.Unbaked(
                        new Material(Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, "block/enricher_waste_still")),
                        new Material(Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, "block/enricher_waste_flow")),
                        null, null
                )
        );
        FluidRenderingRegistry.setBlockTransparency(MtsBlocks.ENRICHER_WASTE_FLUID, true);
    }

    private MtsFluidRenderingRegistries() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
