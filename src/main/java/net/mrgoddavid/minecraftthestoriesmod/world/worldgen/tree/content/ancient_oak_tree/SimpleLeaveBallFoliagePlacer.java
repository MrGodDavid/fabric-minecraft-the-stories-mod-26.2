package net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree.content.ancient_oak_tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree.MtsFoliagePlacerTypes;

/**
 * @author Mr. GodDavid
 * @since 11/15/2026
 */
public class SimpleLeaveBallFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<SimpleLeaveBallFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).and(Codec.intRange(4, 8).fieldOf("height").forGetter(inst -> inst.height))
                    .apply(instance, SimpleLeaveBallFoliagePlacer::new));

    private final int height;

    public SimpleLeaveBallFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return MtsFoliagePlacerTypes.ANCIENT_OAK_TREE;
    }

    @Override
    protected void createFoliage(
            WorldGenLevel level,
            FoliageSetter foliageSetter,
            RandomSource random,
            TreeConfiguration config,
            int treeHeight,
            FoliageAttachment foliageAttachment,
            int foliageHeight,
            int leafRadius,
            int offset
    ) {
        BlockPos center = foliageAttachment.pos().above(offset);
        boolean doubleTrunk = foliageAttachment.doubleTrunk();

        this.placeLeavesRow(level, foliageSetter, random, config, center, leafRadius, -3, doubleTrunk);
        this.placeLeavesRow(level, foliageSetter, random, config, center, leafRadius + 1, -2, doubleTrunk);
        this.placeLeavesRow(level, foliageSetter, random, config, center, leafRadius + 2, -1, doubleTrunk);
        this.placeLeavesRow(level, foliageSetter, random, config, center, leafRadius + 2, 0, doubleTrunk);
        this.placeLeavesRow(level, foliageSetter, random, config, center, leafRadius + 2, 1, doubleTrunk);
        this.placeLeavesRow(level, foliageSetter, random, config, center, leafRadius + 1, 2, doubleTrunk);
        this.placeLeavesRow(level, foliageSetter, random, config, center, leafRadius, 3, doubleTrunk);
    }

    @Override
    public int foliageHeight(RandomSource random, int treeHeight, TreeConfiguration config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int currentRadius, boolean doubleTrunk) {
      return dx == currentRadius && dz == currentRadius;
    }
}
