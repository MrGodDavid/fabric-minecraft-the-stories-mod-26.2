package net.mrgoddavid.minecraftthestoriesmod.event.content;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;

/**
 * Not sure that I will use this. Keep it right now.
 *
 * @author Mr. GodDavid
 * @since 11/23/2026
 */
public interface PlayerFluidEvents {

    Event<FluidStateChange> ENTER_FLUID = EventFactory.createArrayBacked(FluidStateChange.class,
            (listeners) -> (player) -> {
                for (FluidStateChange listener : listeners) {
                    listener.onFluidStateChange(player);
                }
            });

    Event<FluidStateChange> LEAVE_FLUID = EventFactory.createArrayBacked(FluidStateChange.class,
            (listeners) -> (player) -> {
                for (FluidStateChange listener : listeners) {
                    listener.onFluidStateChange(player);
                }
            });

    /**
     * @author Mr. GodDavid
     * @since 11/23/2026
     */
    @FunctionalInterface
    interface FluidStateChange {

        void onFluidStateChange(Player player);
    }
}
