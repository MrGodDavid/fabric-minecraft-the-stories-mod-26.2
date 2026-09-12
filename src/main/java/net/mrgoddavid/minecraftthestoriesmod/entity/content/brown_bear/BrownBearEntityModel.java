package net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class BrownBearEntityModel extends EntityModel<BrownBearEntityRenderState> {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart left_front_leg;
    private final ModelPart right_front_leg;
    private final ModelPart head;
    private final ModelPart left_ear;
    private final ModelPart right_ear;
    private final ModelPart mouth;
    private final ModelPart upper_mouth;
    private final ModelPart bottom_mouth;
    private final ModelPart right_back_leg;
    private final ModelPart left_back_leg;

    private final KeyframeAnimation idleAnimation;
    private final KeyframeAnimation attackAnimation;
    private final KeyframeAnimation runAnimation;
    private final KeyframeAnimation tameAnimation;
    private final KeyframeAnimation sitAnimation;
    private final KeyframeAnimation standAnimation;
    private final KeyframeAnimation walkAnimation;

    public BrownBearEntityModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.left_front_leg = this.body.getChild("left_front_leg");
        this.right_front_leg = this.body.getChild("right_front_leg");
        this.head = this.body.getChild("head");
        this.left_ear = this.head.getChild("left_ear");
        this.right_ear = this.head.getChild("right_ear");
        this.mouth = this.head.getChild("mouth");
        this.upper_mouth = this.mouth.getChild("upper_mouth");
        this.bottom_mouth = this.mouth.getChild("bottom_mouth");
        this.right_back_leg = this.root.getChild("right_back_leg");
        this.left_back_leg = this.root.getChild("left_back_leg");

        this.idleAnimation = BrownBearModelAnimation.IDLE.bake(this.root);
        this.attackAnimation = BrownBearModelAnimation.ATTACK.bake(this.root);
        this.runAnimation = BrownBearModelAnimation.RUN.bake(this.root);
        this.tameAnimation = BrownBearModelAnimation.TAME.bake(this.root);
        this.sitAnimation = BrownBearModelAnimation.SIT.bake(this.root);
        this.standAnimation = BrownBearModelAnimation.STAND.bake(this.root);
        this.walkAnimation = BrownBearModelAnimation.WALK.bake(this.root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition MeshDefinition = new MeshDefinition();
        PartDefinition PartDefinition = MeshDefinition.getRoot();
        PartDefinition root = PartDefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 6.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 27).addBox(-7.0F, -7.0F, -4.5F, 14.0F, 15.0F, 11.0F)
                .texOffs(50, 27).addBox(-7.0F, -7.0F, 16.5F, 14.0F, 15.0F, 11.0F)
                .texOffs(0, 0).addBox(-8.0F, -8.0F, 6.5F, 16.0F, 17.0F, 10.0F), PartPose.offset(0.0F, -18.0F, -11.5F));
        body.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 53).addBox(-3.0F, -2.0F, -3.5F, 6.0F, 18.0F, 7.0F), PartPose.offset(-4.5F, 2.0F, 22.0F));
        body.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(26, 53).addBox(-3.0F, -2.0F, -3.5F, 6.0F, 18.0F, 7.0F), PartPose.offset(4.5F, 2.0F, 22.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(52, 0).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 8.0F), PartPose.offset(0.0F, -1.0F, 27.5F));
        head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(0, 78).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F), PartPose.offset(-3.5F, -5.0F, 5.0F));
        head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(10, 78).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F), PartPose.offset(3.5F, -5.0F, 5.0F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(0.0F, 2.5F, 8.0F));
        mouth.addOrReplaceChild("upper_mouth", CubeListBuilder.create().texOffs(52, 18).addBox(-2.0F, -1.5F, 0.0F, 4.0F, 3.0F, 5.0F), PartPose.offset(0.0F, -1.0F, 0.0F));
        mouth.addOrReplaceChild("bottom_mouth", CubeListBuilder.create().texOffs(70, 18).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 2.0F, 5.0F), PartPose.offset(0.0F, 1.5F, 0.0F));
        root.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(52, 71).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 10.0F, 8.0F), PartPose.offset(-4.0F, -10.0F, -11.0F));
        root.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(52, 53).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 10.0F, 8.0F), PartPose.offset(4.0F, -10.0F, -11.0F));
        return LayerDefinition.create(MeshDefinition, 128, 128);
    }

    @Override
    public void setupAnim(BrownBearEntityRenderState state) {
        super.setupAnim(state);
        this.root.getAllParts().forEach(ModelPart::resetPose);

        this.idleAnimation.apply(state.idleAnimationState, state.ageInTicks);
        this.tameAnimation.apply(state.tameAnimationState, state.ageInTicks);
        if (!state.tameAnimationState.isStarted() && !state.sitAnimationState.isStarted()) {
            this.standAnimation.apply(state.standAnimationState, state.ageInTicks);
        }
        if (!state.tameAnimationState.isStarted()) {
            this.attackAnimation.apply(state.attackAnimationState, state.ageInTicks);
        }
        if (!state.tameAnimationState.isStarted()) {
            this.sitAnimation.apply(state.sitAnimationState, state.ageInTicks);
        }

        if (!state.tameAnimationState.isStarted() && !state.sitAnimationState.isStarted()) {
            if (state.runAnimationState.isStarted()) {
                this.runAnimation.apply(state.runAnimationState, state.ageInTicks);
            } else {
                this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 5.0F, 10.0F);
            }
        }
    }
}