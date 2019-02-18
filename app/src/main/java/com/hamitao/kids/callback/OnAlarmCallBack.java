package com.hamitao.kids.callback;

public interface OnAlarmCallBack {
    /**
     * 闹钟响了
     * @param msg
     */
    void onRing(String msg);

    /**
     * 更新闹钟状态
     * @param msg
     * @param id
     */
    void onUpdataAlarm(String msg, String id);
}
