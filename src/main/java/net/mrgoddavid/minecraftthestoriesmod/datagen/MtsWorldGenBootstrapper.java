package net.mrgoddavid.minecraftthestoriesmod.datagen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;
import net.mrgoddavid.minecraftthestoriesmod.worldgen.MtsWorldGen;

import java.util.List;

/**
 * World generation for Minecraft: The Stories mod. Provides fabric boostrap for fabric's data gen.
 * A feature is basically the unit of forming Minecraft world, like tress, caves, different structures, etc. Each
 * feature needs a configuration and codes that determines how that feature generates. For instance, new ore called
 * MY_MOD_ORE, that ore needs a configuration and rules (placed feature) to determine how Minecraft generates
 * MY_MOD_ORE.
 *
 * @author Mr. GodDavid
 * @since 8/18/2026
 */
public final class MtsWorldGenBootstrapper {

    public static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(MtsWorldGen.STONE_AMETHYST_OVERWORLD_ORE, new ConfiguredFeature<>(
                Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), MtsBlocks.STONE_AMETHYST_ORE.defaultBlockState())
        ), 7, 0.5f)));
        context.register(MtsWorldGen.STONE_TOPAZ_OVERWORLD_ORE, new ConfiguredFeature<>(
                Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), MtsBlocks.STONE_TOPAZ_ORE.defaultBlockState())
        ), 9, 0.5f)));
        context.register(MtsWorldGen.STONE_RUBY_OVERWORLD_ORE, new ConfiguredFeature<>(
                Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), MtsBlocks.STONE_RUBY_ORE.defaultBlockState())
        ), 8, 0.5f)));
        context.register(MtsWorldGen.DEEPSLATE_AMETHYST_OVERWORLD_ORE, new ConfiguredFeature<>(
                Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), MtsBlocks.DEEPSLATE_AMETHYST_ORE.defaultBlockState())
        ), 7, 0.5f)));
        context.register(MtsWorldGen.DEEPSLATE_TOPAZ_OVERWORLD_ORE, new ConfiguredFeature<>(
                Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), MtsBlocks.DEEPSLATE_TOPAZ_ORE.defaultBlockState())
        ), 9, 0.5f)));
        context.register(MtsWorldGen.DEEPSLATE_RUBY_OVERWORLD_ORE, new ConfiguredFeature<>(
                Feature.ORE, new OreConfiguration(List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), MtsBlocks.DEEPSLATE_RUBY_ORE.defaultBlockState())
        ), 8, 0.5f)));
    }

    public static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(MtsWorldGen.STONE_AMETHYST_OVERWORLD_ORE_PLACED, new PlacedFeature(
                configuredFeatures.getOrThrow(MtsWorldGen.STONE_AMETHYST_OVERWORLD_ORE),
                List.of(
                        CountPlacement.of(UniformInt.of(3, 9)),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(64), VerticalAnchor.aboveBottom(84)),
                        BiomeFilter.biome()
                )
        ));
        context.register(MtsWorldGen.STONE_TOPAZ_OVERWORLD_ORE_PLACED, new PlacedFeature(
                configuredFeatures.getOrThrow(MtsWorldGen.STONE_TOPAZ_OVERWORLD_ORE),
                List.of(
                        CountPlacement.of(UniformInt.of(5, 9)),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(54), VerticalAnchor.aboveBottom(104)),
                        BiomeFilter.biome()
                )
        ));
        context.register(MtsWorldGen.STONE_RUBY_OVERWORLD_ORE_PLACED, new PlacedFeature(
                configuredFeatures.getOrThrow(MtsWorldGen.STONE_RUBY_OVERWORLD_ORE),
                List.of(
                        CountPlacement.of(UniformInt.of(4, 9)),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(74), VerticalAnchor.aboveBottom(94)),
                        BiomeFilter.biome()
                )
        ));
        context.register(MtsWorldGen.DEEPSLATE_AMETHYST_OVERWORLD_ORE_PLACED, new PlacedFeature(
                configuredFeatures.getOrThrow(MtsWorldGen.DEEPSLATE_AMETHYST_OVERWORLD_ORE),
                List.of(
                        CountPlacement.of(UniformInt.of(4, 9)),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(20)),
                        BiomeFilter.biome()
                )
        ));
        context.register(MtsWorldGen.DEEPSLATE_TOPAZ_OVERWORLD_ORE_PLACED, new PlacedFeature(
                configuredFeatures.getOrThrow(MtsWorldGen.DEEPSLATE_TOPAZ_OVERWORLD_ORE),
                List.of(
                        CountPlacement.of(UniformInt.of(5, 9)),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(30)),
                        BiomeFilter.biome()
                )
        ));
        context.register(MtsWorldGen.DEEPSLATE_RUBY_OVERWORLD_ORE_PLACED, new PlacedFeature(
                configuredFeatures.getOrThrow(MtsWorldGen.DEEPSLATE_RUBY_OVERWORLD_ORE),
                List.of(
                        CountPlacement.of(UniformInt.of(5, 9)),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(26)),
                        BiomeFilter.biome()
                )
        ));
    }

    private MtsWorldGenBootstrapper() throws IllegalAccessException {
        throw new IllegalAccessException("You can't access [MtsWorldGen] class!");
    }
}
