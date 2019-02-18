package com.hamitao.aispeech.view;

/**
 * @data on 2018/6/19 14:48
 * @describe:
 */

public interface TTSView {

    void onInitSuccess();

    void onInitError();

    void onCompletion();

}
