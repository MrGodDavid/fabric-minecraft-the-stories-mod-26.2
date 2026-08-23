package net.mrgoddavid.minecraftthestoriesmod.block.custom.enricher;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsMenuTypes;

/**
 * Menu of Enricher.
 *
 * @author Mr. GodDavid
 * @since 8/22/2026
 */
public class EnricherMenu extends AbstractContainerMenu {

    private final Container inventory;
    private final ContainerData data;
    public final EnricherBlockEntity blockEntity;

    // SLOT INDICES
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_ROW_COUNT * PLAYER_INVENTORY_COLUMN_COUNT; // 27
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT; // 36
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int ENDER_EXALTER_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT; // 36

    private static final int ENDER_EXALTER_SLOT_COUNT = 3; // modify this to display how many slots you want.

    public EnricherMenu(int containerId, Inventory inventory, BlockPos pos) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(pos), new SimpleContainerData(4));
    }

    public EnricherMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
        super(MtsMenuTypes.ENRICHER_MENU, containerId);
        this.blockEntity = ((EnricherBlockEntity) blockEntity);
        this.data = data;
        this.inventory = this.blockEntity;

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        this.addSlot(new Slot(this.inventory, EnricherBlockEntity.INPUT_SLOT, 53, 10));
        this.addSlot(new Slot(this.inventory, EnricherBlockEntity.FUEL_SLOT, 53, 46));
        this.addSlot(new Slot(this.inventory, EnricherBlockEntity.OUTPUT_SLOT, 109, 29) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return false;
            }
        });

        addDataSlots(data);
    }

    public int getScaledArrowProgress() {
        int progress = data.get(0);
        int maxProgress = data.get(1);
        int arrowPixelSize = 18;
        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }

    public boolean isCrafting() {
        return data.get(0) > 0 && data.get(2) > 0;
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

        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
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
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }
    }
}
