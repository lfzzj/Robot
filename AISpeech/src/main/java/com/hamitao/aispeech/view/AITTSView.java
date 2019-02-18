package com.hamitao.aispeech.view;


public interface AITTSView {

    void onInitSuccess();

    void onInitError(int errorCode);

    void onComplete(String flag);
}
