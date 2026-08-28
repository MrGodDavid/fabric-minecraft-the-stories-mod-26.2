package net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Mth;

public class OreCompressorFreewheelModel extends Model<OreCompressorBlockRenderState> {
	public final ModelPart bone1;

	public OreCompressorFreewheelModel(ModelPart root) {
		super(root, RenderTypes::entityCutout);
		this.bone1 = root.getChild("bone-1");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition root = modelData.getRoot();

		PartDefinition bone1 = root.addOrReplaceChild("bone-1", CubeListBuilder.create().texOffs(54, 69).addBox(-1.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -14.5F, -2.5F));

		PartDefinition wheel = bone1.addOrReplaceChild("wheel", CubeListBuilder.create().texOffs(68, 72).addBox(-0.5F, -5.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 63).addBox(-1.0F, -6.5F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.0F, 0.0F));

		PartDefinition cube_r1 = wheel.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(54, 75).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(72, 0).addBox(-1.0F, -7.5F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r2 = wheel.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 74).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(70, 46).addBox(-1.0F, -7.5F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r3 = wheel.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(12, 74).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 70).addBox(-1.0F, -7.5F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r4 = wheel.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(22, 70).addBox(-1.0F, -7.5F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 74).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 5.5F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r5 = wheel.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(66, 65).addBox(-1.0F, -7.5F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(8, 74).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 5.5F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r6 = wheel.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(66, 58).addBox(-1.0F, -7.5F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(72, 72).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 5.5F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r7 = wheel.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(38, 63).addBox(-1.0F, -7.5F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r8 = wheel.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(4, 74).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 5.5F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void setupAnim(OreCompressorBlockRenderState state) {
		this.bone1.xRot = state.isCompressing ? state.animationTime * Mth.TWO_PI : 0.0f;
	}
}