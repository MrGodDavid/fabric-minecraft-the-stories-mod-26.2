package net.mrgoddavid.minecraftthestoriesmod.recipe.content.squeezer;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

/**
 * @author Mr. GodDavid
 * @since 9/15/2026
 */
public record SqueezerRecipeInput(
        ItemStack fruitInNorthSlot, ItemStack fruitInNortheastSlot, ItemStack fruitInSoutheastSlot,
        ItemStack fruitInSouthSlot, ItemStack fruitInSouthwestSlot, ItemStack fruitInNorthwestSlot
) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return switch(index) {
            case Context.INPUT_SLOT_NORTH ->  fruitInNorthSlot;
            case Context.INPUT_SLOT_NORTHEAST ->   fruitInNortheastSlot;
            case Context.INPUT_SLOT_SOUTHEAST ->   fruitInSoutheastSlot;
            case Context.INPUT_SLOT_SOUTH ->   fruitInSouthSlot;
            case Context.INPUT_SLOT_SOUTHWEST ->   fruitInSouthwestSlot;
            case Context.INPUT_SLOT_NORTHWEST ->   fruitInNorthwestSlot;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int size() {
        return Context.DATA_SIZE;
    }

    /**
     * @author Mr. GodDavid
     * @since 9/15/2026
     */
    public static final class Context {
        public static final int INPUT_SLOT_NORTH = 0;
        public static final int INPUT_SLOT_NORTHEAST = 1;
        public static final int INPUT_SLOT_SOUTHEAST = 2;
        public static final int INPUT_SLOT_SOUTH = 3;
        public static final int INPUT_SLOT_SOUTHWEST = 4;
        public static final int INPUT_SLOT_NORTHWEST = 5;

        public static final int DATA_SIZE = 6;

    }
}
