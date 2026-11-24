package net.mrgoddavid.minecraftthestoriesmod.effect.content;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.mrgoddavid.minecraftthestoriesmod.event.content.thirst.ThirstHolder;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.ThirstManager;
import net.mrgoddavid.minecraftthestoriesmod.utils.math.MathUtils;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Timer;

import java.util.ArrayList;

/**
 * @author Mr. GodDavid
 * @since 11/11/2026
 */
@SuppressWarnings("NullableProblems")
public class EnergeticEffect extends MobEffect {

    private static final int GOLDEN_COLOR = MathUtils.rgbaToArgb(0xFFD505FF);
    private static final int BLUE_COLOR = MathUtils.rgbaToArgb(0x00B0D4FF);
    private static final float REDUCTION_MULTIPLIER = 0.5F;
    private final Timer TICK_TIMER = new Timer(20);

    public EnergeticEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        if (mob instanceof Player player) {
            this.restoreHungerAndHydration(player);
            this.accelerateHarmfulEffects(player);
            this.spawnDualColorParticles(serverLevel, mob);
        }
        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    private void spawnDualColorParticles(ServerLevel serverLevel, LivingEntity mob) {
        ColorParticleOption goldenColor = ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, GOLDEN_COLOR);
        ColorParticleOption blueColor = ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, BLUE_COLOR);
        serverLevel.sendParticles(goldenColor, mob.getX(), mob.getY() + 1.0, mob.getZ(), 1, 0.3, 0.5, 0.3, 0.0);
        serverLevel.sendParticles(blueColor, mob.getX(), mob.getY() + 1.0, mob.getZ(), 1, 0.3, 0.5, 0.3, 0.0);
    }

    private void accelerateHarmfulEffects(Player player) {
        if (player.level().isClientSide()) return;

        if (TICK_TIMER.pass(() -> true)) {
            TICK_TIMER.reset();
            for (MobEffectInstance instance : new ArrayList<>(player.getActiveEffects())) {
                Holder<MobEffect> effectHolder = instance.getEffect();
                MobEffect mobEffect = effectHolder.value();
                if (mobEffect.getCategory() == MobEffectCategory.HARMFUL) {
                    int harmfulDuration = instance.getDuration();
                    int harmfulAmplifier = instance.getAmplifier();
                    int newDuration = Math.round(harmfulDuration * REDUCTION_MULTIPLIER);
                    player.removeEffect(effectHolder);

                    if (newDuration > 1) {
                        MobEffectInstance reducedHarmfulEffectInstance = new MobEffectInstance(effectHolder, newDuration, harmfulAmplifier, false, true, true);
                        player.addEffect(reducedHarmfulEffectInstance);
                    }
                }
            }
        }
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
