package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.mrgoddavid.minecraftthestoriesmod.client.model.effects.MtsDaffiAnimator;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityModel;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 10/16/2026
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class OakieEntityModel extends AbstractWoodlinEntityModel<OakieEntityRenderState> {

    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart staff;
    private final ModelPart left_antler;
    private final ModelPart right_antler;
    private final ModelPart right_leg;
    private final ModelPart left_leg;

    public OakieEntityModel(ModelPart root) {
        super(root);
        this.body = super.root.getChild("body");
        this.left_arm = this.body.getChild("left_arm");
        this.right_arm = this.body.getChild("right_arm");
        this.staff = this.right_arm.getChild("staff");
        this.left_antler = this.body.getChild("left_antler");
        this.right_antler = this.body.getChild("right_antler");
        this.right_leg = super.root.getChild("right_leg");
        this.left_leg = super.root.getChild("left_leg");
    }

    @Override
    protected @NonNull KeyframeAnimation bakeIdleAnimation() {
        return OakieEntityAnimations.IDLE.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeWalkAnimation() {
        return OakieEntityAnimations.WALK.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeAbilityUseAnimation() {
        return OakieEntityAnimations.HEAL.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeEnchantingAnimation() {
        return OakieEntityAnimations.ENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeDisenchantingAnimation() {
        return OakieEntityAnimations.DISENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeTameAnimation() {
        return OakieEntityAnimations.TAME.bake(this.root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));
        body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 26).addBox(-0.25F, -0.5F, -0.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.75F, -3.5F, -0.5F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(8, 26).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.25F, -3.5F, -0.5F));
        right_arm.addOrReplaceChild("staff", CubeListBuilder.create().texOffs(24, 12).addBox(-0.5F, -0.5F, 1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(-0.5F, -0.5F, -8.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(16, 18).addBox(0.0F, -3.5F, -11.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(-3.0F, 0.0F, -12.5F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 1.0F));
        body.addOrReplaceChild("left_antler", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -5.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, 1.5F));
        body.addOrReplaceChild("right_antler", CubeListBuilder.create().texOffs(24, 6).addBox(-5.0F, -5.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -6.0F, 1.5F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(26, 16).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -2.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(26, 19).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -2.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}