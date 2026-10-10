package net.mrgoddavid.minecraftthestoriesmod.registries;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.MtsAdvancementTriggers;
import net.mrgoddavid.minecraftthestoriesmod.effect.MtsEffects;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.utils.TickCounter;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;

/**
 * @author Mr. GodDavid
 * @since 10/9/2026
 */
public final class MtsServerTickEventRegistries {

    private static final TickCounter tickCounter = TickCounter.builder().maxTick(20).build();

    public static void register() {
        MtsLogger.info("Custom End Server Tick Events");

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickCounter.tick();

            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                MtsAdvancementTriggers.ENTER_VILLAGE_WITH_FULL_EMERALD_ARMOR_TRIGGER.trigger(player);

                if (tickCounter.fire(tick -> tick >= tickCounter.getMaxTick())) {
                    tickCounter.reset();
                    if (hasFullAmethystArmor(player)) {
                        player.addEffect(new MobEffectInstance(MtsEffects.PUBLIC_ENEMY, 40, 0, true, false, true));
                    }
                }
            }
        });
    }

    private static boolean hasFullAmethystArmor(ServerPlayer player) {
        return player.getItemBySlot(EquipmentSlot.HEAD).is(MtsItems.STRONG_AMETHYST_HELMET)
                && (player.getItemBySlot(EquipmentSlot.CHEST).is(MtsItems.STRONG_AMETHYST_CHESTPLATE) || player.getItemBySlot(EquipmentSlot.CHEST).is(Items.ELYTRA))
                && player.getItemBySlot(EquipmentSlot.LEGS).is(MtsItems.STRONG_AMETHYST_LEGGINGS)
                && player.getItemBySlot(EquipmentSlot.FEET).is(MtsItems.STRONG_AMETHYST_BOOTS);
    }

    private MtsServerTickEventRegistries() {
    }
}
