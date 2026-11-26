package net.mrgoddavid.minecraftthestoriesmod.networking.payload.s2c;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 11/26/2026
 */
public record DodgeRollAnimationPayloadS2C(int entityId, int duration) implements CustomPacketPayload {

    public static final Type<DodgeRollAnimationPayloadS2C> TYPE = new Type<>(Constants.modId("dodge_roll_animation"));
    public static final StreamCodec<RegistryFriendlyByteBuf, DodgeRollAnimationPayloadS2C> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, DodgeRollAnimationPayloadS2C::entityId,
            ByteBufCodecs.VAR_INT, DodgeRollAnimationPayloadS2C::duration,
            DodgeRollAnimationPayloadS2C::new
    );

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
