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
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

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
        super(builder.recipeCategory, builder.mixture);
        this.fruitInNorthSlot = builder.fruitInNorthSlot;
        this.fruitInNortheastSlot = builder.fruitInNortheastSlot;
        this.fruitInSoutheastSlot = builder.fruitInSoutheastSlot;
        this.fruitInSouthSlot = builder.fruitInSouthSlot;
        this.fruitInSouthwestSlot = builder.fruitInSouthwestSlot;
        this.fruitInNorthwestSlot = builder.fruitInNorthwestSlot;
    }

    public static SqueezerRecipeBuilder.Builder squeezerRecipe(RecipeCategory recipeCategory, ItemLike mixture) {
        return new SqueezerRecipeBuilder.Builder(recipeCategory, mixture);
    }

    public static SqueezerRecipeBuilder.Builder squeezerRecipe(RecipeCategory recipeCategory, ItemLike mixture, int count) {
        return new SqueezerRecipeBuilder.Builder(recipeCategory, mixture, count);
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        SqueezerRecipe recipe = new SqueezerRecipe(
                Optional.ofNullable(this.fruitInNorthSlot),
                Optional.ofNullable(this.fruitInNortheastSlot),
                Optional.ofNullable(this.fruitInSoutheastSlot),
                Optional.ofNullable(this.fruitInSouthSlot),
                Optional.ofNullable(this.fruitInSouthwestSlot),
                Optional.ofNullable(this.fruitInNorthwestSlot),
                super.result());
        output.accept(id, recipe, super.advancementBuilder().build(output, id, super.category()));
    }

    /**
     * @author Mr. GodDavid
     * @since 9/15/2026
     */
    public static final class Builder {
        private final RecipeCategory recipeCategory;
        private final ItemStackTemplate mixture;
        private @Nullable Ingredient fruitInNorthSlot;
        private @Nullable Ingredient fruitInNortheastSlot;
        private @Nullable Ingredient fruitInSoutheastSlot;
        private @Nullable Ingredient fruitInSouthSlot;
        private @Nullable Ingredient fruitInSouthwestSlot;
        private @Nullable Ingredient fruitInNorthwestSlot;

        private Builder(RecipeCategory recipeCategory, ItemLike mixture) {
            this(recipeCategory, mixture, 1);
        }

        private Builder(RecipeCategory recipeCategory, ItemLike mixture, int count) {
            this.recipeCategory = recipeCategory;
            this.mixture = new ItemStackTemplate(mixture.asItem(), count);
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

        public SqueezerRecipeBuilder build() {
            return new SqueezerRecipeBuilder(this);
        }
    }
}
