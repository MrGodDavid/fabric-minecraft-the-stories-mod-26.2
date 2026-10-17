package net.mrgoddavid.minecraftthestoriesmod.client.particle;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 8/30/2026
 */
public final class MtsParticles {

    public static void register() {
        MtsLogger.info("MTS Particles");

        registerParticle("enricher_waste_particle", MtsParticleTypes.ENRICHER_WASTE_PARTICLE);
        registerParticle("colored_cloud_particle", MtsParticleTypes.RED_CLOUD_PARTICLE);

        registerParticle("acaciania_swiftness_particle", MtsParticleTypes.ACACIANIA_SWIFTNESS_PARTICLE);
        registerParticle("birchia_jumping_boost_particle", MtsParticleTypes.BIRCHIA_JUMPING_BOOST_PARTICLE);
        registerParticle("cherrinia_enticement_particle", MtsParticleTypes.CHERRINIA_ENTICEMENT_PARTICLE);
        registerParticle("dense_oakie_strength_particle", MtsParticleTypes.DENSE_OAKIE_STRENGTH_PARTICLE);
        registerParticle("junmi_hydration_particle", MtsParticleTypes.JUNMI_HYDRATION_PARTICLE);
        registerParticle("mangrolino_swimmer_boost_particle", MtsParticleTypes.MANGROLINO_SWIMMER_BOOST_PARTICLE);
        registerParticle("oakie_regeneration_particle", MtsParticleTypes.OAKIE_REGENERATION_PARTICLE);
        registerParticle("light_oakie_invisibility_particle", MtsParticleTypes.LIGHT_OAKIE_INVISIBILITY_PARTICLE);
        registerParticle("sprucia_attack_speed_particle", MtsParticleTypes.SPRUCIA_ATTACK_SPEED_PARTICLE);
    }

    private static <T extends ParticleType<SimpleParticleType>> void registerParticle(final String name, final T particle) {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Constants.modId(name), particle);
    }

    private MtsParticles() throws IllegalAccessException {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
