package net.mrgoddavid.minecraftthestoriesmod.gui.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudStatusBarHeightRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class MtsHud {

    private MtsHud() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }

    public static void register() {
        MtsLogger.info("Custom Hud Elements");

        HudElementRegistry.attachElementAfter(VanillaHudElements.FOOD_BAR, Constants.modId("thirst"), ThirstHud::extractRenderState);
        HudStatusBarHeightRegistry.addRight(Constants.modId("thirst"), player -> 9);
    }
}
