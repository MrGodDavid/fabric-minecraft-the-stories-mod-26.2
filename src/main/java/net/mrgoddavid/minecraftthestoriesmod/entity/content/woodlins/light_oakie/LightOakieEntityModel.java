package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.light_oakie;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityModel;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid fixed this file.
 * @author Blockbench 5.2.2 generated this file.
 * @since 11/14/2026
 */
@SuppressWarnings({"FieldCanBeLocal", "SpellCheckingInspection", "unused"})
public class LightOakieEntityModel extends AbstractWoodlinEntityModel<LightOakieEntityRenderState> {

    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_staff;
    private final ModelPart right_arm;
    private final ModelPart left_staff;
    private final ModelPart potion;
    private final ModelPart cape_left;
    private final ModelPart cape_right;
    private final ModelPart left_antler;
    private final ModelPart right_antler;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public LightOakieEntityModel(ModelPart root) {
        super(root);
        this.body = this.root.getChild("body");
        this.left_arm = this.body.getChild("left_arm");
        this.right_staff = this.left_arm.getChild("right_staff");
        this.right_arm = this.body.getChild("right_arm");
        this.left_staff = this.right_arm.getChild("left_staff");
        this.potion = this.body.getChild("potion");
        this.cape_left = this.potion.getChild("cape_left");
        this.cape_right = this.potion.getChild("cape_right");
        this.left_antler = this.potion.getChild("left_antler");
        this.right_antler = this.potion.getChild("right_antler");
        this.left_leg = this.root.getChild("left_leg");
        this.right_leg = this.root.getChild("right_leg");
    }

    @Override
    protected @NonNull KeyframeAnimation bakeIdleAnimation() {
        return LightOakieEntityAnimations.IDLE.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeWalkAnimation() {
        return LightOakieEntityAnimations.WALK.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeAbilityUseAnimation() {
        return LightOakieEntityAnimations.INVISIBLE_BUFF.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeEnchantingAnimation() {
        return LightOakieEntityAnimations.ENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeDisenchantingAnimation() {
        return LightOakieEntityAnimations.DISENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeTameAnimation() {
        return LightOakieEntityAnimations.TAME.bake(this.root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 18).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));
        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 42).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -4.0F, 0.0F));

        left_arm.addOrReplaceChild("right_staff", CubeListBuilder.create().texOffs(0, 30).addBox(7.5F, -0.5F, -7.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(22, 42).addBox(5.5F, -2.5F, -5.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 9).addBox(6.0F, 0.0F, -15.0F, 6.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 3.5F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(36, 42).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -4.0F, 0.0F));
        right_arm.addOrReplaceChild("left_staff", CubeListBuilder.create().texOffs(24, 18).addBox(-8.5F, -0.5F, -7.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(12, 42).addBox(-10.5F, -2.5F, -5.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-12.0F, 0.0F, -15.0F, 6.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 3.5F, 0.0F));

        PartDefinition potion = body.addOrReplaceChild("potion", CubeListBuilder.create().texOffs(30, 0).addBox(-2.5F, -6.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(-1.5F, -3.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(30, 7).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition cape_left = potion.addOrReplaceChild("cape_left", CubeListBuilder.create(), PartPose.offset(3.0F, -5.0F, 1.5F));
        cape_left.addOrReplaceChild("cape_left_r1", CubeListBuilder.create().texOffs(24, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition cape_right = potion.addOrReplaceChild("cape_right", CubeListBuilder.create(), PartPose.offset(-2.5F, -5.0F, 1.5F));
        cape_right.addOrReplaceChild("cape_right_r1", CubeListBuilder.create().texOffs(32, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));
        potion.addOrReplaceChild("left_antler", CubeListBuilder.create().texOffs(40, 30).addBox(-1.0F, -5.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 2.75F));
        potion.addOrReplaceChild("right_antler", CubeListBuilder.create().texOffs(40, 36).addBox(-4.0F, -5.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 2.75F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(30, 15).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -2.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(34, 15).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -2.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}