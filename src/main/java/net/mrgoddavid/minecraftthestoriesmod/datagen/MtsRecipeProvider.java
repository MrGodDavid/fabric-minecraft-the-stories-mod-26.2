package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.datagen.recipe.content.EnricherRecipeBuilder;
import net.mrgoddavid.minecraftthestoriesmod.datagen.recipe.content.OreCompressorRecipeBuilder;
import net.mrgoddavid.minecraftthestoriesmod.datagen.recipe.content.SqueezerRecipeBuilder;
import net.mrgoddavid.minecraftthestoriesmod.datagen.recipe.content.SuperCrafterRecipeBuilder;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.squeezer.SqueezerRecipe;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

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
        MinecraftTheStoriesMod.LOGGER.info("Providing data of MTS Item/Block Recipe for: " + MinecraftTheStoriesMod.MOD_ID);
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

            private final RecipeOutput vanillaRecipeOutput = new RecipeOutput() {
                @Override
                public void accept(ResourceKey<Recipe<?>> key, Recipe<?> recipe, @Nullable AdvancementHolder advancement) {
                    output.accept(key, recipe, advancement);
                }

                @Override
                public Advancement.Builder advancement() {
                    return output.advancement();
                }

                @Override
                public void includeRootAdvancement() {
                    output.includeRootAdvancement();
                }

                @Override
                public Identifier getRecipeIdentifier(Identifier recipeId) {
                    return recipeId;
                }
            };

            /**
             * Access widened by fabric-data-generation-api-v1 to accessible
             */
            @Override
            public void buildRecipes() {

                // #################################################################################################################
                // #                                CUSTOM STICKS, FENCE, & FENCE GATE RECIPES                                     #
                // #################################################################################################################
                this.mtsFenceBuilder(Items.ACACIA_FENCE, Items.ACACIA_PLANKS, ACACIA_STICK, Items.ACACIA_PLANKS, ACACIA_STICK, "acacia_fence");
                this.mtsFenceBuilder(Items.BIRCH_FENCE, Items.BIRCH_PLANKS, BIRCH_STICK, Items.BIRCH_PLANKS, BIRCH_STICK, "birch_fence");
                this.mtsFenceBuilder(Items.CHERRY_FENCE, Items.CHERRY_PLANKS, CHERRY_STICK, Items.CHERRY_PLANKS, CHERRY_STICK, "cherry_fence");
                this.mtsFenceBuilder(Items.DARK_OAK_FENCE, Items.DARK_OAK_PLANKS, DARK_OAK_STICK, Items.DARK_OAK_PLANKS, DARK_OAK_STICK, "dark_oak_fence");
                this.mtsFenceBuilder(Items.JUNGLE_FENCE, Items.JUNGLE_PLANKS, JUNGLE_STICK, Items.JUNGLE_PLANKS, JUNGLE_STICK, "jungle_fence");
                this.mtsFenceBuilder(Items.MANGROVE_FENCE, Items.MANGROVE_PLANKS, MANGROVE_STICK, Items.MANGROVE_PLANKS, MANGROVE_STICK, "mangrove_fence");
                this.mtsFenceBuilder(Items.OAK_FENCE, Items.OAK_FENCE, Items.STICK, Items.OAK_FENCE, Items.STICK, "oak_fence");
                this.mtsFenceBuilder(Items.PALE_OAK_FENCE, Items.PALE_OAK_PLANKS, PALE_OAK_STICK, Items.PALE_OAK_PLANKS, PALE_OAK_STICK, "pale_oak_fence");
                this.mtsFenceBuilder(Items.SPRUCE_FENCE, Items.SPRUCE_PLANKS, SPRUCE_STICK, Items.SPRUCE_PLANKS, SPRUCE_STICK, "spruce_fence");

                this.mtsFenceGateBuilder(Items.ACACIA_FENCE_GATE, Items.ACACIA_PLANKS, ACACIA_STICK, Items.ACACIA_PLANKS, ACACIA_STICK, "acacia_fence_gate");
                this.mtsFenceGateBuilder(Items.BIRCH_FENCE_GATE, Items.BIRCH_PLANKS, BIRCH_STICK, Items.BIRCH_PLANKS, BIRCH_STICK, "birch_fence_gate");
                this.mtsFenceGateBuilder(Items.CHERRY_FENCE_GATE, Items.CHERRY_PLANKS, CHERRY_STICK, Items.CHERRY_PLANKS, CHERRY_STICK, "cherry_fence_gate");
                this.mtsFenceGateBuilder(Items.DARK_OAK_FENCE_GATE, Items.DARK_OAK_PLANKS, DARK_OAK_STICK, Items.DARK_OAK_PLANKS, DARK_OAK_STICK, "dark_oak_fence_gate");
                this.mtsFenceGateBuilder(Items.JUNGLE_FENCE_GATE, Items.JUNGLE_PLANKS, JUNGLE_STICK, Items.JUNGLE_PLANKS, JUNGLE_STICK, "jungle_fence_gate");
                this.mtsFenceGateBuilder(Items.MANGROVE_FENCE_GATE, Items.MANGROVE_PLANKS, MANGROVE_STICK, Items.MANGROVE_PLANKS, MANGROVE_STICK, "mangrove_fence_gate");
                this.mtsFenceGateBuilder(Items.OAK_FENCE_GATE, Items.OAK_PLANKS, Items.STICK, Items.OAK_PLANKS, Items.STICK, "oak_fence_gate");
                this.mtsFenceGateBuilder(Items.PALE_OAK_FENCE_GATE, Items.PALE_OAK_PLANKS, PALE_OAK_STICK, Items.PALE_OAK_PLANKS, PALE_OAK_STICK, "pale_oak_fence_gate");
                this.mtsFenceGateBuilder(Items.SPRUCE_FENCE_GATE, Items.SPRUCE_PLANKS, SPRUCE_STICK, Items.SPRUCE_PLANKS, SPRUCE_STICK, "spruce_fence_gate");

                this.mtsStickBuilder(ACACIA_STICK, Items.ACACIA_PLANKS, Items.ACACIA_PLANKS, "acacia_stick");
                this.mtsStickBuilder(BIRCH_STICK, Items.BIRCH_PLANKS, Items.BIRCH_PLANKS, "birch_stick");
                this.mtsStickBuilder(CHERRY_STICK, Items.CHERRY_PLANKS, Items.CHERRY_PLANKS, "cherry_stick");
                this.mtsStickBuilder(DARK_OAK_STICK, Items.DARK_OAK_PLANKS, Items.DARK_OAK_PLANKS, "dark_oak_stick");
                this.mtsStickBuilder(JUNGLE_STICK, Items.JUNGLE_PLANKS, Items.JUNGLE_PLANKS, "jungle_stick");
                this.mtsStickBuilder(MANGROVE_STICK, Items.MANGROVE_PLANKS, Items.MANGROVE_PLANKS, "mangrove_stick");
                this.mtsStickBuilder(Items.STICK, Items.OAK_PLANKS, Items.OAK_PLANKS, "stick");
                this.mtsStickBuilder(PALE_OAK_STICK, Items.PALE_OAK_PLANKS, Items.PALE_OAK_PLANKS, "pale_oak_stick");
                this.mtsStickBuilder(SPRUCE_STICK, Items.SPRUCE_PLANKS, Items.SPRUCE_PLANKS, "spruce_stick");

                this.mtsSignBuilder(Items.ACACIA_SIGN, Items.ACACIA_PLANKS, ACACIA_STICK, Items.ACACIA_PLANKS, ACACIA_STICK, "acacia_sign");
                this.mtsSignBuilder(Items.BIRCH_SIGN, Items.BIRCH_PLANKS, BIRCH_STICK, Items.BIRCH_PLANKS, BIRCH_STICK, "birch_sign");
                this.mtsSignBuilder(Items.CHERRY_SIGN, Items.CHERRY_PLANKS, CHERRY_STICK, Items.CHERRY_PLANKS, CHERRY_STICK, "cherry_sign");
                this.mtsSignBuilder(Items.DARK_OAK_SIGN, Items.DARK_OAK_PLANKS, DARK_OAK_STICK, Items.DARK_OAK_PLANKS, DARK_OAK_STICK, "dark_oak_sign");
                this.mtsSignBuilder(Items.JUNGLE_SIGN, Items.JUNGLE_PLANKS, JUNGLE_STICK, Items.JUNGLE_PLANKS, JUNGLE_STICK, "jungle_sign");
                this.mtsSignBuilder(Items.MANGROVE_SIGN, Items.MANGROVE_PLANKS, MANGROVE_STICK, Items.MANGROVE_PLANKS, MANGROVE_STICK, "mangrove_sign");
                this.mtsSignBuilder(Items.OAK_SIGN, Items.OAK_PLANKS, Items.STICK, Items.OAK_PLANKS, Items.STICK, "oak_sign");
                this.mtsSignBuilder(Items.PALE_OAK_SIGN, Items.PALE_OAK_PLANKS, PALE_OAK_STICK, Items.PALE_OAK_PLANKS, PALE_OAK_STICK, "pale_oak_sign");
                this.mtsSignBuilder(Items.SPRUCE_SIGN, Items.SPRUCE_PLANKS, SPRUCE_STICK, Items.SPRUCE_PLANKS, SPRUCE_STICK, "spruce_sign");

                // TODO more wooden weapon variants.
                this.overrideVanillaAxe(Items.STONE_AXE, Items.COBBLESTONE, Items.COBBLESTONE, "stone_axe");
                this.overrideVanillaAxe(Items.IRON_AXE, Items.IRON_INGOT, Items.IRON_INGOT, "stone_axe");
                this.overrideVanillaAxe(Items.GOLDEN_AXE, Items.GOLD_INGOT, Items.GOLD_INGOT, "golden_axe");
                this.overrideVanillaAxe(Items.COPPER_AXE, Items.COPPER_INGOT, Items.COPPER_INGOT, "copper_axe");
                this.overrideVanillaAxe(Items.DIAMOND_AXE, Items.DIAMOND, Items.DIAMOND, "diamond_axe");

                this.overrideVanillaHoe(Items.STONE_HOE, Items.COBBLESTONE, Items.COBBLESTONE, "stone_hoe");
                this.overrideVanillaHoe(Items.IRON_HOE, Items.IRON_INGOT, Items.IRON_INGOT, "stone_hoe");
                this.overrideVanillaHoe(Items.GOLDEN_HOE, Items.GOLD_INGOT, Items.GOLD_INGOT, "golden_hoe");
                this.overrideVanillaHoe(Items.COPPER_HOE, Items.COPPER_INGOT, Items.COPPER_INGOT, "copper_hoe");
                this.overrideVanillaHoe(Items.DIAMOND_HOE, Items.DIAMOND, Items.DIAMOND, "diamond_hoe");

                this.overrideVanillaPickaxe(Items.STONE_PICKAXE, Items.COBBLESTONE, Items.COBBLESTONE, "stone_pickaxe");
                this.overrideVanillaPickaxe(Items.IRON_PICKAXE, Items.IRON_INGOT, Items.IRON_INGOT, "stone_pickaxe");
                this.overrideVanillaPickaxe(Items.GOLDEN_PICKAXE, Items.GOLD_INGOT, Items.GOLD_INGOT, "golden_pickaxe");
                this.overrideVanillaPickaxe(Items.COPPER_PICKAXE, Items.COPPER_INGOT, Items.COPPER_INGOT, "copper_pickaxe");
                this.overrideVanillaPickaxe(Items.DIAMOND_PICKAXE, Items.DIAMOND, Items.DIAMOND, "diamond_pickaxe");

                this.overrideVanillaShovel(Items.STONE_SHOVEL, Items.COBBLESTONE, Items.COBBLESTONE, "stone_shovel");
                this.overrideVanillaShovel(Items.IRON_SHOVEL, Items.IRON_INGOT, Items.IRON_INGOT, "stone_shovel");
                this.overrideVanillaShovel(Items.GOLDEN_SHOVEL, Items.GOLD_INGOT, Items.GOLD_INGOT, "golden_shovel");
                this.overrideVanillaShovel(Items.COPPER_SHOVEL, Items.COPPER_INGOT, Items.COPPER_INGOT, "copper_shovel");
                this.overrideVanillaShovel(Items.DIAMOND_SHOVEL, Items.DIAMOND, Items.DIAMOND, "diamond_shovel");

                this.overrideVanillaSpear(Items.STONE_SPEAR, Items.COBBLESTONE, Items.COBBLESTONE, "stone_spear");
                this.overrideVanillaSpear(Items.IRON_SPEAR, Items.IRON_INGOT, Items.IRON_INGOT, "stone_spear");
                this.overrideVanillaSpear(Items.GOLDEN_SPEAR, Items.GOLD_INGOT, Items.GOLD_INGOT, "golden_spear");
                this.overrideVanillaSpear(Items.COPPER_SPEAR, Items.COPPER_INGOT, Items.COPPER_INGOT, "copper_spear");
                this.overrideVanillaSpear(Items.DIAMOND_SPEAR, Items.DIAMOND, Items.DIAMOND, "diamond_spear");

                this.overrideVanillaSword(Items.STONE_SWORD, Items.COBBLESTONE, Items.COBBLESTONE, "stone_sword");
                this.overrideVanillaSword(Items.IRON_SWORD, Items.IRON_INGOT, Items.IRON_INGOT, "stone_sword");
                this.overrideVanillaSword(Items.GOLDEN_SWORD, Items.GOLD_INGOT, Items.GOLD_INGOT, "golden_sword");
                this.overrideVanillaSword(Items.COPPER_SWORD, Items.COPPER_INGOT, Items.COPPER_INGOT, "copper_sword");
                this.overrideVanillaSword(Items.DIAMOND_SWORD, Items.DIAMOND, Items.DIAMOND, "diamond_sword");

                // #################################################################################################################
                // #                                                BOW RECIPES                                                    #
                // #################################################################################################################
                this.mtsBowBuilder(ACACIA_BOW, ACACIA_STICK, Items.ACACIA_PLANKS, "acacia_bow");
                this.mtsBowBuilder(BIRCH_BOW, BIRCH_STICK, Items.BIRCH_PLANKS, "birch_bow");
                this.mtsBowBuilder(CHERRY_BOW, CHERRY_STICK, Items.CHERRY_PLANKS, "cherry_bow");
                this.mtsBowBuilder(DARK_OAK_BOW, DARK_OAK_STICK, Items.DARK_OAK_PLANKS, "dark_oak_bow");
                this.mtsBowBuilder(JUNGLE_BOW, JUNGLE_STICK, Items.JUNGLE_PLANKS, "jungle_bow");
                this.mtsBowBuilder(MANGROVE_BOW, MANGROVE_STICK, Items.MANGROVE_PLANKS, "mangrove_bow");
                this.mtsBowBuilder(Items.BOW, Items.STICK, Items.OAK_PLANKS, "bow");
                this.mtsBowBuilder(PALE_OAK_BOW, PALE_OAK_STICK, Items.PALE_OAK_PLANKS, "pale_oak_bow");
                this.mtsBowBuilder(SPRUCE_BOW, SPRUCE_STICK, Items.SPRUCE_PLANKS, "spruce_bow");

                // #################################################################################################################
                // #                                                AXE RECIPES                                                    #
                // #################################################################################################################
                this.woodenAxeFromPlanks(ACACIA_AXE, Items.ACACIA_PLANKS, ACACIA_STICK, Blocks.ACACIA_LOG, "acacia_axe");
                this.woodenAxeFromPlanks(BIRCH_AXE, Items.BIRCH_PLANKS, BIRCH_STICK, Blocks.BIRCH_LOG, "birch_axe");
                this.woodenAxeFromPlanks(CHERRY_AXE, Items.CHERRY_PLANKS, CHERRY_STICK, Blocks.CHERRY_LOG, "cherry_axe");
                this.woodenAxeFromPlanks(DARK_OAK_AXE, Items.DARK_OAK_PLANKS, DARK_OAK_STICK, Blocks.DARK_OAK_LOG, "dark_oak_axe");
                this.woodenAxeFromPlanks(JUNGLE_AXE, Items.JUNGLE_PLANKS, JUNGLE_STICK, Blocks.JUNGLE_LOG, "jungle_axe");
                this.woodenAxeFromPlanks(MANGROVE_AXE, Items.MANGROVE_PLANKS, MANGROVE_STICK, Blocks.MANGROVE_LOG, "mangrove_axe");
                this.woodenAxeFromPlanks(PALE_OAK_AXE, Items.PALE_OAK_PLANKS, PALE_OAK_STICK, Blocks.PALE_OAK_LOG, "pale_oak_axe");
                this.woodenAxeFromPlanks(SPRUCE_AXE, Items.SPRUCE_PLANKS, SPRUCE_STICK, Blocks.SPRUCE_LOG, "spruce_axe");

                // #################################################################################################################
                // #                                              ENRICHER RECIPES                                                 #
                // #################################################################################################################
                shaped(RecipeCategory.MISC, ENRICHER)
                        .define('B', Items.BRICK_SLAB)
                        .define('I', Blocks.BLACKSTONE)
                        .define('C', Items.COPPER_INGOT)
                        .define('F', Blocks.BLAST_FURNACE)
                        .unlockedBy(getHasName(Items.BRICK_SLAB), has(Items.BRICK_SLAB))
                        .unlockedBy(getHasName(STRONG_IRON_BLOCK), has(STRONG_IRON_BLOCK))
                        .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                        .unlockedBy(getHasName(Items.BLAST_FURNACE), has(Items.BLAST_FURNACE))
                        .pattern("III")
                        .pattern("CFC")
                        .pattern("BBB")
                        .group("enricher")
                        .save(output, Constants.modRecipe("enricher"));
                EnricherRecipeBuilder.enricherRecipe(RecipeCategory.MISC, Ingredient.of(Items.RAW_GOLD), Ingredient.of(Items.COAL), STRONG_GOLD, 2, 1)
                        .unlockedBy(getHasName(RAW_STRONG_TOPAZ), has(RAW_STRONG_TOPAZ))
                        .save(output, Constants.modRecipe("strong_gold_from_enriching"));
                EnricherRecipeBuilder.enricherRecipe(RecipeCategory.MISC, Ingredient.of(Items.RAW_IRON), Ingredient.of(Items.COAL), STRONG_IRON, 5, 1)
                        .unlockedBy(getHasName(RAW_STRONG_TOPAZ), has(RAW_STRONG_TOPAZ))
                        .save(output, Constants.modRecipe("strong_iron_from_enriching"));
                EnricherRecipeBuilder.enricherRecipe(RecipeCategory.MISC, Ingredient.of(RAW_STRONG_EMERALD), Ingredient.of(Items.COAL), STRONG_EMERALD, 10, 1)
                        .unlockedBy(getHasName(RAW_STRONG_TOPAZ), has(RAW_STRONG_TOPAZ))
                        .save(output, Constants.modRecipe("strong_emerald_from_enriching"));
                EnricherRecipeBuilder.enricherRecipe(RecipeCategory.MISC, Ingredient.of(RAW_STRONG_DIAMOND), Ingredient.of(Items.COAL), STRONG_DIAMOND, 15, 1)
                        .unlockedBy(getHasName(RAW_STRONG_TOPAZ), has(RAW_STRONG_TOPAZ))
                        .save(output, Constants.modRecipe("strong_diamond_from_enriching"));
                EnricherRecipeBuilder.enricherRecipe(RecipeCategory.MISC, Ingredient.of(RAW_STRONG_TOPAZ), Ingredient.of(Items.COAL), STRONG_TOPAZ, 20, 1)
                        .unlockedBy(getHasName(RAW_STRONG_TOPAZ), has(RAW_STRONG_TOPAZ))
                        .save(output, Constants.modRecipe("strong_topaz_from_enriching"));
                EnricherRecipeBuilder.enricherRecipe(RecipeCategory.MISC, Ingredient.of(RAW_STRONG_RUBY), Ingredient.of(Items.COAL), STRONG_RUBY, 25, 1)
                        .unlockedBy(getHasName(RAW_STRONG_TOPAZ), has(RAW_STRONG_TOPAZ))
                        .save(output, Constants.modRecipe("strong_ruby_from_enriching"));
                EnricherRecipeBuilder.enricherRecipe(RecipeCategory.MISC, Ingredient.of(RAW_STRONG_AMETHYST), Ingredient.of(Items.COAL), STRONG_AMETHYST, 35, 1)
                        .unlockedBy(getHasName(RAW_STRONG_TOPAZ), has(RAW_STRONG_TOPAZ))
                        .save(output, Constants.modRecipe("strong_amethyst_from_enriching"));

                // #################################################################################################################
                // #                                        ORE COMPRESSOR RECIPES                                                 #
                // #################################################################################################################
                OreCompressorRecipeBuilder.oreCompressorRecipe(RecipeCategory.MISC, Ingredient.of(STRONG_IRON), Ingredient.of(Items.COAL), STRONG_IRON_INGOT)
                        .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON))
                        .save(output, MinecraftTheStoriesMod.MOD_ID + ":strong_iron_ingot_from_ore_compressing");

                // #################################################################################################################
                // #                                         SUPER CRAFTER RECIPES                                                 #
                // #################################################################################################################
                SuperCrafterRecipeBuilder.superCrafterRecipe(RecipeCategory.MISC, Ingredient.of(RARE_UPGRADE_SCROLL_STAGE_ONE), Ingredient.of(HAMMER_OF_CRAFTER), Ingredient.of(STRONG_IRON_INGOT), STRONG_IRON_PRESSED_PLATE, 10)
                        .unlockedBy(getHasName(HAMMER_OF_CRAFTER), has(HAMMER_OF_CRAFTER))
                        .save(output, Constants.modRecipe("strong_iron_pressed_plate_from_super_crafting"));
                SuperCrafterRecipeBuilder.superCrafterRecipe(RecipeCategory.MISC, Ingredient.of(RARE_UPGRADE_SCROLL_STAGE_TWO), Ingredient.of(HAMMER_OF_CRAFTER), Ingredient.of(STRONG_IRON_PRESSED_PLATE), STRONG_IRON_ZEN_STAFF_STAGE_TWO, 30)
                        .unlockedBy(getHasName(HAMMER_OF_CRAFTER), has(HAMMER_OF_CRAFTER))
                        .save(output, Constants.modRecipe("strong_iron_zen_staff_stage_two_from_super_crafting"));
                SuperCrafterRecipeBuilder.superCrafterRecipe(RecipeCategory.MISC, Ingredient.of(RARE_UPGRADE_SCROLL_STAGE_THREE), Ingredient.of(HAMMER_OF_CRAFTER), Ingredient.of(STRONG_IRON_ZEN_STAFF_STAGE_TWO), STRONG_IRON_ZEN_STAFF_STAGE_THREE, 50)
                        .unlockedBy(getHasName(HAMMER_OF_CRAFTER), has(HAMMER_OF_CRAFTER))
                        .save(output, Constants.modRecipe("strong_iron_zen_staff_stage_three_from_super_crafting"));

                // #################################################################################################################
                // #                                              SQUEEZER RECIPES                                                 #
                // #################################################################################################################
                SqueezerRecipeBuilder.squeezerRecipe(RecipeCategory.MISC, STRAWBERRY)
                        .fruitInNorthSlot(Ingredient.of(RAW_STRAWBERRY))
                        .fruitInSouthSlot(Ingredient.of(RAW_STRAWBERRY))
                        .build()
                        .unlockedBy(getHasName(RAW_STRAWBERRY), has(RAW_STRAWBERRY))
                        .save(output, Constants.modRecipe("strawberry_from_squeezing"));

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
                shapeless(RecipeCategory.MISC, COMPRESSED_WOOD_PLANKS, 4)
                        .requires(COMPRESSED_WOOD_LOG)
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
                        .save(output, "compressed_wood_planks_from_compressed_wood_logs");
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

            private void mtsBowBuilder(final ItemLike bow, final ItemLike stick, final ItemLike unlockPlanks, final String id) {
                super.shaped(RecipeCategory.COMBAT, bow)
                        .define('S', stick)
                        .define('I', Items.STRING)
                        .pattern(" SI")
                        .pattern("S I")
                        .pattern(" SI")
                        .unlockedBy(getHasName(unlockPlanks), has(unlockPlanks))
                        .group(id)
                        .save(output);
            }

            private void mtsStickBuilder(final ItemLike stick, final ItemLike planks, final ItemLike unlockPlanks, final String id) {
                super.shaped(RecipeCategory.BUILDING_BLOCKS, stick, 4)
                        .define('P', planks)
                        .pattern("P")
                        .pattern("P")
                        .unlockedBy(getHasName(unlockPlanks), has(unlockPlanks))
                        .group(id)
                        .save(vanillaRecipeOutput);
            }

            private void mtsSignBuilder(final ItemLike sign, final ItemLike planks, final ItemLike stick, final ItemLike unlockPlanks, final ItemLike unlockStick, final String id) {
                super.shaped(RecipeCategory.BUILDING_BLOCKS, sign, 3)
                        .define('P', planks)
                        .define('S', stick)
                        .pattern("PPP")
                        .pattern("PPP")
                        .pattern(" S ")
                        .unlockedBy(getHasName(unlockPlanks), has(unlockPlanks))
                        .unlockedBy(getHasName(unlockStick), has(unlockStick))
                        .group(id)
                        .save(vanillaRecipeOutput);
            }

            private void mtsFenceBuilder(final ItemLike crafted, final ItemLike planks, final ItemLike stick, final ItemLike unlockPlanks, final ItemLike unlockStick, final String id) {
                super.shaped(RecipeCategory.BUILDING_BLOCKS, crafted, 3)
                        .define('P', planks)
                        .define('S', stick)
                        .pattern("PSP")
                        .pattern("PSP")
                        .unlockedBy(getHasName(unlockPlanks), has(unlockPlanks))
                        .unlockedBy(getHasName(unlockStick), has(unlockStick))
                        .group(id)
                        .save(vanillaRecipeOutput);
            }

            private void mtsFenceGateBuilder(final ItemLike crafted, final ItemLike planks, final ItemLike stick, final ItemLike unlockPlanks, final ItemLike unlockStick, final String id) {
                super.shaped(RecipeCategory.BUILDING_BLOCKS, crafted)
                        .define('P', planks)
                        .define('S', stick)
                        .pattern("SPS")
                        .pattern("SPS")
                        .unlockedBy(getHasName(unlockPlanks), has(unlockPlanks))
                        .unlockedBy(getHasName(unlockStick), has(unlockStick))
                        .group(id)
                        .save(vanillaRecipeOutput);
            }

            private void axeFromIngot(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.TOOLS, crafted)
                        .define('#', MtsTags.Recipes.MTS_UNIVERSAL_STICK)
                        .define('X', ingot)
                        .pattern("XX")
                        .pattern("X#")
                        .pattern(" #")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(output);
            }

            private void woodenAxeFromPlanks(final ItemLike crafted, final ItemLike planks, final ItemLike stick, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.TOOLS, crafted)
                        .define('#', stick)
                        .define('X', planks)
                        .pattern("XX")
                        .pattern("X#")
                        .pattern(" #")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(output);
            }

            private void overrideVanillaAxe(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.TOOLS, crafted)
                        .define('#', MtsTags.Recipes.MTS_UNIVERSAL_STICK)
                        .define('X', ingot)
                        .pattern("XX")
                        .pattern("X#")
                        .pattern(" #")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(vanillaRecipeOutput);
            }

            private void overrideVanillaHoe(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.TOOLS, crafted)
                        .define('#', MtsTags.Recipes.MTS_UNIVERSAL_STICK)
                        .define('X', ingot)
                        .pattern("XX")
                        .pattern(" #")
                        .pattern(" #")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(vanillaRecipeOutput);
            }

            private void overrideVanillaPickaxe(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.TOOLS, crafted)
                        .define('#', MtsTags.Recipes.MTS_UNIVERSAL_STICK)
                        .define('X', ingot)
                        .pattern("XXX")
                        .pattern(" # ")
                        .pattern(" # ")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(vanillaRecipeOutput);
            }

            private void overrideVanillaShovel(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.TOOLS, crafted)
                        .define('#', MtsTags.Recipes.MTS_UNIVERSAL_STICK)
                        .define('X', ingot)
                        .pattern("X")
                        .pattern("#")
                        .pattern("#")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(vanillaRecipeOutput);
            }

            private void overrideVanillaSpear(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.COMBAT, crafted)
                        .define('#', MtsTags.Recipes.MTS_UNIVERSAL_STICK)
                        .define('X', ingot)
                        .pattern("  X")
                        .pattern(" # ")
                        .pattern("#  ")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(vanillaRecipeOutput);
            }

            private void overrideVanillaSword(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.COMBAT, crafted)
                        .define('#', MtsTags.Recipes.MTS_UNIVERSAL_STICK)
                        .define('X', ingot)
                        .pattern("X")
                        .pattern("X")
                        .pattern("#")
                        .unlockedBy(getHasName(unlockItem), has(unlockItem))
                        .group(id)
                        .save(vanillaRecipeOutput);
            }

            private void hoeFromIngot(final ItemLike crafted, final ItemLike ingot, final ItemLike unlockItem, final String id) {
                super.shaped(RecipeCategory.TOOLS, crafted)
                        .define('#', MtsTags.Recipes.MTS_UNIVERSAL_STICK)
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
                        .define('#', MtsTags.Recipes.MTS_UNIVERSAL_STICK)
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
                        .define('#', MtsTags.Recipes.MTS_UNIVERSAL_STICK)
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
                        .define('#', MtsTags.Recipes.MTS_UNIVERSAL_STICK)
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
                        .define('#', MtsTags.Recipes.MTS_UNIVERSAL_STICK)
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
