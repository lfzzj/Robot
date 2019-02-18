package com.hamitao.kids.turing.engine;

import android.content.Context;

import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.turing.manager.TuringSemantic;
import com.hamitao.kids.turing.manager.TuringManager;
import com.hamitao.kids.turing.callback.SemanticCallBack;
import com.turing.authority.authentication.AuthenticationListener;
import com.turing.semantic.listener.OnHttpRequestListener;

/**
 * TTS引擎
 */
public class TuringEngine {
    private static String TAG = "TuringEngine";
    private static final TuringEngine singleton = new TuringEngine();

    private static String TTS_FLAG_COMP_NO_NONE = "tts_flag_comp_no_none";//播放完成不需要任何回调

    private static boolean isTTSInitSuccess = false;

    //限制产生多个对象
    private TuringEngine() {
    }

    //通过该方法获得实例对象
    public static TuringEngine getSingleton() {
        return singleton;
    }


    /**
     * Turing初始化
     *
     * @param mContext
     */
    public static void initTuringEngine(Context mContext, String uniqueType) {
        TuringManager.init(mContext, MyApp.getInstance().getTuringKey(), MyApp.getInstance().getTuringSecret(),
                uniqueType, new AuthenticationListener() {
                    @Override
                    public void onSuccess() {
                        Logger.d(TAG, "Turing初始化成功");
//                        turingInitSuccess(mContext);
                    }

                    @Override
                    public void onError(int i, String s) {
                        Logger.d(TAG, "Turing初始化失败   " + s);
                    }
                });
    }

    /**
     * Turing初始化成功回调
     */
//    private static void turingInitSuccess(Context mContext) {
//        //初始化TTS
//        TuringTTS.init(mContext, new TTSInitListener() {
//            @Override
//            public void onSuccess() {
//                Logger.d(TAG, "Turing tts 初始化成功");
////                TuringTTS.switchOnLineTTSEngine();//切换成在线引擎
//                isTTSInitSuccess = true;
//            }
//
//            @Override
//            public void onFailed(int i, String s) {
//                Logger.d(TAG, "Turing tts 初始化失败");
//            }
//        });
//        setSemanticInit();
//    }

    /**
     * 设置语义解析的一些初始化
     */
    private static void setSemanticInit() {
    }


    /**
     * 开始播放
     *
     * @param content
     * @param flag
     */
//    public static void speak(String content, String flag) {
//        if (!isTTSInitSuccess) {
//            Logger.d(TAG, "Turing未初始化，请稍后再试");
//            return;
//        }
//        TuringTTS.startTTS(content, new TTSListener() {
//            @Override
//            public void onSpeakBegin(String s) {
//                Logger.d(TAG, "TTSListener onSpeakBegin=" + s);
//
//            }
//
//            @Override
//            public void onSpeakPaused() {
//                Logger.d(TAG, "TTSListener onSpeakPaused");
//            }
//
//            @Override
//            public void onSpeakResumed() {
//
//            }
//
//            @Override
//            public void onSpeakCompleted() {
//                Logger.d(TAG, "TTSListener onSpeakCompleted");
//                if (flag != TTS_FLAG_COMP_NO_NONE) {
//                    if (mCallBack != null) {
//                        mCallBack.onComplete(flag);
//                    }
//                }
//            }
//
//            @Override
//            public void onSpeakFailed() {
//                Logger.d(TAG, "TTSListener onSpeakFailed");
//            }
//        });
//    }

//    public static void stop() {
//        TuringTTS.stopTTS();
//    }

    /**
     * 语义解析
     */
    public static void semanticData(String content, SemanticCallBack callBack) {
        TuringSemantic.requestSemantic(content, new OnHttpRequestListener() {
            @Override
            public void onSuccess(String s) {
                if (callBack != null) {
                    callBack.onResult(s);
                }
            }

            @Override
            public void onError(int i, String s) {
                Logger.d(TAG, "semanticData   数据请求失败");
                if (callBack != null) {
                    callBack.onError(s);
                }
            }

            @Override
            public void onCancel() {
                Logger.d(TAG, "semanticData   取消请求");
            }
        });
    }

    public static void cancelRequest() {
        TuringSemantic.cancelRequest();
    }


}
