package com.hamitao.kids.manager.play;

public interface IMediaPlayer {

    String getVideoTotalTime();

    String getVideoCurTime();

    int getSeekBarMaxTime();

    int getSeekBarCurTime();

    void setVideoSeekTo(int progress);

    boolean isVideoPlaying();

    void pausePlay();

    void resumePlay();

    void playAnim(String res);

    void hideAnim();

    /**
     * 显示和隐藏加载框
     */
    void showOrHideLoading(boolean isShow);

    void showOrHideMask(boolean isShow);


}
