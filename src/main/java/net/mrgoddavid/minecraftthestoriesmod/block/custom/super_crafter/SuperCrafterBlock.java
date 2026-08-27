package net.mrgoddavid.minecraftthestoriesmod.block.custom.super_crafter;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Super Crafter Block class.
 *
 * @author Mr. GodDavid
 * @since 8/15/2026
 */
public class SuperCrafterBlock extends BaseEntityBlock implements EntityBlock {

    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final MapCodec<SuperCrafterBlock> CODEC = simpleCodec(SuperCrafterBlock::new);
    public static final VoxelShape SHAPE = Block.box(0, 0.01, 0, 16, 14, 16);
    public static final String NAME = "super_crafter";

    /**
     * Indicates the block state of Super Crafter Block.
     *
     * @author Mr. GodDavid
     * @since 8/15/2026
     */
    public enum TYPE implements StringRepresentable {
        WITH_BLUEPRINT("with_blueprint"),
        WITH_HAMMER("with_hammer"),
        WITH_HAMMER_WITH_BLUEPRINT("with_hammer_with_blueprint"),
        DEFAULT("default");

        private final String name;

        TYPE(String name) {
            this.name = name;
        }

        public String path() {
            return "block/" + NAME + "_" + name;
        }

        @Override
        public @NonNull String getSerializedName() {
            return name;
        }
    }

    public static final EnumProperty<TYPE> STATE = EnumProperty.create("state", TYPE.class);

    public SuperCrafterBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState()
                .setValue(STATE, TYPE.DEFAULT)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, STATE);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state,
                              @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        if (level.getBlockEntity(pos) instanceof SuperCrafterBlockEntity superCrafterBlockEntity) {
            superCrafterBlockEntity.drops();
            level.updateNeighbourForOutputSignal(pos, this);
        }
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            if (level.getBlockEntity(pos) instanceof SuperCrafterBlockEntity superCrafterBlockEntity) {
                player.openMenu(superCrafterBlockEntity);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new SuperCrafterBlockEntity(worldPosition, blockState);
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }
}
