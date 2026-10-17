package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.particle.BubbleParticle;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleTypes;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.content.*;
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
        ParticleProviderRegistry.getInstance().register(MtsParticleTypes.RED_CLOUD_PARTICLE, sprite -> new ColoredPlayerCloudParticle.Provider(sprite).color(255, 0, 0));

        ParticleProviderRegistry.getInstance().register(MtsParticleTypes.ACACIANIA_SWIFTNESS_PARTICLE, AcacianiaSwiftnessParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(MtsParticleTypes.BIRCHIA_JUMPING_BOOST_PARTICLE, BirchiaJumpingBoostParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(MtsParticleTypes.CHERRINIA_ENTICEMENT_PARTICLE, CherriniaEnticementParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(MtsParticleTypes.DENSE_OAKIE_STRENGTH_PARTICLE, DenseOakieStrengthParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(MtsParticleTypes.JUNMI_HYDRATION_PARTICLE, JunmiHydrationParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(MtsParticleTypes.MANGROLINO_SWIMMER_BOOST_PARTICLE, MangrolinoSwimmerBoostParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(MtsParticleTypes.OAKIE_REGENERATION_PARTICLE, OakieRegenerationParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(MtsParticleTypes.LIGHT_OAKIE_INVISIBILITY_PARTICLE, LightOakieInvisibilityParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(MtsParticleTypes.SPRUCIA_ATTACK_SPEED_PARTICLE, SpruciaAttackSpeedParticle.Provider::new);
    }
}
