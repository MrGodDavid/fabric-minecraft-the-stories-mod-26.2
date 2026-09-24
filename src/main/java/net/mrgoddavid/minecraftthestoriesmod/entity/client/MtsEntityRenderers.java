package net.mrgoddavid.minecraftthestoriesmod.entity.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.mrgoddavid.minecraftthestoriesmod.entity.MtsEntityTypes;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 9/7/2026
 */
public class MtsEntityRenderers {

    public static void register() {
        MtsLogger.info("Entity Renderers");

        EntityRenderers.register(MtsEntityTypes.BROWN_BEAR, BrownBearEntityRenderer::new);
    }
}
