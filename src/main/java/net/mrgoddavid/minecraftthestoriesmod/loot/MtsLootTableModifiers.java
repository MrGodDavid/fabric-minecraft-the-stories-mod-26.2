package net.mrgoddavid.minecraftthestoriesmod.loot;

import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.vanilla.VanillaPaths;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Modifies mod item's loot tables.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class MtsLootTableModifiers {

    private MtsLootTableModifiers() {
        throw new IllegalAccessError("Utility class");
    }

    public static @Nullable LootTable replaceLootTables(ResourceKey<LootTable> key, LootTable lootTable, LootTableSource source, HolderLookup.Provider provider) {
        MinecraftTheStoriesMod.LOGGER.info("Replacing loot tables of blocks, structures, and mob of Vanilla Minecraft for " + MinecraftTheStoriesMod.MOD_ID);

        if (!source.isBuiltin()) {
            return null;
        }

        if (matches(key, VanillaPaths.ACACIA_LEAVES)) {
            return replaceVanillaBlockLootTable(MtsItems.ACACIA_STICK, 1.0f, 0.02f, 1.0f, 2.0f);
        } else if (matches(key, VanillaPaths.BIRCH_LEAVES)) {
            return replaceVanillaBlockLootTable(MtsItems.BIRCH_STICK, 1.0f, 0.02f, 1.0f, 2.0f);
        } else if (matches(key, VanillaPaths.CHERRY_LEAVES)) {
            return replaceVanillaBlockLootTable(MtsItems.CHERRY_STICK, 1.0f, 0.02f, 1.0f, 2.0f);
        } else if (matches(key, VanillaPaths.DARK_OAK_LEAVES)) {
            return replaceVanillaBlockLootTable(MtsItems.DARK_OAK_STICK, 1.0f, 0.02f, 1.0f, 2.0f);
        } else if (matches(key, VanillaPaths.JUNGLE_LEAVES)) {
            return replaceVanillaBlockLootTable(MtsItems.JUNGLE_STICK, 1.0f, 0.02f, 1.0f, 2.0f);
        } else if (matches(key, VanillaPaths.MANGROVE_LEAVES)) {
            return replaceVanillaBlockLootTable(MtsItems.MANGROVE_STICK, 1.0f, 0.02f, 1.0f, 2.0f);
        } else if (matches(key, VanillaPaths.PALE_OAK_LEAVES)) {
            return replaceVanillaBlockLootTable(MtsItems.PALE_OAK_STICK, 1.0f, 0.02f, 1.0f, 2.0f);
        } else if (matches(key, VanillaPaths.SPRUCE_LEAVES)) {
            return replaceVanillaBlockLootTable(MtsItems.SPRUCE_STICK, 1.0f, 0.02f, 1.0f, 2.0f);
        }
        return null;
    }

    private static @NonNull LootTable replaceVanillaBlockLootTable(final Item newLoot, float maxNumOfItem, float probability, float minimumCountOfDropping, float maximumCountOfDropping) {
        return LootTable.lootTable()
                .pool(LootPool.lootPool()
                        .setRolls(constantOf(maxNumOfItem))
                        .when(chanceOfDroppingIs(probability))
                        .add(lootTableItem(newLoot))
                        .apply(dropCount(minimumCountOfDropping, maximumCountOfDropping))
                        .build()
                ).build();
    }

    private static boolean matches(ResourceKey<LootTable> key, final String targetBlockPath) {
        return key.identifier().equals(Identifier.withDefaultNamespace(targetBlockPath));
    }

    public static void modifyLootTables(ResourceKey<LootTable> key, FabricLootTableBuilder builder, LootTableSource tableSource, HolderLookup.Provider provider) {
        MinecraftTheStoriesMod.LOGGER.info("Modifying loot tables of blocks, structures, and mob of Vanilla Minecraft for " + MinecraftTheStoriesMod.MOD_ID);

        modifiesVanillaBlockLootTable(key, builder, VanillaPaths.DIAMOND_ORE, MtsItems.RAW_STRONG_DIAMOND, 1.0f, 0.1f, 0.0f, 1.0f);
        modifiesVanillaBlockLootTable(key, builder, VanillaPaths.DEEPSLATE_DIAMOND_ORE, MtsItems.RAW_STRONG_DIAMOND, 1.0f, 0.1f, 0.0f, 1.0f);
        modifiesVanillaBlockLootTable(key, builder, VanillaPaths.EMERALD_ORE, MtsItems.RAW_STRONG_EMERALD, 1.0f, 0.1f, 0.0f, 1.0f);
        modifiesVanillaBlockLootTable(key, builder, VanillaPaths.DEEPSLATE_EMERALD_ORE, MtsItems.RAW_STRONG_EMERALD, 1.0f, 0.1f, 0.0f, 1.0f);

        modifiesVanillaStructureLootTable(key, builder, BuiltInLootTables.ANCIENT_CITY, MtsItems.RAW_STRONG_AMETHYST, 1.0f, 0.01f, 1.0f, 5.0f);

        modifiesVanillaMobLootTable(key, builder, VanillaPaths.CREEPER, MtsItems.RAW_STRONG_RUBY, 1.0f, 1.0f, 1.0f, 2.0f);
    }

    private static void modifiesVanillaMobLootTable(ResourceKey<LootTable> key,
                                                    FabricLootTableBuilder builder,
                                                    final String targetMobPath,
                                                    Item addedLoot,
                                                    final float maxNumOfItems,
                                                    final float probability,
                                                    final float minimumCountOfDropping,
                                                    final float maximumCountOfDropping
    ) {
        if (addedLoot != null && key.identifier().equals(Identifier.withDefaultNamespace(targetMobPath))) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(constantOf(maxNumOfItems))
                    .when(chanceOfDroppingIs(probability))
                    .add(lootTableItem(addedLoot))
                    .apply(dropCount(minimumCountOfDropping, maximumCountOfDropping));
            builder.pool(poolBuilder.build());
        }
    }

    private static void modifiesVanillaStructureLootTable(ResourceKey<LootTable> key,
                                                          FabricLootTableBuilder builder,
                                                          ResourceKey<LootTable> targetStructure,
                                                          Item addedLoot,
                                                          final float maxNumOfItems,
                                                          final float probability,
                                                          final float minimumCountOfDropping,
                                                          final float maximumCountOfDropping
    ) {
        // This targets all ancient city chest loot tables!
        if (targetStructure.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(constantOf(maxNumOfItems))
                    .when(chanceOfDroppingIs(probability))
                    .add(lootTableItem(addedLoot))
                    .apply(dropCount(minimumCountOfDropping, maximumCountOfDropping));
            builder.pool(poolBuilder.build());
        }
    }

    private static void modifiesVanillaBlockLootTable(ResourceKey<LootTable> key,
                                                      FabricLootTableBuilder builder,
                                                      final String targetBlockPath,
                                                      Item addedLoot,
                                                      final float maxNumOfItems,
                                                      final float probability,
                                                      final float minimumCountOfDropping,
                                                      final float maximumCountOfDropping
    ) {
        if (addedLoot != null && key.identifier().equals(Identifier.withDefaultNamespace(targetBlockPath))) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(constantOf(maxNumOfItems))
                    .when(chanceOfDroppingIs(probability))
                    .add(lootTableItem(addedLoot))
                    .apply(dropCount(minimumCountOfDropping, maximumCountOfDropping));
            builder.pool(poolBuilder.build());
        }
    }


    private static LootItemFunction dropCount(final float minimumCount, final float maximumCount) {
        return SetItemCountFunction.setCount(UniformGenerator.between(minimumCount, maximumCount)).build();
    }

    private static LootPoolSingletonContainer.@NonNull Builder<?> lootTableItem(final Item item) {
        return LootItem.lootTableItem(item);
    }

    private static LootItemCondition.Builder chanceOfDroppingIs(final float probability) {
        return LootItemRandomChanceCondition.randomChance(probability);
    }

    private static NumberProvider constantOf(final float value) {
        return ConstantValue.exactly(value);
    }
}
