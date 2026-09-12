package net.mrgoddavid.minecraftthestoriesmod.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.enricher.EnricherRecipe;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.ore_compressor.OreCompressorRecipe;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.super_crafter.SuperCrafterRecipe;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 8/27/2026
 */
public class MtsRecipes {

    public static final RecipeSerializer<EnricherRecipe> ENRICHER_SERIALIZER = registerRecipeSerializer("enriching", new RecipeSerializer<>(EnricherRecipe.CODEC, EnricherRecipe.STREAM_CODEC));
    public static final RecipeSerializer<SuperCrafterRecipe> SUPER_CRAFTER_SERIALIZER = registerRecipeSerializer("super_crafting", new RecipeSerializer<>(SuperCrafterRecipe.CODEC, SuperCrafterRecipe.STREAM_CODEC));
    public static final RecipeSerializer<OreCompressorRecipe> ORE_COMPRESSOR_SERIALIZER = registerRecipeSerializer("ore_compressing", new RecipeSerializer<>(OreCompressorRecipe.CODEC, OreCompressorRecipe.STREAM_CODEC));

    public static final RecipeType<EnricherRecipe> ENRICHER_TYPE = registerRecipeType("enriching", new RecipeType<EnricherRecipe>() {
        @Override
        public String toString() {
            return "enriching";
        }
    });
    public static final RecipeType<SuperCrafterRecipe> SUPER_CRAFTER_TYPE = registerRecipeType("super_crafting", new RecipeType<SuperCrafterRecipe>() {
        @Override
        public String toString() {
            return "super_crafting";
        }
    });
    public static final RecipeType<OreCompressorRecipe> ORE_COMPRESSOR_TYPE = registerRecipeType("ore_compressing", new RecipeType<OreCompressorRecipe>() {
        @Override
        public String toString() {
            return "ore_compressing";
        }
    });


    private static <T extends Recipe<?>> RecipeType<T> registerRecipeType(String path, RecipeType<T> recipeType) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, Constants.modId(path), recipeType);
    }

    private static <T extends Recipe<?>> RecipeSerializer<T> registerRecipeSerializer(final String name, RecipeSerializer<T> recipeSerializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Constants.modId(name), recipeSerializer);
    }

    public static void register() {
        MtsLogger.info("MTS Recipes");
    }
}
