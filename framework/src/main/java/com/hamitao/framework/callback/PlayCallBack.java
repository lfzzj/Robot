package com.hamitao.framework.callback;

public interface PlayCallBack {
    /**
     * 上一首
     */
    void onPlayPrevious();

    /**
     * 下一首
     */
    void onPlayNext();

    /**
     * 播放完成
     */
    void onPlayComplete();
}
