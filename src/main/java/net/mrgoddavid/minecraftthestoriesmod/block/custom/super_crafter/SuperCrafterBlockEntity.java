package net.mrgoddavid.minecraftthestoriesmod.block.custom.super_crafter;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
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

/**
 * Block entity for Super Crafter Block.
 *
 * @author Mr. GodDavid
 * @since 8/17/2026
 */
public class SuperCrafterBlockEntity extends MtsAbstractBlockEntity implements ExtendedMenuProvider<BlockPos>, ImplementedContainer {

    public NonNullList<ItemStack> inventory = NonNullList.withSize(4, ItemStack.EMPTY);

    public static final Component DEFAULT_NAME = Component.translatable("block.minecraft-the-stories-mod.super_crafter_default");

    public SuperCrafterBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MtsBlockEntities.SUPER_CRAFTER_BE, worldPosition, blockState);
    }

    /**
     * Defines inventory drop logics here.
     */
    @Override
    public void drops() {
        super.defaultDrops(this.inventory);
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

    public static final class Context {
        public static final int TEMPLATE_CONSUMER_SLOT = 0;
        public static final int CRAFTING_HAMMER_SLOT = 1;
        public static final int ITEM_STAGE_SLOT = 2;
        public static final int RESULT_SLOT = 3;

        public static final int TOTAL_SLOTS = 4;
    }
}
