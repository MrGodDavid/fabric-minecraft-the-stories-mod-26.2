package net.mrgoddavid.minecraftthestoriesmod.datagen.recipe;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.mrgoddavid.minecraftthestoriesmod.recipe.custom.EnricherRecipe;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * @author Mr. GodDavid
 * @since 8/31/2026
 */
public class EnricherRecipeBuilder implements RecipeBuilder {

    private final RecipeCategory category;
    private final ItemStackTemplate result;
    private final Ingredient input;
    private final Ingredient fuel;
    private final int wasteAmount;
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    private @NonNull String group;

    private EnricherRecipeBuilder(RecipeCategory category, Ingredient input, Ingredient fuel, int wasteAmount, ItemStackTemplate result) {
        this.category = category;
        this.input = input;
        this.fuel = fuel;
        this.wasteAmount = wasteAmount;
        this.result = result;
    }

    public static EnricherRecipeBuilder enricherRecipe(RecipeCategory category, Ingredient input, Ingredient fuel, ItemLike result, int wasteAmount, int count) {
        return new EnricherRecipeBuilder(category, input, fuel, wasteAmount, new ItemStackTemplate(result.asItem(), count));
    }

    public static EnricherRecipeBuilder enricherRecipe(RecipeCategory category, Ingredient input, Ingredient fuel, ItemLike result, int wasteAmount) {
        return new EnricherRecipeBuilder(category, input, fuel, wasteAmount, new ItemStackTemplate(result.asItem()));
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(result);
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        EnricherRecipe recipe = new EnricherRecipe(this.input, this.fuel, this.result, this.wasteAmount);
        output.accept(id, recipe, this.advancementBuilder.build(output, id, this.category));
    }
}
