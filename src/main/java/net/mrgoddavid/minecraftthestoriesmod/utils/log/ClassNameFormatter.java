package net.mrgoddavid.minecraftthestoriesmod.utils.log;

import java.lang.reflect.Type;

/**
 * @author Mr. GodDavid
 * @since 9/23/2026
 */
public final class ClassNameFormatter {

    public static String format(Class<?> clazz) {
        return "[" + clazz.getSimpleName() + "]";
    }

    public static String format(Type clazz) {
        return "[" + clazz.getTypeName() + "]";
    }

    private ClassNameFormatter() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate " + format(ClassNameFormatter.class) + "!");
    }
}
