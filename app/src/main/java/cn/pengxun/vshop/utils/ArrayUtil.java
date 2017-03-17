package cn.pengxun.vshop.utils;

import java.util.List;

/**
 * @author liu-feng
 * @date 2017/2/22 0022.
 * Email:w710989327@foxmail.com
 */
public class ArrayUtil {

    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
}
