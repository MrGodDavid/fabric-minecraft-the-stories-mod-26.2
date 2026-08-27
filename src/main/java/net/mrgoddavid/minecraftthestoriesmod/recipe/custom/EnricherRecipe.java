package net.mrgoddavid.minecraftthestoriesmod.recipe.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.recipe.MtsRecipes;

/**
 * @author Mr. GodDavid
 * @since 8/27/2026
 */
public record EnricherRecipe(Ingredient input, Ingredient fuel, ItemStackTemplate output) implements Recipe<EnricherRecipeInput> {

    // CODEC ---> Codec & StreamCodec
    // Codec ==> Create a Java Object Instance from JSON File and write to JSON.
    public static final MapCodec<EnricherRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("input").forGetter(EnricherRecipe::input),
                    Ingredient.CODEC.fieldOf("fuel").forGetter(EnricherRecipe::fuel),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(EnricherRecipe::output)
            ).apply(instance, EnricherRecipe::new));
    // StreamCodec
    // Java Object ==> Turn in into Bytes for Network traffic.
    // Turn Bytes into a new Object again!
    public static final StreamCodec<RegistryFriendlyByteBuf, EnricherRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, EnricherRecipe::input,
            Ingredient.CONTENTS_STREAM_CODEC, EnricherRecipe::fuel,
            ItemStackTemplate.STREAM_CODEC, EnricherRecipe::output,
            EnricherRecipe::new
    );

    @Override
    public boolean matches(EnricherRecipeInput input, Level level) {
        if (level.isClientSide()) return false;
        return this.input.test(input.getItem(EnricherRecipeInput.Context.ORE_INDEX))
                && this.fuel.test(input.getItem(EnricherRecipeInput.Context.FUEL_INDEX));
    }

    @Override
    public ItemStack assemble(EnricherRecipeInput input) {
        return output.create().copy();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "Enriching";
    }

    @Override
    public RecipeSerializer<? extends Recipe<EnricherRecipeInput>> getSerializer() {
        return MtsRecipes.ENRICHER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<EnricherRecipeInput>> getType() {
        return MtsRecipes.ENRICHER_TYPE;
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
