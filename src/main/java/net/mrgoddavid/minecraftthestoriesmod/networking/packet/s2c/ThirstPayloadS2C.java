package net.mrgoddavid.minecraftthestoriesmod.networking.packet.s2c;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;

/**
 * TODO: add logics of when to trigger the game sending this packet.
 *
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public record ThirstPayloadS2C(int thirst) implements CustomPacketPayload {

    public static final Type<ThirstPayloadS2C> TYPE = new Type<>(Constants.modId("thirst_payload_s2c"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ThirstPayloadS2C> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, ThirstPayloadS2C::thirst,
            ThirstPayloadS2C::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
