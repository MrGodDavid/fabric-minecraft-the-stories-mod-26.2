package net.mrgoddavid.minecraftthestoriesmod.effect.content;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.mrgoddavid.minecraftthestoriesmod.event.thirst.ThirstHolder;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.ThirstManager;

/**
 * @author Mr. GodDavid
 * @since 11/11/2026
 */
@SuppressWarnings("NullableProblems")
public class EnergeticEffect extends MobEffect {

    public EnergeticEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        if (mob instanceof Player player) {
            this.restoreHungerAndHydration(player);
        }
        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    private void restoreHungerAndHydration(Player player) {
        player.getFoodData().setFoodLevel(20);
        ThirstManager thirstManager = ((ThirstHolder) player).mts$getThirstManager();
        if (thirstManager != null) {
            thirstManager.restoreHydration();
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }
}
