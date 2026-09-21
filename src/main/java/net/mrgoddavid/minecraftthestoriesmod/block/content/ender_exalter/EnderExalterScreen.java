package net.mrgoddavid.minecraftthestoriesmod.block.content.ender_exalter;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsBlockAbstractScreen;

import static net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsScreenTextures.*;

/**
 * A screen class just draws each individual elements (such as slots, inventory, etc.) on screen.
 * It has no functionality. IT IS CLIENT-SIDE only!
 *
 * @author Mr. GodDavid
 * @since 8/21/2026
 */
public class EnderExalterScreen extends MtsBlockAbstractScreen<EnderExalterMenu> {

    public EnderExalterScreen(EnderExalterMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    /**
     * Put the identifier of this block entity's screen here.
     *
     * @return the identifier of the screen texture of this custom block.
     */
    @Override
    protected Identifier registerScreenTexture() {
        return ENDER_EXALTER_GUI;
    }
}
