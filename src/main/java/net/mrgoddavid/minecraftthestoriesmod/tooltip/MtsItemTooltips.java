package net.mrgoddavid.minecraftthestoriesmod.tooltip;

import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

/**
 * Custom tooltips for Mts mod.
 *
 * @author Mr. GodDavid
 * @since 8/19/2026
 */
public class MtsItemTooltips {

    public static final Identifier EMERALD_AXE_3D_ICON = icon("emerald_axe");

    public static final Identifier COMMON_WEAPONS_TOOLTIP_STYLE = tooltip("common_weapons_tooltip");

    private static Identifier tooltip(String path) {
        return Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, path);
    }

    private static Identifier icon(String path) {
        return Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, "textures/gui/icons/" + path + "_3d_icon.png");
    }

    public static void register() {
    }
}
