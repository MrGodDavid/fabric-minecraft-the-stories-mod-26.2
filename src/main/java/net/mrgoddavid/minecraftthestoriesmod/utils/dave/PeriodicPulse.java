package net.mrgoddavid.minecraftthestoriesmod.utils.dave;

/**
 * @author Mr. GodDavid
 * @since 10/9/2026
 */
public final class PeriodicPulse {

    private int tickCounter;
    private final int maxTick;

    private PeriodicPulse(Builder builder) {
        this.tickCounter = builder.tickCounter;
        this.maxTick = builder.maxTick;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void tick() {
        if (tickCounter < maxTick) {
            tickCounter++;
        }
    }

    public boolean fire(Criterion criterion) {
        return criterion.fire(this.tickCounter);
    }

    public void reset() {
        tickCounter = 0;
    }

    public int getMaxTick() {
        return maxTick;
    }

    /**
     * @author Mr. GodDavid
     * @since 10/9/2026
     */
    public static final class Builder {

        private int tickCounter = 0;
        private int maxTick = 10;

        public Builder startFrom(int tickCounter) {
            if (tickCounter < 0) {
                throw new IllegalArgumentException("Starting tick cannot be negative! Current tick is " + tickCounter);
            }
            this.tickCounter = tickCounter;
            return this;
        }

        public Builder maxTick(int maxTick) {
            this.maxTick = maxTick;
            return this;
        }

        public PeriodicPulse build() {
            return new PeriodicPulse(this);
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 10/9/2026
     */
    @FunctionalInterface
    public interface Criterion {

        boolean fire(int tickCounter);
    }
}
