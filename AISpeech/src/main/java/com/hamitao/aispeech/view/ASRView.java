package com.hamitao.aispeech.view;

/**
 * @data on 2018/6/19 14:48
 * @describe:
 */

public interface ASRView {

    void onInitSuccess();

    void onInitFail();

    void onError();

    void onResults(String result);

    void onRmsChanged(float db);
}
