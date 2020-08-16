package com.adam.util;

/**
 * @author AdamSun
 * @date 2020/8/15 16:40
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }

    /**
     * 判断是否为非空
     *
     * @param value
     * @return
     */
    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    /**
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }

    /**
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }
}
