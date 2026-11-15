package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.junmi;

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
public class JunmiEntityModel extends AbstractWoodlinEntityModel<JunmiEntityRenderState> {

    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart staff;
    private final ModelPart banner;
    private final ModelPart left_flag;
    private final ModelPart right_flag;
    private final ModelPart left_antler;
    private final ModelPart right_antler;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public JunmiEntityModel(ModelPart root) {
        super(root);
        this.body = this.root.getChild("body");
        this.left_arm = this.body.getChild("left_arm");
        this.right_arm = this.body.getChild("right_arm");
        this.staff = this.right_arm.getChild("staff");
        this.banner = this.body.getChild("banner");
        this.left_flag = this.banner.getChild("left_flag");
        this.right_flag = this.banner.getChild("right_flag");
        this.left_antler = this.body.getChild("left_antler");
        this.right_antler = this.body.getChild("right_antler");
        this.left_leg = this.root.getChild("left_leg");
        this.right_leg = this.root.getChild("right_leg");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));
        body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(12, 23).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -4.0F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(28, 31).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -4.0F, 0.0F));
        right_arm.addOrReplaceChild("staff", CubeListBuilder.create().texOffs(0, 12).addBox(-0.5F, -0.5F, -6.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(22, 12).addBox(-2.0F, 0.0F, -12.0F, 4.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(12, 31).addBox(-3.0F, -2.0F, -5.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, 0.0F));

        PartDefinition banner = body.addOrReplaceChild("banner", CubeListBuilder.create().texOffs(24, 31).addBox(-0.5F, -9.0F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 20).addBox(-4.5F, -10.0F, -0.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(4.5F, -10.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(6, 32).addBox(-5.5F, -10.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 3.0F));

        PartDefinition left_flag = banner.addOrReplaceChild("left_flag", CubeListBuilder.create(), PartPose.offset(2.75F, -9.25F, 0.0F));
        left_flag.addOrReplaceChild("left_flag_r1", CubeListBuilder.create().texOffs(22, 22).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.25F, 0.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition right_flag = banner.addOrReplaceChild("right_flag", CubeListBuilder.create(), PartPose.offset(-2.75F, -9.25F, 0.0F));
        right_flag.addOrReplaceChild("right_flag_r1", CubeListBuilder.create().texOffs(0, 23).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.25F, 0.0F, 0.3491F, 0.0F, 0.0F));
        body.addOrReplaceChild("left_antler", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -4.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -6.0F, 2.0F));
        body.addOrReplaceChild("right_antler", CubeListBuilder.create().texOffs(24, 6).addBox(-4.0F, -4.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -6.0F, 2.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(18, 23).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -2.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(18, 26).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -2.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeAbilityUseAnimation() {
        return JunmiEntityAnimations.HYDRATE.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeEnchantingAnimation() {
        return JunmiEntityAnimations.ENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeDisenchantingAnimation() {
        return JunmiEntityAnimations.DISCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeTameAnimation() {
        return JunmiEntityAnimations.TAME.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeIdleAnimation() {
        return JunmiEntityAnimations.IDLE.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeWalkAnimation() {
        return JunmiEntityAnimations.WALK.bake(this.root);
    }
}