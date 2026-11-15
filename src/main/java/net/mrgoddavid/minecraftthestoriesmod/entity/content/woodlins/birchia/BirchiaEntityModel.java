package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.birchia;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityModel;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid fixed this file.
 * @author Blockbench 5.2.2 generated this file.
 * @since 11/13/2026
 */
@SuppressWarnings({"unused", "FieldCanBeLocal", "SpellCheckingInspection"})
public class BirchiaEntityModel extends AbstractWoodlinEntityModel<BirchiaEntityRenderState> {

    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart staff;
    private final ModelPart cape_left;
    private final ModelPart cape_right;
    private final ModelPart left_antler;
    private final ModelPart right_antler;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public BirchiaEntityModel(ModelPart root) {
        super(root);
        this.body = this.root.getChild("body");
        this.left_arm = this.body.getChild("left_arm");
        this.right_arm = this.body.getChild("right_arm");
        this.staff = this.right_arm.getChild("staff");
        this.cape_left = this.body.getChild("cape_left");
        this.cape_right = this.body.getChild("cape_right");
        this.left_antler = this.body.getChild("left_antler");
        this.right_antler = this.body.getChild("right_antler");
        this.left_leg = this.root.getChild("left_leg");
        this.right_leg = this.root.getChild("right_leg");
    }

    @Override
    protected @NonNull KeyframeAnimation bakeIdleAnimation() {
        return BirchiaEntityAnimations.IDLE.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeWalkAnimation() {
        return BirchiaEntityAnimations.WALK.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeAbilityUseAnimation() {
        return BirchiaEntityAnimations.JUMP_BOOST.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeEnchantingAnimation() {
        return BirchiaEntityAnimations.ENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeDisenchantingAnimation() {
        return BirchiaEntityAnimations.DISENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeTameAnimation() {
        return BirchiaEntityAnimations.TAME.bake(this.root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 21).addBox(-4.0F, -7.0F, 2.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(22, 31).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 33).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -5.0F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(28, 31).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 31).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -5.0F, 0.0F));

        PartDefinition staff = right_arm.addOrReplaceChild("staff", CubeListBuilder.create().texOffs(0, 12).addBox(-0.5F, -0.5F, -7.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(22, 12).addBox(-1.5F, -1.5F, -13.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(12, 31).addBox(-2.5F, -2.5F, -6.5F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.5F, 0.0F));

        PartDefinition cape_left = body.addOrReplaceChild("cape_left", CubeListBuilder.create(), PartPose.offset(2.5F, -6.0F, 4.0F));
        cape_left.addOrReplaceChild("cape_left_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
        PartDefinition cape_right = body.addOrReplaceChild("cape_right", CubeListBuilder.create(), PartPose.offset(-2.5F, -6.0F, 4.0F));
        cape_right.addOrReplaceChild("cape_right_r1", CubeListBuilder.create().texOffs(0, 23).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
        body.addOrReplaceChild("left_antler", CubeListBuilder.create().texOffs(12, 25).addBox(-1.0F, -5.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -6.0F, 3.0F));
        body.addOrReplaceChild("right_antler", CubeListBuilder.create().texOffs(24, 25).addBox(-5.0F, -5.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -6.0F, 3.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 33).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -2.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(4, 33).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -2.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}