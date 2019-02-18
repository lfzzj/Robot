package com.hamitao.kids.turing.callback;

import com.hamitao.kids.turing.model.AlarmBean;

public interface TuringParserCallBack {
    /**
     * 直接播放value
     *
     * @param value
     */
    void readValue(String value);

    /**
     * 播放完value  播放音乐
     *
     * @param value
     * @param url
     */
    void playUrl(String value, String url, String title);

    /**
     * 打电话
     *
     * @param value
     * @param name
     */
    void callPhone(String value, String name);

    /**
     * 退出
     */
    void exit();

    /**
     * 设置
     *
     * @param values
     * @param operateState
     */
    void setState(String values, int operateState);

    /**
     * 未知领域
     */
    void unknownContent();

    /**
     * 启动App
     *
     * @param values
     * @param appName
     */
    void startApp(String values, String appName);

    /**
     * 设置闹钟
     * @param values
     * @param alarmBean
     */
    void setAlarm(String values, AlarmBean alarmBean);
}
