package net.mrgoddavid.minecraftthestoriesmod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;
import net.mrgoddavid.minecraftthestoriesmod.block.renderer.custom.EnricherBlockEntityRenderer;

public class MinecraftTheStoriesModClient implements ClientModInitializer {

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(MtsBlockEntities.ENRICHER_BE, EnricherBlockEntityRenderer::new);
    }
}
