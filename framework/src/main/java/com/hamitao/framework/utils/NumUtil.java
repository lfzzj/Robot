package com.hamitao.framework.utils;

import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @data on 2018/5/5 15:16
 * @describe:
 */

public class NumUtil {


    /**
     * 获取随机生成数
     */
    public static int getRandomNum(int length) {
        Random random = new Random();
        return random.nextInt(length);
    }

    /**
     * 获取一个从1000到9999的随机数
     * @return
     */
    public static int getForeRandomNum() {
        Random ne = new Random();
        return ne.nextInt(9999 - 1000 + 1) + 1000;//为变量赋随机值1000-9999
    }

    /**
     * 在规定的区域内随机产生一个小时
     */
    public static String getRandomTime(int length) {
        Random random = new Random();
        return "0" + random.nextInt(length);
    }

    /**
     * 移除指定的标点符号
     *
     * @param s
     * @return
     */
    public static String removeFormat(String s) {
//        String str=s.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
        String str = s.replaceAll("[。]", "");
        return str;
    }

    /**
     * 将paramValue中的汉字提取出来
     *
     * @param paramValue
     * @return
     */
    public static String getChinese(String paramValue) {
        String str = "";
        String regex = "([\u4e00-\u9fa5]+)";
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str += matcher.group(0);
        }
        return str;
    }

    public static String getdecodeUrl(String url) {
        String gradeChineseStr = getChinese(url);
        String[] arr = new String[gradeChineseStr.length()];
        String decodeSrc = url;
        for (int i = 0; i < gradeChineseStr.length(); i++) {
            arr[i] = gradeChineseStr.substring(i, i + 1);
            String gradeStr = URLEncoder.encode(arr[i]);
            decodeSrc = decodeSrc.replace(arr[i], gradeStr);
        }
        return decodeSrc;
    }

    /**
     * 删除前几位数字
     *
     * @return
     */
    /**
     * 删除前几位数字
     *
     * @return
     */
    public static String deleteNumber(String str) {
        if (Pattern.matches("^\\d{3}.*", str)) {
            return str.replaceFirst("\\d{3}", "");
        } else {
            return str;
        }
    }


}
