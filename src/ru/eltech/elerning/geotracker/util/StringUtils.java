package ru.eltech.elerning.geotracker.util;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/19/11
 */
public class StringUtils {
    public static String nvl(final String str, final String defValue){
        return str == null ? defValue : str;
    }

    public static String emptyIfNull(final String str){
        return nvl(str, "");
    }

    public static String nullIfEmpty(final String str){
        return str == null ? null : str.isEmpty() ? null : str;
    }

    public static boolean isEmpty(final String str){
        return str == null || str.trim().isEmpty();
    }
}
