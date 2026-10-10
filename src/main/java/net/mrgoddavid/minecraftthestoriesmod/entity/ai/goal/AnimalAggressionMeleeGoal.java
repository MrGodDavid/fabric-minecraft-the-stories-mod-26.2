package net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.player.Player;
import net.mrgoddavid.minecraftthestoriesmod.effect.MtsEffects;

/**
 * @author Mr. GodDavid
 * @since 10/9/2026
 */
public class AnimalAggressionMeleeGoal extends MeleeAttackGoal {

    private final PathfinderMob mob;

    public AnimalAggressionMeleeGoal(PathfinderMob mob, double speedModifier) {
        super(mob, speedModifier, true);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return this.isValidTarget() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return this.isValidTarget() && super.canContinueToUse();
    }

    private boolean isValidTarget() {
        return this.mob.getTarget() instanceof Player player && animalAggression(player);
    }

    private boolean animalAggression(Player player) {
        return player.isAlive() && !player.isSpectator() && !player.isCreative() && player.hasEffect(MtsEffects.PUBLIC_ENEMY);
    }
}
