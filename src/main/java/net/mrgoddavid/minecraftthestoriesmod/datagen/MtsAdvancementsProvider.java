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
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.advancement.MineBlockWithToolTrigger;
import net.mrgoddavid.minecraftthestoriesmod.advancement.MtsAdvancementTriggers;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;

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
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            false,
                            false
                    )
                    .addCriterion("has_wooden_pickaxe", hasObtainItem(items, Items.WOODEN_PICKAXE))
                    .save(output, generateSaveName("root"));

            AdvancementHolder useIronPicMinDiamonds = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            MtsItems.BROKEN_IRON_PICKAXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.use_iron_pic_mine_diamonds.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.use_iron_pic_mine_diamonds.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
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
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("make_emerald_pickaxe_with_emeralds", hasObtainItem(items, MtsItems.EMERALD_PICKAXE))
                    .save(output, generateSaveName("make_emerald_pickaxe"));

            AdvancementHolder obtainMoreEmeraldTools = Advancement.Builder.advancement()
                    .parent(obtainEmeraldPickaxe)
                    .display(
                            MtsItems.EMERALD_AXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.make_more_emerald_tools.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.make_more_emerald_tools.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("make_emerald_axes", hasObtainItem(items, MtsItems.EMERALD_AXE))
                    .addCriterion("make_emerald_hoes", hasObtainItem(items, MtsItems.EMERALD_HOE))
                    .addCriterion("make_emerald_pickaxes", hasObtainItem(items, MtsItems.EMERALD_PICKAXE))
                    .addCriterion("make_emerald_shovels", hasObtainItem(items, MtsItems.EMERALD_SHOVEL))
                    .addCriterion("make_emerald_spears", hasObtainItem(items, MtsItems.EMERALD_SPEAR))
                    .addCriterion("make_emerald_swords", hasObtainItem(items, MtsItems.EMERALD_SWORD))
                    .save(output, generateSaveName("make_more_emerald_tools"));

            AdvancementHolder showOff = Advancement.Builder.advancement()
                    .parent(obtainEmeraldPickaxe)
                    .display(
                            MtsItems.EMERALD_CHESTPLATE,
                            Component.translatable("advancements.minecraft-the-stories-mod.show_off.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.show_off.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("make_emerald_helmet", hasObtainItem(items, MtsItems.EMERALD_HELMET))
                    .addCriterion("make_emerald_chestplate", hasObtainItem(items, MtsItems.EMERALD_CHESTPLATE))
                    .addCriterion("make_emerald_leggings", hasObtainItem(items, MtsItems.EMERALD_LEGGINGS))
                    .addCriterion("make_emerald_boots", hasObtainItem(items, MtsItems.EMERALD_BOOTS))
                    .save(output, generateSaveName("show_off_emerald_armors"));

            // Use emerald pickaxe mining diamond ores.
            AdvancementHolder itsYouNow = Advancement.Builder.advancement()
                    .parent(obtainEmeraldPickaxe)
                    .display(
                            Items.DIAMOND_ORE,
                            Component.translatable("advancements.minecraft-the-stories-mod.use_emerald_pic_mining_diamonds.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.use_emerald_pic_mining_diamonds.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
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
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
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
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("obtain_raw_strong_topaz_ore_from_stone_topaz", hasObtainItem(items, MtsItems.RAW_STRONG_TOPAZ))
                    .save(output, generateSaveName("obtain_topaz_ores"));

            // Create any axes for the first time.
            AdvancementHolder timeForLumberjacking = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            Items.IRON_AXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.time_for_lumberjacking.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.time_for_lumberjacking.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("obtain_any_axes_wooden_axe", hasObtainItem(items, Items.WOODEN_AXE))
                    .addCriterion("obtain_any_axes_stone_axe", hasObtainItem(items, Items.STONE_AXE))
                    .addCriterion("obtain_any_axes_iron_axe", hasObtainItem(items, Items.IRON_AXE))
                    .addCriterion("obtain_any_axes_golden_axe", hasObtainItem(items, Items.GOLDEN_AXE))
                    .addCriterion("obtain_any_axes_emerald_axe", hasObtainItem(items, MtsItems.EMERALD_AXE))
                    .addCriterion("obtain_any_axes_diamond_axe", hasObtainItem(items, Items.DIAMOND_AXE))
                    .addCriterion("obtain_any_axes_topaz_axe", hasObtainItem(items, MtsItems.STRONG_TOPAZ_AXE))
                    .addCriterion("obtain_any_axes_ruby_axe", hasObtainItem(items, MtsItems.STRONG_RUBY_AXE))
                    .addCriterion("obtain_any_axes_netherite_axe", hasObtainItem(items, Items.NETHERITE_AXE))
                    .addCriterion("obtain_any_axes_amethyst_axe", hasObtainItem(items, MtsItems.STRONG_AMETHYST_AXE))
                    .save(output, generateSaveName("time_for_lumberjacking"));

            // Obtain all tree types in minecraft.
            AdvancementHolder woodCollector = Advancement.Builder.advancement()
                    .parent(timeForLumberjacking)
                    .display(
                            Items.OAK_LOG,
                            Component.translatable("advancements.minecraft-the-stories-mod.wood_collector.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.wood_collector.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("obtain_oak_log", hasObtainItem(items, Items.OAK_LOG))
                    .addCriterion("obtain_dark_oak_log", hasObtainItem(items, Items.DARK_OAK_LOG))
                    .addCriterion("obtain_pale_oak_log", hasObtainItem(items, Items.PALE_OAK_LOG))
                    .addCriterion("obtain_spruce_log", hasObtainItem(items, Items.SPRUCE_LOG))
                    .addCriterion("obtain_birch_log", hasObtainItem(items, Items.BIRCH_LOG))
                    .addCriterion("obtain_jungle_log", hasObtainItem(items, Items.JUNGLE_LOG))
                    .addCriterion("obtain_acacia_log", hasObtainItem(items, Items.ACACIA_LOG))
                    .addCriterion("obtain_mangrove_log", hasObtainItem(items, Items.MANGROVE_LOG))
                    .addCriterion("obtain_cherry_log", hasObtainItem(items, Items.CHERRY_LOG))
                    .save(output, generateSaveName("wood_collector"));

            // Obtain a compressed wood.
            AdvancementHolder compressedWoodPlanks = Advancement.Builder.advancement()
                    .parent(woodCollector)
                    .display(
                            MtsBlocks.COMPRESSED_WOOD_PLANKS,
                            Component.translatable("advancements.minecraft-the-stories-mod.compressed_wood_planks.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.compressed_wood_planks.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("obtain_compressed_wood_planks", hasObtainItem(items, MtsBlocks.COMPRESSED_WOOD_PLANKS))
                    .save(output, generateSaveName("compressed_wood_planks"));

            // Craft a super crafter.
            AdvancementHolder superCrafter = Advancement.Builder.advancement()
                    .parent(compressedWoodPlanks)
                    .display(
                            MtsBlocks.SUPER_CRAFTER_BLOCK,
                            Component.translatable("advancements.minecraft-the-stories-mod.super_crafter.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.super_crafter.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    )
                    .addCriterion("obtain_super_crafter", hasObtainItem(items, MtsBlocks.SUPER_CRAFTER_BLOCK))
                    .save(output, generateSaveName("super_crafter"));

            // Obtain full stack of oak, dark oak, pale oak, spruce, birch, acacia. mangrove, and cherry log.
            AdvancementHolder woodMaster = Advancement.Builder.advancement()
                    .parent(woodCollector)
                    .display(
                            MtsItems.STRONG_AMETHYST_AXE,
                            Component.translatable("advancements.minecraft-the-stories-mod.wood_master.title"),
                            Component.translatable("advancements.minecraft-the-stories-mod.wood_master.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
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
        }

        public Criterion<?> hasObtainFullStackOf(final HolderGetter<Item> currentTime, final ItemLike requiredItem) {
            return this.hasObtainNumItemOf(currentTime, requiredItem, 64);
        }

        public Criterion<?> hasObtainNumItemOf(final HolderGetter<Item> currentItem, final ItemLike requiredItem, final int count) {
            return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(currentItem, requiredItem).withCount(MinMaxBounds.Ints.exactly(count)).build());
        }

        public Criterion<?> specificItemMinesSpecificBlock(final HolderGetter<Item> currentTool, final HolderGetter<Block> currentBlock, final ItemLike requiredItem, final Block targetBlock) {
            return MtsAdvancementTriggers.MINE_BLOCK_WITH_TOOL_TRIGGER.createCriterion(
                    new MineBlockWithToolTrigger.TriggerInstance(
                            Optional.of(HolderSet.direct(currentBlock.getOrThrow(MtsBlocks.getResourceKey(targetBlock)))),
                            Optional.of(ItemPredicate.Builder.item().of(currentTool, requiredItem).build()))
            );
        }

        public Criterion<?> hasObtainItem(final HolderGetter<Item> current, final ItemLike require) {
            return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(current, require));
        }

        /**
         * Returns the path of the advancement in this format:
         * <pre>{@code &lt;MOD_ID&rt;:&lt;MOD_ID&rt;/&lt;ID_OF_ADVANCEMENT&rt;}</pre>
         *
         * @param id of the advancement.
         * @return the path of storing this advancement in .json file.
         */
        public String generateSaveName(final String id) {
            return MinecraftTheStoriesMod.MOD_ID + ":minecraft-the-stories-mod/" + id;
        }
    }
}
