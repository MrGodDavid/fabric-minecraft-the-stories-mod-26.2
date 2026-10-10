package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 10/9/2026
 */
public final class MtsEntityAttributes {

    private static final List<EntityType<? extends LivingEntity>> MODIFIED_VANILLA_PASSIVE_MOBS = List.of(
            EntityTypes.ALLAY,
            EntityTypes.ARMADILLO,
            EntityTypes.AXOLOTL,
            EntityTypes.BAT,
            EntityTypes.CAMEL,
            EntityTypes.CAMEL_HUSK,
            EntityTypes.CAT,
            EntityTypes.CHICKEN,
            EntityTypes.COD,
            EntityTypes.COPPER_GOLEM,
            EntityTypes.COW,
            EntityTypes.DONKEY,
            EntityTypes.FROG,
            EntityTypes.GLOW_SQUID,
            EntityTypes.HAPPY_GHAST,
            EntityTypes.HORSE,
            EntityTypes.MOOSHROOM,
            EntityTypes.MULE,
            EntityTypes.OCELOT,
            EntityTypes.PARROT,
            EntityTypes.PIG,
            EntityTypes.RABBIT,
            EntityTypes.SALMON,
            EntityTypes.SHEEP,
            EntityTypes.SKELETON_HORSE,
            EntityTypes.SNIFFER,
            EntityTypes.SNOW_GOLEM,
            EntityTypes.SQUID,
            EntityTypes.STRIDER,
            EntityTypes.SULFUR_CUBE,
            EntityTypes.TADPOLE,
            EntityTypes.TROPICAL_FISH,
            EntityTypes.TURTLE,
            EntityTypes.VILLAGER,
            EntityTypes.WANDERING_TRADER,
            EntityTypes.ZOMBIE_HORSE
    );

    public static void register() {
        MtsLogger.info("Vanilla Custom Entity Attributes");

        FabricDefaultAttributeRegistry.MODIFY.register(context ->
                context.modify(MODIFIED_VANILLA_PASSIVE_MOBS, (entityType, builder) ->
                        builder.add(Attributes.ATTACK_DAMAGE, 3.0)));
    }

    private MtsEntityAttributes() {
    }
}
