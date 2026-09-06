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

    public static final ToolMaterial EMERALD;
    public static final ToolMaterial STRONG_AMETHYST;
    public static final ToolMaterial STRONG_RUBY;
    public static final ToolMaterial STRONG_TOPAZ;

    public static final ToolMaterial STRONG_IRON;
    public static final ToolMaterial STRONG_GOLD;
    public static final ToolMaterial STRONG_DIAMOND;
    public static final ToolMaterial STRONG_EMERALD;

    static {
        EMERALD = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_EMERALD_TOOL, 750, 6.5f, 2.5f, 30, MtsTags.Items.EMERALD_REPAIR);
        STRONG_AMETHYST = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_AMETHYST_TOOL, 2599, 9.0f, 6.0f, 20, MtsTags.Items.AMETHYST_REPAIR);
        STRONG_RUBY = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_RUBY_TOOL, 1897, 7.5f, 5.0f, 30, MtsTags.Items.RUBY_REPAIR);
        STRONG_TOPAZ = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_TOPAZ_TOOL, 1241, 10.0f, 3.5f, 30, MtsTags.Items.TOPAZ_REPAIR);

        STRONG_IRON = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_IRON_TOOL, StrongMaterialBaseAttributes.STRONG_IRON_MATERIAL_BASE_DURABILITY, 6.75f, 3.5f, 30, MtsTags.Items.STRONG_IRON_REPAIR);
        STRONG_GOLD = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_GOLD_TOOL, StrongMaterialBaseAttributes.STRONG_GOLD_MATERIAL_BASE_DURABILITY, 14.0f, 2.5f, 30, MtsTags.Items.STRONG_GOLD_REPAIR);
        STRONG_EMERALD = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_EMERALD_TOOL, StrongMaterialBaseAttributes.STRONG_EMERALD_MATERIAL_BASE_DURABILITY, 8.5f, 3.0f, 30, MtsTags.Items.STRONG_EMERALD_REPAIR);
        STRONG_DIAMOND = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_DIAMOND_TOOL, StrongMaterialBaseAttributes.STRONG_DIAMOND_MATERIAL_BASE_DURABILITY, 10.0f, 4.5f, 30, MtsTags.Items.STRONG_DIAMOND_REPAIR);
    }

    static class StrongMaterialBaseAttributes {

        /**
         * Specifically indicates Strong Version of Iron, Gold, Diamond, and Emerald.
         */
        private static final int STRONG_VANILLA_MATERIAL_BASE_DURABILITY = 700;

        private static final int STRONG_GOLD_MATERIAL_BASE_DURABILITY = STRONG_VANILLA_MATERIAL_BASE_DURABILITY * 2 / 3;
        private static final int STRONG_IRON_MATERIAL_BASE_DURABILITY = STRONG_VANILLA_MATERIAL_BASE_DURABILITY;
        private static final int STRONG_EMERALD_MATERIAL_BASE_DURABILITY = STRONG_VANILLA_MATERIAL_BASE_DURABILITY * 4 / 3;
        private static final int STRONG_DIAMOND_MATERIAL_BASE_DURABILITY = STRONG_VANILLA_MATERIAL_BASE_DURABILITY * 2;
    }
}
