package net.mrgoddavid.minecraftthestoriesmod.test;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.WorldDataConfiguration;
import net.minecraft.world.level.levelgen.WorldDimensions;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.levelgen.presets.WorldPresets;

/**
 * Context of MTS Test World.
 *
 * @author Mr. GodDavid
 * @since 8/24/2026
 */
public class MtsTestWorldContext {

    public static final String WORLD_NAME = "MTS Test World";
    private static ServerLevel overworld;
    private static BlockPos origin;

    private MtsTestWorldContext() {
    }

    public static void register(ServerLevel overworld, BlockPos origin) {
        MtsTestWorldContext.overworld = overworld;
        MtsTestWorldContext.origin = origin;
    }

    public static LevelSettings levelSettings() {
        return new LevelSettings(WORLD_NAME, GameType.CREATIVE, LevelSettings.DifficultySettings.DEFAULT, true, WorldDataConfiguration.DEFAULT);
    }

    public static WorldOptions worldOptions() {
        return new WorldOptions(WorldOptions.randomSeed(), true, true);
    }

    public static WorldDimensions worldDimensions(HolderLookup.Provider provider) {
        return WorldPresets.createTestWorldDimensions(provider);
    }

    public static ServerLevel overworld() {
        return overworld;
    }

    public static BlockPos origin() {
        return origin;
    }
}
