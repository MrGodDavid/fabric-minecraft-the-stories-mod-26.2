package net.mrgoddavid.minecraftthestoriesmod.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.particles.ParticleOptions;
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

    public static void spawnCircle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double radius, int count, ParticleSpawnMode mode) {
        switch (mode) {
            case CIRCLE_EIGHT_DIR ->
                    doSpawnEightDirParticle(mc, pulse, particleType, renderState, yOffset, radius, count);
            case CIRCLE_SIXTEEN_DIR ->
                    doSpawnSixteenDirParticle(mc, pulse, particleType, renderState, yOffset, radius, count);
            default ->
                    MtsLogger.warn("Invalid mode: " + "[" + mode + "] when spawning particles at entity: " + ClassNameFormatter.extractEntityNameFromEntityRenderState(renderState));
        }
    }

    private static void doSpawnSixteenDirParticle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double radius, int count) {
        doSpawnParticleClusterInner(mc, pulse, particleType, renderState, Constants.Universal.SIXTEEN_DIR_OFFSET, yOffset, radius, count);
    }

    private static void doSpawnEightDirParticle(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, double yOffset, double radius, int count) {
        doSpawnParticleClusterInner(mc, pulse, particleType, renderState, Constants.Universal.EIGHT_DIR_OFFSET, yOffset, radius, count);
    }

    private static void doSpawnParticleClusterInner(Minecraft mc, RandomPulse pulse, ParticleOptions particleType, LivingEntityRenderState renderState, MtsElementSets.Pair<Double, Double>[] offsetPresets, double yOffset, double radius, int count) {
        if (mc.level == null) return;
        if (pulse.isLock()) return;
        if (count <= 0 || radius <= 0.0d) return;

        double centerX = renderState.x;
        double centerY = renderState.y + yOffset;
        double centerZ = renderState.z;

        for (MtsElementSets.Pair<Double, Double> offsetXZCoordinate : offsetPresets) {
            mc.level.addParticle(particleType,
                    centerX + offsetXZCoordinate.first() * radius,
                    centerY,
                    centerZ + offsetXZCoordinate.second() * radius,
                    0.0d, 0.0d, 0.0d);
        }
    }
}
