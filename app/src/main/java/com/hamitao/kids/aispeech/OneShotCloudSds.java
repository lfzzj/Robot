package com.hamitao.kids.aispeech;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.common.AIConstant;
import com.aispeech.common.JSONResultParser;
import com.aispeech.export.engines.AICloudSdsEngine;
import com.aispeech.export.engines.AILocalWakeupDnnEngine;
import com.aispeech.export.listeners.AILocalWakeupDnnListener;
import com.aispeech.export.listeners.AISdsListener;
import com.hamitao.aispeech.util.AppKey;
import com.hamitao.aispeech.util.SampleConstants;
import com.hamitao.aispeech.view.OneShotView;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.app.MyApp;

import org.json.JSONException;
import org.json.JSONObject;

public class OneShotCloudSds {
    private static final String TAG = "OneShotCloudSds";
    private Context mContext;

//    private String mPrevDomain = "";
//    private static HashMap mDomainMap = new HashMap();
//
//    static {
//        mDomainMap.put("weather", "天气");
//        mDomainMap.put("calendar", "日历");
//        mDomainMap.put("chat", "聊天");
//        mDomainMap.put("navi", "地图");
//        mDomainMap.put("nearby", "地图");
//        mDomainMap.put("music", "音乐");
//        mDomainMap.put("phonecall", "打电话");
//        mDomainMap.put("weather", "天气");
//        mDomainMap.put("nearby", "地图");
//        mDomainMap.put("calendar", "日历");
////        mDomainMap.put("poetry", "古诗");
//    }

    /**
     * 语音唤醒
     */
    private AILocalWakeupDnnEngine mWakeupEngine;

    /**
     * 云端对话
     */
    private AICloudSdsEngine mAsrEngine;

    /**
     * 云端语音合成
     */
//    private AICloudTTSEngine mTtsEngine;


    private OneShotView mView;

    public  OneShotCloudSds(Context mContext, OneShotView view) {
        this.mContext = mContext;
        this.mView = view;
    }

    public void initAsrEngine() {
        if (mAsrEngine != null) {
            mAsrEngine.destroy();
        }
        mAsrEngine = AICloudSdsEngine.createInstance();
//        mAsrEngine.setResStoragePath("/sdcard/aispeech/");//设置自定义目录放置资源，如果要设置，请预先把相关资源放在该目录下
        mAsrEngine.setRes("aihome");
        mAsrEngine.setNoSpeechTimeOut(15000);
        mAsrEngine.setServer("ws://s.api.aispeech.com:1028,ws://s.api.aispeech.com:80"); //正式产品环境
        mAsrEngine.setVadResource(SampleConstants.vad_res);
//        mAsrEngine.setUseCustomFeed(true);
        mAsrEngine.setEnv("use_slot_index=1;");
        mAsrEngine.init(mContext, new AIASRListenerImpl(), AppKey.APPKEY, AppKey.SECRETKEY);
    }

    private void initWakeupEngine() {
        mWakeupEngine = AILocalWakeupDnnEngine.createInstance(); //创建实例
//        mWakeupEngine.setResStoragePath("/sdcard/aispeech/");//设置自定义目录放置资源，如果要设置，请预先把相关资源放在该目录下
        mWakeupEngine.setResBin(SampleConstants.wakeup_res); //非自定义唤醒资源可以不用设置words和thresh，资源已经自带唤醒词
        mWakeupEngine.setOneShotCacheTime(1200);
        mWakeupEngine.init(mContext, new AIWakeupListenerImpl(), AppKey.APPKEY, AppKey.SECRETKEY);
        mWakeupEngine.setStopOnWakeupSuccess(true);//设置当检测到唤醒词后自动停止唤醒引擎
        mWakeupEngine.setWords(new String[]{MyApp.getInstance().getWakeUpWord()});
        mWakeupEngine.setThreshold(new float[]{0.1f});
//        mWakeupEngine.setThreshold(new float[]{0.2f});
//        mWakeupEngine.setNREnable(true);
//        mWakeupEngine.setNRResource("NR_ch1-2-ch1_com_20171117_v1.0.0.bin");
    }

    public void startWakeup() {
        if (mWakeupEngine != null) {
            mWakeupEngine.setUseOneShotFunction(true);//是否使用oneshot功能
            mWakeupEngine.start();
        }
    }



    public void stopWakeup() {
        if (mWakeupEngine != null) {
            mWakeupEngine.setUseOneShotFunction(false);//是否使用oneshot功能
            mWakeupEngine.stop();
        }
    }

    private void startAsrUsingOneShot() {
        //这里启动带有oneshot功能的识别引擎，该引擎如果检测到连说（唤醒词+命令词），
        //那么就会直接在唤醒引擎的onResult回调中输出结果，如果检测到不是连说（只有唤醒词），那么会回调onNotOneShot(),用户可以在onNotOneShot()兼容老版本的内容
        mAsrEngine.notifyWakeup();
        mAsrEngine.setPauseTime(0);//唤醒后pauseTime设为0 ,这里为了加快判断是否是连着说
        mAsrEngine.setOneShotIntervalTimeThreshold(600); //默认为600，这个值和上面的pauseTime的差值可以根据实际情况调整
        //如果想增加连说之间的间隔，请同时增加上面两个值，比如同时加500ms，能体验到唤醒词和命令词之间的间隔可以很长
        mAsrEngine.setUseOneShotFunction(true);
        mAsrEngine.setWakeupWord(MyApp.getInstance().getWakeUpWordChinese());
        mAsrEngine.startWithRecording();
    }

    public void startAsrNotUsingOneShot() {
        //启动不带oneshot功能的识别引擎，这里onNotOneShot()不会被回调，这个只是单纯的识别，和老版本一样
        mAsrEngine.setPauseTime(300); //合成后起的识别pauseTime设为300或其他
        mAsrEngine.setUseOneShotFunction(false);
        mAsrEngine.setWakeupWord("");
        mAsrEngine.startWithRecording();
    }

    /**
     * 开启多伦对话
     *
     * @param content
     */
    public void openDoeonDialogue(String content) {
        Logger.d(TAG, "content=" + content);
//        if (TextUtils.isEmpty(content)) {
//            return;
//        }
//        String domain = StringUtil.getStringBefore(content);
//        String mContextId = StringUtil.getStringAfter(content);
//        Logger.d(TAG, "domain=" + domain + "  mContextId=" + mContextId);
//        if (mDomainMap.containsKey(domain)) {
//            mPrevDomain = (String) mDomainMap.get(domain);
//            Log.i(TAG, "mPrevDomain : " + mPrevDomain);
//        } else {
//            mPrevDomain = "";
//        }
//        mAsrEngine.cancel();
//        mAsrEngine.setDlgDomain(mPrevDomain);
//        mAsrEngine.setPrevDomain(mPrevDomain);
//        mAsrEngine.setContextId(mContextId);
        Logger.d(TAG, "start=======================");
        mAsrEngine.setNoSpeechTimeOut(15000);
        mAsrEngine.startWithRecording();
    }

    /**
     * 识别引擎回调接口，用以接收相关事件
     */
    public class AIASRListenerImpl implements AISdsListener {

        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onEndOfSpeech() {
        }

        @Override
        public void onReadyForSpeech() {
        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onError(AIError error) {
            Logger.d(TAG, "AIASRListenerImpl   " + error.toString());
            if (mView != null) {
                mView.onAsrError();
            }
            startWakeup();
        }

        @Override
        public void onResults(AIResult results) {
            if (mView != null) {
                mView.onStopTTS();
            }
            Logger.e(TAG, "===onResults===");

            JSONResultParser parser = new JSONResultParser(results.getResultObject().toString());
            String input = parser.getInput();
            String rec = parser.getRec();
            boolean isWakeupWord = TextUtils.equals(input, MyApp.getInstance().getWakeUpWordChinese()) || TextUtils.equals(input, "^")
                    || TextUtils.equals(rec, MyApp.getInstance().getWakeUpWordChinese());//云端识别结果为^，表示是唤醒词
            if (isWakeupWord) {
                Logger.e(TAG, "not one shot");
//                speakWake();
                if (mView != null) {
                    mView.notOneShot();
                }
            } else {
                try {
                    Logger.d("way", "识别结果");
                    Logger.d(TAG, "识别结果");
                    String result = new JSONObject(results.getResultObject().toString()).toString(4);
                    if (mView != null) {
                        mView.onResults(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startWakeup();
            }
        }

        @Override
        public void onInit(int status) {
            if (status == 0) {
                Logger.e(TAG, "识别引擎初始化成功!");
                initWakeupEngine();
//                initTtsEngine();
            } else {
                Logger.e(TAG, "识别引擎初始化失败");
            }
        }

        @Override
        public void onRecorderReleased() {
        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onNotOneShot() {
            Log.e(TAG, "not one shot");
            Logger.e(TAG, "唤醒成功");
//            speakWake();
            if (mView != null) {
                mView.notOneShot();
            }
        }
    }

    /**
     * 唤醒回调接口
     */
    private class AIWakeupListenerImpl implements AILocalWakeupDnnListener {

        @Override
        public void onError(AIError error) {
            Logger.d(TAG, "AIWakeupListenerImpl   " + error.toString());
        }

        @Override
        public void onInit(int status) {
            if (status == AIConstant.OPT_SUCCESS) {
                Logger.d(TAG, "唤醒引擎初始化成功!\n");
                startWakeup();
            } else {
                Logger.d(TAG, "唤醒引擎初始化失败!code:" + status);
            }
        }

        @Override
        public void onRmsChanged(float rmsdB) {
        }

        @Override
        public void onWakeup(String recordId, double confidence, String wakeupWord) {
            if (mView != null) {
                mView.onStopTTS();
            }
            Logger.d(TAG, "设备被唤醒了-------recordId=" + recordId + "    wakeupWord=" + wakeupWord);
            //原本非oneshot中的唤醒后的一些逻辑请移到onNotOneShot()中做
            startAsrUsingOneShot();//这里启动带有oneshot功能的识别引擎，该引擎如果检测到连说（唤醒词+命令词），
            //那么就会直接在唤醒引擎的onResult回调中输出结果，如果检测到不是连说（只有唤醒词），那么会回调onNotOneShot(),用户可以在onNotOneShot()兼容老版本的内容
        }

        @Override
        public void onReadyForSpeech() {
//            Logger.d(TAG, "你可以说你好小乐来唤醒 或者直接说 你好小乐+命令词\n");
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
        }

        @Override
        public void onRecorderReleased() {
        }

        @Override
        public void onWakeupEngineStopped() {
        }

    }

    public void onDestroy() {
        if (mWakeupEngine != null) {
            mWakeupEngine.destroy();
        }
        if (mAsrEngine != null) {
            mAsrEngine.destroy();
        }
    }

    public void stopRecording(){
        if (mAsrEngine != null) {
//            mAsrEngine.stopRecording();
            mAsrEngine.cancel();
        }
    }

}
