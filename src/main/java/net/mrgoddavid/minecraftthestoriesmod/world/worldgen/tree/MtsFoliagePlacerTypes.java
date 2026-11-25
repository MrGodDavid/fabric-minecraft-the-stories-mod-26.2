package net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.MtsLogger;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Constants;
import net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree.content.ancient_oak_tree.SimpleLeaveBallFoliagePlacer;
import net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree.content.lemon_tree.LemonTreeFoliagePlacer;

/**
 * @author Mr. GodDavid
 * @since 11/15/2026
 */
public final class MtsFoliagePlacerTypes {

    public static final FoliagePlacerType<SimpleLeaveBallFoliagePlacer> ANCIENT_OAK_TREE = register("ancient_oak_tree_foliage_placer", SimpleLeaveBallFoliagePlacer.CODEC);
    public static final FoliagePlacerType<LemonTreeFoliagePlacer> LEMON_TREE_LEAVES = register("lemon_tree_leaves_foliage_placer", LemonTreeFoliagePlacer.CODEC);

    private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, Constants.modId(name), new FoliagePlacerType<>(codec));
    }

    public static void register() {
        MtsLogger.info("Custom Foliage Placer Types");
    }

    private MtsFoliagePlacerTypes() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
