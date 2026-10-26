package net.mrgoddavid.minecraftthestoriesmod.client.model.effects;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.utils.math.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

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
                .orElse(model.root()).yRot = headYaw * ((float) Math.PI / 180F);
        Optional.ofNullable(model.getChildPart("head"))
                .or(() -> Optional.ofNullable(model.getChildPart("body")))
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

    // ===================================================[TRANSLATION]===================================================
    public static void moveLeft(PoseStack poseStack, float attackTick, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float translateX = MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(-translateX, 0.0f, 0.0f);
    }

    public static void moveRight(PoseStack poseStack, float attackTick, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float translateX = MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(translateX, 0.0f, 0.0f);
    }

    public static void moveUp(PoseStack poseStack, float attackTick, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float translateY = MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(0.0f, translateY, 0.0f);
    }

    public static void moveDown(PoseStack poseStack, float attackTick, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float translateY = MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(0.0f, -translateY, 0.0f);
    }

    public static void moveForward(PoseStack poseStack, float attackTick, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float translateZ = MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(0.0f, 0.0f, -translateZ);
    }

    public static void moveBackward(PoseStack poseStack, float attackTick, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float translateZ = MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(0.0f, 0.0f, translateZ);
    }

    // ====================================================[ROTATION]=====================================================
    public static void rotateXForward(PoseStack poseStack, float attackTick, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float xRot = MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.XN.rotationDegrees(xRot));
    }

    public static void rotateXBackward(PoseStack poseStack, float attackTick, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float xRot = MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
    }

    public static void rotateYLeft(PoseStack poseStack, float attackTick, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float yRot = MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
    }

    public static void rotateYRight(PoseStack poseStack, float attackTick, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float yRot = MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.YN.rotationDegrees(yRot));
    }

    public static void rotateHorizontallyZLeft(PoseStack poseStack, float attackTick, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float zRot = MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.ZN.rotationDegrees(zRot));
    }

    public static void rotateHorizontallyZRight(PoseStack poseStack, float attackTick, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float zRot = MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));
    }

    // ======================================================[SCALE]======================================================
    public static void expandLeft(PoseStack poseStack, float attackTick, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float scaleX = 1.0f + MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(scaleX, 1.0f, 1.0f);
    }

    public static void shrinkRight(PoseStack poseStack, float attackTick, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float scaleX = 1.0f - MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(scaleX, 1.0f, 1.0f);
    }

    public static void expandUp(PoseStack poseStack, float attackTick, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float scaleY = 1.0f + MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(1.0f, scaleY, 1.0f);
    }

    public static void shrinkDown(PoseStack poseStack, float attackTick, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float scaleY = 1.0f - MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(1.0f, scaleY, 1.0f);
    }

    public static void expandLongitudinally(PoseStack poseStack, float attackTick, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float scaleZ = 1.0f + MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(1.0f, 1.0f, scaleZ);
    }

    public static void shrinkLongitudinally(PoseStack poseStack, float attackTick, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float clampedAttackTick = Mth.clamp(attackTick, startFrame, endFrame);
        float scaleZ = 1.0f - MathUtils.linearInterpolation(clampedAttackTick, startFrame, endFrame, startScale, endScale);
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

    private MtsEntityAnimationAnimator() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }

    /**
     * @author Mr. GodDavid
     * @since 10/24/2026
     */
    public static final class AnimationBuilder {
        private final List<BiConsumer<PoseStack, Float>> frames;

        private AnimationBuilder() {
            this.frames = new ArrayList<>();
        }

        public AnimationBuilder moveLeft(float startPos, float endPos, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.moveLeft(poseStack, attackTick, startPos, endPos, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder moveRight(float startPos, float endPos, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.moveRight(poseStack, attackTick, startPos, endPos, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder moveUp(float startPos, float endPos, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.moveUp(poseStack, attackTick, startPos, endPos, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder moveDown(float startPos, float endPos, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.moveDown(poseStack, attackTick, startPos, endPos, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder moveForward(float startPos, float endPos, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.moveForward(poseStack, attackTick, startPos, endPos, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder moveBackward(float startPos, float endPos, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.moveBackward(poseStack, attackTick, startPos, endPos, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder rotateXBackward(float startDegrees, float endDegrees, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.rotateXBackward(poseStack, attackTick, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder rotateXForward(float startDegrees, float endDegrees, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.rotateXForward(poseStack, attackTick, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder rotateYRight(float startDegrees, float endDegrees, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.rotateYRight(poseStack, attackTick, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder rotateYLeft(float startDegrees, float endDegrees, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.rotateYLeft(poseStack, attackTick, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder rotateHorizontallyZRight(float startDegrees, float endDegrees, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.rotateHorizontallyZRight(poseStack, attackTick, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder rotateHorizontallyZLeft(float startDegrees, float endDegrees, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.rotateHorizontallyZLeft(poseStack, attackTick, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder expandLeft(float startSize, float endSize, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.expandLeft(poseStack, attackTick, startSize, endSize, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder shrinkRight(float startSize, float endSize, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.shrinkRight(poseStack, attackTick, startSize, endSize, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder expandUp(float startSize, float endSize, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.expandUp(poseStack, attackTick, startSize, endSize, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder shrinkDown(float startSize, float endSize, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.shrinkDown(poseStack, attackTick, startSize, endSize, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder expandLongitudinally(float startSize, float endSize, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.expandLongitudinally(poseStack, attackTick, startSize, endSize, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public AnimationBuilder shrinkLongitudinally(float startSize, float endSize, int startFrame, int endFrame) {
            float normalizedStartFrame = normalizeFrames(startFrame);
            float normalizedEndFrame = normalizeFrames(endFrame);
            this.frames.add(((poseStack, attackTick) -> MtsEntityAnimationAnimator.shrinkLongitudinally(poseStack, attackTick, startSize, endSize, normalizedStartFrame, normalizedEndFrame)));
            return this;
        }

        public void animate(PoseStack poseStack, float attackTick) {
            for (BiConsumer<PoseStack, Float> frame : this.frames) {
                frame.accept(poseStack, attackTick);
            }
        }

        private float normalizeFrames(int frame) {
            return frame / 24.0F;
        }
    }
}
