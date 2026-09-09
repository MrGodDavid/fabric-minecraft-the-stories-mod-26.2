package net.mrgoddavid.minecraftthestoriesmod.entity.client;

import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.MtsLogger;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 9/8/2026
 */
public class MtsEntityTextures {

    public static final Identifier BROWN_BEAR = register("brown_bear");

    private static Identifier register(@NonNull final String name) {
        return Constants.modId("textures/entity/" + name + "/" + name + ".png");
    }

    private static Identifier register(@NonNull final String type, @NonNull final String name) {
        return Constants.modId("textures/entity/" + type + "/" + name + ".png");
    }

    public static void register() {
        MtsLogger.info("Mts Entity Textures");
    }

    private MtsEntityTextures() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
