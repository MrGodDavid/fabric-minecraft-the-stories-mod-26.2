package net.mrgoddavid.minecraftthestoriesmod.gui.hud;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.thirst.ThirstClientManager;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.RandomPulse;

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

    private static final RandomPulse randomPulse = RandomPulse.builder(100)
            .origin(0).bound(100).probability(5).build();
    private static int counter = 0;
    private static boolean updateOnce = false;

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

        randomPulse.tick();

        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();
        int startX = screenWidth / 2 + 1 + SIZE;
        int standardY = screenHeight - 49;

        boolean pulseActive = !randomPulse.isLock();
        if (pulseActive) {
            if (!updateOnce) {
                counter++;
                if (counter >= 2) {
                    counter = 0;
                }
                updateOnce = true;
            }
        } else {
            updateOnce = false;
        }

        for (int i = 0; i < 10; i++) {
            int x = startX + i * (SIZE - 1);
            int thirstForIcon = thirst - (9 - i) * 2;
            Identifier texture;
            int color;

            if (thirstForIcon >= 2) {
                texture = THIRST_FULL;
                color = 0xFFFFFFFF;
            } else if (thirstForIcon == 1) {
                texture = THIRST_HALF;
                color = 0xFFFF67FF; // <--- You love to see it ;) Boring joke, bruh...
            } else {
                texture = THIRST_EMPTY;
                color = 0xFFFF00FF;
            }

            int y = standardY;
            if (pulseActive) {
                boolean shouldAnimate = (counter % 2 == 0 && i % 2 == 0) || (counter % 2 == 1 && i % 2 == 1);
                if (shouldAnimate) {
                    y = updateYPosition(standardY, thirstForIcon);
                }
            }

            graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0, 0, SIZE, SIZE, SIZE, SIZE, color);
        }
    }

    private static int updateYPosition(int standardY, int thirstForIcon) {
        int y = standardY;
        int animationTick = randomPulse.getTick();
        final int animationCycle = randomPulse.cycle();
        if (thirstForIcon <= 0) {
            if (animationTick >= animationCycle / 4 && animationTick < animationCycle / 2) {
                y++;
            } else if (animationTick >= animationCycle * 3 / 4 && animationTick < animationCycle) {
                y--;
            }
        }
        return y;
    }
}
