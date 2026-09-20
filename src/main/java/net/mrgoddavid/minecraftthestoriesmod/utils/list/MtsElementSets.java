package net.mrgoddavid.minecraftthestoriesmod.utils.list;

/**
 * @author Mr. GodDavid
 * @since 9/18/2026
 */
public interface MtsElementSets {

    /**
     * A set of two numbers/things is called a Quartet. The two numbers/things are unique from each other.
     *
     * @param first  first nonnull element.
     * @param second  second nonnull element.
     * @param <A> type of the first nonnull element.
     * @param <B> type of the second nonnull element.
     * @author Mr. GodDavid
     * @since 9/18/2026
     */
    record Pair<A, B>(A first, B second) implements MtsElementSets {
    }

    /**
     * A set of three numbers/things is called a Quartet. The three numbers/things are unique from each other.
     *
     * @param first  first nonnull element.
     * @param second  second nonnull element.
     * @param third  third nonnull element.
     * @param <A> type of the first nonnull element.
     * @param <B> type of the second nonnull element.
     * @param <C> type of the third nonnull element.
     * @author Mr. GodDavid
     * @since 9/18/2026
     */
    record Trio<A, B, C>(A first, B second, C third) implements MtsElementSets {
    }

    /**
     * A set of four numbers/things is called a Quartet. The four numbers/things are unique from each other.
     *
     * @param first  first nonnull element.
     * @param second  second nonnull element.
     * @param third  third nonnull element.
     * @param fourth  fourth nonnull element.
     * @param <A> type of the first nonnull element.
     * @param <B> type of the second nonnull element.
     * @param <C> type of the third nonnull element.
     * @param <D> type of the fourth nonnull element.
     * @author Mr. GodDavid
     * @since 9/18/2026
     */
    record Quartet<A, B, C, D>(A first, B second, C third, D fourth) implements MtsElementSets {
    }
}
