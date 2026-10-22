package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsRenderTypes;

/**
 * @author Mr. GodDavid
 * @since 10/21/2026
 */
@SuppressWarnings("NullableProblems")
public class OakieStaffGlowingLayer extends EyesLayer<OakieEntityRenderState, OakieEntityModel> {

    public OakieStaffGlowingLayer(RenderLayerParent<OakieEntityRenderState, OakieEntityModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, OakieEntityRenderState state, float yRot, float xRot) {
        submitNodeCollector.order(1).submitModel(this.getParentModel(), state, poseStack, this.renderType(), lightCoords, OverlayTexture.NO_OVERLAY, state.staffGlowingColor, null, state.outlineColor, null);
    }

    @Override
    public RenderType renderType() {
        return MtsRenderTypes.OAKIE_STAFF_GLOWING;
    }
}
