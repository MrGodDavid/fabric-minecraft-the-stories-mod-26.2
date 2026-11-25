package net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree.content.lemon_tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree.MtsFoliagePlacerTypes;

/**
 * @author Mr. GodDavid
 * @since 11/25/2026
 */
@SuppressWarnings("NullableProblems")
public class LemonTreeFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<LemonTreeFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).and(Codec.intRange(2, 7).fieldOf("height").forGetter(inst -> inst.height))
                    .apply(instance, LemonTreeFoliagePlacer::new));

    private final int height; // 5 in this case.

    public LemonTreeFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return MtsFoliagePlacerTypes.LEMON_TREE_LEAVES;
    }

    @Override
    protected void createFoliage(WorldGenLevel level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int treeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int leafRadius, int offset) {
        BlockPos center = foliageAttachment.pos().above(offset);

        // leafRadius = 1;
        this.placeLeavesRow(level, foliageSetter, random, config, center, leafRadius + 1, -2, false);
        this.placeLeavesRow(level, foliageSetter, random, config, center, leafRadius + 1, -1, false);
        this.placeLeavesRow(level, foliageSetter, random, config, center, leafRadius + 1, 0, false);
        this.placeLeavesRow(level, foliageSetter, random, config, center, leafRadius, 1, false);
        this.placeLeavesRow(level, foliageSetter, random, config, center, leafRadius, 2, false);
    }

    @Override
    public int foliageHeight(RandomSource random, int treeHeight, TreeConfiguration config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int currentRadius, boolean doubleTrunk) {
        return Mth.abs(y) != 1 && (dx == currentRadius && dz == currentRadius);
    }
}
