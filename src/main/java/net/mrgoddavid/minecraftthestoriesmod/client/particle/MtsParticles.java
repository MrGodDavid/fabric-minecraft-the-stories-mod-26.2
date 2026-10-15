package net.mrgoddavid.minecraftthestoriesmod.client.particle;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 8/30/2026
 */
public final class MtsParticles {

    public static void register() {
        MtsLogger.info("MTS Particles");

        registerParticle("enricher_waste_particle", MtsParticleTypes.ENRICHER_WASTE_PARTICLE);
        registerParticle("colored_cloud_particle", MtsParticleTypes.RED_CLOUD_PARTICLE);
        registerParticle("oakie_regeneration_particle", MtsParticleTypes.OAKIE_REGENERATION_PARTICLE);
    }

    private static <T extends ParticleType<SimpleParticleType>> void registerParticle(final String name, final T particle) {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Constants.modId(name), particle);
    }

    private MtsParticles() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
