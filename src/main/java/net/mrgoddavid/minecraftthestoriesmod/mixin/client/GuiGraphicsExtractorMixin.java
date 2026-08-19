package net.mrgoddavid.minecraftthestoriesmod.mixin.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(AbstractContainerScreen.class)
public abstract class GuiGraphicsExtractorMixin {

    private static final Identifier COMMON_WEAPONS_TOOPTIP_STYLE = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, "common_weapons_tooltip");

    @Redirect(
            method = "extractTooltip",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;get(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;"
            )
    )

    private Object minecraftthestoriesmod$changeTooltipStyle(
            ItemStack item, DataComponentType dataComponentType
    ) {
        if (dataComponentType == DataComponents.TOOLTIP_STYLE
                && item.is(MtsTags.Items.MTS_COMMON_WEAPONS)) {

            return COMMON_WEAPONS_TOOPTIP_STYLE;
        }

        return item.get(dataComponentType);
    }
}
