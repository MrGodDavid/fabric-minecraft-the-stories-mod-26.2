package net.mrgoddavid.minecraftthestoriesmod.block.screen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ender_exalter.EnderExalterScreen;
import net.mrgoddavid.minecraftthestoriesmod.block.content.enricher.EnricherScreen;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor.OreCompressorScreen;
import net.mrgoddavid.minecraftthestoriesmod.block.content.super_crafter.SuperCrafterScreen;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsMenuTypes;

/**
 * Registers screens of menus.
 *
 * @author Mr. GodDavid
 * @since 8/26/2026
 */
public class MtsMenuScreens {

    public static void register() {
        MinecraftTheStoriesMod.LOGGER.info("Registering menus of screens for: " + MinecraftTheStoriesMod.MOD_ID);

        MenuScreens.register(MtsMenuTypes.ENDER_EXALTER_MENU, EnderExalterScreen::new);
        MenuScreens.register(MtsMenuTypes.ENRICHER_MENU, EnricherScreen::new);
        MenuScreens.register(MtsMenuTypes.SUPER_CRAFTER_MENU, SuperCrafterScreen::new);
        MenuScreens.register(MtsMenuTypes.ORE_COMPRESSOR_MENU, OreCompressorScreen::new);
    }

    private MtsMenuScreens() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
