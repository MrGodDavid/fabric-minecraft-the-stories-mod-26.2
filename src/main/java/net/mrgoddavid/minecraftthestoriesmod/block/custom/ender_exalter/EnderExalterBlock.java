package net.mrgoddavid.minecraftthestoriesmod.block.custom.ender_exalter;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Block of Ender Exalter.
 *
 * @author Mr. GodDavid
 * @since 8/21/2026
 */
public class EnderExalterBlock extends BaseEntityBlock {

    public static final VoxelShape SHAPE = Block.box(1.0, 0.001, 1.0, 15.0, 15.0, 15.0);
    private static final MapCodec<EnderExalterBlock> CODEC = simpleCodec(EnderExalterBlock::new);

    public EnderExalterBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        if (level.getBlockEntity(pos) instanceof EnderExalterBlockEntity entity) {
            entity.drops();
            level.updateNeighbourForOutputSignal(pos, this);
        }
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof EnderExalterBlockEntity entity) {

            if (!level.isClientSide()) {
                player.openMenu(entity);
            }

//            Vec3 hit = hitResult.getLocation();
//            double localX = hit.x - pos.getX();
//            int slotIndex = localX < 0.5 ? 0 : 1;
//
//            boolean isEmpty = entity.isEmpty();
//            // INSERT
//            if (isEmpty && !itemStack.isEmpty()) {
//                entity.setItem(slotIndex, itemStack);
//                itemStack.shrink(1);
//                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0f, 2.0f);
//            }
//            // EXTRACT
//            else if (!isEmpty) {
//                ItemStack stackOnExalter = entity.getItem(slotIndex);
//                entity.clearContent();
//                if (!player.getInventory().add(stackOnExalter)) {
//                    player.drop(stackOnExalter, false);
//                    level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0f, 1.0f);
//                }
//            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos worldPosition, @NonNull BlockState blockState) {
        return new EnderExalterBlockEntity(worldPosition, blockState);
    }
}
