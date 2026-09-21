package net.mrgoddavid.minecraftthestoriesmod.block.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

/**
 * Specific type of block entity screen for this mod. This type of screen does not have a title. The size of screen
 * could be customized if you choose to create the constructor with the image width and image height parameter.
 * <p>User MUST give the location (identifier) of the texture of the screen of this custom block entity of this mod.</p>
 *
 * @author Mr. GodDavid
 * @since 9/14/2026
 */
@SuppressWarnings("NullableProblems")
public abstract class MtsBlockAbstractScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

    private int x;
    private int y;

    public MtsBlockAbstractScreen(T menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    public MtsBlockAbstractScreen(T menu, Inventory inventory, Component title, int imageWidth, int imageHeight) {
        super(menu, inventory, title, imageWidth, imageHeight);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        centeringGui();
        graphics.blit(RenderPipelines.GUI_TEXTURED, registerScreenTexture(), x, y, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        graphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, -12566464, false);
    }

    private void centeringGui() {
        this.x = (this.width - this.imageWidth) / 2;
        this.y = (this.height - this.imageHeight) / 2;
    }

    /**
     * Put the identifier of this block entity's screen here.
     *
     * @return the identifier of the screen texture of this custom block.
     */
    protected abstract Identifier registerScreenTexture();

    protected int x() {
        return this.x;
    }

    protected int y() {
        return this.y;
    }
}
