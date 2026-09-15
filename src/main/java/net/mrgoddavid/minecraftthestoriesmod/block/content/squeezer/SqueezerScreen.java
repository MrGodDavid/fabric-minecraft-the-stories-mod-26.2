package net.mrgoddavid.minecraftthestoriesmod.block.content.squeezer;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsAbstractScreen;
import net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsScreenTextures;

/**
 * @author Mr. GodDavid
 * @since 9/14/2026
 */
public class SqueezerScreen extends MtsAbstractScreen<SqueezerMenu> {

    public SqueezerScreen(SqueezerMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 209);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
    }

    /**
     * Put the identifier of this block entity's screen here.
     *
     * @return the identifier of the screen texture of this custom block.
     */
    @Override
    protected Identifier registerScreenTexture() {
        return MtsScreenTextures.SQUEEZER_GUI;
    }
}
