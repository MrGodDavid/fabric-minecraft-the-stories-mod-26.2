package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.birchia;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.WoodlinContext;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.utils.list.MtsElementSets;

/**
 * @author Mr. GodDavid
 * @since 11/15/2026
 */
@SuppressWarnings("SpellCheckingInspection")
public class BirchiaEntity extends AbstractWoodlinEntity {

    public static final double JUMP_BOOSE_RADIUS = 15.0D;
    public final AnimationState jumpBoostAnimationState = new AnimationState();

    public BirchiaEntity(EntityType<BirchiaEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createMtsEntityAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.MOVEMENT_SPEED, 0.20004D)
                .add(Attributes.FOLLOW_RANGE, 24);
    }

    /**
     * First one is item for taming woodlines; the second is used for healing; the third one is used for
     * enchanting/disenchanting.
     */
    @Override
    protected MtsElementSets.Trio<Item, Item, Item> registerWoodlinInteractableItems() {
        return super.woodlinInteractableItemsBuilder()
                .tame(Items.BIRCH_SAPLING)
                .healing(Items.BIRCH_LEAVES)
                .enchantmentToggler(MtsItems.BIRCH_STICK)
                .build();
    }

    /**
     * Be aware of registering the goals.
     * <pre>{@code
     * GOAL_NAME                        PRIORITY
     * FloatGoal                            0
     * TamableAnimalPanicGoal               1
     * SitWhenOrderedToGoal                 3
     * FollowOwnerGoal                      4
     * LookAtPlayerGoal                     7
     * RandomLookAroundGoal                 7
     * }
     * </pre>
     */
    @Override
    protected void registerBehavioralGoals() {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    protected WoodlinContext registerWoodlinContexts() {
        return WoodlinContext.builder()
                .woodlinAbilityUsageRadius(JUMP_BOOSE_RADIUS)
                .tamedAnimalPanicSpeedModifier(1.25D)
                .tamedAnimalFollowOwnerSpeedModifier(1.025D)
                .tamedAnimalFollowOwnerStartDistance(24.0F)
                .tamedAnimalFollowOwnerStopDistance(2.0F)
                .entityRandomlyLookDistance(4.0F)
                .woodlinForbiddenBiomeFleeSafeDistance(32.0F)
                .woodlinForbiddenBiomeFleeingWalkSpeedModifier(1.25D)
                .woodlinForbiddenBiomeFleeingSprintSpeedModifier(2.5D)
                .build();
    }

    @Override
    protected void handleAbilityUsageAnimation(EntityDataAccessor<?> accessor) {
        if (DATA_USING_ABILITY.equals(accessor)) {
            if (this.level().isClientSide()) {
                super.updateAbilityUsageAnimationHelper(this.jumpBoostAnimationState);
            }
        }
    }

    @Override
    protected void grantsEffectsToLivingEntitiesInEnchantingRegion(LivingEntity entity) {

    }

    @Override
    protected void grantsEffectToPlayersInEnchantingRegion(ServerPlayer player) {

    }

    @Override
    protected void additionalEntityClientSideTick() {
        super.updateAbilityUsageAnimationHelper(this.jumpBoostAnimationState);
    }

    @Override
    protected void addAdditionalEntityServerSideTick(ServerLevel level) {

    }
}
