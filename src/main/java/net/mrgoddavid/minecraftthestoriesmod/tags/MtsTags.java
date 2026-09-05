package net.mrgoddavid.minecraftthestoriesmod.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

/**
 * Custom mod tags for grouping blocks, items, or entities. It just makes our life easier.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public final class MtsTags {

    /**
     * Mod tags for recipes.
     *
     * @author Mr. GodDavid
     * @since 9/4/2026
     */
    public static class Recipes {

        public static final TagKey<Item> MTS_UNIVERSAL_STICK = createTag("mts_universal_stick");

        private static TagKey<Item> createTag(final String name) {
            Identifier identifier = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name);
            return TagKey.create(Registries.ITEM, identifier);
        }
    }

    /**
     * Mod tags for mod fluids.
     *
     * @author Mr. GodDavid
     * @since 9/4/2026
     */
    public static class Fluids {

        public static final TagKey<Fluid> ENRICHER_WASTE = createTag("enricher_waste");
        public static final TagKey<Fluid> BLUE_FUEL = createTag("blue_fuel");

        private static TagKey<Fluid> createTag(final String name) {
            return TagKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name));
        }
    }

    /**
     * Mod tags for mod blocks.
     *
     * @author Mr. GodDavid
     * @since 8/14/2026
     */
    public static class Blocks {

        public static final TagKey<Block> NEEDS_AMETHYST_TOOL = createTag("needs_amethyst_tool");
        public static final TagKey<Block> NEEDS_EMERALD_TOOL = createTag("needs_emerald_tool");
        public static final TagKey<Block> NEEDS_RUBY_TOOL = createTag("needs_ruby_tool");
        public static final TagKey<Block> NEEDS_TOPAZ_TOOL = createTag("needs_topaz_tool");

        /**
         * If you are the first looking at this, it's a bit of headache to understand of it works. Basically, instead of
         * registering all the correct blocks that the emerald tool can mine, we tell Minecraft that "hey, these blocks
         * cannot be mined by emerald tools". Minecraft is happy because there are just few blocks that it needs to pay
         * attention while player is mining, and we are happy because we don't need to add thousands of lines of code
         * to register the correct blocks.
         */
        public static final TagKey<Block> INCORRECT_FOR_AMETHYST_TOOL = createTag("incorrect_for_amethyst_tool");
        public static final TagKey<Block> INCORRECT_FOR_EMERALD_TOOL = createTag("incorrect_for_emerald_tool");
        public static final TagKey<Block> INCORRECT_FOR_RUBY_TOOL = createTag("incorrect_for_ruby_tool");
        public static final TagKey<Block> INCORRECT_FOR_TOPAZ_TOOL = createTag("incorrect_for_topaz_tool");

        private static TagKey<Block> createTag(final String name) {
            Identifier identifier = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name);
            return TagKey.create(Registries.BLOCK, identifier);
        }
    }

    /**
     * Mod tags for mod items.
     *
     * @author Mr. GodDavid
     * @since 8/14/2026
     */
    public static class Items {

        public static final TagKey<Item> MTS_COMMON_WEAPONS_AXES = createTag("mts_common_weapon_axes");
        public static final TagKey<Item> MTS_COMMON_WEAPONS_SWORDS = createTag("mts_common_weapon_swords");
        public static final TagKey<Item> MTS_COMMON_WEAPONS_SPEARS = createTag("mts_common_weapon_spears");
        public static final TagKey<Item> MTS_UNCOMMON_WEAPONS_AXES = createTag("mts_uncommon_axes");
        public static final TagKey<Item> MTS_UNCOMMON_WEAPONS_SWORDS = createTag("mts_uncommon_swords");
        public static final TagKey<Item> MTS_UNCOMMON_WEAPONS_SPEARS = createTag("mts_uncommon_spears");
        public static final TagKey<Item> MTS_RARE_WEAPONS_AXES = createTag("mts_rare_weapons_axes");
        public static final TagKey<Item> MTS_RARE_WEAPONS_SPEARS = createTag("mts_rare_weapons_spears");
        public static final TagKey<Item> MTS_RARE_WEAPONS_SWORDS = createTag("mts_rare_weapons_swords");

        public static final TagKey<Item> MTS_COMMON_WEAPONS = createTag("mts_common_weapons");
        public static final TagKey<Item> MTS_UNCOMMON_WEAPONS = createTag("mts_uncommon_weapons");
        public static final TagKey<Item> MTS_RARE_WEAPONS = createTag("mts_rare_weapons");
        public static final TagKey<Item> MTS_EPIC_WEAPONS = createTag("mts_epic_weapons");
        public static final TagKey<Item> MTS_LEGENDARY_WEAPONS = createTag("mts_legendary_weapons");

        public static final TagKey<Item> AMETHYST_REPAIR = createTag("amethyst_repair");
        public static final TagKey<Item> EMERALD_REPAIR = createTag("emerald_repair");
        public static final TagKey<Item> RUBY_REPAIR = createTag("ruby_repair");
        public static final TagKey<Item> TOPAZ_REPAIR = createTag("topaz_repair");

        private static TagKey<Item> createTag(final String name) {
            Identifier identifier = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name);
            return TagKey.create(Registries.ITEM, identifier);
        }
    }
}
