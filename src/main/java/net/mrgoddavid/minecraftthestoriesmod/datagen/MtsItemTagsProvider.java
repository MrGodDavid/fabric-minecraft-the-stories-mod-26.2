package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
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
    }

    /**
     * Implement this method and then use {@link FabricTagsProvider#builder} to get and register new tag builders.
     *
     * @param registries registries.
     */
    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {

        tag(ItemTags.AXES)
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_AXE))
                .add(MtsItems.getResourceKey(EMERALD_AXE))
                .add(MtsItems.getResourceKey(STRONG_RUBY_AXE))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_AXE));

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

        tag(ItemTags.SPEARS)
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_SPEAR))
                .add(MtsItems.getResourceKey(EMERALD_SPEAR))
                .add(MtsItems.getResourceKey(STRONG_RUBY_SPEAR))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_SPEAR));

        tag(ItemTags.SWORDS)
                .add(MtsItems.getResourceKey(STRONG_AMETHYST_SWORD))
                .add(MtsItems.getResourceKey(EMERALD_SWORD))
                .add(MtsItems.getResourceKey(STRONG_RUBY_SWORD))
                .add(MtsItems.getResourceKey(STRONG_TOPAZ_SWORD));
    }
}
