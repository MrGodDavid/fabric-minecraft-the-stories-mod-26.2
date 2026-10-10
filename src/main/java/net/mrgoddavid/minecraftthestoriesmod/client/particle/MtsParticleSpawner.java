package net.mrgoddavid.minecraftthestoriesmod.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.RandomPulse;
import net.mrgoddavid.minecraftthestoriesmod.utils.list.MtsElementSets;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ClassNameFormatter;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * Holds some designs of spawning particles.
 *
 * @author Mr. GodDavid
 * @since 10/7/2026
 */
public class MtsParticleSpawner {

    private MtsParticleSpawner() {
    }

    public static void spawnSquare(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double size, ParticleSpawnMode mode, boolean randomness) {
        spawnSquare(mc, pulse, particleType, renderState, yOffset, size, 1, mode, randomness);
    }

    public static void spawnSquare(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double size, int times, ParticleSpawnMode mode, boolean randomness) {
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
                doSpawnSquareFilledInParticle(mc, pulse, particleType, renderState, yOffset, size, times, randomness);
            }
            default ->
                    MtsLogger.warn("Invalid mode: " + "[" + mode + "] when spawning particles at entity: " + ClassNameFormatter.extractEntityNameFromEntityRenderState(renderState));
        }
    }

    private static void doSpawnSquareFilledInParticle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double size, int times, boolean randomness) {
        doSpawnParticleContinuousSquarePatternInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SQUARE_CORNER_OFFSET, yOffset, size, times, randomness);
    }

    private static void doSpawnSquareEightParticle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double size, boolean randomness) {
        doSpawnParticleClusterPatternInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SQUARE_EIGHT_PARTICLE_OFFSET, yOffset, size, randomness);
    }

    private static void doSpawnSquareCornerParticle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double size, boolean randomness) {
        doSpawnParticleClusterPatternInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SQUARE_CORNER_OFFSET, yOffset, size, randomness);
    }

    public static void spawnCircle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double radius, ParticleSpawnMode mode, boolean randomness) {
        switch (mode) {
            case CIRCLE_EIGHT_DIR ->
                    doSpawnEightDirParticle(mc, pulse, particleType, renderState, yOffset, radius, randomness);
            case CIRCLE_SIXTEEN_DIR ->
                    doSpawnSixteenDirParticle(mc, pulse, particleType, renderState, yOffset, radius, randomness);
            default ->
                    MtsLogger.warn("Invalid mode: " + "[" + mode + "] when spawning particles at entity: " + ClassNameFormatter.extractEntityNameFromEntityRenderState(renderState));
        }
    }

    private static void doSpawnSixteenDirParticle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double radius, boolean randomness) {
        doSpawnParticleClusterPatternInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SIXTEEN_DIR_OFFSET, yOffset, radius, randomness);
    }

    private static void doSpawnEightDirParticle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double radius, boolean randomness) {
        doSpawnParticleClusterPatternInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.EIGHT_DIR_OFFSET, yOffset, radius, randomness);
    }

    private static void doSpawnParticleContinuousSquarePatternInner(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, MtsElementSets.Pair<Double, Double>[] squareCornerOffset, double yOffset, double size, int countOfPointPerSide, boolean randomness) {
        if (mc.level == null) return;
        if (countOfPointPerSide <= 0) return;

        double randomX = Mth.randomBetween(mc.level.getRandom(), -0.5f, 0.5f);
        double randomZ = Mth.randomBetween(mc.level.getRandom(), -0.5f, 0.5f);

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

    private static void doSpawnParticleClusterPatternInner(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, MtsElementSets.Pair<Double, Double>[] offsetPresets, double yOffset, double radius, boolean randomness) {
        if (mc.level == null) return;

        double randomX = Mth.randomBetween(mc.level.getRandom(), 0f, 0.5f);
        double randomZ = Mth.randomBetween(mc.level.getRandom(), 0f, 0.5f);

        double centerX = renderState.x;
        double centerY = renderState.y + yOffset;
        double centerZ = renderState.z;

        for (MtsElementSets.Pair<Double, Double> offsetXZCoordinate : offsetPresets) {
            if (pulse.isLock()) continue;
            mc.level.addParticle(particleType,
                    centerX + offsetXZCoordinate.first() * radius + (randomness ? randomX : 0.0d),
                    centerY,
                    centerZ + offsetXZCoordinate.second() * radius + (randomness ? randomZ : 0.0d),
                    0.0d, 0.0d, 0.0d);
        }
    }
}
