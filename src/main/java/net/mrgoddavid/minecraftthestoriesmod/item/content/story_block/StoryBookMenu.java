package net.mrgoddavid.minecraftthestoriesmod.item.content.story_block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.general.MtsMenuTypes;

/**
 * @author Mr. GodDavid
 * @since 9/21/2026
 */
public class StoryBookMenu extends AbstractContainerMenu {

    public StoryBookMenu(int containerId, Inventory inventory) {
        super(MtsMenuTypes.STORY_BOOK_MENU, containerId);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
