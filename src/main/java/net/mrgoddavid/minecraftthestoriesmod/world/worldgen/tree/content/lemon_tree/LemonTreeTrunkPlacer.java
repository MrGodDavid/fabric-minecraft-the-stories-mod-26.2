package net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree.content.lemon_tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree.MtsTrunkPlacerTypes;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author Mr. GodDavid
 * @since 11/25/2026 - one day before Thanksgiving!
 */
public class LemonTreeTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<LemonTreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            trunkPlacerParts(instance).apply(instance, LemonTreeTrunkPlacer::new));

    public LemonTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NonNull TrunkPlacerType<?> type() {
        return MtsTrunkPlacerTypes.LEMON_TREE;
    }

    @Override
    public @NonNull List<FoliagePlacer.FoliageAttachment> placeTrunk(
            @NonNull WorldGenLevel level,
            @NonNull BiConsumer<BlockPos, BlockState> trunkSetter,
            @NonNull RandomSource random,
            int treeHeight,
            @NonNull BlockPos origin,
            @NonNull TreeConfiguration config) {
        placeBelowTrunkBlock(level, trunkSetter, random, origin.below(), config);
        BlockPos currentPos = new BlockPos(origin);
        int maxHeight = treeHeight + random.nextInt(heightRandA, heightRandB + 3) + random.nextInt(heightRandA - 1, heightRandB + 2);
        int currentHeight = 0;
        while (currentHeight <  maxHeight) {
            currentPos = origin.offset(0, currentHeight, 0);
            BlockState state = config.trunkProvider.getState(level, random, origin);
            trunkSetter.accept(currentPos, state);
            currentHeight++;
        }
        return List.of(new FoliagePlacer.FoliageAttachment(currentPos, 0, false));
    }
}
