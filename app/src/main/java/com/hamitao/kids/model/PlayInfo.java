package com.hamitao.kids.model;

import com.hamitao.framework.model.MediaInfo;

import java.util.List;

public class PlayInfo {

    private int curPlayTime;//当前播放时间
    private int curPlayIndex;//当前播放索引
    private List<MediaInfo> mediaInfos;//播放列表

    public PlayInfo(int curPlayTime, int curPlayIndex, List<MediaInfo> mediaInfos) {
        this.curPlayTime = curPlayTime;
        this.curPlayIndex = curPlayIndex;
        this.mediaInfos = mediaInfos;
    }

    public int getCurPlayTime() {
        return curPlayTime;
    }

    public void setCurPlayTime(int curPlayTime) {
        this.curPlayTime = curPlayTime;
    }

    public int getCurPlayIndex() {
        return curPlayIndex;
    }

    public void setCurPlayIndex(int curPlayIndex) {
        this.curPlayIndex = curPlayIndex;
    }

    public List<MediaInfo> getMediaInfos() {
        return mediaInfos;
    }

    public void setMediaInfos(List<MediaInfo> mediaInfos) {
        this.mediaInfos = mediaInfos;
    }
}
