package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

/**
 * @author Mr. GodDavid
 * @since 10/25/2026
 */
public class OakieHealingBallEntityRenderState extends LivingEntityRenderState {

    public final AnimationState spawnAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();
    public final AnimationState healAnimationState = new AnimationState();
}
