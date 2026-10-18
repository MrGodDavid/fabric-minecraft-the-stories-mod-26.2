package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

import java.util.UUID;

/**
 * @author Mr. GodDavid
 * @since 10/16/2026
 */
public class OakieEntityRenderState extends LivingEntityRenderState {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState healAnimationState = new AnimationState();
    public final AnimationState enchantingAnimationState = new AnimationState();
    public final AnimationState disenchantingAnimationState = new AnimationState();
    public final AnimationState tameAnimationState = new AnimationState();
    public boolean enchanting = false;
    public UUID entityUUID;
}
