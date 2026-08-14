package net.mrgoddavid.minecraftthestoriesmod.item;

import net.minecraft.world.item.ToolMaterial;
import net.mrgoddavid.minecraftthestoriesmod.tags.ModTags;

/**
 * Defines different materials of the mod item.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class ModItemToolMaterials {

    public static final ToolMaterial EMERALD;

    static {
        EMERALD  = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_EMERALD_TOOL, 1200, 5f, 4f, 20, ModTags.Items.EMERALD_REPAIR);
    }


}
