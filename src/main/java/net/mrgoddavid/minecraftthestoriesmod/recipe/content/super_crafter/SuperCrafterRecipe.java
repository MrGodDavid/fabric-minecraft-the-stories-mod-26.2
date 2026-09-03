package net.mrgoddavid.minecraftthestoriesmod.recipe.content.super_crafter;

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
 * @since 9/1/2026
 */
public record SuperCrafterRecipe(Ingredient updateScroll, Ingredient hammer, Ingredient stageItem, ItemStackTemplate output) implements Recipe<SuperCrafterRecipeInput> {

    public static final MapCodec<SuperCrafterRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("update_scroll").forGetter(SuperCrafterRecipe::updateScroll),
                    Ingredient.CODEC.fieldOf("hammer").forGetter(SuperCrafterRecipe::hammer),
                    Ingredient.CODEC.fieldOf("stage_item").forGetter(SuperCrafterRecipe::stageItem),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(SuperCrafterRecipe::output)
            ).apply(instance, SuperCrafterRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SuperCrafterRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, SuperCrafterRecipe::updateScroll,
            Ingredient.CONTENTS_STREAM_CODEC, SuperCrafterRecipe::hammer,
            Ingredient.CONTENTS_STREAM_CODEC, SuperCrafterRecipe::stageItem,
            ItemStackTemplate.STREAM_CODEC, SuperCrafterRecipe::output,
            SuperCrafterRecipe::new);

    @Override
    public boolean matches(SuperCrafterRecipeInput input, Level level) {
        if (level.isClientSide()) return false;
        return this.updateScroll.test(input.getItem(SuperCrafterRecipeInput.Context.TEMPLATE_POSITION))
                && this.hammer.test(input.getItem(SuperCrafterRecipeInput.Context.HAMMER_POSITION))
                && this.stageItem.test(input.getItem(SuperCrafterRecipeInput.Context.STAGE_ITEM_POSITION));
    }

    @Override
    public ItemStack assemble(SuperCrafterRecipeInput input) {
        return output.create().copy();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "SuperCrafting";
    }

    @Override
    public RecipeSerializer<? extends Recipe<SuperCrafterRecipeInput>> getSerializer() {
        return MtsRecipes.SUPER_CRAFTER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<SuperCrafterRecipeInput>> getType() {
        return MtsRecipes.SUPER_CRAFTER_TYPE;
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
