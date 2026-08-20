package net.mrgoddavid.minecraftthestoriesmod.block.custom.super_crafter;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsAbstractBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;

/**
 * Block entity for Super Crafter Block.
 *
 * @author Mr. GodDavid
 * @since 8/17/2026
 */
public class SuperCrafterBlockEntity extends MtsAbstractBlockEntity implements Container {

    public NonNullList<ItemStack> inventory = NonNullList.withSize(3, ItemStack.EMPTY);

    public SuperCrafterBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MtsBlockEntities.SUPER_CRAFTER_BE, worldPosition, blockState);
    }

    @Override
    public int getContainerSize() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return inventory.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        ItemStack item = ContainerHelper.removeItem(inventory, slot, count);
        if (!item.isEmpty()) {
            setChanged();
        }
        return item;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(inventory, slot);
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        inventory.set(slot, itemStack);
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        inventory.clear();
        setChanged();
    }

    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, this.inventory);
    }

    /* SAVING DATA */
    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, inventory);
    }

    /* LOADING DATA */
    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, inventory);
    }
}
