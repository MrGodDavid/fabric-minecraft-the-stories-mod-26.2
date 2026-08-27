package net.mrgoddavid.minecraftthestoriesmod.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.recipe.custom.EnricherRecipe;
import net.mrgoddavid.minecraftthestoriesmod.recipe.custom.EnricherRecipeInput;

/**
 * @author Mr. GodDavid
 * @since 8/27/2026
 */
public class MtsRecipes {

    public static final RecipeSerializer<EnricherRecipe> ENRICHER_SERIALIZER =
            registerRecipeSerializer("enriching", new RecipeSerializer<>(EnricherRecipe.CODEC, EnricherRecipe.STREAM_CODEC));
    public static final RecipeType<EnricherRecipe> ENRICHER_TYPE = registerRecipeType("enriching",
            new RecipeType<>() {
                @Override
                public String toString() {
                    return "enriching";
                }
            });

    private static <T extends Recipe<?>> RecipeType<T> registerRecipeType(String path, RecipeType<T> recipeType) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, path), recipeType);
    }

    private static <T extends Recipe<?>> RecipeSerializer<T> registerRecipeSerializer(final String name, RecipeSerializer<T> recipeSerializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name), recipeSerializer);
    }

    public static void register() {
        MinecraftTheStoriesMod.LOGGER.info("Registering custom recipes for " + MinecraftTheStoriesMod.MOD_ID);
    }
}
