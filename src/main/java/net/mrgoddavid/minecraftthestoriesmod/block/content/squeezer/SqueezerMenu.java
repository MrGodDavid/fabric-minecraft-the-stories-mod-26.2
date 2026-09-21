package net.mrgoddavid.minecraftthestoriesmod.block.content.squeezer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsAbstractNonDataComponentContainerMenu;
import net.mrgoddavid.minecraftthestoriesmod.general.MtsMenuTypes;

/**
 * @author Mr. GodDavid
 * @since 9/14/2026
 */
public class SqueezerMenu extends MtsAbstractNonDataComponentContainerMenu {

    private final Container inventory;

    public SqueezerMenu(int containerId, Inventory inventory, BlockPos blockPos) {
       this(containerId, inventory, inventory.player.level().getBlockEntity(blockPos));
    }

    public SqueezerMenu(int containerId, Inventory inventory, BlockEntity blockEntity) {
        super(MtsMenuTypes.SQUEEZER_MENU, containerId, inventory, blockEntity);
        super.registerTotalSlots(SqueezerBlockEntity.Context.TOTAL_SLOTS);
        this.inventory = ((Container) blockEntity);

        addSlot(new Slot(this.inventory, SqueezerBlockEntity.Context.SQUEEZER_MATRIX_SLOT_NORTH, 80, 8));
        addSlot(new Slot(this.inventory, SqueezerBlockEntity.Context.SQUEEZER_MATRIX_SLOT_NORTHEAST, 125, 30));
        addSlot(new Slot(this.inventory, SqueezerBlockEntity.Context.SQUEEZER_MATRIX_SLOT_SOUTHEAST, 125, 76));
        addSlot(new Slot(this.inventory, SqueezerBlockEntity.Context.SQUEEZER_MATRIX_SLOT_SOUTH, 80, 97));
        addSlot(new Slot(this.inventory, SqueezerBlockEntity.Context.SQUEEZER_MATRIX_SLOT_SOUTHWEST, 35, 76));
        addSlot(new Slot(this.inventory, SqueezerBlockEntity.Context.SQUEEZER_MATRIX_SLOT_NORTHWEST, 35, 30));
        addSlot(new Slot(this.inventory, SqueezerBlockEntity.Context.SQUEEZER_MATRIX_SLOT_JUICE, 80, 53){
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack carried) {
                super.onTake(player, carried);
                ((SqueezerBlockEntity) blockEntity).consumeIngredients();
            }
        });
    }

    @Override
    protected PlayerInventoryMenuCoordsContext registerContext() {
        return new PlayerInventoryMenuCoordsContext(8,185,8,127);
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }
}
