package net.mrgoddavid.minecraftthestoriesmod.datagen.recipe;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 9/1/2026
 */
@SuppressWarnings("NullableProblems")
public abstract class MtsAbstractRecipeBuilder implements RecipeBuilder {

    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    private final RecipeCategory category;
    private final ItemStackTemplate result;
    private @Nullable String group;

    public MtsAbstractRecipeBuilder(RecipeCategory category, ItemStackTemplate result) {
        this.category = category;
        this.result = result;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(result);
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        this.group = Optional.ofNullable(group).orElse("[DEFAULT_GROUP]");
        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    protected final ItemStackTemplate result() {
        return this.result;
    }

    protected final RecipeCategory getCategory() {
        return this.category;
    }

    protected final RecipeUnlockAdvancementBuilder advancementBuilder() {
        return this.advancementBuilder;
    }
}
