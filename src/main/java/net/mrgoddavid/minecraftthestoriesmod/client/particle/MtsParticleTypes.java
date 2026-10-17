package net.mrgoddavid.minecraftthestoriesmod.client.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 8/30/2026
 */
public final class MtsParticleTypes {

    public static final SimpleParticleType ENRICHER_WASTE_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType RED_CLOUD_PARTICLE = FabricParticleTypes.simple();
    
    public static final SimpleParticleType ACACIANIA_SWIFTNESS_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType BIRCHIA_JUMPING_BOOST_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType CHERRINIA_ENTICEMENT_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType DENSE_OAKIE_STRENGTH_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType JUNMI_HYDRATION_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType MANGROLINO_SWIMMER_BOOST_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType OAKIE_REGENERATION_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType LIGHT_OAKIE_INVISIBILITY_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType SPRUCIA_ATTACK_SPEED_PARTICLE = FabricParticleTypes.simple();

    public static void register() {
        MtsLogger.info("Particle Types");
    }

    private MtsParticleTypes() throws IllegalAccessException {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
