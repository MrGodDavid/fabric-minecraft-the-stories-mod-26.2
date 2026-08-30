package net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsScreenTextures;

/**
 * Screen of Enricher.
 *
 * @author Mr. GodDavid
 * @since 8/22/2026
 */
public class OreCompressorScreen extends AbstractContainerScreen<OreCompressorMenu> {

    public OreCompressorScreen(OreCompressorMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 189);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, MtsScreenTextures.ORE_COMPRESSOR_GUI, x, y, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
        drawArrowProgress(graphics, x, y);
        drawRemainingFuelProgress(graphics, x, y);
    }

    private void drawRemainingFuelProgress(GuiGraphicsExtractor graphics, int x, int y) {
        if (menu.a()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, MtsScreenTextures.ORE_COMPRESSOR_BLUE_FUEL_BAR, x + 25, y + 12 + (70 - menu.getScaledFuelProgress()),
                    0, (70 - menu.getScaledFuelProgress()), 16, menu.getScaledFuelProgress(), 16, 70);
        }
    }

    private void drawArrowProgress(GuiGraphicsExtractor graphics, int x, int y) {
        if (menu.isCompressing()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, MtsScreenTextures.ORE_COMPRESSOR_PROGRESS_ARROW, x + 87, y + 44, 0, 0, menu.getScaledArrowProgress(), 13, 27, 13);
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        drawUpperPressPlate(graphics, x, y);
        drawBottomPressPlaye(graphics, x, y);
    }

    private void drawBottomPressPlaye(GuiGraphicsExtractor graphics, int x, int y) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, MtsScreenTextures.ORE_COMPRESSOR_BOTTOM_PRESS_PLATE, x + 62, y + 65 + (8 - menu.getScaledPressPlateProgress()),
                0, 8 - menu.getScaledPressPlateProgress(), 16, menu.getScaledPressPlateProgress(), 16, 8);
    }

    private void drawUpperPressPlate(GuiGraphicsExtractor graphics, int x, int y) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, MtsScreenTextures.ORE_COMPRESSOR_TOP_PRESS_PLATE, x + 62, y + 57,
                0, 8 - menu.getScaledPressPlateProgress(), 16, menu.getScaledPressPlateProgress(), 16, 8);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        graphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, -12566464, false);
    }
}
