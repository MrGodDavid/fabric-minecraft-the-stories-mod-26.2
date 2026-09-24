package net.mrgoddavid.minecraftthestoriesmod.entity.client;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 9/7/2026
 */
public class MtsEntityModelLayers {

    public static final ModelLayerLocation BROWN_BEAR = createMain("brown_bear");

    private static ModelLayerLocation createMain(@NonNull final String name) {
        return new ModelLayerLocation(Constants.modId(name), "main");
    }

    public static void register() {
        MtsLogger.info("Entity Model Layers");

        ModelLayerRegistry.registerModelLayer(MtsEntityModelLayers.BROWN_BEAR, BrownBearEntityModel::getTexturedModelData);
    }
}
