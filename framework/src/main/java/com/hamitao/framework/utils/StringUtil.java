package com.hamitao.framework.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @data on 2018/4/25 15:20
 * @describe:
 */

public class StringUtil {

    /**
     * str是否包含str1
     *
     * @param str
     * @param str1
     * @return
     */
    public static boolean isContains(String str, String str1) {
        return str.contains(str1);
    }

    /**
     * str的最后一个字符是否是str1
     *
     * @param str
     * @param str1
     * @return
     */
    public static boolean isEndsWith(String str, String str1) {
        return str.endsWith(str1);
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 如果字符串的前三位是数字则在前面添加000（防止后面再播放的时候对标题进行设置（删除前三位））
     *
     * @param str
     * @return
     */
    public static String cheakTitle(String str) {
        return StringUtil.isNumeric(str.substring(0, 2)) ? ("000" + str) : str;
    }

    /**
     * 字符串是否为null
     *
     * @param str
     * @return
     */
    public static boolean isNULL(String str) {
        return str == null || TextUtils.isEmpty(str);
    }


    public static String getWifiName(String wifiName) {
        return wifiName.replaceAll("\"", "");
    }


    //截取,之前的字符串
    public static String getStringBefore(String before) {
        return before.substring(0, before.indexOf(","));
    }

    //截取,之后的字符串
    public static String getStringAfter(String after) {
        return after.substring(after.indexOf(",") + 1);
    }

    //去除最后一个字符
    public static String substringLast(String s) {
        return s.substring(0, s.length() - 1);
    }

    /**
     * TextView自动换行文字，排版混乱的解决方法
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public static String transport(String inputStr) {
        char arr[] = inputStr.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                arr[i] = '\u3000';
            } else if (arr[i] < '\177') {
                arr[i] = (char) (arr[i] + 65248);
            }

        }
        return new String(arr);
    }

    /**
     *  
     *      * 去除特殊字符或将所有中文标号替换为英文标号 
     *      *  
     *      * @param str 
     *      * @return 
     *      
     */
    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]").
                replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号  
        String regEx = "[『』]"; // 清除掉特殊字符  
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
