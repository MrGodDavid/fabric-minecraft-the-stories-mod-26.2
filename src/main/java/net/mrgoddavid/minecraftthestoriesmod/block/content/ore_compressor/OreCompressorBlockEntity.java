package net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
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
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Ore Compressor block entity.
 *
 * @author Mr. GodDavid
 * @since 8/28/2026
 */
public class OreCompressorBlockEntity extends MtsAbstractBlockEntity implements ExtendedMenuProvider<BlockPos>, ImplementedContainer {

    private static final Component DEFAULT_NAME = Component.translatable("block.minecraft-the-stories-mod.ore_compressor_default");
    public static final int INPUT_SLOT = 0;
    public static final int FUEL_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;
    public NonNullList<ItemStack> inventory = NonNullList.withSize(3, ItemStack.EMPTY);

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 144;
    private int maxFuelAmount = 200;
    private int fuelAmount = maxFuelAmount;
    private int pressProgress = 0;
    private int maxPressProgress = 4;

    public OreCompressorBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MtsBlockEntities.ORE_COMPRESSOR_BE, worldPosition, blockState);
        this.data = new ContainerData() {

            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case ContainerDataContext.PROGRESS_POSITION -> OreCompressorBlockEntity.this.progress;
                    case ContainerDataContext.MAX_PROGRESS_POSITION -> OreCompressorBlockEntity.this.maxProgress;
                    case ContainerDataContext.REMAINING_FUEL_POSITION -> OreCompressorBlockEntity.this.fuelAmount;
                    case ContainerDataContext.MAX_FUEL_POSITION -> OreCompressorBlockEntity.this.maxFuelAmount;
                    case ContainerDataContext.PLATE_PRESSING_PROGRESS_POSITION ->
                            OreCompressorBlockEntity.this.pressProgress;
                    case ContainerDataContext.MAX_PLATE_PRESSING_PROGRESS_POSITION ->
                            OreCompressorBlockEntity.this.maxPressProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case ContainerDataContext.PROGRESS_POSITION -> OreCompressorBlockEntity.this.progress = value;
                    case ContainerDataContext.MAX_PROGRESS_POSITION ->
                            OreCompressorBlockEntity.this.maxProgress = value;
                    case ContainerDataContext.REMAINING_FUEL_POSITION ->
                            OreCompressorBlockEntity.this.fuelAmount = value;
                    case ContainerDataContext.MAX_FUEL_POSITION -> OreCompressorBlockEntity.this.maxFuelAmount = value;
                    case ContainerDataContext.PLATE_PRESSING_PROGRESS_POSITION ->
                            OreCompressorBlockEntity.this.pressProgress = value;
                    case ContainerDataContext.MAX_PLATE_PRESSING_PROGRESS_POSITION ->
                            OreCompressorBlockEntity.this.maxPressProgress = value;
                }
            }

            @Override
            public int getCount() {
                return ContainerDataContext.DATA_ARRAY_SIZE;
            }
        };
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("ore_compressor.progress", progress);
        output.putInt("ore_compressor.maxProgress", maxProgress);
        output.putInt("ore_compressor.fuelAmount", fuelAmount);
        output.putInt("ore_compressor.maxFuelAmount", maxFuelAmount);
        output.putInt("ore_compressor.pressProgress", pressProgress);
        output.putInt("ore_compressor.maxPressProgress", maxPressProgress);
        ContainerHelper.saveAllItems(output, inventory);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        input.getInt("ore_compressor.progress");
        input.getInt("ore_compressor.maxProgress");
        input.getInt("ore_compressor.fuelAmount");
        input.getInt("ore_compressor.maxFuelAmount");
        input.getInt("ore_compressor.pressProgress");
        input.getInt("ore_compressor.maxPressProgress");
        ContainerHelper.loadAllItems(input, inventory);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (hasRemainingFuel()) {
            boolean shouldWork = hasRecipe() && isOutputSlotEmptyOrReceivable();
            if (shouldWork) {
                increaseCompressingProgress();
                setChanged(level, blockPos, blockState);

                if (hasCompressingFinished()) {
                    craftItem();
                    resetProgress();
                    consumeFuel();
                }
                if (updateFrequency(5)) {
                    updatePressPlateAnimation();
                }
            } else {
                resetProgress();
            }
        } else {
            resetProgress();
            if (hasCorrectFuelBottle()) {
                consumeFuelBottle();
                refillFuel();
                setChanged(level, blockPos, blockState);
            }
        }
    }

    @SuppressWarnings("SameParameterValue")
    private boolean updateFrequency(int frequency) {
        return this.progress % (maxProgress / frequency) == 0;
    }

    private void updatePressPlateAnimation() {
        if (this.pressProgress < this.maxPressProgress) {
            this.pressProgress++;
        }
    }

    private boolean hasCorrectFuelBottle() {
        return !inventory.get(FUEL_SLOT).isEmpty() && inventory.get(FUEL_SLOT).is(Items.COAL);
    }

    private void consumeFuelBottle() {
        this.inventory.set(FUEL_SLOT, inventory.get(FUEL_SLOT).copyWithCount(inventory.get(FUEL_SLOT).getCount() - 1));
    }

    private void consumeFuel() {
        this.fuelAmount -= 20;
    }

    public boolean isCompressing() {
        return this.progress > 0;
    }

    private void refillFuel() {
        this.fuelAmount = this.maxFuelAmount;
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 144;
        this.pressProgress = 0;
        this.maxPressProgress = 4;
    }

    private void craftItem() {
        ItemStack output = new ItemStack(MtsItems.STRONG_TOPAZ_INGOT);
        this.inventory.set(INPUT_SLOT, inventory.get(INPUT_SLOT).copyWithCount(inventory.get(INPUT_SLOT).getCount() - 4));
        this.inventory.set(OUTPUT_SLOT, output.copyWithCount(inventory.get(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCompressingFinished() {
        return this.progress >= this.maxProgress;
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return inventory.get(OUTPUT_SLOT).isEmpty()
                || inventory.get(OUTPUT_SLOT).getCount() < inventory.get(OUTPUT_SLOT).getMaxStackSize();
    }

    /**
     * Increases the progress bar.
     */
    private void increaseCompressingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        boolean isInputCorrect = inventory.get(INPUT_SLOT).is(MtsItems.STRONG_TOPAZ);

        ItemStack output = new ItemStack(MtsItems.STRONG_TOPAZ_INGOT);
        boolean isItemOutputRight = canInsertItemIntoOutputSlot(output);
        boolean isAmountCorrect = canInsertAmountIntoOutputSlot(output.getCount());
        boolean hasEnoughInput = inventory.get(INPUT_SLOT).getCount() >= 4;
        return hasEnoughInput && isInputCorrect && isItemOutputRight && isAmountCorrect;
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inventory.get(OUTPUT_SLOT).isEmpty() ? 64 : inventory.get(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = inventory.get(OUTPUT_SLOT).getCount();
        return maxCount >= currentCount + count;
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.get(OUTPUT_SLOT).isEmpty() || inventory.get(OUTPUT_SLOT).is(output.getItem());
    }

    private boolean hasRemainingFuel() {
        return this.fuelAmount > 0;
    }

    /**
     * Defines inventory drop logics here.
     */
    @Override
    public void drops() {
        super.defaultDrops(inventory);
    }

    /**
     * Writes additional server -&gt; client screen opening data to the buffer.
     *
     * @param player the player that is opening the screen
     * @return the screen opening data
     */
    @Override
    public BlockPos getScreenOpeningData(@NonNull ServerPlayer player) {
        return this.worldPosition;
    }

    @Override
    public @NonNull Component getDisplayName() {
        return DEFAULT_NAME;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, @NonNull Inventory inventory, @NonNull Player player) {
        return new OreCompressorMenu(containerId, inventory, this, data);
    }

    /**
     * Retrieves the item list of this container.
     * Must return the same instance every time it's called.
     */
    @Override
    public NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    public static final class ContainerDataContext {
        public static final int PROGRESS_POSITION = 0;
        public static final int MAX_PROGRESS_POSITION = 1;
        public static final int REMAINING_FUEL_POSITION = 2;
        public static final int MAX_FUEL_POSITION = 3;
        public static final int PLATE_PRESSING_PROGRESS_POSITION = 4;
        public static final int MAX_PLATE_PRESSING_PROGRESS_POSITION = 5;

        public static final int DATA_ARRAY_SIZE = 6;
    }
}
