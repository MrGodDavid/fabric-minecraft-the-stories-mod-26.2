package net.mrgoddavid.minecraftthestoriesmod.entity;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.client.model.effects.MtsDaffiAnimator;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

/**
 * @author Mr. GodDavid
 * @since 11/13/2026
 */
public abstract class AbstractMtsEntityModel<T extends AbstractMtsEntityRenderState> extends EntityModel<T> {

    protected final ModelPart root;
    protected final KeyframeAnimation idleAnimation;
    protected final KeyframeAnimation walkAnimation;

    protected AbstractMtsEntityModel(ModelPart root) {
        this(root, RenderTypes::entityCutout);
    }

    protected AbstractMtsEntityModel(ModelPart root, Function<Identifier, RenderType> renderType) {
        super(root, renderType);
        this.root = root.getChild("root");
        this.idleAnimation = this.bakeIdleAnimation();
        this.walkAnimation = this.bakeWalkAnimation();
    }

    protected abstract @NonNull KeyframeAnimation bakeIdleAnimation();

    protected abstract @NonNull KeyframeAnimation bakeWalkAnimation();

    @Override
    public void setupAnim(T state) {
        super.setupAnim(state);
        this.root.getAllParts().forEach(ModelPart::resetPose);
        MtsDaffiAnimator.applyMobHeadRotation(this, state.yRot, state.xRot);

    }

    protected final void applyWalkAndIdleAnimations(T state) {
        if (state.walkAnimationSpeed > 0.0F) {
            this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 5.0F, 10.0F);
        }

        this.idleAnimation.apply(state.idleAnimationState, state.ageInTicks);
    }
}
