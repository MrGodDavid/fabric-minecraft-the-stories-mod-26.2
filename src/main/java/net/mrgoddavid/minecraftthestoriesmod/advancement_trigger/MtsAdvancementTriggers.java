package net.mrgoddavid.minecraftthestoriesmod.advancement_trigger;

import net.minecraft.advancements.triggers.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.custom.EnteredVillageWithFullEmeraldArmorTrigger;
import net.mrgoddavid.minecraftthestoriesmod.advancement_trigger.custom.MineBlockWithToolTrigger;

/**
 * Triggers of MTS advancements.
 *
 * @author Mr. GodDavid
 * @since 8/25/2026
 */
public class MtsAdvancementTriggers {

    public static final MineBlockWithToolTrigger MINE_BLOCK_WITH_TOOL_TRIGGER = register("mine_block_with_tool", new MineBlockWithToolTrigger());
    public static final EnteredVillageWithFullEmeraldArmorTrigger ENTER_VILLAGE_WITH_FULL_EMERALD_ARMOR_TRIGGER = register("enter_village_with_full_emerald_armor", new EnteredVillageWithFullEmeraldArmorTrigger());

    private static <T extends CriterionTrigger<?>> T register(final String name, T trigger) {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES, Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name), trigger);
    }

    public static void register(){
        MinecraftTheStoriesMod.LOGGER.info("Registering MtsAdvancementTriggers for: " + MinecraftTheStoriesMod.MOD_ID);
    }
}
