package net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

/**
 * @author Mr. GodDavid
 * @since 9/8/2026
 */
public class BrownBearEntityRenderState extends LivingEntityRenderState {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState runAnimationState = new AnimationState();
    public final AnimationState tameAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState standAnimationState = new AnimationState();

    public boolean animateWalkingAnimationWhenRiding;
}
