package net.mrgoddavid.minecraftthestoriesmod.tooltip;

import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.jspecify.annotations.Nullable;

import java.util.Optional;


/**
 * Helper class of the style of items in MTS mod.
 *
 * @author Mr. GodDavid
 * @since 8/20/2026
 */
public abstract class MtsItemToolStyleHelper {

    public static FormattedCharSequence boldUnderlinedText(@Nullable String text, int color) {
        return underlineTextInner(Optional.ofNullable(text).orElse("NULL TEXT"), color);
    }

    private static FormattedCharSequence underlineTextInner(String text, int color) {
        return Component.literal(text).withStyle((style -> style.withColor(color).withUnderlined(true))).getVisualOrderText();
    }

    public static void register() {
    }
}
