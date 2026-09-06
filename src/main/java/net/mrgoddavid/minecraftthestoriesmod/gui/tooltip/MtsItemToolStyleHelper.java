package net.mrgoddavid.minecraftthestoriesmod.gui.tooltip;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.util.Optional;


/**
 * Helper class of the style of items in MTS mod.
 *
 * @author Mr. GodDavid
 * @since 8/20/2026
 */
public abstract class MtsItemToolStyleHelper {

    public static FormattedCharSequence plainText(@Nullable String text, int color) {
        return plainTextInner(Optional.ofNullable(text).orElse("NULL TEXT"), color);
    }

    public static FormattedCharSequence plainText(@Nullable String text, ChatFormatting color) {
        return plainTextInner(Optional.ofNullable(text).orElse("NULL TEXT"), color);
    }

    public static FormattedCharSequence plainTextWithNumber(float number, @Nullable String text, int color) {
        String nonnullText = numberText(number, Optional.ofNullable(text).orElse("NULL TEXT"));
        return plainTextInner(nonnullText, color);
    }

    public static FormattedCharSequence plainTextWithNumber(float number, @Nullable String text, ChatFormatting color) {
        String nonnullText = numberText(number, Optional.ofNullable(text).orElse("NULL TEXT"));
        return plainTextInner(nonnullText, color);
    }

    private static String numberText(float number, @NonNull String text) {
        return new DecimalFormat("#.#").format(number).concat(" ").concat(text);
    }

    private static FormattedCharSequence plainTextInner(@NonNull String text, int color) {
        return Component.literal(text).withStyle(style -> style.withColor(color)).getVisualOrderText();
    }

    private static FormattedCharSequence plainTextInner(@NonNull String text, ChatFormatting color) {
        return Component.literal(text).withStyle(style -> style.withColor(color)).getVisualOrderText();
    }

    public static FormattedCharSequence boldText(@Nullable String text, int color) {
        return boldTextInner(Optional.ofNullable(text).orElse("NULL TEXT"), color);
    }

    public static FormattedCharSequence boldTextWithNumber(float number, @Nullable String text, int color) {
        String nonnullText = new DecimalFormat("#.#").format(number).concat(" ").concat(Optional.ofNullable(text).orElse("NULL TEXT"));
        return boldTextInner(nonnullText, color);
    }

    public static FormattedCharSequence boldText(@Nullable String text, ChatFormatting color) {
        return boldTextInner(Optional.ofNullable(text).orElse("NULL TEXT"), color);
    }

    public static FormattedCharSequence boldTextWithNumber(float number, @Nullable String text, ChatFormatting color) {
        String nonnullText = new DecimalFormat("#.#").format(number).concat(" ").concat(Optional.ofNullable(text).orElse("NULL TEXT"));
        return boldTextInner(nonnullText, color);
    }

    private static FormattedCharSequence boldTextInner(@NonNull String text, int color) {
        return Component.literal(text).withStyle(style -> style.withColor(color).withBold(true)).getVisualOrderText();
    }

    private static FormattedCharSequence boldTextInner(@NonNull String text, ChatFormatting color) {
        return Component.literal(text).withStyle(style -> style.withColor(color).withBold(true)).getVisualOrderText();
    }

    public static FormattedCharSequence boldUnderlinedText(@Nullable String text, int color) {
        return underlineTextInner(Optional.ofNullable(text).orElse("NULL TEXT"), color);
    }

    public static FormattedCharSequence boldUnderlinedText(@Nullable String text, ChatFormatting color) {
        return underlineTextInner(Optional.ofNullable(text).orElse("NULL TEXT"), color);
    }

    private static FormattedCharSequence underlineTextInner(@NonNull String text, int color) {
        return Component.literal(text).withStyle((style -> style.withColor(color).withUnderlined(true))).getVisualOrderText();
    }

    private static FormattedCharSequence underlineTextInner(@NonNull String text, ChatFormatting color) {
        return Component.literal(text).withStyle((style -> style.withColor(color).withUnderlined(true))).getVisualOrderText();
    }
}
