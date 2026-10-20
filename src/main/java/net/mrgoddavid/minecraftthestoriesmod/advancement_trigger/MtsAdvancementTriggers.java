package net.mrgoddavid.minecraftthestoriesmod.advancement_trigger;

import net.minecraft.advancements.triggers.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.content.*;
import net.mrgoddavid.minecraftthestoriesmod.utils.Constants;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

/**
 * Triggers of MTS advancements.
 *
 * @author Mr. GodDavid
 * @since 8/25/2026
 */
public final class MtsAdvancementTriggers {

    public static final MineBlockWithToolTrigger MINE_BLOCK_WITH_TOOL_TRIGGER = register("mine_block_with_tool", new MineBlockWithToolTrigger());
    public static final EnteredVillageWithFullEmeraldArmorTrigger ENTER_VILLAGE_WITH_FULL_EMERALD_ARMOR_TRIGGER = register("enter_village_with_full_emerald_armor", new EnteredVillageWithFullEmeraldArmorTrigger());
    public static final BathingInEnricherWasteTrigger BATHING_IN_ENRICHER_WASTE_TRIGGER = register("bathing_in_enricher_waste", new BathingInEnricherWasteTrigger());
    public static final WearFullAmethystArmorTrigger WEAR_FULL_AMETHYST_ARMOR_TRIGGER = register("wear_full_amethyst_armor", new WearFullAmethystArmorTrigger());
    public static final WearFullTopazArmorTrigger WEAR_FULL_TOPAZ_ARMOR_TRIGGER = register("wear_full_topaz_armor", new WearFullTopazArmorTrigger());
    public static final WearFullRubyArmorTrigger WEAR_FULL_RUBY_ARMOR_TRIGGER = register("wear_full_ruby_armor", new WearFullRubyArmorTrigger());
    public static final HasPublicEnemyEffectTrigger HAS_PUBLIC_ENEMY_EFFECT_TRIGGER = register("has_public_effect", new HasPublicEnemyEffectTrigger());
    public static final HitEnemyWithCudgelTrigger HIT_ENEMY_WITH_CUDGEL_TRIGGER = register("hit_enemy_with_cudgel", new HitEnemyWithCudgelTrigger());

    private static <T extends CriterionTrigger<?>> T register(final String name, T trigger) {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES, Constants.modId(name), trigger);
    }

    public static void register() {
        MtsLogger.info("Custom Advancement Triggers");
    }

    private MtsAdvancementTriggers() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
