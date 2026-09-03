package net.mrgoddavid.minecraftthestoriesmod.datagen.recipe;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * @author Mr. GodDavid
 * @since 9/1/2026
 */
public abstract class MtsAbstractRecipeBuilder implements RecipeBuilder {

    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    private final RecipeCategory category;
    private final ItemStackTemplate result;
    private @NonNull String group;

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
        this.group = group;
        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    protected ItemStackTemplate result() {
        return result;
    }

    protected RecipeCategory category() {
        return category;
    }

    protected RecipeUnlockAdvancementBuilder advancementBuilder() {
        return advancementBuilder;
    }
}
