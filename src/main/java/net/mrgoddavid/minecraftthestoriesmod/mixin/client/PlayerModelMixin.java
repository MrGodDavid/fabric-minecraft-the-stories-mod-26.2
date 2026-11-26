package net.mrgoddavid.minecraftthestoriesmod.mixin.client;

import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.util.Mth;
import net.mrgoddavid.minecraftthestoriesmod.client.model.effects.MtsDaffiAnimator;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.DodgeRollAnimationManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mr. GodDavid
 * @since 11/26/2026
 */
@Mixin(PlayerModel.class)
public class PlayerModelMixin {

    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)V", at = @At("TAIL"))
    private void mts$addPlayerAnimations(AvatarRenderState state, CallbackInfo ci) {
        PlayerModel playerModel = (PlayerModel) (Object) this;
        if (!DodgeRollAnimationManager.isRolling(state.id)) return;

        float partialTick = state.ageInTicks - Mth.floor(state.ageInTicks);
        float animationFrame = DodgeRollAnimationManager.getProgress(state.id, partialTick);

//        MtsPlayerAnimations.TEST_ANIMATION.animate(playerModel, state.attackTime);

        MtsDaffiAnimator.thirdPersonAnimator()
                .addKeyFrame(MtsDaffiAnimator.thirdPersonAnimationKeyFrame()
                        .rotateXForward(MtsDaffiAnimator.Part.LEFT_ARM, 0.0F, 43.301F, 0, 1, null)
                        .rotateXBackward(MtsDaffiAnimator.Part.HEAD, 0.0F, 15.863F, 0, 1, null)
                        .rotateXBackward(MtsDaffiAnimator.Part.BODY, 0.0F, 25.0F, 0, 1, null)
                        .moveDown(MtsDaffiAnimator.Part.BODY, 0.0F, 10.0F + 0.10302F, 0, 1, null)
                        .rotateXForward(MtsDaffiAnimator.Part.LEFT_LEG, 0.0F, 11.063F, 0, 1, null)
                        .rotateXForward(MtsDaffiAnimator.Part.RIGHT_LEG, 0.0F, 44.665F, 0, 1, null)
                )
                .addKeyFrame(MtsDaffiAnimator.thirdPersonAnimationKeyFrame()
                        .rotateXForward(MtsDaffiAnimator.Part.LEFT_ARM, 0.0F, 50.577F, 1, 2, null)
                        .rotateXForward(MtsDaffiAnimator.Part.HEAD, 0.0F, 48.491F, 1, 2, null)
                        .rotateXBackward(MtsDaffiAnimator.Part.BODY, 0.0F, 49.0F, 1, 2, null)
                        .moveDown(MtsDaffiAnimator.Part.BODY, 0.0F, 0.32968F, 1, 2, null)
                        .rotateXForward(MtsDaffiAnimator.Part.LEFT_LEG, 0.0F, 16.706F, 1, 2, null)
                        .rotateXForward(MtsDaffiAnimator.Part.RIGHT_LEG, 0.0F, 62.575F, 1, 2, null)
                )
                .addKeyFrame(MtsDaffiAnimator.thirdPersonAnimationKeyFrame()
                        .rotateXBackward(MtsDaffiAnimator.Part.LEFT_ARM, 0.0F, 32.173F, 2, 3, null)
                        .rotateXForward(MtsDaffiAnimator.Part.HEAD, 0.0F, 28.61F, 2, 3, null)
                        .rotateXBackward(MtsDaffiAnimator.Part.BODY, 0.0F, 42.0F, 2, 3, null)
                        .moveUp(MtsDaffiAnimator.Part.BODY, 0.0F, 1.02435F, 2, 3, null)
                        .rotateXForward(MtsDaffiAnimator.Part.LEFT_LEG, 0.0F, 14.97F, 2, 3, null)
                        .rotateXForward(MtsDaffiAnimator.Part.RIGHT_LEG, 0.0F, 6.14F, 2, 3, null)
                )
                .addKeyFrame(MtsDaffiAnimator.thirdPersonAnimationKeyFrame()
                        .rotateXBackward(MtsDaffiAnimator.Part.HEAD, 0.0F, 22.958F, 3, 5, null)
                        .moveForward(MtsDaffiAnimator.Part.HEAD, 0.0F, 0.25706F, 3, 5, null)
                        .moveDown(MtsDaffiAnimator.Part.HEAD, 0.0F, 0.2125F, 3, 5, null)
                        .rotateXBackward(MtsDaffiAnimator.Part.BODY, 0.0F, 67.0F, 3, 5, null)
                        .moveUp(MtsDaffiAnimator.Part.BODY, 0.0F, 1.08585F, 3, 5, null)
                        .rotateXForward(MtsDaffiAnimator.Part.LEFT_LEG, 0.0F, 79.741F, 3, 5, null)
                        .rotateXBackward(MtsDaffiAnimator.Part.RIGHT_LEG, 0.0F, 24.82F, 3, 5, null)
                )
                .addKeyFrame(MtsDaffiAnimator.thirdPersonAnimationKeyFrame()
                        .rotateXForward(MtsDaffiAnimator.Part.HEAD, 0.0F, 7.077F, 5, 7, null)
                        .rotateYLeft(MtsDaffiAnimator.Part.HEAD, 0.0F, 1.2778F, 5, 7, null)
                        .moveForward(MtsDaffiAnimator.Part.HEAD, 0.0F, 0.05913F, 5, 7, null)
                        .moveUp(MtsDaffiAnimator.Part.HEAD, 0.0F, 0.0984F, 5, 7, null)
                        .rotateHorizontallyZLeft(MtsDaffiAnimator.Part.HEAD, 0.0F, 5.5711F, 5, 7, null)
                        .rotateXBackward(MtsDaffiAnimator.Part.BODY, 0.0F, 103.0F, 5, 7, null)
                        .moveDown(MtsDaffiAnimator.Part.BODY, 0.0F, 1.761921F, 5, 7, null)
                        .rotateXBackward(MtsDaffiAnimator.Part.LEFT_LEG, 0.0F, 10.38F, 5, 7, null)
                        .rotateXBackward(MtsDaffiAnimator.Part.RIGHT_LEG, 0.0F, 56.439F, 5, 7, null)
                )
                .addKeyFrame(MtsDaffiAnimator.thirdPersonAnimationKeyFrame()
                        .rotateXBackward(MtsDaffiAnimator.Part.HEAD, 0.0F, 88.642F, 7, 10, null)
                        .rotateYRight(MtsDaffiAnimator.Part.HEAD, 0.0F, 1.2778F, 7, 10, null)
                        .moveForward(MtsDaffiAnimator.Part.HEAD, 0.0F, 0.08868F, 3, 5, null)
                        .moveUp(MtsDaffiAnimator.Part.HEAD, 0.0F, 0.1476F, 3, 5, null)
                        .rotateHorizontallyZRight(MtsDaffiAnimator.Part.HEAD, 0.0F, 5.5711F, 7, 10, null)
                        .rotateXBackward(MtsDaffiAnimator.Part.BODY, 0.0F, 127.0F, 7, 10, null)
                        .moveDown(MtsDaffiAnimator.Part.BODY, 0.0F, 0.594119F, 7, 10, null)
                        .rotateXBackward(MtsDaffiAnimator.Part.LEFT_LEG, 0.0F, 121.6144F, 7, 10, null)
                        .rotateXForward(MtsDaffiAnimator.Part.RIGHT_LEG, 0.0F, 33.764F, 7, 10, null)
                )
                .animate(playerModel, animationFrame);
    }
}
