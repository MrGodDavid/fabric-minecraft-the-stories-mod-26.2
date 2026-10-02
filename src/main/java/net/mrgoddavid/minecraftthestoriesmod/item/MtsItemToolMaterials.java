package net.mrgoddavid.minecraftthestoriesmod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;

import static net.mrgoddavid.minecraftthestoriesmod.item.MtsItemToolMaterials.WoodenToolsDurabilityBaseline.*;
import static net.mrgoddavid.minecraftthestoriesmod.item.MtsItemToolMaterials.StrongMaterialBaseAttributes.*;

/**
 * Defines different materials of the mod item.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class MtsItemToolMaterials {

    public static final ToolMaterial ACACIA_BOW = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, (int) (OAK_BOW_DURABILITY * 1.5F), 1.0F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial BIRCH_BOW = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_BOW_DURABILITY, 2.0F * 1.25F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial CHERRY_BOW = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_BOW_DURABILITY / 2, 4.0F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial DARK_OAK_BOW = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_BOW_DURABILITY, 2.0F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial JUNGLE_BOW = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_BOW_DURABILITY * 2, 1.0F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial MANGROVE_BOW = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, (int) (OAK_BOW_DURABILITY * 0.75F), 2.0F * 1.25F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial OAK_BOW = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_BOW_DURABILITY, 2.0F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial PALE_OAK_BOW = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_BOW_DURABILITY, 2.0F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial SPRUCE_BOW = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_BOW_DURABILITY / 2, 5.0F,  0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ToolMaterial ACACIA = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, (int) (OAK_DURABILITY * 1.5F), 1.0F, 0.2F, 10, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial BIRCH = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_DURABILITY, 2.0F * 1.25F, 0.1F, 10, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial CHERRY = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_DURABILITY / 2, 4.0F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial DARK_OAK = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_DURABILITY, 2.0F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial JUNGLE = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_DURABILITY * 2, 1.0F, 0.2F, 10, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial MANGROVE = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, (int) (OAK_DURABILITY * 0.75F), 2.0F * 1.25F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial OAK = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_DURABILITY, 2.0F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial PALE_OAK = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_DURABILITY, 2.0F, 0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial SPRUCE = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, OAK_DURABILITY / 2, 5.0F,  0.0F, 15, ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ToolMaterial EMERALD = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_EMERALD_TOOL, 750, 6.5f, 2.5f, 30, MtsTags.Items.EMERALD_REPAIR);
    public static final ToolMaterial STRONG_AMETHYST = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_AMETHYST_TOOL, 2599, 9.0f, 6.0f, 20, MtsTags.Items.AMETHYST_REPAIR);
    public static final ToolMaterial STRONG_RUBY = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_RUBY_TOOL, 1897, 7.5f, 5.0f, 30, MtsTags.Items.RUBY_REPAIR);
    public static final ToolMaterial STRONG_TOPAZ = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_TOPAZ_TOOL, 1241, 10.0f, 3.5f, 30, MtsTags.Items.TOPAZ_REPAIR);

    public static final ToolMaterial STRONG_IRON = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_IRON_TOOL, STRONG_IRON_MATERIAL_BASE_DURABILITY, 6.75f, 3.5f, 30, MtsTags.Items.STRONG_IRON_REPAIR);
    public static final ToolMaterial STRONG_GOLD = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_GOLD_TOOL, STRONG_GOLD_MATERIAL_BASE_DURABILITY, 14.0f, 2.5f, 30, MtsTags.Items.STRONG_GOLD_REPAIR);
    public static final ToolMaterial STRONG_EMERALD = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_EMERALD_TOOL, STRONG_EMERALD_MATERIAL_BASE_DURABILITY, 8.5f, 3.0f, 30, MtsTags.Items.STRONG_EMERALD_REPAIR);
    public static final ToolMaterial STRONG_DIAMOND = new ToolMaterial(MtsTags.Blocks.INCORRECT_FOR_STRONG_DIAMOND_TOOL, STRONG_DIAMOND_MATERIAL_BASE_DURABILITY, 10.0f, 4.5f, 30, MtsTags.Items.STRONG_DIAMOND_REPAIR);

    static class WoodenToolsDurabilityBaseline {
        static final int OAK_BOW_DURABILITY = 378;
        static final int OAK_DURABILITY = 59;
    }

    static class StrongMaterialBaseAttributes {

        /**
         * Specifically indicates Strong Version of Iron, Gold, Diamond, and Emerald.
         */
        static final int STRONG_VANILLA_MATERIAL_BASE_DURABILITY = 700;

        static final int STRONG_GOLD_MATERIAL_BASE_DURABILITY = STRONG_VANILLA_MATERIAL_BASE_DURABILITY * 2 / 3;
        static final int STRONG_IRON_MATERIAL_BASE_DURABILITY = STRONG_VANILLA_MATERIAL_BASE_DURABILITY;
        static final int STRONG_EMERALD_MATERIAL_BASE_DURABILITY = STRONG_VANILLA_MATERIAL_BASE_DURABILITY * 4 / 3;
        static final int STRONG_DIAMOND_MATERIAL_BASE_DURABILITY = STRONG_VANILLA_MATERIAL_BASE_DURABILITY * 2;
    }
}
