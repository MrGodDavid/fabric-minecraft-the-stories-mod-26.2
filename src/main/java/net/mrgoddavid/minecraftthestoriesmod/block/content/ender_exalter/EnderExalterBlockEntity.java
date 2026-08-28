package net.mrgoddavid.minecraftthestoriesmod.block.content.ender_exalter;

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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.mrgoddavid.minecraftthestoriesmod.block.ImplementedContainer;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsAbstractBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;
import org.jspecify.annotations.Nullable;

public class EnderExalterBlockEntity extends MtsAbstractBlockEntity implements ImplementedContainer, ExtendedMenuProvider<BlockPos> {

    public static final int TOTAL_SLOTS = 2;
    public static final Component DISPLAY_NAME = Component.translatable("block.minecraft-the-stories-mod.ender_exalter");
    public NonNullList<ItemStack> inventory = NonNullList.withSize(TOTAL_SLOTS, ItemStack.EMPTY);

    public EnderExalterBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MtsBlockEntities.ENDER_EXALTER_BE, worldPosition, blockState);
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
    public void clearContent() {
        inventory.set(0, ItemStack.EMPTY);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, inventory);
    }

    /**
     * Defines inventory drop logics here.
     */
    @Override
    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, this.inventory);
    }

    /* MENU METHODS */
    @Override
    public Component getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new EnderExalterMenu(containerId, inventory, this);
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
}
