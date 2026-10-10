package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.MtsAdvancementTriggers;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.TargetDummyDamageManager;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 10/9/2026
 */
public final class MtsClientTickEventRegistries {

    public static void register() {
        MtsLogger.info("Custom End Client Tick Events");

        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            TargetDummyDamageManager.tick();
        });
    }

    private MtsClientTickEventRegistries() {
    }
}
