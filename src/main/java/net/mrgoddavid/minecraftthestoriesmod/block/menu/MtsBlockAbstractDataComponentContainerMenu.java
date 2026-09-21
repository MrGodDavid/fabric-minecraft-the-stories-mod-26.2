package net.mrgoddavid.minecraftthestoriesmod.block.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import org.jspecify.annotations.Nullable;

/**
 * Menus with data components.
 *
 * @author Mr. GodDavid
 * @since 8/26/2026
 */
public abstract class MtsBlockAbstractDataComponentContainerMenu extends MtsBlockBlockAbstractContainerMenu {

    public MtsBlockAbstractDataComponentContainerMenu(@Nullable MenuType<?> menuType, int containerId, Inventory inventory, @SuppressWarnings("unused") ContainerData data) {
        super(menuType, containerId, inventory);
    }
}
