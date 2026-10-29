package net.mrgoddavid.minecraftthestoriesmod.client.model.effects;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrgoddavid.ocl.function.interpolation.EasingFunctions;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.utils.math.MathUtils;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 10/11/2026
 */
public final class MtsEntityAnimationAnimator {

    /**
     * Must call this method before animating any items with mixin.
     *
     * @param player      player.
     * @param frameInterp delta time between each tick.
     * @param attack      a value between 0 and 1 when player left click while holding the item.
     * @param poseStack   stack of poses.
     * @param invert      1 or -1, depends on main hand or offhand while holding the item.
     */
    public static void startAnimation(AbstractClientPlayer player, float frameInterp, float attack, PoseStack poseStack, float invert) {
        float rise = 0.0f;
        boolean isSelectedItem = player.getMainHandItem().is(MtsItems.TEST_LONG_STICK);
        if (isSelectedItem && attack <= 0.0f) {
            float attackStrength = Mth.clamp(player.getAttackStrengthScale(frameInterp), 0.0F, 1.0F);
            if (attackStrength <= 0.0f) {
                rise = 0.01f * (1.0F - attackStrength);
            }
        }
        poseStack.translate(invert * 0.56F, -0.52F + rise, -0.72F);
    }

    public static void applyMobHeadRotation(EntityModel<? extends LivingEntityRenderState> model, float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 40f);

        Optional.ofNullable(model.getChildPart("head"))
                .or(() -> Optional.ofNullable(model.getChildPart("body")))
                .or(() -> Optional.ofNullable(model.getChildPart("root")))
                .orElse(model.root()).yRot = headYaw * ((float) Math.PI / 180F);
        Optional.ofNullable(model.getChildPart("head"))
                .or(() -> Optional.ofNullable(model.getChildPart("body")))
                .or(() -> Optional.ofNullable(model.getChildPart("root")))
                .orElse(model.root()).xRot = multiplierFromEntityType(model) * headPitch * ((float) Math.PI / 180F);
    }

    /**
     * Don't ask me (Mr. GodDavid) why we have this here. It works here and please DO NOT TRY TO SIMPLIFY IT!
     *
     * @param model model.
     * @return multiplier based on the type of model.
     */
    private static float multiplierFromEntityType(EntityModel<? extends LivingEntityRenderState> model) {
        if (model instanceof BrownBearEntityModel) {
            return -1.0f;
        }
        return 1.0f;
    }

    private static int getMultiplierFromArm(HumanoidArm arm) {
        return (arm == HumanoidArm.RIGHT) ? 1 : -1;
    }

    // ===================================================[TRANSLATION]===================================================
    public static void moveLeft(PoseStack poseStack, float attackTick, HumanoidArm arm, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = getMultiplierFromArm(arm);
        float translateX = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(-translateX, 0.0f, 0.0f);
    }

    public static void moveRight(PoseStack poseStack, float attackTick, HumanoidArm arm, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = getMultiplierFromArm(arm);
        float translateX = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(translateX, 0.0f, 0.0f);
    }

    public static void moveUp(PoseStack poseStack, float attackTick, HumanoidArm arm, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float translateY = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(0.0f, translateY, 0.0f);
    }

    public static void moveDown(PoseStack poseStack, float attackTick, HumanoidArm arm, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float translateY = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(0.0f, -translateY, 0.0f);
    }

    public static void moveForward(PoseStack poseStack, float attackTick, HumanoidArm arm, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float translateZ = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(0.0f, 0.0f, -translateZ);
    }

    public static void moveBackward(PoseStack poseStack, float attackTick, HumanoidArm arm, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float translateZ = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(0.0f, 0.0f, translateZ);
    }

    // ====================================================[ROTATION]=====================================================
    public static void rotateXForward(PoseStack poseStack, float attackTick, HumanoidArm arm, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = getMultiplierFromArm(arm);
        float xRot = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.XN.rotationDegrees(xRot));
    }

    public static void rotateXBackward(PoseStack poseStack, float attackTick, HumanoidArm arm, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float xRot = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
    }

    public static void rotateYLeft(PoseStack poseStack, float attackTick, HumanoidArm arm, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float yRot = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
    }

    public static void rotateYRight(PoseStack poseStack, float attackTick, HumanoidArm arm, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = getMultiplierFromArm(arm);
        float yRot = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.YN.rotationDegrees(yRot));
    }

    public static void rotateHorizontallyZLeft(PoseStack poseStack, float attackTick, HumanoidArm arm, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = getMultiplierFromArm(arm);
        float zRot = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));
    }

    public static void rotateHorizontallyZRight(PoseStack poseStack, float attackTick, HumanoidArm arm, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = getMultiplierFromArm(arm);
        float zRot = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.ZN.rotationDegrees(zRot));
    }

    // ======================================================[SCALE]======================================================
    public static void expandLeft(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float scaleX = 1.0f + MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(scaleX, 1.0f, 1.0f);
    }

    public static void shrinkRight(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float scaleX = 1.0f - MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(scaleX, 1.0f, 1.0f);
    }

    public static void expandUp(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float scaleY = 1.0f + MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(1.0f, scaleY, 1.0f);
    }

    public static void shrinkDown(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float scaleY = 1.0f - MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(1.0f, scaleY, 1.0f);
    }

    public static void expandLongitudinally(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float scaleZ = 1.0f + MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(1.0f, 1.0f, scaleZ);
    }

    public static void shrinkLongitudinally(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float scaleZ = 1.0f - MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(1.0f, 1.0f, scaleZ);
    }

    private static void validateParamsOrThrow(float startFrame, float endFrame) {
        if (startFrame < 0.0F || startFrame > 1.0F) {
            ErrorMessageBuilder.infoAndThrow(MtsEntityAnimationAnimator.class, ThrowReason.ENTITY_ANIMATION_UTILS_INVALID_START_FRAME, startFrame);
        } else if (endFrame < 0.0F || endFrame > 1.0F) {
            ErrorMessageBuilder.infoAndThrow(MtsEntityAnimationAnimator.class, ThrowReason.ENTITY_ANIMATION_UTILS_INVALID_END_FRAME, endFrame);
        } else if (startFrame > endFrame) {
            ErrorMessageBuilder.infoAndThrow(MtsEntityAnimationAnimator.class, ThrowReason.ENTITY_ANIMATION_UTILS_START_FRAME_GREATER_THAN_END_FRAME, startFrame, endFrame);
        }
    }

    public static AnimationBuilder animator() {
        return new AnimationBuilder();
    }

    public static KeyFrameBuilder keyFrame() {
        return new KeyFrameBuilder();
    }

    private MtsEntityAnimationAnimator() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }

    /**
     * @author Mr. GodDavid
     * @since 10/26/2026
     */
    public static final class KeyFrameBuilder {
        private final List<TriConsumer<PoseStack, Float, HumanoidArm>> frameChannels;

        private KeyFrameBuilder() {
            this.frameChannels = new ArrayList<>();
        }

        private float easeOrLinear(float attackTick, float startFrame, float endFrame, EasingFunctions.EasingFunction easingFunction) {
            float manipulatedTick = Mth.clamp(attackTick, startFrame, endFrame);
            if (easingFunction == null) {
                return manipulatedTick;
            }
            float progress = MathUtils.linearInterpolation(manipulatedTick, startFrame, endFrame, 0.0F, 1.0F);
            return (float) easingFunction.value(progress);
        }

        public KeyFrameBuilder moveLeft(float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.moveLeft(poseStack, manipulatedTick, arm, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder moveRight(float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.moveRight(poseStack, manipulatedTick, arm, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder moveUp(float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.moveUp(poseStack, manipulatedTick, arm, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder moveDown(float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.moveDown(poseStack, manipulatedTick, arm, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder moveForward(float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.moveForward(poseStack, manipulatedTick, arm, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder moveBackward(float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.moveBackward(poseStack, manipulatedTick, arm, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder rotateXBackward(float startDegrees, float endDegrees, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.rotateXBackward(poseStack, manipulatedTick, arm, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder rotateXForward(float startDegrees, float endDegrees, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.rotateXForward(poseStack, manipulatedTick, arm, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder rotateYRight(float startDegrees, float endDegrees, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.rotateYRight(poseStack, manipulatedTick, arm, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder rotateYLeft(float startDegrees, float endDegrees, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.rotateYLeft(poseStack, manipulatedTick, arm, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder rotateHorizontallyZRight(float startDegrees, float endDegrees, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.rotateHorizontallyZRight(poseStack, manipulatedTick, arm, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder rotateHorizontallyZLeft(float startDegrees, float endDegrees, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.rotateHorizontallyZLeft(poseStack, manipulatedTick, arm, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder expandLeft(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.expandLeft(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder shrinkRight(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.shrinkRight(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder expandUp(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.expandUp(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder shrinkDown(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.shrinkDown(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder expandLongitudinally(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.expandLongitudinally(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        public KeyFrameBuilder shrinkLongitudinally(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frameChannels.add(((poseStack, attackTick, arm) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsEntityAnimationAnimator.shrinkLongitudinally(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            }));
            return this;
        }

        private float normalizeFrames(int frame) {
            return frame / 24.0F;
        }

        public void accept(PoseStack poseStack, float attackTick, HumanoidArm arm) {
            this.frameChannels.forEach(frameChannel -> frameChannel.accept(poseStack, attackTick, arm));
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 10/24/2026
     */
    public static final class AnimationBuilder {
        private final List<KeyFrameBuilder> frames;

        private AnimationBuilder() {
            this.frames = new ArrayList<>();
        }

        public AnimationBuilder addKeyFrame(KeyFrameBuilder keyFrameBuilder) {
            this.frames.add(keyFrameBuilder);
            return this;
        }

        public void animate(PoseStack poseStack, float attackTick, HumanoidArm arm) {
            for (int i = this.frames.size() - 1; i >= 0; i--) {
                KeyFrameBuilder frame = this.frames.get(i);
                frame.accept(poseStack, attackTick, arm);
            }
        }
    }
}
