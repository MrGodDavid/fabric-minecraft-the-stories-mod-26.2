package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.MtsAdvancementTriggers;
import net.mrgoddavid.minecraftthestoriesmod.effect.MtsEffects;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.utils.app.dave.PeriodicPulse;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * @author Mr. GodDavid
 * @since 10/9/2026
 */
public final class MtsServerTickEventRegistries {

    private static final PeriodicPulse ONE_SECOND_20_TICKS_PERIODIC_PULSE = PeriodicPulse.builder().maxTick(20).build();

    public static void register() {
        MtsLogger.info("Custom End Server Tick Events");

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            ONE_SECOND_20_TICKS_PERIODIC_PULSE.tick();

            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                // Checks advancement triggers of player while playing.
                checkPlayerAdvancementTriggers(player);

                // Grants Public Enemy effect to player if the player wears full Amethyst armor.
                grantsPublicEnemyEffectIfWearingFullAmethystArmor(player);
            }
        });
    }

    private static void checkPlayerAdvancementTriggers(ServerPlayer player) {
        MtsAdvancementTriggers.ENTER_VILLAGE_WITH_FULL_EMERALD_ARMOR_TRIGGER.trigger(player);
        MtsAdvancementTriggers.BATHING_IN_ENRICHER_WASTE_TRIGGER.trigger(player);
        MtsAdvancementTriggers.WEAR_FULL_AMETHYST_ARMOR_TRIGGER.trigger(player);
//                MtsAdvancementTriggers.WEAR_FULL_RUBY_ARMOR_TRIGGER.trigger(player);
//                MtsAdvancementTriggers.WEAR_FULL_TOPAZ_ARMOR_TRIGGER.trigger(player);
        MtsAdvancementTriggers.HAS_PUBLIC_ENEMY_EFFECT_TRIGGER.trigger(player);
    }

    private static void grantsPublicEnemyEffectIfWearingFullAmethystArmor(ServerPlayer player) {
        if (ONE_SECOND_20_TICKS_PERIODIC_PULSE.fire(tick -> tick >= ONE_SECOND_20_TICKS_PERIODIC_PULSE.getMaxTick())) {
            ONE_SECOND_20_TICKS_PERIODIC_PULSE.reset();
            if (hasFullAmethystArmor(player)) {
                player.addEffect(new MobEffectInstance(MtsEffects.PUBLIC_ENEMY, 40, 0, true, false, true));
            }
        }
    }

    private static boolean hasFullAmethystArmor(ServerPlayer player) {
        return player.getItemBySlot(EquipmentSlot.HEAD).is(MtsItems.STRONG_AMETHYST_HELMET)
                && (player.getItemBySlot(EquipmentSlot.CHEST).is(MtsItems.STRONG_AMETHYST_CHESTPLATE) || player.getItemBySlot(EquipmentSlot.CHEST).is(Items.ELYTRA))
                && player.getItemBySlot(EquipmentSlot.LEGS).is(MtsItems.STRONG_AMETHYST_LEGGINGS)
                && player.getItemBySlot(EquipmentSlot.FEET).is(MtsItems.STRONG_AMETHYST_BOOTS);
    }

    private MtsServerTickEventRegistries() throws IllegalAccessException {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
