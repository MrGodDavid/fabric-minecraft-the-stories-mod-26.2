package net.mrgoddavid.minecraftthestoriesmod.item.content.story_block;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.mrgoddavid.minecraftthestoriesmod.gui.sprites.widget.StoryBookPageButton;

import java.util.List;

import static net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsScreenTextures.*;

/**
 * @author Mr. GodDavid
 * @since 9/21/2026
 */
public class StoryBookScreen extends AbstractContainerScreen<StoryBookMenu> {

    public static final int BOOK_GUI_Y_OFFSET = 35;
    public static final int IMAGE_WIDTH = 200;
    public static final int IMAGE_HEIGHT = 142;
    private StoryBookPageButton forwardButton;
    private StoryBookPageButton backButton;
    private int currentPage = 10;

    public StoryBookScreen(StoryBookMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 200, 142);
    }

    @Override
    protected void init() {
        super.init();
        this.createDoneButton();
    }

    private void createDoneButton() {
        int left = (this.width - IMAGE_WIDTH) / 2;
        int top = (this.height - IMAGE_HEIGHT) / 2;
        this.forwardButton = this.addRenderableWidget(new StoryBookPageButton(left, top + 65, true, button -> System.out.println("Next Page!"), true));
        this.backButton = this.addRenderableWidget(new StoryBookPageButton(left + 100, top + 65, false, button -> System.out.println("Previous Page!"), true));
        this.updateButtonVisibility();
    }

    private void updateButtonVisibility() {
        this.forwardButton.visible = this.currentPage < this.getNumPages() - 1;
        this.backButton.visible = this.currentPage > 0;
    }

    private int getNumPages() {
        return 100;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x = (this.width - IMAGE_WIDTH) / 2;
        int y = (this.height - IMAGE_HEIGHT) / 2 - BOOK_GUI_Y_OFFSET;
        graphics.blit(RenderPipelines.GUI_TEXTURED, STORY_BOOK_GUI, x, y, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        return;
    }
}
