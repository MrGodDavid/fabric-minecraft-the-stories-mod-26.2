package net.mrgoddavid.minecraftthestoriesmod.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;

public class ClientItemHeaderTooltipComponent implements ClientTooltipComponent {

    private final ItemStack stack;
    private final FormattedCharSequence name;

    public ClientItemHeaderTooltipComponent(
            ItemStack stack,
            FormattedCharSequence name
    ) {
        this.stack = stack;
        this.name = name;
    }

    @Override
    public int getHeight(Font font) {
        return 16;
    }

    @Override
    public int getWidth(Font font) {
        return 16 + 4 + font.width(name);
    }

    @Override
    public void extractImage(Font font, int x, int y, int w, int h, GuiGraphicsExtractor graphics) {
        graphics.item(stack, x, y, 0);
    }

    @Override
    public void extractText(GuiGraphicsExtractor graphics, Font font, int x, int y) {
        // I forgot that the int is in RGBA format instead of RGB format.
        // I put the color int as 0xffffff and the text went transparent.
        // wasting 4 hours and frustrating for nothing...
        // I am questioning my life and the meaning of my existence.
        // I am going to go insane now.
        graphics.text(font, name, x + 20, y + 3, 0xffffffff);
    }
}
