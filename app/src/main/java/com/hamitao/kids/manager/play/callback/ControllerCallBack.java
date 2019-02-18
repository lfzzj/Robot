package com.hamitao.kids.manager.play.callback;

public interface ControllerCallBack {

    void onSeekBarChange(int progress);

    void onPrevious();

    void onNext();

    void onPlayPause();//暂停/播放

    void onSendHideCtrl();

    void onCancelHideCtrl();
}
