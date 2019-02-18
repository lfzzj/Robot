package com.hamitao.kids.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.audiofx.AcousticEchoCanceler;
import android.net.rtp.AudioGroup;
import android.support.multidex.MultiDex;

import com.aispeech.common.AIConstant;
import com.hamitao.aispeech.engine.TTSEngine;
import com.hamitao.framework.network.NetStatusWatch;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.WifiUtil;
import com.hamitao.kids.manager.SPManager;
import com.hamitao.kids.oss.OSSManager;
import com.hamitao.kids.utils.ManifestUtil;
import com.hamitao.kids.utils.WindowUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;

/**
 * @data on 2018/3/15 13:39
 * @describe:
 */

public class MyApp extends Application {
    private static final String TAG = "MyApp";

    public static MyApp instance;

    public static MyApp getInstance() {
        if (instance == null) {
            instance = new MyApp();
        }
        return instance;
    }


    private boolean isPlayingState = false;//记录设备的播放状态
    private int timingCloseTime = 0;//定时关闭的时间
    private boolean isAllowPlayVideo = true;//是否允许播放视频
    private boolean isVideoPlaying = false;//视频是否正在播放
    private boolean isVideoChating = false;//是否正在视频聊天
    private int GSMIntensity = 0;
    private static TTSEngine ttsEngine;


    private AudioGroup audioGroup;

    public AudioGroup getAudioGroup() {
        return audioGroup;
    }

    public void setAudioGroup(AudioGroup audioGroup) {
        this.audioGroup = audioGroup;
    }


    private AcousticEchoCanceler canceler;


    //---------------用户主动改变----------------

    public static void saveWifiSwitch(boolean state) {
        spManager.setWifiState(state);
    }

    public static void saveMobieSwitch(boolean state) {
        spManager.setMobieSwitch(state);
    }

    public static boolean getWifiSwitch() {
        return spManager.getWifiState();
    }

    public static boolean getMobieSwitch() {
        return spManager.getMobieSwitch();
    }
    //---------------用户主动改变----------------


    public static void saveMobieState(boolean state) {
        spManager.saveMobieState(state);
    }

    public static boolean getMobieState() {
        return spManager.getMobieState();
    }


    /**
     * 唤醒词
     */
    public String wakeUpWord;
    public String wakeUpWordChinese;
    public String turingKey;//turing Key
    private String turingSecret;//turing 秘钥

    private static OSSManager ossManager;
    //sp管理类
    private static SPManager spManager;

    private boolean isDeviceCreateSuccess = false;//设备是否创建成功


    @Override
    public void onCreate() {
        Logger.d(TAG, "[Application] onCreate");
        super.onCreate();

        spManager = SPManager.getInstance();
        spManager.init(getApplicationContext());

        ossManager = new OSSManager(getApplicationContext());

        NetStatusWatch.getInstance().init(this);
        JPushInterface.setDebugMode(false);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush

        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);

        JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_SILENCE);

        ManifestUtil.setManifestJPushData(this);
        WindowUtils.initMsgWindowDialog(getApplicationContext());

        WifiUtil.getInstance().init(getApplicationContext());

        //用来控制回声消除，唤醒或者多轮对话的时候要设置mode为 AudioGroup.MODE_ECHO_SUPPRESSION（此模式类似于MODE_NORMAL启用回声抑制。），
        // 还原设置为 AudioGroup.MODE_NORMAL（此模式表示AudioStream启用了扬声器，麦克风和组中的所有 s。）
//        audioGroup = new AudioGroup();
//        //经常要用到唤醒和对话，默认为开启回声消除,当电话或者视频聊天的时候才设为普通状态。
//        audioGroup.setMode(AudioGroup.MODE_ECHO_SUPPRESSION);
//        Logger.d(TAG, "打开了回声消除");


        /**
         * 必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
         */
        BGASwipeBackHelper.init(this, null);

//        AIConstant.setNewEchoEnable(true);// 打开AEC
//        AIConstant.setEchoCfgFile(SampleConstants.aec_res);// 设置AEC的配置文件
//        AIConstant.setRecChannel(1);// 默认为1,即左通道为rec录音音频,右通道为play参考音频（播放音频）
        // 若设置为2, 通道会互换，即左通道为play参考音频（播放音频）,右通道为rec录音音频

        AIConstant.openLog();//设置开启日志，发布时请关闭日志


    }

    public static boolean isDeviceSupport() {
        return AcousticEchoCanceler.isAvailable();
    }


    public boolean initAEC(int audioSession) {
        if (canceler != null) {
            return false;
        }
        canceler = AcousticEchoCanceler.create(audioSession);
        canceler.setEnabled(true);
        return canceler.getEnabled();

    }


    private static List<Activity> lists = new ArrayList<>();

    public static void addActivity(Activity activity) {
        lists.add(activity);
    }

    public static void clearActivity() {
        if (lists != null && lists.size() > 0) {
            for (Activity activity : lists) {
                activity.finish();
            }
            lists.clear();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        NetStatusWatch.getInstance().clearAllListener(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }


    public boolean isPlayingState() {
        return isPlayingState;
    }

    public int getTimingCloseTime() {
        return timingCloseTime;
    }

    public boolean isAllowPlayVideo() {
        return isAllowPlayVideo;
    }

    public boolean isVideoPlaying() {
        return isVideoPlaying;
    }

    public boolean isVideoChating() {
        return isVideoChating;
    }

    public OSSManager getOssManager() {
        return ossManager;
    }

    public boolean isDeviceCreateSuccess() {
        return isDeviceCreateSuccess;
    }

    public int getGSMIntensity() {
        return GSMIntensity;
    }

    public void setGSMIntensity(int GSMIntensity) {
        this.GSMIntensity = GSMIntensity;
    }

    public SPManager getSpManager() {
        return spManager;
    }

    public void setDeviceCreateSuccess(boolean deviceCreateSuccess) {
        isDeviceCreateSuccess = deviceCreateSuccess;
    }

    //设置播放状态
    public void setPlayingState(boolean playingState) {
        isPlayingState = playingState;
    }

    //设置媒体的定时关闭
    public void setTimingCloseTime(int timingCloseTime) {
        this.timingCloseTime = timingCloseTime;
    }

    //是否允许播放视频
    public void setAllowPlayVideo(boolean allowPlayVideo) {
        isAllowPlayVideo = allowPlayVideo;
    }

    //设置视频是否正在播放
    public void setVideoPlaying(boolean videoPlaying) {
        isVideoPlaying = videoPlaying;
    }

    //是否正在视频聊天
    public void setVideoChating(boolean videoChating) {
        isVideoChating = videoChating;
    }

    public String getWakeUpWord() {
        return wakeUpWord;
    }

    public void setWakeUpWord(String wakeUpWord) {
        this.wakeUpWord = wakeUpWord;
    }

    public String getWakeUpWordChinese() {
        return wakeUpWordChinese;
    }

    public void setWakeUpWordChinese(String wakeUpWordChinese) {
        this.wakeUpWordChinese = wakeUpWordChinese;
    }

    public String getTuringKey() {
        return turingKey;
    }

    public void setTuringKey(String turingKey) {
        this.turingKey = turingKey;
    }

    public String getTuringSecret() {
        return turingSecret;
    }

    public void setTuringSecret(String turingSecret) {
        this.turingSecret = turingSecret;
    }
}
