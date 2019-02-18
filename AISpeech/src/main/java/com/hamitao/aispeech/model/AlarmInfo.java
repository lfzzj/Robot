package com.hamitao.aispeech.model;

public class AlarmInfo {
    private String time;//闹钟时间（07:00:00）
    private String date;// 日期 {1.单次（如：20181023）2.周期性（如：W5）3.每天（如：EVERYDAY）}
    private String event;// 事件
    private String repeat;//周期（1.W5  2.EVERYDAY）
    private String timerid;//闹钟id
    private String status;//状态（enable/disable）

    public AlarmInfo(String time, String date, String event, String repeat, String timerid, String status) {
        this.time = time;
        this.date = date;
        this.event = event;
        this.repeat = repeat;
        this.timerid = timerid;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getTimerid() {
        return timerid;
    }

    public void setTimerid(String timerid) {
        this.timerid = timerid;
    }
}
