package net.mrgoddavid.minecraftthestoriesmod.recipe.content.ore_compressor;

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
 * @since 9/11/2026
 */
public record OreCompressorRecipe(Ingredient compressedOre, Ingredient blueFuel, ItemStackTemplate output) implements Recipe<OreCompressorRecipeInput> {

    public static final MapCodec<OreCompressorRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("compressed_ore").forGetter(OreCompressorRecipe::compressedOre),
                    Ingredient.CODEC.fieldOf("blue_fuel").forGetter(OreCompressorRecipe::blueFuel),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(OreCompressorRecipe::output)
            ).apply(instance, OreCompressorRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, OreCompressorRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, OreCompressorRecipe::compressedOre,
            Ingredient.CONTENTS_STREAM_CODEC, OreCompressorRecipe::blueFuel,
            ItemStackTemplate.STREAM_CODEC, OreCompressorRecipe::output,
            OreCompressorRecipe::new
    );

    @Override
    public boolean matches(OreCompressorRecipeInput input, Level level) {
        if (level.isClientSide()) return false;
        return this.compressedOre.test(input.compressedOre()) && this.blueFuel.test(input.blueFuel());
    }

    @Override
    public ItemStack assemble(OreCompressorRecipeInput input) {
        return output.create().copy();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "OreCompressing";
    }

    @Override
    public RecipeSerializer<? extends Recipe<OreCompressorRecipeInput>> getSerializer() {
        return MtsRecipes.ORE_COMPRESSOR_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<OreCompressorRecipeInput>> getType() {
        return MtsRecipes.ORE_COMPRESSOR_TYPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.BLAST_FURNACE_MISC;
    }
}
