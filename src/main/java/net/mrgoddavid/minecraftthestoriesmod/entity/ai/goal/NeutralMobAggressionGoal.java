package net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.mrgoddavid.minecraftthestoriesmod.effect.MtsEffects;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 10/10/2026
 */
public class NeutralMobAggressionGoal extends Goal {

    private static final double TARGET_RANGE = 20.0D;
    private static final double CONTINUE_RANGE = 30.0D;

    private final Mob neutralMob;
    private Player target;

    public NeutralMobAggressionGoal(Mob neutralMob) {
        this.neutralMob = neutralMob;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        List<Player> players = this.neutralMob.level()
                .getEntitiesOfClass(Player.class, this.neutralMob.getBoundingBox().inflate(TARGET_RANGE), this::canPlayerBeAttacked);
        this.target = players.stream()
                .min(Comparator.comparingDouble(this.neutralMob::distanceToSqr)).orElse(null);
        return this.target != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null
                && this.canPlayerBeAttacked(this.target)
                && this.neutralMob.distanceToSqr(this.target) <= CONTINUE_RANGE * CONTINUE_RANGE
                && this.neutralMob.getTarget() == this.target;
    }

    @Override
    public void start() {
        this.neutralMob.setTarget(this.target);
    }

    @Override
    public void stop() {
        if (this.neutralMob.getTarget() == this.target) {
            this.neutralMob.setTarget(null);
        }
        this.target = null;
    }

    private boolean canPlayerBeAttacked(Player player) {
        return player.isAlive()
                && !player.isSpectator()
                && !player.isCreative()
                && player.hasEffect(MtsEffects.PUBLIC_ENEMY)
                && this.neutralMob.distanceToSqr(player) <= TARGET_RANGE * TARGET_RANGE;
    }
}
