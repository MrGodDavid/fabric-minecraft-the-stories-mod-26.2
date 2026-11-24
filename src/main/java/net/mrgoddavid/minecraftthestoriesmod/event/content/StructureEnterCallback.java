package net.mrgoddavid.minecraftthestoriesmod.event.content;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.Structure;

/**
 * @author Mr. GodDavid
 * @since 11/23/2026
 */
public interface StructureEnterCallback {

    Event<StructureEnterCallback> EVENT = EventFactory.createArrayBacked(StructureEnterCallback.class,
            (listeners) -> (player, structure) -> {
                for (StructureEnterCallback event : listeners) {
                    event.onStructureEnter(player, structure);
                }
            });

    void onStructureEnter(ServerPlayer player, Holder<Structure> structure);
}
