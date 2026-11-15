package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.sprucia;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityModel;
import org.jspecify.annotations.NonNull;

/**
 * @author Blockbench 5.2.2 generated this file.
 * @author Mr. GodDavid fixed errors of this file.
 * @since 11/14/2026
 */
@SuppressWarnings({"SpellCheckingInspection", "FieldCanBeLocal", "unused"})
public class SpruciaEntityModel extends AbstractWoodlinEntityModel<SpruciaEntityRenderState> {

	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart staff;
	private final ModelPart left_wing;
	private final ModelPart right_wing;
	private final ModelPart left_arm;
	private final ModelPart left_antler;
	private final ModelPart right_antler;
	private final ModelPart banner;
	private final ModelPart left_flag;
	private final ModelPart right_flag;
	private final ModelPart cape;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public SpruciaEntityModel(ModelPart root) {
		super(root);
		this.body = this.root.getChild("body");
		this.right_arm = this.body.getChild("right_arm");
		this.staff = this.right_arm.getChild("staff");
		this.left_wing = this.staff.getChild("left_wing");
		this.right_wing = this.staff.getChild("right_wing");
		this.left_arm = this.body.getChild("left_arm");
		this.left_antler = this.body.getChild("left_antler");
		this.right_antler = this.body.getChild("right_antler");
		this.banner = this.body.getChild("banner");
		this.left_flag = this.banner.getChild("left_flag");
		this.right_flag = this.banner.getChild("right_flag");
		this.cape = this.body.getChild("cape");
		this.left_leg = this.root.getChild("left_leg");
		this.right_leg = this.root.getChild("right_leg");
	}

	@Override
	protected @NonNull KeyframeAnimation bakeIdleAnimation() {
		return SpruciaEntityAnimations.IDLE.bake(this.root);
	}

	@Override
	protected @NonNull KeyframeAnimation bakeWalkAnimation() {
		return SpruciaEntityAnimations.WALK.bake(this.root);
	}

	@Override
	protected @NonNull KeyframeAnimation bakeAbilityUseAnimation() {
		return SpruciaEntityAnimations.MERCURY.bake(this.root);
	}

	@Override
	protected @NonNull KeyframeAnimation bakeEnchantingAnimation() {
		return SpruciaEntityAnimations.ENCHANTING.bake(this.root);
	}

	@Override
	protected @NonNull KeyframeAnimation bakeDisenchantingAnimation() {
		return SpruciaEntityAnimations.DISCHANTING.bake(this.root);
	}

	@Override
	protected @NonNull KeyframeAnimation bakeTameAnimation() {
		return SpruciaEntityAnimations.TAME.bake(this.root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(22, 22).addBox(-4.0F, -7.0F, 2.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(36, 6).addBox(-0.5F, -1.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -4.0F, 0.0F));

		PartDefinition staff = right_arm.addOrReplaceChild("staff", CubeListBuilder.create().texOffs(0, 12).addBox(-0.5F, -0.5F, -7.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 35).addBox(-1.5F, -1.5F, -11.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, -0.5F));
		staff.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -3.0F, -1.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, -9.5F));
		staff.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 25).addBox(0.0F, -3.0F, -1.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, -9.5F));
		body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(36, 0).addBox(-0.5F, -1.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -4.0F, 0.0F));
		body.addOrReplaceChild("left_antler", CubeListBuilder.create().texOffs(14, 35).addBox(-1.0F, -4.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -6.0F, 1.5F));
		body.addOrReplaceChild("right_antler", CubeListBuilder.create().texOffs(26, 35).addBox(-5.0F, -4.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -6.0F, 1.5F));

		PartDefinition banner = body.addOrReplaceChild("banner", CubeListBuilder.create().texOffs(36, 26).addBox(-0.5F, -7.0F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(-4.5F, -8.0F, -0.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 12).addBox(4.5F, -8.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(38, 16).addBox(-5.5F, -8.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 3.0F));

		PartDefinition left_flag = banner.addOrReplaceChild("left_flag", CubeListBuilder.create(), PartPose.offset(2.75F, -7.0F, 0.0F));
		left_flag.addOrReplaceChild("left_flag_r1", CubeListBuilder.create().texOffs(12, 26).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition right_flag = banner.addOrReplaceChild("right_flag", CubeListBuilder.create(), PartPose.offset(-2.75F, -7.0F, 0.0F));
		right_flag.addOrReplaceChild("right_flag_r1", CubeListBuilder.create().texOffs(24, 26).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cape = body.addOrReplaceChild("cape", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 3.0F));
		cape.addOrReplaceChild("cape_r1", CubeListBuilder.create().texOffs(22, 12).addBox(-3.0F, 0.0F, -1.0F, 6.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
		root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(38, 34).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -2.0F, 0.0F));
		root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(38, 37).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}