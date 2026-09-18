package net.mrgoddavid.minecraftthestoriesmod.networking.contents;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.mrgoddavid.minecraftthestoriesmod.networking.packet.s2c.ThirstPayloadS2C;
import net.mrgoddavid.minecraftthestoriesmod.thirst.ThirstManager;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class ThirstNetworking {

    private ThirstNetworking() {
    }

    public static void sync(ServerPlayer player, ThirstManager thirstManager) {
        ServerPlayNetworking.send(player, new ThirstPayloadS2C(thirstManager.getThirst()));
    }
}
