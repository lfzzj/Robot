package com.hamitao.aispeech.listener;

import com.aispeech.AIError;
import com.aispeech.common.AIConstant;
import com.aispeech.export.listeners.AITTSListener;
import com.hamitao.aispeech.view.TTSView;
import com.hamitao.framework.utils.Logger;

/**
 * @data on 2018/6/19 14:46
 * @describe:
 */

public class AITTSListenerImpl implements AITTSListener {
    private static String TAG = "AITTSListenerImpl";
    private TTSView mView;

    public AITTSListenerImpl(TTSView ttsView) {
        this.mView = ttsView;
    }

    @Override
    public void onInit(int status) {
        Logger.i(TAG, "初始化完成，返回值：" + status);
        if (status == AIConstant.OPT_SUCCESS) {
            Logger.i(TAG, "TTS  初始化成功");
            if (mView != null) {
                mView.onInitSuccess();
            }
        } else {
            Logger.i(TAG, "TTS  初始化失败");
            if (mView != null) {
                mView.onInitError();
            }
        }
    }

    @Override
    public void onError(String s, AIError aiError) {
        Logger.i(TAG, "TTS  onError");
    }

    @Override
    public void onReady(String utteranceId) {
        Logger.i(TAG, "TTS  开始播放=" + utteranceId);
    }

    @Override
    public void onCompletion(String s) {
        Logger.i(TAG, "TTS  合成完成" + s);
        if (mView != null) {
            mView.onCompletion();
        }
    }

    @Override
    public void onProgress(int i, int i1, boolean b) {

    }
}
