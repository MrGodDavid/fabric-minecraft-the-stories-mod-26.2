package net.mrgoddavid.minecraftthestoriesmod.block.custom.enricher;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsAbstractDataComponentContainerMenu;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsMenuTypes;

/**
 * Menu of Enricher.
 *
 * @author Mr. GodDavid
 * @since 8/22/2026
 */
public class EnricherMenu extends MtsAbstractDataComponentContainerMenu {

    private final Container inventory;
    private final ContainerData data;
    public final EnricherBlockEntity blockEntity;

    public EnricherMenu(int containerId, Inventory inventory, BlockPos pos) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(pos), new SimpleContainerData(EnricherBlockEntity.ContainerDataContext.DATA_ARRAY_SIZE));
    }

    public EnricherMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
        super(MtsMenuTypes.ENRICHER_MENU, containerId, inventory, data);
        super.registerTotalSlots(3);
        this.blockEntity = ((EnricherBlockEntity) blockEntity);
        this.data = data;
        this.inventory = this.blockEntity;

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
        int progress = data.get(EnricherBlockEntity.ContainerDataContext.PROGRESS_POSITION);
        int maxProgress = data.get(EnricherBlockEntity.ContainerDataContext.MAX_PROGRESS_POSITION);
        int arrowPixelSize = 18;
        return (maxProgress != 0 && progress != 0) ? progress * arrowPixelSize / maxProgress : 0;
    }

    public int getScaledWasteFluidProgress() {
        int wasteFluid = data.get(EnricherBlockEntity.ContainerDataContext.WASTE_FLUID_POSITION);
        int maxWasteFluid = data.get(EnricherBlockEntity.ContainerDataContext.MAX_WASTE_FLUID_POSITION);
        int wastePixelSize = 53;
        return (maxWasteFluid != 0 && wasteFluid != 0) ? wasteFluid * wastePixelSize / maxWasteFluid : 0;
    }

    public boolean isEnriching() {
        return data.get(EnricherBlockEntity.ContainerDataContext.PROGRESS_POSITION) > 0;
    }

    /**
     * I don't know what should I call this...
     *
     * @return a magical boolean value.
     */
    public boolean a() {
        return data.get(EnricherBlockEntity.ContainerDataContext.WASTE_FLUID_POSITION) > 0 || isEnriching();
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }

    @Override
    protected PlayerInventoryMenuCoordsContext registerContext() {
        return new PlayerInventoryMenuCoordsContext(8, 142, 8, 84);
    }
}
