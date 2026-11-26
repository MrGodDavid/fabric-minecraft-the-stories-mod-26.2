package net.mrgoddavid.minecraftthestoriesmod.client.model.effects;

import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 11/26/2026
 */
public final class MtsPlayerAnimations {

    public static final MtsDaffiAnimator.ThirdPersonAnimationBuilder TEST_ANIMATION = MtsDaffiAnimator.thirdPersonAnimator()
            .addKeyFrame(MtsDaffiAnimator.thirdPersonAnimationKeyFrame()
                    .moveLeft(MtsDaffiAnimator.Part.LEFT_ARM, 0.0F, 1.0F, 2, 3, null)
            );

    private MtsPlayerAnimations() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
