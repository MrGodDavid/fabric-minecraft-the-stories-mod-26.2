package net.mrgoddavid.minecraftthestoriesmod.registries;

import dev.architectury.event.events.common.PlayerEvent;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.MtsAdvancementTriggers;
import net.mrgoddavid.minecraftthestoriesmod.datagen.MtsDamageTypes;
import net.mrgoddavid.minecraftthestoriesmod.effect.MtsEffects;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie.OakieEntity;
import net.mrgoddavid.minecraftthestoriesmod.event.content.PlayerFluidEvents;
import net.mrgoddavid.minecraftthestoriesmod.event.content.StructureEnterCallback;
import net.mrgoddavid.minecraftthestoriesmod.event.content.thirst.ThirstHolder;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.networking.contents.ThirstNetworking;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.ThirstManager;
import net.mrgoddavid.minecraftthestoriesmod.utils.app.dave.PeriodicPulse;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.client.EntityUtils;

/**
 * @author Mr. GodDavid
 * @since 10/9/2026
 */
@SuppressWarnings("SpellCheckingInspection")
public final class MtsServerTickEventRegistries {

    private static final PeriodicPulse ONE_SECOND_20_TICKS_PERIODIC_PULSE = PeriodicPulse.builder().maxTick(20).build();

    public static void register() {
        MtsLogger.info("Custom End Server Tick Events");

        ServerTickEvents.START_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                ThirstManager thirstManager = ((ThirstHolder) player).mts$getThirstManager();
                if (thirstManager.tickThirstDamageCoolDown()) {
                    ServerLevel level = player.level();
                    player.hurtServer(level, MtsDamageTypes.create(level, MtsDamageTypes.THIRST), 1.0F);
                    thirstManager.resetThirstDamageCoolDown();
                }
            }
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            ONE_SECOND_20_TICKS_PERIODIC_PULSE.tick();

            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                registerPlayerThirstEvents(player);

                // Checks advancement triggers of player while playing.
                checkPlayerAdvancementTriggers(player);
                checkOakieAdvancements(server, player);

                // Grants Public Enemy effect to player if the player wears full Amethyst armor.
                grantsPublicEnemyEffectIfWearingFullAmethystArmor(player);
            }
        });

        // todo
        ServerEntityEvents.EQUIPMENT_CHANGE.register((entity, equipmentSlot, previousStack, currentStack) -> {
            if (entity instanceof ServerPlayer player && equipmentSlot.isArmor()) {
                MtsAdvancementTriggers.WEAR_FULL_AMETHYST_ARMOR_TRIGGER.trigger(player);
            }
        });

        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
            if (blocked || damageTaken <= 0.0F) return;

            if (source.getEntity() instanceof ServerPlayer player) {
                MtsAdvancementTriggers.HIT_ENEMY_WITH_CUDGEL_TRIGGER.trigger(player, entity);

                if (entity instanceof AbstractWoodlinEntity woodlinVictim) {
                    MtsAdvancementTriggers.ATTACKED_SPECIFIC_MOB_TRIGGER.trigger(player, woodlinVictim, AbstractWoodlinEntity.class);
                }
            }
        });

        PlayerEvent.PLAYER_QUIT.register((player) -> {
            if (player instanceof ServerPlayer serverPlayer) {
                MtsAdvancementTriggers.BATHING_IN_ENRICHER_WASTE_TRIGGER.removePlayer(serverPlayer);
            }
        });

        StructureEnterCallback.EVENT.register((player, structure) -> {
            if (structure.is(BuiltinStructures.VILLAGE_PLAINS)) {
                MtsAdvancementTriggers.ENTER_VILLAGE_WITH_FULL_EMERALD_ARMOR_TRIGGER.trigger(player);
            }
        });
    }

    private static void registerPlayerThirstEvents(ServerPlayer player) {
        ThirstManager thirstManager = ((ThirstHolder) player).mts$getThirstManager();
        if (thirstManager.tick(player)) {
            ThirstNetworking.sync(player, thirstManager);
        }
    }

    private static void checkOakieAdvancements(MinecraftServer server, ServerPlayer player) {
        EntityUtils.getNearestLivingEntity(server.overworld(), player, OakieEntity.class, 5.0F).ifPresent(oakieEntity -> {
            MtsAdvancementTriggers.BATHING_IN_ENRICHER_WASTE_WHILE_BEING_ENCHANTED_BY_OAKIE_TRIGGER.trigger(player, oakieEntity);
        });
    }

    private static void checkPlayerAdvancementTriggers(ServerPlayer player) {
        MtsAdvancementTriggers.BATHING_IN_ENRICHER_WASTE_TRIGGER.trigger(player);
        MtsAdvancementTriggers.HAS_PUBLIC_ENEMY_EFFECT_TRIGGER.trigger(player);
    }

    private static void grantsPublicEnemyEffectIfWearingFullAmethystArmor(ServerPlayer player) {
        if (ONE_SECOND_20_TICKS_PERIODIC_PULSE.fire(tick -> tick >= ONE_SECOND_20_TICKS_PERIODIC_PULSE.getMaxTick())) {
            ONE_SECOND_20_TICKS_PERIODIC_PULSE.reset();
            if (hasFullAmethystArmor(player)) {
                player.addEffect(new MobEffectInstance(MtsEffects.PUBLIC_ENEMY, 40, 0, true, true, true));
            }
        }
    }

    private static boolean hasFullAmethystArmor(ServerPlayer player) {
        return player.getItemBySlot(EquipmentSlot.HEAD).is(MtsItems.STRONG_AMETHYST_HELMET)
                && (player.getItemBySlot(EquipmentSlot.CHEST).is(MtsItems.STRONG_AMETHYST_CHESTPLATE) || player.getItemBySlot(EquipmentSlot.CHEST).is(Items.ELYTRA))
                && player.getItemBySlot(EquipmentSlot.LEGS).is(MtsItems.STRONG_AMETHYST_LEGGINGS)
                && player.getItemBySlot(EquipmentSlot.FEET).is(MtsItems.STRONG_AMETHYST_BOOTS);
    }

    private MtsServerTickEventRegistries() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
