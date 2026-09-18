package net.mrgoddavid.minecraftthestoriesmod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.mrgoddavid.minecraftthestoriesmod.networking.packet.s2c.ThirstPayloadS2C;
import net.mrgoddavid.minecraftthestoriesmod.utils.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class MtsPackets {

    // These payloads are sent from Server to Client (S2C) ---> CLIENTBOUND
    private static void registerClientbound(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry) {
        registry.register(ThirstPayloadS2C.TYPE, ThirstPayloadS2C.STREAM_CODEC);

        ClientPlayNetworking.registerGlobalReceiver(ThirstPayloadS2C.TYPE, ClientboundPackets::handleThirstPayload);
    }

        // These payloads are sent from Client to Server (C2S) ---> SERVERBOUND
    private static void registerServerbound(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry) {
    }

    public static void register() {
        MtsLogger.info("Custom Packets");

        registerClientbound(PayloadTypeRegistry.clientboundPlay());
        registerServerbound(PayloadTypeRegistry.serverboundPlay());
    }
}
