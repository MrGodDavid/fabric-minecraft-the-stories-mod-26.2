package net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.entity.client.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.entity.client.MtsEntityTextures;

/**
 * @author Mr. GodDavid
 * @since 9/7/2026
 */
public class BrownBearEntityRenderer extends MobRenderer<BrownBearEntity, BrownBearEntityRenderState, BrownBearEntityModel> {

    public BrownBearEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new BrownBearEntityModel(context.bakeLayer(MtsEntityModelLayers.BROWN_BEAR)), 0.6F);
    }

    @Override
    public BrownBearEntityRenderState createRenderState() {
        return new BrownBearEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(BrownBearEntityRenderState state) {
        return MtsEntityTextures.BROWN_BEAR;
    }

    @Override
    public void extractRenderState(BrownBearEntity entity, BrownBearEntityRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.attackAnimationState.copyFrom(entity.attackAnimationState);
        state.runAnimationState.copyFrom(entity.runAnimationState);
        state.tameAnimationState.copyFrom(entity.tameAnimationState);
        state.sitAnimationState.copyFrom(entity.sitAnimationState);
        state.standAnimationState.copyFrom(entity.standAnimationState);
    }
}
