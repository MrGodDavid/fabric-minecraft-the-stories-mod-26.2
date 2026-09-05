package net.mrgoddavid.minecraftthestoriesmod.item.content;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Custom MTS bow item.
 *
 * @author Mr. GodDavid
 * @since 9/4/2026
 */
@SuppressWarnings("NullableProblems")
public class MtsBowItem extends BowItem {

    private final Attribute attribute;

    public MtsBowItem(Properties properties, Attribute attribute) {
        super(properties);
        this.attribute = attribute;
    }

    public float getChargeDuration() {
        return this.attribute.chargeDuration();
    }

    @Override
    protected void shootProjectile(LivingEntity shooter, Projectile projectileEntity, int index, float power, float uncertainty, float angle, @Nullable LivingEntity targetOverrride) {
        super.shootProjectile(shooter, projectileEntity, index, power, this.attribute.uncertainty(), angle, targetOverrride);
    }

    @Override
    protected Projectile createProjectile(Level level, LivingEntity shooter, ItemStack weapon, ItemStack projectile, boolean isCrit) {
        ArrowItem arrowItem = projectile.getItem() instanceof ArrowItem arrow ? arrow : (ArrowItem) Items.ARROW;
        AbstractArrow arrow = arrowItem.createArrow(level, projectile, shooter, weapon);
        if (isCrit) {
            arrow.setCritArrow(true);
        }

        arrow.setBaseDamage(this.attribute.damage());
        return arrow;
    }

    private float calculatePow(final int timeHeld) {
        float pow = timeHeld / this.attribute.chargeDuration(); // changed this line.
        pow = (pow * pow + pow * 2.0F) / 3.0F;
        if (pow > 1.0F) {
            pow = 1.0F;
        }

        return pow;
    }

    /**
     * Basically from {@link BowItem#releaseUsing(ItemStack, Level, LivingEntity, int)} method.
     */
    @Override
    public boolean releaseUsing(final ItemStack itemStack, final Level level, final LivingEntity entity, final int remainingTime) {
        if (entity instanceof Player player) {
            ItemStack projectile = player.getProjectile(itemStack);
            if (projectile.isEmpty()) {
                return false;
            }

            int timeHeld = super.getUseDuration(itemStack, entity) - remainingTime;
            float pow = this.calculatePow(timeHeld); // changed this line.
            if (pow < 0.1) {
                return false;
            }

            List<ItemStack> firedProjectiles = draw(itemStack, projectile, player);
            if (level instanceof ServerLevel serverLevel && !firedProjectiles.isEmpty()) {
                this.shoot(serverLevel, player, player.getUsedItemHand(), itemStack, firedProjectiles, pow * 3.0F, 1.0F, pow == 1.0F, null);
            }

            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.ARROW_SHOOT,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + pow * 0.5F
            );
            player.awardStat(Stats.ITEM_USED.get(this));
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 9/4/2026
     */
    public static class Attribute {

        private final float damage;
        private final float chargeDuration;
        private final float uncertainty;

        private Attribute(float damage, float chargeDuration, float uncertainty) {
            this.damage = damage;
            this.chargeDuration = chargeDuration;
            this.uncertainty = uncertainty;
        }

        public float chargeDuration() {
            return chargeDuration;
        }

        public float damage() {
            return damage;
        }

        public float uncertainty() {
            return uncertainty;
        }

        /**
         * @author Mr. GodDavid
         * @since 9/4/2026
         */
        public static class Builder {
            private float damage;
            private float chargeDuration;
            private float uncertainty;

            public Builder damage(float damage) {
                this.damage = damage;
                return this;
            }

            public Builder chargeDuration(float chargeDuration) {
                this.chargeDuration = chargeDuration;
                return this;
            }

            public Builder uncertainty(float uncertainty) {
                this.uncertainty = uncertainty;
                return this;
            }

            public Attribute build() {
                return new Attribute(this.damage, this.chargeDuration, this.uncertainty);
            }
        }
    }
}
