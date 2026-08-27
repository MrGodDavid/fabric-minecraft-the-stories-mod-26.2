package net.mrgoddavid.minecraftthestoriesmod.recipe.custom;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

/**
 * @author Mr. GodDavid
 * @since 8/27/2026
 */
public record EnricherRecipeInput(ItemStack ore, ItemStack fuel) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return index == Context.ORE_INDEX ? ore : fuel;
    }

    @Override
    public int size() {
        return Context.TOTAL_SIZE;
    }

    public static final class Context {
        public static final int ORE_INDEX = 0;
        public static final int FUEL_INDEX = 1;

        public static final int TOTAL_SIZE = 2;
    }
}
