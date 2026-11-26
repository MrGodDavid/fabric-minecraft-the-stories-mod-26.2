package net.mrgoddavid.minecraftthestoriesmod.networking.manager;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Timer;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Mr. GodDavid
 * @since 11/26/2026
 */
public class DodgeRollAnimationManager {

    private static final Map<Integer, RollAnimationState> ROLLING_PLAYERS = Maps.newHashMap();

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> tick());
    }

    public static void startRoll(int entityId, int duration) {
        if (duration <= 0) return;

        ROLLING_PLAYERS.put(entityId, new RollAnimationState(duration));

    }

    public static boolean isRolling(int entityId) {
        return ROLLING_PLAYERS.containsKey(entityId);
    }

    public static float getProgress(int entityId, float partialTicks) {
        RollAnimationState state = ROLLING_PLAYERS.get(entityId);
        if (state == null) return 0.0F;
        return Math.min(1.0F, (state.elapsedTick() + partialTicks) / state.durationTick);
    }

    private static void tick() {
        Iterator<Map.Entry<Integer, RollAnimationState>> iterator = ROLLING_PLAYERS.entrySet().iterator();
        while (iterator.hasNext()) {
            RollAnimationState state = iterator.next().getValue();
            state.tick();
            if (state.reachedMax()) {
                iterator.remove();
            }
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 11/26/2026
     */
    private static class RollAnimationState {

        private final int durationTick;
        private final Timer elapsedTimer;

        private RollAnimationState(int durationTick) {
            this.durationTick = durationTick;
            this.elapsedTimer = new Timer(this.durationTick);
        }

        private void tick() {
            this.elapsedTimer.pass(Timer.Criterion.ALWAYS_TRUE);
        }

        private boolean reachedMax() {
            return this.elapsedTimer.hasReachedMaxTick();
        }

        private float elapsedTick() {
            return this.elapsedTimer.currentTick();
        }
    }
}
