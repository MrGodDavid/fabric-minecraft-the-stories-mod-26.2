package net.mrgoddavid.minecraftthestoriesmod.client.model.effects;

import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 11/26/2026
 */
public final class MtsPlayerAnimations {

    public static final MtsDaffiAnimator.ThirdPersonAnimationBuilder DODGE_ROLL = MtsDaffiAnimator.thirdPersonAnimator()
            .addKeyFrame(MtsDaffiAnimator.thirdPersonAnimationKeyFrame()
                    .rotateXForward(MtsDaffiAnimator.Part.LEFT_ARM, 0.0F, 43.301F, 0, 1, null)
                    .rotateXForward(MtsDaffiAnimator.Part.LEFT_ARM, 0.0F, 30.534F, 0, 1, null)
                    .rotateXBackward(MtsDaffiAnimator.Part.HEAD, 0.0F, 15.863F, 0, 1, null)
                    .rotateXBackward(MtsDaffiAnimator.Part.BODY, 0.0F, 25.0F, 0, 1, null)
                    .moveDown(MtsDaffiAnimator.Part.BODY, 0.0F, 15.0F + 0.10302F, 0, 1, null)
                    .rotateXForward(MtsDaffiAnimator.Part.LEFT_LEG, 0.0F, 11.063F, 0, 1, null)
                    .rotateXForward(MtsDaffiAnimator.Part.RIGHT_LEG, 0.0F, 44.665F, 0, 1, null)
            )
            .addKeyFrame(MtsDaffiAnimator.thirdPersonAnimationKeyFrame()
                    .rotateXForward(MtsDaffiAnimator.Part.LEFT_ARM, 0.0F, 50.577F, 1, 2, null)
                    .rotateXForward(MtsDaffiAnimator.Part.LEFT_ARM, 0.0F, 46.816F, 1, 2, null)
                    .rotateXForward(MtsDaffiAnimator.Part.HEAD, 0.0F, 48.491F, 1, 2, null)
                    .rotateXBackward(MtsDaffiAnimator.Part.BODY, 0.0F, 49.0F, 1, 2, null)
                    .moveDown(MtsDaffiAnimator.Part.BODY, 0.0F, 0.32968F, 1, 2, null)
                    .rotateXForward(MtsDaffiAnimator.Part.LEFT_LEG, 0.0F, 16.706F, 1, 2, null)
                    .rotateXForward(MtsDaffiAnimator.Part.RIGHT_LEG, 0.0F, 62.575F, 1, 2, null)
            )
            .addKeyFrame(MtsDaffiAnimator.thirdPersonAnimationKeyFrame()
                    .rotateXBackward(MtsDaffiAnimator.Part.LEFT_ARM, 0.0F, 32.173F, 2, 3, null)
                    .rotateXBackward(MtsDaffiAnimator.Part.LEFT_ARM, 0.0F, 34.842F, 2, 3, null)
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
            .addKeyFrame(MtsDaffiAnimator.thirdPersonAnimationKeyFrame()
                    .moveBackward(MtsDaffiAnimator.Part.BODY, 0.0F, 0.594119F, 10, 24, null)
                    .moveUp(MtsDaffiAnimator.Part.BODY, 0.0F, 15.0F + 0.67854F, 10, 24, null)
                    .rotateXForward(MtsDaffiAnimator.Part.BODY, 0.0F, 53.0F, 10, 24, null)
                    .rotateXBackward(MtsDaffiAnimator.Part.LEFT_ARM, 0.0F, 61.705F, 10, 24, null)
                    .rotateXBackward(MtsDaffiAnimator.Part.LEFT_ARM, 0.0F, 42.507F, 10, 24, null)
                    .moveBackward(MtsDaffiAnimator.Part.HEAD, 0.0F, 0.40487F, 10, 24, null)
                    .moveDown(MtsDaffiAnimator.Part.HEAD, 0.0F, 0.0335F, 10, 24, null)
                    .rotateXForward(MtsDaffiAnimator.Part.HEAD, 0.0F, 43.285F, 10, 24, null)
                    .moveBackward(MtsDaffiAnimator.Part.LEFT_LEG, 0.0F, 1.10494F, 10, 24, null)
                    .moveDown(MtsDaffiAnimator.Part.LEFT_LEG, 0.0F, 0.771756F, 10, 24, null)
                    .rotateXForward(MtsDaffiAnimator.Part.LEFT_LEG, 0.0F, 9.51441F, 10, 24, null)
                    .rotateXBackward(MtsDaffiAnimator.Part.RIGHT_LEG, 0.0F, 65.885F, 10, 24, null)
            );

    private MtsPlayerAnimations() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
