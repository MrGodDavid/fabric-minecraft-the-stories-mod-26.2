package net.mrgoddavid.minecraftthestoriesmod.utils.list;

import net.minecraft.core.NonNullList;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 9/18/2028
 */
public class MtsNonnullElementSetLists {

    /**
     * @author Mr. GodDavid
     * @since 9/18/2028
     */
    public static class NonnullPairList<A, B> extends NonNullList<MtsElementSets.Pair<A, B>> {

        public NonnullPairList(List<MtsElementSets.Pair<A, B>> list, MtsElementSets.Pair<A, B> defaultValue) {
            super(list, defaultValue);
        }

        public void add(A e1, B e2) {
            super.add(new MtsElementSets.Pair<>(e1, e2));
        }

        @SafeVarargs
        public static <A, B> NonnullPairList<A, B> of (final MtsElementSets.Pair<A, B> defaultValue, MtsElementSets.Pair<A, B>... elements) {
            return new NonnullPairList<>(Arrays.asList(elements), defaultValue);
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 9/18/2028
     */
    public static class NonnullTrioList<A, B, C> extends NonNullList<MtsElementSets.Trio<A, B, C>> {

        public NonnullTrioList(List<MtsElementSets.Trio<A, B, C>> list, MtsElementSets.Trio<A, B, C> defaultValue) {
            super(list, defaultValue);
        }

        public void add(A e1, B e2, C e3) {
            super.add(new MtsElementSets.Trio<>(e1, e2, e3));
        }

        @SafeVarargs
        public static <A, B, C> NonnullTrioList<A, B, C> of (final MtsElementSets.Trio<A, B, C> defaultValue, MtsElementSets.Trio<A, B, C>... elements) {
            return new NonnullTrioList<>(Arrays.asList(elements), defaultValue);
        }
    }

    /**
     * @author Mr. GodDavid
     * @since 9/18/2028
     */
    public static class NonnullQuartetList<A, B, C, D> extends NonNullList<MtsElementSets.Quartet<A, B, C, D>> {

        public NonnullQuartetList(List<MtsElementSets.Quartet<A, B, C, D>> list, MtsElementSets.Quartet<A, B, C, D> defaultValue) {
            super(list, defaultValue);
        }

        public void add(A e1, B e2, C e3, D e4) {
            super.add(new MtsElementSets.Quartet<>(e1, e2, e3, e4));
        }

        @SafeVarargs
        public static <A, B, C, D> NonnullQuartetList<A, B, C, D> of(final MtsElementSets.Quartet<A, B, C, D> defaultValue, final MtsElementSets.Quartet<A, B, C, D>... elements) {
            return new NonnullQuartetList<>(Arrays.asList(elements), defaultValue);
        }
    }
}
