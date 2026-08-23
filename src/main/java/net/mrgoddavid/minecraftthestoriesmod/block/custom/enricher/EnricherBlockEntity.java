package net.mrgoddavid.minecraftthestoriesmod.block.custom.enricher;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.mrgoddavid.minecraftthestoriesmod.block.ImplementedContainer;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsAbstractBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import org.jspecify.annotations.Nullable;

/**
 * Block entity for Enricher.
 *
 * @author Mr. GodDavid
 * @since 8/17/2026
 */
public class EnricherBlockEntity extends MtsAbstractBlockEntity implements ExtendedMenuProvider<BlockPos>, ImplementedContainer {

    private static final Component DEFAULT_NAME = Component.translatable("block.minecraft-the-stories-mod.enricher_default");

    public NonNullList<ItemStack> inventory = NonNullList.withSize(3, ItemStack.EMPTY);
    public static final int INPUT_SLOT = 0;
    public static final int FUEL_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
    private int coalEndurance = 0;
    private int coalMaxEndurance = 100;

    public EnricherBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MtsBlockEntities.ENRICHER_BE, worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> EnricherBlockEntity.this.progress;
                    case 1 -> EnricherBlockEntity.this.maxProgress;
                    case 2 -> EnricherBlockEntity.this.coalEndurance;
                    case 3 -> EnricherBlockEntity.this.coalMaxEndurance;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0:
                        EnricherBlockEntity.this.progress = value;
                        break;
                    case 1:
                        EnricherBlockEntity.this.maxProgress = value;
                        break;
                    case 2:
                        EnricherBlockEntity.this.coalEndurance = value;
                        break;
                    case 3:
                        EnricherBlockEntity.this.coalMaxEndurance = value;
                        break;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("enricher.progress", this.progress);
        output.putInt("enricher.maxProgress", this.maxProgress);
        ContainerHelper.saveAllItems(output, inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        input.getInt("enricher.progress");
        input.getInt("enricher.maxProgress");
        ContainerHelper.loadAllItems(input, inventory);
    }

    /**
     * Writes additional server -&gt; client screen opening data to the buffer.
     *
     * @param player the player that is opening the screen
     * @return the screen opening data
     */
    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return this.worldPosition;
    }

    @Override
    public Component getDisplayName() {
        return DEFAULT_NAME;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new EnricherMenu(containerId, inventory, this, this.data);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {

        boolean shouldWork = hasRecipe() && isOutputSlotEmptyOrReceivable();
        if (shouldWork) {
            increaseEnrichingProgress();
            setChanged(level, blockPos, blockState);

            if (hasCraftingFinihsed()) {
                craftItem();
                resetProgress();
            }
            if (hasCoalBurnedOut()) {
                consumeCoal();
                burnNewCoal();
            }
        } else {
            resetProgress();
        }

        if (blockState.getValue(EnricherBlock.LIT) != shouldWork) {
            level.setBlockAndUpdate(blockPos, blockState
                    .setValue(EnricherBlock.LIT, shouldWork)
                    .cycle(EnricherBlock.STATE)
            );
        }
        setChanged(level, blockPos, blockState);
    }

    private void burnNewCoal() {
        this.coalEndurance = 0;
        this.coalMaxEndurance = 100;
    }

    private void consumeCoal() {
        inventory.set(FUEL_SLOT, inventory.get(FUEL_SLOT).copyWithCount(inventory.get(FUEL_SLOT).count() - 1));
    }

    private boolean hasCoalBurnedOut() {
        return this.coalEndurance >= this.coalMaxEndurance;
    }

    private boolean hasRecipe() {
        ItemStack output = new ItemStack(MtsItems.STRONG_AMETHYST_INGOT);
        Item input = MtsItems.RAW_STRONG_AMETHYST;
        Item fuel = Items.COAL;
        boolean hasCorrectInput = inventory.get(INPUT_SLOT).is(input);
        boolean hasCorrectFuel = inventory.get(FUEL_SLOT).is(fuel);
        boolean isItemOutputRight = canInsertItemIntoOutputSlot(output);
        boolean isAmountRight = canInsertAmoundIntoOutputSlot(output.getCount());
        boolean hasFuel = hasRemainingFuels();
        return hasFuel && hasCorrectFuel && hasCorrectInput && isItemOutputRight && isAmountRight;
    }

    private boolean hasRemainingFuels() {
        return !inventory.get(FUEL_SLOT).isEmpty();
    }

    private boolean canInsertAmoundIntoOutputSlot(int count) {
        int maxCount = inventory.get(OUTPUT_SLOT).isEmpty() ? 64 : inventory.get(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = inventory.get(OUTPUT_SLOT).getCount();
        return maxCount >= currentCount + count;
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.get(OUTPUT_SLOT).isEmpty() || inventory.get(OUTPUT_SLOT).is(output.getItem());
    }

    private void craftItem() {
        ItemStack output = new ItemStack(MtsItems.STRONG_AMETHYST_INGOT);
        inventory.set(INPUT_SLOT, inventory.get(INPUT_SLOT).copyWithCount(inventory.get(INPUT_SLOT).count() - 1));
        inventory.set(OUTPUT_SLOT, output.copyWithCount(inventory.get(OUTPUT_SLOT).count() + output.getCount()));
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return inventory.get(OUTPUT_SLOT).isEmpty()
                || inventory.get(OUTPUT_SLOT).getCount() < inventory.get(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasCraftingFinihsed() {
        return this.progress >= this.maxProgress;
    }

    private void increaseEnrichingProgress() {
        this.progress++;
        this.coalEndurance++;
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 72;
    }

    /**
     * Retrieves the item list of this container.
     * Must return the same instance every time it's called.
     */
    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    /**
     * Defines inventory drop logics here.
     */
    @Override
    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
}
