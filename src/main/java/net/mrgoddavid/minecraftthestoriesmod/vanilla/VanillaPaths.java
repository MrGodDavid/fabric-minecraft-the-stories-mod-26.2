package net.mrgoddavid.minecraftthestoriesmod.vanilla;

import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

/**
 * Holds a connection of the path of blocks in vanilla Minecraft.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class VanillaPaths {

    public static final String DEEPSLATE_DIAMOND_ORE = concatBlockPath("deepslate_diamond_ore");
    public static final String DEEPSLATE_EMERALD_ORE = concatBlockPath("deepslate_emerald_ore");
    public static final String DEEPSLATE_IRON_ORE = concatBlockPath("deepslate_iron_ore");
    public static final String DEEPSLATE_GOLD_ORE = concatBlockPath("deepslate_gold_ore");
    public static final String DIAMOND_ORE = concatBlockPath("diamond_ore");
    public static final String EMERALD_ORE = concatBlockPath("emerald_ore");
    public static final String IRON_ORE = concatBlockPath("iron_ore");
    public static final String GOLD_ORE = concatBlockPath("gold_ore");
    public static final String SHORT_GRASS = concatBlockPath("short_grass");

    public static final String OAK_LEAVES = concatBlockPath("oak_leaves");
    public static final String SPRUCE_LEAVES = concatBlockPath("spruce_leaves");
    public static final String BIRCH_LEAVES = concatBlockPath("birch_leaves");
    public static final String JUNGLE_LEAVES = concatBlockPath("jungle_leaves");
    public static final String ACACIA_LEAVES = concatBlockPath("acacia_leaves");
    public static final String CHERRY_LEAVES = concatBlockPath("cherry_leaves");
    public static final String DARK_OAK_LEAVES = concatBlockPath("dark_oak_leaves");
    public static final String PALE_OAK_LEAVES = concatBlockPath("pale_oak_leaves");
    public static final String MANGROVE_LEAVES = concatBlockPath("mangrove_leaves");

    public static final String CREEPER = concatMobPath("creeper");


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

    public static void registerVanillaEntityPaths() {
        MinecraftTheStoriesMod.LOGGER.info("Registering Vanilla Entity Paths for " + MinecraftTheStoriesMod.MOD_ID);
    }

    private VanillaPaths() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
