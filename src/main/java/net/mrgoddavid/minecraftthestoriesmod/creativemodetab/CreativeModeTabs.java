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
                    .icon(() -> new ItemStack(ModItems.STRONG_DIAMOND))
                    .title(Component.translatable("creative_mode_tab.mts.strong_diamond_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.STRONG_DIAMOND);
                        output.accept(ModItems.RAW_DIAMOND);
                    })
                    .build()
    );

    public static final CreativeModeTab MTS_OVERWORLD_BLOCKS_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB, 
            Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, "mts_overworld_blocks"),
            FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.RAW_DIAMOND))
                    .title(Component.translatable("creative_mode_tab.mts.strong_diamond_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.RAW_DIAMOND);
                    })
                    .build()
    );

    public static void registerModCreativeModeTabs() {
        MinecraftTheStoriesMod.LOGGER.info("Registering Creative Mode Tabs for " + MinecraftTheStoriesMod.MOD_ID);
    }
}
