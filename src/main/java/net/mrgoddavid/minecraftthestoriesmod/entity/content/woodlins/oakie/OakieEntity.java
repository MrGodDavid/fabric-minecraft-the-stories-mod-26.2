package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 10/16/2026
 */
public class OakieEntity extends TamableAnimal {

    private final byte STAND_EVENT = 99;
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState healAnimationState = new AnimationState();
    public final AnimationState enchantingAnimationState = new AnimationState();
    public final AnimationState disenchantingAnimationState = new AnimationState();
    public final AnimationState tameAnimationState = new AnimationState();
    private boolean pendingSitAnimation;

    public OakieEntity(EntityType<? extends OakieEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TamableAnimalPanicGoal(1.25, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createCubeAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.FOLLOW_RANGE, 32);
    }

    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        this.setInSittingPose(orderedToSit);

        this.jumping = false;
        this.navigation.stop();
        setTarget(null);

        if (!orderedToSit && !this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, STAND_EVENT);
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        if (DATA_FLAGS_ID.equals(accessor)) {
            if (isInSittingPose()) {
                if (this.tameAnimationState.isStarted()) {
                    this.pendingSitAnimation = true;
                    this.enchantingAnimationState.stop();
                } else {
                    this.enchantingAnimationState.startIfStopped(tickCount);
                }
            } else {
                this.pendingSitAnimation = false;
                this.enchantingAnimationState.stop();
            }
        }
        super.onSyncedDataUpdated(accessor);
    }

    @Override
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);

        if (id == EntityEvent.TAMING_SUCCEEDED) {
            this.tameAnimationState.start(this.tickCount);
        }
        if (id == STAND_EVENT) {
            this.disenchantingAnimationState.start(this.tickCount);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (level().isClientSide()) {
            this.idleAnimationState.animateWhen(!isInWater() && !this.walkAnimation.isMoving(), this.tickCount);

            if (this.tameAnimationState.isStarted() && this.tameAnimationState.getTimeInMillis(this.tickCount) > 1000L) {
                this.tameAnimationState.stop();
                if (pendingSitAnimation) {
                    this.enchantingAnimationState.startIfStopped(this.tickCount);
                    this.pendingSitAnimation = false;
                }
            }

            if (this.enchantingAnimationState.isStarted()
                    && this.enchantingAnimationState.getTimeInMillis(this.tickCount) > 1000L) {
                this.enchantingAnimationState.stop();
            }

            if (this.disenchantingAnimationState.isStarted() && this.disenchantingAnimationState.getTimeInMillis(this.tickCount) > 1000L) {
                this.disenchantingAnimationState.stop();
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        boolean isTameItemCorrect = itemStack.is(Items.OAK_SAPLING);

        if (!this.level().isClientSide()) {
            if (!isBaby()) {
                // taming
                if (!isTame() && isTameItemCorrect) {
                    itemStack.consume(1, player);
                    tryToTame(player);
                    return InteractionResult.SUCCESS;
                }

                // performing actions once it is tamed by player.
                if (isTame() && isOwnedBy(player)) {
                    // healing
                    if (itemStack.is(Items.OAK_LEAVES) && this.getHealth() < this.getMaxHealth()) {
                        this.feed(player, hand, itemStack, 2.0F, 2.0F);
                        this.spawnHealingParticles();
                        return InteractionResult.SUCCESS;
                    }

                    // enchant & disenchant
                    if (itemStack.is(Items.STICK)) {
                        System.out.println("HERE");
                        setOrderedToSit(!isOrderedToSit());
                        return InteractionResult.SUCCESS;
                    }

                    InteractionResult result = super.mobInteract(player, hand);
                    if (result.consumesAction()) {
                        return result;
                    }
                }
            }
        }

        return isOwnedBy(player) || isTame() || isTameItemCorrect ? InteractionResult.CONSUME : InteractionResult.PASS;
    }

    private void tryToTame(Player player) {
        if (this.random.nextInt(10) == 0) {
            tame(player);
            setOrderedToSit(true);
            level().broadcastEntityEvent(this, EntityEvent.TAMING_SUCCEEDED);
        } else {
            this.spawnTamingFailedParticles();
        }
    }

    private void spawnHealingParticles() {
        ((ServerLevel) this.level()).sendParticles(ParticleTypes.HEART, this.getX(), this.getY() + this.getBbHeight() + 0.5d, this.getZ(), 2, 0.3d, 0.3d, 0.3d, 0.0);
    }

    private void spawnTamingFailedParticles() {
        ((ServerLevel) this.level()).sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + this.getBbHeight() + 0.5d, this.getZ(), 2, 0.3d, 0.3d, 0.3d, 0.0);
    }
}
