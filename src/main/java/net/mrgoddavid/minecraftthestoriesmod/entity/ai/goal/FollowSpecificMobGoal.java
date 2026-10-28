package net.mrgoddavid.minecraftthestoriesmod.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import org.jspecify.annotations.Nullable;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

/**
 * @param <T> type of the living entity to follow.
 * @author Mr. GodDavid
 * @since 10/27/2026
 */
public class FollowSpecificMobGoal<T extends LivingEntity> extends Goal {

    private final Mob mob;
    private final Class<T> followClass;
    private final double speedModifier;
    private final float followRange;
    private final float stopDistance;
    private final PathNavigation navigation;
    private final Predicate<T> followMobPredicate;
    private @Nullable T followMob;
    private int timeToRecalcPath;

    public FollowSpecificMobGoal(final Mob mob, final Class<T> followClass, final double speedModifier, final float followRange, final float stopDistance) {
        this(mob, followClass, speedModifier, followRange, stopDistance, candidate -> true);
    }

    public FollowSpecificMobGoal(final Mob mob, final Class<T> followClass, final double speedModifier, final float followRange, final float stopDistance, final Predicate<T> followMobPredicate) {
        this.mob = mob;
        this.followClass = followClass;
        this.speedModifier = speedModifier;
        this.followRange = followRange;
        this.stopDistance = stopDistance;
        this.followMobPredicate = followMobPredicate;
        this.navigation = this.mob.getNavigation();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE,  Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        List<T> mobs = this.mob.level().getEntitiesOfClass(
                this.followClass, this.mob.getBoundingBox().inflate(this.followRange),
                target -> this.followMobPredicate.test(target) && target.isAlive() && !target.isInvisible() && target != this.mob);
        this.followMob = mobs.stream().min(Comparator.comparingDouble(this.mob::distanceToSqr)).orElse(null);
        return this.followMob != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.followMob != null && this.followMob.isAlive() && !this.followMob.isInvisible()
                && this.mob.distanceToSqr(this.followMob) > this.stopDistance * this.stopDistance;
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
    }

    @Override
    public void stop() {
        this.followMob = null;
        this.navigation.stop();
    }

    @Override
    public void tick() {
        if (this.followMob != null || this.mob.isLeashed()) return;
        this.mob.getLookControl().setLookAt(this.followMob, 10.0F, this.mob.getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            if (this.mob.distanceToSqr(this.followMob) > this.stopDistance * this.stopDistance) {
                this.navigation.moveTo(this.followMob, this.speedModifier);
            } else {
                this.navigation.stop();
            }
        }
    }
}
