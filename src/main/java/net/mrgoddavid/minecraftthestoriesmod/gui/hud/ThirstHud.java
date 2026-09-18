package net.mrgoddavid.minecraftthestoriesmod.gui.hud;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.thirst.ThirstClientManager;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class ThirstHud {

    private static final Identifier THIRST_FULL = Constants.modId("textures/gui/sprites/hud/thirst_full.png");
    private static final Identifier THIRST_EMPTY = Constants.modId("textures/gui/sprites/hud/thirst_empty.png");
    private static final Identifier THIRST_HALF = Constants.modId("textures/gui/sprites/hud/thirst_half.png");

    private static final int SIZE = 9;
    private static final int SPACING = 0;

    private ThirstHud() {
    }

    /**
     * Renders the HUD element.
     *
     * @param graphics     the {@link GuiGraphicsExtractor} used for rendering
     * @param deltaTracker the {@link DeltaTracker} providing timing information
     */
    public static void extractRenderState(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        int thirst = ThirstClientManager.getThirst();
        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();
        int startX = screenWidth / 2 + 1 + SIZE;
        int y = screenHeight - 49;

        for (int i = 0; i < 10; i++) {
            int x = startX + i * (SIZE - 1);
            int thirstForIcon = thirst - (9 - i) * 2;
            Identifier texture;
            if (thirstForIcon >= 2) {
                texture = THIRST_FULL;
            } else if (thirstForIcon == 1) {
                texture = THIRST_HALF;
            } else {
                texture = THIRST_EMPTY;
            }

            graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0, 0, SIZE, SIZE, SIZE, SIZE);
        }
    }
}
