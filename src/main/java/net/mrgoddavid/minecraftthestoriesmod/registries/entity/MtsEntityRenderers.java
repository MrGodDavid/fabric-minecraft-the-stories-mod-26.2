package net.mrgoddavid.minecraftthestoriesmod.registries.entity;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.target_dummy.TargetDummyEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.acaciania.AcacianiaEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.birchia.BirchiaEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.cherrinia.CherriniaEntityRenderState;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.cherrinia.CherriniaEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.dense_oakie.DenseOakieEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.junmi.JunmiEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.light_oakie.LightOakieEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.mangrolino.MangrolinoEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.mangrolino.MangrolinoEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie.OakieEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie.OakieHealingBallEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.sprucia.SpruciaEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 9/7/2026
 */
public final class MtsEntityRenderers {

    public static void register() {
        MtsLogger.info("Entity Renderers");

        EntityRenderers.register(MtsEntityTypes.BROWN_BEAR, BrownBearEntityRenderer::new);
        EntityRenderers.register(MtsEntityTypes.TARGET_DUMMY, TargetDummyEntityRenderer::new);

        EntityRenderers.register(MtsEntityTypes.ACACIANIA, AcacianiaEntityRenderer::new);
        EntityRenderers.register(MtsEntityTypes.BIRCHIA, BirchiaEntityRenderer::new);
        EntityRenderers.register(MtsEntityTypes.CHERRINIA, CherriniaEntityRenderer::new);
        EntityRenderers.register(MtsEntityTypes.DENSE_OAKIE, DenseOakieEntityRenderer::new);
        EntityRenderers.register(MtsEntityTypes.JUNMI, JunmiEntityRenderer::new);
        EntityRenderers.register(MtsEntityTypes.MANGROLINO, MangrolinoEntityRenderer::new);
        EntityRenderers.register(MtsEntityTypes.OAKIE, OakieEntityRenderer::new);
        EntityRenderers.register(MtsEntityTypes.OAKIE_HEALING_BALL, OakieHealingBallEntityRenderer::new);
        EntityRenderers.register(MtsEntityTypes.LIGHT_OAKIE, LightOakieEntityRenderer::new);
        EntityRenderers.register(MtsEntityTypes.SPRUCIA, SpruciaEntityRenderer::new);
    }

    private MtsEntityRenderers() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
