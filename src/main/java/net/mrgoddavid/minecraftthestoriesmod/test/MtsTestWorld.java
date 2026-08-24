package net.mrgoddavid.minecraftthestoriesmod.test;

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.storage.LevelStorageSource;

import java.util.List;

/**
 * MTS Test World.
 *
 * @author Mr. GodDavid
 * @since 8/24/2026
 */
public class MtsTestWorld {

    public static final String WORLD_ID = "mts_test";

    private MtsTestWorld() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!!");
    }

    public static void changeDevTitleScreen() {
        createTestWorldButton();
    }

    public static void open(Minecraft minecraft, Screen parentScreen) {
        LevelStorageSource storage = minecraft.getLevelSource();

        if (storage.levelExists(WORLD_ID)) {
            // load the test world
            openExistingWorld(minecraft, parentScreen);
        } else {
            // create a new world
            createWorld(minecraft, parentScreen);
        }
    }

    private static void openExistingWorld(Minecraft minecraft, Screen parentScreen) {
        minecraft.createWorldOpenFlows().openWorld(WORLD_ID, () -> minecraft.setScreenAndShow(parentScreen));
    }

    private static void createWorld(Minecraft minecraft, Screen parentScreen) {
        minecraft.createWorldOpenFlows().createFreshLevel(
                WORLD_ID, MtsTestWorldContext.levelSettings(), MtsTestWorldContext.worldOptions(), MtsTestWorldContext::worldDimensions, parentScreen);
    }

    private static void createTestWorldButton() {
        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (!(screen instanceof TitleScreen)) {
                return;
            }

            List<AbstractWidget> widgets = Screens.getWidgets(screen);

            AbstractWidget realmsButton = widgets.stream()
                    .filter(widget -> widget instanceof Button)
                    .filter(widget -> {
                        Component message = widget.getMessage();
                        return message.getString().equals("Minecraft Realms");
                    })
                    .findFirst()
                    .orElse(null);
            if (realmsButton == null) {
                return;
            }

            int shiftAmount = 24;
            int realmsY = realmsButton.getY();
            for (AbstractWidget widget : widgets) {
                if (widget instanceof Button button) {
                    if (button.getY() <= realmsY) {
                        button.setY(button.getY() - shiftAmount);
                    }
                }
            }

            Button mtsTestWorldButton = Button.builder(
                    Component.literal("MTS Test"),
                    button -> MtsTestWorld.open(client, screen)
            ).build();

            int buttonWidth = 200;
            int buttonHeight = 20;
            int centeredX = (scaledWidth - buttonWidth) / 2;

            mtsTestWorldButton.setX(centeredX);
            mtsTestWorldButton.setY(realmsY);
            mtsTestWorldButton.setWidth(buttonWidth);
            mtsTestWorldButton.setHeight(buttonHeight);
            Screens.getWidgets(screen).add(mtsTestWorldButton);
        });
    }
}
