package net.mrgoddavid.minecraftthestoriesmod.utils.log;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

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

    public static <T extends LivingEntityRenderState> String extractEntityNameFromEntityRenderState(T state) {
        String name = state.getClass().getSimpleName();
        if (name.length() < 11) {
            MtsLogger.warn("Invalid entity name: " + name);
            return "[ERROR]";
        }
        return name.substring(0, name.length() - 11);
    }

    private ClassNameFormatter() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate " + format(ClassNameFormatter.class) + "!");
    }
}
