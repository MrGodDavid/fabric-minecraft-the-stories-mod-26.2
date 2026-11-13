package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.mrgoddavid.minecraftthestoriesmod.block.MtsBlocks;
import net.mrgoddavid.minecraftthestoriesmod.datagen.MtsDamageTypes;
import net.mrgoddavid.minecraftthestoriesmod.effect.MtsEffects;
import net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal.AvoidBlockGoal;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.WoodlinContext;
import net.mrgoddavid.minecraftthestoriesmod.registries.MtsEntityTypes;
import net.mrgoddavid.minecraftthestoriesmod.tags.MtsTags;
import net.mrgoddavid.minecraftthestoriesmod.utils.list.MtsElementSets;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.Timer;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.client.EntityUtils;

import java.util.*;
import java.util.function.Predicate;

/**
 * Oakie is the spirit of the Dense Oaklin, where a mysterious dense oak forest and home to many ancient wooden
 * species. In the original comic of Minecraft: The Stories, Dense Oaklin is a biome that is recorded by very few
 * adventurers. So few people recorded that even the "author" did not draw this biome in the comic. (Kind of lazy, uh)
 * Anyway, Dense Oaklin grows dense, tall, and strong ancient oak trees known as Oaklin. There is a very small chance
 * that the House of Oakie would spawn in the center of the Oaklin.
 * <p>According to an anonymous adventurer from the Empire of Monster, "I understand why the greatest and most powerful
 * conquerer Naeus was forced to bypass this region. There are so many unknow plants and creatures living in here. The
 * terrain is unpredictable due to the density of vegetation. This is incredible." His recording stops here.</p>
 * <p>Later, the Player successfully entered the Dense Oaklin and tamed an Oakie. On his way back home, the "player"
 * discovered that Oakies grant healing buffs to player and harm any enemies that is too close to player. The Player
 * named this effect the Leech of Oakie, because he believed Oakies steal life from enemy to restore its owner's
 * health.</p>
 * <p>Oakies not only restore its owner's health but also help all crops within a circle of radius of 5 blocks to grow.
 * Oakie can only do this if it's in Enchanting mode. Enchanting mode is the most powerful state for all Oakies. Oakie
 * under this mode grants massive health boost and regeneration buffs. The only downside is Oakies cannot move when
 * performing Enchanting mode because they need to focus on casting spells and make sure they don't cast spells to the
 * wrong person.</p>
 * <p>Oakies are loyal once it gained trust. They would make sure that their owner would not die by following close to
 * their owner and heal them whenever the owner is critically injured.</p>
 * <p>Oakies hate water and desert environment. They would receive water damage and heat damage respectively under
 * the environments.</p>
 * <p>Oakies receive twice as much as burning damage as players because they are made of wood! Make sure they don't get
 * too close to lava, camp fire, or any fire sources. There is a small chance that oakie would receive fire damage once
 * it stands next to a torch. Oakies have high chance of receiving fire damage next to a soul torch and less chance
 * of receiving fire damage next to a copper torch.</p>
 * <p>Oakies have a small chance of receiving frost damage when staying in a frost biome.</p>
 *
 * @author Mr. GodDavid
 * @since 10/16/2026
 */
@SuppressWarnings({"NullableProblems", "resource", "SpellCheckingInspection"})
public class OakieEntity extends AbstractWoodlinEntity {

    public static final int HEALING_RADIUS = 5;
    public static final float MIN_NOTICE_NEEDS_HEALING_DISTANCE = 6.0F;
    public static final float MAX_NOTICE_NEEDS_HEALING_DISTANCE = 18.0F;
    public final AnimationState healAnimationState = new AnimationState();
    private final Timer campFireHurtCoolDown = new Timer(40);
    private final Timer fireblocksHurtCoolDown = new Timer(40);
    private final Timer torchesHurtCoolDown = new Timer(40);
    private final Timer lavaHurtCoolDown = new Timer(40);

    public OakieEntity(EntityType<OakieEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createMtsEntityAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.FOLLOW_RANGE, 32);
    }

    @Override
    protected MtsElementSets.Trio<Item, Item, Item> registerWoodlinInteractableItems() {
        return new MtsElementSets.Trio<>(Items.OAK_SAPLING, Items.OAK_LEAVES, Items.STICK);
    }

    @Override
    protected void registerBehavioralGoals() {
        this.goalSelector.addGoal(2, new OakieEntity.OakieAvoidFireBlockGoal(this, this::isFireblocks));
        this.goalSelector.addGoal(2, new OakieEntity.OakieAvoidCampFiresGoal(this, this::isCampfires));
        this.goalSelector.addGoal(2, new OakieEntity.OakieAvoidLavaGoal(this, this::isLava));
        this.goalSelector.addGoal(3, new OakieEntity.OakieHealOwnerGoal(MIN_NOTICE_NEEDS_HEALING_DISTANCE, MAX_NOTICE_NEEDS_HEALING_DISTANCE));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new OakieEntity.OakieGrowCropsGoal());
    }

    @Override
    protected WoodlinContext registerWoodlinContexts() {
        return WoodlinContext.builder()
                .woodlinAbilityUsageRadius(HEALING_RADIUS)
                .tamedAnimalPanicSpeedModifier(1.25D)
                .tamedAnimalFollowOwnerSpeedModifier(1.025D)
                .tamedAnimalFollowOwnerStartDistance(24.0F)
                .tamedAnimalFollowOwnerStopDistance(2.0F)
                .entityRandomlyLookDistance(4.0F)
                .build();
    }

    private boolean isLava(BlockState blockState) {
        return blockState.is(Blocks.LAVA);
    }

    private boolean isCampfires(BlockState blockState) {
        return blockState.is(Blocks.CAMPFIRE) || blockState.is(Blocks.SOUL_CAMPFIRE);
    }

    private boolean isTorches(BlockState blockState) {
        return blockState.is(Blocks.TORCH)
                || blockState.is(Blocks.SOUL_TORCH)
                || blockState.is(Blocks.COPPER_TORCH)
                || blockState.is(Blocks.WALL_TORCH)
                || blockState.is(Blocks.SOUL_WALL_TORCH)
                || blockState.is(Blocks.COPPER_WALL_TORCH);
    }

    private boolean isFireblocks(BlockState blockState) {
        return blockState.is(Blocks.FIRE) || blockState.is(Blocks.SOUL_FIRE);
    }

    @Override
    protected void additionalEntityClientSideTick() {
        this.updateHealingAnimation();
    }

    @Override
    protected void addAdditionalEntityServerSideTick(ServerLevel level) {
        this.burnByFireSourceBlocks(level);
    }

    @Override
    protected void handleAbilityUsageAnimation(EntityDataAccessor<?> accessor) {
        if (DATA_USING_ABILITY.equals(accessor)) {
            if (this.level().isClientSide()) {
                this.updateHealingAnimation();
            }
        }
    }

    private void updateHealingAnimation() {
        if (this.isUsingAbility()) {
            this.healAnimationState.startIfStopped(tickCount);
        } else {
            this.healAnimationState.stop();
        }
    }

    private void burnByFireSourceBlocks(ServerLevel level) {
        BlockPos nearestFireSource = EntityUtils.getNearestBlockPos(this, this::anyFireSourceBlock, 5);
        if (nearestFireSource == null) return;

        BlockState fireSourceBlockState = level.getBlockState(nearestFireSource);
        if (this.campFireHurtCoolDown.pass(() -> this.isCampfires(fireSourceBlockState))) {
            this.hurtServer(level, MtsDamageTypes.create(level, MtsDamageTypes.OAKIE_BURN), 0.5F);
            this.campFireHurtCoolDown.reset();

        } else if (this.fireblocksHurtCoolDown.pass(() -> this.isFireblocks(fireSourceBlockState))) {
            this.hurtServer(level, MtsDamageTypes.create(level, MtsDamageTypes.OAKIE_BURN), 1.0F);
            this.fireblocksHurtCoolDown.reset();

        } else if (this.lavaHurtCoolDown.pass(() -> this.isLava(fireSourceBlockState))) {
            this.hurtServer(level, MtsDamageTypes.create(level, MtsDamageTypes.OAKIE_BURN), 2.0F);
            this.lavaHurtCoolDown.reset();

        } else if (this.torchesHurtCoolDown.pass(() -> this.isTorches(fireSourceBlockState))) {
            this.torchesHurtCoolDown.reset();
            if (this.random.nextInt(20) == 0) {
                this.hurtServer(level, MtsDamageTypes.create(level, MtsDamageTypes.OAKIE_BURN), 0.5F);
            }
        }
    }

    private boolean anyFireSourceBlock(BlockState blockState) {
        return this.isFireblocks(blockState) || this.isCampfires(blockState) || this.isLava(blockState);
    }

    @Override
    protected void grantsEffectsToLivingEntitiesInEnchantingRegion(LivingEntity entity) {
        if (entity instanceof OakieEntity oakie) {
            oakie.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 100, 2, false, true, true));

        } else if (entity instanceof Enemy) {
            if (this.getOwner() != null) {
                boolean isEnchanting = this.isEnchanting();
                entity.addEffect(new MobEffectInstance(MtsEffects.LEECH_OF_OAKIE, (isEnchanting ? 150 : 100), (isEnchanting ? 2 : 1), false, true, true));
                this.cursedEntities.add(entity.getUUID());
            }
        }
    }

    @Override
    protected void grantsEffectToPlayersInEnchantingRegion(ServerPlayer player) {
        if (this.isOwnedBy(player) && this.isEnchanting()) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1, false, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 600, 0, false, true, true));

        } else {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0, false, true, true));
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 10/21/2026
     */
    private static class OakieAvoidFireBlockGoal extends AvoidBlockGoal {

        public OakieAvoidFireBlockGoal(PathfinderMob mob, Predicate<BlockState> blockSelector) {
            super(mob, blockSelector, 20, 1.05, 1.55, 10);
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 10/21/2026
     */
    private static class OakieAvoidCampFiresGoal extends AvoidBlockGoal {

        public OakieAvoidCampFiresGoal(PathfinderMob mob, Predicate<BlockState> blockSelector) {
            super(mob, blockSelector, 15, 1.025, 1.525, 8);
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 10/21/2026
     */
    private static class OakieAvoidLavaGoal extends AvoidBlockGoal {

        public OakieAvoidLavaGoal(PathfinderMob mob, Predicate<BlockState> blockSelector) {
            super(mob, blockSelector, 24, 1.075, 1.75, 12);
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 10/20/2026 First version.
     * @since 11/4/2026 Huge abstraction rewrite of this class.
     */
    @SuppressWarnings("DataFlowIssue")
    private class OakieHealOwnerGoal extends WoodlinUseAbilityGoal {

        public OakieHealOwnerGoal(float minNoticeDistance, float maxNoticeDistance) {
            super(minNoticeDistance, maxNoticeDistance);
        }

        @Override
        protected void registerAbilityUsageBehavior() {
            ServerLevel serverLevel = (ServerLevel) OakieEntity.this.level();
            BlockPos pos = OakieEntity.this.blockPosition().offset(
                    -2 + OakieEntity.this.random.nextInt(5),
                    1,
                    -2 + OakieEntity.this.random.nextInt(5)
            );
            OakieHealingBallEntity healingBall = MtsEntityTypes.OAKIE_HEALING_BALL.create(OakieEntity.this.level(), EntitySpawnReason.MOB_SUMMONED);
            if (healingBall != null) {
                healingBall.snapTo(pos, 0.0F, 0.0F);
                healingBall.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(pos), EntitySpawnReason.MOB_SUMMONED, null);
                serverLevel.addFreshEntity(healingBall);
                serverLevel.gameEvent(GameEvent.ENTITY_PLACE, pos, GameEvent.Context.of(OakieEntity.this));
            }
        }

        @Override
        protected boolean customBaseCriteria() {
            double ownerHealth = this.target.getHealth();
            double ownerMaxHealth = this.target.getMaxHealth();
            return ownerHealth <= ownerMaxHealth * 0.8;
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 10/19/2026
     */
    private class OakieGrowCropsGoal extends Goal {

        public static final int SPAWN_HAPPY_VILLAGER_PARTICLE_EVENT = 2011;

        @Override
        public boolean canUse() {
            return OakieEntity.this.isEnchanting();
        }

        @Override
        public void tick() {
            if (OakieEntity.this.random.nextInt(this.adjustedTickDelay(30)) == 0) {
                for (BlockPos candidateBlockPos : getBlockPosInCircle()) {
                    if (this.hasChanceToGrow()) {
                        BlockState state = OakieEntity.this.level().getBlockState(candidateBlockPos);
                        BlockState growState = getCropStateFromPos(candidateBlockPos, state);
                        this.applyGrowCrops(growState, candidateBlockPos);
                    }

                    for (int i = 1; i <= 2; i++) {
                        if (this.hasChanceToGrow()) {
                            // below two blocks
                            BlockPos belowPos = candidateBlockPos.below(i);
                            BlockState belowState = OakieEntity.this.level().getBlockState(belowPos);
                            BlockState growBelowState = getCropStateFromPos(belowPos, belowState);
                            this.applyGrowCrops(growBelowState, belowPos);
                            // above two blocks
                            BlockPos abovePos = candidateBlockPos.above(i);
                            BlockState aboveState = OakieEntity.this.level().getBlockState(abovePos);
                            BlockState growAboveState = getCropStateFromPos(abovePos, aboveState);
                            this.applyGrowCrops(growAboveState, abovePos);
                        }
                    }
                }
            }
        }

        private boolean hasChanceToGrow() {
            return OakieEntity.this.random.nextInt(20) == 0; // 5% of change growing crops around it
        }

        private List<BlockPos> getBlockPosInCircle() {
            return EntityUtils.getBlockPosInCircle(OakieEntity.this, HEALING_RADIUS);
        }

        private void applyGrowCrops(BlockState cropState, BlockPos blockPos) {
            if (cropState != null) {
                OakieEntity.this.level().levelEvent(SPAWN_HAPPY_VILLAGER_PARTICLE_EVENT, blockPos, 15);
                OakieEntity.this.level().setBlockAndUpdate(blockPos, cropState);
            }
        }

        /**
         * I copied this from vanilla bee's {@code BeeGrowCropGoal} class.
         *
         * @param blockPos   position of crop below/above the oakie, as well as in the oakie's effectiveness region.
         * @param blockState state of the crop below/above the oakie, as well as in the oakie's effectiveness region.
         * @return the grown state of the crop below/above the oakie, as well as in the oakie's effectiveness region.
         */
        private BlockState getCropStateFromPos(BlockPos blockPos, BlockState blockState) {
            BlockState growState = null;
            Block block = blockState.getBlock();
            if (blockState.is(MtsTags.Entities.OAKIE_GROWABLE)) {
                if (block instanceof CropBlock cropBlockBelow) {
                    if (!cropBlockBelow.isMaxAge(blockState)) {
                        growState = cropBlockBelow.getStateForAge(cropBlockBelow.getAge(blockState) + 1);
                    }
                } else if (block instanceof StemBlock) {
                    int age = blockState.getValue(StemBlock.AGE);
                    if (age < 7) {
                        growState = blockState.setValue(StemBlock.AGE, age + 1);
                    }
                } else if (blockState.is(Blocks.SWEET_BERRY_BUSH) || blockState.is(MtsBlocks.BLUEBERRY_BUSH)) {
                    int age = blockState.getValue(SweetBerryBushBlock.AGE);
                    if (age < 3) {
                        growState = blockState.setValue(SweetBerryBushBlock.AGE, age + 1);
                    }
                } else if (blockState.is(Blocks.CAVE_VINES) || blockState.is(Blocks.CAVE_VINES_PLANT)) {
                    BonemealableBlock bonemealableBlock = (BonemealableBlock) blockState.getBlock();
                    if (bonemealableBlock.isValidBonemealTarget(OakieEntity.this.level(), blockPos, blockState)) {
                        bonemealableBlock.performBonemeal((ServerLevel) OakieEntity.this.level(), OakieEntity.this.random, blockPos, blockState);
                        growState = OakieEntity.this.level().getBlockState(blockPos);
                    }
                }
            }
            return growState;
        }
    }
}
