package net.mrgoddavid.minecraftthestoriesmod.networking.payload.c2s;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;

/**
 * @author Mr. GodDavid
 * @since 11/26/2026
 */
public record DodgeRollPayloadC2S() implements CustomPacketPayload {

    public static final Type<DodgeRollPayloadC2S> TYPE = new Type<>(Constants.modId("dodge_roll"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DodgeRollPayloadC2S> STREAM_CODEC = StreamCodec.unit(new DodgeRollPayloadC2S());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
