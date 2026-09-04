package net.mrgoddavid.minecraftthestoriesmod.item;

import net.minecraft.world.item.ToolMaterial;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;

/**
 * Defines different materials of the mod item.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class MtsItemToolMaterials {

    public static final ToolMaterial AMETHYST;
    public static final ToolMaterial EMERALD;
    public static final ToolMaterial RUBY;
    public static final ToolMaterial TOPAZ;

    static {
        AMETHYST = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_AMETHYST_TOOL, 2599, 9.0f, 6.0f, 20, MtsTags.Items.AMETHYST_REPAIR);
        EMERALD = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_EMERALD_TOOL, 750, 6.5f, 2.5f, 20, MtsTags.Items.EMERALD_REPAIR);
        RUBY = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_RUBY_TOOL, 1897, 7.5f, 5.0f, 20, MtsTags.Items.RUBY_REPAIR);
        TOPAZ = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_TOPAZ_TOOL, 1241, 10.0f, 3.5f, 20, MtsTags.Items.TOPAZ_REPAIR);
    }
}
