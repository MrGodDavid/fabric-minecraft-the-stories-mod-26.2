package net.mrgoddavid.minecraftthestoriesmod.combat.content.enricher;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;
import net.mrgoddavid.minecraftthestoriesmod.block.screen.MtsScreenTextures;
import net.mrgoddavid.minecraftthestoriesmod.combat.MtsREICommon;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 9/22/2026
 */
public class EnricherCategory implements DisplayCategory<Display> {

    @Override
    public CategoryIdentifier<? extends Display> getCategoryIdentifier() {
        return MtsREICommon.ENRICHER;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.minecraft-the-stories-mod.enricher");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(MtsBlocks.ENRICHER.asItem().getDefaultInstance());
    }

    @Override
    public List<Widget> setupDisplay(Display display, Rectangle bounds) {
        List<Widget> widgets = new LinkedList<>();
        Point startPoint = new Point(bounds.getCenterX() - 88, bounds.getCenterY() - 83);
        widgets.add(Widgets.createTexturedWidget(MtsScreenTextures.ENRICHER_GUI, new Rectangle(startPoint.x, startPoint.y, 176, 166)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 53, startPoint.y + 10)).entries(display.getInputEntries().getFirst()).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 53, startPoint.y + 46)).entries(display.getInputEntries().get(1)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 109, startPoint.y + 29)).entries(display.getOutputEntries().getFirst()).markOutput());
        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
