package net.mrgoddavid.minecraftthestoriesmod.utils;

import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

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
            ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.BAD_TIMER_PASS_METHOD_ARGUMENT_CONDITION, maxTick);
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

    /**
     * Checks if the timer has reached the certain criteria. If the parameter is null, we just check if the timer hits
     * its maximum value.
     *
     * @param criterion criterion that cumulate the tick count of this timer.
     * @return true if this timer has reached its maximum tick count.
     */
    public boolean pass(Criterion criterion) {
        if (criterion == null) {
            return this.hasReachedMaxTick();
        }
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
