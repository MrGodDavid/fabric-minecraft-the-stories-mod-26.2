package net.mrgoddavid.minecraftthestoriesmod.block.content.woodlin_pot;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityModelLayers;
import net.mrgoddavid.minecraftthestoriesmod.utils.directory.Directory;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;

/**
 * @author Mr. GodDavid
 * @since 11/8/2026
 */
public class WoodlinPotBlockRenderer implements BlockEntityRenderer<WoodlinPotBlockEntity, WoodlinPotBlockRenderState> {

    private static final String directory = Directory.builder().textures().block().build().toString().concat("woodlin_pot_branch.png");
    public static final Identifier TEXTURE = Constants.modId(directory);
    private final WoodlinPotBranchModel branchModel;

    public WoodlinPotBlockRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart root = context.bakeLayer(MtsEntityModelLayers.WOODLIN_POT);
        this.branchModel = new WoodlinPotBranchModel(root);
    }

    @Override
    public WoodlinPotBlockRenderState createRenderState() {
        return new WoodlinPotBlockRenderState();
    }

    @Override
    public void submit(WoodlinPotBlockRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        submitNodeCollector.submitModel(branchModel, state, poseStack, branchModel.renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF, null, 0x00000000, null);
    }
}
