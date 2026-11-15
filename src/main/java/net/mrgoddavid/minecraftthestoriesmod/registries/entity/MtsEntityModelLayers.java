package net.mrgoddavid.minecraftthestoriesmod.registries.entity;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.target_dummy.TargetDummyModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.acaciania.AcacianiaEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.birchia.BirchiaEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.cherrinia.CherriniaEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.dense_oakie.DenseOakieEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.junmi.JunmiEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.light_oakie.LightOakieEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.mangrolino.MangrolinoEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie.OakieEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie.OakieHealingBallEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.sprucia.SpruciaEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 9/7/2026
 */
@SuppressWarnings("SpellCheckingInspection")
public final class MtsEntityModelLayers {

    public static final ModelLayerLocation ORE_COMPRESSOR = createMain("ore_compressor");
    public static final ModelLayerLocation WOODLIN_POT = createMain("woodlinpot");

    public static final ModelLayerLocation BROWN_BEAR = createMain("brown_bear");
    public static final ModelLayerLocation TARGET_DUMMY = createMain("target_dummy");
    public static final ModelLayerLocation ACACIANIA = createMain("acaciania");
    public static final ModelLayerLocation BIRCHIA = createMain("birchia");
    public static final ModelLayerLocation CHERRINIA = createMain("cherrinia");
    public static final ModelLayerLocation DENSE_OAKIE = createMain("dense_oakie");
    public static final ModelLayerLocation JUNMI = createMain("junmi");
    public static final ModelLayerLocation MANGROLINO = createMain("mangrolino");
    public static final ModelLayerLocation OAKIE = createMain("oakie");
    public static final ModelLayerLocation OAKIE_HEALING_BALL = createMain("oakie_healing_ball");
    public static final ModelLayerLocation LIGHT_OAKIE = createMain("light_oakie");
    public static final ModelLayerLocation SPRUCIA = createMain("sprucia");

    private static ModelLayerLocation createMain(@NonNull final String name) {
        return new ModelLayerLocation(Constants.modId(name), "main");
    }

    public static void register() {
        MtsLogger.info("Entity Model Layers");

        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.BROWN_BEAR, BrownBearEntityModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.TARGET_DUMMY, TargetDummyModel::getTexturedModelData);

        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.ACACIANIA, AcacianiaEntityModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.BIRCHIA, BirchiaEntityModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.CHERRINIA, CherriniaEntityModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.DENSE_OAKIE, DenseOakieEntityModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.JUNMI, JunmiEntityModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.MANGROLINO, MangrolinoEntityModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.OAKIE, OakieEntityModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.OAKIE_HEALING_BALL, OakieHealingBallEntityModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.LIGHT_OAKIE, LightOakieEntityModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.SPRUCIA, SpruciaEntityModel::getTexturedModelData);
    }

    private MtsEntityModelLayers() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
