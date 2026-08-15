package net.mrgoddavid.minecraftthestoriesmod.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.item.ModItems;

import static net.mrgoddavid.minecraftthestoriesmod.block.ModBlocks.*;
import static net.mrgoddavid.minecraftthestoriesmod.item.ModItems.*;

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
                    .icon(() -> new ItemStack(ModItems.STRONG_AMETHYST))
                    .title(Component.translatable("creative_mode_tab.mts.strong_diamond_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(RAW_AMETHYST);
                        output.accept(STRONG_AMETHYST);
                        output.accept(STRONG_AMETHYST_INGOT);
                        output.accept(RAW_DIAMOND);
                        output.accept(STRONG_DIAMOND);
                        output.accept(STRONG_DIAMOND_INGOT);
                        output.accept(RAW_EMERALD);
                        output.accept(STRONG_EMERALD);
                        output.accept(STRONG_EMERALD_INGOT);
                        output.accept(RAW_RUBY);
                        output.accept(STRONG_RUBY);
                        output.accept(STRONG_RUBY_INGOT);
                        output.accept(RAW_TOPAZ);
                        output.accept(STRONG_TOPAZ);
                        output.accept(STRONG_TOPAZ_INGOT);
                        output.accept(STRONG_IRON);
                        output.accept(STRONG_IRON_INGOT);
                        output.accept(STRONG_GOLD);
                        output.accept(STRONG_GOLD_INGOT);

                        output.accept(STRONG_AMETHYST_AXE);
                        output.accept(AMETHYST_HOE);
                        output.accept(AMETHYST_PICKAXE);
                        output.accept(AMETHYST_SHOVEL);
                        output.accept(AMETHYST_SPEAR);
                        output.accept(AMETHYST_SWORD);
                        output.accept(EMERALD_AXE);
                        output.accept(EMERALD_HOE);
                        output.accept(EMERALD_PICKAXE);
                        output.accept(EMERALD_SHOVEL);
                        output.accept(EMERALD_SPEAR);
                        output.accept(EMERALD_SWORD);
                        output.accept(RUBY_AXE);
                        output.accept(RUBY_HOE);
                        output.accept(RUBY_PICKAXE);
                        output.accept(RUBY_SHOVEL);
                        output.accept(RUBY_SPEAR);
                        output.accept(RUBY_SWORD);
                        output.accept(TOPAZ_AXE);
                        output.accept(TOPAZ_HOE);
                        output.accept(TOPAZ_PICKAXE);
                        output.accept(TOPAZ_SHOVEL);
                        output.accept(TOPAZ_SPEAR);
                        output.accept(TOPAZ_SWORD);
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
                        output.accept(RAW_AMETHYST_BLOCK);
                        output.accept(RAW_DIAMOND_BLOCK);
                        output.accept(RAW_EMERALD_BLOCK);
                        output.accept(RAW_RUBY_BLOCK);
                        output.accept(RAW_TOPAZ_BLOCK);
                        output.accept(STRONG_AMETHYST_BLOCK);
                        output.accept(STRONG_DIAMOND_BLOCK);
                        output.accept(STRONG_EMERALD_BLOCK);
                        output.accept(STRONG_RUBY_BLOCK);
                        output.accept(STRONG_TOPAZ_BLOCK);
                        output.accept(STRONG_IRON_BLOCK);
                        output.accept(STRONG_GOLD_BLOCK);
                    })
                    .build()
    );

    public static void registerModCreativeModeTabs() {
        MinecraftTheStoriesMod.LOGGER.info("Registering Creative Mode Tabs for " + MinecraftTheStoriesMod.MOD_ID);
    }
}
