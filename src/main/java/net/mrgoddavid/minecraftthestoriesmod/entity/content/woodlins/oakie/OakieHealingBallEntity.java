package net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.oakie;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.mrgoddavid.minecraftthestoriesmod.entity.content.woodlins.AbstractWoodlinAbilityEntity;
import net.mrgoddavid.minecraftthestoriesmod.event.thirst.ThirstHolder;
import net.mrgoddavid.minecraftthestoriesmod.networking.manager.ThirstManager;
import net.mrgoddavid.minecraftthestoriesmod.utils.mod.client.EntityUtils;

/**
 * @author Mr. GodDavid
 * @since 10/25/2026
 */
@SuppressWarnings({"NullableProblems", "FieldCanBeLocal", "SpellCheckingInspection"})
public class OakieHealingBallEntity extends AbstractWoodlinAbilityEntity {

    public static final int FOOD_RESTORATION = 2;
    public static final int HYDRATION_RESTORATION = 1;
    private static final int HEAL_AMOUNT = 2;
    public static final float THIRST_EXHAUSTION = -0.25F;
    private static final float HEAL_DISTANCE = 1.5F;
    private static final float HEAL_DISTANCE_SQUARED = HEAL_DISTANCE * HEAL_DISTANCE;

    public OakieHealingBallEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier createMtsEntityAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.FLYING_SPEED, 0.4)
                .build();
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, level);
        flyingPathNavigation.setCanOpenDoors(false);
        flyingPathNavigation.setCanFloat(true);
        flyingPathNavigation.setRequiredPathLength(24.0F);
        return flyingPathNavigation;
    }

    @Override
    protected void tryUseAbilityToNearbyPlayer() {
        EntityUtils.getNearestLivingEntity((ServerLevel) this.level(), this, Player.class, this::selectAroundPlayer, HEAL_DISTANCE).ifPresent(player -> {
            ThirstManager thirstManager = ((ThirstHolder) player).mts$getThirstManager();
            this.grantPositiveBuffs(player, thirstManager);
            this.beginDying();
        });
    }

    private boolean selectAroundPlayer(Player player) {
        return this.validPlayerBaseCriteria(player) && this.distanceToSqr(player) < HEAL_DISTANCE_SQUARED && this.needHealPlayer(player);
    }

    private boolean needHealPlayer(Player player) {
        ThirstManager thirstManager = ((ThirstHolder) player).mts$getThirstManager();
        return (player.getHealth() < player.getMaxHealth() || player.getFoodData().needsFood() || !thirstManager.isHydrationFull());
    }

    private void grantPositiveBuffs(Player player, ThirstManager thirstManager) {
        player.getFoodData().eat(FOOD_RESTORATION, 1);
        thirstManager.restoreThirst(HYDRATION_RESTORATION, THIRST_EXHAUSTION);
        player.heal(HEAL_AMOUNT);
    }
}
