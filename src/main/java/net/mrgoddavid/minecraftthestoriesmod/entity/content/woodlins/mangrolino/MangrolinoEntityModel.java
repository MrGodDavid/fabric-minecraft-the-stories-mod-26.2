package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.mangrolino;

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
public class MangrolinoEntityModel extends AbstractWoodlinEntityModel<MangrolinoEntityRenderState> {

    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart staff;
    private final ModelPart energy_ball;
    private final ModelPart left_cape;
    private final ModelPart right_cape;
    private final ModelPart left_antler;
    private final ModelPart right_antler;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public MangrolinoEntityModel(ModelPart root) {
        super(root);
        this.body = this.root.getChild("body");
        this.left_arm = this.body.getChild("left_arm");
        this.right_arm = this.body.getChild("right_arm");
        this.staff = this.right_arm.getChild("staff");
        this.energy_ball = this.staff.getChild("energy_ball");
        this.left_cape = this.body.getChild("left_cape");
        this.right_cape = this.body.getChild("right_cape");
        this.left_antler = this.body.getChild("left_antler");
        this.right_antler = this.body.getChild("right_antler");
        this.left_leg = this.root.getChild("left_leg");
        this.right_leg = this.root.getChild("right_leg");
    }

    @Override
    protected @NonNull KeyframeAnimation bakeIdleAnimation() {
        return MangrolinoEntityAnimations.IDLE.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeWalkAnimation() {
        return MangrolinoEntityAnimations.WALK.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeAbilityUseAnimation() {
        return MangrolinoEntityAnimations.AQUA_BUFF.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeEnchantingAnimation() {
        return MangrolinoEntityAnimations.ENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeDisenchantingAnimation() {
        return MangrolinoEntityAnimations.DISCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeTameAnimation() {
        return MangrolinoEntityAnimations.TAME.bake(this.root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));
        body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(36, 7).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -4.0F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(36, 35).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -4.0F, 0.0F));

        PartDefinition staff = right_arm.addOrReplaceChild("staff", CubeListBuilder.create().texOffs(0, 12).addBox(-0.5F, -0.5F, -9.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(24, 35).addBox(-2.5F, -0.5F, -6.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-3.5F, 0.0F, -15.0F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, 0.5F));
        staff.addOrReplaceChild("staff_stick3_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -11.5F, 0.0F, 0.0F, -1.5708F));
        staff.addOrReplaceChild("energy_ball", CubeListBuilder.create().texOffs(36, 13).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -11.5F));

        PartDefinition left_cape = body.addOrReplaceChild("left_cape", CubeListBuilder.create().texOffs(12, 31).addBox(-2.0F, -1.5F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -5.0F, 3.0F));
        left_cape.addOrReplaceChild("left_cape2_r1", CubeListBuilder.create().texOffs(24, 7).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition right_cape = body.addOrReplaceChild("right_cape", CubeListBuilder.create().texOffs(12, 35).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -5.5F, 3.0F));
        right_cape.addOrReplaceChild("right_cape2_r1", CubeListBuilder.create().texOffs(28, 18).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.3491F, 0.0F, 0.0F));
        body.addOrReplaceChild("left_antler", CubeListBuilder.create().texOffs(28, 29).addBox(-1.5F, -5.5F, 1.75F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -5.5F, 0.0F));
        body.addOrReplaceChild("right_antler", CubeListBuilder.create().texOffs(0, 31).addBox(-4.5F, -5.5F, 1.75F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -5.5F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(24, 18).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -2.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(24, 21).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -2.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}