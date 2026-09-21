package net.mrgoddavid.minecraftthestoriesmod.general;

import net.minecraft.client.gui.screens.MenuScreens;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ender_exalter.EnderExalterScreen;
import net.mrgoddavid.minecraftthestoriesmod.block.content.enricher.EnricherScreen;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor.OreCompressorScreen;
import net.mrgoddavid.minecraftthestoriesmod.block.content.squeezer.SqueezerScreen;
import net.mrgoddavid.minecraftthestoriesmod.block.content.super_crafter.SuperCrafterScreen;
import net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.StoryBookScreen;
import net.mrgoddavid.minecraftthestoriesmod.utils.MtsLogger;

/**
 * Registers screens of menus.
 *
 * @author Mr. GodDavid
 * @since 8/26/2026
 */
public final class MtsMenuScreens {

    public static void registerBlockScreens() {
        MtsLogger.info("Block Screens For Menus");

        MenuScreens.register(MtsMenuTypes.ENDER_EXALTER_MENU, EnderExalterScreen::new);
        MenuScreens.register(MtsMenuTypes.ENRICHER_MENU, EnricherScreen::new);
        MenuScreens.register(MtsMenuTypes.SUPER_CRAFTER_MENU, SuperCrafterScreen::new);
        MenuScreens.register(MtsMenuTypes.ORE_COMPRESSOR_MENU, OreCompressorScreen::new);
        MenuScreens.register(MtsMenuTypes.SQUEEZER_MENU, SqueezerScreen::new);
    }

    public static void registerItemScreens() {
        MtsLogger.info("Item Screens For Menus");

        MenuScreens.register(MtsMenuTypes.STORY_BOOK_MENU, StoryBookScreen::new);
    }

    private MtsMenuScreens() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
