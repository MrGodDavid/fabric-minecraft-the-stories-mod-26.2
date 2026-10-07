package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.target_dummy.TargetDummyEntity;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 9/7/2026
 */
public final class MtsEntityTypes {

    public static final EntityType<BrownBearEntity> BROWN_BEAR = register("brown_bear",
            EntityType.Builder.<BrownBearEntity>of(BrownBearEntity::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .eyeHeight(1.1875f)
    );
    public static final EntityType<TargetDummyEntity> TARGET_DUMMY = register("target_dummy",
            EntityType.Builder.<TargetDummyEntity>of(TargetDummyEntity::new, MobCategory.MISC)
                    .sized(0.8f, 1.8f)
                    .eyeHeight(1.6f)
    );

    private static <T extends Entity> EntityType<T> register(final String name, final EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Constants.modId(name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void register() {
        MtsLogger.info("Entity Type");
    }

    public static void registerAttributes() {
        MtsLogger.info("Entity Attributes");

        FabricDefaultAttributeRegistry.register(BROWN_BEAR, BrownBearEntity.createCubeAttributes());
        FabricDefaultAttributeRegistry.register(TARGET_DUMMY, TargetDummyEntity.createLivingAttributes());
    }
}
