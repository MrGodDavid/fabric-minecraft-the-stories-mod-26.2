package net.mrgoddavid.minecraftthestoriesmod.vanilla;

/**
 * Holds a connection of the path of blocks in vanilla Minecraft.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class VanillaPaths {

    public static final String DEEPSLATE_DIAMOND_ORE;
    public static final String DEEPSLATE_EMERALD_ORE;
    public static final String DEEPSLATE_IRON_ORE;
    public static final String DEEPSLATE_GOLD_ORE;
    public static final String DIAMOND_ORE;
    public static final String EMERALD_ORE;
    public static final String IRON_ORE;
    public static final String GOLD_ORE;
    public static final String SHORT_GRASS;

    public static final String CREEPER;

    static {
        DEEPSLATE_DIAMOND_ORE = concatBlockPath("deepslate_diamond_ore");
        DEEPSLATE_EMERALD_ORE = concatBlockPath("deepslate_emerald_ore");
        DEEPSLATE_IRON_ORE = concatBlockPath("deepslate_iron_ore");
        DEEPSLATE_GOLD_ORE = concatBlockPath("deepslate_gold_ore");
        DIAMOND_ORE = concatBlockPath("diamond_ore");
        EMERALD_ORE = concatBlockPath("emerald_ore");
        IRON_ORE = concatBlockPath("iron_ore");
        GOLD_ORE = concatBlockPath("gold_ore");
        SHORT_GRASS = concatBlockPath("short_grass");

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
