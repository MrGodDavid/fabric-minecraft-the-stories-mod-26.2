package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleSpawner;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.MtsParticleTypes;
import net.mrgoddavid.minecraftthestoriesmod.client.particle.ParticleSpawnMode;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityTextures;
import net.mrgoddavid.minecraftthestoriesmod.utils.Timer;
import net.mrgoddavid.minecraftthestoriesmod.utils.app.dave.RandomPulse;

/**
 * @author Mr. GodDavid
 * @since 10/16/2026
 */
public class OakieEntityRenderer extends MobRenderer<OakieEntity, OakieEntityRenderState, OakieEntityModel> {

    private static final RandomPulse RANDOM_PULSE = RandomPulse.builder(100).origin(0).probability(50).build();

    public OakieEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new OakieEntityModel(context.bakeLayer(MtsEntityModelLayers.OAKIE)), 0.6F);
    }

    @Override
    public Identifier getTextureLocation(OakieEntityRenderState state) {
        return MtsEntityTextures.OAKIE;
    }

    @Override
    public OakieEntityRenderState createRenderState() {
        return new OakieEntityRenderState();
    }

    @Override
    public void extractRenderState(OakieEntity entity, OakieEntityRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.healAnimationState.copyFrom(entity.healAnimationState);
        state.enchantingAnimationState.copyFrom(entity.enchantingAnimationState);
        state.disenchantingAnimationState.copyFrom(entity.disenchantingAnimationState);
        state.tameAnimationState.copyFrom(entity.tameAnimationState);
        state.enchanting = entity.isEnchanting();
        state.entityUUID = entity.getUUID();
    }

    @Override
    public void submit(OakieEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
        RANDOM_PULSE.tick();

        spawnEffectBorderParticles(state);
        if (state.enchanting) {
            spawnTamedOakieRegenerationParticles(state);
        }
    }

    private void spawnTamedOakieRegenerationParticles(OakieEntityRenderState renderState) {
        Minecraft mc = Minecraft.getInstance();
        MtsParticleSpawner.fillCircle(mc, RANDOM_PULSE, MtsParticleTypes.OAKIE_REGENERATION_PARTICLE, renderState.entityUUID, "oakie_regeneration_particle", new Timer(40), renderState, 0.0, 5, 2, ParticleSpawnMode.FILL_CIRCLE_SIXTEEN_DIR, true);
    }

    private void spawnEffectBorderParticles(OakieEntityRenderState renderState) {
        Minecraft mc = Minecraft.getInstance();
        MtsParticleSpawner.spawnCircle(mc, RANDOM_PULSE, MtsParticleTypes.RED_CLOUD_PARTICLE, renderState.entityUUID, "oakie_particle_circle", new Timer(60), renderState, 2.0, 5, ParticleSpawnMode.CIRCLE_SIXTEEN_DIR, true);
    }
}
