package net.mrgoddavid.minecraftthestoriesmod.gui.sprites.widget;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.mrgoddavid.minecraftthestoriesmod.gui.sprites.MtsWidgetTextures;

/**
 * @author Mr. GodDavid
 * @since 9/21/2026
 */
public class StoryBookPageButton extends Button {

    private static final Component PAGE_BUTTON_NEXT = Component.translatable("book.minecraft-the-stories-mod.page_next");
    private static final Component PAGE_BUTTON_PREVIOUS = Component.translatable("book.minecraft-the-stories-mod.page_previous");
    private final boolean isForward;
    private final boolean playTurnSound;

    public StoryBookPageButton(int x, int y, boolean isForward, Button.OnPress onPress, boolean playTurnSound) {
        super(x, y, 17,9, isForward ? PAGE_BUTTON_NEXT : PAGE_BUTTON_PREVIOUS, onPress, DEFAULT_NARRATION);
        this.isForward = isForward;
        this.playTurnSound = playTurnSound;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        Identifier sprite;
        if (this.isForward) {
            sprite = this.isHoveredOrFocused() ? MtsWidgetTextures.STORY_BOOK_PAGE_FORWARD_HIGHLIGHTED : MtsWidgetTextures.STORY_BOOK_PAGE_FORWARD;
        } else {
            sprite = this.isHoveredOrFocused() ? MtsWidgetTextures.STORY_BOOK_PAGE_BACKWARD_HIGHLIGHTED : MtsWidgetTextures.STORY_BOOK_PAGE_BACKWARD;
        }
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, this.getX(), this.getY(), 17, 9);
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        if (this.playTurnSound) {
            soundManager.play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
        }
    }

    @Override
    public boolean shouldTakeFocusAfterInteraction() {
        return false;
    }
}
