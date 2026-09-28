package net.mrgoddavid.minecraftthestoriesmod.networking.payload.s2c;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;

/**
 * @author Mr. GodDavid
 * @since 9/25/2026
 */
public record TargetDummyDamageNumberS2C(int entityId, float damage) implements CustomPacketPayload {

    public static final Type<TargetDummyDamageNumberS2C> TYPE = new Type<>(Constants.modId("target_dummy_damage_number_payload_s2c"));
    public static final StreamCodec<RegistryFriendlyByteBuf, TargetDummyDamageNumberS2C> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, TargetDummyDamageNumberS2C::entityId,
            ByteBufCodecs.FLOAT, TargetDummyDamageNumberS2C::damage,
            TargetDummyDamageNumberS2C::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
