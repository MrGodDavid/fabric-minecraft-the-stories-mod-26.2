package net.mrgoddavid.minecraftthestoriesmod.block.screen;

import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

/**
 * This class holds a collection of texture paths of MTS mod.
 *
 * @author Mr GodDavid
 * @since 8/29/2026
 */
public class MtsScreenTextures {

    public static final Identifier ENDER_EXALTER_GUI = registerTexturePath("ender_exalter", "ender_exalter_gui");
    public static final Identifier ENRICHER_GUI = registerTexturePath("enricher", "enricher_gui");
    public static final Identifier ENRICHER_PROGRESS_ARROW = registerTexturePath("enricher", "enricher_progress_bar");
    public static final Identifier ENRICHER_WASTE_BAR = registerTexturePath("enricher", "enricher_waste_liquid");
    public static final Identifier SUPER_CRAFTER_GUI = registerTexturePath("super_crafter", "super_crafter_gui");
    public static final Identifier ORE_COMPRESSOR_GUI = registerTexturePath("ore_compressor", "ore_compressor_gui");
    public static final Identifier ORE_COMPRESSOR_TOP_PRESS_PLATE = registerTexturePath("ore_compressor", "ore_compressor_top_press_plate");
    public static final Identifier ORE_COMPRESSOR_BOTTOM_PRESS_PLATE = registerTexturePath("ore_compressor", "ore_compressor_bottom_press_plate");
    public static final Identifier ORE_COMPRESSOR_PROGRESS_ARROW = registerTexturePath("ore_compressor", "ore_compressor_progress_arrow");
    public static final Identifier ORE_COMPRESSOR_BLUE_FUEL_BAR = registerTexturePath("ore_compressor", "ore_compressor_blue_fuel_bar");

    private static Identifier registerTexturePath(final String block, final String name) {
        return Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, path(block, name));
    }

    private static String path(final String block, final String name) {
        return "textures/gui/menu/" + block + "/" + name + ".png";
    }

    public static void register() {
        MinecraftTheStoriesMod.LOGGER.info("Registering Mts Screen Textures for " + MinecraftTheStoriesMod.MOD_ID);
    }

    private MtsScreenTextures() throws IllegalAccessException {
        throw new IllegalAccessException("Class MtsScreenTextures cannot be instantiated!");
    }
}
