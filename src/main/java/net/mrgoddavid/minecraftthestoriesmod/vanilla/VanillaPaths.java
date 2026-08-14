package net.mrgoddavid.minecraftthestoriesmod.vanilla;

/**
 * Holds a connection of the path of blocks in vanilla Minecraft.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class VanillaPaths {

    public static final String SHORT_GRASS;
    public static final String DIAMOND_ORE;
    public static final String DEEPSLATE_DIAMOND_ORE;

    public static final String CREEPER;

    static {
        SHORT_GRASS = concatBlockPath("short_grass");
        DIAMOND_ORE = concatBlockPath("diamond_ore");
        DEEPSLATE_DIAMOND_ORE =  concatBlockPath("deepslate_diamond_ore");

        CREEPER = concatMobPath("creeper");
    }

    /**
     * Concates the block path initial with the name of the block.
     *
     * @param blockName name of the block.
     * @return the concated path of the block.
     */
    private static String concatBlockPath(final String blockName) {
        return "blocks/" + blockName;
    }

    private static String concatMobPath(final String mobName) {
        return "entities/" + mobName;
    }
}
