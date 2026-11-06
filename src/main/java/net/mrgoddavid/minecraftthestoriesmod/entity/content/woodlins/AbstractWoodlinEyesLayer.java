package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

/**
 * @author Mr. GodDavid
 * @since 11/5/2026
 */
@SuppressWarnings("NullableProblems")
public abstract class AbstractWoodlinEyesLayer<S extends AbstractWoodlinEntityRenderState, M extends EntityModel<S>> extends EyesLayer<S, M> {

    public AbstractWoodlinEyesLayer(RenderLayerParent<S, M> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
        submitNodeCollector.order(1).submitModel(this.getParentModel(), state, poseStack, this.renderType(), lightCoords, OverlayTexture.NO_OVERLAY, state.eyeColor, null, state.outlineColor, null);
    }
}
