package net.mrgoddavid.minecraftthestoriesmod.block.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * Menus without data components.
 *
 * @author Mr. GodDavid
 * @since 8/26/2026
 */
public abstract class MtsAbstractNonDataComponentContainerMenu extends MtsAbstractContainerMenu {

    public MtsAbstractNonDataComponentContainerMenu(MenuType<?> type, int containerId, Inventory inventory,
                                                    @SuppressWarnings("unused") BlockEntity blockEntity) {
        super(type, containerId, inventory);
    }
}
