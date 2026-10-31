package net.mrgoddavid.minecraftthestoriesmod.item;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

import java.util.Optional;

public final class MtsItemModelTemplates {

    public static final ModelTemplate SCALE_2X = register("item/scale_2x");

    private static ModelTemplate register(String path) {
        return new ModelTemplate(Optional.of(Constants.modId(path)), Optional.empty(), TextureSlot.LAYER0);
    }

    public static void register() {
        MtsLogger.info("Mts Item Model Templates");
    }

    private MtsItemModelTemplates() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
