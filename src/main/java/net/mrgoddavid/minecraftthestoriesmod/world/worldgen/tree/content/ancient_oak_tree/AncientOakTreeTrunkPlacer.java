package net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree.content.ancient_oak_tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrgoddavid.ocl.function.Expression;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.mrgoddavid.minecraftthestoriesmod.world.worldgen.tree.MtsTrunkPlacerTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author Mr. GodDavid
 * @since 11/15/2026
 */
public class AncientOakTreeTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<AncientOakTreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            trunkPlacerParts(instance).apply(instance, AncientOakTreeTrunkPlacer::new));

    public AncientOakTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return MtsTrunkPlacerTypes.ANCIENT_OAK_TREE;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            WorldGenLevel level,
            BiConsumer<BlockPos, BlockState> trunkSetter,
            RandomSource random,
            int treeHeight,
            BlockPos origin,
            TreeConfiguration config
    ) {
        placeBelowTrunkBlock(level, trunkSetter, random, origin.below(), config);
        int maxHeight = treeHeight + random.nextInt(heightRandA, heightRandB + 3) + random.nextInt(heightRandA - 1, heightRandB + 2);
        BlockPos currentPos;
        List<FoliagePlacer.FoliageAttachment> attachments = new ArrayList<>();

        int outmostLayerMaxHeight = (int) (maxHeight * 0.25F);
        int outmostPillarBaseHeight = outmostLayerMaxHeight / 2;
        int outmostPillarHeight = 0;
        int counter = 0;
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                if ((Mth.abs(x) == 3 || Mth.abs(z) == 3) && (Mth.abs(x) != Mth.abs(z))) {
                    counter++;
                    int shapeMaxHeight = -(counter * counter) + outmostLayerMaxHeight;
                    while (outmostPillarHeight < shapeMaxHeight) {
                        currentPos = origin.offset(x, outmostPillarHeight, z);
                        if (outmostPillarHeight >= outmostPillarBaseHeight && random.nextFloat() < 0.3F) {
                            break;
                        }
                        BlockState state = config.trunkProvider.getState(level, random, origin);
                        trunkSetter.accept(currentPos, state);
                        outmostPillarHeight++;
                    }
                    counter = 0;
                }
                outmostPillarHeight = 0;
            }
        }

        // * X X X *
        // X       X
        // X   O   X
        // X       X
        // * X X X *
        int outLayerMaxHeight = (int) (maxHeight * 0.6);
        int outerPillarHeight = 0;
        int outerBaseHeight = (int) (outLayerMaxHeight * 0.75);
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if ((Mth.abs(x) == 2 || Mth.abs(z) == 2) && (Mth.abs(x) != Mth.abs(z))) {
                    while (outerPillarHeight < outLayerMaxHeight) {
                        currentPos = origin.offset(x, outerPillarHeight, z);
                        if (outerPillarHeight >= outerBaseHeight && random.nextBoolean()) {
                            if (Mth.abs(x) != 2 || Mth.abs(z) != 2) {
                                BlockPos branchOrigin = origin.offset(x, Math.max(0, outerPillarHeight - 1), z);
                                int directionX = Mth.abs(x) == 2 ? Integer.signum(x) : 0;
                                int directionZ = Mth.abs(z) == 2 ? Integer.signum(z) : 0;
                                placeBranch(level, trunkSetter, random, branchOrigin, directionX, directionZ, random.nextInt(2, 5), config, attachments, false);
                            }
                            break;
                        }
                        BlockState state = config.trunkProvider.getState(level, random, origin);
                        trunkSetter.accept(currentPos, state);
                        outerPillarHeight++;
                    }
                    outerPillarHeight = 0;
                }
            }
        }

        int innerLayerMaxHeight = maxHeight - random.nextInt(5, 10);
        int innerPillarHeight;
        int innerPillarBaseHeight = (int) (innerLayerMaxHeight * 0.8);
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (Mth.abs(x) == 1 || Mth.abs(z) == 1) {
                    innerPillarHeight = 0;
                    currentPos = origin;
                    while (innerPillarHeight < innerLayerMaxHeight) {
                        currentPos = origin.offset(x, innerPillarHeight, z);
                        float heightProgress = (float) innerPillarBaseHeight / innerLayerMaxHeight;
                        if (innerPillarHeight > innerPillarBaseHeight && random.nextFloat() < 0.1F) {
                            break;
                        }
                        BlockState state = config.trunkProvider.getState(level, random, origin);
                        trunkSetter.accept(currentPos, state);
                        innerPillarHeight++;

                        if (heightProgress > 0.2f && heightProgress < 0.9f && random.nextFloat() < 0.09F) {
                            float branchMultiplier = Mth.sin(heightProgress * Mth.PI);
                            int branchLength = Math.max(2, 3 + Mth.floor(branchMultiplier * 7 + random.nextInt(-1, 2)));
                            int directionX = Integer.signum(x);
                            int directionZ = Integer.signum(z);
                            placeBranch(level, trunkSetter, random, currentPos, directionX, directionZ, branchLength, config, attachments, true);
                        }
                    }
                    attachments.add(this.createAncientOakTreeFoliageAttachments(currentPos, innerPillarHeight, innerLayerMaxHeight));
                }
            }
        }
        return attachments;
    }

    private FoliagePlacer.FoliageAttachment createAncientOakTreeFoliageAttachments(BlockPos currentPos, int height, int maxHeight) {
        float progress = Math.clamp((float) height / maxHeight, 0, 1);
        int radiusOffset = Mth.floor(progress * progress * 2.5F);
        return new FoliagePlacer.FoliageAttachment(currentPos, radiusOffset, false);
    }

    private BlockPos placeBranch(
            WorldGenLevel level,
            BiConsumer<BlockPos, BlockState> trunkSetter,
            RandomSource random,
            BlockPos origin,
            int directionX,
            int directionZ,
            int branchLength,
            TreeConfiguration config,
            List<FoliagePlacer.FoliageAttachment> attachments,
            boolean createSecondary
    ) {
        Expression curve = (x) -> (1f / 12) * x * x;
        BlockPos current = origin;
        for (int i = 0; i <= branchLength; i++) {
            int rise = (int) curve.value(i);
            current = origin.offset(directionX * i, rise, directionZ * i);
            BlockState state = config.trunkProvider.getState(level, random, origin);
            if (state.hasProperty(RotatedPillarBlock.AXIS)) {
                Direction.Axis axis = directionX != 0 ? Direction.Axis.X : Direction.Axis.Z;
                state = state.setValue(RotatedPillarBlock.AXIS, axis);
            }
            trunkSetter.accept(current, state);
        }
        attachments.add(new FoliagePlacer.FoliageAttachment(current, 0, false));

        if (createSecondary && branchLength >= 5 && random.nextBoolean()) {
            int splitDistance = branchLength / 2;
            BlockPos splitPos = origin.offset(directionX * splitDistance, branchLength / 4, directionZ * splitDistance);
            int secondaryX = -directionZ;
            int secondaryZ = -directionX;
            if (random.nextBoolean()) {
                secondaryX = -directionX;
                secondaryZ = -directionZ;
            }
            placeBranch(level, trunkSetter, random, splitPos, secondaryX, secondaryZ, random.nextInt(2, 5), config, attachments, false);
        }
        return current;
    }
}
