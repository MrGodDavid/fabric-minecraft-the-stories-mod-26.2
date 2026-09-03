package net.mrgoddavid.minecraftthestoriesmod.datagen.recipe;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.super_crafter.SuperCrafterRecipe;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 9/1/2026
 */
public class SuperCrafterRecipeBuilder extends MtsAbstractRecipeBuilder{

    private final Ingredient updateScroll;
    private final Ingredient hammer;
    private final Ingredient stageItem;

    public SuperCrafterRecipeBuilder(RecipeCategory category, Ingredient updateScroll, Ingredient hammer, Ingredient stageItem, ItemStackTemplate result) {
        super(category, result);
        this.updateScroll = updateScroll;
        this.hammer = hammer;
        this.stageItem = stageItem;
    }

    public static SuperCrafterRecipeBuilder superCrafterRecipe(RecipeCategory category, Ingredient updateScroll, Ingredient hammer, Ingredient stageItem, ItemLike result, int count) {
        return new SuperCrafterRecipeBuilder(category, updateScroll, hammer, stageItem, new ItemStackTemplate(result.asItem(), count));
    }

    public static SuperCrafterRecipeBuilder superCrafterRecipe(RecipeCategory category, Ingredient updateScroll, Ingredient hammer, Ingredient stageItem, ItemLike result) {
        return new SuperCrafterRecipeBuilder(category, updateScroll, hammer, stageItem, new ItemStackTemplate(result.asItem()));
    }

    @Override
    public void save(RecipeOutput output, @NonNull ResourceKey<Recipe<?>> id) {
        SuperCrafterRecipe recipe = new SuperCrafterRecipe(this.updateScroll, this.hammer, this.stageItem, super.result());
        output.accept(id, recipe, super.advancementBuilder().build(output, id, super.category()));
    }
}
