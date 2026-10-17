package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityTextures;

/**
 * @author Mr. GodDavid
 * @since 10/16/2026
 */
public class OakieEntityRenderer extends MobRenderer<OakieEntity, OakieEntityRenderState, OakieEntityModel> {

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
    }
}
