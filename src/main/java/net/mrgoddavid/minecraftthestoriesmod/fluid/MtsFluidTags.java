package net.mrgoddavid.minecraftthestoriesmod.fluid;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

public class MtsFluidTags {

    public static final TagKey<Fluid> ENRICHER_WASTE = createTag("enricher_waste");

    private static TagKey<Fluid> createTag(String name) {
        return TagKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name));
    }
}
