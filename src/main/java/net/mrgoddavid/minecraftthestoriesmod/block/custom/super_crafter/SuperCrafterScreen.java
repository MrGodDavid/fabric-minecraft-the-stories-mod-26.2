package net.mrgoddavid.minecraftthestoriesmod.block.custom.super_crafter;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

public class SuperCrafterScreen extends AbstractContainerScreen<SuperCrafterMenu> {

    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID,
            "textures/gui/menu/super_crafter/super_crafter_gui.png");

    public SuperCrafterScreen(SuperCrafterMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 181);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        graphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, -12566464, false);
    }
}
