package net.mrgoddavid.minecraftthestoriesmod.mixin.client;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HayBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.target_dummy.TargetDummyEntity;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mr. GodDavid
 * @since 10/25/2026
 */
@Mixin(BlockBehaviour.class)
public class BlockBehaviorMixin {

    @Inject(method = "onPlace", at = @At("HEAD"))
    private void mts$onPlaceCheckSpawnDummyTarget(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston, CallbackInfo ci) {
        if ((Object) this instanceof HayBlock) {
            if (!(level instanceof ServerLevel serverLevel)) return;
            if (oldState.is(state.getBlock())) return;
            if (!isRelevantBlock(state)) return;
            final BlockPos bottomPos = this.findTargetDummyPattern(serverLevel, pos);
            if (bottomPos == null) return;
            serverLevel.setBlock(bottomPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL); // fence
            serverLevel.setBlock(bottomPos.above(1), Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL); // hay
            serverLevel.setBlock(bottomPos.above(2), Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL); // hay
            this.spawnTargetDummyParticles(serverLevel, bottomPos);
            this.spawnTargetDummy(serverLevel, bottomPos);
        }
    }

    @Unique
    private void spawnTargetDummy(ServerLevel level, BlockPos bottomPos) {
        final TargetDummyEntity targetDummy = MtsEntityTypes.TARGET_DUMMY.create(level, EntitySpawnReason.TRIGGERED);
        if (targetDummy == null) return;
        targetDummy.snapTo(bottomPos.getX() + 0.5f, bottomPos.getY() + 0.05f, bottomPos.getZ() + 0.5f);
        level.addFreshEntity(targetDummy);
    }

    @Unique
    private void spawnTargetDummyParticles(ServerLevel serverLevel, BlockPos bottomPos) {
        serverLevel.sendParticles(ParticleTypes.POOF,
                bottomPos.getX() + 0.5D, bottomPos.getY() + 0.5D, bottomPos.getZ() + 0.5D,
                20, 0.25D, 0.5D, 0.25D, 0.02D);
    }

    @Unique
    private BlockPos findTargetDummyPattern(ServerLevel serverLevel, BlockPos pos) {
        for (int offset = 0; offset <= 2; offset++) {
            final BlockPos bottomPos = pos.below(offset);
            if (this.isDummyTargetPattern(serverLevel, bottomPos)) {
                return bottomPos;
            }
        }
        return null;
    }

    @Unique
    private boolean isDummyTargetPattern(ServerLevel serverLevel, BlockPos bottomPos) {
        boolean isBottomFence = serverLevel.getBlockState(bottomPos).is(BlockTags.FENCES);
        boolean isSecondLayerHayBlocks = serverLevel.getBlockState(bottomPos.above(1)).is(Blocks.HAY_BLOCK);
        boolean isThirdLayerHayBlocks = serverLevel.getBlockState(bottomPos.above(2)).is(Blocks.HAY_BLOCK);
        return isBottomFence && isSecondLayerHayBlocks && isThirdLayerHayBlocks;
    }

    @Unique
    private boolean isRelevantBlock(BlockState state) {
        return state.is(BlockTags.FENCES) || state.is(Blocks.HAY_BLOCK);
    }
}
