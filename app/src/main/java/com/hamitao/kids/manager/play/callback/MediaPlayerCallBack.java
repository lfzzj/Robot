package com.hamitao.kids.manager.play.callback;

public interface MediaPlayerCallBack {
    void onPrepared(String title);

    void onCompletion();

    /**
     * 媒体开始播放
     * @param isVideo 是否是视屏
     */
    void onMediaPlaying(boolean isVideo);
}
