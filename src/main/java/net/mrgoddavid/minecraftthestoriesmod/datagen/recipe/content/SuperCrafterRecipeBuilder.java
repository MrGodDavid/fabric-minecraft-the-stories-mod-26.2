package net.mrgoddavid.minecraftthestoriesmod.datagen.recipe.content;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.mrgoddavid.minecraftthestoriesmod.datagen.recipe.MtsAbstractRecipeBuilder;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.super_crafter.SuperCrafterRecipe;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 9/1/2026
 */
public class SuperCrafterRecipeBuilder extends MtsAbstractRecipeBuilder {

    private final Ingredient updateScroll;
    private final Ingredient hammer;
    private final Ingredient stageItem;
    private final int hammerUsage;

    private SuperCrafterRecipeBuilder(RecipeCategory category, Ingredient updateScroll, Ingredient hammer, Ingredient stageItem, ItemStackTemplate result, int hammerUsage) {
        super(category, result);
        this.updateScroll = updateScroll;
        this.hammer = hammer;
        this.stageItem = stageItem;
        this.hammerUsage = hammerUsage;
    }

    public static SuperCrafterRecipeBuilder superCrafterRecipe(RecipeCategory category, Ingredient updateScroll, Ingredient hammer, Ingredient stageItem, ItemLike result, int count, int hammerUsage) {
        return new SuperCrafterRecipeBuilder(category, updateScroll, hammer, stageItem, new ItemStackTemplate(result.asItem(), count), hammerUsage);
    }

    public static SuperCrafterRecipeBuilder superCrafterRecipe(RecipeCategory category, Ingredient updateScroll, Ingredient hammer, Ingredient stageItem, ItemLike result, int hammerUsage) {
        return new SuperCrafterRecipeBuilder(category, updateScroll, hammer, stageItem, new ItemStackTemplate(result.asItem()), hammerUsage);
    }

    @Override
    public void save(RecipeOutput output, @NonNull ResourceKey<Recipe<?>> id) {
        SuperCrafterRecipe recipe = new SuperCrafterRecipe(this.updateScroll, this.hammer, this.stageItem, super.result(),  this.hammerUsage);
        output.accept(id, recipe, super.advancementBuilder().build(output, id, super.category()));
    }
}
