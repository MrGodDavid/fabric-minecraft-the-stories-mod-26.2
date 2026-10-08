package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.particle.BubbleParticle;
import net.mrgoddavid.minecraftthestoriesmod.particle.MtsParticleTypes;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * Registry class for MTS particle types.
 *
 * @author Mr. GodDavid
 * @since 10/7/2026
 */
public final class MtsParticleTypeRegistries {

    public static void register() {
        MtsLogger.info("Particle Types");

        ParticleProviderRegistry.getInstance().register(MtsParticleTypes.ENRICHER_WASTE_PARTICLE, BubbleParticle.Provider::new);
    }
}
