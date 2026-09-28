package net.mrgoddavid.minecraftthestoriesmod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.entity.Entity;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.TargetDummyDamageManager;
import net.mrgoddavid.minecraftthestoriesmod.networking.payload.s2c.TargetDummyDamageNumberS2C;
import net.mrgoddavid.minecraftthestoriesmod.networking.payload.s2c.ThirstPayloadS2C;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.ThirstClientManager;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class ClientboundPackets {

    public static void handleThirstPayload(ThirstPayloadS2C thirstPayloadS2C, ClientPlayNetworking.Context context) {
        ThirstClientManager.setThirst(thirstPayloadS2C.thirst());
    }

    public static void handleTargetDummyDamagedPayload(TargetDummyDamageNumberS2C payload, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            if (context.client().level == null) return;
            Entity entity = context.client().level.getEntity(payload.entityId());
            if (entity == null) return;
            TargetDummyDamageManager.addDamage(entity, payload.damage());
        });
    }
}
