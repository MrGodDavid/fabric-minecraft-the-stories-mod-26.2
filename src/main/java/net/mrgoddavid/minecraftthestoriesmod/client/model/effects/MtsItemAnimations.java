package net.mrgoddavid.minecraftthestoriesmod.client.model.effects;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.utils.math.BlenderQuaternionf;
import net.mrgoddavid.minecraftthestoriesmod.utils.math.MathUtils;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * Combined {@link CudgelAnimations} with this class on 10/24/2026.
 *
 * @author Mr. GodDavid
 * @since 10/24/2026
 */
public final class MtsItemAnimations {

    public static final MtsEntityAnimationAnimator.AnimationBuilder TEST_ANIMATION = MtsEntityAnimationAnimator.animator()
            .moveLeft(0.0f, 0.1f, 0, 10)
            .moveRight(0.0f, 0.1f, 10, 20);

    private MtsItemAnimations() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }

    /**
     * Using brutal mathematical functions to animate cudgel animation:)
     *
     * @author Mr. GodDavid
     * @since 9/27/2026
     */
    public static class CudgelAnimations {

        private CudgelAnimations() {
            ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
        }

        private static CudgelAnimations.TranslationKeyframe[] TRANSLATION_KEYFRAMES = new CudgelAnimations.TranslationKeyframe[]{
                new CudgelAnimations.TranslationKeyframe(0.0F, new Vector3f(0.0F, 0.0F, 0.0F)),
                new CudgelAnimations.TranslationKeyframe(0.25F, new Vector3f(-0.0F, 0.005F, 0.0F)),
                new CudgelAnimations.TranslationKeyframe(0.375F, new Vector3f(0.0F, -0.005F, -0.0F)),
                new CudgelAnimations.TranslationKeyframe(1.0F, new Vector3f(0.0F, 0.0F, 0.0F)),
        };
        private static final CudgelAnimations.RotationKeyframe[] ROTATION_KEYFRAMES = new CudgelAnimations.RotationKeyframe[]{
                new CudgelAnimations.RotationKeyframe(0.0F, BlenderQuaternionf.boneLocalToMinecraft(0.832F, -0.555F, 0.0F, 0.0F)),
                new CudgelAnimations.RotationKeyframe(0.25F, BlenderQuaternionf.boneLocalToMinecraft(0.436F, -0.883F, 0.156F, 0.077F)),
                new CudgelAnimations.RotationKeyframe(0.375F, BlenderQuaternionf.boneLocalToMinecraft(0.875F, -0.296F, -0.123F, -0.362F)),
                new CudgelAnimations.RotationKeyframe(1.0F, BlenderQuaternionf.boneLocalToMinecraft(0.832F, -0.555F, 0.0F, 0.0F))
        };

        public static void swing(PoseStack poseStack, float attackProgress, HumanoidArm arm) {
            float t = Math.clamp(attackProgress, 0.0F, 1.0F);
//        float invert = arm == HumanoidArm.RIGHT ? 1.0F : -1.0F;
//        Vec3 translation = interpolateTranslation(t);
//        poseStack.translate(translation.multiply(-invert, 1.0F, 1.0F));
            Quaternionf rotation = interpolateRotation(t);
            poseStack.mulPose(rotation);
        }

        private static Vec3 interpolateTranslation(float progress) {
            for (int i = 0; i < TRANSLATION_KEYFRAMES.length - 1; ++i) {
                CudgelAnimations.TranslationKeyframe current = TRANSLATION_KEYFRAMES[i];
                CudgelAnimations.TranslationKeyframe next = TRANSLATION_KEYFRAMES[i + 1];
                if (progress >= current.progress() && progress <= next.progress()) {
                    float interval = next.progress() - current.progress();
                    float localProgress = (progress - current.progress()) / interval;
                    return MathUtils.slerp(current.translation(), next.translation(), localProgress);
                }
            }
            return new Vec3(TRANSLATION_KEYFRAMES[TRANSLATION_KEYFRAMES.length - 1].translation());
        }

        private static Quaternionf interpolateRotation(float progress) {
            for (int i = 0; i < ROTATION_KEYFRAMES.length - 1; ++i) {
                CudgelAnimations.RotationKeyframe current = ROTATION_KEYFRAMES[i];
                CudgelAnimations.RotationKeyframe next = ROTATION_KEYFRAMES[i + 1];
                if (progress >= current.progress() && progress <= next.progress()) {
                    float interval = next.progress() - current.progress();
                    float localProgress = (progress - current.progress()) / interval;
                    return new Quaternionf(current.rotation()).slerp(next.rotation(), localProgress).normalize();
                }
            }
            return new Quaternionf(ROTATION_KEYFRAMES[ROTATION_KEYFRAMES.length - 1].rotation()).normalize();
        }

        /**
         * @author Mr. GodDavid
         * @since 9/27/2026
         */
        private record RotationKeyframe(float progress, Quaternionf rotation) {
        }

        /**
         * @author Mr. GodDavid
         * @since 9/27/2026
         */
        private record TranslationKeyframe(float progress, Vector3f translation) {
        }
    }
}
