package net.mrgoddavid.minecraftthestoriesmod.client.model.effects;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrgoddavid.ocl.function.interpolation.EasingFunctions;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.brown_bear.BrownBearEntityModel;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.utils.math.MathUtils;
import org.apache.logging.log4j.util.TriConsumer;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * Daffi animator animates animations of item-use animation in first pov. I'm figuring out how to animate third pov
 * animations.
 *
 * @author Mr. GodDavid
 * @since 10/11/2026 created this file.
 * @since 11/25/2026 to 11/25/2026 implemented animatable third-person poses features.
 * @since 11/27/2026 implemented animatable held-in-hand item features.
 */
public final class MtsDaffiAnimator {

    public static int invertAnimation(HumanoidArm arm) {
        return (arm == HumanoidArm.RIGHT) ? 1 : -1;
    }

    public static <S extends LivingEntityRenderState> float getPartialTicks(S state) {
        return state.ageInTicks - Mth.floor(state.ageInTicks);
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

    // ==================================================================================================================
    //                                        THIRD PERSON ITEM IN HAND ANIMATOR
    // ==================================================================================================================
    public enum Part {
        ROOT(null),
        BODY(ROOT),
        HEAD(BODY),
        HAT(HEAD),
        JACKET(BODY),
        LEFT_ARM(BODY),
        RIGHT_ARM(BODY),
        LEFT_SLEEVE(LEFT_ARM),
        RIGHT_SLEEVE(RIGHT_ARM),
        LEFT_LEG(BODY),
        RIGHT_LEG(BODY),
        LEFT_PANTS(LEFT_LEG),
        RIGHT_PANTS(RIGHT_LEG);

        private final Part parent;

        Part(Part parent) {
            this.parent = parent;
        }

        public ModelPart get(PlayerModel model) {
            ModelPart root = model.root();
            return switch (this) {
                case ROOT -> root;
                case BODY -> model.body;
                case HEAD -> model.head;
                case HAT -> model.hat;
                case JACKET -> model.jacket;
                case LEFT_ARM -> model.leftArm;
                case RIGHT_ARM -> model.rightArm;
                case LEFT_LEG -> model.leftLeg;
                case RIGHT_LEG -> model.rightLeg;
                case LEFT_SLEEVE -> model.leftSleeve;
                case RIGHT_SLEEVE -> model.rightSleeve;
                case LEFT_PANTS -> model.leftPants;
                case RIGHT_PANTS -> model.rightPants;
            };
        }

        public Part parent() {
            return this.parent;
        }
    }

    public static void moveLeft(PlayerModel playerModel, Part part, float attackTick, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float translateX = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        ModelPart targetPart = part.get(playerModel);
        targetPart.x += translateX;
    }

    public static void moveRight(PlayerModel playerModel, Part part, float attackTick, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float translateX = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        ModelPart targetPart = part.get(playerModel);
        targetPart.x -= translateX;
    }

    public static void moveUp(PlayerModel playerModel, Part part, float attackTick, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float translateY = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        ModelPart targetPart = part.get(playerModel);
        targetPart.y -= translateY;
    }

    public static void moveDown(PlayerModel playerModel, Part part, float attackTick, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float translateY = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        ModelPart targetPart = part.get(playerModel);
        targetPart.y += translateY;
    }

    public static void moveForward(PlayerModel playerModel, Part part, float attackTick, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float translateZ = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        ModelPart targetPart = part.get(playerModel);
        targetPart.z -= translateZ;
    }

    public static void moveBackward(PlayerModel playerModel, Part part, float attackTick, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float translateZ = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        ModelPart targetPart = part.get(playerModel);
        targetPart.z += translateZ;
    }

    public static void rotateXForward(PlayerModel playerModel, Part part, float attackTick, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float rotationX = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        ModelPart targetPart = part.get(playerModel);
        targetPart.xRot -= MathUtils.degreeToRadians(rotationX);
    }

    public static void rotateXBackward(PlayerModel playerModel, Part part, float attackTick, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float rotationX = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        ModelPart targetPart = part.get(playerModel);
        targetPart.xRot += MathUtils.degreeToRadians(rotationX);
    }

    public static void rotateYLeft(PlayerModel playerModel, Part part, float attackTick, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float rotationY = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        ModelPart targetPart = part.get(playerModel);
        targetPart.yRot -= MathUtils.degreeToRadians(rotationY);
    }

    public static void rotateYRight(PlayerModel playerModel, Part part, float attackTick, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float rotationY = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        ModelPart targetPart = part.get(playerModel);
        targetPart.yRot += MathUtils.degreeToRadians(rotationY);
    }

    public static void rotateHorizontallyZLeft(PlayerModel playerModel, Part part, float attackTick, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float rotationZ = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        ModelPart targetPart = part.get(playerModel);
        targetPart.zRot -= MathUtils.degreeToRadians(rotationZ);
    }

    public static void rotateHorizontallyZRight(PlayerModel playerModel, Part part, float attackTick, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float rotationZ = MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        ModelPart targetPart = part.get(playerModel);
        targetPart.zRot += MathUtils.degreeToRadians(rotationZ);
    }

    public static void expandLeft(PlayerModel playerModel, Part part, float attackTick, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float scaleX = 1.0f + MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        ModelPart targetPart = part.get(playerModel);
        targetPart.xScale *= scaleX;
    }

    public static void shrinkRight(PlayerModel playerModel, Part part, float attackTick, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float scaleX = 1.0f - MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        ModelPart targetPart = part.get(playerModel);
        targetPart.xScale *= scaleX;
    }

    public static void expandUp(PlayerModel playerModel, Part part, float attackTick, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float scaleY = 1.0f + MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        ModelPart targetPart = part.get(playerModel);
        targetPart.yScale *= scaleY;
    }

    public static void shrinkDown(PlayerModel playerModel, Part part, float attackTick, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float scaleY = 1.0f - MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        ModelPart targetPart = part.get(playerModel);
        targetPart.yScale *= scaleY;
    }

    public static void expandLongitudinally(PlayerModel playerModel, Part part, float attackTick, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float scaleZ = 1.0f + MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        ModelPart targetPart = part.get(playerModel);
        targetPart.zScale *= scaleZ;
    }

    public static void shrinkLongitudinally(PlayerModel playerModel, Part part, float attackTick, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        float scaleZ = 1.0f - MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        ModelPart targetPart = part.get(playerModel);
        targetPart.zScale *= scaleZ;
    }

    /**
     * @author Mr. GodDavid
     * @since 11/25/2026
     */
    public static final class ThirdPersonAnimationKeyFrameBuilder {

        private final List<BiConsumer<PlayerModel, Float>> frameChannels;

        private ThirdPersonAnimationKeyFrameBuilder() {
            this.frameChannels = new ArrayList<>();
        }

        public ThirdPersonAnimationKeyFrameBuilder moveLeft(Part part, float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.moveLeft(playerPart, part, manipulatedTick, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder moveRight(Part part, float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.moveRight(playerPart, part, manipulatedTick, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder moveUp(Part part, float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.moveUp(playerPart, part, manipulatedTick, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder moveDown(Part part, float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.moveDown(playerPart, part, manipulatedTick, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder moveForward(Part part, float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.moveForward(playerPart, part, manipulatedTick, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder moveBackward(Part part, float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.moveBackward(playerPart, part, manipulatedTick, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder rotateXForward(Part part, float startDegree, float endDegree, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.rotateXForward(playerPart, part, manipulatedTick, startDegree, endDegree, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        /**
         * Use this method when animating player's body rotate forward.
         *
         * @param part        part of the player model.
         * @param startDegree the starting angle of the model part's animation.
         * @param endDegree   the ending angle of the model part's animation.
         * @param startFrame  the starting frame of the model part's animation.
         * @param endFrame    the ending frame of the model part's animation.
         * @param ease        easing function.
         * @return the third person animation key frame builder.
         */
        public ThirdPersonAnimationKeyFrameBuilder rotateXBackward(Part part, float startDegree, float endDegree, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.rotateXBackward(playerPart, part, manipulatedTick, startDegree, endDegree, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder rotateYLeft(Part part, float startDegree, float endDegree, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.rotateYLeft(playerPart, part, manipulatedTick, startDegree, endDegree, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder rotateYRight(Part part, float startDegree, float endDegree, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.rotateYRight(playerPart, part, manipulatedTick, startDegree, endDegree, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder rotateHorizontallyZLeft(Part part, float startDegree, float endDegree, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.rotateHorizontallyZLeft(playerPart, part, manipulatedTick, startDegree, endDegree, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder rotateHorizontallyZRight(Part part, float startDegree, float endDegree, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.rotateHorizontallyZRight(playerPart, part, manipulatedTick, startDegree, endDegree, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder expandLeft(Part part, float startScale, float endScale, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.expandLeft(playerPart, part, manipulatedTick, startScale, endScale, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder shrinkRight(Part part, float startScale, float endScale, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.shrinkRight(playerPart, part, manipulatedTick, startScale, endScale, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder expandUp(Part part, float startScale, float endScale, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.expandUp(playerPart, part, manipulatedTick, startScale, endScale, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder shrinkDown(Part part, float startScale, float endScale, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.shrinkDown(playerPart, part, manipulatedTick, startScale, endScale, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder expandLongitudinally(Part part, float startScale, float endScale, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.expandLongitudinally(playerPart, part, manipulatedTick, startScale, endScale, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public ThirdPersonAnimationKeyFrameBuilder shrinkLongitudinally(Part part, float startScale, float endScale, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((playerPart, attackTick) -> {
                float manipulatedTick = easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.shrinkLongitudinally(playerPart, part, manipulatedTick, startScale, endScale, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public void accept(PlayerModel playerModel, float attackTick) {
            this.frameChannels.forEach(frame -> frame.accept(playerModel, attackTick));
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 11/26/2026
     */
    public static final class ThirdPersonAnimationBuilder {
        private final List<ThirdPersonAnimationKeyFrameBuilder> frames;
        private HeldItemAnimationBuilder itemAnimation = null;

        private ThirdPersonAnimationBuilder() {
            this.frames = new ArrayList<>();
        }

        public ThirdPersonAnimationBuilder withItem(@NonNull HeldItemAnimationBuilder itemAnimation) {
            this.itemAnimation = Objects.requireNonNull(itemAnimation);
            return this;
        }

        public void animateItem(PoseStack poseStack, float attackTick, HumanoidArm arm) {
            if (this.itemAnimation == null) return;
            this.itemAnimation.animate(poseStack, attackTick, arm);
        }

        public ThirdPersonAnimationBuilder addKeyFrame(ThirdPersonAnimationKeyFrameBuilder keyFrameBuilder) {
            this.frames.add(keyFrameBuilder);
            return this;
        }

        public void animate(PlayerModel model, float attackTick) {
            Map<Part, PartPose> originalPoses = Maps.newEnumMap(Part.class);
            Map<Part, ModelPart> modelParts = Maps.newEnumMap(Part.class);
            Objects.requireNonNull(model);
            for (Part part : Part.values()) {
                if (part == Part.ROOT || isPartClothing(part)) continue;

                ModelPart modelPart = part.get(model);
                if (modelPart == null) {
                    throw new IllegalStateException("Missing ModelPart for virtual hierarchy part: " + part);
                }

                modelParts.put(part, modelPart);
                originalPoses.put(part, PartPose.capture(modelPart));
            }

            for (int frame = this.frames.size() - 1; frame >= 0; frame--) {
                this.frames.get(frame).accept(model, attackTick);
            }

            Map<Part, PartPose> animatedPoses = Maps.newEnumMap(Part.class);
            for (Part part : Part.values()) {
                if (part == Part.ROOT || isPartClothing(part)) continue;

                animatedPoses.put(part, PartPose.capture(part.get(model)));
            }
            applyVirtualHierarchy(modelParts, originalPoses, animatedPoses);
        }
    }

    private static boolean isPartClothing(Part part) {
        return part == Part.LEFT_SLEEVE
                || part == Part.RIGHT_SLEEVE
                || part == Part.LEFT_PANTS
                || part == Part.RIGHT_PANTS;
    }

    private static void applyVirtualHierarchy(
            Map<Part, ModelPart> modelParts,
            Map<Part, PartPose> originalPoses,
            Map<Part, PartPose> animatedPoses
    ) {
        Map<Part, Vector3f> finalPositions = Maps.newEnumMap(Part.class);
        Map<Part, Quaternionf> accumulatedRotations = Maps.newEnumMap(Part.class);
        Map<Part, Vector3f> accumulatedScales = Maps.newEnumMap(Part.class);

        for (Part part : Part.values()) {
            if (isPartClothing(part)) continue;
            ModelPart modelPart = modelParts.get(part);
            PartPose original = originalPoses.get(part);
            PartPose animated = animatedPoses.get(part);

            if (part == Part.ROOT) {
                finalPositions.put(part, new Vector3f());
                accumulatedRotations.put(part, new Quaternionf());
                accumulatedScales.put(part, new Vector3f(1.0F, 1.0F, 1.0F));
                continue;
            }

            Part parent = part.parent();
            Objects.requireNonNull(parent, "Only ROOT may have null parent! Current part: " + part);
            Vector3f parentFinalPosition;
            Vector3f parentOriginalPosition;
            Quaternionf parentAccumulatedRotation;
            Vector3f parentAccumulatedScale;
            if (parent == Part.ROOT) {
                parentFinalPosition = new Vector3f();
                parentOriginalPosition = new Vector3f();
                parentAccumulatedRotation = new Quaternionf();
                parentAccumulatedScale = new Vector3f(1.0F, 1.0F, 1.0F);
            } else {
                parentFinalPosition = finalPositions.get(parent);
                parentOriginalPosition = originalPoses.get(parent).position();
                parentAccumulatedRotation = accumulatedRotations.get(parent);
                parentAccumulatedScale = accumulatedScales.get(parent);
                if (parentFinalPosition == null || parentAccumulatedRotation == null || parentAccumulatedScale == null) {
                    throw new IllegalStateException("Cannot apply virtual hierarchy for part " + part
                            + "because virtual parent has not been evaluated: " + parent + " -> " + part);
                }
            }

            // v' = T * R * S * v
            Vector3f localPosition = original.position()
                    .sub(parentOriginalPosition)
                    .add(animated.x() - original.x(), animated.y() - original.y(), animated.z() - original.z());
            localPosition.mul(parentAccumulatedScale);
            parentAccumulatedRotation.transform(localPosition);
            Vector3f finalPosition = new Vector3f(parentFinalPosition).add(localPosition);

            Quaternionf ownRotationDelta = animated.rotation()
                    .mul(original.rotation().invert());
            Quaternionf finalRotation = new Quaternionf(parentAccumulatedRotation)
                    .mul(animated.rotation());

            Vector3f animatedScale = animated.scale();
            Vector3f originalScale = original.scale();
            Vector3f finalScale = new Vector3f(parentAccumulatedScale)
                    .mul(animatedScale);
            Vector3f ownScaleDelta = new Vector3f(
                    safeScaleRatio(animatedScale.x, originalScale.x),
                    safeScaleRatio(animatedScale.y, originalScale.y),
                    safeScaleRatio(animatedScale.z, originalScale.z)
            );
            Vector3f accumulatedScale = new Vector3f(parentAccumulatedScale)
                    .mul(ownScaleDelta);
            Vector3f euler = finalRotation.getEulerAnglesZYX(new Vector3f());

            modelPart.x = finalPosition.x();
            modelPart.y = finalPosition.y();
            modelPart.z = finalPosition.z();

            modelPart.xRot = euler.x();
            modelPart.yRot = euler.y();
            modelPart.zRot = euler.z();

            modelPart.xScale = finalScale.x();
            modelPart.yScale = finalScale.y();
            modelPart.zScale = finalScale.z();

            finalPositions.put(part, finalPosition);
            accumulatedRotations.put(part, new Quaternionf(parentAccumulatedRotation).mul(ownRotationDelta));
            accumulatedScales.put(part, accumulatedScale);
        }
    }

    private static float safeScaleRatio(float animatedScale, float originalScale) {
        if (Mth.abs(animatedScale - originalScale) < 1E-6F) {
            return 1.0F;
        }
        return animatedScale / originalScale;
    }

    /**
     * @author Mr. GodDavid
     * @since 11/26/2026
     */
    private record PartPose(float x, float y, float z,
                            float xRot, float yRot, float zRot,
                            float xScale, float yScale, float zScale
    ) {

        private static PartPose capture(ModelPart part) {
            return new PartPose(part.x, part.y, part.z, part.xRot, part.yRot, part.zRot, part.xScale, part.yScale, part.zScale);
        }

        private Vector3f position() {
            return new Vector3f(x, y, z);
        }

        private Vector3f scale() {
            return new Vector3f(xScale, yScale, zScale);
        }

        private Quaternionf rotation() {
            // modelPart applies in Z, Y, X rotation order
            return new Quaternionf().rotationZYX(zRot, yRot, xRot);
        }
    }

    public static ThirdPersonAnimationBuilder thirdPersonAnimator() {
        return new ThirdPersonAnimationBuilder();
    }

    public static ThirdPersonAnimationKeyFrameBuilder thirdPersonAnimationKeyFrame() {
        return new ThirdPersonAnimationKeyFrameBuilder();
    }

    // ==================================================================================================================
    //                                        FIRST PERSON ITEM IN HAND ANIMATOR
    // ==================================================================================================================

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

    // ===================================================[TRANSLATION]===================================================
    public static void moveLeft(PoseStack poseStack, float attackTick, HumanoidArm arm, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float translateX = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(-translateX, 0.0f, 0.0f);
    }

    public static void moveRight(PoseStack poseStack, float attackTick, HumanoidArm arm, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float translateX = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(translateX, 0.0f, 0.0f);
    }

    public static void moveUp(PoseStack poseStack, float attackTick, HumanoidArm arm, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float translateY = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(0.0f, translateY, 0.0f);
    }

    public static void moveDown(PoseStack poseStack, float attackTick, HumanoidArm arm, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float translateY = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(0.0f, -translateY, 0.0f);
    }

    public static void moveForward(PoseStack poseStack, float attackTick, HumanoidArm arm, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float translateZ = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(0.0f, 0.0f, -translateZ);
    }

    public static void moveBackward(PoseStack poseStack, float attackTick, HumanoidArm arm, float startPos, float endPos, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float translateZ = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startPos, endPos);
        poseStack.translate(0.0f, 0.0f, translateZ);
    }

    // ====================================================[ROTATION]=====================================================
    public static void rotateXForward(PoseStack poseStack, float attackTick, HumanoidArm arm, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float xRot = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.XN.rotationDegrees(xRot));
    }

    public static void rotateXBackward(PoseStack poseStack, float attackTick, HumanoidArm arm, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float xRot = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
    }

    public static void rotateYLeft(PoseStack poseStack, float attackTick, HumanoidArm arm, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float yRot = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
    }

    public static void rotateYRight(PoseStack poseStack, float attackTick, HumanoidArm arm, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float yRot = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.YN.rotationDegrees(yRot));
    }

    public static void rotateHorizontallyZLeft(PoseStack poseStack, float attackTick, HumanoidArm arm, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float zRot = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.ZP.rotationDegrees(zRot));
    }

    public static void rotateHorizontallyZRight(PoseStack poseStack, float attackTick, HumanoidArm arm, float startDegrees, float endDegrees, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float zRot = mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startDegrees, endDegrees);
        poseStack.mulPose(Axis.ZN.rotationDegrees(zRot));
    }

    // ======================================================[SCALE]======================================================
    public static void expandLeft(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float scaleX = 1.0f + mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(scaleX, 1.0f, 1.0f);
    }

    public static void shrinkRight(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float scaleX = 1.0f - mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(scaleX, 1.0f, 1.0f);
    }

    public static void expandUp(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float scaleY = 1.0f + mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(1.0f, scaleY, 1.0f);
    }

    public static void shrinkDown(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float scaleY = 1.0f - mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(1.0f, scaleY, 1.0f);
    }

    public static void expandLongitudinally(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float scaleZ = 1.0f + mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(1.0f, 1.0f, scaleZ);
    }

    public static void shrinkLongitudinally(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
        validateParamsOrThrow(startFrame, endFrame);
        int mul = MtsDaffiAnimator.invertAnimation(arm);
        float scaleZ = 1.0f - mul * MathUtils.linearInterpolation(attackTick, startFrame, endFrame, startScale, endScale);
        poseStack.scale(1.0f, 1.0f, scaleZ);
    }

    public static void expandInScale(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
//        expandUp(poseStack, attackTick,  arm, startScale, endScale, startFrame, endFrame);
        expandLeft(poseStack, attackTick, arm, startScale, endScale, startFrame, endFrame);
        expandLongitudinally(poseStack, attackTick, arm, startScale, endScale, startFrame, endFrame);
    }

    public static void shrinkInScale(PoseStack poseStack, float attackTick, HumanoidArm arm, float startScale, float endScale, float startFrame, float endFrame) {
//        shrinkDown(poseStack, attackTick,  arm, startScale, endScale, startFrame, endFrame);
        shrinkRight(poseStack, attackTick, arm, startScale, endScale, startFrame, endFrame);
        shrinkLongitudinally(poseStack, attackTick, arm, startScale, endScale, startFrame, endFrame);
    }

    private static void validateParamsOrThrow(float startFrame, float endFrame) {
        if (startFrame < 0.0F || startFrame > 1.0F) {
            ErrorMessageBuilder.infoAndThrow(MtsDaffiAnimator.class, ThrowReason.ENTITY_ANIMATION_UTILS_INVALID_START_FRAME, startFrame);
        } else if (endFrame < 0.0F || endFrame > 1.0F) {
            ErrorMessageBuilder.infoAndThrow(MtsDaffiAnimator.class, ThrowReason.ENTITY_ANIMATION_UTILS_INVALID_END_FRAME, endFrame);
        } else if (startFrame > endFrame) {
            ErrorMessageBuilder.infoAndThrow(MtsDaffiAnimator.class, ThrowReason.ENTITY_ANIMATION_UTILS_START_FRAME_GREATER_THAN_END_FRAME, startFrame, endFrame);
        }
    }

    /**
     * Using easing function if the easing function parameter is not null. Using linear function if the easing function
     * parameter is null.
     *
     * @param attackTick     animation tick.
     * @param startFrame     normalized starting frame.
     * @param endFrame       normalized ending frame.
     * @param easingFunction type of easing function.
     * @return the value that is either thrown into an easing function or not changed.
     */
    private static float easeOrLinear(float attackTick, float startFrame, float endFrame, EasingFunctions.EasingFunction easingFunction) {
        float manipulatedTick = Mth.clamp(attackTick, startFrame, endFrame);
        if (easingFunction == null) {
            return manipulatedTick;
        }
        float progress = MathUtils.linearInterpolation(manipulatedTick, startFrame, endFrame, 0.0F, 1.0F);
        return (float) easingFunction.value(progress);
    }

    public static HeldItemAnimationBuilder heldItemAnimator() {
        return new HeldItemAnimationBuilder();
    }

    public static HeldItemKeyframeBuilder FPInHandAnimKeyFrame() {
        return new HeldItemKeyframeBuilder();
    }

    private MtsDaffiAnimator() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }

    /**
     * @author Mr. GodDavid
     * @since 10/26/2026
     */
    public static final class HeldItemKeyframeBuilder {
        private final List<TriConsumer<PoseStack, Float, HumanoidArm>> frameChannels;

        private HeldItemKeyframeBuilder() {
            this.frameChannels = new ArrayList<>();
        }

        public HeldItemKeyframeBuilder moveLeft(float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.moveLeft(poseStack, manipulatedTick, arm, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder moveRight(float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.moveRight(poseStack, manipulatedTick, arm, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder moveUp(float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.moveUp(poseStack, manipulatedTick, arm, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder moveDown(float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.moveDown(poseStack, manipulatedTick, arm, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder moveForward(float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.moveForward(poseStack, manipulatedTick, arm, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder moveBackward(float startPos, float endPos, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.moveBackward(poseStack, manipulatedTick, arm, startPos, endPos, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder rotateXBackward(float startDegrees, float endDegrees, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.rotateXBackward(poseStack, manipulatedTick, arm, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder rotateXForward(float startDegrees, float endDegrees, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.rotateXForward(poseStack, manipulatedTick, arm, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder rotateYRight(float startDegrees, float endDegrees, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.rotateYRight(poseStack, manipulatedTick, arm, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder rotateYLeft(float startDegrees, float endDegrees, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.rotateYLeft(poseStack, manipulatedTick, arm, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder rotateHorizontallyZRight(float startDegrees, float endDegrees, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.rotateHorizontallyZRight(poseStack, manipulatedTick, arm, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder rotateHorizontallyZLeft(float startDegrees, float endDegrees, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.rotateHorizontallyZLeft(poseStack, manipulatedTick, arm, startDegrees, endDegrees, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder expandLeft(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.expandLeft(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder shrinkRight(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.shrinkRight(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder expandUp(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.expandUp(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder shrinkDown(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.shrinkDown(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder expandLongitudinally(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.expandLongitudinally(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder shrinkLongitudinally(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.shrinkLongitudinally(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder expandInScale(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.expandInScale(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public HeldItemKeyframeBuilder shrinkInScale(float startSize, float endSize, int startFrame, int endFrame, EasingFunctions.EasingFunction ease) {
            float normalizedStartFrame = MtsDaffiAnimator.normalizeFrames(startFrame);
            float normalizedEndFrame = MtsDaffiAnimator.normalizeFrames(endFrame);
            this.frameChannels.add((poseStack, attackTick, arm) -> {
                float manipulatedTick = MtsDaffiAnimator.easeOrLinear(attackTick, normalizedStartFrame, normalizedEndFrame, ease);
                MtsDaffiAnimator.shrinkInScale(poseStack, manipulatedTick, arm, startSize, endSize, normalizedStartFrame, normalizedEndFrame);
            });
            return this;
        }

        public void accept(PoseStack poseStack, float attackTick, HumanoidArm arm) {
            this.frameChannels.forEach(frameChannel -> frameChannel.accept(poseStack, attackTick, arm));
        }
    }

    private static float normalizeFrames(int frame) {
        return frame / 24.0F;
    }


    /**
     * @author Mr. GodDavid
     * @since 10/24/2026
     */
    public static final class HeldItemAnimationBuilder {
        private final List<HeldItemKeyframeBuilder> frames;

        private HeldItemAnimationBuilder() {
            this.frames = new ArrayList<>();
        }

        public HeldItemAnimationBuilder addKeyFrame(HeldItemKeyframeBuilder keyFrameBuilder) {
            this.frames.add(keyFrameBuilder);
            return this;
        }

        public void animate(PoseStack poseStack, float attackTick, HumanoidArm arm) {
            for (int i = this.frames.size() - 1; i >= 0; i--) {
                HeldItemKeyframeBuilder frame = this.frames.get(i);
                frame.accept(poseStack, attackTick, arm);
            }
        }
    }
}
