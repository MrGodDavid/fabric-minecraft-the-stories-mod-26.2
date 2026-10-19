package net.mrgoddavid.minecraftthestoriesmod.utils.log;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;

/**
 * @author Mr. GodDavid
 * @since 10/17/2026
 */
public final class ErrorMessageBuilder {

    @SuppressWarnings("DuplicateBranchesInSwitch")
    public static void infoAndThrow(Class<?> clazz, ThrowReason reason) throws IllegalAccessException {
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
            default -> {
                msg = buildSwitchCaseOutOfScopeErrMsg(clazz);
                MtsLogger.error(msg);
                throw new IllegalAccessException(msg);
            }
        }
    }

    @SuppressWarnings("DuplicateBranchesInSwitch")
    public static void infoAndThrowAtLivingEntity(LivingEntityRenderState clazz, ThrowReason reason) throws IllegalAccessException {
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

    private ErrorMessageBuilder() throws IllegalAccessException {
        infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
