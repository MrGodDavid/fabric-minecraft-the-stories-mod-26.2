package net.mrgoddavid.minecraftthestoriesmod.component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 10/30/2026
 */
public final class MtsDataComponentTypes {

    public static final DataComponentType<Integer> THIRST_RESTORATION = register("thirst_restoration", Codec.INT, ByteBufCodecs.INT);

    private static <T> DataComponentType<T> register(String name, Codec<T> codec, StreamCodec<ByteBuf, T> streamCodec) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Constants.modId(name),
                DataComponentType.<T>builder().persistent(codec).networkSynchronized(streamCodec).build());
    }

    public static void register() {
        MtsLogger.info("Custom Data Component Types");
    }

    private MtsDataComponentTypes() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
