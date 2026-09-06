package net.mrgoddavid.minecraftthestoriesmod.client.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record MtsTooltipComponent(ItemStack item) implements TooltipComponent {
}
