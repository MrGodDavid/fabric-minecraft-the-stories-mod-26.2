package net.mrgoddavid.minecraftthestoriesmod.entity;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

/**
 * Each MTS entity must have an idle state.
 *
 * @author Mr. GodDavid
 * @since 11/13/2026
 */
public abstract class AbstractMtsEntityRenderState extends LivingEntityRenderState {

    public final AnimationState idleAnimationState = new AnimationState();
}
