package net.mrgoddavid.minecraftthestoriesmod.block.content.ore_compressor;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
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
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.mrgoddavid.minecraftthestoriesmod.block.entity.MtsBlockEntities;
import org.jspecify.annotations.Nullable;

/**
 * Ore Compressor block.
 *
 * @author Mr. GodDavid
 * @since 8/28/2026
 */
public class OreCompressorBlock extends BaseEntityBlock implements EntityBlock {

    public static final MapCodec<OreCompressorBlock> CODEC = simpleCodec(OreCompressorBlock::new);
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape SHAPE = Block.box(0, 0.001, 0, 16, 30, 16);
    public static final String NAME = "ore_compressor";

    public enum TYPE implements StringRepresentable {
        WITHOUT_FREEWHEEL("without_freewheel"), // use this when placing the ore compressor. Default block state of ore compressor.
        DEFAULT("default"); // use this for displaying ore compressor in player's inventory.

        private final String name;

        TYPE(String name) {
            this.name = name;
        }

        public String path() {
            return "block/" + NAME + "_" + name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }

    public enum ANGLE_CORRECTION implements StringRepresentable {
        NORTH(180.0f, new FreewheelModelTranslationCorrection(-0.5f, -0.5f, -0.125f), "north"),
        EAST(90.0f, new FreewheelModelTranslationCorrection(-0.5f, -0.5f, 0.8125f), "east"),
        SOUTH(0.0f, new FreewheelModelTranslationCorrection(0.5f, -0.5f, 0.825f), "south"),
        WEST(-90.0f, new FreewheelModelTranslationCorrection(0.5f, -0.5f, -0.18f), "west");

        private final float angleCorrection;
        private final FreewheelModelTranslationCorrection translation;
        private final String name;

        ANGLE_CORRECTION(float angleCorrection, FreewheelModelTranslationCorrection translation, String name) {
            this.angleCorrection = angleCorrection;
            this.translation = translation;
            this.name = name;
        }

        public FreewheelModelTranslationCorrection translation() {
            return translation;
        }

        public float angleCorrection() {
            return angleCorrection;
        }

        @Override
        public String getSerializedName() {
            return "angle_correction_" + name;
        }
    }

    public record FreewheelModelTranslationCorrection(float x, float y, float z) {
    }

    public static final EnumProperty<TYPE> STATE = EnumProperty.create("state", TYPE.class);
    public static final EnumProperty<ANGLE_CORRECTION> FREEWHEEL_MODEL_CORRECTION = EnumProperty.create("angle_correction", ANGLE_CORRECTION.class);

    public OreCompressorBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(STATE, TYPE.DEFAULT)
                .setValue(FREEWHEEL_MODEL_CORRECTION, ANGLE_CORRECTION.NORTH));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection())
                .setValue(STATE, TYPE.WITHOUT_FREEWHEEL)
                .setValue(FREEWHEEL_MODEL_CORRECTION, correctAngle(context));
    }

    private ANGLE_CORRECTION correctAngle(BlockPlaceContext context) {
        return switch (context.getHorizontalDirection()) {
            case NORTH -> ANGLE_CORRECTION.NORTH;
            case EAST -> ANGLE_CORRECTION.EAST;
            case SOUTH -> ANGLE_CORRECTION.SOUTH;
            case WEST -> ANGLE_CORRECTION.WEST;
            default -> null;
        };
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof OreCompressorBlockEntity oreCompressorBlockEntity) {
            player.openMenu(oreCompressorBlockEntity);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, STATE, FREEWHEEL_MODEL_CORRECTION);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        if (level.getBlockEntity(pos) instanceof OreCompressorBlockEntity oreCompressorBlockEntity) {
            oreCompressorBlockEntity.drops();
        }
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        return createTickerHelper(type, MtsBlockEntities.ORE_COMPRESSOR_BE,
                ((level1, pos, state, entity) -> entity.tick(level1, pos, state)));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new OreCompressorBlockEntity(worldPosition, blockState);
    }
}
