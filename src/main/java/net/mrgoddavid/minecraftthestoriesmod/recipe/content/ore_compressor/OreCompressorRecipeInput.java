package net.mrgoddavid.minecraftthestoriesmod.recipe.content.ore_compressor;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

/**
 * @author Mr. GodDavid
 * @since 9/11/2026
 */
public record OreCompressorRecipeInput(ItemStack compressedOre, ItemStack blueFuel) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return switch(index) {
            case Context.COMPRESSED_ORE_POSITION ->  compressedOre;
            case Context.BLUE_FUEL_POSITION ->  blueFuel;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int size() {
        return Context.DATA_SIZE;
    }

    /**
     * @author Mr. GodDavid
     * @since 9/11/2026
     */
    public static class Context {
        public static final int COMPRESSED_ORE_POSITION = 0;
        public static final int BLUE_FUEL_POSITION = 1;

        public static final int DATA_SIZE = 2;
    }
}
