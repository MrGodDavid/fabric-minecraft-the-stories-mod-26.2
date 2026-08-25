package net.mrgoddavid.minecraftthestoriesmod.block.custom.enricher;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

/**
 * Screen of Enricher. Nice.
 *
 * @author Mr. GodDavid
 * @since 8/22/2026
 */
public class EnricherScreen extends AbstractContainerScreen<EnricherMenu> {

    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath(
            MinecraftTheStoriesMod.MOD_ID, "textures/gui/menu/enricher/enricher_gui.png"
    );
    private static final Identifier ARROW_TEXTURE = Identifier.fromNamespaceAndPath(
            MinecraftTheStoriesMod.MOD_ID, "textures/gui/menu/enricher/enricher_progress_bar.png"
    );
    private static final Identifier WASTE_FLUID_BAR_TEXTURE = Identifier.fromNamespaceAndPath(
            MinecraftTheStoriesMod.MOD_ID, "textures/gui/menu/enricher/enricher_waste_liquid.png"
    );

    public EnricherScreen(EnricherMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int centeredX = (width - imageWidth) / 2;
        int centeredY = (height - imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, centeredX, centeredY, 0, 0, imageWidth, imageHeight, 256, 256);
        renderProgressArrow(graphics, centeredX, centeredY);
        renderProgressWasteFluidBar(graphics, centeredX, centeredY);
    }

    private void renderProgressArrow(GuiGraphicsExtractor graphics, int x, int y) {
        if (menu.isEnriching()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 76, y + 29, 0, 0, menu.getScaledArrowProgress(), 13, 18, 13);
        }
    }

    private void renderProgressWasteFluidBar(GuiGraphicsExtractor graphics, int x, int y) {
        if (menu.a()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, WASTE_FLUID_BAR_TEXTURE, x + 12, y + 9 + 53 - menu.getScaledWasteFluidProgress(),
                    0, 53 - menu.getScaledWasteFluidProgress(), 10, menu.getScaledWasteFluidProgress(), 10, 53);
        }
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        graphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, -12566464, false);
    }
}
