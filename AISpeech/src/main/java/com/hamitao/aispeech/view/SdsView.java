package com.hamitao.aispeech.view;

/**
 * @data on 2018/6/19 19:29
 * @describe:
 */

public interface SdsView {
    void onInitSuccess();

    void onInitError();

    void onResults(String result);
}
