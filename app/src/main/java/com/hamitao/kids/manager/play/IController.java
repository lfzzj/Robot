package com.hamitao.kids.manager.play;

public interface IController {
    void setMediaTitle(String title);

    void initListener();

    void initVocie();

    void setVideoTotalTime(String totalTime);

    void setVideoCurTime(String curTime);

    void setSeekBarMax(int max);

    void setSeekBarProgress(int progress);

    void updataPlayBtnBg(boolean isPlaying);

    void showOrHideCtrlLayout();

    void showOrHideTitle(boolean isShow);
}
