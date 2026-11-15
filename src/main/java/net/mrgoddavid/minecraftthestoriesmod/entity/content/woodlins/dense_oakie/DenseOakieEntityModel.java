package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.dense_oakie;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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
public class DenseOakieEntityModel extends AbstractWoodlinEntityModel<DenseOakieEntityRenderState> {

    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart hammer_left;
    private final ModelPart right_arm;
    private final ModelPart hammer_right;
    private final ModelPart cape;
    private final ModelPart left_antler;
    private final ModelPart right_antler;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public DenseOakieEntityModel(ModelPart root) {
        super(root);
        this.body = this.root.getChild("body");
        this.left_arm = this.body.getChild("left_arm");
        this.hammer_left = this.left_arm.getChild("hammer_left");
        this.right_arm = this.body.getChild("right_arm");
        this.hammer_right = this.right_arm.getChild("hammer_right");
        this.cape = this.body.getChild("cape");
        this.left_antler = this.body.getChild("left_antler");
        this.right_antler = this.body.getChild("right_antler");
        this.left_leg = this.root.getChild("left_leg");
        this.right_leg = this.root.getChild("right_leg");
    }

    @Override
    protected @NonNull KeyframeAnimation bakeIdleAnimation() {
        return DenseOakieEntityAnimations.IDLE.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeWalkAnimation() {
        return DenseOakieEntityAnimations.WALK.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeAbilityUseAnimation() {
        return DenseOakieEntityAnimations.STRENGTH.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeEnchantingAnimation() {
        return DenseOakieEntityAnimations.ENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeDisenchantingAnimation() {
        return DenseOakieEntityAnimations.DISENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeTameAnimation() {
        return DenseOakieEntityAnimations.TAME.bake(this.root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(16, 23).addBox(-4.0F, -7.0F, 2.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));
        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(30, 33).addBox(-0.5F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 0).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -3.0F, 0.0F));
        left_arm.addOrReplaceChild("hammer_left", CubeListBuilder.create().texOffs(0, 12).addBox(-0.5F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(-1.5F, -2.5F, -9.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 3.0F, 0.0F));
        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(12, 36).addBox(-1.5F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 4).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -3.0F, 0.0F));
        right_arm.addOrReplaceChild("hammer_right", CubeListBuilder.create().texOffs(22, 12).addBox(-0.5F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(16, 27).addBox(-1.5F, -2.5F, -9.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 3.0F, 0.0F));
        PartDefinition cape = body.addOrReplaceChild("cape", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 3.0F));
        cape.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 23).addBox(-3.0F, 0.0F, -1.0F, 6.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
        body.addOrReplaceChild("left_antler", CubeListBuilder.create().texOffs(30, 27).addBox(-1.0F, -4.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -6.0F, 1.5F));
        body.addOrReplaceChild("right_antler", CubeListBuilder.create().texOffs(0, 33).addBox(-5.0F, -4.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -6.0F, 1.5F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(20, 36).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -2.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(36, 23).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -2.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}