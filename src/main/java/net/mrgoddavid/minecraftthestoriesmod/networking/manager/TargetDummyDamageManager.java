package net.mrgoddavid.minecraftthestoriesmod.networking.manager;

import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author Mr. GodDavid
 * @since 9/26/2026
 */
public class TargetDummyDamageManager {

    private static final List<TargetDummyDamageNumber> DAMAGE_NUMBERS = new ArrayList<>();

    public static void addDamage(Entity entity, float damage) {
        DAMAGE_NUMBERS.add(new TargetDummyDamageNumber(entity.getY() + entity.getBbHeight(), damage));
    }

    public static void tick() {
        Iterator<TargetDummyDamageNumber> iterator = DAMAGE_NUMBERS.iterator();
        while (iterator.hasNext()) {
            TargetDummyDamageNumber damageNumber = iterator.next();
            damageNumber.tick();
            if (damageNumber.isExpired()) {
                iterator.remove();
            }
        }
    }

    public static List<TargetDummyDamageNumber> getDamageNumbers() {
        return DAMAGE_NUMBERS;
    }

    private TargetDummyDamageManager() {
    }

    /**
     * @author Mr. GodDavid
     * @since 9/26/2026
     */
    public static final class TargetDummyDamageNumber {

        private static final int MAX_AGE = 30;
        private static final double RISE_SPEED = 0.025D;

        private final double y;
        private final float damage;
        private final double xOffset, zOffset;
        private int age;

        public TargetDummyDamageNumber(double y, float damage) {
            this.y = y;
            this.damage = damage;
            this.age = 0;
            Random random = new Random();
            this.xOffset = random.nextDouble(-1.0, 1.0);
            this.zOffset = random.nextDouble(-1.0, 1.0);
        }

        public void tick() {
            this.age++;
        }

        public boolean isExpired() {
            return this.age >= MAX_AGE;
        }

        public double getY() {
            return this.y + age * RISE_SPEED;
        }

        public float getDamage() {
            return this.damage;
        }

        public float getAlpha() {
            return 1.0F - (float) this.age / MAX_AGE;
        }

        public double getXOffset() {
            return this.xOffset;
        }
        public double getZOffset() {
            return this.zOffset;
        }
    }
}
