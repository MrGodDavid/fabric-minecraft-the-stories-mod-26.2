package net.mrgoddavid.minecraftthestoriesmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ender_exalter.EnderExalterBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.content.enricher.EnricherBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor.OreCompressorBlockEntity;
import net.mrgoddavid.minecraftthestoriesmod.block.content.super_crafter.SuperCrafterBlockEntity;

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

    public static void registerBlockEntities() {
        MinecraftTheStoriesMod.LOGGER.info("Registering Block Entities for " + MinecraftTheStoriesMod.MOD_ID);
    }

    public static <T extends BlockEntity> BlockEntityType<T> register(String path, FabricBlockEntityTypeBuilder.Factory<T> factory, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, path),
                FabricBlockEntityTypeBuilder.create(factory, block).build());
    }

}
