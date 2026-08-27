package net.mrgoddavid.minecraftthestoriesmod.block.custom.super_crafter;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsAbstractNonDataComponentContainerMenu;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsMenuTypes;

/**
 * @author Mr. GodDavid
 * @since 8/26/2026
 */
public class SuperCrafterMenu extends MtsAbstractNonDataComponentContainerMenu {

    private final Container inventory;

    public SuperCrafterMenu(int containerId, Inventory inventory, BlockPos blockPos) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(blockPos));
    }

    public SuperCrafterMenu(int containerId, Inventory inventory, BlockEntity blockEntity) {
        super(MtsMenuTypes.SUPER_CRAFTER_MENU, containerId, inventory, blockEntity);
        super.registerTotalSlots(4);
        this.inventory = ((Container) blockEntity);

        addSlot(new Slot(this.inventory, SuperCrafterBlockEntity.Context.TEMPLATE_CONSUMER_SLOT, 20, 61) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        addSlot(new Slot(this.inventory, SuperCrafterBlockEntity.Context.CRAFTING_HAMMER_SLOT, 39, 18) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        addSlot(new Slot(this.inventory, SuperCrafterBlockEntity.Context.ITEM_STAGE_SLOT, 68, 46) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        addSlot(new Slot(this.inventory, SuperCrafterBlockEntity.Context.RESULT_SLOT, 128, 42) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack carried) {
                super.onTake(player, carried);
                ((SuperCrafterBlockEntity) blockEntity).consumeIngredients();
            }
        });
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }

    @Override
    protected PlayerInventoryMenuCoordsContext registerContext() {
        return new PlayerInventoryMenuCoordsContext(8, 157, 8, 99);
    }
}
