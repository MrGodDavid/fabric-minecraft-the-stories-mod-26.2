package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.mrgoddavid.minecraftthestoriesmod.item.ModItems;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

import static net.mrgoddavid.minecraftthestoriesmod.item.ModItems.*;

/**
 * Provides commands for generating JSON files for mod item tags.
 *
 * @author Mr. GodDavid
 * @since 8/14/2026
 */
public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    /**
     * Construct an {@link ItemTagsProvider} tags provider <b>without</b> an associated {@link BlockTagsProvider} tags
     * provider.
     *
     * @param output               The {@link FabricPackOutput} instance
     * @param registryLookupFuture
     */
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
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
                .add(ModItems.getResourceKey(STRONG_AMETHYST_AXE))
                .add(ModItems.getResourceKey(EMERALD_AXE))
                .add(ModItems.getResourceKey(RUBY_AXE))
                .add(ModItems.getResourceKey(TOPAZ_AXE));

        tag(ItemTags.HOES)
                .add(ModItems.getResourceKey(AMETHYST_HOE))
                .add(ModItems.getResourceKey(EMERALD_HOE))
                .add(ModItems.getResourceKey(RUBY_HOE))
                .add(ModItems.getResourceKey(TOPAZ_HOE));

        tag(ItemTags.PICKAXES)
                .add(ModItems.getResourceKey(AMETHYST_PICKAXE))
                .add(ModItems.getResourceKey(EMERALD_PICKAXE))
                .add(ModItems.getResourceKey(RUBY_PICKAXE))
                .add(ModItems.getResourceKey(TOPAZ_PICKAXE));

        tag(ItemTags.SHOVELS)
                .add(ModItems.getResourceKey(AMETHYST_SHOVEL))
                .add(ModItems.getResourceKey(EMERALD_SHOVEL))
                .add(ModItems.getResourceKey(RUBY_SHOVEL))
                .add(ModItems.getResourceKey(TOPAZ_SHOVEL));

        tag(ItemTags.SPEARS)
                .add(ModItems.getResourceKey(AMETHYST_SPEAR))
                .add(ModItems.getResourceKey(EMERALD_SPEAR))
                .add(ModItems.getResourceKey(RUBY_SPEAR))
                .add(ModItems.getResourceKey(TOPAZ_SPEAR));

        tag(ItemTags.SWORDS)
                .add(ModItems.getResourceKey(AMETHYST_SWORD))
                .add(ModItems.getResourceKey(EMERALD_SWORD))
                .add(ModItems.getResourceKey(RUBY_SWORD))
                .add(ModItems.getResourceKey(TOPAZ_SWORD));
    }
}
