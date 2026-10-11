package net.mrgoddavid.minecraftthestoriesmod.utils;

/**
 * This timer has a maximum time. The timer always starts from 0. The timer accumulates numbers if its criteria are
 * always met. Otherwise, the timer will reset the time back to 0. Once the timer meets its maximum time, it never reset
 * to its original stare unless the user manually use the reset method.
 *
 * @author Mr. GodDavid
 * @since 10/10/2026
 */
public class Timer {

    private final int maxTick;
    private int currentTick;

    public Timer(int maxTick) {
        if (maxTick <= 0) {
            throw new IllegalArgumentException("Bad timer initialization: current maxTick: [" + maxTick
                    + "] is less than or greater than 0");
        }
        this.maxTick = maxTick;
        this.currentTick = 0;
    }

    public void reset() {
        this.currentTick = 0;
    }

    private void tick(Criterion criterion) {
        if (criterion.pass()) {
            if (this.currentTick < this.maxTick) {
                this.currentTick++;
            }
        } else {
            this.reset();
        }
    }

    public boolean pass(Criterion criterion) {
        this.tick(criterion);
        return hasReachedMaxTick();
    }

    private boolean hasReachedMaxTick() {
        return this.currentTick >= this.maxTick;
    }

    /**
     * @author Mr. GodDavid
     * @since 10/10/2026
     */
    @FunctionalInterface
    public interface Criterion {

        boolean pass();
    }
}
