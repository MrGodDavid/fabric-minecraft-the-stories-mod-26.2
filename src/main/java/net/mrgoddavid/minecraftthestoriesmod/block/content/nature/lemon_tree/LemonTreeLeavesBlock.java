package net.mrgoddavid.minecraftthestoriesmod.block.content.nature.lemon_tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.client.EntityUtils;
import org.jspecify.annotations.NonNull;

/**
 * @author Mr. GodDavid
 * @since 11/25/2026
 */
public class LemonTreeLeavesBlock extends TintedParticleLeavesBlock implements BonemealableBlock {

    public static final MapCodec<LemonTreeLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(ExtraCodecs.floatRange(0.0F, 1.0F)
                            .fieldOf("leaf_particle_chance")
                            .forGetter(leave -> leave.leafParticleChance), propertiesCodec())
                    .apply(instance, LemonTreeLeavesBlock::new));

    /**
     * @author Mr. GodDavid
     * @since 11/25/2026
     */
    public enum TYPE implements StringRepresentable {
        NORMAL("lemon_tree_leaves"),
        FLOWERING("flowering_lemon_tree_leaves"),
        MATURE("lemon_tree_leaves");

        private final String name;

        TYPE(String name) {
            this.name = name;
        }

        public String path() {
            return "block/" + name;
        }

        @Override
        public @NonNull String getSerializedName() {
            return this.name;
        }
    }

    public static final EnumProperty<TYPE> LEMON_LEAF_STATE = EnumProperty.create("lemon_leaf_state", TYPE.class);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final int MAX_AGE = 3;

    public LemonTreeLeavesBlock(float leafParticleChance, Properties properties) {
        super(leafParticleChance, properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(LEMON_LEAF_STATE, TYPE.NORMAL));
    }

    @Override
    public @NonNull BlockState getStateForPlacement(@NonNull BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(LEMON_LEAF_STATE, TYPE.NORMAL);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LEMON_LEAF_STATE);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        // todo: add lemon model below this bone-mealed lemon leave.
        if (!isFullyGrown(state)) {
            this.grow(state);
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        if (!EntityUtils.isInHarshEnvironment(level, pos)) {
            if (!isFullyGrown(state) && random.nextInt(10) == 0) {
                this.grow(state);
            }
        }
    }

    private void grow(BlockState state) {
        state.cycle(AGE);
        state.cycle(LEMON_LEAF_STATE);
    }

    private static boolean isFullyGrown(BlockState state) {
        return state.getValue(AGE) == MAX_AGE;
    }
}
