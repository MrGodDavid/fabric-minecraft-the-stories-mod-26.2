package net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import org.jspecify.annotations.Nullable;

/**
 * @author Mr. GodDavid
 * @since 8/28/2026
 */
public class OreCompressorBlockRenderer implements BlockEntityRenderer<OreCompressorBlockEntity, OreCompressorBlockRenderState> {

    public static final ModelLayerLocation MODEL_LAYER = new ModelLayerLocation(
            Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, "ore_compressor"), "main"
    );

    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            MinecraftTheStoriesMod.MOD_ID, "textures/block/ore_compressor_3d_texture.png"
    );

    private OreCompressorFreewheelModel model;

    public OreCompressorBlockRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart root = context.bakeLayer(MODEL_LAYER);
        model = new OreCompressorFreewheelModel(root);
    }

    @Override
    public OreCompressorBlockRenderState createRenderState() {
        return new OreCompressorBlockRenderState();
    }

    @Override
    public void extractRenderState(OreCompressorBlockEntity blockEntity, OreCompressorBlockRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        state.isCompressing = blockEntity.isCompressing();
        state.animationTime = ((blockEntity.getLevel().getGameTime() + partialTicks) % 20.0f) / 20.0f;
        state.angleCorrection = blockEntity.getBlockState().getValue(OreCompressorBlock.FREEWHEEL_MODEL_CORRECTION).angleCorrection();
        state.translationCorrection = blockEntity.getBlockState().getValue(OreCompressorBlock.FREEWHEEL_MODEL_CORRECTION).translation();
    }

    @Override
    public void submit(OreCompressorBlockRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.scale(1.0f, -1.0f, 1.0f);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.angleCorrection));
        poseStack.translate(state.translationCorrection.x(), state.translationCorrection.y(), state.translationCorrection.z());

        submitNodeCollector.submitModel(model, state, poseStack, model.renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF, null, 0x00000000, null);

        poseStack.popPose();
    }
}
