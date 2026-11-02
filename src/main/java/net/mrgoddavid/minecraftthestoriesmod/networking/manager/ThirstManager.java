package net.mrgoddavid.minecraftthestoriesmod.networking.manager;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Timer;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class ThirstManager {

    public static final int MAX_HYDRATION = 20;
    public static final float MAX_EXHAUSTION = 4.0F;

    private final Timer thirstDamageCoolDown = new Timer(60);
    private int hydrationPoint = 20;
    private float exhaustion = 0.0f;

    public void copyFrom(ThirstManager old) {
        this.hydrationPoint = old.hydrationPoint;
        this.exhaustion = old.exhaustion;
    }

    public boolean tickThirstDamageCoolDown() {
        return thirstDamageCoolDown.pass(() -> this.getHydrationPoint() < 6.0f);
    }

    public void resetThirstDamageCoolDown() {
        this.thirstDamageCoolDown.reset();
    }

    public boolean tick(ServerPlayer player) {
        int oldThirst = this.getHydrationPoint();
        float exhaustAmount = 0.0F;

        if (player.isSwimming()) {
            exhaustAmount = 0.03f;
        } else if (player.isSprinting()) {
            exhaustAmount = 0.02f;
        } else if (player.getDeltaMovement().horizontalDistanceSqr() > 0.0f) {
            exhaustAmount = 0.01f;
        } else if (player.isJumping()) {
            exhaustAmount = 0.015f;
        }

        this.addExhaustion(exhaustAmount);
        return this.hydrationPoint != oldThirst;
    }

    public boolean restoreThirst(int hydrationRestoration, float exhaustion) {
        if (this.hydrationPoint >= MAX_HYDRATION) return false;

        int oldThirst = this.getHydrationPoint();
        this.hydrationPoint = Mth.clamp(this.hydrationPoint + hydrationRestoration, 0, MAX_HYDRATION);
        this.exhaustion = 0.0F;
        if (exhaustion < 0.0F) {
            this.exhaustion = Mth.clamp(this.exhaustion + exhaustion, 0.0F, MAX_EXHAUSTION);
        }
        return this.hydrationPoint != oldThirst;
    }

    public boolean addExhaustion(float amount) {
        if (amount <= 0 || this.hydrationPoint <= 0) return false;

        int oldThirst = this.hydrationPoint;
        this.exhaustion += amount;
        while (this.exhaustion >= MAX_EXHAUSTION && this.hydrationPoint > 0) {
            this.exhaustion -= MAX_EXHAUSTION;
            this.hydrationPoint--;
        }
        return this.hydrationPoint != oldThirst;
    }

    public int getHydrationPoint() {
        return hydrationPoint;
    }

    public void setHydrationPoint(int hydrationPoint) {
        this.hydrationPoint = Mth.clamp(hydrationPoint, 0, MAX_HYDRATION);
    }

    public float getExhaustion() {
        return exhaustion;
    }

    public void setExhaustion(float exhaustion) {
        this.exhaustion = Math.max(0.0F, exhaustion);
    }

    public boolean isHydrationFull() {
        return this.hydrationPoint >= MAX_HYDRATION;
    }
}
