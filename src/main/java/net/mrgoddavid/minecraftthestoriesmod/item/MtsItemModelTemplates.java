package net.mrgoddavid.minecraftthestoriesmod.item;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

import java.util.Optional;

public class MtsItemModelTemplates {

    public static final ModelTemplate SCALE_2X = register("item/scale_2x");

    private static ModelTemplate register(String path) {
        return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, path)), Optional.empty(), TextureSlot.LAYER0);
    }

    public static void registerTemplates() {
        MinecraftTheStoriesMod.LOGGER.info("Registering MtsItemModelTemplates for " + MinecraftTheStoriesMod.MOD_ID);
    }
}
