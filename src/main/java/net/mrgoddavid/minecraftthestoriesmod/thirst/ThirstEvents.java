package net.mrgoddavid.minecraftthestoriesmod.thirst;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.mrgoddavid.minecraftthestoriesmod.networking.contents.ThirstNetworking;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class ThirstEvents {

    private ThirstEvents() {
    }

    public static void register() {
        MtsLogger.info("Thirst Events");

        ServerTickEvents.END_SERVER_TICK.register(ThirstEvents::registerThirstEvent);
        ServerPlayConnectionEvents.JOIN.register(ThirstEvents::playerJoin);
        ServerPlayerEvents.COPY_FROM.register(ThirstEvents::copyFrom);
    }

    private static void playerJoin(ServerGamePacketListenerImpl serverGamePacketListener, PacketSender packetSender, MinecraftServer server) {
        ServerPlayer player = serverGamePacketListener.getPlayer();
        ThirstManager thirstManager = ((ThirstHolder) player).mts$getThirstManager();
        ThirstNetworking.sync(player, thirstManager);
    }

    private static void copyFrom(ServerPlayer oldPlayer, ServerPlayer newPlayer, boolean alive) {
        ThirstManager newThirstManager = ((ThirstHolder) newPlayer).mts$getThirstManager();
        if (alive) {
            ThirstManager oldThirstManager = ((ThirstHolder) oldPlayer).mts$getThirstManager();
            newThirstManager.copyFrom(oldThirstManager);
        }
        ThirstNetworking.sync(newPlayer, newThirstManager);
    }

    private static void registerThirstEvent(MinecraftServer server) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            ThirstManager thirstManager = ((ThirstHolder) player).mts$getThirstManager();
            if (thirstManager.tick(player)) {
                ThirstNetworking.sync(player, thirstManager);
            }
        }
    }
}
