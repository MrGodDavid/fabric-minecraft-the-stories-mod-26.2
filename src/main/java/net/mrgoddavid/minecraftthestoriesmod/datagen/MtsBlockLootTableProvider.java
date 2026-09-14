package net.mrgoddavid.minecraftthestoriesmod.datagen;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditions;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.block.content.crops.StrawberryCropBlock;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks.*;
import static net.mrgoddavid.minecraftthestoriesmod.item.MtsItems.*;

/**
 * Defines the loot table of the mod blocks.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class MtsBlockLootTableProvider extends FabricBlockLootSubProvider {

    public MtsBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
        MinecraftTheStoriesMod.LOGGER.info("Providing data of MTS Blook LootTable for: " + MinecraftTheStoriesMod.MOD_ID);
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

        dropSelf(COMPRESSED_WOOD_LOG);
        dropSelf(COMPRESSED_WOOD_FENCE);
        dropSelf(COMPRESSED_WOOD_FENCE_GATE);
        dropSelf(COMPRESSED_WOOD);
        dropSelf(COMPRESSED_WOOD_PLANKS);
        dropSelf(STRIPPED_COMPRESSED_WOOD_LOG);
        dropSelf(STRIPPED_COMPRESSED_WOOD);

        dropSelf(STRONG_AMETHYST_FENCE);
        dropSelf(STRONG_AMETHYST_FENCE_GATE);
        dropSelf(STRONG_AMETHYST_WALL);

        dropWhenSilkTouch(SUPER_CRAFTER_BLOCK);
        dropSelf(ENRICHER);
        dropSelf(ENDER_EXALTER);
        dropSelf(ORE_COMPRESSOR);

        // ores - custom mod blocks ONLY.
        add(STONE_AMETHYST_ORE, createMultipleOreDrops(STONE_AMETHYST_ORE, RAW_STRONG_AMETHYST, 1.0f, 2.0f));
        add(DEEPSLATE_AMETHYST_ORE, createMultipleOreDrops(DEEPSLATE_TOPAZ_ORE, RAW_STRONG_AMETHYST, 1.0f, 3.0f));
        add(STONE_RUBY_ORE, createMultipleOreDrops(STONE_RUBY_ORE, RAW_STRONG_RUBY, 1.0f, 2.0f));
        add(DEEPSLATE_RUBY_ORE, createMultipleOreDrops(DEEPSLATE_RUBY_ORE, RAW_STRONG_RUBY, 1.0f, 3.0f));
        add(STONE_TOPAZ_ORE, createMultipleOreDrops(STONE_TOPAZ_ORE, RAW_STRONG_TOPAZ, 1.0f, 2.0f));
        add(DEEPSLATE_TOPAZ_ORE, createMultipleOreDrops(DEEPSLATE_AMETHYST_ORE, RAW_STRONG_TOPAZ, 1.0f, 3.0f));

        add(NETHER_STRONG_RUBY_ORE, createMultipleOreDrops(NETHER_STRONG_RUBY_ORE, RAW_STRONG_TOPAZ, 1.0f, 4.0f));

        add(END_STRONG_AMETHYST_ORE, createMultipleOreDrops(END_STRONG_AMETHYST_ORE, RAW_STRONG_AMETHYST, 1.0f, 4.0f));

        this.createCropDrops(STRAWBERRY_CROP, this.defineStrawberryDropsRules());
    }

    private ImmutableMap<LootItemCondition.Builder, Item> defineStrawberryDropsRules() {
        LootItemCondition.Builder rawStrawberryDropsCondition =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(STRAWBERRY_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, StrawberryCropBlock.RAW_STRAWBERRY_AGE));
        LootItemCondition.Builder strawberryDropsCondition =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(STRAWBERRY_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, StrawberryCropBlock.MAX_AGE));
        LootItemCondition.Builder strawberrySeedsDropsCondition =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(STRAWBERRY_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, Constants.Universal.NEW_BORN));

        return ImmutableMap.of(
                rawStrawberryDropsCondition, RAW_STRAWBERRY,
                strawberryDropsCondition, STRAWBERRY,
                strawberrySeedsDropsCondition, STRAWBERRY_SEEDS
        );

    }

    private void createCropDrops(Block cropBlock, ImmutableMap<LootItemCondition.Builder, Item> lootCropMap) {
        LootTable.Builder cropDropsBuilder = LootTable.lootTable();
        for (ImmutableMap.Entry<LootItemCondition.Builder, Item> entry : lootCropMap.entrySet()) {
            Item item = entry.getValue();
            if (item != null) {
                cropDropsBuilder.withPool(LootPool.lootPool()
                        .when(entry.getKey())
                        .add(LootItem.lootTableItem(item)));
            }
        }
        this.add(cropBlock, cropDropsBuilder);
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
