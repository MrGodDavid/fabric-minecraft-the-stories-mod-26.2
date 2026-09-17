package net.mrgoddavid.minecraftthestoriesmod.block.content.squeezer;

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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.mrgoddavid.minecraftthestoriesmod.block.ImplementedContainer;
import net.mrgoddavid.minecraftthestoriesmod.block.content.super_crafter.SuperCrafterBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsAbstractBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsCraftableBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.recipe.MtsRecipes;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.squeezer.SqueezerRecipe;
import net.mrgoddavid.minecraftthestoriesmod.recipe.content.squeezer.SqueezerRecipeInput;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

import static net.mrgoddavid.minecraftthestoriesmod.block.content.squeezer.SqueezerBlockEntity.Context.*;

/**
 * @author Mr. GodDavid
 * @since 9/14/2026
 */
public class SqueezerBlockEntity extends MtsAbstractBlockEntity implements ExtendedMenuProvider<BlockPos>, ImplementedContainer, MtsCraftableBlockEntity<SqueezerRecipe> {

    public final NonNullList<ItemStack> inventory = NonNullList.withSize(Context.TOTAL_SLOTS, ItemStack.EMPTY);
    public static final Component DEFAULT_NAME = Component.translatable("block.minecraft-the-stories-mod.squeezer_default");

    public SqueezerBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MtsBlockEntities.SQUEEZER_BE, worldPosition, blockState);
    }

    /**
     * Defines inventory drop logics here.
     */
    @Override
    public void drops() {
        super.defaultDrops(this.inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        ContainerHelper.loadAllItems(input, this.inventory);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        ContainerHelper.saveAllItems(output, this.inventory);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (level.isClientSide()) return;
        if (!(level.getBlockEntity(blockPos) instanceof SqueezerBlockEntity)) return;

        if (hasRecipe()) {
            craftItem();
            setChanged(level, blockPos, blockState);
        } else {
            clearResultSlot();
        }
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<SqueezerRecipe>> recipe = this.getCurrentRecipe();
        if (recipe.isEmpty()) return false;
        boolean isFruitInNorthSlotCorrect = this.checkFruitInNorthSlot(recipe);
        boolean isFruitInNortheastSlotCorrect = this.checkFruitInNortheastSlot(recipe);
        boolean isFruitInSoutheastSlotCorrect = this.checkFruitInSoutheastSlot(recipe);
        boolean isFruitInSouthSlotCorrect = this.checkFruitInSouthSlot(recipe);
        boolean isFruitInSouthwestSlotCorrect = this.checkFruitInSouthwestSlot(recipe);
        boolean isFruitInNorthwestSlotCorrect = this.checkFruitInNorthwestSlot(recipe);
        return isFruitInNorthSlotCorrect && isFruitInNortheastSlotCorrect && isFruitInSoutheastSlotCorrect
                && isFruitInSouthSlotCorrect && isFruitInSouthwestSlotCorrect && isFruitInNorthwestSlotCorrect;
    }

    private boolean matches(Optional<Ingredient> ingredient, ItemStack stack) {
        return ingredient.map(value -> value.test(stack)).orElse(stack.isEmpty());
    }

    private boolean checkFruitInNorthwestSlot(Optional<RecipeHolder<SqueezerRecipe>> recipe) {
        ItemStack fruitInNorthwestSlot = this.inventory.get(SQUEEZER_MATRIX_SLOT_NORTHWEST);
        return matches(recipe.get().value().fruitInNorthwestSlot(), fruitInNorthwestSlot);
    }

    private boolean checkFruitInSouthwestSlot(Optional<RecipeHolder<SqueezerRecipe>> recipe) {
        ItemStack fruitInSouthwestSlot = this.inventory.get(SQUEEZER_MATRIX_SLOT_SOUTHWEST);
        return matches(recipe.get().value().fruitInSouthwestSlot(), fruitInSouthwestSlot);
    }

    private boolean checkFruitInSouthSlot(Optional<RecipeHolder<SqueezerRecipe>> recipe) {
        ItemStack fruitInSouthSlot = this.inventory.get(SQUEEZER_MATRIX_SLOT_SOUTH);
        return matches(recipe.get().value().fruitInSouthSlot(), fruitInSouthSlot);
    }

    private boolean checkFruitInSoutheastSlot(Optional<RecipeHolder<SqueezerRecipe>> recipe) {
        ItemStack fruitInSoutheastSlot = this.inventory.get(SQUEEZER_MATRIX_SLOT_SOUTHEAST);
        return matches(recipe.get().value().fruitInSoutheastSlot(), fruitInSoutheastSlot);
    }

    private boolean checkFruitInNortheastSlot(Optional<RecipeHolder<SqueezerRecipe>> recipe) {
        ItemStack fruitInNortheastSlot = this.inventory.get(SQUEEZER_MATRIX_SLOT_NORTHEAST);
        return matches(recipe.get().value().fruitInNortheastSlot(), fruitInNortheastSlot);
    }

    private boolean checkFruitInNorthSlot(Optional<RecipeHolder<SqueezerRecipe>> recipe) {
        ItemStack fruitInNorthSlot = this.inventory.get(SQUEEZER_MATRIX_SLOT_NORTH);
        return matches(recipe.get().value().fruitInNorthSlot(), fruitInNorthSlot);
    }

    private void craftItem() {
        Optional<RecipeHolder<SqueezerRecipe>> recipe = this.getCurrentRecipe();
        if (recipe.isEmpty()) return;
        ItemStack mixture = recipe.get().value().assemble(new SqueezerRecipeInput(
                inventory.get(SQUEEZER_MATRIX_SLOT_NORTH),
                inventory.get(SQUEEZER_MATRIX_SLOT_NORTHEAST),
                inventory.get(SQUEEZER_MATRIX_SLOT_SOUTHEAST),
                inventory.get(SQUEEZER_MATRIX_SLOT_SOUTH),
                inventory.get(SQUEEZER_MATRIX_SLOT_SOUTHWEST),
                inventory.get(SQUEEZER_MATRIX_SLOT_NORTHWEST))
        );
        inventory.set(SQUEEZER_MATRIX_SLOT_JUICE, mixture.copy());
    }

    private void clearResultSlot() {
        inventory.set(SQUEEZER_MATRIX_SLOT_JUICE, ItemStack.EMPTY);
    }

    public void consumeIngredients() {
        inventory.set(SQUEEZER_MATRIX_SLOT_NORTH, inventory.get(SQUEEZER_MATRIX_SLOT_NORTH).copyWithCount(inventory.get(SQUEEZER_MATRIX_SLOT_NORTH).getCount() - 1));
        inventory.set(SQUEEZER_MATRIX_SLOT_NORTHEAST, inventory.get(SQUEEZER_MATRIX_SLOT_NORTHEAST).copyWithCount(inventory.get(SQUEEZER_MATRIX_SLOT_NORTHEAST).getCount() - 1));
        inventory.set(SQUEEZER_MATRIX_SLOT_SOUTHEAST, inventory.get(SQUEEZER_MATRIX_SLOT_SOUTHEAST).copyWithCount(inventory.get(SQUEEZER_MATRIX_SLOT_SOUTHEAST).getCount() - 1));
        inventory.set(SQUEEZER_MATRIX_SLOT_SOUTH, inventory.get(SQUEEZER_MATRIX_SLOT_SOUTH).copyWithCount(inventory.get(SQUEEZER_MATRIX_SLOT_SOUTH).getCount() - 1));
        inventory.set(SQUEEZER_MATRIX_SLOT_SOUTHWEST, inventory.get(SQUEEZER_MATRIX_SLOT_SOUTHWEST).copyWithCount(inventory.get(SQUEEZER_MATRIX_SLOT_SOUTHWEST).getCount() - 1));
        inventory.set(SQUEEZER_MATRIX_SLOT_NORTHWEST, inventory.get(SQUEEZER_MATRIX_SLOT_NORTHWEST).copyWithCount(inventory.get(SQUEEZER_MATRIX_SLOT_NORTHWEST).getCount() - 1));
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
        return new SqueezerMenu(containerId, inventory, this);
    }

    /**
     * Retrieves the item list of this container.
     * Must return the same instance every time it's called.
     */
    @Override
    public NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    /**
     * Implements this method based on the inventory of your block entity.
     *
     * @return optional recipe holder of your block's custom entity (if that block entity can craft/blast/smith items).
     */
    @Override
    public Optional<RecipeHolder<SqueezerRecipe>> getCurrentRecipe() {
        return ((ServerLevel) level).recipeAccess().
                getRecipeFor(MtsRecipes.SQUEEZER_TYPE, new SqueezerRecipeInput(
                                inventory.get(SQUEEZER_MATRIX_SLOT_NORTH),
                                inventory.get(SQUEEZER_MATRIX_SLOT_NORTHEAST),
                                inventory.get(SQUEEZER_MATRIX_SLOT_SOUTHEAST),
                                inventory.get(SQUEEZER_MATRIX_SLOT_SOUTH),
                                inventory.get(SQUEEZER_MATRIX_SLOT_SOUTHWEST),
                                inventory.get(SQUEEZER_MATRIX_SLOT_NORTHWEST)),
                        level);
    }

    /**
     * @author Mr. GodDavid
     * @since 9/14/2026
     */
    public static final class Context {
        public static final int SQUEEZER_MATRIX_SLOT_NORTH = 0;
        public static final int SQUEEZER_MATRIX_SLOT_NORTHEAST = 1;
        public static final int SQUEEZER_MATRIX_SLOT_SOUTHEAST = 2;
        public static final int SQUEEZER_MATRIX_SLOT_SOUTH = 3;
        public static final int SQUEEZER_MATRIX_SLOT_SOUTHWEST = 4;
        public static final int SQUEEZER_MATRIX_SLOT_NORTHWEST = 5;
        public static final int SQUEEZER_MATRIX_SLOT_JUICE = 6;

        public static final int TOTAL_SLOTS = 7;
    }
}
