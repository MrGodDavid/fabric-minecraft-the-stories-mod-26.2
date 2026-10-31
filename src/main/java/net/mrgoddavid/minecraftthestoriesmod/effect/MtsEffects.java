package net.mrgoddavid.minecraftthestoriesmod.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.mrgoddavid.minecraftthestoriesmod.effect.content.LeechOfOakieEffect;
import net.mrgoddavid.minecraftthestoriesmod.effect.content.PublicEnemyEffect;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 10/9/2026
 */
public final class MtsEffects {

    public static final Holder<MobEffect> PUBLIC_ENEMY = registerMobEffect("public_enemy", new PublicEnemyEffect(MobEffectCategory.NEUTRAL, 0x942610));
    public static final Holder<MobEffect> LEECH_OF_OAKIE = registerMobEffect("leech_of_oakie", new LeechOfOakieEffect(MobEffectCategory.NEUTRAL, 0xD8FF14));

    private static Holder<MobEffect> registerMobEffect(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Constants.modId(name), effect);
    }

    public static void register() {
        MtsLogger.info("Custom Mob Effects");
    }

    private MtsEffects() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
