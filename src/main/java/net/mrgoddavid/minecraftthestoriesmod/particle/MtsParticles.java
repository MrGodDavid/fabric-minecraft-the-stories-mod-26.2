package net.mrgoddavid.minecraftthestoriesmod.particle;

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
public class MtsParticles {

    public static void register() {
        MtsLogger.info("MTS Particles");

        registerParticle("enricher_waste_particle", MtsParticleTypes.ENRICHER_WASTE_PARTICLE);
    }

    private static <T extends ParticleType<SimpleParticleType>> void registerParticle(final String name, final T particle) {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Constants.modId(name), particle);
    }

    private MtsParticles() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
