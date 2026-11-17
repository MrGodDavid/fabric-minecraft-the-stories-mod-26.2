package net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;
import net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree.content.ancient_oak_tree.AncientOakTreeTrunkPlacer;

/**
 * @author Mr. GodDavid
 * @since 11/15/2026
 */
public final class MtsTrunkPlacerTypes {

    public static final TrunkPlacerType<AncientOakTreeTrunkPlacer> ANCIENT_OAK_TREE = register("ancient_oak_tree", AncientOakTreeTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, Constants.modId(name),
                new TrunkPlacerType<P>(codec));
    }

    public static void register() {
        MtsLogger.info("Custom Trunk Placer Types");
    }

    private MtsTrunkPlacerTypes() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
