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
        tag(MtsTags.Items.MTS_COMMON_WEAPONS)
                .addTag(MtsTags.Items.MTS_COMMON_WEAPONS_AXES)
                .addTag(MtsTags.Items.MTS_COMMON_WEAPONS_SWORDS)
                .addTag(MtsTags.Items.MTS_COMMON_WEAPONS_SPEARS);
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
                .add(MtsItems.getResourceKey(STRONG_RUBY_SWORD));

        tag(MtsTags.Items.MTS_LEGENDARY_WEAPONS)
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_SWORD));

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
    }

    /**
     * Moves Wooden Spear, Stone Spear, Iron Spear, Golden Spear, Diamond Spear, and Copper Spear from {@code SPEARS} to
     * {@code MTS_COMMON_WEAPONS_SPEARS}. Then adds {@code MTS_COMMON_WEAPONS_SPEARS} to {@code SPEARS}.
     * <p>Moves Netherite Spear from {@code SPEARS} to {@code MTS_UNCOMMON_WEAPONS_SPEARS}</p>
     */
    private void moveSpearsToTiers() {
        tag(ItemTags.SPEARS)
                .remove(MtsItems.getResourceKey(Items.WOODEN_SPEAR))
                .remove(MtsItems.getResourceKey(Items.STONE_SPEAR))
                .remove(MtsItems.getResourceKey(Items.IRON_SPEAR))
                .remove(MtsItems.getResourceKey(Items.GOLDEN_SPEAR))
                .remove(MtsItems.getResourceKey(Items.DIAMOND_SPEAR))
                .remove(MtsItems.getResourceKey(Items.COPPER_SPEAR))
                .remove(MtsItems.getResourceKey(Items.NETHERITE_SPEAR));

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

        tag(ItemTags.SPEARS)
                .addTag(MtsTags.Items.MTS_COMMON_WEAPONS_SPEARS)
                .addTag(MtsTags.Items.MTS_UNCOMMON_WEAPONS_SPEARS);
//                .addTag(MtsTags.Items.MTS_RARE_WEAPONS_SPEARS);
    }

    /**
     * Moves Wooden Sword, Stone Sword, Iron Sword, Golden Sword, Diamond Sword, and Copper Sword from {@code SWORDS} to
     * {@code MTS_COMMON_WEAPONS_SWORD}. Then adds {@code MTS_COMMON_WEAPONS_SWORD} to {@code SWORDS}.
     * <p>Moves Netherite Sword from {@code SWORDS} to {@code MTS_UNCOMMON_WEAPONS_SWORDS}</p>
     * <p>Adds {@code DIAMOND_BATTLE_AXE} to {@code MTS_UNCOMMON_WEAPONS_SWORDS}.</p>
     */
    private void moveSwordsToTiers() {
        tag(ItemTags.SWORDS)
                .remove(MtsItems.getResourceKey(Items.WOODEN_SWORD))
                .remove(MtsItems.getResourceKey(Items.STONE_SWORD))
                .remove(MtsItems.getResourceKey(Items.IRON_SWORD))
                .remove(MtsItems.getResourceKey(Items.GOLDEN_SWORD))
                .remove(MtsItems.getResourceKey(Items.DIAMOND_SWORD))
                .remove(MtsItems.getResourceKey(Items.COPPER_SWORD))
                .remove(MtsItems.getResourceKey(Items.NETHERITE_SWORD));

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
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_LONG_KNIFE));

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
        tag(ItemTags.AXES)
                .remove(MtsItems.getResourceKey(Items.WOODEN_AXE))
                .remove(MtsItems.getResourceKey(Items.STONE_AXE))
                .remove(MtsItems.getResourceKey(Items.IRON_AXE))
                .remove(MtsItems.getResourceKey(Items.GOLDEN_AXE))
                .remove(MtsItems.getResourceKey(Items.DIAMOND_AXE))
                .remove(MtsItems.getResourceKey(Items.COPPER_AXE))
                .remove(MtsItems.getResourceKey(Items.NETHERITE_AXE));

        tag(MtsTags.Items.MTS_COMMON_WEAPONS_AXES)
                .add(MtsItems.getResourceKey(Items.WOODEN_AXE))
                .add(MtsItems.getResourceKey(Items.STONE_AXE))
                .add(MtsItems.getResourceKey(Items.IRON_AXE))
                .add(MtsItems.getResourceKey(Items.GOLDEN_AXE))
                .add(MtsItems.getResourceKey(Items.DIAMOND_AXE))
                .add(MtsItems.getResourceKey(Items.COPPER_AXE));
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
