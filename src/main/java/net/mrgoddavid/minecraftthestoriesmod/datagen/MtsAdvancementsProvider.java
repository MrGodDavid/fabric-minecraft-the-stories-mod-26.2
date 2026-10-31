package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.content.*;
import net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.MtsAdvancementTriggers;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Does data generation of MTS advancements.
 *
 * @author Mr. GodDavid
 * @since 8/25/2026
 */
public class MtsAdvancementsProvider extends AdvancementProvider {

    public MtsAdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new MtsOverworldAdvancements()));
        MinecraftTheStoriesMod.LOGGER.info("Providing data of MTS Advancements for: " + MinecraftTheStoriesMod.MOD_ID);
    }

    public static class MtsOverworldAdvancements implements AdvancementSubProvider {

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
            var items = registries.lookupOrThrow(Registries.ITEM);
            var blocks = registries.lookupOrThrow(Registries.BLOCK);

            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(
                            Items.WOODEN_PICKAXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.root.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.root.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            false,
                            false
                    )
                    .addCriterion("has_wooden_pickaxe", hasObtainedItem(items, Items.WOODEN_PICKAXE))
                    .save(output, generateSaveName("root"));

            AdvancementHolder useIronPicMinDiamonds = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            MtsItems.BROKEN_IRON_PICKAXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.use_iron_pic_mine_diamonds.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.use_iron_pic_mine_diamonds.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("use_iron_pickaxe_mining_stone_diamonds_ores", specificItemMinesSpecificBlock(items, blocks, Items.IRON_PICKAXE, Blocks.DIAMOND_ORE))
                    .addCriterion("use_iron_pickaxe_mining_deepslate_diamonds_ores", specificItemMinesSpecificBlock(items, blocks, Items.IRON_PICKAXE, Blocks.DEEPSLATE_DIAMOND_ORE))
                    .save(output, generateSaveName("use_iron_pic_mine_diamonds"));

            AdvancementHolder obtainEmeraldPickaxe = Advancement.Builder.advancement()
                    .parent(useIronPicMinDiamonds)
                    .display(
                            MtsItems.EMERALD_PICKAXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.make_emerald_pickaxe.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.make_emerald_pickaxe.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("make_emerald_pickaxe_with_emeralds", hasObtainedItem(items, MtsItems.EMERALD_PICKAXE))
                    .save(output, generateSaveName("make_emerald_pickaxe"));

            // Made all emerald tools
            AdvancementHolder moneyPower = Advancement.Builder.advancement()
                    .parent(obtainEmeraldPickaxe)
                    .display(
                            MtsItems.EMERALD_AXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.money_power.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.money_power.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("make_emerald_axes", hasObtainedItem(items, MtsItems.EMERALD_AXE))
                    .addCriterion("make_emerald_hoes", hasObtainedItem(items, MtsItems.EMERALD_HOE))
                    .addCriterion("make_emerald_pickaxes", hasObtainedItem(items, MtsItems.EMERALD_PICKAXE))
                    .addCriterion("make_emerald_shovels", hasObtainedItem(items, MtsItems.EMERALD_SHOVEL))
                    .addCriterion("make_emerald_spears", hasObtainedItem(items, MtsItems.EMERALD_SPEAR))
                    .addCriterion("make_emerald_swords", hasObtainedItem(items, MtsItems.EMERALD_SWORD))
                    .save(output, generateSaveName("money_power"));

            AdvancementHolder moneyProtection = Advancement.Builder.advancement()
                    .parent(obtainEmeraldPickaxe)
                    .display(
                            MtsItems.EMERALD_CHESTPLATE,
                            Component.translatable("advancements.minecraft-the-stories-mod.money_protection.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.money_protection.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("make_emerald_helmet", hasObtainedItem(items, MtsItems.EMERALD_HELMET))
                    .addCriterion("make_emerald_chestplate", hasObtainedItem(items, MtsItems.EMERALD_CHESTPLATE))
                    .addCriterion("make_emerald_leggings", hasObtainedItem(items, MtsItems.EMERALD_LEGGINGS))
                    .addCriterion("make_emerald_boots", hasObtainedItem(items, MtsItems.EMERALD_BOOTS))
                    .save(output, generateSaveName("money_protection_emerald_armor"));

            AdvancementHolder showOff = Advancement.Builder.advancement()
                    .parent(moneyProtection)
                    .display(
                            Items.EMERALD_BLOCK,
                            Component.translatable("advancements.minecraft-the-stories-mod.show_off.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.show_off.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("wear_full_emerald_armor_in_villages", hasEnteredVillagesWithFullEmeraldArmor())
                    .save(output, generateSaveName("show_off_emerald_armor"));

            // Use emerald pickaxe mining diamond ores.
            AdvancementHolder itsYouNow = Advancement.Builder.advancement()
                    .parent(obtainEmeraldPickaxe)
                    .display(
                            Items.DIAMOND_ORE,
                            Component.translatable("advancements.minecraft-the-stories-mod.use_emerald_pic_mining_diamonds.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.use_emerald_pic_mining_diamonds.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("use_emerald_pic_mine_stone_emerald_ores", specificItemMinesSpecificBlock(items, blocks, MtsItems.EMERALD_PICKAXE, Blocks.DIAMOND_ORE))
                    .addCriterion("use_emerald_pic_mine_deepslate_emerald_ores", specificItemMinesSpecificBlock(items, blocks, MtsItems.EMERALD_PICKAXE, Blocks.DEEPSLATE_DIAMOND_ORE))
                    .save(output, generateSaveName("use_emerald_pic_mining_diamonds"));

            // Use diamond pickaxes to mine topaz ores.
            AdvancementHolder newOres = Advancement.Builder.advancement()
                    .parent(itsYouNow)
                    .display(
                            MtsBlocks.STONE_TOPAZ_ORE,
                            Component.translatable("advancements.minecraft-the-stories-mod.use_diamond_pic_mining_topaz_ores.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.use_diamond_pic_mining_topaz_ores.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("use_diamond_pic_mining_stone_topaz_ores", specificItemMinesSpecificBlock(items, blocks, Items.DIAMOND_PICKAXE, MtsBlocks.STONE_TOPAZ_ORE))
                    .addCriterion("use_diamond_pic_mining_deepslate_topaz_ores", specificItemMinesSpecificBlock(items, blocks, Items.DIAMOND_PICKAXE, MtsBlocks.DEEPSLATE_TOPAZ_ORE))
                    .save(output, generateSaveName("use_diamond_pic_mining_topaz_ores"));

            // Obtain a raw topaz stone/deepslate ore.
            AdvancementHolder orangeThings = Advancement.Builder.advancement()
                    .parent(newOres)
                    .display(
                            MtsItems.RAW_STRONG_TOPAZ,
                            Component.translatable("advancements.minecraft-the-stories-mod.obtain_topaz_ores.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.obtain_topaz_ores.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("obtain_raw_strong_topaz_ore_from_stone_topaz", hasObtainedItem(items, MtsItems.RAW_STRONG_TOPAZ))
                    .save(output, generateSaveName("obtain_topaz_ores"));

            // Use Super Crafter to make a Strong Topaz Pickaxe.
            AdvancementHolder fasterGoldenPickaxe = Advancement.Builder.advancement()
                    .parent(orangeThings)
                    .display(
                            MtsItems.STRONG_TOPAZ_PICKAXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.faster_golden_pickaxe.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.faster_golden_pickaxe.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("obtain_strong_topaz_pickaxe", hasObtainedItem(items, MtsItems.STRONG_TOPAZ_PICKAXE))
                    .save(output, generateSaveName("faster_golden_pickaxe"));

            // Mine stone/deepslate ruby ore with Strong Topaz Pickaxe.
            AdvancementHolder redstone = Advancement.Builder.advancement()
                    .parent(fasterGoldenPickaxe)
                    .display(
                            MtsItems.RAW_STRONG_RUBY,
                            Component.translatable("advancements.minecraft-the-stories-mod.redstone.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.redstone.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("mine_stone_ruby_ore_with_strong_topaz_pickaxe", specificItemMinesSpecificBlock(items, blocks, MtsItems.STRONG_TOPAZ_PICKAXE, MtsBlocks.STONE_RUBY_ORE))
                    .addCriterion("mine_deepslate_ruby_ore_with_strong_topaz_pickaxe", specificItemMinesSpecificBlock(items, blocks, MtsItems.STRONG_TOPAZ_PICKAXE, MtsBlocks.DEEPSLATE_RUBY_ORE))
                    .save(output, generateSaveName("redstone"));

            // Use Super Crafter to make Strong Ruby Pickaxe.
            AdvancementHolder firePickaxe = Advancement.Builder.advancement()
                    .parent(redstone)
                    .display(
                            MtsItems.STRONG_RUBY_PICKAXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.fire_pickaxe.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.fire_pickaxe.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("craft_strong_ruby_pickaxe", hasObtainedItem(items, MtsItems.STRONG_RUBY_PICKAXE))
                    .save(output, generateSaveName("fire_pickaxe"));

            // Mine ancient debris with Strong Ruby Pickaxe
            AdvancementHolder backOnTrack = Advancement.Builder.advancement()
                    .parent(firePickaxe)
                    .display(
                            Blocks.ANCIENT_DEBRIS,
                            Component.translatable("advancements.minecraft-the-stories-mod.back_on_track.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.back_on_track.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    )
                    .addCriterion("mine_ancient_debris_with_strong_ruby_pickaxe", specificItemMinesSpecificBlock(items, blocks, MtsItems.STRONG_RUBY_PICKAXE, Blocks.ANCIENT_DEBRIS))
                    .save(output, generateSaveName("back_on_track"));

            // Make Netherite Pickaxe
            AdvancementHolder goodToSeeYouOldFriend = Advancement.Builder.advancement()
                    .parent(backOnTrack)
                    .display(
                            Items.NETHERITE_PICKAXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.good_to_see_you_old_friend.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.good_to_see_you_old_friend.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("upgrade a diamond pickaxe to netherite pickaxe", hasObtainedItem(items, Items.NETHERITE_PICKAXE))
                    .save(output, generateSaveName("good_to_see_you_old_friend"));

            // Mine stone/deepslate amethyst ore with netherite pickaxe
            AdvancementHolder newAmethyst = Advancement.Builder.advancement()
                    .parent(goodToSeeYouOldFriend)
                    .display(
                            MtsItems.RAW_STRONG_AMETHYST,
                            Component.translatable("advancements.minecraft-the-stories-mod.new_amethyst.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.new_amethyst.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("mine_stone_amethyst_ore_with_netherite_pickaxe", specificItemMinesSpecificBlock(items, blocks, Items.NETHERITE_PICKAXE, MtsBlocks.STONE_AMETHYST_ORE))
                    .addCriterion("mine_deepslate_amethyst_ore_with_netherite_pickaxe", specificItemMinesSpecificBlock(items, blocks, Items.NETHERITE_PICKAXE, MtsBlocks.DEEPSLATE_AMETHYST_ORE))
                    .save(output, generateSaveName("new_amethyst"));

            // Obtain full strong amethyst armor
            AdvancementHolder aStepCloserToImmortal = Advancement.Builder.advancement()
                    .parent(newAmethyst)
                    .display(
                            MtsItems.STRONG_AMETHYST_CHESTPLATE,
                            Component.translatable("advancements.minecraft-the-stories-mod.a_step_closer_to_immortal.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.a_step_closer_to_immortal.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    )
                    .addCriterion("wear_full_amethyst_armor", wearFullAmethystArmor())
                    .save(output, generateSaveName("a_step_closer_to_immortal"));

            // You are too powerful, so everyone hates you. Even the passive mobs hate you now...
            AdvancementHolder publicEnemy = Advancement.Builder.advancement()
                    .parent(newAmethyst)
                    .display(
                            MtsItems.PUBLIC_ENEMY_EFFECT_ICON,
                            Component.translatable("advancements.minecraft-the-stories-mod.public_enemy.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.public_enemy.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("has_public_enemy_effect", hasPublicEnemyEffect())
                    .save(output, generateSaveName("public_enemy"));

            // Craft Strong Amethyst Pickaxe with Super Crafter
            AdvancementHolder purplePickaxe = Advancement.Builder.advancement()
                    .parent(newAmethyst)
                    .display(
                            MtsItems.STRONG_AMETHYST_PICKAXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.purple_pickaxe.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.purple_pickaxe.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    )
                    .addCriterion("craft_strong_amethyst_pickaxe", hasObtainedItem(items, MtsItems.STRONG_AMETHYST_PICKAXE))
                    .save(output, generateSaveName("purple_pickaxe"));

            // Create any axes for the first time.
            AdvancementHolder timeForLumberjacking = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            Items.IRON_AXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.time_for_lumberjacking.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.time_for_lumberjacking.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("obtain_any_axes_wooden_axe", hasObtainedItem(items, Items.WOODEN_AXE))
                    .addCriterion("obtain_any_axes_stone_axe", hasObtainedItem(items, Items.STONE_AXE))
                    .addCriterion("obtain_any_axes_iron_axe", hasObtainedItem(items, Items.IRON_AXE))
                    .addCriterion("obtain_any_axes_golden_axe", hasObtainedItem(items, Items.GOLDEN_AXE))
                    .addCriterion("obtain_any_axes_emerald_axe", hasObtainedItem(items, MtsItems.EMERALD_AXE))
                    .addCriterion("obtain_any_axes_diamond_axe", hasObtainedItem(items, Items.DIAMOND_AXE))
                    .addCriterion("obtain_any_axes_topaz_axe", hasObtainedItem(items, MtsItems.STRONG_TOPAZ_AXE))
                    .addCriterion("obtain_any_axes_ruby_axe", hasObtainedItem(items, MtsItems.STRONG_RUBY_AXE))
                    .addCriterion("obtain_any_axes_netherite_axe", hasObtainedItem(items, Items.NETHERITE_AXE))
                    .addCriterion("obtain_any_axes_amethyst_axe", hasObtainedItem(items, MtsItems.STRONG_AMETHYST_AXE))
                    .save(output, generateSaveName("time_for_lumberjacking"));

            // Hit enemy with cudgel.
            AdvancementHolder bonk = Advancement.Builder.advancement()
                    .parent(timeForLumberjacking)
                    .display(
                            MtsItems.OAK_CUDGEL,
                            Component.translatable("advancements.minecraft-the-stories-mod.bonk.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.bonk.description"),
                            Constants.defaultId("gui/advancements/backgrounds/advanture"),
                            AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("hit_enemy_with_cudgel", hitHitEnemyWithCudgel())
                    .save(output, generateSaveName("bonk"));

            // Obtain all tree types in minecraft.
            AdvancementHolder woodCollector = Advancement.Builder.advancement()
                    .parent(timeForLumberjacking)
                    .display(
                            Items.OAK_LOG,
                            Component.translatable("advancements.minecraft-the-stories-mod.wood_collector.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.wood_collector.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("obtain_oak_log", hasObtainedItem(items, Items.OAK_LOG))
                    .addCriterion("obtain_dark_oak_log", hasObtainedItem(items, Items.DARK_OAK_LOG))
                    .addCriterion("obtain_pale_oak_log", hasObtainedItem(items, Items.PALE_OAK_LOG))
                    .addCriterion("obtain_spruce_log", hasObtainedItem(items, Items.SPRUCE_LOG))
                    .addCriterion("obtain_birch_log", hasObtainedItem(items, Items.BIRCH_LOG))
                    .addCriterion("obtain_jungle_log", hasObtainedItem(items, Items.JUNGLE_LOG))
                    .addCriterion("obtain_acacia_log", hasObtainedItem(items, Items.ACACIA_LOG))
                    .addCriterion("obtain_mangrove_log", hasObtainedItem(items, Items.MANGROVE_LOG))
                    .addCriterion("obtain_cherry_log", hasObtainedItem(items, Items.CHERRY_LOG))
                    .save(output, generateSaveName("wood_collector"));

            // Obtain a compressed wood.
            AdvancementHolder compressedWoodPlanks = Advancement.Builder.advancement()
                    .parent(woodCollector)
                    .display(
                            MtsBlocks.COMPRESSED_WOOD_PLANKS,
                            Component.translatable("advancements.minecraft-the-stories-mod.compressed_wood_planks.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.compressed_wood_planks.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("obtain_compressed_wood_planks", hasObtainedItem(items, MtsBlocks.COMPRESSED_WOOD_PLANKS))
                    .save(output, generateSaveName("compressed_wood_planks"));

            // Craft a super crafter.
            AdvancementHolder superCrafter = Advancement.Builder.advancement()
                    .parent(compressedWoodPlanks)
                    .display(
                            MtsBlocks.SUPER_CRAFTER_BLOCK,
                            Component.translatable("advancements.minecraft-the-stories-mod.super_crafter.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.super_crafter.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    )
                    .addCriterion("obtain_super_crafter", hasObtainedItem(items, MtsBlocks.SUPER_CRAFTER_BLOCK))
                    .save(output, generateSaveName("super_crafter"));

            // Obtain full stack of oak, dark oak, pale oak, spruce, birch, acacia. mangrove, and cherry log.
            AdvancementHolder woodMaster = Advancement.Builder.advancement()
                    .parent(woodCollector)
                    .display(
                            MtsItems.STRONG_AMETHYST_AXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.wood_master.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.wood_master.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("obtain_full_stack_of_oak_log", hasObtainFullStackOf(items, Items.OAK_LOG))
                    .addCriterion("obtain_full_stack_of_dark_oak_log", hasObtainFullStackOf(items, Items.DARK_OAK_LOG))
                    .addCriterion("obtain_full_stack_of_pale_oak_log", hasObtainFullStackOf(items, Items.PALE_OAK_LOG))
                    .addCriterion("obtain_full_stack_of_spruce_log", hasObtainFullStackOf(items, Items.SPRUCE_LOG))
                    .addCriterion("obtain_full_stack_of_birch_log", hasObtainFullStackOf(items, Items.BIRCH_LOG))
                    .addCriterion("obtain_full_stack_of_jungle_log", hasObtainFullStackOf(items, Items.JUNGLE_LOG))
                    .addCriterion("obtain_full_stack_of_acacia_log", hasObtainFullStackOf(items, Items.ACACIA_LOG))
                    .addCriterion("obtain_full_stack_of_mangrove_log", hasObtainFullStackOf(items, Items.MANGROVE_LOG))
                    .addCriterion("obtain_full_stack_of_cherry_log", hasObtainFullStackOf(items, Items.CHERRY_LOG))
                    .addCriterion("obtain_full_stack_of_compressed_wood_log", hasObtainFullStackOf(items, MtsBlocks.COMPRESSED_WOOD_LOG))
                    .save(output, generateSaveName("wood_master"));

            // Obtain all 9 types of wooden axe, bow, cudgel, hoe, pickaxe, shovel, sword, and spear.
            // The types of wood are acacia, birch, cherry, dark oak, jungle, mangrove, pale oak, oak, and spruce.
            AdvancementHolder woodenPsychopath = Advancement.Builder.advancement()
                    .parent(woodMaster)
                    .display(
                            MtsItems.OAK_CUDGEL,
                            Component.translatable("advancements.minecraft-the-stories-mod.wooden_psychopath.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.wooden_psychopath.desciription"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("obtain_acacia_axe", hasObtainedItem(items, MtsItems.ACACIA_AXE))
                    .addCriterion("obtain_acacia_bow", hasObtainedItem(items, MtsItems.ACACIA_BOW))
                    .addCriterion("obtain_acacia_cudgel", hasObtainedItem(items, MtsItems.ACACIA_CUDGEL))
                    .addCriterion("obtain_acacia_hoe", hasObtainedItem(items, MtsItems.ACACIA_HOE))
                    .addCriterion("obtain_acacia_pickaxe", hasObtainedItem(items, MtsItems.ACACIA_PICKAXE))
                    .addCriterion("obtain_acacia_shovel", hasObtainedItem(items, MtsItems.ACACIA_SHOVEL))
                    .addCriterion("obtain_acacia_sword", hasObtainedItem(items, MtsItems.ACACIA_SWORD))
                    .addCriterion("obtain_acacia_spear", hasObtainedItem(items, MtsItems.ACACIA_SPEAR))
                    .addCriterion("obtain_birch_axe", hasObtainedItem(items, MtsItems.BIRCH_AXE))
                    .addCriterion("obtain_birch_bow", hasObtainedItem(items, MtsItems.BIRCH_BOW))
                    .addCriterion("obtain_birch_cudgel", hasObtainedItem(items, MtsItems.BIRCH_CUDGEL))
                    .addCriterion("obtain_birch_hoe", hasObtainedItem(items, MtsItems.BIRCH_HOE))
                    .addCriterion("obtain_birch_pickaxe", hasObtainedItem(items, MtsItems.BIRCH_PICKAXE))
                    .addCriterion("obtain_birch_shovel", hasObtainedItem(items, MtsItems.BIRCH_SHOVEL))
                    .addCriterion("obtain_birch_sword", hasObtainedItem(items, MtsItems.BIRCH_SWORD))
                    .addCriterion("obtain_birch_spear", hasObtainedItem(items, MtsItems.BIRCH_SPEAR))
                    .addCriterion("obtain_cherry_axe", hasObtainedItem(items, MtsItems.CHERRY_AXE))
                    .addCriterion("obtain_cherry_bow", hasObtainedItem(items, MtsItems.CHERRY_BOW))
                    .addCriterion("obtain_cherry_cudgel", hasObtainedItem(items, MtsItems.CHERRY_CUDGEL))
                    .addCriterion("obtain_cherry_hoe", hasObtainedItem(items, MtsItems.CHERRY_HOE))
                    .addCriterion("obtain_cherry_pickaxe", hasObtainedItem(items, MtsItems.CHERRY_PICKAXE))
                    .addCriterion("obtain_cherry_shovel", hasObtainedItem(items, MtsItems.CHERRY_SHOVEL))
                    .addCriterion("obtain_cherry_sword", hasObtainedItem(items, MtsItems.CHERRY_SWORD))
                    .addCriterion("obtain_cherry_spear", hasObtainedItem(items, MtsItems.CHERRY_SPEAR))
                    .addCriterion("obtain_dark_oak_axe", hasObtainedItem(items, MtsItems.DARK_OAK_AXE))
                    .addCriterion("obtain_dark_oak_bow", hasObtainedItem(items, MtsItems.DARK_OAK_BOW))
                    .addCriterion("obtain_dark_oak_cudgel", hasObtainedItem(items, MtsItems.DARK_OAK_CUDGEL))
                    .addCriterion("obtain_dark_oak_hoe", hasObtainedItem(items, MtsItems.DARK_OAK_HOE))
                    .addCriterion("obtain_dark_oak_pickaxe", hasObtainedItem(items, MtsItems.DARK_OAK_PICKAXE))
                    .addCriterion("obtain_dark_oak_shovel", hasObtainedItem(items, MtsItems.DARK_OAK_SHOVEL))
                    .addCriterion("obtain_dark_oak_sword", hasObtainedItem(items, MtsItems.DARK_OAK_SWORD))
                    .addCriterion("obtain_dark_oak_spear", hasObtainedItem(items, MtsItems.DARK_OAK_SPEAR))
                    .addCriterion("obtain_jungle_axe", hasObtainedItem(items, MtsItems.JUNGLE_AXE))
                    .addCriterion("obtain_jungle_bow", hasObtainedItem(items, MtsItems.JUNGLE_BOW))
                    .addCriterion("obtain_jungle_cudgel", hasObtainedItem(items, MtsItems.JUNGLE_CUDGEL))
                    .addCriterion("obtain_jungle_hoe", hasObtainedItem(items, MtsItems.JUNGLE_HOE))
                    .addCriterion("obtain_jungle_pickaxe", hasObtainedItem(items, MtsItems.JUNGLE_PICKAXE))
                    .addCriterion("obtain_jungle_shovel", hasObtainedItem(items, MtsItems.JUNGLE_SHOVEL))
                    .addCriterion("obtain_jungle_sword", hasObtainedItem(items, MtsItems.JUNGLE_SWORD))
                    .addCriterion("obtain_jungle_spear", hasObtainedItem(items, MtsItems.JUNGLE_SPEAR))
                    .addCriterion("obtain_mangrove_axe", hasObtainedItem(items, MtsItems.MANGROVE_AXE))
                    .addCriterion("obtain_mangrove_bow", hasObtainedItem(items, MtsItems.MANGROVE_BOW))
                    .addCriterion("obtain_mangrove_cudgel", hasObtainedItem(items, MtsItems.MANGROVE_CUDGEL))
                    .addCriterion("obtain_mangrove_hoe", hasObtainedItem(items, MtsItems.MANGROVE_HOE))
                    .addCriterion("obtain_mangrove_pickaxe", hasObtainedItem(items, MtsItems.MANGROVE_PICKAXE))
                    .addCriterion("obtain_mangrove_shovel", hasObtainedItem(items, MtsItems.MANGROVE_SHOVEL))
                    .addCriterion("obtain_mangrove_sword", hasObtainedItem(items, MtsItems.MANGROVE_SWORD))
                    .addCriterion("obtain_mangrove_spear", hasObtainedItem(items, MtsItems.MANGROVE_SPEAR))
                    .addCriterion("obtain_pale_oak_axe", hasObtainedItem(items, MtsItems.PALE_OAK_AXE))
                    .addCriterion("obtain_pale_oak_bow", hasObtainedItem(items, MtsItems.PALE_OAK_BOW))
                    .addCriterion("obtain_pale_oak_cudgel", hasObtainedItem(items, MtsItems.PALE_OAK_CUDGEL))
                    .addCriterion("obtain_pale_oak_hoe", hasObtainedItem(items, MtsItems.PALE_OAK_HOE))
                    .addCriterion("obtain_pale_oak_pickaxe", hasObtainedItem(items, MtsItems.PALE_OAK_PICKAXE))
                    .addCriterion("obtain_pale_oak_shovel", hasObtainedItem(items, MtsItems.PALE_OAK_SHOVEL))
                    .addCriterion("obtain_pale_oak_sword", hasObtainedItem(items, MtsItems.PALE_OAK_SWORD))
                    .addCriterion("obtain_pale_oak_spear", hasObtainedItem(items, MtsItems.PALE_OAK_SPEAR))
                    .addCriterion("obtain_oak_axe", hasObtainedItem(items, Items.WOODEN_AXE))
                    .addCriterion("obtain_oak_bow", hasObtainedItem(items, Items.BOW))
                    .addCriterion("obtain_oak_cudgel", hasObtainedItem(items, MtsItems.OAK_CUDGEL))
                    .addCriterion("obtain_oak_hoe", hasObtainedItem(items, Items.WOODEN_HOE))
                    .addCriterion("obtain_oak_pickaxe", hasObtainedItem(items, Items.WOODEN_PICKAXE))
                    .addCriterion("obtain_oak_shovel", hasObtainedItem(items, Items.WOODEN_SHOVEL))
                    .addCriterion("obtain_oak_sword", hasObtainedItem(items, Items.WOODEN_SWORD))
                    .addCriterion("obtain_oak_spear", hasObtainedItem(items, Items.WOODEN_SPEAR))
                    .addCriterion("obtain_spruce_oak_axe", hasObtainedItem(items, MtsItems.SPRUCE_AXE))
                    .addCriterion("obtain_spruce_oak_bow", hasObtainedItem(items, MtsItems.SPRUCE_BOW))
                    .addCriterion("obtain_spruce_oak_cudgel", hasObtainedItem(items, MtsItems.SPRUCE_CUDGEL))
                    .addCriterion("obtain_spruce_oak_hoe", hasObtainedItem(items, MtsItems.SPRUCE_HOE))
                    .addCriterion("obtain_spruce_oak_pickaxe", hasObtainedItem(items, MtsItems.SPRUCE_PICKAXE))
                    .addCriterion("obtain_spruce_oak_shovel", hasObtainedItem(items, MtsItems.SPRUCE_SHOVEL))
                    .addCriterion("obtain_spruce_oak_sword", hasObtainedItem(items, MtsItems.SPRUCE_SWORD))
                    .addCriterion("obtain_spruce_oak_spear", hasObtainedItem(items, MtsItems.SPRUCE_SPEAR))
                    .save(output, generateSaveName("wooden_psychopath"));

            // Use Diamond Pickaxe to mine (stone) ruby ore, (stone) amethyst ore, and netherite.
            AdvancementHolder notTodayMyOldFriend = Advancement.Builder.advancement()
                    .parent(fasterGoldenPickaxe)
                    .display(
                            MtsItems.BROKEN_DIAMOND_PICKAXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.not_today_my_old_friend.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.not_today_my_old_friend.description"),
                            Constants.defaultId("gui/advancements/backgrounds/adventure"),
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("use_diamond_pic_mining_stone_ruby_ore", specificItemMinesSpecificBlock(items, blocks, Items.DIAMOND_PICKAXE, MtsBlocks.STONE_RUBY_ORE))
                    .addCriterion("use_diamond_pic_mining_deepslate_ruby_ore", specificItemMinesSpecificBlock(items, blocks, Items.DIAMOND_PICKAXE, MtsBlocks.DEEPSLATE_RUBY_ORE))
                    .addCriterion("use_diamond_pic_mining_ancient_debris", specificItemMinesSpecificBlock(items, blocks, Items.DIAMOND_PICKAXE, Blocks.ANCIENT_DEBRIS))
                    .addCriterion("use_diamond_pic_mining_stone_amethyst_ore", specificItemMinesSpecificBlock(items, blocks, Items.DIAMOND_PICKAXE, MtsBlocks.STONE_AMETHYST_ORE))
                    .addCriterion("use_diamond_pic_mining_deepslate_amethyst_ore", specificItemMinesSpecificBlock(items, blocks, Items.DIAMOND_PICKAXE, MtsBlocks.DEEPSLATE_AMETHYST_ORE))
                    .save(output, generateSaveName("not_today_my_old_friend"));

            // Craft an enricher.
            AdvancementHolder enriching = Advancement.Builder.advancement()
                    .parent(orangeThings)
                    .display(
                            MtsBlocks.ENRICHER,
                            Component.translatable("advancements.minecraft-the-stories-mod.enriching.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.enriching.description"),
                            Constants.defaultId("gui/advancement/backgrounds/advanture"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("craft_an_enricher", hasObtainedItem(items, MtsBlocks.ENRICHER))
                    .save(output, generateSaveName("enriching"));

            // Obtain a strong material.
            AdvancementHolder strongOres = Advancement.Builder.advancement()
                    .parent(enriching)
                    .display(
                            MtsItems.STRONG_AMETHYST,
                            Component.translatable("advancements.minecraft-the-stories-mod.strong_ores.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.strong_ores.description"),
                            Constants.defaultId("gui/advancement/backgrounds/advanture"),
                            AdvancementType.TASK,
                            true, true, false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("obtain_strong_amethyst", hasObtainedItem(items, MtsItems.STRONG_AMETHYST))
                    .addCriterion("obtain_strong_diamond", hasObtainedItem(items, MtsItems.STRONG_DIAMOND))
                    .addCriterion("obtain_strong_emerald", hasObtainedItem(items, MtsItems.STRONG_EMERALD))
                    .addCriterion("obtain_strong_gold", hasObtainedItem(items, MtsItems.STRONG_GOLD))
                    .addCriterion("obtain_strong_iron", hasObtainedItem(items, MtsItems.STRONG_IRON))
                    .addCriterion("obtain_strong_ruby", hasObtainedItem(items, MtsItems.STRONG_RUBY))
                    .addCriterion("obtain_strong_topaz", hasObtainedItem(items, MtsItems.STRONG_TOPAZ))
                    .save(output, generateSaveName("strong_ores"));

            // Obtain full stacks of all ingot version of strong materials.
            AdvancementHolder superStrongOres = Advancement.Builder.advancement()
                    .parent(strongOres)
                    .display(
                            MtsItems.STRONG_AMETHYST_INGOT,
                            Component.translatable("advancements.minecraft-the-stories-mod.super_strong_ores.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.super_strong_ores.description"),
                            Constants.defaultId("gui/advancement/backgrounds/advanture"),
                            AdvancementType.CHALLENGE,
                            true, true, false
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("obtain_a_full_stack_of_strong_amethyst_ingot", hasObtainFullStackOf(items, MtsItems.STRONG_AMETHYST_INGOT))
                    .addCriterion("obtain_a_full_stack_of_strong_diamond_ingot", hasObtainFullStackOf(items, MtsItems.STRONG_DIAMOND_INGOT))
                    .addCriterion("obtain_a_full_stack_of_strong_emerald_ingot", hasObtainFullStackOf(items, MtsItems.STRONG_EMERALD_INGOT))
                    .addCriterion("obtain_a_full_stack_of_strong_gold_ingot", hasObtainFullStackOf(items, MtsItems.STRONG_GOLD_INGOT))
                    .addCriterion("obtain_a_full_stack_of_strong_iron_ingot", hasObtainFullStackOf(items, MtsItems.STRONG_IRON_INGOT))
                    .addCriterion("obtain_a_full_stack_of_strong_ruby_ingot", hasObtainFullStackOf(items, MtsItems.STRONG_RUBY_INGOT))
                    .addCriterion("obtain_a_full_stack_of_strong_topaz_ingot", hasObtainFullStackOf(items, MtsItems.STRONG_TOPAZ_INGOT))
                    .save(output, generateSaveName("super_strong_ores"));

            // Use bucket to collect Enricher Waste.
            AdvancementHolder purpleBucket = Advancement.Builder.advancement()
                    .parent(enriching)
                    .display(
                            MtsItems.ENRICHER_WASTE_BUCKET,
                            Component.translatable("advancements.minecraft-the-stories-mod.purple_bucket.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.purple_bucket.description"),
                            Constants.defaultId("gui/advancement/backgrounds/advanture"),
                            AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("obtain_enricher_waste_bucket", hasObtainedItem(items, MtsItems.ENRICHER_WASTE_BUCKET))
                    .save(output, generateSaveName("purple_bucket"));

            // Bathing in dangerous §cEnricher §cWaste. What a brave cat...
            AdvancementHolder dangerousBath = Advancement.Builder.advancement()
                    .parent(purpleBucket)
                    .display(
                            Items.SKELETON_SKULL,
                            Component.translatable("advancements.minecraft-the-stories-mod.dangerous_bath.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.dangerous_bath.description"),
                            Constants.defaultId("gui/advancement/backgrounds/advanture"),
                            AdvancementType.CHALLENGE,
                            true, true, false
                    )
                    .addCriterion("bath_in_enricher_waste", hasBathedInEnricherWaste())
                    .save(output, generateSaveName("dangerous_bath"));

            // Bathing in dangerous Enricher Waste while being enchanted by oakie.
            AdvancementHolder iAmAGenius = Advancement.Builder.advancement()
                    .parent(dangerousBath)
                    .display(
                            Items.PLAYER_HEAD,
                            Component.translatable("advancements.minecraft-the-stories-mod.i_am_a_genius.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.i_am_a_genius.description"),
                            Constants.defaultId("gui/advancement/backgrounds/advanture"),
                            AdvancementType.CHALLENGE,
                            true, true, false
                    )
                    .addCriterion("bath_in_enricher_waste_while_being_enchanted_by_an_oakie", hasBathedInEnricherWasteWhileBeingEnchantedByOakies())
                    .save(output, generateSaveName("i_am_a_genius"));

        }

        private Criterion<?> hasBathedInEnricherWasteWhileBeingEnchantedByOakies() {
            return MtsAdvancementTriggers.BATHING_IN_ENRICHER_WASTE_WHILE_BEING_ENCHANTED_BY_OAKIE_TRIGGER.createCriterion(
                    new BathingInEnricherWasteWhileBeingEnchantedByOakieTrigger.TriggerInstance(Optional.empty()));
        }

        private Criterion<?> hitHitEnemyWithCudgel() {
            return MtsAdvancementTriggers.HIT_ENEMY_WITH_CUDGEL_TRIGGER.createCriterion(
                    new HitEnemyWithCudgelTrigger.TriggerInstance(Optional.empty(), Optional.empty(), Optional.empty()));
        }

        private Criterion<?> hasPublicEnemyEffect() {
            return MtsAdvancementTriggers.HAS_PUBLIC_ENEMY_EFFECT_TRIGGER.createCriterion(
                    new AbstractHasMobEffectTrigger.TriggerInstance(Optional.empty()));
        }

        private Criterion<?> wearFullAmethystArmor() {
            return MtsAdvancementTriggers.WEAR_FULL_AMETHYST_ARMOR_TRIGGER.createCriterion(
                    new AbstractWearFullArmorTrigger.TriggerInstance(Optional.empty()));
        }

        private Criterion<?> hasBathedInEnricherWaste() {
            return MtsAdvancementTriggers.BATHING_IN_ENRICHER_WASTE_TRIGGER.createCriterion(
                    new BathingInEnricherWasteTrigger.TriggerInstance(Optional.empty()));
        }

        private Criterion<?> hasEnteredVillagesWithFullEmeraldArmor() {
            return MtsAdvancementTriggers.ENTER_VILLAGE_WITH_FULL_EMERALD_ARMOR_TRIGGER.createCriterion(
                    new EnteredVillageWithFullEmeraldArmorTrigger.TriggerInstance(Optional.empty()));
        }

        private Criterion<?> hasObtainFullStackOf(final HolderGetter<Item> currentTime, final ItemLike requiredItem) {
            return this.hasObtainNumItemOf(currentTime, requiredItem, 64);
        }

        private Criterion<?> hasObtainNumItemOf(final HolderGetter<Item> currentItem, final ItemLike requiredItem, final int count) {
            return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(currentItem, requiredItem).withCount(MinMaxBounds.Ints.exactly(count)).build());
        }

        private Criterion<?> specificItemMinesSpecificBlock(final HolderGetter<Item> currentTool, final HolderGetter<Block> currentBlock, final ItemLike requiredItem, final Block targetBlock) {
            return MtsAdvancementTriggers.MINE_BLOCK_WITH_TOOL_TRIGGER.createCriterion(
                    new MineBlockWithToolTrigger.TriggerInstance(
                            Optional.of(HolderSet.direct(currentBlock.getOrThrow(MtsBlocks.getResourceKey(targetBlock)))),
                            Optional.of(ItemPredicate.Builder.item().of(currentTool, requiredItem).build()))
            );
        }

        private Criterion<?> hasObtainedItem(final HolderGetter<Item> current, final ItemLike require) {
            return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(current, require));
        }

        /**
         * Returns the path of the advancement in this format:
         * <pre>{@code &lt;MOD_ID&rt;:&lt;MOD_ID&rt;/&lt;ID_OF_ADVANCEMENT&rt;}</pre>
         *
         * @param id of the advancement.
         * @return the path of storing this advancement in .json file.
         */
        private String generateSaveName(final String id) {
            return MinecraftTheStoriesMod.MOD_ID + ":minecraft-the-stories-mod/" + id;
        }
    }
}
