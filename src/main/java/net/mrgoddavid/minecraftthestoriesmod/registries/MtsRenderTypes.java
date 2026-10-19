package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 10/18/2026
 */
public final class MtsRenderTypes {

    public static final RenderType OAKIE_EYES = RenderTypes.eyes(MtsEntityTextures.OAKIE_EYES);

    public static void register() {
        MtsLogger.info("Custom Render Types");
    }

    private MtsRenderTypes() throws IllegalAccessException {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
