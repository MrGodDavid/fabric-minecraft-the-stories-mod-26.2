package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.acaciania;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.effect.MtsEffects;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinEntity;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.WoodlinContext;
import net.mrgoddavid.minecraftthestoriesmod.item.MtsItems;
import net.mrgoddavid.minecraftthestoriesmod.utils.list.MtsElementSets;

/**
 * <p>Acaciania is the spirit of the Wild Acacia. Born as the representative of the endless wildness, Acaciania
 * grants swiftness to its master. It grants slowness to the surrounding mobs.</p>
 * <p>Acaciania follows its master in a smaller range since it runs faster than most of the other woodlins.</p>
 * <p>When Acaciania is not tamed, It grants player slowness as well. Acaciania curses every harmful living entity
 * who arrogantly hurt it. The Plodding of Acaciania is the punishment of this behavior. Plodding of Acaciania is a
 * curse that you move slower the longer you move. Normally, you cannot escape from any mob in the world if you have
 * this effect. There are talismans or artifacts to remove this effect, so you'd better not harm this cute Acaciania!
 * :)</p>
 *
 * @author Mr. GodDavid
 * @since 11/6/2026
 */
@SuppressWarnings("SpellCheckingInspection")
public class AcacianiaEntity extends AbstractWoodlinEntity {

    public static final int SPEED_BOOST_RADIUS = 5;
    public final AnimationState hydrateAnimationState = new AnimationState();

    public AcacianiaEntity(EntityType<AcacianiaEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createMtsEntityAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.MOVEMENT_SPEED, 0.20004D)
                .add(Attributes.FOLLOW_RANGE, 24);
    }

    @Override
    protected MtsElementSets.Trio<Item, Item, Item> registerWoodlinInteractableItems() {
        return new MtsElementSets.Trio<>(Items.ACACIA_SAPLING, Items.ACACIA_LEAVES, MtsItems.ACACIA_STICK);
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
                .woodlinAbilityUsageRadius(SPEED_BOOST_RADIUS)
                .tamedAnimalPanicSpeedModifier(1.25D)
                .tamedAnimalFollowOwnerSpeedModifier(1.025D)
                .tamedAnimalFollowOwnerStartDistance(24.0F)
                .tamedAnimalFollowOwnerStopDistance(2.0F)
                .entityRandomlyLookDistance(4.0F)
                .build();
    }

    @Override
    protected void handleAbilityUsageAnimation(EntityDataAccessor<?> accessor) {
        if (DATA_USING_ABILITY.equals(accessor)) {
            if (this.level().isClientSide()) {
                this.updateHydrateAnimationState();
            }
        }
    }

    @Override
    protected void grantsEffectsToLivingEntitiesInEnchantingRegion(LivingEntity entity) {
        if (entity instanceof Enemy) {
            boolean isEnchanting = this.isEnchanting();
            entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, (isEnchanting ? 160 : 100), (isEnchanting ? 2 : 1), false, true, true));
            this.cursedEntities.add(entity.getUUID());

        } else if (entity instanceof AcacianiaEntity acaciania) {
            acaciania.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 160, 1, false, true, true));
        }
    }

    @Override
    protected void grantsEffectToPlayersInEnchantingRegion(ServerPlayer player) {
        if (this.isOwnedBy(player) && this.isEnchanting()) {
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 600, 1, false, true, true));
            player.addEffect(new MobEffectInstance(MtsEffects.ENERGETIC, 600, 1, false, true, true));

        } else {
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 600, 1, false, true, true));
        }
    }

    @Override
    protected void additionalEntityClientSideTick() {
        this.updateHydrateAnimationState();
    }

    private void updateHydrateAnimationState() {
        if (this.isUsingAbility()) {
            this.hydrateAnimationState.startIfStopped(tickCount);
        } else {
            this.hydrateAnimationState.stop();
        }
    }

    @Override
    protected void addAdditionalEntityServerSideTick(ServerLevel level) {
    }


}
