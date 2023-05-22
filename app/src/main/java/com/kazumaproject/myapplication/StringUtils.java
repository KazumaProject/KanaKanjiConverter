package com.kazumaproject.myapplication;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    /**
     * 文字列を指定された文字列で分割します。
     *
     * @param str          分割対象の文字列
     * @param delimiter    分割する文字列
     * @return 分割された文字列配列
     */
    public static String[] split(String str, String delimiter) {
        return split(str, delimiter, 0);
    }

    /**
     * 文字列を指定された文字列で分割します。
     *
     * @param str          分割対象の文字列
     * @param delimiter    分割する文字列
     * @param limit        最大分割数 (0を指定した場合は可能な限りすべて分割し、負の値を指定した場合は文字列の後方から分割します)
     * @return 分割された文字列配列
     */
    public static String[] split(String str, String delimiter, int limit) {
        List<String> result = new ArrayList<String>();
        int delimiterLen = delimiter.length();
        if (limit == 0) limit = str.length();

        if (limit > 0) {
            int start = 0;
            int end = 0;
            for (int i = 1; i < limit; i++) {
                end = str.indexOf(delimiter, start);
                if (end < 0) break;
                result.add(str.substring(start, end));
                start = end + delimiterLen;
            }
            result.add(str.substring(start));

        } else {
            int start = 0;
            int end = str.length();
            for (int i = -1; i > limit; i--) {
                start = str.lastIndexOf(delimiter, end - 1);
                if (start < 0) break;
                result.add(str.substring(start + delimiterLen, end));
                end = start;
            }
            result.add(str.substring(0, end));
        }
        return result.toArray(new String[0]);
    }

    /**
     * 文字列を指定された文字列でint型配列に分割します。
     *
     * @param str          分割対象の文字列
     * @param delimiter    分割する文字列
     * @return 分割されたint型配列
     */
    public static int[] splitToInt(String str, String delimiter) {
        return splitToInt(str, delimiter, 10);
    }

    /**
     * 文字列を指定された文字列でint型配列に分割します。
     * 基数を指定することで10進数以外の数値文字列も分割できます。
     *
     * @param str          分割対象の文字列
     * @param delimiter    分割する文字列
     * @param radix        基数
     * @return 分割されたint型配列
     */
    public static int[] splitToInt(String str, String delimiter, int radix) {
        String[] strs = split(str, delimiter);
        int[] result = new int[strs.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strs[i], radix);
        }
        return result;
    }
}
