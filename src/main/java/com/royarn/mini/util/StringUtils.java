package com.royarn.mini.util;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/7/12 15:35
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
