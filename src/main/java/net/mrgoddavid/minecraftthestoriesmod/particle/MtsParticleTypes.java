package net.mrgoddavid.minecraftthestoriesmod.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.utils.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 8/30/2026
 */
public class MtsParticleTypes {

    public static final SimpleParticleType ENRICHER_WASTE_PARTICLE = FabricParticleTypes.simple();

    public static void register() {
        MtsLogger.info("Particle Types");
    }

    private MtsParticleTypes() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
