package net.mrgoddavid.minecraftthestoriesmod.mixin.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(GuiGraphicsExtractor.class)
public interface GuiGraphicsExtractorAccessor {

    @Invoker("setTooltipForNextFrameInternal")
    void invokeSetTooltipForNextFrameInternal(
            Font font,
            List<ClientTooltipComponent> lines,
            int xo,
            int yo,
            ClientTooltipPositioner positioner,
            Identifier style,
            boolean replaceExisting
    );
}
