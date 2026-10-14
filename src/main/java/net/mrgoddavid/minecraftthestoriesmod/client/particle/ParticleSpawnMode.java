package net.mrgoddavid.minecraftthestoriesmod.client.particle;

/**
 * Mode of spawning shape of particles around the living entity.
 *
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
    SQUARE_FILL_IN,
    /**
     * Spawn particle in a shape of equilibrium triangle around the living entity.
     */
    EQUILIBRIUM_TRIANGLE_VERTEX,
    /**
     * Continuously spawn particle on each side of an equilibrium triangle.
     */
    EQUILIBRIUM_TRIANGLE_CONTINUOUS,
    /**
     * Spawn particle in a normal triangle. (The user needs to define the coordinate offset around the living entity.)
     */
    TRIANGLE_VERTEX,
    /**
     * Spawn particle in a normal triangle continuously. (The user needs to define the coordinate offset around the
     * living entity.)
     */
    TRIANGLE_VERTEX_CONTINUOUS,
    /**
     * Fill the circular region with eight-dir circle offset coordinate offsets.
     */
    FILL_CIRCLE_EIGHT_DIR,
    /**
     * Fill the circular region with sixteen-dir circle offset coordinate offsets.
     */
    FILL_CIRCLE_SIXTEEN_DIR;
}
