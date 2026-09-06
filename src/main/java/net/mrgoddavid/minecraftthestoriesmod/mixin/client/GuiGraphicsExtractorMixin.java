package net.mrgoddavid.minecraftthestoriesmod.mixin.client;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Items;
import net.mrgoddavid.minecraftthestoriesmod.client.tooltip.MtsTooltipComponent;
import net.mrgoddavid.minecraftthestoriesmod.client.tooltip.ClientIconTextTooltipComponent;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.item.content.MtsBowItem;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import net.mrgoddavid.minecraftthestoriesmod.gui.tooltip.MtsItemToolStyleHelper;
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

        if (!(optionalImage.get() instanceof MtsTooltipComponent tc)) {
            return;
        }

        if (texts.isEmpty()) {
            return;
        }

        /*
         * Convert the tooltip text into ClientTooltipComponents.
         */
        List<ClientTooltipComponent> components = new ArrayList<>();

        String stackName = tc.item().getHoverName().getString();
        if (tc.item().is(MtsTags.Items.MTS_COMMON_WEAPONS)) {
            components.add(ClientIconTextTooltipComponent.item(tc.item(), Component.literal(stackName).withStyle(ChatFormatting.GRAY).getVisualOrderText()));
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.boldUnderlinedText("Common Weapon", 0xA5A5A5), 8));

        } else if (tc.item().is(MtsTags.Items.MTS_UNCOMMON_WEAPONS)) {
            components.add(ClientIconTextTooltipComponent.item(tc.item(), Component.literal(stackName).withStyle(ChatFormatting.GREEN).getVisualOrderText()));
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.boldUnderlinedText("Uncommon Weapon", 0x25A791), 8));

        } else if (tc.item().is(MtsTags.Items.MTS_RARE_WEAPONS)) {
            components.add(ClientIconTextTooltipComponent.item(tc.item(), Component.literal(stackName).withStyle(ChatFormatting.AQUA).getVisualOrderText()));
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.boldUnderlinedText("Rare Weapon",  0xA5BDFF), 8));

        } else if (tc.item().is(MtsTags.Items.MTS_EPIC_WEAPONS)) {
            components.add(ClientIconTextTooltipComponent.item(tc.item(), Component.literal(stackName).withStyle(ChatFormatting.LIGHT_PURPLE).getVisualOrderText()));
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.boldUnderlinedText("Epic Weapon",  0x9141AC), 8));

        } else if (tc.item().is(MtsTags.Items.MTS_LEGENDARY_WEAPONS)) {
            components.add(ClientIconTextTooltipComponent.item(tc.item(), Component.literal(stackName).withStyle(ChatFormatting.YELLOW).getVisualOrderText()));
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.boldUnderlinedText("Legendary Weapon",  0xEC971E), 8));

        }

        /*
         * Add the remaining vanilla tooltip lines.
         */
        for (int i = 1; i < texts.size(); i++) {
            components.add(ClientTooltipComponent.create(texts.get(i).getVisualOrderText()));
        }

        // Bow tooltips.
        if (tc.item().is(MtsTags.Items.MTS_COMMON_WEAPONS_BOWS) && tc.item().getItem() instanceof MtsBowItem mtsBowItem) {
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.plainTextWithNumber(mtsBowItem.damage() * 3.0F, "Attack Damage", ChatFormatting.DARK_GREEN), 1));
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.plainTextWithNumber(mtsBowItem.chargeDuration() / 20.0F, "Charge", ChatFormatting.DARK_GREEN), 1));
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.plainTextWithNumber(mtsBowItem.uncertainty(), "Uncertainty", ChatFormatting.DARK_GREEN), 1));
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.plainText("", 0xFFFFFFFF), 0)); // empty line.
        }
        // hard-coded bow's tooltip
        else if (tc.item().is(MtsItems.getResourceKey(Items.BOW))) {
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.plainTextWithNumber(6.0F, "Attack Damage", ChatFormatting.DARK_GREEN), 1));
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.plainTextWithNumber(1.0F, "Charge", ChatFormatting.DARK_GREEN), 1));
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.plainTextWithNumber(1.0F, "Uncertainty", ChatFormatting.DARK_GREEN), 1));
            components.add(ClientIconTextTooltipComponent.text(MtsItemToolStyleHelper.plainText("", 0xFFFFFFFF), 0)); // empty line.
        }

        /*
         * Call the private Minecraft method through our
         * Mixin accessor.
         */
        ((GuiGraphicsExtractorAccessor) (Object) this).invokeSetTooltipForNextFrameInternal(font, components, xo, yo, net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner.INSTANCE, style, false);

        /*
         * Prevent vanilla from continuing and creating
         * the original separate image component.
         */
        ci.cancel();
    }
}
