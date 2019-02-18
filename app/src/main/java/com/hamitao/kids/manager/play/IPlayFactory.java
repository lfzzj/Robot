package com.hamitao.kids.manager.play;

import com.hamitao.framework.model.MediaInfo;

import java.util.List;

public interface IPlayFactory {
    /**
     * 设置媒体内容
     */
    void setMediaContent(List<MediaInfo> mediaInfos);

    /**
     * 暂停播放
     */
    void onPause();

    /**
     * 继续播放
     */
    void onResume();

    /**
     * 退出界面后的继续播放
     */
    void onResumePlay(int curPlayIndex,int curPlatTime,List<MediaInfo> mediaInfos);

    /**
     * 上一首
     */
    void onPlayPrevious();

    /**
     * 下一首
     */
    void onPlayNext();


    /**
     * 显示和隐藏控制面板
     */
    void showOrHideCtrlLayout();


    /**
     * 修改系统音量
     *
     * @param distanceYY
     */
    void changeVolume(float distanceYY, int curVolume);

    /**
     * 获取当前音量
     *
     * @return
     */
    int getStreamVolume();

    /**
     * 播放   暂停/继续播放
     */
    void pauseOrResumePlay();

    /**
     * 设置播放状态
     * @param isPlaying
     */
    void setPlayState(boolean isPlaying);


    /**
     * 释放播放器
     */
    void releasePlayer();


}
