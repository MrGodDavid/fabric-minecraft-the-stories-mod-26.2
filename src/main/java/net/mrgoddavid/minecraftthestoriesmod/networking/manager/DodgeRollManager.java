package net.mrgoddavid.minecraftthestoriesmod.networking.manager;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.mrgoddavid.minecraftthestoriesmod.networking.payload.s2c.DodgeRollAnimationPayloadS2C;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Timer;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * @author Mr. GodDavid
 * @since 11/26/2026
 */
public class DodgeRollManager {

    private static final double ROLL_SPEED = 1.2D;
    private static final Map<UUID, RollState> ROLLING_PLAYERS = Maps.newHashMap();

    public static boolean tryRoll(ServerPlayer player) {
        if (!canRoll(player)) return false;

        UUID uuid = player.getUUID();
        RollState currentState = ROLLING_PLAYERS.get(uuid);
        if (currentState != null && (currentState.isRolling() || currentState.hasCoolDown())) return false;

        applyRollMovement(player);
        ROLLING_PLAYERS.put(uuid, new RollState());
        broadcastRollAnimation(player);
        return true;
    }

    private static void broadcastRollAnimation(ServerPlayer player) {
        DodgeRollAnimationPayloadS2C payload = new DodgeRollAnimationPayloadS2C(player.getId(), RollState.ROLL_DURATION);
        for (ServerPlayer serverPlayer : PlayerLookup.tracking(player)) {
            ServerPlayNetworking.send(serverPlayer, payload);
        }
        ServerPlayNetworking.send(player, payload);
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(DodgeRollManager::tick);
    }

    private static boolean canRoll(ServerPlayer player) {
        return player.isAlive() && !player.isSpectator() && !player.isPassenger() && !player.isFallFlying() && player.onGround();
    }

    private static void applyRollMovement(ServerPlayer player) {
        float yaw = player.getYRot() * Mth.DEG_TO_RAD;
        Vec3 direction = new Vec3(-Mth.sin(yaw), 0.0D, Mth.cos(yaw));
        Vec3 currentVelocity =  player.getDeltaMovement();
        player.setDeltaMovement(direction.scale(ROLL_SPEED)
                .add(0.0D, currentVelocity.y(), 0.0D));
        player.hurtMarked = true;
    }

    public static void tick(MinecraftServer server) {
        Iterator<Map.Entry<UUID, RollState>> iterator = ROLLING_PLAYERS.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, RollState> entry = iterator.next();
            ServerPlayer player = server.getPlayerList().getPlayer(entry.getKey());
            if (player == null) {
                iterator.remove();
                continue;
            }

            RollState state = entry.getValue();
            state.tick();
            if (state.isExpired()) {
                iterator.remove();
            }
        }
    }

    public static boolean isRolling(ServerPlayer player) {
        RollState state = ROLLING_PLAYERS.get(player.getUUID());
        return state != null && state.isRolling();
    }

    public static int getRemainingCoolDown(ServerPlayer player) {
        RollState state = ROLLING_PLAYERS.get(player.getUUID());
        return state == null ? 0 : state.coolDownTick();
    }

    /**
     * @author Mr. GodDavid
     * @since 11/26/2026
     */
    private static class RollState {

        private static final int ROLL_DURATION = 20;
        private static final int ROLL_COOLDOWN = 60;
        private final Timer durationTimer=  new Timer(ROLL_DURATION);
        private final Timer coolDownTimer = new Timer(ROLL_COOLDOWN);

        private RollState() {
            this.durationTimer.reset();
            this.coolDownTimer.reset();
        }

        private void tick() {
            this.durationTimer.pass(Timer.Criterion.ALWAYS_TRUE);
            this.coolDownTimer.pass(Timer.Criterion.ALWAYS_TRUE);
        }

        private boolean isRolling() {
            return this.durationTimer.maxTick() - this.durationTimer.currentTick() > 0;
        }

        private boolean hasCoolDown() {
            return this.coolDownTimer.maxTick() - this.coolDownTimer.currentTick() > 0;
        }

        private boolean isExpired() {
            return !this.isRolling() && !this.hasCoolDown();
        }

        private int coolDownTick() {
            return this.coolDownTimer.currentTick();
        }
    }

    private DodgeRollManager() {}
}
