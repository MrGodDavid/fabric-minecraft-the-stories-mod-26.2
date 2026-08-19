package net.mrgoddavid.minecraftthestoriesmod.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

/**
 * Custom mod tags for grouping blocks, items, or entities. It just makes our life easier.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public final class MtsTags {

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

        private static TagKey<Block> createTag(String name) {
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

        /**
         * <h3>Current Items</h3>
         * <ul>
         *     <li>Vanilla Wooden Axe</li>
         *     <li>Vanilla Stone Axe</li>
         *     <li>Vanilla Golden Axe</li>
         *     <li>Vanilla Iron Axe</li>
         *     <li>Vanilla Diamond Axe</li>
         *     <li>Vanilla Copper Axe</li>
         * </ul>
         */
        public static final TagKey<Item> MTS_COMMON_WEAPONS_AXES = createTag("mts_common_weapon_axes");

        /**
         * <h3>Current Items</h3>
         * <ul>
         *     <li>Vanilla Wooden Sword</li>
         *     <li>Vanilla Stone Sword</li>
         *     <li>Vanilla Golden Sword</li>
         *     <li>Vanilla Iron Sword</li>
         *     <li>Vanilla Diamond Sword</li>
         *     <li>Vanilla Copper Sword</li>
         * </ul>
         */
        public static final TagKey<Item> MTS_COMMON_WEAPONS_SWORDS = createTag("mts_common_weapon_swords");

        /**
         * <h3>Current Items</h3>
         * <ul>
         *     <li>Vanilla Wooden Spear</li>
         *     <li>Vanilla Stone Spear</li>
         *     <li>Vanilla Golden Spear</li>
         *     <li>Vanilla Iron Spear</li>
         *     <li>Vanilla Diamond Spear</li>
         *     <li>Vanilla Copper Spear</li>
         * </ul>
         */
        public static final TagKey<Item> MTS_COMMON_WEAPONS_SPEARS = createTag("mts_common_weapon_spears");

        /**
         * <h3>Current Items</h3>
         * <ul>
         *     <li>Vanilla Netherite Axe</li>
         * </ul>
         */
        public static final TagKey<Item> MTS_UNCOMMON_WEAPONS_AXES = createTag("mts_uncommon_axes");

        /**
         * <h3>Current Items</h3>
         * <ul>
         *     <li>Vanilla Netherite Sword</li>
         * </ul>
         */
        public static final TagKey<Item> MTS_UNCOMMON_WEAPONS_SWORDS = createTag("mts_uncommon_swords");

        /**
         * <h3>Current Items</h3>
         * <ul>
         *     <li>Vanilla Netherite Spear</li>
         * </ul>
         */
        public static final TagKey<Item> MTS_UNCOMMON_WEAPONS_SPEARS = createTag("mts_uncommon_spears");

        /**
         * <h3>Current Items</h3>
         * <ul>
         *     <li>Tag: MTS Common-Weapon Axes</li>
         *     <li>Tag: MTS Common-Weapon Swords</li>
         *     <li>Tag: MTS Common-Weapon Spears</li>
         * </ul>
         */
        public static final TagKey<Item> MTS_COMMON_WEAPONS = createTag("mts_common_weapons");

        /**
         * <h3>Current Items</h3>
         * <ul>
         *     <li>Tag: MTS Uncommon-Weapon Axes</li>
         *     <li>Tag: MTS Uncommon-Weapon Swords</li>
         *     <li>Tag: MTS Uncommon-Weapon Spears</li>
         * </ul>
         */
        public static final TagKey<Item> MTS_UNCOMMON_WEAPONS = createTag("mts_uncommon_weapons");

        public static final TagKey<Item> AMETHYST_REPAIR = createTag("amethyst_repair");
        public static final TagKey<Item> EMERALD_REPAIR = createTag("emerald_repair");
        public static final TagKey<Item> RUBY_REPAIR = createTag("ruby_repair");
        public static final TagKey<Item> TOPAZ_REPAIR = createTag("topaz_repair");

        private static TagKey<Item> createTag(String name) {
            Identifier identifier = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name);
            return TagKey.create(Registries.ITEM, identifier);
        }
    }
}
