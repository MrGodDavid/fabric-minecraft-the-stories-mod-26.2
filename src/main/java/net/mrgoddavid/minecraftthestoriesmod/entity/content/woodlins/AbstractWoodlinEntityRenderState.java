package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

import java.util.UUID;

/**
 * @author Mr. GodDavid
 * @since 11/5/2026
 */
public class AbstractWoodlinEntityRenderState extends LivingEntityRenderState {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState enchantingAnimationState = new AnimationState();
    public final AnimationState disenchantingAnimationState = new AnimationState();
    public final AnimationState tameAnimationState = new AnimationState();
    public final AnimationState abilityUseAnimationState = new AnimationState();
    public boolean enchanting = false;
    public UUID entityUUID;
    public int eyeColor;
}
