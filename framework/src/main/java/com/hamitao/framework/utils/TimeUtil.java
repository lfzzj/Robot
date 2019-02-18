package com.hamitao.framework.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateFormat;

import com.hamitao.framework.constant.BaseConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @data on 2018/4/17 14:02
 * @describe:
 */

public class TimeUtil {

    /**
     * 获取设备ID
     *
     * @return
     */
    public static String getDeviceIdFormat() {
        return getCurrentTime("yyyyMMddHHmmssSSS");
    }

    /**
     * 获取当前时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String getCurrentTime() {
        return getCurrentTime("yyyyMMddHHmmss");
    }

    public static String getCourseSchedule() {
        return getCurrentTime("HHmmss");
    }

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String getCurrentData() {
        return getCurrentTime("yyyy-MM-dd HH:mm:ss");
    }

    public static String getTimeByCurrentTime(long currentTime) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateformat.format(currentTime);
    }

    public static String getTime() {
        return getCurrentTime("HH:mm");
    }

    /**
     * 获取yyyy-MM-dd格式的日期
     *
     * @return
     */
    public static String getCurDataInfo() {
        return getCurrentTime("MM月dd日");
    }

    /**
     * 获取当前系统时间
     *
     * @param context
     * @return
     */
    public static String getCurTimeInfo(Context context) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date());
//        if (is24HourFormat(context)) {
//            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
//            return format.format(new Date());
//        } else {
//            SimpleDateFormat format = new SimpleDateFormat("hh:mm");
//            return format.format(new Date());
//        }
    }

    /**
     * 判断时间是否为上午
     *
     * @return
     */
    @SuppressLint("WrongConstant")
    public static boolean isAM() {
        Calendar calendar = Calendar.getInstance();
        return Calendar.AM == calendar.get(Calendar.AM_PM);
    }

    /**
     * 判断是否是24小时制
     *
     * @param context
     * @return
     */
    public static boolean is24HourFormat(Context context) {
        return DateFormat.is24HourFormat(context);
    }


    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * 获取需要检测更新apk的时间
     *
     * @return
     */
    public static String getCheckUpdataData() {
        String hour = NumUtil.getRandomTime(6);
        return getTomorrowData() + " " + hour + ":00:00";
    }

    @SuppressLint("WrongConstant")
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    /**
     * 计算离第二天需要检测升级的时间
     *
     * @return
     */
    public static long getCheckTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String time1 = getCheckUpdataData();
            String time2 = getCurrentData();
            Date date1 = sdf.parse(time1);
            Date date2 = sdf.parse(time2);
            return date1.getTime() - date2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获得明天日期
     *
     * @return
     */
    @SuppressLint("WrongConstant")
    public static String getTomorrowData() {

        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        List<String> list_big = Arrays.asList(months_big);
        List<String> list_little = Arrays.asList(months_little);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        if (day == 30) {
            if (list_big.contains(String.valueOf(month))) {
                day = 31;
            }
            if (list_little.contains(String.valueOf(month))) {
                day = 1;
                if (month == 12) {
                    year++;
                    month = 1;
                } else {
                    month++;
                }

            }
        } else if (day == 31) {
            day = 1;
            if (month == 12) {
                year++;
                month = 1;
            } else {
                month++;
            }

        } else {
            day++;
        }
        String data;
        String monthStr;
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = "" + month;
        }
        if (day < 10) {
            data = year + "-" + monthStr + "-" + "0" + day;
        } else {
            data = year + "-" + monthStr + "-" + day;
        }

        return data;
    }

    /**
     * 格式化一个毫秒值，如果有小时，则格式化为时分秒，如：02:30:59，如果没有小时则格式化为分秒，如：30:59
     *
     * @param duration
     * @return
     */
    public static CharSequence formatMillis(long duration) {
        // 把duration转换为一个日期
        Calendar calendar = Calendar.getInstance();
        calendar.clear();    // 清除时间
        calendar.add(Calendar.MILLISECOND, (int) duration);
        CharSequence inFormat = duration / BaseConstant.HOUR_MILLIS > 0 ? "kk:mm:ss" : "mm:ss";
        return DateFormat.format(inFormat, calendar.getTime());
    }
}
