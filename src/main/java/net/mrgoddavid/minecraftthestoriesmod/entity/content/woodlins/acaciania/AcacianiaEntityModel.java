package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.acaciania;// Made with Blockbench 5.2.2

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntityModel;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 11/6/2026
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class AcacianiaEntityModel extends AbstractWoodlinEntityModel<AcacianiaEntityRenderState> {

    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart potion_left;
    private final ModelPart right_arm;
    private final ModelPart potion_right;
    private final ModelPart cape;
    private final ModelPart left_antler;
    private final ModelPart right_antler;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public AcacianiaEntityModel(ModelPart root) {
        super(root, RenderTypes::entityTranslucent);
        this.body = this.root.getChild("body");
        this.left_arm = this.body.getChild("left_arm");
        this.potion_left = this.left_arm.getChild("potion_left");
        this.right_arm = this.body.getChild("right_arm");
        this.potion_right = this.right_arm.getChild("potion_right");
        this.cape = this.body.getChild("cape");
        this.left_antler = this.body.getChild("left_antler");
        this.right_antler = this.body.getChild("right_antler");
        this.left_leg = this.root.getChild("left_leg");
        this.right_leg = this.root.getChild("right_leg");
    }

    @Override
    protected @NonNull KeyframeAnimation bakeIdleAnimation() {
        return AcacianiaEntityAnimations.IDLE.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeWalkAnimation() {
        return AcacianiaEntityAnimations.WALK.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeAbilityUseAnimation() {
        return AcacianiaEntityAnimations.HYDRATE.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeEnchantingAnimation() {
        return AcacianiaEntityAnimations.ENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeDisenchantingAnimation() {
        return AcacianiaEntityAnimations.DISENCHANTING.bake(this.root);
    }

    @Override
    protected @NonNull KeyframeAnimation bakeTameAnimation() {
        return AcacianiaEntityAnimations.TAME.bake(this.root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(16, 12).addBox(-4.0F, -6.5F, 2.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 0).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -4.0F, 0.0F));
        left_arm.addOrReplaceChild("potion_left", CubeListBuilder.create().texOffs(16, 16).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(30, 4).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 27).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 27).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 3.5F, -2.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 6).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -4.0F, 0.0F));
        right_arm.addOrReplaceChild("potion_right", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(30, 7).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 16).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 3.5F, -2.0F));

        PartDefinition cape = body.addOrReplaceChild("cape", CubeListBuilder.create(), PartPose.offset(0.0F, -5.5F, 3.0F));
        cape.addOrReplaceChild("cape_r1", CubeListBuilder.create().texOffs(0, 12).addBox(-3.0F, 0.0F, -1.0F, 6.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.3491F, 0.0F, 0.0F));
        body.addOrReplaceChild("left_antler", CubeListBuilder.create().texOffs(12, 22).addBox(-1.0F, -4.0F, 1.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -6.0F, 0.0F));
        body.addOrReplaceChild("right_antler", CubeListBuilder.create().texOffs(22, 22).addBox(-4.0F, -4.0F, 1.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -6.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(28, 27).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -2.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(30, 0).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -2.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}