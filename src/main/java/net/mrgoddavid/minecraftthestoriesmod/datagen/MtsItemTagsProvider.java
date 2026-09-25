package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

import static net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks.*;
import static net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks.COMPRESSED_WOOD_PLANKS;
import static net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks.LEMON_TREE_LOG;
import static net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks.LEMON_TREE_WOOD;
import static net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks.STRIPPED_COMPRESSED_WOOD_LOG;
import static net.mrgoddavid.minecraftthestoriesmod.item.MtsItems.*;

/**
 * Provides commands for generating JSON files for mod item tags.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class MtsItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    /**
     * Construct an {@link ItemTagsProvider} tags provider <b>without</b> an associated {@link BlockTagsProvider} tags
     * provider.
     *
     * @param output               The {@link FabricPackOutput} instance
     * @param registryLookupFuture
     */
    public MtsItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
        MinecraftTheStoriesMod.LOGGER.info("Providing data of MTS Item Tags for: " + MinecraftTheStoriesMod.MOD_ID);
    }

    /**
     * Implement this method and then use {@link FabricTagsProvider#builder} to get and register new tag builders.
     *
     * @param registries registries.
     */
    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {

        // if you are confused by which method's functionality, read the javadoc of the method.
        moveAxesToTiers();
        moveSwordsToTiers();
        moveSpearsToTiers();
        moveBowsToTiers();

        tag(MtsTags.Entities.AGRO_BROWN_BEAR_ITEMS)
                .add(MtsItems.getResourceKey(Items.HONEY_BOTTLE))
                .add(MtsItems.getResourceKey(Items.HONEYCOMB))
                .add(MtsItems.getResourceKey(Items.HONEY_BLOCK))
                .add(MtsItems.getResourceKey(Items.HONEYCOMB_BLOCK));
        tag(MtsTags.Entities.BREED_BROWN_BEAR_ITEMS)
                .add(MtsItems.getResourceKey(Items.SALMON))
                .add(MtsItems.getResourceKey(Items.SALMON_BUCKET));

        tag(MtsTags.Recipes.MTS_UNIVERSAL_STICK)
                .add(MtsItems.getResourceKey(Items.STICK))
                .add(MtsItems.getResourceKey(ACACIA_STICK))
                .add(MtsItems.getResourceKey(BIRCH_STICK))
                .add(MtsItems.getResourceKey(CHERRY_STICK))
                .add(MtsItems.getResourceKey(DARK_OAK_STICK))
                .add(MtsItems.getResourceKey(JUNGLE_STICK))
                .add(MtsItems.getResourceKey(MANGROVE_STICK))
                .add(MtsItems.getResourceKey(PALE_OAK_STICK))
                .add(MtsItems.getResourceKey(SPRUCE_STICK));

        tag(MtsTags.Items.MTS_COMMON_WEAPONS)
                .addTag(MtsTags.Items.MTS_COMMON_WEAPONS_AXES)
                .addTag(MtsTags.Items.MTS_COMMON_WEAPONS_BOWS)
                .addTag(MtsTags.Items.MTS_COMMON_WEAPONS_SPEARS)
                .addTag(MtsTags.Items.MTS_COMMON_WEAPONS_SWORDS);
        tag(MtsTags.Items.MTS_UNCOMMON_WEAPONS)
                .addTag(MtsTags.Items.MTS_UNCOMMON_WEAPONS_AXES)
                .addTag(MtsTags.Items.MTS_UNCOMMON_WEAPONS_SWORDS)
                .addTag(MtsTags.Items.MTS_UNCOMMON_WEAPONS_SPEARS);
        tag(MtsTags.Items.MTS_RARE_WEAPONS)
                .addTag(MtsTags.Items.MTS_RARE_WEAPONS_AXES)
//                .addTag(MtsTags.Items.MTS_RARE_WEAPONS_SPEARS)
                .addTag(MtsTags.Items.MTS_RARE_WEAPONS_SWORDS)
                .add(MtsItems.getResourceKey(HAMMER_OF_CRAFTER));

        tag(MtsTags.Items.MTS_EPIC_WEAPONS)
                .add(MtsItems.getResourceKey(BROKEN_IRON_PICKAXE));

        tag(MtsTags.Items.MTS_LEGENDARY_WEAPONS)
                .add(MtsItems.getResourceKey(BROKEN_DIAMOND_PICKAXE));

        tag(ItemTags.HOES)
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_HOE))
                .add(MtsItems.getResourceKey(EMERALD_HOE))
                .add(MtsItems.getResourceKey(STRONG_RUBY_HOE))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_HOE));

        tag(ItemTags.PICKAXES)
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_PICKAXE))
                .add(MtsItems.getResourceKey(EMERALD_PICKAXE))
                .add(MtsItems.getResourceKey(STRONG_RUBY_PICKAXE))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_PICKAXE));

        tag(ItemTags.SHOVELS)
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_SHOVEL))
                .add(MtsItems.getResourceKey(EMERALD_SHOVEL))
                .add(MtsItems.getResourceKey(STRONG_RUBY_SHOVEL))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_SHOVEL));

        tag(ItemTags.HEAD_ARMOR)
                .add(MtsItems.getResourceKey(EMERALD_HELMET))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_HELMET))
                .add(MtsItems.getResourceKey(STRONG_RUBY_HELMET))
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_HELMET));
        tag(ItemTags.CHEST_ARMOR)
                .add(MtsItems.getResourceKey(EMERALD_CHESTPLATE))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_CHESTPLATE))
                .add(MtsItems.getResourceKey(STRONG_RUBY_CHESTPLATE))
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_CHESTPLATE));
        tag(ItemTags.LEG_ARMOR)
                .add(MtsItems.getResourceKey(EMERALD_LEGGINGS))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_LEGGINGS))
                .add(MtsItems.getResourceKey(STRONG_RUBY_LEGGINGS))
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_LEGGINGS));
        tag(ItemTags.FOOT_ARMOR)
                .add(MtsItems.getResourceKey(EMERALD_BOOTS))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_BOOTS))
                .add(MtsItems.getResourceKey(STRONG_RUBY_BOOTS))
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_BOOTS));

        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(MtsItems.getResourceKey(HAMMER_OF_CRAFTER));

        tag(MtsTags.Items.COMPRESSED_WOOD_LOGS)
                .add(MtsItems.getResourceKey(COMPRESSED_WOOD.asItem()))
                .add(MtsItems.getResourceKey(COMPRESSED_WOOD_LOG.asItem()))
                .add(MtsItems.getResourceKey(STRIPPED_COMPRESSED_WOOD.asItem()))
                .add(MtsItems.getResourceKey(STRIPPED_COMPRESSED_WOOD_LOG.asItem()))
                .add(MtsItems.getResourceKey(COMPRESSED_WOOD_PLANKS.asItem()));
        tag(MtsTags.Items.LEMON_TREE_LOGS)
                .add(MtsItems.getResourceKey(LEMON_TREE_WOOD.asItem()))
                .add(MtsItems.getResourceKey(LEMON_TREE_LOG.asItem()))
                .add(MtsItems.getResourceKey(LEMON_TREE_WOOD.asItem()))
                .add(MtsItems.getResourceKey(LEMON_TREE_LOG.asItem()));

        this.addToVanillaItemTag$LOGS_THAT_BURN();
        this.addToVanillaItemTag$PLANKS();
        this.addToVanillaItemTag$LEAVES();
    }

    private void addToVanillaItemTag$LEAVES() {
        tag(ItemTags.LEAVES)
                .add(MtsItems.getResourceKey(LEMON_TREE_LEAVES.asItem()));
    }

    private void addToVanillaItemTag$PLANKS() {
        tag(ItemTags.PLANKS)
                .add(MtsItems.getResourceKey(COMPRESSED_WOOD_PLANKS.asItem()))
                .add(MtsItems.getResourceKey(LEMON_TREE_PLANKS.asItem()));
    }

    private void addToVanillaItemTag$LOGS_THAT_BURN() {
        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(MtsTags.Items.COMPRESSED_WOOD_LOGS)
                .addTag(MtsTags.Items.LEMON_TREE_LOGS);
    }

    /**
     * Removes Bow from tag {@code BOW_ENCHANTABLE}.
     * <p>Adds Acacia Bow, Birch Bow, Cherry Bow, Dark Oak Bow, Jungle Bow, Mangrove Bow, (Oak) Bow, Pale Oak Bow, and
     * Spruce Bow to tag {@code MTS_COMMON_WEAPONS_BOWS}.</p>
     * <p>Adds tag {@code MTS_COMMON_WEAPONS_BOWS} to tag {@code BOW_ENCHANTABLE}.</p>
     */
    private void moveBowsToTiers() {
        tag(MtsTags.Items.MTS_COMMON_WEAPONS_BOWS)
                .add(MtsItems.getResourceKey(Items.BOW))
                .add(MtsItems.getResourceKey(ACACIA_BOW))
                .add(MtsItems.getResourceKey(BIRCH_BOW))
                .add(MtsItems.getResourceKey(CHERRY_BOW))
                .add(MtsItems.getResourceKey(DARK_OAK_BOW))
                .add(MtsItems.getResourceKey(JUNGLE_BOW))
                .add(MtsItems.getResourceKey(MANGROVE_BOW))
                .add(MtsItems.getResourceKey(PALE_OAK_BOW))
                .add(MtsItems.getResourceKey(SPRUCE_BOW));
        tag(ItemTags.BOW_ENCHANTABLE)
                .addTag(MtsTags.Items.MTS_COMMON_WEAPONS_BOWS);
    }

    /**
     * Moves Wooden Spear, Stone Spear, Iron Spear, Golden Spear, Diamond Spear, and Copper Spear from {@code SPEARS} to
     * {@code MTS_COMMON_WEAPONS_SPEARS}. Then adds {@code MTS_COMMON_WEAPONS_SPEARS} to {@code SPEARS}.
     * <p>Moves Netherite Spear from {@code SPEARS} to {@code MTS_UNCOMMON_WEAPONS_SPEARS}</p>
     */
    private void moveSpearsToTiers() {
        tag(MtsTags.Items.MTS_COMMON_WEAPONS_SPEARS)
                .add(MtsItems.getResourceKey(Items.WOODEN_SPEAR))
                .add(MtsItems.getResourceKey(Items.STONE_SPEAR))
                .add(MtsItems.getResourceKey(Items.IRON_SPEAR))
                .add(MtsItems.getResourceKey(Items.GOLDEN_SPEAR))
                .add(MtsItems.getResourceKey(Items.DIAMOND_SPEAR))
                .add(MtsItems.getResourceKey(Items.COPPER_SPEAR));

        tag(MtsTags.Items.MTS_UNCOMMON_WEAPONS_SPEARS)
                .add(MtsItems.getResourceKey(Items.NETHERITE_SPEAR))
                .add(MtsItems.getResourceKey(EMERALD_SPEAR))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_SPEAR))
                .add(MtsItems.getResourceKey(STRONG_RUBY_SPEAR))
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_SPEAR));

        tag(MtsTags.Items.MTS_RARE_WEAPONS_SPEARS)
                .add(MtsItems.getResourceKey(STRONG_IRON_ZEN_STAFF));

        tag(ItemTags.SPEARS)
                .addTag(MtsTags.Items.MTS_COMMON_WEAPONS_SPEARS)
                .addTag(MtsTags.Items.MTS_UNCOMMON_WEAPONS_SPEARS)
                .addTag(MtsTags.Items.MTS_RARE_WEAPONS_SPEARS);
    }

    /**
     * Moves Wooden Sword, Stone Sword, Iron Sword, Golden Sword, Diamond Sword, and Copper Sword from {@code SWORDS} to
     * {@code MTS_COMMON_WEAPONS_SWORD}. Then adds {@code MTS_COMMON_WEAPONS_SWORD} to {@code SWORDS}.
     * <p>Moves Netherite Sword from {@code SWORDS} to {@code MTS_UNCOMMON_WEAPONS_SWORDS}</p>
     * <p>Adds {@code DIAMOND_BATTLE_AXE} to {@code MTS_UNCOMMON_WEAPONS_SWORDS}.</p>
     */
    private void moveSwordsToTiers() {
        tag(MtsTags.Items.MTS_COMMON_WEAPONS_SWORDS)
                .add(MtsItems.getResourceKey(Items.WOODEN_SWORD))
                .add(MtsItems.getResourceKey(Items.STONE_SWORD))
                .add(MtsItems.getResourceKey(Items.IRON_SWORD))
                .add(MtsItems.getResourceKey(Items.GOLDEN_SWORD))
                .add(MtsItems.getResourceKey(Items.DIAMOND_SWORD))
                .add(MtsItems.getResourceKey(Items.COPPER_SWORD));

        tag(MtsTags.Items.MTS_UNCOMMON_WEAPONS_SWORDS)
                .add(MtsItems.getResourceKey(Items.NETHERITE_SWORD))
                .add(MtsItems.getResourceKey(EMERALD_SWORD))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_SWORD))
                .add(MtsItems.getResourceKey(STRONG_RUBY_SWORD))
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_SWORD));

        tag(MtsTags.Items.MTS_RARE_WEAPONS_SWORDS)
                .add(MtsItems.getResourceKey(STRONG_IRON_LONG_KNIFE))
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_LONG_KNIFE))
                .add(MtsItems.getResourceKey(STRONG_IRON_DOUBLE_BLADE));

        tag(ItemTags.SWORDS)
                .addTag(MtsTags.Items.MTS_COMMON_WEAPONS_SWORDS)
                .addTag(MtsTags.Items.MTS_UNCOMMON_WEAPONS_SWORDS)
                .addTag(MtsTags.Items.MTS_RARE_WEAPONS_SWORDS);
    }

    /**
     * Removes Wooden Axe, Stone Axe, Iron Axe, Golden Axe, Diamond Axe, and Copper Axe from Minecraft's {@code AXES}
     * tag. These axes are added into {@code MTS_COMMON_WEAPONS_AXES}, and {@code MTS_COMMON_WEAPONS_AXES} is added into
     * Minecraft's {@code AXES} tag.
     * <p>Moves Netherite Axe from {@code AXES} to {@code MTS_UNCOMMON_WEAPONS_AXES}</p>
     * <p>Adds Diamond Battle Axe to Uncommon Weapon Axes</p>
     */
    private void moveAxesToTiers() {
        tag(MtsTags.Items.MTS_COMMON_WEAPONS_AXES)
                .add(MtsItems.getResourceKey(Items.WOODEN_AXE))
                .add(MtsItems.getResourceKey(Items.STONE_AXE))
                .add(MtsItems.getResourceKey(Items.IRON_AXE))
                .add(MtsItems.getResourceKey(Items.GOLDEN_AXE))
                .add(MtsItems.getResourceKey(Items.DIAMOND_AXE))
                .add(MtsItems.getResourceKey(Items.COPPER_AXE))
                .add(MtsItems.getResourceKey(ACACIA_AXE))
                .add(MtsItems.getResourceKey(BIRCH_AXE))
                .add(MtsItems.getResourceKey(CHERRY_AXE))
                .add(MtsItems.getResourceKey(DARK_OAK_AXE))
                .add(MtsItems.getResourceKey(JUNGLE_AXE))
                .add(MtsItems.getResourceKey(MANGROVE_AXE))
                .add(MtsItems.getResourceKey(PALE_OAK_AXE))
                .add(MtsItems.getResourceKey(SPRUCE_AXE));
        tag(MtsTags.Items.MTS_UNCOMMON_WEAPONS_AXES)
                .add(MtsItems.getResourceKey(Items.NETHERITE_AXE))
                .add(MtsItems.getResourceKey(EMERALD_AXE))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_AXE))
                .add(MtsItems.getResourceKey(STRONG_RUBY_AXE))
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_AXE));
        tag(MtsTags.Items.MTS_RARE_WEAPONS_AXES)
                .add(MtsItems.getResourceKey(STRONG_DIAMOND_BATTLE_AXE))
                .add(MtsItems.getResourceKey(STRONG_DIAMOND_VILLAGER_SOLIDER_AXE));

        tag(ItemTags.AXES)
                .addTag(MtsTags.Items.MTS_COMMON_WEAPONS_AXES)
                .addTag(MtsTags.Items.MTS_UNCOMMON_WEAPONS_AXES)
                .addTag(MtsTags.Items.MTS_RARE_WEAPONS_AXES);

    }
}
