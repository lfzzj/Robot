package com.hamitao.kids.utils;

import android.content.Context;
import android.text.TextUtils;

import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.StringUtil;
import com.hamitao.requestframe.entity.RequestCourseScheduleInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @data on 2018/4/17 9:18
 * @describe:
 */

public class AlarmUtil {
    private static String TAG = "AlarmUtil";
    private static String MON = "MON";
    private static String TUE = "TUE";
    private static String WED = "WED";
    private static String THU = "THU";
    private static String FRI = "FRI";
    private static String SAT = "SAT";
    private static String SUN = "SUN";

    public static void setAlarm(Context context, String timer, int cycle, int ring, String name, int idx, String id) {
        String[] times = timer.split(":");
        if (cycle == 0) {//是每天的闹钟
            Logger.d(TAG, "设置了每天的闹钟");
            AlarmManagerUtil.setAlarm(context, 1, Integer.parseInt(times[0]), Integer.parseInt
                    (times[1]), createIdByIdx(idx, "9"), 0, name, ring,id);

        } else if (cycle == -1) {//是只响一次的闹钟
            Logger.d(TAG, "设置了只响一次的闹钟");
            AlarmManagerUtil.setAlarm(context, 0, Integer.parseInt(times[0]), Integer.parseInt
                    (times[1]), createIdByIdx(idx, "0"), 0, name, ring,id);
        } else {//多选，周几的闹钟
            String weeksStr = AlarmUtil.parseRepeat(cycle, 1);
            String[] weeks = weeksStr.split(",");
            for (int i = 0; i < weeks.length; i++) {
                Logger.d(TAG, "设置周" + weeks[i] + "的闹钟");
                AlarmManagerUtil.setAlarm(context, 2, Integer.parseInt(times[0]), Integer
                        .parseInt(times[1]), createIdByIdx(idx, weeks[i]), Integer.parseInt(weeks[i]), name, ring,id);
            }
        }
    }

    //设置课程表
    public static void setCourseSchedule(Context context, String timer, int id, List<RequestCourseScheduleInfo> requestCourseScheduleInfos) {
        if (!TextUtils.isEmpty(timer)) {
            String[] times = timer.split(":");

            AlarmManagerUtil.setCourseSchedule(context, 1, Integer.parseInt(times[0]), Integer.parseInt(times[1]), id, 0, requestCourseScheduleInfos);
        }
    }

    /**
     * @param repeat 解析二进制闹钟周期
     * @param flag   flag=0返回带有汉字的周一，周二cycle等，flag=1,返回weeks(1,2,3)
     * @return
     */
    public static String parseRepeat(int repeat, int flag) {
        String cycle = "";
        String weeks = "";
        if (repeat == 0) {
            repeat = 127;
        }
        if (repeat % 2 == 1) {
            cycle = "周一";
            weeks = "1";
        }
        if (repeat % 4 >= 2) {
            if ("".equals(cycle)) {
                cycle = "周二";
                weeks = "2";
            } else {
                cycle = cycle + "," + "周二";
                weeks = weeks + "," + "2";
            }
        }
        if (repeat % 8 >= 4) {
            if ("".equals(cycle)) {
                cycle = "周三";
                weeks = "3";
            } else {
                cycle = cycle + "," + "周三";
                weeks = weeks + "," + "3";
            }
        }
        if (repeat % 16 >= 8) {
            if ("".equals(cycle)) {
                cycle = "周四";
                weeks = "4";
            } else {
                cycle = cycle + "," + "周四";
                weeks = weeks + "," + "4";
            }
        }
        if (repeat % 32 >= 16) {
            if ("".equals(cycle)) {
                cycle = "周五";
                weeks = "5";
            } else {
                cycle = cycle + "," + "周五";
                weeks = weeks + "," + "5";
            }
        }
        if (repeat % 64 >= 32) {
            if ("".equals(cycle)) {
                cycle = "周六";
                weeks = "6";
            } else {
                cycle = cycle + "," + "周六";
                weeks = weeks + "," + "6";
            }
        }
        if (repeat / 64 == 1) {
            if ("".equals(cycle)) {
                cycle = "周日";
                weeks = "7";
            } else {
                cycle = cycle + "," + "周日";
                weeks = weeks + "," + "7";
            }
        }

        return flag == 0 ? cycle : weeks;
    }

    /**
     * 把服务器获取到字符串数据转化成数据形式
     *
     * @param day
     * @return
     */
    public static List<String> getDayLists(String day) {
        List<String> dayLists = new ArrayList<>();
        if (StringUtil.isContains(day, MON)) {
            dayLists.add(MON);
        }
        if (StringUtil.isContains(day, TUE)) {
            dayLists.add(TUE);
        }
        if (StringUtil.isContains(day, WED)) {
            dayLists.add(WED);
        }
        if (StringUtil.isContains(day, THU)) {
            dayLists.add(THU);
        }
        if (StringUtil.isContains(day, FRI)) {
            dayLists.add(FRI);
        }
        if (StringUtil.isContains(day, SAT)) {
            dayLists.add(SAT);
        }
        if (StringUtil.isContains(day, SUN)) {
            dayLists.add(SUN);
        }
        return dayLists;
    }

    /**
     * 获取星期的二进制数
     *
     * @return
     */
    public static int getWeekBinaryNumber(List<String> dayLists) {

        String data = "";
        for (int i = 0; i < dayLists.size(); i++) {
            if (i == 0) {
                data = dayLists.get(i);
            } else {
                data = data + "," + dayLists.get(i);
            }
        }
        int remind = ((data.indexOf(MON) == -1) ? 0 : 1) * 1 // 周一
                + ((data.indexOf(TUE) == -1) ? 0 : 1) * 2 // 周二
                + ((data.indexOf(WED) == -1) ? 0 : 1) * 4 // 周三
                + ((data.indexOf(THU) == -1) ? 0 : 1) * 8 // 周四
                + ((data.indexOf(FRI) == -1) ? 0 : 1) * 16 // 周五
                + ((data.indexOf(SAT) == -1) ? 0 : 1) * 32 // 周六
                + ((data.indexOf(SUN) == -1) ? 0 : 1) * 64; // 周日

        return remind;
    }


    /**
     * 由于设置的周期闹钟(如周一，周二)每一天都会创建一个新的闹钟，而服务器会当作是一个闹钟，因此在服务器给的id的基础上
     * 自定义生成一个新的id用来创建闹钟
     * <p>
     * ****生成方式：id*10+星期几的值，
     *
     * @param idx   服务器返回的id
     * @param weeks 周几 0-->只响一次  9-->每天都响  1-->周一  ……
     * @return
     */
    public static int createIdByIdx(int idx, String weeks) {
        return idx * 10 + Integer.parseInt(weeks);
    }

    /**
     * 删除闹钟
     *
     * @param context
     * @param id      对id进行处理
     * @return
     */
    public static void cancelAlarmClock(Context context, int id) {
        for (int i = 0; i < 10; i++) {
            AlarmManagerUtil.cancelAlarm(context, AlarmManagerUtil.ALARM_ACTION, id * 10 + i);
        }
    }

    /**
     * 删除课程表闹钟
     *
     * @param context
     * @param id
     */
    public static void cancelCourseSchedule(Context context, int id) {
        AlarmManagerUtil.cancelAlarm(context, AlarmManagerUtil.COURSES_CHEDULE_ACTION, id);
    }

    /**
     * 删除所有闹钟
     */
    public static void cancelAllAlarmClock(Context context, List<Map<String, String>> timerAlarmsBeans) {
        if (timerAlarmsBeans != null && timerAlarmsBeans.size() != 0) {
            for (int i = 0; i < timerAlarmsBeans.size(); i++) {
                if (timerAlarmsBeans.get(i) != null) {
                    String idx = timerAlarmsBeans.get(i).get("idx");
                    cancelAlarmClock(context, Integer.parseInt(idx));
                }
            }
        }
    }

    /**
     * 删除所有课程表id
     *
     * @param context
     * @param courseScheduleId
     */
    public static void cancelAllCourseScheduleClock(Context context, String courseScheduleId) {
        if (courseScheduleId != null && courseScheduleId.length() > 0) {
            String[] sourceStrArray = courseScheduleId.split(",");
            for (int i = 0; i < sourceStrArray.length; i++) {
                cancelCourseSchedule(context, Integer.parseInt(sourceStrArray[i]));
            }

        }
    }


    /**
     * 设置闹钟重复
     *
     * @param data
     */
    public static String getRepeatAlarmData(String data) {
        Logger.d(TAG, "闹钟 重复===" + data);
        String repeat = "";
        if ("EVERYDAY".equals(data)) {
            return "每天";
        } else if (StringUtil.isContains(data, "W")) {
            for (int i = 1; i <= 7; i++) {
                if (StringUtil.isContains(data, "W" + i)) {
                    repeat = repeat + "周" + getWeek(i) + " ";
                }
            }
            return getRepeatData(repeat);
        }
        return "";
    }

    private static String getWeek(int num) {
        switch (num) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "日";
        }
        return "";
    }

    private static String getRepeatData(String repeat) {
        if (StringUtil.isContains(repeat, "W1W2W3W4W5")) {
            return "每个工作日";
        } else if (StringUtil.isContains(repeat, "W6W7")) {
            return "每个周末";
        }
        return repeat;
    }
}
