package net.mrgoddavid.minecraftthestoriesmod.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;

/**
 * World generation for Minecraft: The Stories mod.
 *
 * @author Mr. GodDavid
 * @since 8/18/2026
 */
public class MtsWorldGen {

    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_AMETHYST_OVERWORLD_ORE = configuredFeatureRK("deepslate_amethyst_overworld_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_TOPAZ_OVERWORLD_ORE = configuredFeatureRK("deepslate_topaz_overworld_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_RUBY_OVERWORLD_ORE = configuredFeatureRK("deepslate_ruby_overworld_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_AMETHYST_OVERWORLD_ORE = configuredFeatureRK("stone_amethyst_overworld_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_TOPAZ_OVERWORLD_ORE = configuredFeatureRK("stone_topaz_overworld_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_RUBY_OVERWORLD_ORE = configuredFeatureRK("stone_ruby_overworld_ore");

    public static final ResourceKey<PlacedFeature> DEEPSLATE_AMETHYST_OVERWORLD_ORE_PLACED = placedFeatureRK("deepslate_amethyst_overworld_ore_placed");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_TOPAZ_OVERWORLD_ORE_PLACED = placedFeatureRK("deepslate_topaz_overworld_ore_placed");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_RUBY_OVERWORLD_ORE_PLACED = placedFeatureRK("deepslate_ruby_overworld_ore_placed");
    public static final ResourceKey<PlacedFeature> STONE_AMETHYST_OVERWORLD_ORE_PLACED = placedFeatureRK("stone_amethyst_overworld_ore_placed");
    public static final ResourceKey<PlacedFeature> STONE_TOPAZ_OVERWORLD_ORE_PLACED = placedFeatureRK("stone_topaz_overworld_ore_placed");
    public static final ResourceKey<PlacedFeature> STONE_RUBY_OVERWORLD_ORE_PLACED = placedFeatureRK("stone_ruby_overworld_ore_placed");

    private static ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureRK(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name);
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, id);
    }

    private static ResourceKey<PlacedFeature> placedFeatureRK(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(MinecraftTheStoriesMod.MOD_ID, name);
        return ResourceKey.create(Registries.PLACED_FEATURE, id);
    }

    public static void registerWorldGen() {
        MinecraftTheStoriesMod.LOGGER.info("Registering World Gen for " + MinecraftTheStoriesMod.MOD_ID);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, MtsWorldGen.STONE_AMETHYST_OVERWORLD_ORE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, MtsWorldGen.DEEPSLATE_AMETHYST_OVERWORLD_ORE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, MtsWorldGen.STONE_TOPAZ_OVERWORLD_ORE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, MtsWorldGen.DEEPSLATE_TOPAZ_OVERWORLD_ORE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, MtsWorldGen.STONE_RUBY_OVERWORLD_ORE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, MtsWorldGen.DEEPSLATE_RUBY_OVERWORLD_ORE_PLACED);
    }

    private MtsWorldGen() throws IllegalAccessException {
        throw new IllegalAccessException("You can't access [MtsWorldGen] class!");
    }
}
