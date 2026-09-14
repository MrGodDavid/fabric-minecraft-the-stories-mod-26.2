package net.mrgoddavid.minecraftthestoriesmod.utils;

import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 9/7/2026
 */
public final class Constants {

    /**
     * Creates an identifier that combines mod id as namespace and path.
     * <pre>{@code minecraft-the-stories-mod:path}</pre>
     *
     * @param path of the thing in mod.
     * @return the identifier of the path.
     */
    public static Identifier modId(@NonNull final String path) {
        return Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, path);
    }

    /**
     * Creates an identifier with Minecraft's default namespace and path.
     * <pre>{@code minecraft:path}</pre>
     *
     * @param path of the thing in mod.
     * @return the identifier of the thing with Minecraft's default namespace.
     */
    public static Identifier defaultId(@NonNull final String path) {
        return Identifier.withDefaultNamespace(path);
    }

    /**
     * @author Mr. GodDavid
     * @since 9/13/2026
     */
    public static final class Universal {

        public static final int NEW_BORN = 0;

        private Universal() throws IllegalAccessException {
            throw new IllegalAccessException("You cannot instantiate this class!");
        }
    }

    private Constants() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
