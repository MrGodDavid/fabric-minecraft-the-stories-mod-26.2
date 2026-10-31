package net.mrgoddavid.minecraftthestoriesmod.utils.mod.common;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.mrgoddavid.minecraftthestoriesmod.component.MtsDataComponentTypes;
import net.mrgoddavid.minecraftthestoriesmod.event.thirst.ThirstHolder;
import net.mrgoddavid.minecraftthestoriesmod.networking.contents.ThirstNetworking;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.ThirstManager;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 10/30/2026
 */
public final class ThirstUtils {

    public static void restoreThirst(LivingEntity entity, ItemStack itemStack) {
        if (!(entity instanceof ServerPlayer player)) return;
        int thirstRestoration = itemStack.getOrDefault(MtsDataComponentTypes.THIRST_RESTORATION, 0);
        if (thirstRestoration <= 0.0F) return;
        ThirstManager thirstManager = ((ThirstHolder) player).mts$getThirstManager();
        if (thirstManager != null && thirstManager.restoreThirst(thirstRestoration)) {
            ThirstNetworking.sync(player, thirstManager);
        }
    }

    private ThirstUtils() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
