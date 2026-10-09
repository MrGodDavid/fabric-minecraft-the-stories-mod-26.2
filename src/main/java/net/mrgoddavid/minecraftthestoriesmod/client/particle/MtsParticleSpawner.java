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

    public static void spawnSquare(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double size, int count, ParticleSpawnMode mode, boolean randomness) {
        spawnSquare(mc, pulse, particleType, renderState, yOffset, size, count, 1, mode, randomness);
    }

    public static void spawnSquare(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double size, int count, int times, ParticleSpawnMode mode, boolean randomness) {
        switch (mode) {
            case SQUARE_CORNER_DIR ->
                    doSpawnSquareCornerParticle(mc, pulse, particleType, renderState, yOffset, size, count, randomness);
            case SQUARE_EIGHT_PARTICLES_DIR ->
                    doSpawnSquareEightParticle(mc, pulse, particleType, renderState, yOffset, size, count, randomness);
            case SQUARE_FILL_IN -> {
                if (times <= 0) {
                    throw new IllegalArgumentException("Times under continuous mode: [" + times + "] must be positive!");
                }
                doSpawnSquareFilledInParticle(mc, pulse, particleType, renderState, yOffset, size, count, times, randomness);
            }
            default ->
                    MtsLogger.warn("Invalid mode: " + "[" + mode + "] when spawning particles at entity: " + ClassNameFormatter.extractEntityNameFromEntityRenderState(renderState));
        }
    }

    private static void doSpawnSquareFilledInParticle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double size, int count, int times, boolean randomness) {
        doSpawnParticleContinuousInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SQUARE_CORNER_OFFSET, yOffset, size, count, times, randomness);
    }

    private static void doSpawnSquareEightParticle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double size, int count, boolean randomness) {
        doSpawnParticleClusterInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SQUARE_EIGHT_PARTICLE_OFFSET, yOffset, size, count, randomness);
    }

    private static void doSpawnSquareCornerParticle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double size, int count, boolean randomness) {
        doSpawnParticleClusterInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SQUARE_CORNER_OFFSET, yOffset, size, count, randomness);
    }

    public static void spawnCircle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double radius, int count, ParticleSpawnMode mode, boolean randomness) {
        switch (mode) {
            case CIRCLE_EIGHT_DIR ->
                    doSpawnEightDirParticle(mc, pulse, particleType, renderState, yOffset, radius, count, randomness);
            case CIRCLE_SIXTEEN_DIR ->
                    doSpawnSixteenDirParticle(mc, pulse, particleType, renderState, yOffset, radius, count, randomness);
            default ->
                    MtsLogger.warn("Invalid mode: " + "[" + mode + "] when spawning particles at entity: " + ClassNameFormatter.extractEntityNameFromEntityRenderState(renderState));
        }
    }

    private static void doSpawnSixteenDirParticle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double radius, int count, boolean randomness) {
        doSpawnParticleClusterInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.SIXTEEN_DIR_OFFSET, yOffset, radius, count, randomness);
    }

    private static void doSpawnEightDirParticle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double radius, int count, boolean randomness) {
        doSpawnParticleClusterInner(mc, pulse, particleType, renderState, Constants.ParticleSpawnOffsets.EIGHT_DIR_OFFSET, yOffset, radius, count, randomness);
    }

    // TODO
    private static void doSpawnParticleContinuousInner(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, MtsElementSets.Pair<Double, Double>[] squareCornerOffset, double yOffset, double size, int count, int countOfPointPerSide, boolean randomness) {
        if (mc.level == null) return;
        if (count <= 0 || size <= 0.0d) return;
        if (countOfPointPerSide <= 0) return;

        double randomX = Mth.randomBetween(mc.level.getRandom(), -0.5f, 0.5f);
        double randomZ = Mth.randomBetween(mc.level.getRandom(), -0.5f, 0.5f);

        double centerX = renderState.x;
        double centerY = renderState.y + yOffset;
        double centerZ = renderState.z;
        double step = size / countOfPointPerSide;

        int iterationTimes = 4 * countOfPointPerSide;

        for (int i = 0; i < iterationTimes; i++) {
            if (pulse.isLock()) continue;

            int side = i / countOfPointPerSide;
            int point = i % countOfPointPerSide;
            double xOffset = squareCornerOffset[side].first() + size / 2.0d;
            double zOffset = squareCornerOffset[side].second() + size / 2.0d;
            switch(side) {
                case 0 -> zOffset -= step * point;
                case 1 -> xOffset -= step * point;
                case 2 -> zOffset += step * point;
                case 3 -> xOffset += step * point;
                default -> throw new IllegalStateException("Invalid side: " + side + ". Reason of throwing this exception here " +
                        "is because the side index should be from 0 to 3. Check the countOfPointPerSide parameter!");
            }

            mc.level.addParticle(particleType,
                    centerX + xOffset + (randomness ? randomX : 0.0d),
                    centerY,
                    centerZ + zOffset + (randomness ? randomZ : 0.0d),
                    0.0d, 0.0d, 0.0d);
        }
    }

    private static void doSpawnParticleClusterInner(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, MtsElementSets.Pair<Double, Double>[] offsetPresets, double yOffset, double radius, int count, boolean randomness) {
        if (mc.level == null) return;
        if (count <= 0 || radius <= 0.0d) return;

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
