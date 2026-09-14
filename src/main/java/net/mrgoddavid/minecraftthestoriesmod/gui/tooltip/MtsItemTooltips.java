package net.mrgoddavid.minecraftthestoriesmod.gui.tooltip;

import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.MtsLogger;

/**
 * Custom tooltips for Mts mod.
 *
 * @author Mr. GodDavid
 * @since 8/19/2026
 */
public final class MtsItemTooltips {

    public static final Identifier COMMON_WEAPONS_TOOLTIP_STYLE = tooltip("common_weapons_tooltip");
    public static final Identifier UNCOMMON_WEAPONS_TOOLTIP_STYLE = tooltip("uncommon_weapons_tooltip");
    public static final Identifier RARE_WEAPONS_TOOLTIP_STYLE = tooltip("rare_weapons_tooltip");
    public static final Identifier EPIC_WEAPONS_TOOLTIP_STYLE = tooltip("epic_weapons_tooltip");
    public static final Identifier LEGENDARY_WEAPONS_TOOLTIP_STYLE = tooltip("legendary_weapons_tooltip");

    private static Identifier tooltip(String path) {
        return Constants.modId( path);
    }

    @Deprecated
    private static Identifier icon(String path) {
        return Constants.modId( "textures/gui/icons/" + path + "_3d_icon.png");
    }

    public static void register() {
        MtsLogger.info("MTS Tooltips");
    }
}
