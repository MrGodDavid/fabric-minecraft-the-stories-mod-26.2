package net.mrgoddavid.minecraftthestoriesmod.utils.log;

import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 9/7/2026
 */
public final class MtsLogger {

    public static void header() {
        MinecraftTheStoriesMod.LOGGER.info("============= [BEGIN REGISTRATION] =============");
    }

    public static void tailer() {
        MinecraftTheStoriesMod.LOGGER.info("============= [FINISH REGISTRATION] =============");
    }

    public static void info(@NonNull final String name) {
        MinecraftTheStoriesMod.LOGGER.info("Registering {} for: \"" + MinecraftTheStoriesMod.MOD_ID + "\"", name);
    }

    public static void init(@NonNull final String msg) {
        MinecraftTheStoriesMod.LOGGER.info("[INITIALIZATION] {} for: \"" + MinecraftTheStoriesMod.MOD_ID + "\"", msg);
    }

    private MtsLogger() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
