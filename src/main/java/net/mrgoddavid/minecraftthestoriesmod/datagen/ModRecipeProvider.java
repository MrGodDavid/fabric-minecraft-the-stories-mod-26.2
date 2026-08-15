package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.mrgoddavid.minecraftthestoriesmod.item.ModItems.*;
import static net.mrgoddavid.minecraftthestoriesmod.block.ModBlocks.*;

/**
 * Provides custom crafting recipes of this mod.
 *
 * @author Mr. GodDavid
 * @since 8/15/2026
 */
public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * Implement this method and then use the range of methods in {@link RecipeProvider} or from one of the recipe json
     * factories such as {@link ShapedRecipeBuilder} or {@link ShapelessRecipeBuilder}.
     *
     * @param registries registries
     * @param output     output.
     */
    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {

            /**
             * Access widened by fabric-data-generation-api-v1 to accessible
             */
            @Override
            public void buildRecipes() {
                // #################################################################################################################
                // #                                        IRON MATERIAL RECIPES                                                  #
                // #################################################################################################################
                nineBlockStorageRecipes(RecipeCategory.MISC, STRONG_IRON_INGOT, RecipeCategory.BUILDING_BLOCKS, STRONG_IRON_BLOCK);
                shapeless(RecipeCategory.MISC, STRONG_IRON_INGOT, 9)
                        .requires(STRONG_IRON_BLOCK)
                        .unlockedBy(getHasName(STRONG_IRON_INGOT), has(STRONG_IRON_BLOCK))
                        .group("strong_iron")
                        .save(output);

                // #################################################################################################################
                // #                                        TOPAZ MATERIAL RECIPES                                                 #
                // #################################################################################################################
                List<ItemLike> TOPAZ_SMELTABLE = List.of(STONE_TOPAZ_ORE, DEEPSLATE_TOPAZ_ORE);
                oreSmelting(TOPAZ_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, RAW_TOPAZ, 0.25f, 200, "topaz");
                oreBlasting(TOPAZ_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, RAW_TOPAZ, 0.25f, 100, "topaz");
                nineBlockStorageRecipes(RecipeCategory.MISC, RAW_TOPAZ, RecipeCategory.BUILDING_BLOCKS, RAW_TOPAZ_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.MISC, STRONG_TOPAZ_INGOT, RecipeCategory.BUILDING_BLOCKS, STRONG_TOPAZ_BLOCK);
                shapeless(RecipeCategory.MISC, RAW_TOPAZ, 9)
                        .requires(RAW_TOPAZ_BLOCK)
                        .unlockedBy(getHasName(RAW_TOPAZ), has(RAW_TOPAZ))
                        .group("topaz")
                        .save(output);
                shapeless(RecipeCategory.MISC, STRONG_TOPAZ_INGOT, 9)
                        .requires(STRONG_TOPAZ_BLOCK)
                        .unlockedBy(getHasName(RAW_TOPAZ), has(RAW_TOPAZ))
                        .group("topaz")
                        .save(output);
                shaped(RecipeCategory.TOOLS, TOPAZ_AXE)
                        .define('#', Items.STICK)
                        .define('X', STRONG_TOPAZ_INGOT)
                        .pattern("XX")
                        .pattern("X#")
                        .pattern(" #")
                        .unlockedBy(getHasName(RAW_TOPAZ), has(RAW_TOPAZ))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.TOOLS, TOPAZ_HOE)
                        .define('#', Items.STICK)
                        .define('X', STRONG_TOPAZ_INGOT)
                        .pattern("XX")
                        .pattern(" #")
                        .pattern(" #")
                        .unlockedBy(getHasName(RAW_TOPAZ), has(RAW_TOPAZ))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.TOOLS, TOPAZ_PICKAXE)
                        .define('#', Items.STICK)
                        .define('X', STRONG_TOPAZ_INGOT)
                        .pattern("XXX")
                        .pattern(" # ")
                        .pattern(" # ")
                        .unlockedBy(getHasName(RAW_TOPAZ), has(RAW_TOPAZ))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.TOOLS, TOPAZ_SHOVEL)
                        .define('#', Items.STICK)
                        .define('X', STRONG_TOPAZ_INGOT)
                        .pattern("X")
                        .pattern("#")
                        .pattern("#")
                        .unlockedBy(getHasName(RAW_TOPAZ), has(RAW_TOPAZ))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.COMBAT, TOPAZ_SPEAR)
                        .define('#', Items.STICK)
                        .define('X', STRONG_TOPAZ_INGOT)
                        .pattern("  X")
                        .pattern(" # ")
                        .pattern("#  ")
                        .unlockedBy(getHasName(RAW_TOPAZ), has(RAW_TOPAZ))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.COMBAT, TOPAZ_SWORD)
                        .define('#', Items.STICK)
                        .define('X', STRONG_TOPAZ_INGOT)
                        .pattern("X")
                        .pattern("X")
                        .pattern("#")
                        .unlockedBy(getHasName(RAW_TOPAZ), has(RAW_TOPAZ))
                        .group("amethyst")
                        .save(output);

                // #################################################################################################################
                // #                                           RUBY MATERIAL RECIPES                                               #
                // #################################################################################################################
                List<ItemLike> RUBY_SMELTABLE = List.of(STONE_RUBY_ORE, DEEPSLATE_RUBY_ORE);
                oreSmelting(RUBY_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, RAW_RUBY, 0.25f, 200, "topaz");
                oreBlasting(RUBY_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, RAW_RUBY, 0.25f, 100, "topaz");
                nineBlockStorageRecipes(RecipeCategory.MISC, RAW_RUBY, RecipeCategory.BUILDING_BLOCKS, RAW_RUBY_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.MISC, STRONG_RUBY_INGOT, RecipeCategory.BUILDING_BLOCKS, STRONG_TOPAZ_BLOCK);
                shapeless(RecipeCategory.MISC, RAW_RUBY, 9)
                        .requires(RAW_RUBY_BLOCK)
                        .unlockedBy(getHasName(RAW_RUBY), has(RAW_RUBY))
                        .group("topaz")
                        .save(output);
                shapeless(RecipeCategory.MISC, STRONG_RUBY_INGOT, 9)
                        .requires(STRONG_RUBY_BLOCK)
                        .unlockedBy(getHasName(RAW_RUBY), has(RAW_RUBY))
                        .group("topaz")
                        .save(output);
                shaped(RecipeCategory.TOOLS, RUBY_AXE)
                        .define('#', Items.STICK)
                        .define('X', STRONG_RUBY_INGOT)
                        .pattern("XX")
                        .pattern("X#")
                        .pattern(" #")
                        .unlockedBy(getHasName(RAW_RUBY), has(RAW_RUBY))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.TOOLS, RUBY_HOE)
                        .define('#', Items.STICK)
                        .define('X', STRONG_RUBY_INGOT)
                        .pattern("XX")
                        .pattern(" #")
                        .pattern(" #")
                        .unlockedBy(getHasName(RAW_RUBY), has(RAW_RUBY))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.TOOLS, RUBY_PICKAXE)
                        .define('#', Items.STICK)
                        .define('X', STRONG_RUBY_INGOT)
                        .pattern("XXX")
                        .pattern(" # ")
                        .pattern(" # ")
                        .unlockedBy(getHasName(RAW_RUBY), has(RAW_RUBY))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.TOOLS, RUBY_SHOVEL)
                        .define('#', Items.STICK)
                        .define('X', STRONG_RUBY_INGOT)
                        .pattern("X")
                        .pattern("#")
                        .pattern("#")
                        .unlockedBy(getHasName(RAW_RUBY), has(RAW_RUBY))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.COMBAT, RUBY_SPEAR)
                        .define('#', Items.STICK)
                        .define('X', STRONG_RUBY_INGOT)
                        .pattern("  X")
                        .pattern(" # ")
                        .pattern("#  ")
                        .unlockedBy(getHasName(RAW_RUBY), has(RAW_TOPAZ))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.COMBAT, RUBY_SWORD)
                        .define('#', Items.STICK)
                        .define('X', STRONG_RUBY_INGOT)
                        .pattern("X")
                        .pattern("X")
                        .pattern("#")
                        .unlockedBy(getHasName(RAW_RUBY), has(RAW_RUBY))
                        .group("amethyst")
                        .save(output);

                // #################################################################################################################
                // #                                        AMETHYST MATERIAL RECIPES                                              #
                // #################################################################################################################
                List<ItemLike> AMETHYST_SMELTABLE = List.of(STONE_AMETHYST_ORE, DEEPSLATE_AMETHYST_ORE);
                // smelting amethyst ores -> raw amethyst
                oreSmelting(AMETHYST_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, RAW_AMETHYST, 0.5f, 300, "amethyst");
                oreBlasting(AMETHYST_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, RAW_AMETHYST, 0.5f, 150, "amethyst");
                // 9 x RAW_AMETHYST => RAW_AMETHYST_BLOCK
                // The first ItemLike parameter is the ingredient, and the second ItemLike parameter is the crafted item.
                nineBlockStorageRecipes(RecipeCategory.MISC, RAW_AMETHYST, RecipeCategory.BUILDING_BLOCKS, RAW_AMETHYST_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.MISC, STRONG_AMETHYST_INGOT, RecipeCategory.BUILDING_BLOCKS, STRONG_AMETHYST_BLOCK);
                shapeless(RecipeCategory.MISC, RAW_AMETHYST, 9)
                        .requires(RAW_AMETHYST_BLOCK)
                        .unlockedBy(getHasName(RAW_AMETHYST), has(RAW_AMETHYST))
                        .group("amethyst")
                        .save(output, "raw_amethyst_from_raw_amethyst_block");
                shapeless(RecipeCategory.MISC, STRONG_AMETHYST_INGOT, 9)
                        .requires(STRONG_AMETHYST_BLOCK)
                        .unlockedBy(getHasName(RAW_AMETHYST), has(RAW_AMETHYST))
                        .group("amethyst")
                        .save(output, "strong_amethyst_ingot_from_strong_amethyst_block");
                shaped(RecipeCategory.TOOLS, STRONG_AMETHYST_AXE)
                        .define('#', Items.STICK)
                        .define('X', STRONG_AMETHYST_INGOT)
                        .pattern("XX")
                        .pattern("X#")
                        .pattern(" #")
                        .unlockedBy(getHasName(RAW_AMETHYST), has(RAW_AMETHYST))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.TOOLS, AMETHYST_HOE)
                        .define('#', Items.STICK)
                        .define('X', STRONG_AMETHYST_INGOT)
                        .pattern("XX")
                        .pattern(" #")
                        .pattern(" #")
                        .unlockedBy(getHasName(RAW_AMETHYST), has(RAW_AMETHYST))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.TOOLS, AMETHYST_PICKAXE)
                        .define('#', Items.STICK)
                        .define('X', STRONG_AMETHYST_INGOT)
                        .pattern("XXX")
                        .pattern(" # ")
                        .pattern(" # ")
                        .unlockedBy(getHasName(RAW_AMETHYST), has(RAW_AMETHYST))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.TOOLS, AMETHYST_SHOVEL)
                        .define('#', Items.STICK)
                        .define('X', STRONG_AMETHYST_INGOT)
                        .pattern("X")
                        .pattern("#")
                        .pattern("#")
                        .unlockedBy(getHasName(RAW_AMETHYST), has(RAW_AMETHYST))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.COMBAT, AMETHYST_SPEAR)
                        .define('#', Items.STICK)
                        .define('X', STRONG_AMETHYST_INGOT)
                        .pattern("  X")
                        .pattern(" # ")
                        .pattern("#  ")
                        .unlockedBy(getHasName(RAW_AMETHYST), has(RAW_AMETHYST))
                        .group("amethyst")
                        .save(output);
                shaped(RecipeCategory.COMBAT, AMETHYST_SWORD)
                        .define('#', Items.STICK)
                        .define('X', STRONG_AMETHYST_INGOT)
                        .pattern("X")
                        .pattern("X")
                        .pattern("#")
                        .unlockedBy(getHasName(RAW_AMETHYST), has(RAW_AMETHYST))
                        .group("amethyst")
                        .save(output);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "Mod Recipe Provider";
    }
}
