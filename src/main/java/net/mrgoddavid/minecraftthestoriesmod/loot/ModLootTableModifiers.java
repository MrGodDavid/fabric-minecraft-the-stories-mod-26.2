package net.mrgoddavid.minecraftthestoriesmod.loot;

import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
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
import net.mrgoddavid.minecraftthestoriesmod.item.ModItems;
import net.mrgoddavid.minecraftthestoriesmod.vanilla.VanillaPaths;
import org.jspecify.annotations.NonNull;

/**
 * Modifies mod item's loot tables.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class ModLootTableModifiers {

    public static void modifyLootTables(ResourceKey<LootTable> key, FabricLootTableBuilder builder,
                                        LootTableSource tableSource, HolderLookup.Provider provider) {
        if (key.identifier().equals(Identifier.withDefaultNamespace(VanillaPaths.DIAMOND_ORE))
                || key.identifier().equals(Identifier.withDefaultNamespace(VanillaPaths.DEEPSLATE_DIAMOND_ORE))) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(constantOf(1.0f))
                    .when(chanceOfDroppingIs(1.0f))
                    .add(lootTableItem(ModItems.STRONG_DIAMOND))
                    .apply(dropCount(1.0f, 2.0f));
            builder.pool(poolBuilder.build());
        }

        // This targets all ancient city chest loot tables!
        if (BuiltInLootTables.ANCIENT_CITY.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(constantOf(1.0f))
                    .when(chanceOfDroppingIs(1.0f))
                    .add(lootTableItem(ModItems.STRONG_AMETHYST))
                    .apply(dropCount(1.0f, 2.0f));
            builder.pool(poolBuilder.build());
        }

        // This targets creeper's loot table.
        if (key.identifier().equals(Identifier.withDefaultNamespace(VanillaPaths.CREEPER))) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(constantOf(1.0f))
                    .when(chanceOfDroppingIs(1.0f))
                    .add(lootTableItem(ModItems.RAW_RUBY))
                    .apply(dropCount(2.0f, 10.0f));
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
