package net.mrgoddavid.minecraftthestoriesmod.recipe.content.super_crafter;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

/**
 * @author Mr. GodDavid
 * @since 9/1/2026
 */
public record SuperCrafterRecipeInput(ItemStack template, ItemStack hammer, ItemStack stageItem) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            case Context.TEMPLATE_POSITION -> template;
            case Context.HAMMER_POSITION -> hammer;
            case Context.STAGE_ITEM_POSITION -> stageItem;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int size() {
        return Context.DATA_SIZE;
    }

    public static final class Context {
        public static final int TEMPLATE_POSITION = 0;
        public static final int HAMMER_POSITION = 1;
        public static final int STAGE_ITEM_POSITION = 2;

        public static final int DATA_SIZE = 3;
    }
}
