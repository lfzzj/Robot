package com.hamitao.aispeech.engine;

import android.content.Context;

import com.aispeech.AIError;
import com.aispeech.common.AIConstant;
import com.aispeech.common.Util;
import com.aispeech.export.engines.AICloudTTSEngine;
import com.aispeech.export.listeners.AITTSListener;
import com.hamitao.aispeech.util.AppKey;
import com.hamitao.aispeech.view.AITTSView;
import com.hamitao.framework.utils.Logger;
//import com.turing.tts.TTSInitListener;
//import com.turing.tts.TTSListener;
//import com.turing.tts.TTSManager;


public class TTSEngine {
    private static final String TAG = "TTS";


    private static final TTSEngine instance = new TTSEngine();

    //构造函数私有化
    private TTSEngine() {
    }

    //通过该方法获得实例对象
    public static TTSEngine getSingleton() {
        return instance;
    }


    public static final String TTS_FLAG_COMP_NO_NONE = "tts_flag_comp_no_none";//tts不需要任何返回
    public static final String TTS_FLAG_COMP_NO_RESULT = "tts_flag_comp_no_result";//播放完成不需要执行任何操作
    public static final String TTS_FLAG_COMP_TEXT_READER = "tts_flag_comp_text_reader";//播放界面 文本朗读完成
    public static final String TTS_FLAG_COMP_OPEN_DORON_DIALOG = "tts_flag_comp_open_doron_dialog";//唤醒界面 tts播放完成后开启多伦
    public static final String TTS_FLAG_COMP_FINISH = "tts_flag_comp_finish";//需要关闭界面
    public static final String TTS_FLAG_COMP_START_ASR = "tts_flag_comp_start_asr";//启动识别
    public static final String TTS_FLAG_COMP_TIMING_CLOSURE = "tts_flag_comp_timing_closure";//定时关闭的时间到了
    public static final String TTS_FLAG_COMP_TURN_OFF = "tts_flag_comp_turn_off";//执行关机
    public static final String TTS_FLAG_COMP_PLAY_CONTENT_TITLE = "tts_flag_comp_play_content_title";//提示播放内容的标题
    public static final String TTS_FLAG_COMP_PLAY_CONTENT = "tts_flag_comp_play_content";//语音播放完成之后播放内容
    public static final String TTS_FLAG_COMP_OPEN_APP = "tts_flag_comp_open_app";//启动activity

    private static AICloudTTSEngine mTtsEngine;
    private static AITTSView mAITTSView;
    private static String curFlag = TTS_FLAG_COMP_NO_NONE;

//    public static boolean initTuringTTS = false;


    public static void setmAITTSView(AITTSView view) {
        mAITTSView = view;
    }

    //类中其他方法，尽量是static
    public void initTTSEngine(Context mContext) {
       /* if (initTuringTTS) {
            TTSManager.getInstance().init(mContext, new TTSInitListener() {
                @Override
                public void onSuccess() {
//                mView.onInitSuccess();
                    Logger.d("TTS", "初始化成功");
                    initTuringTTS = true;
                }

                @Override
                public void onFailed(int i, String s) {
                    mAITTSView.onInitError(i);
                }
            });


        } else {

            mTtsEngine = AICloudTTSEngine.createInstance();//创建实例
            mTtsEngine.setRealBack(true);
            mTtsEngine.init(mContext, new AICloudTTSListenerImpl(), AppKey.APPKEY, AppKey.SECRETKEY);
            // 指定默认中文合成
            mTtsEngine.setLanguage(AIConstant.CN_TTS);
            //女童
            mTtsEngine.setRes("syn_chnsnt_qianranf");
            mTtsEngine.setDeviceId(Util.getIMEI(mContext));
        }*/

        mTtsEngine = AICloudTTSEngine.createInstance();//创建实例
        mTtsEngine.setRealBack(true);
        mTtsEngine.init(mContext, new AICloudTTSListenerImpl(), AppKey.APPKEY, AppKey.SECRETKEY);
        // 指定默认中文合成
        mTtsEngine.setLanguage(AIConstant.CN_TTS);
        //女童
        mTtsEngine.setRes("syn_chnsnt_qianranf");
        mTtsEngine.setDeviceId(Util.getIMEI(mContext));
    }

    /**
     * 停止播放
     */
    public void stop() {
        curFlag = TTS_FLAG_COMP_NO_NONE;

//        if (initTuringTTS) {
//            TTSManager.getInstance().stopTTS();
//        } else {
//            if (mTtsEngine != null) {
//                mTtsEngine.stop();
//            }
//        }

        if (mTtsEngine != null) {
            mTtsEngine.stop();
        }

    }


    /**
     * 开始播放
     *
     * @param key
     * @param flag
     */
    public void speak(String key, String flag) {
        stop();

      /*  if (initTuringTTS) {
            curFlag = flag;
            TTSManager.getInstance().startTTS(key, TTSManager.LIANGJIANHE_TONE, new TTSListener() {
                @Override
                public void onSpeakBegin(String s) {
                    Logger.d(TAG, "开始朗读!");
                }

                @Override
                public void onSpeakPaused() {
                    Logger.d(TAG, "朗读暂停!");
                }

                @Override
                public void onSpeakResumed() {
                    Logger.d(TAG, "重新朗读!");
                }

                @Override
                public void onSpeakCompleted() {
                    mAITTSView.onComplete(curFlag);
                    Logger.d(TAG, "朗读完成!");
                }

                @Override
                public void onSpeakFailed() {
                    Logger.d(TAG, "合成引擎初始化成功!");
                }
            });
        } else {
            if (mTtsEngine != null && isSuccess) {
                curFlag = flag;
                mTtsEngine.speak(key, "1024");
            }
        }*/

        if (mTtsEngine != null && isSuccess) {
            curFlag = flag;
            mTtsEngine.speak(key, "1024");
        }
    }

    /**
     * 销毁
     */
    public void destory() {
        curFlag = TTS_FLAG_COMP_NO_NONE;
        if (mTtsEngine != null) {
            mTtsEngine.destroy();
        }
    }

    private static boolean isSuccess = false;

    /**
     * 合成回调接口
     */
    private static class AICloudTTSListenerImpl implements AITTSListener {
        @Override
        public void onInit(int status) {
            if (status == AIConstant.OPT_SUCCESS) {
                Logger.d(TAG, "合成引擎初始化成功!\n");
                isSuccess = true;
                if (mAITTSView != null) {
                    mAITTSView.onInitSuccess();
                }
            } else {
                Logger.d(TAG, "初始化失败!code:" + status);
                isSuccess = false;
                if (mAITTSView != null) {
                    mAITTSView.onInitError(status);
                }
            }
        }

        @Override
        public void onProgress(int currentTime, int totalTime, boolean isRefTextTTSFinished) {
        }

        @Override
        public void onError(String utteranceId, AIError error) {
            Logger.d(TAG, "AICloudTTSListenerImpl  " + error.toString());

        }

        @Override
        public void onReady(String utteranceId) {
        }

        @Override
        public void onCompletion(String utteranceId) {
            Logger.d(TAG, "TTS播放完成   curFlag=" + curFlag);
            if (curFlag != TTS_FLAG_COMP_NO_NONE) {
                if (mAITTSView != null) {
                    mAITTSView.onComplete(curFlag);
                }
            }
        }
    }
}
