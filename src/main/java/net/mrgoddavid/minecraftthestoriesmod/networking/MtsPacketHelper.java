package net.mrgoddavid.minecraftthestoriesmod.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.target_dummy.TargetDummyEntity;
import net.mrgoddavid.minecraftthestoriesmod.networking.payload.s2c.TargetDummyDamageNumberS2C;

/**
 * @author Mr. GodDavid
 * @since 9/26/2026
 */
public final class MtsPacketHelper {

    public static void sendTargetDummyDamageNumber(ServerLevel server, TargetDummyEntity targetDummy, float damage) {
        TargetDummyDamageNumberS2C payload = new TargetDummyDamageNumberS2C(targetDummy.getId(),  damage);
        double distance = 32.0D * 32.0D;
        for (ServerPlayer player : server.players()) {
            if (player.distanceToSqr(targetDummy.position()) <= distance) {
                ServerPlayNetworking.send(player, payload);
            }
        }
    }

    private MtsPacketHelper() {
    }
}
