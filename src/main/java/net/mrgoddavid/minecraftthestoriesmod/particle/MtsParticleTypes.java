package net.mrgoddavid.minecraftthestoriesmod.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

/**
 * @author Mr. GodDavid
 * @since 8/30/2026
 */
public class MtsParticleTypes {

    public static final SimpleParticleType ENRICHER_WASTE_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticleTypes() {
        MinecraftTheStoriesMod.LOGGER.info("Registering particle types for " + MinecraftTheStoriesMod.MOD_ID);
    }

    private MtsParticleTypes() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
