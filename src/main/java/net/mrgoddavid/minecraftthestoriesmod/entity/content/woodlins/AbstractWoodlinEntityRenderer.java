package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.mrgoddavid.minecraftthestoriesmod.utils.app.dave.RandomPulse;

/**
 * @author Mr. GodDavid
 * @since 11/5/2026
 */
public abstract class AbstractWoodlinEntityRenderer<T extends AbstractWoodlinEntity, S extends AbstractWoodlinEntityRenderState, M extends EntityModel<S>> extends MobRenderer<T, S, M> {

    protected static final RandomPulse RANDOM_PULSE = RandomPulse.builder(100).origin(0).probability(50).build();

    public AbstractWoodlinEntityRenderer(EntityRendererProvider.Context context, M model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public void extractRenderState(T entity, S state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.tameAnimationState.copyFrom(entity.tameAnimationState);
        state.enchantingAnimationState.copyFrom(entity.enchantingAnimationState);
        state.disenchantingAnimationState.copyFrom(entity.disenchantingAnimationState);
        state.enchanting = entity.isEnchanting();
        state.entityUUID = entity.getUUID();
        state.eyeColor = this.selectEyeColor(entity);
        state.isAlerting = entity.isDefending();
    }

    @Override
    public void submit(S state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
        RANDOM_PULSE.tick();

        this.spawnEffectBorderParticles(state);
        if (state.enchanting) {
            this.spawnTamedWoodlinAbilityUseParticles(state);
        }
    }

    protected abstract void spawnEffectBorderParticles(S state);

    protected abstract void spawnTamedWoodlinAbilityUseParticles(S state);

    protected abstract int getEyeGlowColor();

    private int selectEyeColor(T woodlin) {
        return woodlin.isEnchanting() ? this.getEyeGlowColor() : 0xFF000000;
    }
}
