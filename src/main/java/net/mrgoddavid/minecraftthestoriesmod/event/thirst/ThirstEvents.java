package net.mrgoddavid.minecraftthestoriesmod.event.thirst;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.mrgoddavid.minecraftthestoriesmod.networking.contents.ThirstNetworking;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.ThirstManager;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class ThirstEvents {

    private ThirstEvents() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }

    public static void register() {
        MtsLogger.info("Thirst Events");

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
}
