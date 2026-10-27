package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityTextures;

/**
 * @author Mr. GodDavid
 * @since 10/25/2026
 */
@SuppressWarnings("NullableProblems")
public class OakieHealingBallEntityRenderer extends MobRenderer<OakieHealingBallEntity, OakieHealingBallEntityRenderState, OakieHealingBallEntityModel> {

    public OakieHealingBallEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new OakieHealingBallEntityModel(context.bakeLayer(MtsEntityModelLayers.OAKIE_HEALING_BALL)), 0.6F);
    }

    @Override
    public Identifier getTextureLocation(OakieHealingBallEntityRenderState state) {
        return MtsEntityTextures.OAKIE_HEALING_BALL;
    }

    @Override
    public OakieHealingBallEntityRenderState createRenderState() {
        return new OakieHealingBallEntityRenderState();
    }

    @Override
    public void extractRenderState(OakieHealingBallEntity entity, OakieHealingBallEntityRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.spawnAnimationState.copyFrom(entity.spawnAnimationState);
        state.flyingAnimationState.copyFrom(entity.flyingAnimationState);
        state.healAnimationState.copyFrom(entity.healAnimationState);
    }
}
