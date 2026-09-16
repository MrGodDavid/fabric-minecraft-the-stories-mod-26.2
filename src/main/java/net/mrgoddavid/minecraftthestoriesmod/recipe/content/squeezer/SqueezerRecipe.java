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
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 9/15/2026
 */
public record SqueezerRecipe(
        Ingredient fruitInNorthSlot, Ingredient fruitInNortheastSlot, Ingredient fruitInSoutheastSlot,
        Ingredient fruitInSouthSlot, Ingredient fruitInSouthwestSlot, Ingredient fruitInNorthwestSlot,
        ItemStackTemplate mixture
) implements Recipe<SqueezerRecipeInput> {

    public static final MapCodec<SqueezerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("fruit_in_north_slot").forGetter(SqueezerRecipe::fruitInNorthSlot),
                    Ingredient.CODEC.fieldOf("fruit_in_northeast_slot").forGetter(SqueezerRecipe::fruitInNortheastSlot),
                    Ingredient.CODEC.fieldOf("fruit_in_southeast_slot").forGetter(SqueezerRecipe::fruitInSoutheastSlot),
                    Ingredient.CODEC.fieldOf("fruit_in_south_slot").forGetter(SqueezerRecipe::fruitInSouthSlot),
                    Ingredient.CODEC.fieldOf("field_in_southwest_slot").forGetter(SqueezerRecipe::fruitInSouthwestSlot),
                    Ingredient.CODEC.fieldOf("field_in_northwest_slot").forGetter(SqueezerRecipe::fruitInNorthwestSlot),
                    ItemStackTemplate.CODEC.fieldOf("mixture").forGetter(SqueezerRecipe::mixture)
            ).apply(instance, SqueezerRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SqueezerRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, SqueezerRecipe::fruitInNorthSlot,
            Ingredient.CONTENTS_STREAM_CODEC, SqueezerRecipe::fruitInNortheastSlot,
            Ingredient.CONTENTS_STREAM_CODEC, SqueezerRecipe::fruitInSoutheastSlot,
            Ingredient.CONTENTS_STREAM_CODEC, SqueezerRecipe::fruitInSouthSlot,
            Ingredient.CONTENTS_STREAM_CODEC, SqueezerRecipe::fruitInSouthwestSlot,
            Ingredient.CONTENTS_STREAM_CODEC, SqueezerRecipe::fruitInSoutheastSlot,
            ItemStackTemplate.STREAM_CODEC, SqueezerRecipe::mixture,
            SqueezerRecipe::new
    );

    @Override
    public boolean matches(SqueezerRecipeInput input, Level level) {
        if (level.isClientSide()) return false;
        return doMatches(input);
    }

    private boolean doMatches(SqueezerRecipeInput input) {
        return this.fruitInNorthSlot.test(input.fruitInNorthSlot())
                && this.fruitInNortheastSlot.test(input.fruitInNortheastSlot())
                && this.fruitInSoutheastSlot.test(input.fruitInSoutheastSlot())
                && this.fruitInSouthSlot.test(input.fruitInSouthSlot())
                && this.fruitInSouthwestSlot.test(input.fruitInSouthwestSlot())
                && this.fruitInNorthwestSlot.test(input.fruitInNorthwestSlot());
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

    @Override
    public @NonNull Ingredient fruitInNorthSlot() {
        return (this.fruitInNorthSlot != null) ? this.fruitInNorthSlot : Constants.Universal.NULL_INGREDIENT;
    }

    @Override
    public @NonNull Ingredient fruitInNortheastSlot() {
        return (this.fruitInNortheastSlot != null) ? this.fruitInNortheastSlot : Constants.Universal.NULL_INGREDIENT;
    }

    @Override
    public @NonNull Ingredient fruitInSoutheastSlot() {
        return (this.fruitInSoutheastSlot != null) ? this.fruitInSoutheastSlot : Constants.Universal.NULL_INGREDIENT;
    }

    @Override
    public @NonNull Ingredient fruitInSouthSlot() {
        return (this.fruitInSouthSlot != null) ? this.fruitInSouthSlot : Constants.Universal.NULL_INGREDIENT;
    }

    @Override
    public @NonNull Ingredient fruitInSouthwestSlot() {
        return (this.fruitInSouthwestSlot != null) ? this.fruitInSouthwestSlot : Constants.Universal.NULL_INGREDIENT;
    }

    @Override
    public @NonNull Ingredient fruitInNorthwestSlot() {
        return (this.fruitInNorthwestSlot != null) ? this.fruitInNorthwestSlot : Constants.Universal.NULL_INGREDIENT;
    }
}
