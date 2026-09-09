package net.mrgoddavid.minecraftthestoriesmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ender_exalter.EnderExalterBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.content.enricher.EnricherBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor.OreCompressorBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.content.super_crafter.SuperCrafterBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.MtsLogger;

import static net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks.*;

/**
 * Holds all block entities.
 *
 * @author Mr. GodDavid
 * @since 8/17/2026
 */
public class MtsBlockEntities {

    public static final BlockEntityType<SuperCrafterBlockEntity> SUPER_CRAFTER_BE = register("super_crafter_be", SuperCrafterBlockEntity::new, SUPER_CRAFTER_BLOCK);
    public static final BlockEntityType<EnricherBlockEntity> ENRICHER_BE = register("enricher_be", EnricherBlockEntity::new, ENRICHER);
    public static final BlockEntityType<EnderExalterBlockEntity> ENDER_EXALTER_BE = register("ender_exalter_be", EnderExalterBlockEntity::new, ENDER_EXALTER);
    public static final BlockEntityType<OreCompressorBlockEntity> ORE_COMPRESSOR_BE = register("ore_compressor_be", OreCompressorBlockEntity::new, ORE_COMPRESSOR);

    public static void register() {
        MtsLogger.info("MTS Block Entities");
    }

    public static <T extends BlockEntity> BlockEntityType<T> register(String path, FabricBlockEntityTypeBuilder.Factory<T> factory, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Constants.modId(path),
                FabricBlockEntityTypeBuilder.create(factory, block).build());
    }

}
