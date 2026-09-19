package net.mrgoddavid.minecraftthestoriesmod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.mrgoddavid.minecraftthestoriesmod.networking.packet.s2c.ThirstPayloadS2C;
import net.mrgoddavid.minecraftthestoriesmod.thirst.ThirstClientManager;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class ClientboundPackets {

    public static void handleThirstPayload(ThirstPayloadS2C thirstPayloadS2C, ClientPlayNetworking.Context context) {
        // Here we can do whatever we want to handle thirst logic.
        ThirstClientManager.setThirst(thirstPayloadS2C.thirst());
//        System.out.println(
//                "[MTS] Client received thirst: " + thirstPayloadS2C.thirst()
//        );
    }
}
