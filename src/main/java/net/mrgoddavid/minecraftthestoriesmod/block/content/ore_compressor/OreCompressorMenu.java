package net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsAbstractDataComponentContainerMenu;
import net.mrgoddavid.minecraftthestoriesmod.block.menu.MtsMenuTypes;

/**
 * Menu of Ore Compressor.
 *
 * @author Mr. GodDavid
 * @since 8/29/2026
 */
public class OreCompressorMenu extends MtsAbstractDataComponentContainerMenu {

    private final Container inventory;
    private final ContainerData data;
    private final OreCompressorBlockEntity blockEntity;

    public OreCompressorMenu(int containerId, Inventory inventory, BlockPos pos) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(pos), new SimpleContainerData(OreCompressorBlockEntity.ContainerDataContext.DATA_ARRAY_SIZE));
    }

    public OreCompressorMenu(int containerId, Inventory inventory, BlockEntity entity, ContainerData data) {
        super(MtsMenuTypes.ORE_COMPRESSOR_MENU, containerId, inventory, data);
        super.registerTotalSlots(3);
        this.blockEntity = ((OreCompressorBlockEntity) entity);
        this.data = data;
        this.inventory = this.blockEntity;

        this.addSlot(new Slot(this.inventory, OreCompressorBlockEntity.INPUT_SLOT, 62, 57));
        this.addSlot(new Slot(this.inventory, OreCompressorBlockEntity.FUEL_SLOT, 62, 29));
        this.addSlot(new Slot(this.inventory, OreCompressorBlockEntity.OUTPUT_SLOT, 129, 44) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return false;
            }
        });

        addDataSlots(this.data);
    }

    public int getScaledPressPlateProgress() {
        int pressProgress = this.data.get(OreCompressorBlockEntity.ContainerDataContext.PLATE_PRESSING_PROGRESS_POSITION);
        int maxPressProgress = this.data.get(OreCompressorBlockEntity.ContainerDataContext.MAX_PLATE_PRESSING_PROGRESS_POSITION);
        int pressPlatePixelSize = 8; // height
        return (maxPressProgress != 0 && pressProgress != 0) ? pressProgress * pressPlatePixelSize / maxPressProgress : 0;
    }

    public int getScaledArrowProgress() {
        int progress = this.data.get(OreCompressorBlockEntity.ContainerDataContext.PROGRESS_POSITION);
        int maxProgress = this.data.get(OreCompressorBlockEntity.ContainerDataContext.MAX_PROGRESS_POSITION);
        int arrowPixelSize = 27;
        return (maxProgress != 0 && progress != 0) ? progress * arrowPixelSize / maxProgress : 0;
    }

    public int getScaledFuelProgress() {
        int remainingFuel = this.data.get(OreCompressorBlockEntity.ContainerDataContext.REMAINING_FUEL_POSITION);
        int maxFuelAmount = this.data.get(OreCompressorBlockEntity.ContainerDataContext.MAX_FUEL_POSITION);
        int fuelPixelSize = 70;
        return (maxFuelAmount != 0 && remainingFuel != 0) ? remainingFuel * fuelPixelSize / maxFuelAmount : 0;
    }

    @Override
    protected PlayerInventoryMenuCoordsContext registerContext() {
        return new PlayerInventoryMenuCoordsContext(7, 164, 7, 106);
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }

    public boolean isCompressing() {
        return data.get(OreCompressorBlockEntity.ContainerDataContext.PROGRESS_POSITION) > 0;
    }

    public boolean a() {
        return data.get(OreCompressorBlockEntity.ContainerDataContext.REMAINING_FUEL_POSITION) > 0 || isCompressing();
    }
}
