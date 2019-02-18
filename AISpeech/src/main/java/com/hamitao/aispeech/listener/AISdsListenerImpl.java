package com.hamitao.aispeech.listener;

import android.text.TextUtils;

import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.common.AIConstant;
import com.aispeech.common.JSONResultParser;
import com.aispeech.export.listeners.AISdsListener;
import com.hamitao.aispeech.util.AISpeechParserUtil;
import com.hamitao.aispeech.view.OneShotCloudASRView;
import com.hamitao.aispeech.view.SdsView;
import com.hamitao.framework.utils.Logger;

/**
 * @data on 2018/6/19 19:40
 * @describe:
 */

public class AISdsListenerImpl implements AISdsListener {
    private static String TAG = "AISdsListenerImpl";
    private SdsView mView;
    private OneShotCloudASRView oneShotCloudASRView;



    public AISdsListenerImpl(SdsView sdsView) {

        mView = sdsView;
    }

    public AISdsListenerImpl(OneShotCloudASRView oneShotCloudASRView) {
        this.oneShotCloudASRView = oneShotCloudASRView;
    }

    @Override
    public void onInit(int status) {
        if (status == AIConstant.OPT_SUCCESS) {
            Logger.d(TAG, "ASR 初始化成功");
            if (mView != null) {
                mView.onInitSuccess();
            }
            if (oneShotCloudASRView != null) {
                oneShotCloudASRView.onInitSuccess();
            }
        } else {
            Logger.d(TAG, "ASR 初始化失败");
            if (mView != null) {
                mView.onInitError();
            }
            if (oneShotCloudASRView != null) {
                oneShotCloudASRView.onInitError();
            }
        }
    }

    @Override
    public void onError(AIError aiError) {
        Logger.d(TAG, "错误：" + aiError.toString());
        if (oneShotCloudASRView != null) {
            oneShotCloudASRView.onError();
        }
    }

    @Override
    public void onResults(AIResult aiResult) {
        if (mView != null) {
            Logger.i(TAG, "ASR 识别结果：" + aiResult);
            if (aiResult.isLast()) {
                if (aiResult.getResultType() == AIConstant.AIENGINE_MESSAGE_TYPE_JSON) {
                    JSONResultParser parser = AISpeechParserUtil.getJSONResultParser(aiResult.getResultObject().toString());
                    String sds = AISpeechParserUtil.getParserValue(parser, AISpeechParserUtil.FLAG_PARSER_SDS);
                    if (sds != null && !"".equals(sds)) {
                        mView.onResults(sds);
                    }

                }
            }
        }

        if (oneShotCloudASRView != null) {
            JSONResultParser parser = new JSONResultParser(aiResult.getResultObject().toString());
            String input = parser.getInput();
            String rec = parser.getRec();
            boolean isWakeupWord = TextUtils.equals(input, "小陶同学") || TextUtils.equals(input, "^")
                    || TextUtils.equals(rec, "小陶同学");//云端识别结果为^，表示是唤醒词

            oneShotCloudASRView.onResults(isWakeupWord);
        }
    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onReadyForSpeech() {
        Logger.d(TAG, "请说话...");

    }

    @Override
    public void onBeginningOfSpeech() {
        Logger.d(TAG, "检测到说话");
    }

    @Override
    public void onEndOfSpeech() {

        Logger.d(TAG, "检测到语音停止，开始识别...");
    }

    @Override
    public void onRecorderReleased() {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onNotOneShot() {
        if (oneShotCloudASRView != null) {
            oneShotCloudASRView.onNotOneShot();
        }
    }
}
