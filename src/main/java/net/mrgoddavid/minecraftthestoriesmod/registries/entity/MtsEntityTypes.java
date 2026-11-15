package net.mrgoddavid.minecraftthestoriesmod.registries.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.target_dummy.TargetDummyEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.acaciania.AcacianiaEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.birchia.BirchiaEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.birchia.BirchiaEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.cherrinia.CherriniaEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.dense_oakie.DenseOakieEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.junmi.JunmiEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.light_oakie.LightOakieEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.mangrolino.MangrolinoEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie.OakieEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie.OakieHealingBallEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.sprucia.SpruciaEntity;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 9/7/2026
 */
@SuppressWarnings("SpellCheckingInspection")
public final class MtsEntityTypes {

    public static final EntityType<BrownBearEntity> BROWN_BEAR = register("brown_bear",
            EntityType.Builder.<BrownBearEntity>of(BrownBearEntity::new, MobCategory.CREATURE).sized(1.0f, 1.0f).eyeHeight(1.1875f));

    public static final EntityType<TargetDummyEntity> TARGET_DUMMY = register("target_dummy",
            EntityType.Builder.<TargetDummyEntity>of(TargetDummyEntity::new, MobCategory.MISC).sized(0.8f, 1.8f).eyeHeight(1.6f));

    public static final EntityType<AcacianiaEntity> ACACIANIA = register("acaciania",
            EntityType.Builder.<AcacianiaEntity>of(AcacianiaEntity::new, MobCategory.CREATURE).sized(0.375f, 0.625f).eyeHeight(0.3125f));

    public static final EntityType<BirchiaEntity> BIRCHIA = register("birchia",
            EntityType.Builder.<BirchiaEntity>of(BirchiaEntity::new, MobCategory.CREATURE).sized(0.375f, 0.625f).eyeHeight(0.3125f));

    public static final EntityType<CherriniaEntity> CHERRINIA = register("cherrinia",
            EntityType.Builder.<CherriniaEntity>of(CherriniaEntity::new, MobCategory.CREATURE).sized(0.375f, 0.625f).eyeHeight(0.3125f));

    public static final EntityType<DenseOakieEntity> DENSE_OAKIE = register("dense_oakie",
            EntityType.Builder.<DenseOakieEntity>of(DenseOakieEntity::new, MobCategory.CREATURE).sized(0.375f, 0.625f).eyeHeight(0.3125f));

    public static final EntityType<JunmiEntity> JUNMI = register("junmi",
            EntityType.Builder.<JunmiEntity>of(JunmiEntity::new, MobCategory.CREATURE).sized(0.375f, 0.625f).eyeHeight(0.3125f));

    public static final EntityType<MangrolinoEntity> MANGROLINO = register("mangrolino",
            EntityType.Builder.<MangrolinoEntity>of(MangrolinoEntity::new, MobCategory.CREATURE).sized(0.375f, 0.625f).eyeHeight(0.3125f));

    public static final EntityType<OakieEntity> OAKIE = register("oakie",
            EntityType.Builder.<OakieEntity>of(OakieEntity::new, MobCategory.MISC).sized(0.375f, 0.625f).eyeHeight(0.3125f));

    public static final EntityType<OakieHealingBallEntity> OAKIE_HEALING_BALL = register("oakie_healing_ball",
            EntityType.Builder.<OakieHealingBallEntity>of(OakieHealingBallEntity::new, MobCategory.MISC).sized(0.375f, 0.375f).eyeHeight(0.1875f));

    public static final EntityType<LightOakieEntity> LIGHT_OAKIE = register("light_oakie",
            EntityType.Builder.<LightOakieEntity>of(LightOakieEntity::new, MobCategory.CREATURE).sized(0.375f, 0.625f).eyeHeight(0.3125f));

    public static final EntityType<SpruciaEntity> SPRUCIA = register("sprucia",
            EntityType.Builder.<SpruciaEntity>of(SpruciaEntity::new, MobCategory.CREATURE).sized(0.375f, 0.625f).eyeHeight(0.3125f));

    private static <T extends Entity> EntityType<T> register(final String name, final EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Constants.modId(name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void register() {
        MtsLogger.info("Entity Type");
        registerAttributes();
    }

    public static void registerAttributes() {
        MtsLogger.info("Entity Attributes");

        FabricDefaultAttributeRegistry.register(BROWN_BEAR, BrownBearEntity.createMtsEntityAttributes());
        FabricDefaultAttributeRegistry.register(TARGET_DUMMY, TargetDummyEntity.createLivingAttributes());

        FabricDefaultAttributeRegistry.register(ACACIANIA, AcacianiaEntity.createMtsEntityAttributes());
        FabricDefaultAttributeRegistry.register(BIRCHIA, BirchiaEntity.createMtsEntityAttributes());
        FabricDefaultAttributeRegistry.register(CHERRINIA, CherriniaEntity.createMtsEntityAttributes());
        FabricDefaultAttributeRegistry.register(DENSE_OAKIE, DenseOakieEntity.createMtsEntityAttributes());
        FabricDefaultAttributeRegistry.register(JUNMI, JunmiEntity.createMtsEntityAttributes());
        FabricDefaultAttributeRegistry.register(MANGROLINO, MangrolinoEntity.createMtsEntityAttributes());
        FabricDefaultAttributeRegistry.register(OAKIE, OakieEntity.createMtsEntityAttributes());
        FabricDefaultAttributeRegistry.register(OAKIE_HEALING_BALL, OakieHealingBallEntity.createMtsEntityAttributes());
        FabricDefaultAttributeRegistry.register(LIGHT_OAKIE, LightOakieEntity.createMtsEntityAttributes());
        FabricDefaultAttributeRegistry.register(SPRUCIA, SpruciaEntity.createMtsEntityAttributes());
    }

    private MtsEntityTypes() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
