package study.daydayup.wolf.common.util.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2020/1/17 9:15 下午
 **/
public class ListUtil {
    public static <E> List<E> empty(){
        return new ArrayList<>();
    }

    public static <E> boolean hasValue(List<E> list) {
        return null != list && !list.isEmpty();
    }

    public static <E> E last(List<E> list) {
        if (!hasValue(list)) {
            return null;
        }

        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }
}