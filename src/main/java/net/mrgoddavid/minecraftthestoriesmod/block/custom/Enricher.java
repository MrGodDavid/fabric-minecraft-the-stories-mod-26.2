package net.mrgoddavid.minecraftthestoriesmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Enricher block.
 *
 * @author Mr. GodDavid
 * @since 8/17/2026
 */
public class Enricher extends HorizontalDirectionalBlock {

    public static final MapCodec<Enricher> CODEC = simpleCodec(Enricher::new);
    public static final String NAME = "enricher";
    public static final VoxelShape SHAPE = Block.box(0, 0.001, 0, 16, 29, 16);

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
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");

    public Enricher(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState()
                .setValue(STATE, TYPE.DEFAULT)
                .setValue(CLICKED, false));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.setBlockAndUpdate(pos, state.cycle(CLICKED).cycle(STATE));
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, STATE, CLICKED);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
