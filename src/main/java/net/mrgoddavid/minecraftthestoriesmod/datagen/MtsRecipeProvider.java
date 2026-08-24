package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.mrgoddavid.minecraftthestoriesmod.item.MtsItems.*;
import static net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks.*;

/**
 * Provides custom crafting recipes of this mod.
 *
 * @author Mr. GodDavid
 * @since 8/15/2026
 */
public class MtsRecipeProvider extends FabricRecipeProvider {

    public MtsRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * Implement this method and then use the range of methods in {@link RecipeProvider} or from one of the recipe JSON
     * factories such as {@link ShapedRecipeBuilder} or {@link ShapelessRecipeBuilder}.
     *
     * @param registries registries
     * @param output     output.
     */
    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries, output) {

            /**
             * Access widened by fabric-data-generation-api-v1 to accessible
             */
            @Override
            public void buildRecipes() {
                // #################################################################################################################
                // #                                        COMPRESSED TOOLS RECIPES                                               #
                // #################################################################################################################
                shaped(RecipeCategory.MISC, COMPRESSED_WOOD_PLANKS)
                        .define('A', Blocks.OAK_PLANKS)
                        .define('B', Blocks.SPRUCE_PLANKS)
                        .define('C', Blocks.JUNGLE_PLANKS)
                        .define('D', Blocks.BIRCH_PLANKS)
                        .define('E', Blocks.DARK_OAK_PLANKS)
                        .define('F', Blocks.ACACIA_PLANKS)
                        .define('G', Blocks.MANGROVE_PLANKS)
                        .define('H', Blocks.PALE_OAK_PLANKS)
                        .define('I', Blocks.CHERRY_PLANKS)
                        .pattern("ABC")
                        .pattern("DEF")
                        .pattern("GHI")
                        .unlockedBy(getHasName(Blocks.OAK_LOG), has(Blocks.OAK_LOG))
                        .unlockedBy(getHasName(Blocks.SPRUCE_LOG), has(Blocks.SPRUCE_LOG))
                        .unlockedBy(getHasName(Blocks.BIRCH_LOG), has(Blocks.BIRCH_LOG))
                        .unlockedBy(getHasName(Blocks.JUNGLE_LOG), has(Blocks.JUNGLE_LOG))
                        .unlockedBy(getHasName(Blocks.ACACIA_LOG), has(Blocks.ACACIA_LOG))
                        .unlockedBy(getHasName(Blocks.DARK_OAK_LOG), has(Blocks.DARK_OAK_LOG))
                        .unlockedBy(getHasName(Blocks.MANGROVE_LOG), has(Blocks.MANGROVE_LOG))
                        .unlockedBy(getHasName(Blocks.CHERRY_LOG), has(Blocks.CHERRY_LOG))
                        .unlockedBy(getHasName(Blocks.PALE_OAK_LOG), has(Blocks.PALE_OAK_LOG))
                        .group("compressed_wood")
                        .save(output);
                shaped(RecipeCategory.MISC, COMPRESSED_WOOD_LOG)
                        .define('A', Blocks.OAK_LOG)
                        .define('B', Blocks.SPRUCE_LOG)
                        .define('C', Blocks.JUNGLE_LOG)
                        .define('D', Blocks.BIRCH_LOG)
                        .define('E', Blocks.DARK_OAK_LOG)
                        .define('F', Blocks.ACACIA_LOG)
                        .define('G', Blocks.MANGROVE_LOG)
                        .define('H', Blocks.PALE_OAK_LOG)
                        .define('I', Blocks.CHERRY_LOG)
                        .pattern("ABC")
                        .pattern("DEF")
                        .pattern("GHI")
                        .unlockedBy(getHasName(Blocks.OAK_LOG), has(Blocks.OAK_LOG))
                        .unlockedBy(getHasName(Blocks.SPRUCE_LOG), has(Blocks.SPRUCE_LOG))
                        .unlockedBy(getHasName(Blocks.BIRCH_LOG), has(Blocks.BIRCH_LOG))
                        .unlockedBy(getHasName(Blocks.JUNGLE_LOG), has(Blocks.JUNGLE_LOG))
                        .unlockedBy(getHasName(Blocks.ACACIA_LOG), has(Blocks.ACACIA_LOG))
                        .unlockedBy(getHasName(Blocks.DARK_OAK_LOG), has(Blocks.DARK_OAK_LOG))
                        .unlockedBy(getHasName(Blocks.MANGROVE_LOG), has(Blocks.MANGROVE_LOG))
                        .unlockedBy(getHasName(Blocks.CHERRY_LOG), has(Blocks.CHERRY_LOG))
                        .unlockedBy(getHasName(Blocks.PALE_OAK_LOG), has(Blocks.PALE_OAK_LOG))
                        .group("compressed_wood")
                        .save(output);
                shaped(RecipeCategory.MISC, COMPRESSED_WOOD)
                        .define('A', Blocks.OAK_WOOD)
                        .define('B', Blocks.SPRUCE_WOOD)
                        .define('C', Blocks.JUNGLE_WOOD)
                        .define('D', Blocks.BIRCH_WOOD)
                        .define('E', Blocks.DARK_OAK_WOOD)
                        .define('F', Blocks.ACACIA_WOOD)
                        .define('G', Blocks.MANGROVE_WOOD)
                        .define('H', Blocks.PALE_OAK_WOOD)
                        .define('I', Blocks.CHERRY_WOOD)
                        .pattern("ABC")
                        .pattern("DEF")
                        .pattern("GHI")
                        .unlockedBy(getHasName(Blocks.OAK_LOG), has(Blocks.OAK_LOG))
                        .unlockedBy(getHasName(Blocks.SPRUCE_LOG), has(Blocks.SPRUCE_LOG))
                        .unlockedBy(getHasName(Blocks.BIRCH_LOG), has(Blocks.BIRCH_LOG))
                        .unlockedBy(getHasName(Blocks.JUNGLE_LOG), has(Blocks.JUNGLE_LOG))
                        .unlockedBy(getHasName(Blocks.ACACIA_LOG), has(Blocks.ACACIA_LOG))
                        .unlockedBy(getHasName(Blocks.DARK_OAK_LOG), has(Blocks.DARK_OAK_LOG))
                        .unlockedBy(getHasName(Blocks.MANGROVE_LOG), has(Blocks.MANGROVE_LOG))
                        .unlockedBy(getHasName(Blocks.CHERRY_LOG), has(Blocks.CHERRY_LOG))
                        .unlockedBy(getHasName(Blocks.PALE_OAK_LOG), has(Blocks.PALE_OAK_LOG))
                        .group("compressed_wood")
                        .save(output);
                shaped(RecipeCategory.MISC, COMPRESSED_WOOD)
                        .define('L', COMPRESSED_WOOD_LOG)
                        .pattern("LL")
                        .pattern("LL")
                        .unlockedBy(getHasName(Blocks.OAK_LOG), has(Blocks.OAK_LOG))
                        .unlockedBy(getHasName(Blocks.SPRUCE_LOG), has(Blocks.SPRUCE_LOG))
                        .unlockedBy(getHasName(Blocks.BIRCH_LOG), has(Blocks.BIRCH_LOG))
                        .unlockedBy(getHasName(Blocks.JUNGLE_LOG), has(Blocks.JUNGLE_LOG))
                        .unlockedBy(getHasName(Blocks.ACACIA_LOG), has(Blocks.ACACIA_LOG))
                        .unlockedBy(getHasName(Blocks.DARK_OAK_LOG), has(Blocks.DARK_OAK_LOG))
                        .unlockedBy(getHasName(Blocks.MANGROVE_LOG), has(Blocks.MANGROVE_LOG))
                        .unlockedBy(getHasName(Blocks.CHERRY_LOG), has(Blocks.CHERRY_LOG))
                        .unlockedBy(getHasName(Blocks.PALE_OAK_LOG), has(Blocks.PALE_OAK_LOG))
                        .group("compressed_wood")
                        .save(output, "compressed_wood_from_compressed_wood_logs");
                shapeless(RecipeCategory.MISC, COMPRESSED_WOOD_LOG, 4)
                        .requires(COMPRESSED_WOOD)
                        .unlockedBy(getHasName(Blocks.OAK_LOG), has(Blocks.OAK_LOG))
                        .unlockedBy(getHasName(Blocks.SPRUCE_LOG), has(Blocks.SPRUCE_LOG))
                        .unlockedBy(getHasName(Blocks.BIRCH_LOG), has(Blocks.BIRCH_LOG))
                        .unlockedBy(getHasName(Blocks.JUNGLE_LOG), has(Blocks.JUNGLE_LOG))
                        .unlockedBy(getHasName(Blocks.ACACIA_LOG), has(Blocks.ACACIA_LOG))
                        .unlockedBy(getHasName(Blocks.DARK_OAK_LOG), has(Blocks.DARK_OAK_LOG))
                        .unlockedBy(getHasName(Blocks.MANGROVE_LOG), has(Blocks.MANGROVE_LOG))
                        .unlockedBy(getHasName(Blocks.CHERRY_LOG), has(Blocks.CHERRY_LOG))
                        .unlockedBy(getHasName(Blocks.PALE_OAK_LOG), has(Blocks.PALE_OAK_LOG))
                        .group("compressed_wood")
                        .save(output, "compressed_wood_logs_from_compressed_wood");
                fenceBuilder(COMPRESSED_WOOD_FENCE, Ingredient.of(COMPRESSED_WOOD_PLANKS));
                fenceGateBuilder(COMPRESSED_WOOD_FENCE_GATE, Ingredient.of(COMPRESSED_WOOD_PLANKS));

                shaped(RecipeCategory.MISC, SUPER_CRAFTER_BLOCK)
                        .define('E', Blocks.ENCHANTING_TABLE)
                        .define('W', COMPRESSED_WOOD)
                        .define('F', COMPRESSED_WOOD_FENCE)
                        .define('A', STRONG_AMETHYST_INGOT)
                        .define('C', Blocks.CHEST)
                        .pattern("CE ")
                        .pattern("WWW")
                        .pattern("FAF")
                        .unlockedBy(getHasName(RAW_STRONG_AMETHYST), has(RAW_STRONG_AMETHYST))
                        .unlockedBy(getHasName(COMPRESSED_WOOD), has(COMPRESSED_WOOD))
                        .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                        .unlockedBy(getHasName(Blocks.OBSIDIAN), has(Blocks.OBSIDIAN))
                        .group("super_crafter")
                        .save(output);

                // #################################################################################################################
                // #                                        IRON MATERIAL RECIPES                                                  #
                // #################################################################################################################
                nineBlockStorageRecipes(RecipeCategory.MISC, STRONG_IRON_INGOT, RecipeCategory.BUILDING_BLOCKS, STRONG_IRON_BLOCK);
                shapeless(RecipeCategory.MISC, STRONG_IRON_INGOT, 9)
                        .requires(STRONG_IRON_BLOCK)
                        .unlockedBy(getHasName(STRONG_IRON_INGOT), has(STRONG_IRON_BLOCK))
                        .group("strong_iron")
                        .save(output, "strong_iron_ingot_from_strong_iron_block");

                // #################################################################################################################
                // #                                        EMERALD MATERIAL RECIPES                                               #
                // #################################################################################################################
                nineBlockStorageRecipes(RecipeCategory.MISC, RAW_STRONG_EMERALD, RecipeCategory.BUILDING_BLOCKS, RAW_STRONG_EMERALD_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.MISC, STRONG_EMERALD, RecipeCategory.BUILDING_BLOCKS, STRONG_EMERALD_BLOCK);
                shapeless(RecipeCategory.MISC, RAW_STRONG_EMERALD, 9)
                        .requires(RAW_STRONG_EMERALD_BLOCK)
                        .unlockedBy(getHasName(RAW_STRONG_EMERALD), has(RAW_STRONG_EMERALD))
                        .group("emerald")
                        .save(output, "raw_strong_emerald_from_raw_strong_emerald_block");
                shapeless(RecipeCategory.MISC, STRONG_EMERALD, 9)
                        .requires(STRONG_EMERALD_BLOCK)
                        .unlockedBy(getHasName(RAW_STRONG_EMERALD), has(RAW_STRONG_EMERALD))
                        .group("emerald")
                        .save(output, "strong_emerald_from_strong_emerald_block");
                this.axeFromIngot(EMERALD_AXE, Items.EMERALD, Items.EMERALD, "emerald");
                this.hoeFromIngot(EMERALD_HOE, Items.EMERALD, Items.EMERALD, "emerald");
                this.pickaxeFromIngot(EMERALD_PICKAXE, Items.EMERALD, Items.EMERALD, "emerald");
                this.shovelFromIngot(EMERALD_SHOVEL, Items.EMERALD, Items.EMERALD, "emerald");
                this.spearFromIngot(EMERALD_SPEAR, Items.EMERALD, Items.EMERALD, "emerald");
                this.swordFromIngot(EMERALD_SWORD, Items.EMERALD, Items.EMERALD, "emerald");
                this.helmetFromIngot(EMERALD_HELMET, Items.EMERALD, Items.EMERALD, "emerald");
                this.chestplateFromIngot(EMERALD_CHESTPLATE, Items.EMERALD, Items.EMERALD, "emerald");
                this.leggingsFromIngot(EMERALD_LEGGINGS, Items.EMERALD, Items.EMERALD, "emerald");
                this.bootsFromIngot(EMERALD_BOOTS, Items.EMERALD, Items.EMERALD, "emerald");

                // #################################################################################################################
                // #                                        TOPAZ MATERIAL RECIPES                                                 #
                // #################################################################################################################
                List<ItemLike> TOPAZ_SMELTABLE = List.of(STONE_TOPAZ_ORE, DEEPSLATE_TOPAZ_ORE);
                oreSmelting(TOPAZ_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, RAW_STRONG_TOPAZ, 0.25f, 200, "topaz");
                oreBlasting(TOPAZ_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, RAW_STRONG_TOPAZ, 0.25f, 100, "topaz");
                nineBlockStorageRecipes(RecipeCategory.MISC, RAW_STRONG_TOPAZ, RecipeCategory.BUILDING_BLOCKS, RAW_STRONG_TOPAZ_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.MISC, STRONG_TOPAZ_INGOT, RecipeCategory.BUILDING_BLOCKS, STRONG_TOPAZ_BLOCK);
                shapeless(RecipeCategory.MISC, RAW_STRONG_TOPAZ, 9)
                        .requires(RAW_STRONG_TOPAZ_BLOCK)
                        .unlockedBy(getHasName(RAW_STRONG_TOPAZ), has(RAW_STRONG_TOPAZ))
                        .group("topaz")
                        .save(output, "raw_strong_topaz_from_raw_strong_topaz_block");
                shapeless(RecipeCategory.MISC, STRONG_TOPAZ, 9)
                        .requires(STRONG_TOPAZ_BLOCK)
                        .unlockedBy(getHasName(RAW_STRONG_TOPAZ), has(RAW_STRONG_TOPAZ))
                        .group("topaz")
                        .save(output, "strong_topaz_from_strong_topaz_block");
                this.axeFromIngot(STRONG_TOPAZ_AXE, STRONG_TOPAZ_INGOT, RAW_STRONG_TOPAZ, "topaz");
                this.hoeFromIngot(STRONG_TOPAZ_HOE, STRONG_TOPAZ_INGOT, RAW_STRONG_TOPAZ, "topaz");
                this.pickaxeFromIngot(STRONG_TOPAZ_PICKAXE, STRONG_TOPAZ_INGOT, RAW_STRONG_TOPAZ, "topaz");
                this.shovelFromIngot(STRONG_TOPAZ_SHOVEL, STRONG_TOPAZ_INGOT, RAW_STRONG_TOPAZ, "topaz");
                this.spearFromIngot(STRONG_TOPAZ_SPEAR, STRONG_TOPAZ_INGOT, RAW_STRONG_TOPAZ, "topaz");
                this.swordFromIngot(STRONG_TOPAZ_SWORD, STRONG_TOPAZ_INGOT, RAW_STRONG_TOPAZ, "topaz");
                this.helmetFromIngot(STRONG_TOPAZ_HELMET, RAW_STRONG_TOPAZ, RAW_STRONG_TOPAZ, "topaz");
                this.chestplateFromIngot(STRONG_TOPAZ_CHESTPLATE, RAW_STRONG_TOPAZ, RAW_STRONG_TOPAZ, "topaz");
                this.leggingsFromIngot(STRONG_TOPAZ_LEGGINGS, RAW_STRONG_TOPAZ, RAW_STRONG_TOPAZ, "topaz");
                this.bootsFromIngot(STRONG_TOPAZ_BOOTS, RAW_STRONG_TOPAZ, RAW_STRONG_TOPAZ, "topaz");

                // #################################################################################################################
                // #                                           RUBY MATERIAL RECIPES                                               #
                // #################################################################################################################
                List<ItemLike> RUBY_SMELTABLE = List.of(STONE_RUBY_ORE, DEEPSLATE_RUBY_ORE);
                oreSmelting(RUBY_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, RAW_STRONG_RUBY, 0.25f, 200, "topaz");
                oreBlasting(RUBY_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, RAW_STRONG_RUBY, 0.25f, 100, "topaz");
                nineBlockStorageRecipes(RecipeCategory.MISC, RAW_STRONG_RUBY, RecipeCategory.BUILDING_BLOCKS, RAW_STRONG_RUBY_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.MISC, STRONG_RUBY_INGOT, RecipeCategory.BUILDING_BLOCKS, STRONG_RUBY_BLOCK);
                shapeless(RecipeCategory.MISC, RAW_STRONG_RUBY, 9)
                        .requires(RAW_STRONG_RUBY_BLOCK)
                        .unlockedBy(getHasName(RAW_STRONG_RUBY), has(RAW_STRONG_RUBY))
                        .group("ruby")
                        .save(output, "raw_strong_ruby_from_strong_raw_ruby_block");
                shapeless(RecipeCategory.MISC, STRONG_RUBY_INGOT, 9)
                        .requires(STRONG_RUBY_BLOCK)
                        .unlockedBy(getHasName(RAW_STRONG_RUBY), has(RAW_STRONG_RUBY))
                        .group("ruby")
                        .save(output, "strong_ruby_ingot_from_strong_raw_ruby_block");
                this.axeFromIngot(STRONG_RUBY_AXE, STRONG_RUBY_INGOT, RAW_STRONG_RUBY, "ruby");
                this.hoeFromIngot(STRONG_RUBY_HOE, STRONG_RUBY_INGOT, RAW_STRONG_RUBY, "ruby");
                this.pickaxeFromIngot(STRONG_RUBY_PICKAXE, STRONG_RUBY_INGOT, RAW_STRONG_RUBY, "ruby");
                this.shovelFromIngot(STRONG_RUBY_SHOVEL, STRONG_RUBY_INGOT, RAW_STRONG_RUBY, "ruby");
                this.spearFromIngot(STRONG_RUBY_SPEAR, STRONG_RUBY_INGOT, RAW_STRONG_RUBY, "ruby");
                this.swordFromIngot(STRONG_RUBY_SWORD, STRONG_RUBY_INGOT, RAW_STRONG_RUBY, "ruby");
                this.helmetFromIngot(STRONG_RUBY_HELMET, RAW_STRONG_RUBY, RAW_STRONG_RUBY, "ruby");
                this.chestplateFromIngot(STRONG_RUBY_CHESTPLATE, RAW_STRONG_RUBY, RAW_STRONG_RUBY, "ruby");
                this.leggingsFromIngot(STRONG_RUBY_LEGGINGS, RAW_STRONG_RUBY, RAW_STRONG_RUBY, "ruby");
                this.bootsFromIngot(STRONG_RUBY_BOOTS, RAW_STRONG_RUBY, RAW_STRONG_RUBY, "ruby");

                // #################################################################################################################
                // #                                        AMETHYST MATERIAL RECIPES                                              #
                // #################################################################################################################
                List<ItemLike> AMETHYST_SMELTABLE = List.of(STONE_AMETHYST_ORE, DEEPSLATE_AMETHYST_ORE);
                // smelting amethyst ores -> raw amethyst
                oreSmelting(AMETHYST_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, RAW_STRONG_AMETHYST, 0.5f, 300, "amethyst");
                oreBlasting(AMETHYST_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, RAW_STRONG_AMETHYST, 0.5f, 150, "amethyst");
                // 9 x RAW_AMETHYST => RAW_AMETHYST_BLOCK
                // The first ItemLike parameter is the ingredient, and the second ItemLike parameter is the crafted item.
                nineBlockStorageRecipes(RecipeCategory.MISC, RAW_STRONG_AMETHYST, RecipeCategory.BUILDING_BLOCKS, RAW_STRONG_AMETHYST_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.MISC, STRONG_AMETHYST_INGOT, RecipeCategory.BUILDING_BLOCKS, STRONG_AMETHYST_BLOCK);
                shapeless(RecipeCategory.MISC, RAW_STRONG_AMETHYST, 9)
                        .requires(RAW_STRONG_AMETHYST_BLOCK)
                        .unlockedBy(getHasName(RAW_STRONG_AMETHYST), has(RAW_STRONG_AMETHYST))
                        .group("amethyst")
                        .save(output, "raw_amethyst_from_raw_amethyst_block");
                shapeless(RecipeCategory.MISC, STRONG_AMETHYST_INGOT, 9)
                        .requires(STRONG_AMETHYST_BLOCK)
                        .unlockedBy(getHasName(RAW_STRONG_AMETHYST), has(RAW_STRONG_AMETHYST))
                        .group("amethyst")
                        .save(output, "strong_amethyst_ingot_from_strong_amethyst_block");
                this.axeFromIngot(STRONG_AMETHYST_AXE, STRONG_AMETHYST_INGOT, RAW_STRONG_AMETHYST, "amethyst");
                this.hoeFromIngot(STRONG_AMETHYST_HOE, STRONG_AMETHYST_INGOT, RAW_STRONG_AMETHYST, "amethyst");
                this.pickaxeFromIngot(STRONG_AMETHYST_PICKAXE, STRONG_AMETHYST_INGOT, RAW_STRONG_AMETHYST, "amethyst");
                this.shovelFromIngot(STRONG_AMETHYST_SHOVEL, STRONG_AMETHYST_INGOT, RAW_STRONG_AMETHYST, "amethyst");
                this.spearFromIngot(STRONG_AMETHYST_SPEAR, STRONG_AMETHYST_INGOT, RAW_STRONG_AMETHYST, "amethyst");
                this.swordFromIngot(STRONG_AMETHYST_SWORD, STRONG_AMETHYST_INGOT, RAW_STRONG_AMETHYST, "amethyst");
                this.helmetFromIngot(STRONG_AMETHYST_HELMET, RAW_STRONG_AMETHYST, RAW_STRONG_AMETHYST, "amethyst");
                this.chestplateFromIngot(STRONG_AMETHYST_CHESTPLATE, RAW_STRONG_AMETHYST, RAW_STRONG_AMETHYST, "amethyst");
                this.leggingsFromIngot(STRONG_AMETHYST_LEGGINGS, RAW_STRONG_AMETHYST, RAW_STRONG_AMETHYST, "amethyst");
                this.bootsFromIngot(STRONG_AMETHYST_BOOTS, RAW_STRONG_AMETHYST, RAW_STRONG_AMETHYST, "amethyst");
                fenceBuilder(STRONG_AMETHYST_FENCE, Ingredient.of(STRONG_AMETHYST_INGOT))
                        .unlockedBy(getHasName(RAW_STRONG_AMETHYST), has(RAW_STRONG_AMETHYST))
                        .group("amethyst")
                        .save(output);
                fenceGateBuilder(STRONG_AMETHYST_FENCE_GATE, Ingredient.of(STRONG_AMETHYST_INGOT))
                        .unlockedBy(getHasName(RAW_STRONG_AMETHYST), has(RAW_STRONG_AMETHYST))
                        .group("amethyst")
                        .save(output);
                wall(RecipeCategory.BUILDING_BLOCKS, STRONG_AMETHYST_WALL, STRONG_AMETHYST_INGOT);
                wall(RecipeCategory.BUILDING_BLOCKS, RAW_STRONG_AMETHYST_WALL, RAW_STRONG_AMETHYST);
            }

            private void axeFromIngot(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.TOOLS, crafted)
                        .define('#', Items.STICK)
                        .define('X', ingot)
                        .pattern("XX")
                        .pattern("X#")
                        .pattern(" #")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(output);
            }

            private void hoeFromIngot(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.TOOLS, crafted)
                        .define('#', Items.STICK)
                        .define('X', ingot)
                        .pattern("XX")
                        .pattern(" #")
                        .pattern(" #")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(output);
            }

            private void pickaxeFromIngot(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.TOOLS, crafted)
                        .define('#', Items.STICK)
                        .define('X', ingot)
                        .pattern("XXX")
                        .pattern(" # ")
                        .pattern(" # ")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(output);
            }

            private void shovelFromIngot(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.TOOLS, crafted)
                        .define('#', Items.STICK)
                        .define('X', ingot)
                        .pattern("X")
                        .pattern("#")
                        .pattern("#")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(output);
            }

            private void spearFromIngot(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.COMBAT, crafted)
                        .define('#', Items.STICK)
                        .define('X', ingot)
                        .pattern("  X")
                        .pattern(" # ")
                        .pattern("#  ")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(output);
            }

            private void swordFromIngot(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.COMBAT, crafted)
                        .define('#', Items.STICK)
                        .define('X', ingot)
                        .pattern("X")
                        .pattern("X")
                        .pattern("#")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(output);
            }

            private void helmetFromIngot(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.COMBAT, crafted)
                        .define('I', ingot)
                        .pattern("III")
                        .pattern("I I")
                        .pattern("   ")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(output);
            }

            private void chestplateFromIngot(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.COMBAT, crafted)
                        .define('I', ingot)
                        .pattern("I I")
                        .pattern("III")
                        .pattern("III")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(output);
            }

            private void leggingsFromIngot(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.COMBAT, crafted)
                        .define('I', ingot)
                        .pattern("III")
                        .pattern("I I")
                        .pattern("I I")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(output);
            }

            private void bootsFromIngot(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.COMBAT, crafted)
                        .define('I', ingot)
                        .pattern("   ")
                        .pattern("I I")
                        .pattern("I I")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(output);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "Mod Recipe Provider";
    }
}
