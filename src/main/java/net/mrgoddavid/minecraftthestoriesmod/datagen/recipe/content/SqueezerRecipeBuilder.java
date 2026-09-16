package net.mrgoddavid.minecraftthestoriesmod.datagen.recipe.content;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.mrgoddavid.minecraftthestoriesmod.datagen.recipe.MtsAbstractRecipeBuilder;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.squeezer.SqueezerRecipe;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.squeezer.SqueezerRecipeInput;

/**
 * @author Mr. GodDavid
 * @since 9/15/2026
 */
public class SqueezerRecipeBuilder extends MtsAbstractRecipeBuilder {

    private final Ingredient fruitInNorthSlot;
    private final Ingredient fruitInNortheastSlot;
    private final Ingredient fruitInSoutheastSlot;
    private final Ingredient fruitInSouthSlot;
    private final Ingredient fruitInSouthwestSlot;
    private final Ingredient fruitInNorthwestSlot;

    private SqueezerRecipeBuilder(SqueezerRecipeBuilder.Builder builder) {
        super(builder.recipeCategory, builder.resultStack);
        this.fruitInNorthSlot = builder.fruitInNorthSlot;
        this.fruitInNortheastSlot = builder.fruitInNortheastSlot;
        this.fruitInSoutheastSlot = builder.fruitInSoutheastSlot;
        this.fruitInSouthSlot = builder.fruitInSouthSlot;
        this.fruitInSouthwestSlot = builder.fruitInSouthwestSlot;
        this.fruitInNorthwestSlot = builder.fruitInNorthwestSlot;
    }

    public static SqueezerRecipeBuilder.Builder squeezerRecipe() {
        return new SqueezerRecipeBuilder.Builder();
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        SqueezerRecipe recipe = new SqueezerRecipe(this.fruitInNorthSlot, this.fruitInNortheastSlot, this.fruitInSoutheastSlot,
                this.fruitInSouthSlot, this.fruitInSouthwestSlot, fruitInNorthwestSlot, super.result());
        output.accept(id, recipe, super.advancementBuilder().build(output, id, super.category()));
    }

    /**
     * @author Mr. GodDavid
     * @since 9/15/2026
     */
    public static final class Builder {
        private RecipeCategory recipeCategory;
        private Ingredient fruitInNorthSlot;
        private Ingredient fruitInNortheastSlot;
        private Ingredient fruitInSoutheastSlot;
        private Ingredient fruitInSouthSlot;
        private Ingredient fruitInSouthwestSlot;
        private Ingredient fruitInNorthwestSlot;
        private ItemStackTemplate resultStack;

        private Builder() {
        }

        public SqueezerRecipeBuilder.Builder category(RecipeCategory recipeCategory) {
            this.recipeCategory = recipeCategory;
            return this;
        }

        public SqueezerRecipeBuilder.Builder fruitInNorthSlot(Ingredient ingredient) {
            this.fruitInNorthSlot = ingredient;
            return this;
        }

        public SqueezerRecipeBuilder.Builder fruitInNortheastSlot(Ingredient ingredient) {
            this.fruitInNortheastSlot = ingredient;
            return this;
        }

        public SqueezerRecipeBuilder.Builder fruitInSoutheastSlot(Ingredient ingredient) {
            this.fruitInSoutheastSlot = ingredient;
            return this;
        }

        public SqueezerRecipeBuilder.Builder fruitInSouthSlot(Ingredient ingredient) {
            this.fruitInSouthSlot = ingredient;
            return this;
        }

        public SqueezerRecipeBuilder.Builder fruitInSouthwestSlot(Ingredient ingredient) {
            this.fruitInSouthwestSlot = ingredient;
            return this;
        }

        public SqueezerRecipeBuilder.Builder fruitInNorthwestSlot(Ingredient ingredient) {
            this.fruitInNorthwestSlot = ingredient;
            return this;
        }

        public SqueezerRecipeBuilder.Builder mixture(ItemLike mixture) {
            this.resultStack = new ItemStackTemplate(mixture.asItem());
            return this;
        }

        public SqueezerRecipeBuilder.Builder mixture(ItemLike mixture, int count) {
            this.resultStack = new ItemStackTemplate(mixture.asItem(), count);
            return this;
        }

        public SqueezerRecipeBuilder build() {
            return new SqueezerRecipeBuilder(this);
        }
    }
}
