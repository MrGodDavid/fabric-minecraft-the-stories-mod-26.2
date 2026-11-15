package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.client.model.effects.MtsDaffiAnimator;
import net.mrgoddavid.minecraftthestoriesmod.entity.AbstractMtsEntityModel;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

/**
 * @author Mr. GodDavid
 * @since 11/6/2026
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class AbstractWoodlinEntityModel<T extends AbstractWoodlinEntityRenderState> extends AbstractMtsEntityModel<T> {

    private final KeyframeAnimation abilityUseAnimation;
    private final KeyframeAnimation enchantingAnimation;
    private final KeyframeAnimation disenchantingAnimation;
    private final KeyframeAnimation tameAnimation;

    protected AbstractWoodlinEntityModel(ModelPart root) {
        this(root,  RenderTypes::entityTranslucent);
    }

    protected AbstractWoodlinEntityModel(ModelPart root, Function<Identifier, RenderType> renderType) {
        super(root, renderType);
        this.abilityUseAnimation = this.bakeAbilityUseAnimation();
        this.enchantingAnimation = this.bakeEnchantingAnimation();
        this.disenchantingAnimation = this.bakeDisenchantingAnimation();
        this.tameAnimation = this.bakeTameAnimation();
    }

    protected abstract @NonNull KeyframeAnimation bakeAbilityUseAnimation();

    protected abstract @NonNull KeyframeAnimation bakeEnchantingAnimation();

    protected abstract @NonNull KeyframeAnimation bakeDisenchantingAnimation();

    protected abstract @NonNull KeyframeAnimation bakeTameAnimation();

    @Override
    public void setupAnim(T state) {
        super.setupAnim(state);

        if (state.tameAnimationState.isStarted()) {
            this.tameAnimation.apply(state.tameAnimationState, state.ageInTicks);
            return;
        }
        if (state.enchantingAnimationState.isStarted()) {
            this.enchantingAnimation.apply(state.enchantingAnimationState, state.ageInTicks);
            return;
        }
        if (state.disenchantingAnimationState.isStarted()) {
            this.disenchantingAnimation.apply(state.disenchantingAnimationState, state.ageInTicks);
            return;
        }
        if (state.abilityUseAnimationState.isStarted()) {
            this.abilityUseAnimation.apply(state.abilityUseAnimationState, state.ageInTicks);
            return;
        }

        this.applyWalkAndIdleAnimations(state);
    }
}
