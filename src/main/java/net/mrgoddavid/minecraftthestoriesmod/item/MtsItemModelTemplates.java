package net.mrgoddavid.minecraftthestoriesmod.item;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.MtsLogger;

import java.util.Optional;

public class MtsItemModelTemplates {

    public static final ModelTemplate SCALE_2X = register("item/scale_2x");

    private static ModelTemplate register(String path) {
        return new ModelTemplate(Optional.of(Constants.modId(path)), Optional.empty(), TextureSlot.LAYER0);
    }

    public static void register() {
        MtsLogger.info("Mts Item Model Templates");
    }
}
