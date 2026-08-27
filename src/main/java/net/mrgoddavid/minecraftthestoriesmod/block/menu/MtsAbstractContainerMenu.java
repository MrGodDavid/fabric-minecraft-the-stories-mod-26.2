package net.mrgoddavid.minecraftthestoriesmod.block.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public abstract class MtsAbstractContainerMenu extends AbstractContainerMenu {

    // SLOT INDICES
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_ROW_COUNT * PLAYER_INVENTORY_COLUMN_COUNT; // 27
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT; // 36
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int ENDER_EXALTER_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT; // 36

    private final PlayerInventoryMenuCoordsContext context;
    private int ENDER_EXALTER_SLOT_COUNT; // modify this to display how many slots you want.

    protected MtsAbstractContainerMenu(@Nullable MenuType<?> menuType, int containerId, Inventory inventory) {
        super(menuType, containerId);
        this.context = registerContext();

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);
    }

    protected void registerTotalSlots(int slots) {
        this.ENDER_EXALTER_SLOT_COUNT = slots;
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, context.hotBarX() + i * 18, context.hotBarY()));
        }
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, context.inventoryX() + x * 18, context.inventoryY() + y * 18));
            }
        }
    }

    protected abstract PlayerInventoryMenuCoordsContext registerContext();

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        Slot sourceSlot = slots.get(slotIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (slotIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot, so merge the stack into the entity's inventory
            if (!moveItemStackTo(sourceStack, ENDER_EXALTER_FIRST_SLOT_INDEX, ENDER_EXALTER_FIRST_SLOT_INDEX + ENDER_EXALTER_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (slotIndex < ENDER_EXALTER_FIRST_SLOT_INDEX + ENDER_EXALTER_SLOT_COUNT) {
            // This is an Ender-Exalter slot, so merge the stack into player's inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex: " + slotIndex);
            return ItemStack.EMPTY;
        }

        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }

    public record PlayerInventoryMenuCoordsContext(int hotBarX, int hotBarY, int inventoryX, int inventoryY) {
    }
}
