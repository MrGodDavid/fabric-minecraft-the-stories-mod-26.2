package net.mrgoddavid.minecraftthestoriesmod.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsAbstractBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;

/**
 * Block entity for Enricher.
 *
 * @author Mr. GodDavid
 * @since 8/17/2026
 */
public class EnricherBlockEntity extends MtsAbstractBlockEntity {

    public EnricherBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MtsBlockEntities.ENRICHER_BE, worldPosition, blockState);
    }
}
