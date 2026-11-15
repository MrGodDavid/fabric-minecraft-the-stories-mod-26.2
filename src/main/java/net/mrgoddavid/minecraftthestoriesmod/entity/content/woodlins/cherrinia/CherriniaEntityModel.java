package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.cherrinia;
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
public class CherriniaEntityModel extends AbstractWoodlinEntityModel<CherriniaEntityRenderState> {

    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart staff_left;
    private final ModelPart right_arm;
    private final ModelPart staff_right;
    private final ModelPart right_antler;
    private final ModelPart left_antler;
    private final ModelPart potion;
    private final ModelPart cape_left;
    private final ModelPart cape_right;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public CherriniaEntityModel(ModelPart root) {
        super(root);
        this.body = this.root.getChild("body");
        this.left_arm = this.body.getChild("left_arm");
        this.staff_left = this.left_arm.getChild("staff_left");
        this.right_arm = this.body.getChild("right_arm");
        this.staff_right = this.right_arm.getChild("staff_right");
        this.right_antler = this.body.getChild("right_antler");
        this.left_antler = this.body.getChild("left_antler");
        this.potion = this.body.getChild("potion");
        this.cape_left = this.potion.getChild("cape_left");
        this.cape_right = this.potion.getChild("cape_right");
        this.left_leg = this.root.getChild("left_leg");
        this.right_leg = this.root.getChild("right_leg");
    }

    @Override
    protected @NonNull KeyframeAnimation bakeIdleAnimation() {
        return CherriniaEntityAnimations.IDLE.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeWalkAnimation() {
        return CherriniaEntityAnimations.WALK.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeAbilityUseAnimation() {
        return CherriniaEntityAnimations.ENTICE.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeEnchantingAnimation() {
        return CherriniaEntityAnimations.ENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeDisenchantingAnimation() {
        return CherriniaEntityAnimations.DISENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeTameAnimation() {
        return CherriniaEntityAnimations.TAME.bake(this.root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));
        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(30, 37).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -4.5F, -0.5F));

        left_arm.addOrReplaceChild("staff_left", CubeListBuilder.create().texOffs(0, 12).addBox(-0.5F, -0.5F, -5.5F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(-1.5F, -1.5F, -9.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(36, 37).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -4.5F, -0.5F));
        right_arm.addOrReplaceChild("staff_right", CubeListBuilder.create().texOffs(22, 12).addBox(-0.5F, -0.5F, -5.5F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 30).addBox(-1.5F, -1.5F, -9.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));
        body.addOrReplaceChild("right_antler", CubeListBuilder.create().texOffs(0, 37).addBox(-5.0F, -5.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -6.0F, 2.0F));
        body.addOrReplaceChild("left_antler", CubeListBuilder.create().texOffs(36, 23).addBox(-1.0F, -5.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -6.0F, 2.0F));

        PartDefinition potion = body.addOrReplaceChild("potion", CubeListBuilder.create().texOffs(0, 23).addBox(-2.5F, -6.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(30, 31).addBox(-1.5F, -3.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(20, 23).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition cape_left = potion.addOrReplaceChild("cape_left", CubeListBuilder.create(), PartPose.offset(2.5F, -5.25F, 2.0F));
        cape_left.addOrReplaceChild("cape_right_r1", CubeListBuilder.create().texOffs(22, 31).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.25F, -0.5F, 0.5236F, 0.0F, 0.0F));
        PartDefinition cape_right = potion.addOrReplaceChild("cape_right", CubeListBuilder.create(), PartPose.offset(-2.5F, -5.5F, 2.25F));
        cape_right.addOrReplaceChild("cape_left_r1", CubeListBuilder.create().texOffs(14, 31).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -0.75F, 0.5236F, 0.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(24, 7).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -2.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(28, 7).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -2.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}