package net.mrgoddavid.minecraftthestoriesmod.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;

import static net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks.*;
import static net.mrgoddavid.minecraftthestoriesmod.item.MtsItems.*;

/**
 * Custom creative mode tabs.
 *
 * @author Mr. GodDavid
 * @since 8/12/2026
 */
public class CreativeModeTabs {

    private CreativeModeTabs() throws IllegalAccessException {
        throw new IllegalAccessException("You can't instantiate CreativeModeTabs class!");
    }

    public static final CreativeModeTab MTS_OVERWORLD_ITEMS_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, "mts_overworld_items"),
            FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(MtsItems.STRONG_AMETHYST))
                    .title(Component.translatable("creative_mode_tab.mts.strong_diamond_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(RAW_STRONG_AMETHYST);
                        output.accept(STRONG_AMETHYST);
                        output.accept(STRONG_AMETHYST_INGOT);
                        output.accept(RAW_STRONG_DIAMOND);
                        output.accept(STRONG_DIAMOND);
                        output.accept(STRONG_DIAMOND_INGOT);
                        output.accept(RAW_STRONG_EMERALD);
                        output.accept(STRONG_EMERALD);
                        output.accept(STRONG_EMERALD_INGOT);
                        output.accept(RAW_STRONG_RUBY);
                        output.accept(STRONG_RUBY);
                        output.accept(STRONG_RUBY_INGOT);
                        output.accept(RAW_STRONG_TOPAZ);
                        output.accept(STRONG_TOPAZ);
                        output.accept(STRONG_TOPAZ_INGOT);
                        output.accept(STRONG_IRON);
                        output.accept(STRONG_IRON_INGOT);
                        output.accept(STRONG_GOLD);
                        output.accept(STRONG_GOLD_INGOT);

                        output.accept(STRONG_AMETHYST_AXE);
                        output.accept(STRONG_AMETHYST_HOE);
                        output.accept(STRONG_AMETHYST_PICKAXE);
                        output.accept(STRONG_AMETHYST_SHOVEL);
                        output.accept(STRONG_AMETHYST_SPEAR);
                        output.accept(STRONG_AMETHYST_SWORD);
                        output.accept(EMERALD_AXE);
                        output.accept(EMERALD_HOE);
                        output.accept(EMERALD_PICKAXE);
                        output.accept(EMERALD_SHOVEL);
                        output.accept(EMERALD_SPEAR);
                        output.accept(EMERALD_SWORD);
                        output.accept(STRONG_RUBY_AXE);
                        output.accept(STRONG_RUBY_HOE);
                        output.accept(STRONG_RUBY_PICKAXE);
                        output.accept(STRONG_RUBY_SHOVEL);
                        output.accept(STRONG_RUBY_SPEAR);
                        output.accept(STRONG_RUBY_SWORD);
                        output.accept(STRONG_TOPAZ_AXE);
                        output.accept(STRONG_TOPAZ_HOE);
                        output.accept(STRONG_TOPAZ_PICKAXE);
                        output.accept(STRONG_TOPAZ_SHOVEL);
                        output.accept(STRONG_TOPAZ_SPEAR);
                        output.accept(STRONG_TOPAZ_SWORD);

                        output.accept(DIAMOND_BATTLE_AXE);

                        output.accept(EMERALD_BOOTS);
                        output.accept(EMERALD_HELMET);
                        output.accept(EMERALD_CHESTPLATE);
                        output.accept(EMERALD_LEGGINGS);
                        output.accept(STRONG_TOPAZ_BOOTS);
                        output.accept(STRONG_TOPAZ_HELMET);
                        output.accept(STRONG_TOPAZ_CHESTPLATE);
                        output.accept(STRONG_TOPAZ_LEGGINGS);
                        output.accept(STRONG_RUBY_BOOTS);
                        output.accept(STRONG_RUBY_HELMET);
                        output.accept(STRONG_RUBY_CHESTPLATE);
                        output.accept(STRONG_RUBY_LEGGINGS);
                        output.accept(STRONG_AMETHYST_BOOTS);
                        output.accept(STRONG_AMETHYST_HELMET);
                        output.accept(STRONG_AMETHYST_CHESTPLATE);
                        output.accept(STRONG_AMETHYST_LEGGINGS);

                        output.accept(RARE_UPGRADE_SCROLL_STAGE_ONE);
                        output.accept(RARE_UPGRADE_SCROLL_STAGE_TWO);
                        output.accept(RARE_UPGRADE_SCROLL_STAGE_THREE);
                        output.accept(EPIC_UPGRADE_SCROLL_STAGE_ONE);
                        output.accept(EPIC_UPGRADE_SCROLL_STAGE_TWO);
                        output.accept(EPIC_UPGRADE_SCROLL_STAGE_THREE);
                        output.accept(EPIC_UPGRADE_SCROLL_STAGE_FOUR);
                        output.accept(LEGENDARY_UPGRADE_SCROLL_STAGE_ONE);
                        output.accept(LEGENDARY_UPGRADE_SCROLL_STAGE_TWO);
                        output.accept(LEGENDARY_UPGRADE_SCROLL_STAGE_THREE);
                        output.accept(LEGENDARY_UPGRADE_SCROLL_STAGE_FOUR);
                        output.accept(LEGENDARY_UPGRADE_SCROLL_STAGE_FIVE);

                        output.accept(ENRICHER_WASTE_BUCKET);
                        output.accept(BLUE_FUEL_BUCKET);
                    })
                    .build()
    );

    public static final CreativeModeTab MTS_OVERWORLD_BLOCKS_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, "mts_overworld_blocks"),
            FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(STONE_AMETHYST_ORE))
                    .title(Component.translatable("creative_mode_tab.mts.strong_diamond_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(DEEPSLATE_AMETHYST_ORE);
                        output.accept(DEEPSLATE_RUBY_ORE);
                        output.accept(DEEPSLATE_TOPAZ_ORE);
                        output.accept(STONE_AMETHYST_ORE);
                        output.accept(STONE_RUBY_ORE);
                        output.accept(STONE_TOPAZ_ORE);
                        output.accept(RAW_STRONG_AMETHYST_BLOCK);
                        output.accept(RAW_STRONG_DIAMOND_BLOCK);
                        output.accept(RAW_STRONG_EMERALD_BLOCK);
                        output.accept(RAW_STRONG_RUBY_BLOCK);
                        output.accept(RAW_STRONG_TOPAZ_BLOCK);
                        output.accept(STRONG_AMETHYST_BLOCK);
                        output.accept(STRONG_DIAMOND_BLOCK);
                        output.accept(STRONG_EMERALD_BLOCK);
                        output.accept(STRONG_RUBY_BLOCK);
                        output.accept(STRONG_TOPAZ_BLOCK);
                        output.accept(STRONG_IRON_BLOCK);
                        output.accept(STRONG_GOLD_BLOCK);

                        output.accept(COMPRESSED_WOOD);
                        output.accept(COMPRESSED_WOOD_LOG);
                        output.accept(COMPRESSED_WOOD_PLANKS);
                        output.accept(STRIPPED_COMPRESSED_WOOD);
                        output.accept(STRIPPED_COMPRESSED_WOOD_LOG);
                        output.accept(COMPRESSED_WOOD_FENCE);
                        output.accept(COMPRESSED_WOOD_FENCE_GATE);

                        output.accept(STRONG_AMETHYST_FENCE);
                        output.accept(STRONG_AMETHYST_FENCE_GATE);
                        output.accept(STRONG_AMETHYST_WALL);
                        output.accept(RAW_STRONG_AMETHYST_WALL);

                        output.accept(SUPER_CRAFTER_BLOCK);
                        output.accept(ENRICHER);
                        output.accept(ENDER_EXALTER);
                        output.accept(ORE_COMPRESSOR);
                    })
                    .build()
    );

    public static void registerModCreativeModeTabs() {
        MinecraftTheStoriesMod.LOGGER.info("Registering Creative Mode Tabs for " + MinecraftTheStoriesMod.MOD_ID);
    }
}
