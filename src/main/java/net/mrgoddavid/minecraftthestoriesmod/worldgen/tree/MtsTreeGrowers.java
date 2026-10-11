package net.mrgoddavid.minecraftthestoriesmod.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.mrgoddavid.minecraftthestoriesmod.MinecraftTheStoriesMod;
import net.mrgoddavid.minecraftthestoriesmod.worldgen.MtsWorldGen;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

/**
 * @author Mr. GodDavid
 * @since 10/11/2026
 */
public final class MtsTreeGrowers {

    public static final TreeGrower LEMON_TREE = registerNoMegaTreeNoFlowersNearBy("balsa", MtsWorldGen.LEMON_TREE);

    private static TreeGrower registerNoMegaTreeNoFlowersNearBy(String name, ResourceKey<ConfiguredFeature<?, ?>> feature) {
        return new TreeGrower(concatPathString(name), Optional.empty(), Optional.of(feature), Optional.empty());
    }

    private static String concatPathString(@NonNull final String name) {
        return MinecraftTheStoriesMod.MOD_ID.concat(":").concat(name);
    }
}
