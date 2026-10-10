package net.mrgoddavid.minecraftthestoriesmod.client.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * @param item item that is displayed in the item's tooltip.
 * @author Mr. GodDavid
 * @since unknown time in 2026
 */
public record MtsTooltipComponent(ItemStack item) implements TooltipComponent {
}
