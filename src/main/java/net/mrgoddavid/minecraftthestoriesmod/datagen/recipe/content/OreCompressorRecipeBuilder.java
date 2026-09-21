package net.mrgoddavid.minecraftthestoriesmod.datagen.recipe.content;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.mrgoddavid.minecraftthestoriesmod.datagen.recipe.MtsAbstractRecipeBuilder;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.ore_compressor.OreCompressorRecipe;

/**
 * @author Mr. GodDavid
 * @since 9/11/2026
 */
public class OreCompressorRecipeBuilder extends MtsAbstractRecipeBuilder {

    private final Ingredient compressedOre;
    private final Ingredient blueFuel;

    private OreCompressorRecipeBuilder(RecipeCategory category, Ingredient compressedOre, Ingredient blueFuel, ItemStackTemplate result) {
        super(category, result);
        this.compressedOre = compressedOre;
        this.blueFuel = blueFuel;
    }

    public static OreCompressorRecipeBuilder oreCompressorRecipe(RecipeCategory category, Ingredient compressedOre, Ingredient blueFuel, ItemLike result, int count) {
        return new OreCompressorRecipeBuilder(category, compressedOre, blueFuel, new ItemStackTemplate(result.asItem(), count));
    }

    public static OreCompressorRecipeBuilder oreCompressorRecipe(RecipeCategory category, Ingredient compressedOre, Ingredient blueFuel, ItemLike result) {
        return new OreCompressorRecipeBuilder(category, compressedOre, blueFuel, new ItemStackTemplate(result.asItem()));
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        OreCompressorRecipe recipe = new OreCompressorRecipe(this.compressedOre, this.blueFuel, super.result());
        output.accept(id, recipe, super.advancementBuilder().build(output, id, super.getCategory()));
    }
}
