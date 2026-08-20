package net.mrgoddavid.minecraftthestoriesmod.client;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class ClientIconTextTooltipComponent implements ClientTooltipComponent {

    private final @Nullable ItemStack item;
    private final @Nullable Identifier icon;
    private final @Nullable FormattedCharSequence text;

    private int iconSize;
    private int iconTextGap;

    public ClientIconTextTooltipComponent(@Nullable ItemStack item, @Nullable Identifier icon, @Nullable FormattedCharSequence text) {
        this(item, icon, text, 16, 4);
    }

    public ClientIconTextTooltipComponent(@Nullable ItemStack item, @Nullable Identifier icon, @Nullable FormattedCharSequence text, int iconSize, int iconTextGap) {
        this.item = item;
        this.icon = icon;
        this.text = text;
        this.iconSize = iconSize;
        this.iconTextGap = iconTextGap;
    }

    @Override
    public int getHeight(Font font) {
        return Math.max(iconSize, font.lineHeight);
    }

    @Override
    public int getWidth(Font font) {
        int iconWidth = 0;
        if (item != null || icon != null) {
            iconWidth = iconSize + iconTextGap;
        }
        return iconWidth + font.width(Optional.ofNullable(text).orElse(FormattedCharSequence.EMPTY));
    }

    @Override
    public void extractText(@NonNull GuiGraphicsExtractor graphics, @NonNull Font font, int x, int y) {
        int textX = x;
        if (item != null || icon != null) {
            textX += iconSize + iconTextGap;
        }
        graphics.text(font, Optional.ofNullable(text).orElse(FormattedCharSequence.EMPTY), textX, y + 3, 0xFFFFFFFF);
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        if (item != null) {
            graphics.item(item, x, y, 0);
        }
        if (icon != null) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, icon, x, y, 0.0F, 0.0F, iconSize, iconSize, 64, 64);
        }
    }

    public static ClientIconTextTooltipComponent icon(Identifier icon, FormattedCharSequence text) {
        return new ClientIconTextTooltipComponent(null, icon, text);
    }

    public static ClientIconTextTooltipComponent item(ItemStack item, FormattedCharSequence text) {
        return new ClientIconTextTooltipComponent(item, null, text);
    }
}
