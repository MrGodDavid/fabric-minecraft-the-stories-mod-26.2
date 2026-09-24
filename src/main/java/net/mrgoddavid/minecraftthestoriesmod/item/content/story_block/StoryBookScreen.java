package net.mrgoddavid.minecraftthestoriesmod.item.content.story_block;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Inventory;
import net.mrgoddavid.minecraftthestoriesmod.gui.sprites.widget.StoryBookPageButton;
import net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.page.StoryBookPage;
import net.mrgoddavid.minecraftthestoriesmod.item.content.story_block.page.StoryBookPages;

import java.util.ArrayList;
import java.util.List;

import static net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsScreenTextures.*;

/**
 * @author Mr. GodDavid
 * @since 9/21/2026
 */
public class StoryBookScreen extends AbstractContainerScreen<StoryBookMenu> {

    private static final int PAGE_NUMBER_Y = -20;
    private static final int BOOK_GUI_Y_OFFSET = 35;
    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 142;
    private static final int DONE_BUTTON_WIDTH = 200;
    private static final int LEFT_PAGE_X = 14;
    private static final int LEFT_PAGE_Y = 14;
    private static final int RIGHT_PAGE_X = 104;
    private static final int RIGHT_PAGE_Y = 14;
    private static final Style PAGE_NUM_STYLE = Style.EMPTY.withoutShadow().withColor(0xFF000000);

    private List<StoryBookPage> unlockedPages = new ArrayList<>();
    private StoryBookPageButton forwardButton;
    private StoryBookPageButton backButton;
    private int currentSpread = 0;

    public StoryBookScreen(StoryBookMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    @Override
    protected void init() {
        super.init();
        this.unlockedPages = StoryBookPages.getUnlockedPages(this.minecraft.player);
        this.createPageButtons();
        this.createDoneButton();
    }

    private StoryBookPage getLeftPage() {
        int index = this.currentSpread * 2;
        if (index >= this.unlockedPages.size()) {
            return null;
        }
        return this.unlockedPages.get(index);
    }

    private StoryBookPage getRightPage() {
        int index = this.currentSpread * 2 + 1;
        if (index >= this.unlockedPages.size()) {
            return null;
        }
        return this.unlockedPages.get(index);
    }

    private int getNumSpreads() {
        return (this.unlockedPages.size() + 1) / 2;
    }

    private void createDoneButton() {
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button -> this.onClose()).pos((this.width - DONE_BUTTON_WIDTH) / 2, getBackgroundTop() + IMAGE_HEIGHT).width(DONE_BUTTON_WIDTH).build());
    }

    private void createPageButtons() {
        int left = getBackgroundLeft();
        int top = getBackgroundTop();
        this.forwardButton = super.addRenderableWidget(new StoryBookPageButton(left + 105, top + 122 - BOOK_GUI_Y_OFFSET, true, this::processForwardButton, true));
        this.backButton = super.addRenderableWidget(new StoryBookPageButton(left + 78, top + 122 - BOOK_GUI_Y_OFFSET, false, this::processBackwardButton, true));
        this.updateButtonVisibility();
    }

    private void processBackwardButton(Button button) {
        if (this.currentSpread > 0) {
            this.currentSpread--;
        }
        this.updateButtonVisibility();
    }

    private void processForwardButton(Button button) {
        if (this.currentSpread < 100) {
            this.currentSpread++;
        }
        this.updateButtonVisibility();
    }

    private int getBackgroundTop() {
        return (this.height - IMAGE_HEIGHT) / 2;
    }

    private int getBackgroundLeft() {
        return (this.width - IMAGE_WIDTH) / 2;
    }

    private void updateButtonVisibility() {
        this.forwardButton.visible = this.currentSpread < this.getNumPages() - 1;
        this.backButton.visible = this.currentSpread > 0;
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
        this.extractPages(graphics, x, y);
    }

    private void extractPages(GuiGraphicsExtractor graphics, int x, int y) {
        StoryBookPage leftPage = getLeftPage();
        StoryBookPage rightPage = getRightPage();
        if (leftPage != null) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, leftPage.texture(), x + LEFT_PAGE_X, y + LEFT_PAGE_Y, 0, 0, 82, 110, 128, 128);
        }
        if (rightPage != null) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, rightPage.texture(), x + RIGHT_PAGE_X, y + RIGHT_PAGE_Y, 0, 0, 82, 110, 128, 128);
        }
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        if (currentSpread > 0) {
            graphics.text(font, getLeftPageNumber().getVisualOrderText(), 16, PAGE_NUMBER_Y, 0xFFFFFFFF);
        }
        graphics.text(font, getRightPageNumber().getVisualOrderText(), 176, PAGE_NUMBER_Y, 0xFFFFFFFF);
    }

    private Component getLeftPageNumber() {
        return Component.translatable("book.minecraft-the-stories-mod.pageIndicator", currentSpread * 2).withStyle(PAGE_NUM_STYLE);
    }

    private Component getRightPageNumber() {
        return Component.translatable("book.minecraft-the-stories-mod.pageIndicator", currentSpread * 2 + 1).withStyle(PAGE_NUM_STYLE);
    }
}
