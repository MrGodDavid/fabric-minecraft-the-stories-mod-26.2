package net.mrgoddavid.minecraftthestoriesmod.block.content.super_crafter;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.mrgoddavid.minecraftthestoriesmod.block.ImplementedContainer;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsAbstractBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsCraftableBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.recipe.MtsRecipes;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.super_crafter.SuperCrafterRecipe;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.super_crafter.SuperCrafterRecipeInput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

import static net.mrgoddavid.minecraftthestoriesmod.block.content.super_crafter.SuperCrafterBlockEntity.Context.*;

/**
 * Block entity for Super Crafter Block.
 *
 * @author Mr. GodDavid
 * @since 8/17/2026
 */
public class SuperCrafterBlockEntity extends MtsAbstractBlockEntity implements ExtendedMenuProvider<BlockPos>, ImplementedContainer, MtsCraftableBlockEntity<SuperCrafterRecipe> {

    public final NonNullList<ItemStack> inventory = NonNullList.withSize(TOTAL_SLOTS, ItemStack.EMPTY);

    public static final Component DEFAULT_NAME = Component.translatable("block.minecraft-the-stories-mod.super_crafter_default");

    public SuperCrafterBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MtsBlockEntities.SUPER_CRAFTER_BE, worldPosition, blockState);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (level.isClientSide()) return;
        if (!(level.getBlockEntity(blockPos) instanceof SuperCrafterBlockEntity)) return;

        updateBlockModel(level, blockPos, blockState);
        if (hasRecipe()) {
            craftItem();
            setChanged(level, blockPos, blockState);
        } else {
            clearResultSlot();
        }
    }

    private void updateBlockModel(Level level, BlockPos blockPos, BlockState blockState) {
        boolean template = shouldLoadTemplateModel();
        boolean hammer = shouldLoadHammerModel();
        if (template && hammer) {
            level.setBlockAndUpdate(blockPos, blockState.setValue(SuperCrafterBlock.STATE, SuperCrafterBlock.TYPE.WITH_HAMMER_WITH_TEMPLATE));
        } else if (template) {
            level.setBlockAndUpdate(blockPos, blockState.setValue(SuperCrafterBlock.STATE, SuperCrafterBlock.TYPE.WITH_TEMPLATE));
        } else if (hammer) {
            level.setBlockAndUpdate(blockPos, blockState.setValue(SuperCrafterBlock.STATE, SuperCrafterBlock.TYPE.WITH_HAMMER));
        } else {
            level.setBlockAndUpdate(blockPos, blockState.setValue(SuperCrafterBlock.STATE, SuperCrafterBlock.TYPE.DEFAULT));
        }
    }

    private boolean shouldLoadHammerModel() {
        return isHammerIngredientCorrect() && !inventory.get(Context.CRAFTING_HAMMER_SLOT).isEmpty();
    }

    private boolean shouldLoadTemplateModel() {
        return isTemplateIngredientCorrect() && !inventory.get(Context.TEMPLATE_CONSUMER_SLOT).isEmpty();
    }

    private void clearResultSlot() {
        inventory.set(Context.RESULT_SLOT, ItemStack.EMPTY);
    }

    void craftItem() {
        Optional<RecipeHolder<SuperCrafterRecipe>> recipe = this.getCurrentRecipe();
        if (recipe.isEmpty()) return;
        ItemStack output = recipe.get().value().assemble(new SuperCrafterRecipeInput(
                inventory.get(TEMPLATE_CONSUMER_SLOT),
                inventory.get(CRAFTING_HAMMER_SLOT),
                inventory.get(ITEM_STAGE_SLOT))
        );
        inventory.set(Context.RESULT_SLOT, output.copy());
    }

    void consumeIngredients() {
        inventory.set(Context.TEMPLATE_CONSUMER_SLOT, inventory.get(Context.TEMPLATE_CONSUMER_SLOT).copyWithCount(inventory.get(Context.TEMPLATE_CONSUMER_SLOT).getCount() - 1));
        if (usedHammer()) {
            inventory.set(CRAFTING_HAMMER_SLOT, inventory.get(CRAFTING_HAMMER_SLOT).copyWithCount(inventory.get(CRAFTING_HAMMER_SLOT).getCount() - 1));
        }
        inventory.set(Context.ITEM_STAGE_SLOT, inventory.get(Context.ITEM_STAGE_SLOT).copyWithCount(inventory.get(Context.ITEM_STAGE_SLOT).getCount() - 1));
    }

    private boolean usedHammer() {
        inventory.get(CRAFTING_HAMMER_SLOT).setDamageValue(inventory.get(CRAFTING_HAMMER_SLOT).getDamageValue() + 10);
        return inventory.get(CRAFTING_HAMMER_SLOT).getDamageValue() >= inventory.get(CRAFTING_HAMMER_SLOT).getMaxDamage();
    }

    private boolean hasRecipe() {
        boolean isTemplateCorrect = isTemplateIngredientCorrect();
        boolean isHammerCorrect = isHammerIngredientCorrect();
        boolean isStageCorrect = isStageItemIngredientCorrect();
        return isTemplateCorrect && isHammerCorrect && isStageCorrect;
    }

    private boolean isStageItemIngredientCorrect() {
        Optional<RecipeHolder<SuperCrafterRecipe>> recipe = this.getCurrentRecipe();
        if (recipe.isEmpty()) return false;
        ItemStack stageItem = inventory.get(Context.ITEM_STAGE_SLOT);
        return recipe.get().value().stageItem().test(stageItem);
    }

    private boolean isHammerIngredientCorrect() {
        Optional<RecipeHolder<SuperCrafterRecipe>> recipe = this.getCurrentRecipe();
        if (recipe.isEmpty()) return false;
        ItemStack hammer = inventory.get(Context.CRAFTING_HAMMER_SLOT);
        return recipe.get().value().hammer().test(hammer);
    }

    private boolean isTemplateIngredientCorrect() {
        Optional<RecipeHolder<SuperCrafterRecipe>> recipe = this.getCurrentRecipe();
        if (recipe.isEmpty()) return false;
        ItemStack template = inventory.get(Context.TEMPLATE_CONSUMER_SLOT);
        return recipe.get().value().upgradeScroll().test(template);
    }

    /**
     * Defines inventory drop logics here.
     */
    @Override
    public void drops() {
        super.defaultDrops(this.inventory);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        ContainerHelper.loadAllItems(input, this.inventory);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        ContainerHelper.saveAllItems(output, this.inventory);
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
    public @NonNull Component getDisplayName() {
        return DEFAULT_NAME;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new SuperCrafterMenu(containerId, inventory, this);
    }

    /**
     * Retrieves the item list of this container.
     * Must return the same instance every time it's called.
     */
    @Override
    public NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Optional<RecipeHolder<SuperCrafterRecipe>> getCurrentRecipe() {
        return ((ServerLevel) level).recipeAccess().
                getRecipeFor(MtsRecipes.SUPER_CRAFTER_TYPE, new SuperCrafterRecipeInput(
                                inventory.get(TEMPLATE_CONSUMER_SLOT),
                                inventory.get(CRAFTING_HAMMER_SLOT),
                                inventory.get(ITEM_STAGE_SLOT)),
                        level);
    }

    public static final class Context {
        public static final int TEMPLATE_CONSUMER_SLOT = 0;
        public static final int CRAFTING_HAMMER_SLOT = 1;
        public static final int ITEM_STAGE_SLOT = 2;
        public static final int RESULT_SLOT = 3;

        public static final int TOTAL_SLOTS = 4;
    }
}
