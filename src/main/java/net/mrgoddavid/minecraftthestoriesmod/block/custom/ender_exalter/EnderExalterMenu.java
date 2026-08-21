package net.mrgoddavid.minecraftthestoriesmod.block.custom.ender_exalter;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsMenuTypes;
import org.jspecify.annotations.Nullable;

import static net.mrgoddavid.minecraftthestoriesmod.block.custom.ender_exalter.EnderExalterBlockEntity.TOTAL_SLOTS;

/**
 * A menu is the bridge that connects the Block Entity and the Screen. This has functionality of telling Minecraft how
 * to  open the menu of the block entity, which, in this case, is the Ender Exalter. For example where to put the slots
 * in menu.
 *
 * @author Mr. GodDavid
 * @since 8/21/2026
 */
public class EnderExalterMenu extends AbstractContainerMenu {

    private final Container inventory;

    // SLOT INDICES
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_ROW_COUNT * PLAYER_INVENTORY_COLUMN_COUNT; // 27
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT; // 36
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int ENDER_EXALTER_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT; // 36

    private static final int ENDER_EXALTER_SLOT_COUNT = TOTAL_SLOTS; // modify this to display how many slots you want.


    public EnderExalterMenu(int containerId, Inventory inventory, BlockPos blockPos) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(blockPos));
    }

    public EnderExalterMenu(int containerId, Inventory inventory, BlockEntity blockEntity) {
        super(MtsMenuTypes.ENDER_EXALTER_MENU, containerId);
        this.inventory = ((Container) blockEntity);

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

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

        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 140));
        }
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 82 + y * 18));
            }
        }
    }
}
