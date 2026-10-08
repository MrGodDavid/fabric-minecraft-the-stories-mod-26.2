package net.mrgoddavid.minecraftthestoriesmod.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.utils.list.MtsElementSets;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 9/7/2026
 */
public final class Constants {

    public static void initialize() {
        MtsLogger.init("Constants");
        Universal.initialize();
    }

    public static String modRecipe(@NonNull final String name) {
        return MinecraftTheStoriesMod.MOD_ID + ":" + name;
    }

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

        public static void initialize() {
            MtsLogger.init("Universal Constants");
        }

        public static final int AGE_NEW_BORN = 0;
        public static final int LINE_LENGTH = 40;
        public static final int MAX_SATURATION = 20;
        @SuppressWarnings("unchecked")
        public static final MtsElementSets.Pair<Double, Double>[] EIGHT_DIR_OFFSET = new MtsElementSets.Pair[]{
                new MtsElementSets.Pair<Double, Double>(0.0d, 1.0d),
                new MtsElementSets.Pair<Double, Double>(0.7071067812d, 0.7071067812d),
                new MtsElementSets.Pair<Double, Double>(1.0d, 0.0d),
                new MtsElementSets.Pair<Double, Double>(0.7071067812d, -0.7071067812d),
                new MtsElementSets.Pair<Double, Double>(0.0d, -1.0d),
                new MtsElementSets.Pair<Double, Double>(-0.7071067812d, -0.7071067812d),
                new MtsElementSets.Pair<Double, Double>(-1.0d, 0.0d),
                new MtsElementSets.Pair<Double, Double>(-0.7071067812d, 0.7071067812d),
        };
        @SuppressWarnings("unchecked")
        public static final MtsElementSets.Pair<Double, Double>[] SIXTEEN_DIR_OFFSET = new MtsElementSets.Pair[]{
                new MtsElementSets.Pair<>(0.0d, 1.0d),
                new MtsElementSets.Pair<>(0.382683432d, 0.923879533d),
                new MtsElementSets.Pair<>(0.707106781d, 0.707106781d),
                new MtsElementSets.Pair<>(0.923879533d, 0.382683432d),
                new MtsElementSets.Pair<>(1.000000000d, 0.000000000d),
                new MtsElementSets.Pair<>(0.923879533d, -0.382683432d),
                new MtsElementSets.Pair<>(0.707106781d, -0.707106781d),
                new MtsElementSets.Pair<>(0.382683432d, -0.923879533d),
                new MtsElementSets.Pair<>(0.0d, -1.0d),
                new MtsElementSets.Pair<>(-0.382683432d, -0.923879533d),
                new MtsElementSets.Pair<>(-0.707106781d, -0.707106781d),
                new MtsElementSets.Pair<>(-0.923879533d, -0.382683432d),
                new MtsElementSets.Pair<>(-1.000000000d, 0.000000000d),
                new MtsElementSets.Pair<>(-0.923879533d, 0.382683432d),
                new MtsElementSets.Pair<>(-0.707106781d, 0.707106781d),
                new MtsElementSets.Pair<>(-0.382683432d, 0.923879533d)
        };

        public static final Component[] SHIFT_DOWN_TOOLTIP_INFORMATION = new Component[]{Component.translatable("tooltip.minecraft-the-stories-mod.shift_down")};

        @Deprecated
        public static Ingredient nullIngredient() {
            return Ingredient.of(Blocks.BARRIER);
        }

        private Universal() throws IllegalAccessException {
            throw new IllegalAccessException("You cannot instantiate this class!");
        }
    }

    private Constants() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!");
    }
}
