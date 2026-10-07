package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.target_dummy.TargetDummyEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 9/7/2026
 */
public final class MtsEntityRenderers {

    public static void register() {
        MtsLogger.info("Entity Renderers");

        EntityRenderers.register(MtsEntityTypes.BROWN_BEAR, BrownBearEntityRenderer::new);
        EntityRenderers.register(MtsEntityTypes.TARGET_DUMMY, TargetDummyEntityRenderer::new);
    }
}
