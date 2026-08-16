package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

import static net.mrgoddavid.minecraftthestoriesmod.block.ModBlocks.*;
import static net.mrgoddavid.minecraftthestoriesmod.item.ModItems.*;

/**
 * Defines the loot table of the mod blocks.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {

    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    /**
     * Implement this method to add block drops.
     *
     * <p>Use the range of {@link BlockLootSubProvider#add} methods to generate block drops.
     */
    @Override
    public void generate() {

        dropSelf(RAW_STRONG_EMERALD_BLOCK);
        dropSelf(RAW_STRONG_DIAMOND_BLOCK);
        dropSelf(RAW_STRONG_TOPAZ_BLOCK);
        dropSelf(RAW_STRONG_RUBY_BLOCK);
        dropSelf(RAW_STRONG_AMETHYST_BLOCK);
        dropSelf(STRONG_GOLD_BLOCK);
        dropSelf(STRONG_IRON_BLOCK);
        dropSelf(STRONG_EMERALD_BLOCK);
        dropSelf(STRONG_DIAMOND_BLOCK);
        dropSelf(STRONG_TOPAZ_BLOCK);
        dropSelf(STRONG_RUBY_BLOCK);
        dropSelf(STRONG_AMETHYST_BLOCK);

        dropSelf(SUPER_CRAFTER_BLOCK);

        // ores - custom mod blocks ONLY.
        add(STONE_AMETHYST_ORE, createMultipleOreDrops(STONE_AMETHYST_ORE, RAW_STRONG_AMETHYST, 1.0f, 2.0f));
        add(DEEPSLATE_AMETHYST_ORE, createMultipleOreDrops(DEEPSLATE_TOPAZ_ORE, RAW_STRONG_AMETHYST, 1.0f, 3.0f));
        add(STONE_RUBY_ORE, createMultipleOreDrops(STONE_RUBY_ORE, RAW_STRONG_RUBY, 1.0f, 2.0f));
        add(DEEPSLATE_RUBY_ORE, createMultipleOreDrops(DEEPSLATE_RUBY_ORE, RAW_STRONG_RUBY, 1.0f, 3.0f));
        add(STONE_TOPAZ_ORE, createMultipleOreDrops(STONE_TOPAZ_ORE, RAW_STRONG_TOPAZ, 1.0f, 2.0f));
        add(DEEPSLATE_TOPAZ_ORE, createMultipleOreDrops(DEEPSLATE_AMETHYST_ORE, RAW_STRONG_TOPAZ, 1.0f, 3.0f));
    }

    public LootTable.Builder createMultipleOreDrops(final Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(
                block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }
}
