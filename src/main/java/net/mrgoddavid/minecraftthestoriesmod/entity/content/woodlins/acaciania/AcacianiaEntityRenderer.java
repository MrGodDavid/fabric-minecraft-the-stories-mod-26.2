package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.acaciania;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleSpawner;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleTypes;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.ParticleSpawnMode;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityRenderer;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityTextures;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Timer;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 11/7/2026
 */
public class AcacianiaEntityRenderer extends AbstractWoodlinEntityRenderer<AcacianiaEntity, AcacianiaEntityRenderState, AcacianiaEntityModel> {

    public AcacianiaEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new AcacianiaEntityModel(context.bakeLayer(MtsEntityModelLayers.ACACIANIA)), 0.2f);
    }

    @Override
    public void extractRenderState(AcacianiaEntity entity, AcacianiaEntityRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.abilityUseAnimationState.copyFrom(entity.hydrateAnimationState);
    }

    @Override
    protected void spawnEffectBorderParticles(AcacianiaEntityRenderState state) {
        Minecraft mc = Minecraft.getInstance();
        MtsParticleSpawner.circle(
                mc,
                RANDOM_PULSE,
                state.isAlerting ? MtsParticleTypes.RED_CLOUD_PARTICLE : MtsParticleTypes.BLUE_CLOUD_PARTICLE,
                state.entityUUID,
                "acaciania_border_particles",
                new Timer(40),
                state,
                0.0,
                AcacianiaEntity.SPEED_BOOST_RADIUS,
                ParticleSpawnMode.CIRCLE_EIGHT_DIR,
                true
        );
    }

    @Override
    protected void spawnTamedWoodlinAbilityUseParticles(AcacianiaEntityRenderState state) {
        Minecraft mc = Minecraft.getInstance();
        MtsParticleSpawner.fillCircle(
                mc,
                RANDOM_PULSE,
                MtsParticleTypes.ACACIANIA_SWIFTNESS_PARTICLE,
                state.entityUUID,
                "acaciania_swiftness_particle",
                new Timer(40),
                state,
                0.0,
                AcacianiaEntity.SPEED_BOOST_RADIUS,
                2,
                ParticleSpawnMode.FILL_CIRCLE_EIGHT_DIR,
                true
        );
    }

    @Override
    protected int getEyeGlowColor() {
        return 0;
    }

    @Override
    public @NonNull Identifier getTextureLocation(AcacianiaEntityRenderState state) {
        return MtsEntityTextures.ACACIANIA;
    }

    @Override
    public AcacianiaEntityRenderState createRenderState() {
        return new AcacianiaEntityRenderState();
    }
}
