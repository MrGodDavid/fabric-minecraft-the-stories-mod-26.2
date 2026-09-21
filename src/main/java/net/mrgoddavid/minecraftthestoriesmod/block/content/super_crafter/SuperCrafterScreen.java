package net.mrgoddavid.minecraftthestoriesmod.block.content.super_crafter;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsBlockAbstractScreen;

import static net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsScreenTextures.*;

/**
 * Screen of Super Crafter.
 *
 * @author Mr. GodDavid
 * @since 8/26/2026
 */
public class SuperCrafterScreen extends MtsBlockAbstractScreen<SuperCrafterMenu> {

    public SuperCrafterScreen(SuperCrafterMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 181);
    }

    /**
     * Put the identifier of this block entity's screen here.
     *
     * @return the identifier of the screen texture of this custom block.
     */
    @Override
    protected Identifier registerScreenTexture() {
        return SUPER_CRAFTER_GUI;
    }
}
