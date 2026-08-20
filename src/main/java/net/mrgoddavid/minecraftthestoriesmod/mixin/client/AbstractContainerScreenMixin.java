package net.mrgoddavid.minecraftthestoriesmod.mixin.client;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.client.MtsTooltipComponent;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

import static net.mrgoddavid.minecraftthestoriesmod.tooltip.MtsItemTooltips.*;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {

    @Redirect(
            method = "extractTooltip",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;get(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;"
            )
    )
    private Object minecraftthestoriesmod$changeTooltipStyle(ItemStack item, DataComponentType dataComponentType) {
        if (dataComponentType == DataComponents.TOOLTIP_STYLE) {
            if (item.is(MtsTags.Items.MTS_COMMON_WEAPONS))
                return COMMON_WEAPONS_TOOLTIP_STYLE;
            if (item.is(MtsTags.Items.MTS_UNCOMMON_WEAPONS))
                return UNCOMMON_WEAPONS_TOOLTIP_STYLE;
            if (item.is(MtsTags.Items.MTS_RARE_WEAPONS))
                return RARE_WEAPONS_TOOLTIP_STYLE;
            if (item.is(MtsTags.Items.MTS_EPIC_WEAPONS))
                return EPIC_WEAPONS_TOOLTIP_STYLE;
            if (item.is(MtsTags.Items.MTS_LEGENDARY_WEAPONS))
                return LEGENDARY_WEAPONS_TOOLTIP_STYLE;
        }
        return item.get(dataComponentType);
    }

    @Redirect(
            method = "extractTooltip",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;getTooltipImage()Ljava/util/Optional;"
            )
    )
    private Optional<TooltipComponent> minecraftthestoriesmod$customTooltipImage(ItemStack item) {
        if (item.is(MtsTags.Items.MTS_COMMON_WEAPONS)
                || item.is(MtsTags.Items.MTS_UNCOMMON_WEAPONS)
                || item.is(MtsTags.Items.MTS_RARE_WEAPONS)
                || item.is(MtsTags.Items.MTS_EPIC_WEAPONS)
                || item.is(MtsTags.Items.MTS_LEGENDARY_WEAPONS)
        ) {
            return Optional.of(new MtsTooltipComponent(item));
        }
        return item.getTooltipImage();
    }
}
