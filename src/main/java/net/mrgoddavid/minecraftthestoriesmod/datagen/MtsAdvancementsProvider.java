package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.predicates.ItemPredicate;
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
                    .addCriterion("has_wooden_pickaxe", hasObtainTool(items, Items.WOODEN_PICKAXE))
                    .save(output, generateSaveName("root"));

            AdvancementHolder useIronPicMinDiamonds = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            Items.IRON_PICKAXE,
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
                    .addCriterion("make_emerald_pickaxe_with_emeralds", hasObtainTool(items, MtsItems.EMERALD_PICKAXE))
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
                    .addCriterion("make_emerald_axes", hasObtainTool(items, MtsItems.EMERALD_AXE))
                    .addCriterion("make_emerald_hoes", hasObtainTool(items, MtsItems.EMERALD_HOE))
                    .addCriterion("make_emerald_pickaxes", hasObtainTool(items, MtsItems.EMERALD_PICKAXE))
                    .addCriterion("make_emerald_shovels", hasObtainTool(items, MtsItems.EMERALD_SHOVEL))
                    .addCriterion("make_emerald_spears", hasObtainTool(items, MtsItems.EMERALD_SPEAR))
                    .addCriterion("make_emerald_swords", hasObtainTool(items, MtsItems.EMERALD_SWORD))
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
                    .addCriterion("make_emerald_helmet", hasObtainTool(items, MtsItems.EMERALD_HELMET))
                    .addCriterion("make_emerald_chestplate", hasObtainTool(items, MtsItems.EMERALD_CHESTPLATE))
                    .addCriterion("make_emerald_leggings", hasObtainTool(items, MtsItems.EMERALD_LEGGINGS))
                    .addCriterion("make_emerald_boots", hasObtainTool(items, MtsItems.EMERALD_BOOTS))
                    .save(output, generateSaveName("show_off_emerald_armors"));

            AdvancementHolder useEmeraldPicToMineDiamond = Advancement.Builder.advancement()
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
        }

        public Criterion<?> specificItemMinesSpecificBlock(final HolderGetter<Item> currentTool, final HolderGetter<Block> currentBlock, final ItemLike requiredItem, final Block targetBlock) {
            return MtsAdvancementTriggers.MINE_BLOCK_WITH_TOOL_TRIGGER.createCriterion(
                    new MineBlockWithToolTrigger.TriggerInstance(
                            Optional.of(HolderSet.direct(currentBlock.getOrThrow(MtsBlocks.getResourceKey(targetBlock)))),
                            Optional.of(ItemPredicate.Builder.item().of(currentTool, requiredItem).build()))
            );
        }

        public Criterion<?> hasObtainTool(final HolderGetter<Item> current, final ItemLike require) {
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
