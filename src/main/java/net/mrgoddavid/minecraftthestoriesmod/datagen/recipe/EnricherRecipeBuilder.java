package net.mrgoddavid.minecraftthestoriesmod.datagen.recipe;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.enricher.EnricherRecipe;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 8/31/2026
 */
public class EnricherRecipeBuilder extends MtsAbstractRecipeBuilder {

    private final Ingredient input;
    private final Ingredient fuel;
    private final int wasteAmount;

    private EnricherRecipeBuilder(RecipeCategory category, Ingredient input, Ingredient fuel, int wasteAmount, ItemStackTemplate result) {
        super(category, result);
        this.input = input;
        this.fuel = fuel;
        this.wasteAmount = wasteAmount;
    }

    public static EnricherRecipeBuilder enricherRecipe(RecipeCategory category, Ingredient input, Ingredient fuel, ItemLike result, int wasteAmount, int count) {
        return new EnricherRecipeBuilder(category, input, fuel, wasteAmount, new ItemStackTemplate(result.asItem(), count));
    }

    public static EnricherRecipeBuilder enricherRecipe(RecipeCategory category, Ingredient input, Ingredient fuel, ItemLike result, int wasteAmount) {
        return new EnricherRecipeBuilder(category, input, fuel, wasteAmount, new ItemStackTemplate(result.asItem()));
    }

    @Override
    public void save(RecipeOutput output, @NonNull ResourceKey<Recipe<?>> id) {
        EnricherRecipe recipe = new EnricherRecipe(this.input, this.fuel, super.result(), this.wasteAmount);
        output.accept(id, recipe, super.advancementBuilder().build(output, id, super.category()));
    }
}
