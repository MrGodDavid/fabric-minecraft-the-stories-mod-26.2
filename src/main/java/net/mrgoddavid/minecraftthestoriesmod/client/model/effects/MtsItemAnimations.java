package net.mrgoddavid.minecraftthestoriesmod.client.model.effects;

import com.mrgoddavid.ocl.function.interpolation.EasingFunctions;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 *
 * @author Mr. GodDavid
 * @since 10/24/2026
 */
public final class MtsItemAnimations {

    public static final MtsEntityAnimationAnimator.AnimationBuilder CUDGEL_ANIMATION = MtsEntityAnimationAnimator.animator()
            .addKeyFrame(MtsEntityAnimationAnimator.keyFrame()
                    .moveRight(0.0f, 0.2f, 0, 4, null)
                    .moveUp(0.0f, 2.0f, 0, 4, null)
                    .rotateXBackward(0.0f, 45.0f, 0, 4, null)
            )
            .addKeyFrame(MtsEntityAnimationAnimator.keyFrame()
                    .moveLeft(0.0f, 0.6f, 4, 12, null)
                    .moveDown(0.0f, 0.5f, 4, 12, null)
                    .rotateXForward(0.0f, 90.0f, 4, 12, null)
                    .rotateHorizontallyZLeft(0.0f, 1.0f, 4, 12, null)
            );

    private MtsItemAnimations() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
