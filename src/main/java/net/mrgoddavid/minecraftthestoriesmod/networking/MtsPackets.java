package net.mrgoddavid.minecraftthestoriesmod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.mrgoddavid.minecraftthestoriesmod.networking.payload.c2s.DodgeRollPayloadC2S;
import net.mrgoddavid.minecraftthestoriesmod.networking.payload.s2c.DodgeRollAnimationPayloadS2C;
import net.mrgoddavid.minecraftthestoriesmod.networking.payload.s2c.TargetDummyDamageNumberS2C;
import net.mrgoddavid.minecraftthestoriesmod.networking.payload.s2c.ThirstPayloadS2C;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class MtsPackets {

    // These payloads are sent from Server to Client (S2C) ---> CLIENTBOUND
    private static void registerClientbound(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry) {
        registry.register(ThirstPayloadS2C.TYPE, ThirstPayloadS2C.STREAM_CODEC);
        registry.register(TargetDummyDamageNumberS2C.TYPE, TargetDummyDamageNumberS2C.STREAM_CODEC);
        registry.register(DodgeRollAnimationPayloadS2C.TYPE, DodgeRollAnimationPayloadS2C.STREAM_CODEC);

        ClientPlayNetworking.registerGlobalReceiver(ThirstPayloadS2C.TYPE, ClientboundPackets::handleThirstPayload);
        ClientPlayNetworking.registerGlobalReceiver(TargetDummyDamageNumberS2C.TYPE, ClientboundPackets::handleTargetDummyDamagedPayload);
        ClientPlayNetworking.registerGlobalReceiver(DodgeRollAnimationPayloadS2C.TYPE, ClientboundPackets::handleDodgeRollAnimationPayload);
    }

        // These payloads are sent from Client to Server (C2S) ---> SERVERBOUND
    private static void registerServerbound(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry) {
        registry.register(DodgeRollPayloadC2S.TYPE, DodgeRollPayloadC2S.STREAM_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(DodgeRollPayloadC2S.TYPE, ServerboundPackets::handleDodgeRollPayload);
    }

    public static void register() {
        MtsLogger.info("Custom Packets");

        registerClientbound(PayloadTypeRegistry.clientboundPlay());
        registerServerbound(PayloadTypeRegistry.serverboundPlay());
    }

    private MtsPackets() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
