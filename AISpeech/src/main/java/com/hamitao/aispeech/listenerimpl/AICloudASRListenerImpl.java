package com.hamitao.aispeech.listenerimpl;

import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.common.AIConstant;
import com.aispeech.common.JSONResultParser;
import com.aispeech.export.listeners.AIASRListener;
import com.hamitao.aispeech.view.ASRView;
import com.hamitao.framework.utils.Logger;

public class AICloudASRListenerImpl implements AIASRListener {

    private ASRView mView;

    public AICloudASRListenerImpl(ASRView view) {
        this.mView = view;
    }


    @Override
    public void onInit(int status) {
        if (status == AIConstant.OPT_SUCCESS) {
            if (mView != null) {
                mView.onInitSuccess();
            }
        } else {
            if (mView != null) {
                mView.onInitFail();
            }
        }
    }

    @Override
    public void onError(AIError aiError) {
        if (mView != null) {
            mView.onError();
        }
    }

    @Override
    public void onResults(AIResult aiResult) {
        if (aiResult.getResultType() == AIConstant.AIENGINE_MESSAGE_TYPE_JSON) {
            JSONResultParser parser = new JSONResultParser(aiResult.getResultObject().toString());
            String rec = parser.getRec();
            if (mView != null) {
                mView.onResults(rec);
            }
//
        }
    }

    @Override
    public void onRmsChanged(float v) {
        if (mView != null) {
            mView.onRmsChanged(v);
        }
    }

    @Override
    public void onReadyForSpeech() {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onRecorderReleased() {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onNotOneShot() {

    }
}
