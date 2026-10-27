
package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.mrgoddavid.minecraftthestoriesmod.client.model.effects.MtsEntityAnimationAnimator;

/**
 * @author Mr. GodDavid
 * @since 10/25/2026
 */
public class OakieHealingBallEntityModel extends EntityModel<OakieHealingBallEntityRenderState> {
    private final ModelPart root;
    private final ModelPart ball_outside;
    private final ModelPart ball_inside;

    private final KeyframeAnimation spawnAnimation;
    private final KeyframeAnimation flyingAnimation;
    private final KeyframeAnimation healAnimation;

    public OakieHealingBallEntityModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.ball_outside = this.root.getChild("ball_outside");
        this.ball_inside = this.root.getChild("ball_inside");

        this.spawnAnimation = OakieHealingBallEntityModelAnimations.SPAWN.bake(this.root);
        this.flyingAnimation = OakieHealingBallEntityModelAnimations.FLYING.bake(this.root);
        this.healAnimation = OakieHealingBallEntityModelAnimations.HEAL.bake(this.root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 0.0F));
        root.addOrReplaceChild("ball_outside", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("ball_inside", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(modelData, 32, 32);
    }

    @Override
    public void setupAnim(OakieHealingBallEntityRenderState state) {
        super.setupAnim(state);
        this.root.getAllParts().forEach(ModelPart::resetPose);
        MtsEntityAnimationAnimator.applyMobHeadRotation(this, state.yRot, state.xRot);
        if (state.spawnAnimationState.isStarted()) {
            this.spawnAnimation.apply(state.spawnAnimationState, state.ageInTicks);
            return;
        }
        if (state.healAnimationState.isStarted()) {
            this.healAnimation.apply(state.healAnimationState, state.ageInTicks);
            return;
        }
        this.flyingAnimation.apply(state.flyingAnimationState, state.ageInTicks);
    }
}