package net.mrgoddavid.minecraftthestoriesmod.block.custom.ender_exalter;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsAbstractNonDataComponentContainerMenu;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsMenuTypes;

import static net.mrgoddavid.minecraftthestoriesmod.block.custom.ender_exalter.EnderExalterBlockEntity.TOTAL_SLOTS;

/**
 * A menu is the bridge that connects the Block Entity and the Screen. This has functionality of telling Minecraft how
 * to  open the menu of the block entity, which, in this case, is the Ender Exalter. For example where to put the slots
 * in menu.
 *
 * @author Mr. GodDavid
 * @since 8/21/2026
 */
public class EnderExalterMenu extends MtsAbstractNonDataComponentContainerMenu {

    private final Container inventory;

    public EnderExalterMenu(int containerId, Inventory inventory, BlockPos blockPos) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(blockPos));
    }

    public EnderExalterMenu(int containerId, Inventory inventory, BlockEntity blockEntity) {
        super(MtsMenuTypes.ENDER_EXALTER_MENU, containerId, inventory, blockEntity);
        super.registerTotalSlots(TOTAL_SLOTS);
        this.inventory = ((Container) blockEntity);

        // add slots.
        addSlot(new Slot(this.inventory, 0, 62, 34) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        addSlot(new Slot(this.inventory, 1, 98, 34) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }

    @Override
    protected PlayerInventoryMenuCoordsContext registerContext() {
        return new PlayerInventoryMenuCoordsContext(8, 140, 8, 82);
    }
}
