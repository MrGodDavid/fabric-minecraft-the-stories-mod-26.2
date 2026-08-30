package net.mrgoddavid.minecraftthestoriesmod.block.content.super_crafter;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import static net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsScreenTextures.*;

/**
 * Screen of Super Crafter.
 *
 * @author Mr. GodDavid
 * @since 8/26/2026
 */
public class SuperCrafterScreen extends AbstractContainerScreen<SuperCrafterMenu> {

    public SuperCrafterScreen(SuperCrafterMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 181);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, SUPER_CRAFTER_GUI, x, y, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        graphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, -12566464, false);
    }
}
