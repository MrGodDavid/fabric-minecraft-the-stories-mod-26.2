package net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.mrgoddavid.minecraftthestoriesmod.effect.MtsEffects;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 10/9/2026
 */
public class AnimalAggressionTargetGoal extends Goal {

    private static final double TARGET_RANGE = 16.0D;
    private static final double CONTINUE_RANGE = 24.0D;

    private final Animal animal;
    private Player target;

    public AnimalAggressionTargetGoal(Animal animal) {
        this.animal = animal;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        List<Player> players = this.animal.level().getEntitiesOfClass(Player.class, this.animal.getBoundingBox().inflate(TARGET_RANGE), this::animalAggression);
        this.target = players.stream().min(Comparator.comparingDouble(this.animal::distanceToSqr)).orElse(null);
        return this.target != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null && this.target.isAlive() && animalAggression(this.target) && this.animal.distanceToSqr(this.target) <= CONTINUE_RANGE * CONTINUE_RANGE;
    }

    @Override
    public void start() {
        this.animal.setTarget(this.target);
    }

    @Override
    public void stop() {
        if (this.animal.getTarget() == this.target) {
            this.animal.setTarget(null);
        }
        this.target = null;
    }

    private boolean animalAggression(Player player) {
        return player.isAlive() && !player.isSpectator() && !player.isCreative() && player.hasEffect(MtsEffects.PUBLIC_ENEMY);
    }
}
