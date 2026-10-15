package net.mrgoddavid.minecraftthestoriesmod.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.Timer;
import net.mrgoddavid.minecraftthestoriesmod.utils.app.dave.RandomPulse;
import net.mrgoddavid.minecraftthestoriesmod.utils.list.MtsElementSets;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ClassNameFormatter;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

import java.util.*;

/**
 * Holds some designs of spawning particles.
 *
 * @author Mr. GodDavid
 * @since 10/7/2026
 */
public final class MtsParticleSpawner {

    private static final float RANDOMNESS = 0.5f;
    private static final Map<EffectKey, Timer> PARTICLE_OPTIONS_TIMER_MAP = new HashMap<>();

    private MtsParticleSpawner() {
    }

    private static Timer getOrCreateTimer(final UUID entityUUID, final String effectId, final Timer initialTimer) {
        EffectKey key = new EffectKey(entityUUID, effectId);
        return PARTICLE_OPTIONS_TIMER_MAP.computeIfAbsent(key, ignored -> initialTimer);
    }

    public static void fillCircle(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final UUID entityUUID,
            final String effectId,
            final Timer timer,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double radius,
            final int density,
            final ParticleSpawnMode mode,
            final boolean randomness
    ) {
        Timer effectTimer = getOrCreateTimer(entityUUID, effectId, timer);
        double step = radius / (density + 1.0D);
        float halfBoundingBoxWidth = renderState.boundingBoxWidth / 2;
        float minimumDistance = halfBoundingBoxWidth * halfBoundingBoxWidth;

        if (effectTimer.pass(() -> true)) {
            switch (mode) {
                case FILL_CIRCLE_EIGHT_DIR ->
                        doFillPatternedEightDirCircle(mc, pulse, particleType, renderState, yOffset, radius, randomness, minimumDistance, step);
                case FILL_CIRCLE_SIXTEEN_DIR ->
                        doFillPatternedSixteenDirCircle(mc, pulse, particleType, renderState, yOffset, radius, randomness, minimumDistance, step);
            }
            effectTimer.reset();
        }
    }

    private static void doFillPatternedSixteenDirCircle(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double radius,
            final boolean randomness,
            final float minimumDistance,
            final double step
    ) {
        doFillPatternedCircleInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SIXTEEN_DIR_OFFSET, yOffset, radius, randomness, minimumDistance, step);
    }

    private static void doFillPatternedEightDirCircle(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double radius,
            final boolean randomness,
            final float minimumDistance,
            final double step
    ) {
        doFillPatternedCircleInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.EIGHT_DIR_OFFSET, yOffset, radius, randomness, minimumDistance, step);
    }

    private static void doFillPatternedCircleInner(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final MtsElementSets.Pair<Double, Double>[] offsets,
            final double yOffset,
            final double radius,
            final boolean randomness,
            final float minimumDistance,
            final double step
    ) {
        for (double distance = minimumDistance; distance <= radius; distance += step) {
            doSpawnParticleClusterPatternInner(mc, pulse, particleType, renderState, offsets, yOffset, distance, randomness);
        }
    }

    /**
     * Spawn particles in a triangular pattern.
     *
     * @param mc           instance of Minecraft.
     * @param randomPulse  randomly pulsing thing.
     * @param particleType type of particle.
     * @param renderState  contains information of the living entity in the center of the pattern.
     * @param yOffset      the height, relatives to the entity's world position, of the y position of the particles.
     * @param size         how large the pattern of the particle is.
     * @param times        the number of particles IN BETWEEN the two vertices.
     * @param vertexRule   the rule in which defines the coordinates of each vertex in the spawning pattern.
     * @param mode         spawning mode.
     * @param randomness   a boolean variable that controls whether the particle should have a bit of randomness while
     *                     spawning.
     * @apiNote If you are using the {@link ParticleSpawnMode#TRIANGLE_VERTEX} mode, fills
     * {@link Constants.Universal#DOUBLE_PLACEHOLDER} to the parameter "size" and
     * {@link Constants.Universal#INTEGER_PLACEHOLDER} to the parameter "times" since we don't need these two
     * parameters.
     */
    public static void spawnTriangle(
            final Minecraft mc,
            final RandomPulse randomPulse,
            final ParticleOptions particleType,
            final UUID entityUUID,
            final String effectId,
            final Timer timer,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double size,
            final int times,
            final VertexRule vertexRule,
            final ParticleSpawnMode mode,
            final boolean randomness
    ) {
        Timer effectTimer = getOrCreateTimer(entityUUID, effectId, timer);

        if (effectTimer.pass(() -> true)) {
            if (mode == ParticleSpawnMode.TRIANGLE_VERTEX) {
                doSpawnTriangleWithGivenVertices(mc, randomPulse, particleType, renderState, yOffset, 1, vertexRule, randomness);
            } else if (mode == ParticleSpawnMode.TRIANGLE_VERTEX_CONTINUOUS) {
                doSpawnParticleInContinuousNormalTriangle(mc, randomPulse, particleType, renderState, yOffset, size, vertexRule.buildRule(), times, randomness);
            }
            effectTimer.reset();
        }
    }

    private static void doSpawnTriangleWithGivenVertices(
            final Minecraft mc,
            final RandomPulse randomPulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double size,
            final VertexRule vertexRule,
            final boolean randomness
    ) {
        MtsElementSets.Pair<Double, Double>[] vertices = vertexRule.buildRule();
        if (vertices.length > 3) {
            throw new IllegalArgumentException("Bad argument because trying to spawn a triangular pattern with more than three vertices! :( Current number of vertices: " + vertices.length);
        }
        doSpawnParticleClusterPatternInner(mc, randomPulse, particleType, renderState, vertexRule.buildRule(), yOffset, size, randomness);
    }

    public static void spawnTriangle(
            final Minecraft mc,
            final RandomPulse randomPulse,
            final ParticleOptions particleType,
            final UUID entityUUID,
            final String effectId,
            final Timer timer,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double size,
            final int times,
            final ParticleSpawnMode mode,
            final boolean randomness
    ) {
        Timer effectTimer = getOrCreateTimer(entityUUID, effectId, timer);
        if (effectTimer.pass(() -> true)) {
            switch (mode) {
                case EQUILIBRIUM_TRIANGLE_VERTEX ->
                        doSpawnParticleInEquilibriumTriangle(mc, randomPulse, particleType, renderState, yOffset, size, randomness);
                case EQUILIBRIUM_TRIANGLE_CONTINUOUS ->
                        doSpawnParticleInContinuousEquilibriumTriangle(mc, randomPulse, particleType, renderState, yOffset, size, times, randomness);
                default ->
                        MtsLogger.warn("Invalid mode: " + "[" + mode + "] when spawning particles at entity: " + ClassNameFormatter.extractEntityNameFromEntityRenderState(renderState));
            }
            effectTimer.reset();
        }
    }

    private static void doSpawnParticleInContinuousNormalTriangle(
            final Minecraft mc,
            final RandomPulse randomPulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double size,
            final MtsElementSets.Pair<Double, Double>[] vertices,
            final int times,
            final boolean randomness
    ) {
        if (mc.level == null) return;
        if (vertices.length > 3) {
            throw new IllegalArgumentException("Bad argument because trying to spawn a triangular pattern with more than three vertices! :( Current number of vertices: " + vertices.length);
        }

        double centerX = renderState.x;
        double centerY = renderState.y;
        double centerZ = renderState.z;
        double randomX = randomness ? Mth.randomBetween(mc.level.getRandom(), -RANDOMNESS, RANDOMNESS) : 0.0d;
        double randomZ = randomness ? Mth.randomBetween(mc.level.getRandom(), -RANDOMNESS, RANDOMNESS) : 0.0d;
        for (int side = 0; side < 3; side++) {
            MtsElementSets.Pair<Double, Double> firstPoint = vertices[side];
            MtsElementSets.Pair<Double, Double> secondPoint = vertices[(side + 1) % 3];
            double dx = (secondPoint.first() - firstPoint.first()) * size;
            double dz = (secondPoint.second() - firstPoint.second()) * size;
            double xStep = dx / (times + 1d);
            double zStep = dz / (times + 1d);
            double xOffset = firstPoint.first() * size;
            double zOffset = firstPoint.second() * size;
            for (int point = 0; point <= times; point++) {
                if (randomPulse.isLock()) {
                    break;
                }
                mc.level.addParticle(particleType,
                        centerX + xOffset + randomX,
                        centerY + yOffset,
                        centerZ + zOffset + randomZ,
                        0.0d, 0.0d, 0.0d
                );
                xOffset += xStep;
                zOffset += zStep;
            }
        }
    }

    private static void doSpawnParticleInContinuousEquilibriumTriangle(
            final Minecraft mc,
            final RandomPulse randomPulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double size,
            final int times,
            final boolean randomness
    ) {
        doSpawnParticleInContinuousNormalTriangle(mc, randomPulse, particleType, renderState, yOffset, size, Constants.ParticleSpawnOffsets.EQUILIBRIUM_TRIANGLE_VERTEX_OFFSET, times, randomness);
    }

    private static void doSpawnParticleInEquilibriumTriangle(
            final Minecraft mc,
            final RandomPulse randomPulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double size,
            final boolean randomness) {

        doSpawnParticleClusterPatternInner(mc, randomPulse, particleType, renderState, Constants.ParticleSpawnOffsets.EQUILIBRIUM_TRIANGLE_VERTEX_OFFSET, yOffset, size, randomness);
    }

    public static void spawnSquare(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final UUID entityUUID,
            final String effectId,
            final Timer timer,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double size,
            final ParticleSpawnMode mode,
            final boolean randomness
    ) {
        spawnSquare(mc, pulse, particleType, entityUUID, effectId, timer, renderState, yOffset, size, 1, mode, randomness);
    }

    /**
     * Spawn particles in a square pattern.
     *
     * @param mc           instance of Minecraft.
     * @param pulse        randomly pulsing thing.
     * @param particleType type of particle.
     * @param renderState  contains information of the living entity in the center of the pattern.
     * @param yOffset      the height, relatives to the entity's world position, of the y position of the particles.
     * @param size         how large the pattern of the particle is.
     * @param times        the number of particles IN BETWEEN the two vertices.
     * @param mode         spawning mode.
     * @param randomness   a boolean variable that controls whether the particle should have a bit of randomness while
     *                     spawning.
     */
    public static void spawnSquare(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final UUID entityUUID,
            final String effectId,
            final Timer timer,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double size,
            final int times,
            final ParticleSpawnMode mode,
            final boolean randomness
    ) {
        Timer effectTimer = getOrCreateTimer(entityUUID, effectId, timer);
        if (effectTimer.pass(() -> true)) {
            switch (mode) {
                case SQUARE_CORNER_DIR ->
                        doSpawnSquareCornerParticle(mc, pulse, particleType, renderState, yOffset, size, randomness);
                case SQUARE_EIGHT_PARTICLES_DIR ->
                        doSpawnSquareEightParticle(mc, pulse, particleType, renderState, yOffset, size, randomness);
                case SQUARE_FILL_IN -> {
                    if (times <= 0) {
                        throw new IllegalArgumentException("Trying to spawn 0 particles per one side using continuous spawning. " +
                                "Times under continuous mode: [" + times + "] must be positive!");
                    }
                    doSpawnSquareFilledInSideParticle(mc, pulse, particleType, renderState, yOffset, size, times, randomness);
                }
                default ->
                        MtsLogger.warn("Invalid mode: " + "[" + mode + "] when spawning particles at entity: " + ClassNameFormatter.extractEntityNameFromEntityRenderState(renderState));
            }
            System.out.println("HERE");
            effectTimer.reset();
        }
    }

    private static void doSpawnSquareFilledInSideParticle(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double size,
            final int times,
            final boolean randomness
    ) {
        doSpawnParticleContinuousSquarePatternInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SQUARE_CORNER_OFFSET, yOffset, size, times, randomness);
    }

    private static void doSpawnSquareEightParticle(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double size,
            final boolean randomness
    ) {
        doSpawnParticleClusterPatternInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SQUARE_EIGHT_PARTICLE_OFFSET, yOffset, size, randomness);
    }

    private static void doSpawnSquareCornerParticle(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double size,
            final boolean randomness
    ) {
        doSpawnParticleClusterPatternInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SQUARE_CORNER_OFFSET, yOffset, size, randomness);
    }

    /**
     * Spawn particles in a circular pattern.
     *
     * @param mc           instance of Minecraft.
     * @param pulse        randomly pulsing thing.
     * @param particleType type of particle.
     * @param renderState  contains information of the living entity in the center of the pattern.
     * @param yOffset      the height, relatives to the entity's world position, of the y position of the particles.
     * @param radius       how large the pattern of the particle is.
     * @param mode         spawning mode.
     * @param randomness   a boolean variable that controls whether the particle should have a bit of randomness while
     *                     spawning.
     */
    public static void spawnCircle(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final UUID entityUUID,
            final String effectId,
            final Timer timer,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double radius,
            final ParticleSpawnMode mode,
            final boolean randomness
    ) {
        Timer effectTimer = getOrCreateTimer(entityUUID, effectId, timer);
        if (effectTimer.pass(() -> true)) {
            switch (mode) {
                case CIRCLE_EIGHT_DIR ->
                        doSpawnEightDirParticle(mc, pulse, particleType, renderState, yOffset, radius, randomness);
                case CIRCLE_SIXTEEN_DIR ->
                        doSpawnSixteenDirParticle(mc, pulse, particleType, renderState, yOffset, radius, randomness);
                default ->
                        MtsLogger.warn("Invalid mode: " + "[" + mode + "] when spawning particles at entity: " + ClassNameFormatter.extractEntityNameFromEntityRenderState(renderState));
            }
            effectTimer.reset();
        }
    }

    private static void doSpawnSixteenDirParticle(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double radius,
            final boolean randomness
    ) {
        doSpawnParticleClusterPatternInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SIXTEEN_DIR_OFFSET, yOffset, radius, randomness);
    }

    private static void doSpawnEightDirParticle(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final double yOffset,
            final double radius,
            final boolean randomness
    ) {
        doSpawnParticleClusterPatternInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.EIGHT_DIR_OFFSET, yOffset, radius, randomness);
    }

    private static void doSpawnParticleContinuousSquarePatternInner(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final MtsElementSets.Pair<Double, Double>[] squareCornerOffset,
            final double yOffset,
            final double size,
            final int countOfPointPerSide,
            final boolean randomness
    ) {
        if (mc.level == null) return;
        if (countOfPointPerSide <= 0) return;

        double randomX = Mth.randomBetween(mc.level.getRandom(), -RANDOMNESS, RANDOMNESS);
        double randomZ = Mth.randomBetween(mc.level.getRandom(), -RANDOMNESS, RANDOMNESS);

        double centerX = renderState.x;
        double centerY = renderState.y;
        double centerZ = renderState.z;
        double step = size * 2 / ((double) (countOfPointPerSide + 1));

        for (int side = 0; side < 4; side++) {
            // include the corner. (corner is the starting point of each side.)
            double xOffset = squareCornerOffset[side].first() * size;
            double zOffset = squareCornerOffset[side].second() * size;
            for (int point = 0; point <= countOfPointPerSide; point++) { // never uses the "point", it just serves the iteration purpose
                switch (side) {
                    case 0 -> zOffset -= step;
                    case 1 -> xOffset -= step;
                    case 2 -> zOffset += step;
                    case 3 -> xOffset += step;
                    default ->
                            throw new IllegalStateException("Invalid side: " + side + ". Reason of throwing this exception here " +
                                    "is because the side index should be from 0 to 3. Check the countOfPointPerSide parameter!");
                }

                mc.level.addParticle(particleType,
                        centerX + xOffset + (randomness ? randomX : 0.0d),
                        centerY + yOffset,
                        centerZ + zOffset + (randomness ? randomZ : 0.0d),
                        0.0d, 0.0d, 0.0d
                );
            }
        }
    }

    private static void doSpawnParticleClusterPatternInner(
            final Minecraft mc,
            final RandomPulse pulse,
            final ParticleOptions particleType,
            final LivingEntityRenderState renderState,
            final MtsElementSets.Pair<Double, Double>[] offsetPresets,
            final double yOffset,
            final double size,
            final boolean randomness
    ) {
        if (mc.level == null) return;

        double randomX = Mth.randomBetween(mc.level.getRandom(), -RANDOMNESS, RANDOMNESS);
        double randomZ = Mth.randomBetween(mc.level.getRandom(), -RANDOMNESS, RANDOMNESS);

        double centerX = renderState.x;
        double centerY = renderState.y + yOffset;
        double centerZ = renderState.z;

        for (MtsElementSets.Pair<Double, Double> offsetXZCoordinate : offsetPresets) {
            if (pulse.isLock()) continue;
            mc.level.addParticle(particleType,
                    centerX + offsetXZCoordinate.first() * size + (randomness ? randomX : 0.0d),
                    centerY,
                    centerZ + offsetXZCoordinate.second() * size + (randomness ? randomZ : 0.0d),
                    0.0d, 0.0d, 0.0d);
        }
    }

    public static VertexRuleBuilder vertexRuleBuilder() {
        return new VertexRuleBuilder();
    }

    /**
     * @author Mr. GodDavid
     * @since 10/13/2026
     */
    @FunctionalInterface
    public interface VertexRule {

        MtsElementSets.Pair<Double, Double>[] buildRule();
    }

    /**
     * @author Mr. GodDavid
     * @since 10/13/2026
     */
    public static final class VertexRuleBuilder {

        private final List<MtsElementSets.Pair<Double, Double>> offsetVertices;

        private VertexRuleBuilder() {
            offsetVertices = new ArrayList<>();
        }

        public VertexRuleBuilder add(double x, double z) {
            this.offsetVertices.add(new MtsElementSets.Pair<>(x, z));
            return this;
        }

        @SuppressWarnings("unchecked")
        public MtsElementSets.Pair<Double, Double>[] build() {
            return offsetVertices.toArray(new MtsElementSets.Pair[0]);
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 10/13/2026
     */
    private record EffectKey(UUID entityUUID, String effectId) {
    }
}
