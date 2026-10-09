package net.mrgoddavid.minecraftthestoriesmod.client.particle;

/**
 * @author Mr. GodDavid
 * @since 10/7/2026
 */
public enum ParticleSpawnMode {

    /**
     * Spawns in a circle in 8 directions (north, northeast, east, southeast, south, southwest, west, and northwest)
     */
    CIRCLE_EIGHT_DIR,
    /**
     * Spawns in a circle in 16 directions.
     */
    CIRCLE_SIXTEEN_DIR,
    /**
     * Four corners of a square around the entity.
     */
    SQUARE_CORNER_DIR,
    /**
     * Four corners around the entity plus the midpoints between each of the adjacent corner.
     */
    SQUARE_EIGHT_PARTICLES_DIR,
    /**
     * Fill in side of square around the living entity.
     */
    SQUARE_FILL_IN;
}
