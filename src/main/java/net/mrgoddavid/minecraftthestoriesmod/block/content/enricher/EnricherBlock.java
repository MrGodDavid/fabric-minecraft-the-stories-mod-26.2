package net.mrgoddavid.minecraftthestoriesmod.block.content.enricher;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Enricher block.
 *
 * @author Mr. GodDavid
 * @since 8/17/2026
 */
public class EnricherBlock extends BaseEntityBlock implements EntityBlock {

    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final MapCodec<EnricherBlock> CODEC = simpleCodec(EnricherBlock::new);
    public static final VoxelShape SHAPE = Block.box(0, 0.001, 0, 16, 29, 16);
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final String NAME = "enricher";

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new EnricherBlockEntity(worldPosition, blockState);
    }

    /**
     * Indicates the block state of Enricher.
     *
     * @author Mr. GodDavid
     * @since 8/17/2026
     */
    public enum TYPE implements StringRepresentable {
        DEFAULT("default"),
        WITH_MOLTEN("with_molten");

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

    public EnricherBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState()
                .setValue(STATE, TYPE.DEFAULT)
                .setValue(LIT, false)
                .setValue(FACING, Direction.NORTH));
    }

    /* FACING */
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, LIT, STATE);
    }

    /* BLOCK LOGIC */
    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        if (level.getBlockEntity(pos) instanceof EnricherBlockEntity enricherBlockEntity) {
            enricherBlockEntity.drops();
        }
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            if (hand == InteractionHand.MAIN_HAND && itemStack.isEmpty()) {
                if (level.getBlockEntity(pos) instanceof EnricherBlockEntity enricherBlockEntity) {
                    player.openMenu(enricherBlockEntity);
                }
                return InteractionResult.SUCCESS;
            }
            if (itemStack.is(Items.BUCKET) && level.getBlockEntity(pos) instanceof EnricherBlockEntity enricherBlockEntity) {
                if (!enricherBlockEntity.isEnriching()) {
                    enricherBlockEntity.clearWasteFluid();
                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }

                    ItemStack wasteFluidBucket = new ItemStack(MtsItems.ENRICHER_WASTE_BUCKET);
                    if (!player.addItem(wasteFluidBucket)) {
                        player.drop(wasteFluidBucket, false);
                    }
                    return InteractionResult.SUCCESS;
                }
            } else if (level.getBlockEntity(pos) instanceof EnricherBlockEntity enricherBlockEntity) {
                player.openMenu(enricherBlockEntity);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            if (level.getBlockEntity(pos) instanceof EnricherBlockEntity enricherBlockEntity) {
                player.openMenu(enricherBlockEntity);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        return createTickerHelper(type, MtsBlockEntities.ENRICHER_BE,
                (level1, pos, state, entity) -> entity.tick(level1, pos, state));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
