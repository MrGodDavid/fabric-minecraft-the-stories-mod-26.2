package net.mrgoddavid.minecraftthestoriesmod.block.custom.ender_exalter;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class EnderExalterBlockRenderer implements BlockEntityRenderer<EnderExalterBlockEntity, EnderExalterBlockRenderState> {

    private static final float ITEM_MODEL_ROTATION_SPEED = 2.0F;
    private static final float ITEM_MODEL_FLOATING_FREQUENCY = 0.1F;

    private final ItemModelResolver itemModelResolver;

    public EnderExalterBlockRenderer(BlockEntityRendererProvider.Context context) {
        itemModelResolver = context.itemModelResolver();
    }

    @Override
    public EnderExalterBlockRenderState createRenderState() {
        return new EnderExalterBlockRenderState();
    }

    /**
     * Populates your render state here.
     *
     * @param blockEntity    block entity that uses this block entity renderer, which, in this case, the block is the
     *                       Ender Exalter.
     * @param state          render state of the block entity that uses this block entity renderer, which, in this case,
     *                       is the Ender Exalter.
     * @param partialTicks   partial ticks.
     * @param cameraPosition the position of the Minecraft's game camera.
     * @param breakProgress  break progress (I don't know what does this do).
     */
    @Override
    public void extractRenderState(EnderExalterBlockEntity blockEntity, EnderExalterBlockRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        state.level = blockEntity.getLevel();
        state.firstSlotYPos = 0.2f * Mth.sin((blockEntity.getLevel().getGameTime() * ITEM_MODEL_FLOATING_FREQUENCY + partialTicks * ITEM_MODEL_FLOATING_FREQUENCY) % 360.0f);
        state.firstSlotRotation = (blockEntity.getLevel().getGameTime() * ITEM_MODEL_ROTATION_SPEED + partialTicks * ITEM_MODEL_ROTATION_SPEED) % 360.0f;
        state.secondSlotYPos = 0.2f * Mth.sin((blockEntity.getLevel().getGameTime() * ITEM_MODEL_FLOATING_FREQUENCY + partialTicks * ITEM_MODEL_FLOATING_FREQUENCY + 180.0f) % 360.0f);
        state.secondSlotRotation = (blockEntity.getLevel().getGameTime() * ITEM_MODEL_ROTATION_SPEED + partialTicks * ITEM_MODEL_ROTATION_SPEED + 180.0f) % 360.0f;

        itemModelResolver.updateForTopItem(state.firstSlotItemStackRenderState, blockEntity.getItem(0),
                ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
        itemModelResolver.updateForTopItem(state.secondSlotItemStackRenderState, blockEntity.getItem(1),
                ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
    }

    @Override
    public void submit(EnderExalterBlockRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();

        poseStack.translate(0.5f, 1.3f, 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.firstSlotRotation));
        poseStack.translate(-0.5f, state.firstSlotYPos, -0.5f);

        state.firstSlotItemStackRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0xffffffff);

        poseStack.popPose();

        // SECOND ONE
        poseStack.pushPose();

        poseStack.translate(0.5f, 1.3f, 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.secondSlotRotation));
        poseStack.translate(-0.5f, state.secondSlotYPos, -0.5f);

        state.secondSlotItemStackRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0xffffffff);

        poseStack.popPose();
    }
}
