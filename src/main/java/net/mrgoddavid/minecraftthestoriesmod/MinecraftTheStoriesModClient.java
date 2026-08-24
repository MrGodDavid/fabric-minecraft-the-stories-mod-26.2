package net.mrgoddavid.minecraftthestoriesmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.mrgoddavid.minecraftthestoriesmod.block.custom.ender_exalter.EnderExalterBlockRenderer;
import net.mrgoddavid.minecraftthestoriesmod.block.custom.ender_exalter.EnderExalterScreen;
import net.mrgoddavid.minecraftthestoriesmod.block.custom.enricher.EnricherScreen;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsMenuTypes;
import net.mrgoddavid.minecraftthestoriesmod.test.MtsTestWorld;
import net.mrgoddavid.minecraftthestoriesmod.tooltip.MtsItemTooltips;

public class MinecraftTheStoriesModClient implements ClientModInitializer {

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {

        MtsTestWorld.changeDevTitleScreen();

        BlockEntityRenderers.register(MtsBlockEntities.ENDER_EXALTER_BE, EnderExalterBlockRenderer::new);

        MenuScreens.register(MtsMenuTypes.ENDER_EXALTER_MENU, EnderExalterScreen::new);
        MenuScreens.register(MtsMenuTypes.ENRICHER_MENU, EnricherScreen::new);

        MtsItemTooltips.register();

        ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipFlag, lines) -> {
            if (stack.is(Items.DIAMOND_SWORD) || stack.is(Items.NETHERITE_SWORD)) {
                lines.add(Component.literal("A weapon forged in ancient times").withStyle(ChatFormatting.GOLD));
            }
        });
    }
}
