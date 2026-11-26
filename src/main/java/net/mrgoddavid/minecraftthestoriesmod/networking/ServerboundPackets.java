package net.mrgoddavid.minecraftthestoriesmod.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.player.Player;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.DodgeRollManager;
import net.mrgoddavid.minecraftthestoriesmod.networking.payload.c2s.DodgeRollPayloadC2S;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class ServerboundPackets {

    public static void handleDodgeRollPayload(DodgeRollPayloadC2S payload, ServerPlayNetworking.Context context) {
        DodgeRollManager.tryRoll(context.player());
    }

    private ServerboundPackets() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
