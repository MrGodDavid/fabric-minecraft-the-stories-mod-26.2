package net.mrgoddavid.minecraftthestoriesmod.block.custom.super_crafter;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.block.custom.enricher.EnricherBlockEntity;
import org.jspecify.annotations.Nullable;

/**
 * Block entity renderer for Enricher.
 *
 * @author Mr. GodDavid
 * @since 8/17/2026
 */
public class EnricherBlockEntityRenderer implements BlockEntityRenderer<EnricherBlockEntity,  EnricherBlockEntityRenderState> {

    private final BlockModelResolver blockModelResolver;

    public EnricherBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.blockModelResolver = context.blockModelResolver();
    }

    @Override
    public EnricherBlockEntityRenderState createRenderState() {
        return new EnricherBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(EnricherBlockEntity blockEntity, EnricherBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

    }

    @Override
    public void submit(EnricherBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.scale(EnricherBlockEntityRenderState.SCALE, EnricherBlockEntityRenderState.SCALE, EnricherBlockEntityRenderState.SCALE);
        state.blockModelRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }
}
