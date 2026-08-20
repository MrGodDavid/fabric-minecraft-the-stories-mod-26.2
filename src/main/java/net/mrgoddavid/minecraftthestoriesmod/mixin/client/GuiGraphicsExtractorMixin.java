package net.mrgoddavid.minecraftthestoriesmod.mixin.client;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.mrgoddavid.minecraftthestoriesmod.client.CWTooltipComponent;
import net.mrgoddavid.minecraftthestoriesmod.client.ClientIconTextTooltipComponent;
import net.mrgoddavid.minecraftthestoriesmod.client.ClientItemHeaderTooltipComponent;
import net.mrgoddavid.minecraftthestoriesmod.tooltip.MtsItemTooltips;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(GuiGraphicsExtractor.class)
public class GuiGraphicsExtractorMixin {

    @Inject(
            method = "setTooltipForNextFrame(Lnet/minecraft/client/gui/Font;Ljava/util/List;Ljava/util/Optional;IILnet/minecraft/resources/Identifier;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void modifyTooltipComponents(
            Font font, List<Component> texts, Optional<TooltipComponent> optionalImage, int xo, int yo, @Nullable Identifier style, CallbackInfo ci
    ) {

        /*
         * We only modify tooltips that contain our
         * custom CWTooltipComponent.
         */
        if (optionalImage.isEmpty()) {
            return;
        }

        if (!(optionalImage.get() instanceof CWTooltipComponent cw)) {
            return;
        }

        if (texts.isEmpty()) {
            return;
        }

//        System.out.println("CUSTOM TOOLTIP INTERCEPTED!");

        /*
         * Convert the tooltip text into ClientTooltipComponents.
         */
        List<ClientTooltipComponent> components = new ArrayList<>();

        /*
         * The first line is normally the item name.
         *
         * Instead of adding the item image as a separate
         * tooltip component, combine the image and name
         * into ONE component.
         */
        components.add(ClientIconTextTooltipComponent.icon(
                MtsItemTooltips.EMERALD_AXE_3D_ICON,
                Component.literal("Common Weapon").withStyle(ChatFormatting.GRAY).getVisualOrderText()
        ));

        /*
         * Add the remaining vanilla tooltip lines.
         */
        for (int i = 1; i < texts.size(); i++) {
            components.add(ClientTooltipComponent.create(texts.get(i).getVisualOrderText()));
        }

        /*
         * Call the private Minecraft method through our
         * Mixin accessor.
         */
        ((GuiGraphicsExtractorAccessor) (Object) this)
                .invokeSetTooltipForNextFrameInternal(
                        font,
                        components,
                        xo,
                        yo,
                        net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner.INSTANCE,
                        style,
                        false
                );

        /*
         * Prevent vanilla from continuing and creating
         * the original separate image component.
         */
        ci.cancel();
    }
}
