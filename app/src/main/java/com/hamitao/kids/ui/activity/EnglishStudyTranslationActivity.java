package com.hamitao.kids.ui.activity;

import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hamitao.aispeech.engine.TTSEngine;
import com.hamitao.aispeech.manager.CloudASRManager;
import com.hamitao.aispeech.manager.CloudSdsManager;
import com.hamitao.aispeech.util.AISpeechParserUtil;
import com.hamitao.aispeech.view.ASRView;
import com.hamitao.aispeech.view.SdsView;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.base.BaseMsgActivity;


import butterknife.BindView;

/**
 * 英语学习——中译音
 */
public class EnglishStudyTranslationActivity extends BaseMsgActivity {
    @BindView(R.id.iv_hold_down_speak)
    Button ivHoldDownSpeak;
    @BindView(R.id.iv_voice_right)
    ImageView ivVoiceRight;
    @BindView(R.id.iv_voice_left)
    ImageView ivVoiceLeft;
    @BindView(R.id.tv_translation_e)
    TextView tvTranslation;
    @BindView(R.id.tv_translation_c)
    TextView tvOriginal;

    private CloudASRManager cloudASRManager;
    private CloudSdsManager cloudSdsManager;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_translation);
    }

    @Override
    public void initData() {
        cloudASRManager = new CloudASRManager(mContext, mAsrView);
        cloudASRManager.initCloudASR();

        cloudSdsManager = new CloudSdsManager(mContext);
        cloudSdsManager.initCloudSds(mSdsView);

        ivHoldDownSpeak.setOnTouchListener(mOnTouchListener);
    }

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Logger.d(TAG, "按下了");
                ivVoiceRight.setVisibility(View.VISIBLE);
                ivVoiceLeft.setVisibility(View.VISIBLE);
                tvTranslation.setText("");
                tvOriginal.setText("");
                cloudASRManager.start();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                Logger.d(TAG, "松开了");
                cloudASRManager.stop();
                ivVoiceRight.setVisibility(View.GONE);
                ivVoiceLeft.setVisibility(View.GONE);
            }
            return false;
        }
    };


    private ASRView mAsrView = new ASRView() {
        @Override
        public void onInitSuccess() {
            Logger.d(TAG, "onInitSuccess");
        }

        @Override
        public void onInitFail() {
            Logger.d(TAG, "onInitFail");

        }

        @Override
        public void onError() {
            Logger.d(TAG, "onError");

        }

        @Override
        public void onResults(String result) {
            Logger.d(TAG, "onResults   result=" + result);
            if (!TextUtils.isEmpty(result)) {
                tvOriginal.setText(result);
                tvTranslation.setText("");
                cloudSdsManager.startWithText(getStrByRes(R.string.fanyi) + result);
            } else {
                toast("未检测到语音");
            }
        }

        @Override
        public void onRmsChanged(float db) {
            //通过level来找到图片的id，也可以用switch来寻址，但是代码可能会比较长
            int level = 1;
            if (db < 20) {
                level = 1;
            } else if (db >= 20 && db < 40) {
                level = 2;
            } else if (db >= 40 && db < 60) {
                level = 3;
            } else if (db >= 60 && db < 80) {
                level = 4;
            } else {
                level = 5;
            }
            int resIdLeft = mContext.getResources().getIdentifier("icon_voice_left_" + level,
                    "drawable", mContext.getPackageName());
            int resIdRight = mContext.getResources().getIdentifier("icon_voice_right_" + level,
                    "drawable", mContext.getPackageName());
            ivVoiceRight.setBackgroundResource(resIdRight);
            ivVoiceLeft.setBackgroundResource(resIdLeft);
        }
    };

    private SdsView mSdsView = new SdsView() {
        @Override
        public void onInitSuccess() {
            Logger.d(TAG, "onInitSuccess");
        }

        @Override
        public void onInitError() {
            Logger.d(TAG, "onInitError");
        }

        @Override
        public void onResults(String result) {
            Logger.d(TAG, "result=" + result);
            String translation = AISpeechParserUtil.getTranslationParserData(result);
            Logger.d(TAG, "翻译结果：" + translation);
            if (!TextUtils.isEmpty(translation)) {
                tvTranslation.setText(translation);
//                ttsManager.speak(translation);
                speakTTS(translation, TTSEngine.TTS_FLAG_COMP_NO_RESULT);
            } else {

            }
        }
    };


}
