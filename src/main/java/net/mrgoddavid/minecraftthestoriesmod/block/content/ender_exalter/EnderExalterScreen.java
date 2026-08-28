package net.mrgoddavid.minecraftthestoriesmod.block.content.ender_exalter;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

/**
 * A screen class just draws each individual elements (such as slots, inventory, etc.) on screen.
 * It has no functionality. IT IS CLIENT-SIDE only!
 *
 * @author Mr. GodDavid
 * @since 8/21/2026
 */
public class EnderExalterScreen extends AbstractContainerScreen<EnderExalterMenu> {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID,
            "textures/gui/menu/ender_exalter/ender_exalter_gui.png");

    public EnderExalterScreen(EnderExalterMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int centeredX = (this.width - this.imageWidth) / 2;
        int centeredY = (this.height - this.imageHeight) / 2;

        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, centeredX, centeredY, 0, 0, imageWidth, imageHeight, 256, 256);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, -12566464, false);
    }
}
