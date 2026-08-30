package net.mrgoddavid.minecraftthestoriesmod.fluid.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;
import net.mrgoddavid.minecraftthestoriesmod.fluid.MtsFluidTags;
import net.mrgoddavid.minecraftthestoriesmod.fluid.MtsFluids;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.particle.MtsParticleTypes;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public abstract class EnricherWasteFluid extends FlowingFluid {

    @Override
    public Fluid getFlowing() {
        return MtsFluids.ENRICHER_WASTE_FLOWING;
    }

    public Fluid getSource() {
        return MtsFluids.ENRICHER_WASTE_STILL;
    }

    public Item getBucket() {
        return MtsItems.ENRICHER_WASTE_BUCKET;
    }

    @Override
    public boolean isSame(Fluid other) {
        return other == MtsFluids.ENRICHER_WASTE_FLOWING || other == MtsFluids.ENRICHER_WASTE_STILL;
    }

    public BlockState createLegacyBlock(final FluidState fluidState) {
        return MtsBlocks.ENRICHER_WASTE_FLUID.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(fluidState));
    }

    @Override
    public void animateTick(Level level, BlockPos pos, FluidState fluidState, RandomSource random) {
        if (!fluidState.isSource() && !(Boolean) fluidState.getValue(FALLING)) {
            if (random.nextInt(64) == 0) {
                level.playLocalSound(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        SoundEvents.BUBBLE_COLUMN_WHIRLPOOL_AMBIENT,
                        SoundSource.AMBIENT,
                        random.nextFloat() * 0.25f + 0.75f,
                        random.nextFloat() + 0.5f,
                        false);
            }
        } else if (random.nextInt(1) == 0) {
            level.addParticle(
                    MtsParticleTypes.ENRICHER_WASTE_PARTICLE,
                    pos.getX() + random.nextDouble(),
                    pos.getY() + random.nextDouble(),
                    pos.getZ() + random.nextDouble(),
                    0.0D, 0.0D, 0.0D
            );
        }
    }

    @Override
    protected @Nullable ParticleOptions getDripParticle() {
        return ParticleTypes.DRIPPING_WATER;
    }

    @Override
    protected boolean canConvertToSource(ServerLevel level) {
        return level.getGameRules().get(GameRules.WATER_SOURCE_CONVERSION);
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, blockEntity);
    }

    @Override
    protected void entityInside(Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier) {
        effectApplier.apply(InsideBlockEffectType.EXTINGUISH);

        if (!(level instanceof ServerLevel server) || !(entity instanceof LivingEntity livingEntity)) {
            return;
        }

        if (level.getGameTime() % 20 == 0) {
            // Hurt and wither entities inside this fluid.
            livingEntity.hurtServer(server, level.damageSources().magic(), 2.0f); // 1 heart / sec
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 450, -3));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 300, -3));
        }
    }

    @Override
    protected int getSlopeFindDistance(LevelReader world) {
        return 4;
    }

    @Override
    protected int getDropOff(LevelReader level) {
        return 1;
    }

    @Override
    public int getTickDelay(LevelReader level) {
        return 10;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid other, Direction direction) {
        return direction == Direction.DOWN && !other.is(MtsFluidTags.ENRICHER_WASTE);
    }

    @Override
    protected float getExplosionResistance() {
        return 150.0f;
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }

    public static class Flowing extends EnricherWasteFluid {

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public boolean isSource(FluidState fluidState) {
            return false;
        }

        @Override
        public int getAmount(FluidState fluidState) {
            return fluidState.getValue(LEVEL);
        }
    }

    public static class Source extends EnricherWasteFluid {

        @Override
        public boolean isSource(FluidState fluidState) {
            return true;
        }

        @Override
        public int getAmount(FluidState fluidState) {
            return 8;
        }
    }
}
