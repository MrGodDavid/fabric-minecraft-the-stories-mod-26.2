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
public final class ModTags {

    /**
     * Mod tags for mod blocks.
     *
     * @author Mr. GodDavid
     * @since 8/14/2026
     */
    public static class Blocks {

        public static final TagKey<Block> NEEDS_EMERALD_TOOL = createTag("needs_emerald_tool");

        /**
         * If you are the first looking at this, it's a bit of headache to understand of it works. Basically, instead of
         * registering all the correct blocks that the emerald tool can mine, we tell Minecraft that "hey, these blocks
         * cannot be mined by emerald tools". Minecraft is happy because there are just few blocks that it needs to pay
         * attention while player is mining, and we are happy because we don't need to add thousands of lines of code
         * to register the correct blocks.
         */
        public static final TagKey<Block> INCORRECT_FOR_EMERALD_TOOL = createTag("incorrect_for_emerald_tool");

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

        public static final TagKey<Item> EMERALD_REPAIR = createTag("emerald_repair");

        private static TagKey<Item> createTag(String name) {
            Identifier identifier = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name);
            return TagKey.create(Registries.ITEM, identifier);
        }
    }
}
