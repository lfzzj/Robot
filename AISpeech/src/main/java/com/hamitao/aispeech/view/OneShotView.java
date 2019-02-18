package com.hamitao.aispeech.view;

public interface OneShotView {
    void notOneShot();

    void onResults(String result);

    void onAsrError();

    void onTTSError();

    void onStopTTS();
}
