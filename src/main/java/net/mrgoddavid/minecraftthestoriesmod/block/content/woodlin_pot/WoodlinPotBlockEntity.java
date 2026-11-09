package net.mrgoddavid.minecraftthestoriesmod.block.content.woodlin_pot;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsAbstractBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;

/**
 * @author Mr. GodDavid
 * @since 11/8/2026
 */
public class WoodlinPotBlockEntity extends MtsAbstractBlockEntity {

    public WoodlinPotBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MtsBlockEntities.WOODLIN_POT_BE, worldPosition, blockState);
    }

    /**
     * Defines inventory drop logics here.
     */
    @Override
    public void drops() {
    }
}
