package net.mrgoddavid.minecraftthestoriesmod.thirst;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class ThirstClientManager {

    private static int thirst = ThirstManager.MAX_THIRST;

    private ThirstClientManager() {
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
