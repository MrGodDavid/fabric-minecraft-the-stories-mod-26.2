package net.mrgoddavid.minecraftthestoriesmod.test;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.LevelData;

/**
 * Server of Mts Test World.
 *
 * @author Mr. GodDavid
 * @since 8/24/2026
 */
public class MtsTestWorldServer {

    private MtsTestWorldServer() throws IllegalAccessException {
        throw new IllegalAccessException("You cannot instantiate this class!!");
    }

    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            if (!server.getWorldData().getLevelName().equals(MtsTestWorldContext.WORLD_NAME)) {
                return;
            }
            ServerLevel overworld = server.overworld();
            int spawnY = overworld.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 0, 0);
            BlockPos origin = new BlockPos(0, spawnY, 0);
            MtsTestWorldContext.register(overworld, origin);
            overworld.setRespawnData(new LevelData.RespawnData(GlobalPos.of(Level.OVERWORLD, origin), 0.0F, 0.0F));
            MtsTestWorldGenerator.generate();
        });
    }
}
