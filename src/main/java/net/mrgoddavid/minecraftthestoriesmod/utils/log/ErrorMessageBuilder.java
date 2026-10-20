package net.mrgoddavid.minecraftthestoriesmod.utils.log;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

/**
 * @author Mr. GodDavid
 * @since 10/17/2026
 */
public final class ErrorMessageBuilder {

    @SuppressWarnings("DuplicateBranchesInSwitch")
    public static void infoAndThrow(Class<?> clazz, ThrowReason reason, int... numericalArgs) {
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
                    msg = buildBadTimerPassMethodArgumentConditionErrMsg(clazz, numericalArgs);
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

    private static String buildBadTimerPassMethodArgumentConditionErrMsg(Class<?> clazz, int... numericalArgs) {
        return "[ERROR]: Bad " + ClassNameFormatter.format(clazz) + " initialization: current maxTick: [" + numericalArgs[0] + "] is less than or greater than 0";
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
