package net.mrgoddavid.minecraftthestoriesmod.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.util.RandomSource;

/**
 * Gives random pulse in an infinite while loop. Useful when randomly play a cycle of animation.
 *
 * @author Mr. GodDavid
 * @since 9/18/2026
 */
public final class RandomPulse {

    private static final RandomSource random;

    static {
        MouseHandler mouseHandler = Minecraft.getInstance().mouseHandler;
        long seed = (long) (mouseHandler.xpos() * 37 + mouseHandler.ypos() * 13);
        random = RandomSource.create(seed);
    }

    private final int cycle;
    private final int origin;
    private final int bound;
    private final int probability;
    private int tick;
    private boolean lock;
    private int randomTick;

    private RandomPulse(Builder builder) {
        this.cycle = builder.cycle;
        this.origin = builder.origin;
        this.bound = builder.bound;
        this.probability = builder.probability;

        this.randomTick = 0;
        this.tick = 0;
        this.lock = false;
    }

    public static Builder builder(int cycle) {
        return new Builder(cycle);
    }

    public void tick() {
        randomTick = random.nextInt(origin, bound);
        if (tick < cycle) {
            tick++;
        } else if (tick == cycle) {
            // unlock
            if (randomTick % probability == 0) {
                lock = false;
                tick = 0;
            }
            // lock
            else {
                lock = true;
            }
        }
    }

    public boolean isLock() {
        return lock;
    }

    public int getTick() {
        return tick;
    }

    public int cycle() {
        return cycle;
    }

    /**
     * @author Mr. GodDavid
     * @since 9/18/2026
     */
    public static final class Builder {

        private final int cycle;
        private int origin;
        private int bound;
        private int probability;

        private Builder(int cycle) {
            this.cycle = cycle;
            origin = 0;
            bound = 10;
            probability = 0;
        }

        public Builder origin(int origin) {
            if (origin < 0) {
                throw new IllegalArgumentException("Origin must be non-negative!");
            }
            this.origin = origin;
            return this;
        }

        public Builder bound(int bound) {
            this.bound = bound;
            return this;
        }

        public Builder probability(int probability) {
            if (probability < 0 || probability > cycle) {
                throw new IllegalArgumentException("probability must be between 0 and cycle: " + cycle);
            }
            this.probability = cycle - probability;
            return this;
        }

        public RandomPulse build() {
            return new RandomPulse(this);
        }
    }
}
