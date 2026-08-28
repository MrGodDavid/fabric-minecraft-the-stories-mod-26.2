package net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsAbstractBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;

/**
 * Ore Compressor block entity.
 *
 * @author Mr. GodDavid
 * @since 8/28/2026
 */
public class OreCompressorBlockEntity extends MtsAbstractBlockEntity {

    public OreCompressorBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MtsBlockEntities.ORE_COMPRESSOR_BE, worldPosition, blockState);
    }

    /**
     * Defines inventory drop logics here.
     */
    @Override
    public void drops() {
//        super.defaultDrops();
    }
}
