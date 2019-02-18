package com.hamitao.kids.turing.model;

public class AlarmBean {
    private int cycleType;
    private String memoContent;
    private String startData;
    private String time;

    public AlarmBean(int cycleType, String memoContent, String startData, String time) {
        this.cycleType = cycleType;
        this.memoContent = memoContent;
        this.startData = startData;
        this.time = time;
    }

    public int getCycleType() {
        return cycleType;
    }

    public void setCycleType(int cycleType) {
        this.cycleType = cycleType;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public String getStartData() {
        return startData;
    }

    public void setStartData(String startData) {
        this.startData = startData;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
