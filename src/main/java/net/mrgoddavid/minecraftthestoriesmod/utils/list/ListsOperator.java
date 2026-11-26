package net.mrgoddavid.minecraftthestoriesmod.utils.list;

import com.google.common.collect.Lists;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ErrorMessageBuilder;
import net.mrgoddavid.minecraftthestoriesmod.utils.log.ThrowReason;

import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 11/25/2026
 */
public final class ListsOperator {

    public static <T> List<T> addition(List<T> list1, List<T> list2) {
        List<T> sum = Lists.newArrayList(list1);
        sum.addAll(list2);
        return sum;
    }

    private ListsOperator() {
        ErrorMessageBuilder.infoAndThrow(this.getClass(), ThrowReason.INSTANTIATE_UTIL_CLASS);
    }
}
