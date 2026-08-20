package net.mrgoddavid.minecraftthestoriesmod.client;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public record MtsTooltipComponent(ItemStack item) implements TooltipComponent {
}
