package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;

import java.util.concurrent.CompletableFuture;

/**
 * @author Mr. GodDavid
 * @since 10/29/2026
 */
public final class MtsDamageTypeTagsProvider extends DamageTypeTagsProvider {

    public MtsDamageTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
//        super.addTags(registries);
        this.tag(DamageTypeTags.NO_KNOCKBACK)
                .add(MtsDamageTypes.THIRST);
    }
}
