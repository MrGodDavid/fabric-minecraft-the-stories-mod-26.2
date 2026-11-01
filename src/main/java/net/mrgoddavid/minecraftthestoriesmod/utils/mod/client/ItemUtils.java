package net.mrgoddavid.minecraftthestoriesmod.utils.mod.client;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 10/31/2026
 */
public final class ItemUtils {

    public static int obtainTotalNumOf(Player player, Item target) {
        int count = 0;
        for (ItemStack stack : player.getInventory()) {
            if (stack.is(target)) {
                count += stack.getCount();
            }
        }
        return count;
    }

    @SuppressWarnings("unused")
    public static boolean compareAll(ItemStack itemStack, List<TagKey<Item>> tagKeys) {
        boolean result = false;
        for (TagKey<Item> tagKey : tagKeys) {
            result |= itemStack.is(tagKey);
        }
        return result;
    }

    public static int durabilityOf(ItemStack itemStack) {
        return itemStack.getMaxDamage() - itemStack.getDamageValue();
    }

    private ItemUtils() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
