package net.mrgoddavid.minecraftthestoriesmod.recipe.content.squeezer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.recipe.MtsRecipes;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 9/15/2026
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public record SqueezerRecipe(
        Optional<Ingredient> fruitInNorthSlot, Optional<Ingredient> fruitInNortheastSlot, Optional<Ingredient> fruitInSoutheastSlot,
        Optional<Ingredient> fruitInSouthSlot, Optional<Ingredient> fruitInSouthwestSlot, Optional<Ingredient> fruitInNorthwestSlot,
        ItemStackTemplate mixture
) implements Recipe<SqueezerRecipeInput> {

    public static final MapCodec<SqueezerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.optionalFieldOf("fruit_in_north_slot").forGetter(SqueezerRecipe::fruitInNorthSlot),
                    Ingredient.CODEC.optionalFieldOf("fruit_in_northeast_slot").forGetter(SqueezerRecipe::fruitInNortheastSlot),
                    Ingredient.CODEC.optionalFieldOf("fruit_in_southeast_slot").forGetter(SqueezerRecipe::fruitInSoutheastSlot),
                    Ingredient.CODEC.optionalFieldOf("fruit_in_south_slot").forGetter(SqueezerRecipe::fruitInSouthSlot),
                    Ingredient.CODEC.optionalFieldOf("field_in_southwest_slot").forGetter(SqueezerRecipe::fruitInSouthwestSlot),
                    Ingredient.CODEC.optionalFieldOf("field_in_northwest_slot").forGetter(SqueezerRecipe::fruitInNorthwestSlot),
                    ItemStackTemplate.CODEC.fieldOf("mixture").forGetter(SqueezerRecipe::mixture)
            ).apply(instance, SqueezerRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SqueezerRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, SqueezerRecipe::fruitInNorthSlot,
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, SqueezerRecipe::fruitInNortheastSlot,
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, SqueezerRecipe::fruitInSoutheastSlot,
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, SqueezerRecipe::fruitInSouthSlot,
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, SqueezerRecipe::fruitInSouthwestSlot,
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, SqueezerRecipe::fruitInSoutheastSlot,
            ItemStackTemplate.STREAM_CODEC, SqueezerRecipe::mixture,
            SqueezerRecipe::new
    );

    @Override
    public boolean matches(SqueezerRecipeInput input, Level level) {
        if (level.isClientSide()) return false;
        return doMatches(input);
    }

    private boolean doMatches(SqueezerRecipeInput input) {
        return matches(this.fruitInNorthSlot, input.fruitInNorthSlot())
                && matches(this.fruitInNortheastSlot, input.fruitInNortheastSlot())
                && matches(this.fruitInSoutheastSlot, input.fruitInSoutheastSlot())
                && matches(this.fruitInSouthSlot, input.fruitInSouthSlot())
                && matches(this.fruitInSouthwestSlot, input.fruitInSouthwestSlot())
                && matches(this.fruitInNorthwestSlot, input.fruitInNorthwestSlot());
    }

    private boolean matches(Optional<Ingredient> ingredient, ItemStack input) {
        return ingredient.map(value -> value.test(input)).orElseGet(input::isEmpty);
    }

    @Override
    public ItemStack assemble(SqueezerRecipeInput input) {
        return mixture.create().copy();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "Squeezing";
    }

    @Override
    public RecipeSerializer<? extends Recipe<SqueezerRecipeInput>> getSerializer() {
        return MtsRecipes.SQUEEZER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<SqueezerRecipeInput>> getType() {
        return MtsRecipes.SQUEEZER_TYPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }
}
