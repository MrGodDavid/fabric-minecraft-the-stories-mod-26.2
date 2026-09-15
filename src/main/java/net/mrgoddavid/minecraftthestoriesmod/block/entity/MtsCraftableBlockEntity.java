package net.mrgoddavid.minecraftthestoriesmod.block.entity;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 9/11/2026
 */
public interface MtsCraftableBlockEntity<T extends Recipe<?>> {

    /**
     * Implements this method based on the inventory of your block entity.
     *
     * @return optional recipe holder of your block's custom entity (if that block entity can craft/blast/smith items).
     */
    Optional<RecipeHolder<T>> getCurrentRecipe();
}
