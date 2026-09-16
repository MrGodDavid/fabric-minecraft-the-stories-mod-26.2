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
import net.minecraft.world.item.ItemStack;
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
import org.jspecify.annotations.Nullable;

import java.util.Optional;

import static net.mrgoddavid.minecraftthestoriesmod.block.content.squeezer.SqueezerBlockEntity.Context.*;

/**
 * @author Mr. GodDavid
 * @since 9/14/2026
 */
@SuppressWarnings("NullableProblems")
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
        return false;
    }

    private void craftItem() {

    }

    private void clearResultSlot() {
        inventory.set(SuperCrafterBlockEntity.Context.RESULT_SLOT, ItemStack.EMPTY);
    }

    public void consumeIngredients() {

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
