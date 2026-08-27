package net.mrgoddavid.minecraftthestoriesmod.block.custom.enricher;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.mrgoddavid.minecraftthestoriesmod.block.ImplementedContainer;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsAbstractBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.recipe.MtsRecipes;
import net.mrgoddavid.minecraftthestoriesmod.recipe.custom.EnricherRecipe;
import net.mrgoddavid.minecraftthestoriesmod.recipe.custom.EnricherRecipeInput;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

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
    private int maxCoalEndurance = 36;
    private int wasteFluid = 0;
    private int maxWasteFluid = 200;

    public boolean isEnriching() {
        return progress > 0;
    }

    public EnricherBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MtsBlockEntities.ENRICHER_BE, worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case ContainerDataContext.PROGRESS_POSITION -> EnricherBlockEntity.this.progress;
                    case ContainerDataContext.MAX_PROGRESS_POSITION -> EnricherBlockEntity.this.maxProgress;
                    case ContainerDataContext.COAL_ENDURANCE_POSITION -> EnricherBlockEntity.this.coalEndurance;
                    case ContainerDataContext.MAX_COAL_ENDURANCE_POSITION -> EnricherBlockEntity.this.maxCoalEndurance;
                    case ContainerDataContext.WASTE_FLUID_POSITION -> EnricherBlockEntity.this.wasteFluid;
                    case ContainerDataContext.MAX_WASTE_FLUID_POSITION -> EnricherBlockEntity.this.maxWasteFluid;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case ContainerDataContext.PROGRESS_POSITION:
                        EnricherBlockEntity.this.progress = value;
                        break;
                    case ContainerDataContext.MAX_PROGRESS_POSITION:
                        EnricherBlockEntity.this.maxProgress = value;
                        break;
                    case ContainerDataContext.COAL_ENDURANCE_POSITION:
                        EnricherBlockEntity.this.coalEndurance = value;
                        break;
                    case ContainerDataContext.MAX_COAL_ENDURANCE_POSITION:
                        EnricherBlockEntity.this.maxCoalEndurance = value;
                        break;
                    case ContainerDataContext.WASTE_FLUID_POSITION:
                        EnricherBlockEntity.this.wasteFluid = value;
                        break;
                    case ContainerDataContext.MAX_WASTE_FLUID_POSITION:
                        EnricherBlockEntity.this.maxWasteFluid = value;
                        break;
                }
            }

            @Override
            public int getCount() {
                return ContainerDataContext.DATA_ARRAY_SIZE;
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
        boolean shouldWork = hasRecipe() && isOutputSlotEmptyOrReceivable() && !hasWasteOverFlow();
        if (shouldWork) {
            increaseEnrichingProgress();
            setChanged(level, blockPos, blockState);

            if (hasCraftingFinihsed()) {
                craftItem();
                resetProgress();
                addWasteFluid();
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

    private boolean hasWasteOverFlow() {
        return this.wasteFluid >= this.maxWasteFluid;
    }

    public void clearWasteFluid() {
        this.wasteFluid = 0;
        this.maxWasteFluid = 200;
    }

    private void addWasteFluid() {
        this.wasteFluid += 50;
    }

    private void burnNewCoal() {
        this.coalEndurance = 0;
        this.maxCoalEndurance = 100;
    }

    private void consumeCoal() {
        inventory.set(FUEL_SLOT, inventory.get(FUEL_SLOT).copyWithCount(inventory.get(FUEL_SLOT).count() - 1));
    }

    private boolean hasCoalBurnedOut() {
        return this.coalEndurance >= this.maxCoalEndurance;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<EnricherRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack output = recipe.get().value().assemble(new EnricherRecipeInput(inventory.get(INPUT_SLOT), inventory.get(FUEL_SLOT)));

        boolean isItemOutputRight = canInsertItemIntoOutputSlot(output);
        boolean isAmountRight = canInsertAmoundIntoOutputSlot(output.getCount());
        boolean hasFuel = hasRemainingFuels();
        return hasFuel && isItemOutputRight && isAmountRight;
    }

    private Optional<RecipeHolder<EnricherRecipe>> getCurrentRecipe() {
        return ((ServerLevel) level).recipeAccess()
                .getRecipeFor(MtsRecipes.ENRICHER_TYPE, new EnricherRecipeInput(inventory.get(INPUT_SLOT), inventory.get(FUEL_SLOT)), level);
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
        Optional<RecipeHolder<EnricherRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().assemble(new EnricherRecipeInput(inventory.get(INPUT_SLOT), inventory.get(FUEL_SLOT)));

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
        super.defaultDrops(this.inventory);
    }

    public static final class ContainerDataContext {
        public static final int PROGRESS_POSITION = 0;
        public static final int MAX_PROGRESS_POSITION = 1;
        public static final int COAL_ENDURANCE_POSITION = 2;
        public static final int MAX_COAL_ENDURANCE_POSITION = 3;
        public static final int WASTE_FLUID_POSITION = 4;
        public static final int MAX_WASTE_FLUID_POSITION = 5;

        public static final int DATA_ARRAY_SIZE = 6;
    }
}
