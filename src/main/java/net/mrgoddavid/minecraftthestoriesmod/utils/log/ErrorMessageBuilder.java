package net.mrgoddavid.minecraftthestoriesmod.utils.log;

/**
 * @author Mr. GodDavid
 * @since 10/17/2026
 */
public final class ErrorMessageBuilder {

    public static void infoAndThrow(Class<?> clazz, ThrowReason reason) throws IllegalAccessException {
        String msg;
        switch (reason) {
            case INSTANTIATE_UTIL_CLASS -> {
                msg = buildAttemptingInstantiateUtilityClassErrMsg(clazz);
                MtsLogger.error(msg);
                throw new IllegalAccessException(msg);
            }
            default -> {
                msg = switchStatementOutOfCaseErrMsg(clazz);
                MtsLogger.error(msg);
                throw new IllegalAccessException(msg);
            }
        }
    }

    private static String switchStatementOutOfCaseErrMsg(Class<?> clazz) {
        return "[ERROR]: Switch statement out of case in class " + ClassNameFormatter.format(clazz) + "!";
    }

    private static String buildAttemptingInstantiateUtilityClassErrMsg(Class<?> clazz) {
        return "[ERROR]: You cannot instantiate " + ClassNameFormatter.format(clazz) + " because it is an utility class!";
    }

    private ErrorMessageBuilder() throws IllegalAccessException {
        infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
