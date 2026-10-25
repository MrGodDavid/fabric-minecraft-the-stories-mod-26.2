package net.mrgoddavid.minecraftthestoriesmod.utils.log;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

/**
 * @author Mr. GodDavid
 * @since 10/17/2026
 */
public final class ErrorMessageBuilder {

    @SuppressWarnings("DuplicateBranchesInSwitch")
    public static void infoAndThrow(Class<?> clazz, ThrowReason reason, float... numericalArgs) {
        try {
            String msg;
            switch (reason) {
                case INSTANTIATE_UTIL_CLASS -> {
                    msg = buildAttemptingInstantiateUtilityClassErrMsg(clazz);
                    MtsLogger.error(msg);
                    throw new IllegalAccessException(msg);
                }
                case SWITCH_CASE_OUT_OF_SCOPE -> {
                    msg = buildSwitchCaseOutOfScopeErrMsg(clazz);
                    MtsLogger.error(msg);
                    throw new IllegalAccessException(msg);
                }
                case BAD_TIMER_PASS_METHOD_ARGUMENT_CONDITION -> {
                    msg = buildBadTimerPassMethodArgumentConditionErrMsg(clazz, numericalArgs[0]);
                    MtsLogger.error(msg);
                    throw new IllegalArgumentException(msg);
                }
                case ENTITY_ANIMATION_UTILS_INVALID_START_FRAME -> {
                    msg = buildEntityAnimationUtilsInvalidStartFrameErrMsg(clazz, numericalArgs[0]);
                    MtsLogger.error(msg);
                    throw new IllegalArgumentException(msg);
                }
                case ENTITY_ANIMATION_UTILS_INVALID_END_FRAME -> {
                    msg = buildEntityAnimationUtilsInvalidEndFrameErrMsg(clazz, numericalArgs[0]);
                    MtsLogger.error(msg);
                    throw new IllegalArgumentException(msg);
                }
                case ENTITY_ANIMATION_UTILS_START_FRAME_GREATER_THAN_END_FRAME -> {
                    validArrayWithRequireLengthOrThrow(clazz, numericalArgs, 2);
                    msg = buildEntityAnimationUtilsStartFrameGreaterThanEndFrameErrMsg(clazz, numericalArgs[0], numericalArgs[1]);
                    MtsLogger.error(msg);
                    throw new IllegalArgumentException(msg);
                }
                default -> {
                    msg = buildSwitchCaseOutOfScopeErrMsg(clazz);
                    MtsLogger.error(msg);
                    throw new IllegalAccessException(msg);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void validArrayWithRequireLengthOrThrow(Class<?> clazz, float[] numericalArgs, int requiredLength) {
        String msg;
        if (numericalArgs.length < requiredLength) {
            msg = buildArrayLengthFailedRequirementErrMsg(clazz, requiredLength, numericalArgs);
            MtsLogger.error(msg);
            throw new ArrayIndexOutOfBoundsException(msg);
        }
    }

    @SuppressWarnings("DuplicateBranchesInSwitch")
    public static void infoAndThrowAtLivingEntity(LivingEntityRenderState clazz, ThrowReason reason) {
        try {
            String msg;
            switch (reason) {
                case INSTANTIATE_UTIL_CLASS -> {
                    msg = buildAttemptingInstantiateUtilityClassErrMsg(ClassNameFormatter.extractEntityNameFromEntityRenderState(clazz));
                    MtsLogger.error(msg);
                    throw new IllegalAccessException(msg);
                }
                case SWITCH_CASE_OUT_OF_SCOPE -> {
                    msg = buildSwitchCaseOutOfScopeErrMsg(ClassNameFormatter.extractEntityNameFromEntityRenderState(clazz));
                    MtsLogger.error(msg);
                    throw new IllegalAccessException(msg);
                }
                default -> {
                    msg = buildSwitchCaseOutOfScopeErrMsg(ClassNameFormatter.extractEntityNameFromEntityRenderState(clazz));
                    MtsLogger.error(msg);
                    throw new IllegalAccessException(msg);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String buildArrayLengthFailedRequirementErrMsg(Class<?> clazz, int requiredLength, float... numericalArgs) {
        return "[ERROR] in class: " + ClassNameFormatter.format(clazz) + " since current argument array length is: " + numericalArgs.length
                + " , which is less than the required argument array length of: " + requiredLength;
    }

    private static String buildEntityAnimationUtilsStartFrameGreaterThanEndFrameErrMsg(Class<?> clazz, float startFrame, float endFrame) {
        return "[ERROR] in class: " + ClassNameFormatter.format(clazz) + "Invalid parameters of \"start frame\": "
                + startFrame + " and \"end frame\": " + endFrame + " since [START_FRAME] must be LESS THAN [END_FRAME]!";
    }

    private static String buildEntityAnimationUtilsInvalidEndFrameErrMsg(Class<?> clazz, float numericalArgs) {
        return "[ERROR] Invalid parameter of \"end frame\" when using " + ClassNameFormatter.format(clazz)
                + " since \"end frame\": " + numericalArgs + " is out of bound [0.0f, 1.0f]!";
    }

    private static String buildEntityAnimationUtilsInvalidStartFrameErrMsg(Class<?> clazz, float numericalArgs) {
        return "[ERROR] Invalid parameter of \"start frame\" when using " + ClassNameFormatter.format(clazz)
                + " since \"start frame\": " + numericalArgs + " is out of bound [0.0f, 1.0f]!";
    }

    private static String buildBadTimerPassMethodArgumentConditionErrMsg(Class<?> clazz, float numericalArgs) {
        return "[ERROR]: Bad " + ClassNameFormatter.format(clazz) + " initialization: current maxTick: ["
                + numericalArgs + "] is less than or greater than 0";
    }

    private static String buildSwitchCaseOutOfScopeErrMsg(Class<?> clazz) {
        return buildSwitchCaseOutOfScopeErrMsg(clazz.getSimpleName());
    }

    private static String buildSwitchCaseOutOfScopeErrMsg(String className) {
        return "[ERROR]: Switch case out of scope in class " + ClassNameFormatter.format(className);
    }

    private static String buildAttemptingInstantiateUtilityClassErrMsg(Class<?> clazz) {
        return buildAttemptingInstantiateUtilityClassErrMsg(clazz.getSimpleName());
    }

    private static String buildAttemptingInstantiateUtilityClassErrMsg(String className) {
        return "[ERROR]: You cannot instantiate " + ClassNameFormatter.format(className) + " because it is an utility class!";
    }

    private ErrorMessageBuilder() {
        infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
