package com.hamitao.aispeech.view;

/**
 * @data on 2018/6/28 13:56
 * @describe:
 */

public interface OneShotCloudASRView {
    void onError();

    void onResults(boolean isWakeupWord);

    void onInitSuccess();

    void onInitError();

    void onNotOneShot();
}
