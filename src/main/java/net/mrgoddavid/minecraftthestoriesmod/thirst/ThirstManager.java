package net.mrgoddavid.minecraftthestoriesmod.thirst;

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

        if (player.isSwimming()) {
            this.addExhaustion(0.015f);
        } else if (player.isSprinting()) {
            this.addExhaustion(0.01f);
        } else if (player.getDeltaMovement().horizontalDistanceSqr() > 0.0f) {
            this.addExhaustion(0.005f);
        }
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
}
