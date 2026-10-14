package net.mrgoddavid.minecraftthestoriesmod.client.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 8/30/2026
 */
public class MtsParticleTypes {

    public static final SimpleParticleType ENRICHER_WASTE_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType RED_CLOUD_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType OAKIE_REGENERATION_PARTICLE = FabricParticleTypes.simple();

    public static void register() {
        MtsLogger.info("Particle Types");
    }

    private MtsParticleTypes() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
