package net.mrgoddavid.minecraftthestoriesmod.block.entity;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 9/11/2026
 */
public interface MtsCraftableBlockEntity<T extends Recipe<?>> {

    Optional<RecipeHolder<T>> getCurrentRecipe();
}
