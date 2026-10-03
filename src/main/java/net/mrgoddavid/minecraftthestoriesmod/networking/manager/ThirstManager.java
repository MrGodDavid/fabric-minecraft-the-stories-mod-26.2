package net.mrgoddavid.minecraftthestoriesmod.networking.manager;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;

/**
 * @author Mr. GodDavid
 * @since 9/17/2026
 */
public class ThirstManager {

    public static final int MAX_THIRST = 20;
    public static final float MAX_EXHAUSTION = 4.0F;

    private int thirst = 20;
    private float exhaustion = 0.0f;

    public void copyFrom(ThirstManager old) {
        this.thirst = old.thirst;
        this.exhaustion = old.exhaustion;
    }

    public boolean tick(ServerPlayer player) {
        int oldThirst = this.getThirst();
        float exhaustAmount = 0.0F;

        if (player.isSwimming()) {
            exhaustAmount = 0.015f;
        } else if (player.isSprinting()) {
            exhaustAmount = 0.01f;
        } else if (player.getDeltaMovement().horizontalDistanceSqr() > 0.0f) {
            exhaustAmount = 0.005f;
        }

        this.addExhaustion(exhaustAmount);
        return this.thirst != oldThirst;
    }

    public boolean restoreThirst(int amount) {
        if (amount <= 0.0F || this.thirst >= MAX_THIRST) return false;

        int oldThirst = this.getThirst();
        this.thirst = Mth.clamp(this.thirst + amount, 0, MAX_THIRST);
        this.exhaustion = 0.0F;
        return this.thirst != oldThirst;
    }

    public boolean addExhaustion(float amount) {
        if (amount <= 0 || this.thirst <= 0) return false;

        int oldThirst = this.thirst;
        this.exhaustion += amount;
        while (this.exhaustion >= MAX_EXHAUSTION && this.thirst > 0) {
            this.exhaustion -= MAX_EXHAUSTION;
            this.thirst--;
        }
        return this.thirst != oldThirst;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        this.thirst = Mth.clamp(thirst, 0, MAX_THIRST);
    }

    public float getExhaustion() {
        return exhaustion;
    }

    public void setExhaustion(float exhaustion) {
        this.exhaustion = Math.max(0.0F, exhaustion);
    }

    public boolean isThirstFull() {
        return this.thirst >= MAX_THIRST;
    }
}
