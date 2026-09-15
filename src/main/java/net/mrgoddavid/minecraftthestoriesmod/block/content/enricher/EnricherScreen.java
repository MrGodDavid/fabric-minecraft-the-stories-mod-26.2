package net.mrgoddavid.minecraftthestoriesmod.block.content.enricher;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsAbstractScreen;

import static net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsScreenTextures.*;

/**
 * Screen of Enricher. Nice.
 *
 * @author Mr. GodDavid
 * @since 8/22/2026
 */
public class EnricherScreen extends MtsAbstractScreen<EnricherMenu> {

    public EnricherScreen(EnricherMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        renderProgressArrow(graphics, x(), y());
        renderProgressWasteFluidBar(graphics, x(), y());
    }

    private void renderProgressArrow(GuiGraphicsExtractor graphics, int x, int y) {
        if (menu.isEnriching()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, ENRICHER_PROGRESS_ARROW, x + 76, y + 29, 0, 0, menu.getScaledArrowProgress(), 13, 18, 13);
        }
    }

    private void renderProgressWasteFluidBar(GuiGraphicsExtractor graphics, int x, int y) {
        if (menu.a()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, ENRICHER_WASTE_BAR, x + 12, y + 9 + 53 - menu.getScaledWasteFluidProgress(),
                    0, 53 - menu.getScaledWasteFluidProgress(), 10, menu.getScaledWasteFluidProgress(), 10, 53);
        }
    }

    /**
     * Put the identifier of this block entity's screen here.
     *
     * @return the identifier of the screen texture of this custom block.
     */
    @Override
    protected Identifier registerScreenTexture() {
        return ENRICHER_GUI;
    }
}
