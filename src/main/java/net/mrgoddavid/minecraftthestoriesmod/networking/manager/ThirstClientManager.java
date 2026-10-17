package net.mrgoddavid.minecraftthestoriesmod.networking.manager;

import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class ThirstClientManager {

    private static int thirst = ThirstManager.MAX_THIRST;

    private ThirstClientManager() throws IllegalAccessException {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }

    public static int getThirst() {
        return thirst;
    }

    public static void setThirst(int thirst) {
        ThirstClientManager.thirst = thirst;
    }

    public static void reset() {
        thirst = ThirstManager.MAX_THIRST;
    }
}
