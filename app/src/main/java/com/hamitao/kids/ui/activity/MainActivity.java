package com.hamitao.kids.ui.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.cloud.minds.activationlibrary.ActivationManager;
import com.cloud.minds.activationlibrary.Callback;
import com.hamitao.aispeech.engine.TTSEngine;
import com.hamitao.aispeech.manager.AISpeechManager;
import com.hamitao.aispeech.util.InstructUtils;
import com.hamitao.aispeech.view.AISpeechInitView;
import com.hamitao.aispeech.view.AITTSView;
import com.hamitao.aispeech.view.OneShotView;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.network.NetworkStatus;
import com.hamitao.framework.utils.DeviceUtil;
import com.hamitao.framework.utils.FileUtil;
import com.hamitao.framework.utils.GPSManager;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.MobileUtil;
import com.hamitao.framework.utils.WifiUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.aispeech.OneShotCloudSds;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.callback.TelephonyCallBack;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.manager.SystemContacts;
import com.hamitao.kids.manager.play.callback.LimitVideoPlayback;
import com.hamitao.kids.model.AnyEventType;
import com.hamitao.kids.mvp.ipresenter.IDevicePresenter;
import com.hamitao.kids.mvp.presenter.DevicePresenterImpl;
import com.hamitao.kids.mvp.view.IDeviceView;
import com.hamitao.kids.receiver.ScreenListener;
import com.hamitao.kids.turing.engine.TuringEngine;
import com.hamitao.kids.utils.CheckVideoPlay;
import com.hamitao.kids.utils.NetWorkUtil;
import com.hamitao.kids.view.DataTimeView;
import com.hamitao.kids.view.StatusBarView;
import com.hamitao.requestframe.entity.CreateDeviceInfo;
import com.hamitao.requestframe.entity.QueryContactInfo;
import com.hamitao.requestframe.view.QueryContactView;

import org.greenrobot.eventbus.Subscribe;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

/**
 * @data on 2018/6/1 16:17
 * @describe:
 */

public class MainActivity extends BaseMsgActivity {

    @BindView(R.id.status_bar)
    StatusBarView statusBarView;
    @BindView(R.id.status_data_time)
    DataTimeView statusDataTime;


    private boolean isDeviceCreateSuccess = false;

    private IDevicePresenter devicePresenter;
    private String deviceId;

    private boolean isAISpeechInit;//思必驰是否初始化完成
    private OneShotCloudSds oneShotCloudSds;

    private BatteryBroadCastReceiver batteryReceiver;

    ScreenListener screenListener = new ScreenListener(this);

    private Handler stateHandler = new Handler();
    private Runnable runnable;


    @Override
    public void setActivityView() {
        Logger.d(TAG, "=======================MainActivity======================");
        setContentView(R.layout.activity_main);
    }


    @Override
    public void initData() {
        initDatas();
        initTuring();
        initSpeech();
        createDevice();
        clearNeedlessCache();
        checkVideoPlay();
        GPSManager.openAMap(mContext);

        runnable = new Runnable() {
            @Override
            public void run() {
                Logger.d(TAG, "关闭网络连接");
                WifiUtil.getInstance().setWifiEnable(false);
                MobileUtil.setMobileData(MainActivity.this, false);

                Logger.d(TAG, "关闭定位");
                GPSManager.setLocationState(false);
            }
        };


        //监听屏幕开关
        screenListener.begin(new ScreenListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                Logger.d(TAG, "亮屏");

                stateHandler.removeCallbacks(runnable);

                Logger.d(TAG, "打开唤醒");
                oneShotCloudSds.startWakeup();

                Logger.d(TAG, "设置无线网络");
                WifiUtil.getInstance().setWifiEnable(MyApp.getWifiSwitch());

                Logger.d(TAG, "设置移动网络");
             if (MyApp.getMobieSwitch()) {
                    MobileUtil.setMobileData(MainActivity.this, MyApp.getMobieState());
                } else {
                    MobileUtil.setMobileData(MainActivity.this, MobileUtil.ishasSimCard(MainActivity.this) ? true : false);
                }

                Logger.d(TAG, "打开定位");
                GPSManager.setLocationState(true);
            }

            @Override
            public void onScreenOff() {
                Logger.d(TAG, "熄屏");

                Logger.d(TAG, "关闭唤醒");
                oneShotCloudSds.stopRecording();
                oneShotCloudSds.stopWakeup();

                stateHandler.removeCallbacks(runnable);
                stateHandler.postDelayed(runnable, 1000 * 60 * 2);
            }

            @Override
            public void onUserPresent() {

            }
        });

//        int width = ScreenUtil.getScreenWidth(mContext);
//        int height = ScreenUtil.getScreenHeight(mContext);
//        Logger.d(TAG, "width=" + width + "   height=" + height);
//
//        float px_w = DensityUtil.dip2px(mContext, width);
//        float dp_w = DensityUtil.px2dip(mContext, width);
//        Logger.d(TAG, "width   px=" + px_w + "    dp=" + dp_w);
//
//        float px = DensityUtil.dip2px(mContext, height);
//        float dp = DensityUtil.px2dip(mContext, height);
//        Logger.d(TAG, "height   px=" + px + "    dp=" + dp);

    }


    private void checkVideoPlay() {
        CheckVideoPlay.CheckVideoPlaybackTime(new LimitVideoPlayback() {
            @Override
            public void OnStopPlayingVideo() {
                EventBusPost(Constants.FLAG_BAN_VIDEO_PLAY, "");
            }
        }, MyApp.getInstance().getSpManager());
    }

    @Override
    protected void onResume() {
        super.onResume();
        statusDataTime.receiverTime(mContext);
        checkUnReadCount();

        IntentFilter wifiIntentFilter;
        wifiIntentFilter = new IntentFilter();
        wifiIntentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiIntentReceiver, wifiIntentFilter);

        batteryReceiver = new BatteryBroadCastReceiver();
        // 监听电量变化的意图
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(batteryReceiver, filter);

    }

    // 声明wifi消息处理过程
    private BroadcastReceiver wifiIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            statusBarView.changeNetState();
        }
    };

    private boolean isSpeak = false;//是否提示过电量充满

    /**
     * 电量广播
     */
    public class BatteryBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                // 获取当前电量
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                // 电量的总刻度
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);
                int battery = ((level * 100) / scale);
                //是否正在充电
                int status = intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
                String keyHint = "";
                switch (status) {
                    case BatteryManager.BATTERY_STATUS_CHARGING://充电中
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING://放电中
                        if (battery == 20 || battery == 10 || battery == 5) {
                            keyHint = FuncTitle.CONTENT_VOICE_HINT_LOW_POWER.toString();
                        }
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING://未充满
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL://已充满
                        keyHint = FuncTitle.CONTENT_VOICE_HINT_FULL_POWER.toString();
                        break;
                    case BatteryManager.BATTERY_STATUS_UNKNOWN://状态未知
                        break;
                }

                if (!TextUtils.isEmpty(keyHint)) {
                    if (!isSpeak) {
//                        speakTTS(keyHint, TTSEngine.TTS_FLAG_COMP_NO_RESULT);
                        speak(keyHint);
                        isSpeak = true;
                    }
                }
            } else if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                //插入电源
            } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
                //拔出电源
                isSpeak = false;
            }
        }
    }

    /**
     * 初始化 数据
     */
    private void initDatas() {
        devicePresenter = new DevicePresenterImpl(mContext, mDeviceView, queryContactView);
        statusDataTime.setOnVoiceClickListener(onVoiceClickListener);//系统音量点击事件的监听

        statusBarView.setNetStatus();//设置网络状态的图片
        deviceId = deviceManager.getDeviceId();

        NetWorkUtil.getCurrentNetDBM(mContext, mTelephonyCallBack);
    }


    /**
     * 检测是否是第一次进入
     */
    private void createDevice() {
        checkNetCreateDevice();//创建设备
    }

    /**
     * 清理不需要的缓存文件
     */
    private void clearNeedlessCache() {
        //再次开机之后就把录音文件全部删除，防止缓存过大
        FileUtil.deleteRecordFile();
    }

    /**
     * 初始化思必驰
     */
    private void initSpeech() {
        AISpeechManager speechManager = new AISpeechManager(mContext);
        speechManager.initAISpeech(mAISpeechInitView);
    }

    private void initTuring() {
        TuringEngine.initTuringEngine(mContext, deviceId);
    }

    /*思必驰初始化*/
    private AISpeechInitView mAISpeechInitView = new AISpeechInitView() {
        @Override
        public void onSuccess() {//思必驰授权成功
            isAISpeechInit = true;
            initWakeUp();
            initTTSEngine();
        }

        @Override
        public void onFailed() {
            isAISpeechInit = false;
        }
    };


    /**
     * 初始化TTS引擎
     */
    private void initTTSEngine() {

        TTSEngine.getSingleton().initTTSEngine(this);

        TTSEngine.setmAITTSView(new AITTSView() {
            @Override
            public void onInitSuccess() {
//                if (TTSEngine.initTuringTTS) {
//                    TTSManager.getInstance().setSpeed(1);
//                    Logger.d("TTS", "调整了语速");
//                }
            }

            @Override

            public void onInitError(int errorCode) {

            }

            @Override
            public void onComplete(String flag) {
                EventBusPost(flag, "");
                if (TTSEngine.TTS_FLAG_COMP_START_ASR.equals(flag)) {
                    oneShotCloudSds.startAsrNotUsingOneShot();
                }
            }
        });
    }

    /**
     * 初始化唤醒引擎
     */
    private void initWakeUp() {
        oneShotCloudSds = new OneShotCloudSds(mContext, mOneShotView);
        oneShotCloudSds.initAsrEngine();

    }


    private OneShotView mOneShotView = new OneShotView() {
        @Override
        public void notOneShot() {
            speakTTS(InstructUtils.respondAwakenRobotRespond(), TTSEngine.TTS_FLAG_COMP_START_ASR);
            startWakeUpActivity(true);
        }

        @Override
        public void onResults(String result) {
            Logger.d("way", "result=" + result);
            if (isWakeUpActivity()) {
                EventBusPost(Constants.FLAG_START_WAKE_UP_RESULT, result);
            }
        }

        @Override
        public void onAsrError() {
            EventBusPost(Constants.FLAG_START_WAKE_UP_ASR_ERROR, "");
            noNetHint();
        }

        @Override
        public void onTTSError() {
            noNetHint();
        }

        @Override
        public void onStopTTS() {
            stopTTS();
        }
    };

    /**
     * 无网络提示
     */
    public void noNetHint() {
        if (!isNetworkAvailable()) {//如果没有网络，提示无网络连接
            toast(getStrByRes(R.string.hint_net_unconnect));
            speak(FuncTitle.CONTENT_VOICE_HINT_NET_DISCONNTCT.toString());
        }
    }


    private DataTimeView.OnVoiceClickListener onVoiceClickListener = new DataTimeView.OnVoiceClickListener() {
        @Override
        public void onClick() {
            enterSystemControlActivity();
        }
    };

    /**
     * 进入主功能界面
     */
    private void enFuncActivity() {
        openActivity(FuncActivity.class);
    }

    /**
     * 进入系统音量控制
     */
    private void enterSystemControlActivity() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FLAG_SYSTEM_SETTING, Constants.FLAG_SYSTEM_SETTING_VOICE);
        openActivity(SystemControlActivity.class, bundle);
    }


    @OnClick(R.id.rl_home)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_home:
                enFuncActivity();
                break;
        }
    }

    /**
     * 检查网络，网络已连上就去创建设备
     */
    private void checkNetCreateDevice() {
        if (isNetworkAvailable()) {//判断是否连网
            Logger.d(TAG, "--网络已经连接了");
            if (!isDeviceCreateSuccess) {//如果没有创建过，就创建设备
                Logger.d(TAG, "--现在开始创建设备了");
                devicePresenter.createDevice(deviceId);
                initTuring();
                isDeviceCreateSuccess = true;
            }
        } else {
            //没有网络
            if (!isCurWelcomeActivity()) {
                toast(getStrByRes(R.string.hint_net_unconnect));
                speak(FuncTitle.CONTENT_VOICE_HINT_NET_DISCONNTCT.toString());
            }
        }
    }

    private Timer timer = new Timer(true);

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BaseConstant.REPORT_DEVICE_INFO:
                    Logger.d(TAG, "上报设备信息");
                    devicePresenter.reportDeviceInfo(deviceManager.getDeviceInfo());
                    break;
            }
        }
    };


    //上报设备信息
    private TimerTask deviceTask = new TimerTask() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = BaseConstant.REPORT_DEVICE_INFO;
            handler.sendMessage(msg);
        }
    };

    /**
     * 设备创建的回调
     */
    public IDeviceView mDeviceView = new IDeviceView() {
        @Override
        public void OnCreateDeviceSuccessListener(CreateDeviceInfo info) {
            MyApp.getInstance().setDeviceCreateSuccess(true);
            /*4.设备注册成功 获取并保存 terminalid及channelid*/
            String terminalid = info.getResponseDataObj().getTerminalInfo().getTerminalid(); //终端id
            String channelid = info.getResponseDataObj().getTerminalInfo().getChannelid_listen_on_pushserver();  //推送通道id
            Logger.d(TAG, "设备创建成功       channelid=" + channelid + "  deviceId=" + deviceManager.getDeviceId());

            MyApp.getInstance().getSpManager().putTerminalId(terminalid);
            MyApp.getInstance().getSpManager().putChannelId(channelid);

            /*5.设置极光推送  别名*/
            devicePresenter.setJmAlias(channelid);

            /*6.极光IM 注册和登录*/
            dealJPushRegisterLogin();

            //上报设备信息
            timer.schedule(deviceTask, 0, 15 * 60 * 1000);

            /*8.查询闹钟*/
            publicPresenter.queryTimerAlarm(terminalid);

            /*11.课程表查询*/
            publicPresenter.queryPublishedCourseSchedule(terminalid);

            devicePresenter.queryContact(MyApp.getInstance().getSpManager().getTerminalId());

            if (MyApp.getInstance().getSpManager().isFristBoot()) {
                if (getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)) {
                    MyApp.getInstance().getSpManager().putFristBoot();
                    startWakeUpActivity(false);
                } else {
                    openActivity(MachineBindingCodeActivity.class);
                }
            } else {
                Logger.d(TAG, "isAISpeechInit==" + isAISpeechInit);
                startWakeUpActivity(false, false, true);
            }

            if (getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_HAMITAO)) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            reportCloudminds();
                        }
                    }).start();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void OnCreateDeviceErrorListener() {//设备创建失败
            isDeviceCreateSuccess = false;
            toast(getStrByRes(R.string.hint_net_unconnect));
            speak(FuncTitle.CONTENT_VOICE_HINT_NET_DISCONNTCT.toString());
        }

        @Override
        public void OnJMRegisterResultListener(boolean isSuccess, String userName, String password) {
            Logger.d(TAG, "极光注册结果：" + isSuccess);
            if (isSuccess) {
                Logger.d(TAG, "极光IM注册成功后直接登录");
                devicePresenter.jManagerLogin(userName, password);
            } else {
                //注册失败则每隔1秒重新注册
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        JMessageClient.register(userName, password, new BasicCallback() {
                            @Override
                            public void gotResult(int i, String s) {
                                if (i == 898001 || i == 899001) {
                                    Logger.d(TAG, "极光账号已注册，直接登录");
                                    devicePresenter.jManagerLogin(userName, password);
                                } else {
                                    Logger.d(TAG, "注册失败：" + s + "，重新注册");
                                    dealJPushRegisterLogin();
                                }
                            }
                        });
                    }
                }, 1000);
            }
        }

        @Override
        public void onLoginResult(boolean isSuccess) {
            Logger.d(TAG, "极光IM登陆结果：" + isSuccess);
            if (isSuccess) {
                Logger.d(TAG, "极光IM登录成功");
                //更新设备头像
                devicePresenter.updateUserAvatar();
                //设置设备昵称
                String deviceNickName = getStrByRes(R.string.device) + deviceManager.getDeviceId();
                devicePresenter.setNickName(deviceNickName);
            } else {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Logger.d(TAG, "极光IM每隔1秒重新登录一次");
                        devicePresenter.jManagerLogin(deviceId, deviceId);
                    }
                }, 1000);
            }
        }
    };

    /**
     * 机器人激活（达闼）
     */
    private void reportCloudminds() {
        ActivationManager manager = new ActivationManager(mContext, new Callback() {
            @Override
            public void onResponseMessage(int state) {
                if (state == 0) {
                    Logger.d(TAG, "激活结果：成功");
                } else {
                    Logger.d(TAG, "激活结果：失败==" + state);
                }
            }
        });
//        manager.setActivationServerAddress("roc.cloudminds.com");
        manager.setActivationServerAddress("103.235.247.242:32380");
        manager.uploadActivateAccount(deviceManager.getDeviceId());
    }


    /**
     * 处理极光注册和登录
     */
    private void dealJPushRegisterLogin() {
        Logger.d(TAG, "极光im注册：" + deviceId);
        devicePresenter.jMRegister(deviceId, deviceId);//极光im注册
    }

    //任意写一个方法，给这个方法一个@Subscribe注解，参数类型可以自定义，但是一定要与你发出的类型相同
    @Subscribe
    public void getEventBus(AnyEventType anyEventType) {
        String flag = anyEventType.getFlag();
        if (Constants.FLAG_START_ASR_NOT_USING_ONE_SHOT.equals(flag)) {
            if (Constants.FLAG_UN_READCOUNT.equals(flag)) {
//                checkUnReadCount();
            }
        } else if (Constants.FLAG_CLOSE_AWAKEN.equals(flag)) {
            oneShotCloudSds.onDestroy();
        } else if (Constants.FLAG_RE_OPEN_AWAKEN.equals(flag)) {
            oneShotCloudSds.initAsrEngine();
        } else if (Constants.FLAG_OPEN_DORON_DIALOGUE.equals(flag)) {
            oneShotCloudSds.openDoeonDialogue(anyEventType.getContent());
        } else if (Constants.FLAG_START_WAKE_UP.equals(flag)) {//启动唤醒
            Logger.d(TAG, "-----MainActivity  启动唤醒");
            oneShotCloudSds.stopRecording();
            oneShotCloudSds.startWakeup();
        }
    }

    @Override
    public void onNetStatusChanged(NetworkStatus currNetStatus) {//网络发生改变
        statusBarView.setNetStatus();
        Logger.d(TAG, "网络发生改变：" + currNetStatus);
        checkNetCreateDevice();
        statusBarView.changeNetState();
    }

    @Override
    public void onSIMStateChanged(int simState) {
        statusBarView.changeSimState();
    }

    @Override
    public void onBatteryStateChanged(float power, boolean isCharging) {
//        super.onBatteryStateChanged(power, isCharging);
        statusBarView.setBatteryStatus(power, isCharging);
    }

    private TelephonyCallBack mTelephonyCallBack = new TelephonyCallBack() {
        @Override
        public void onDBMCallBack(int dbm) {
            int singal = DeviceUtil.getSignalIntensity(dbm);
            MyApp.getInstance().setGSMIntensity(singal);
        }
    };

    private QueryContactView queryContactView = new QueryContactView() {
        @Override
        public void onSuccess(QueryContactInfo info) {
            Logger.d(TAG, "查询联系人成功");
            SystemContacts.refreshContacts(mContext, info.getResponseDataObj().getContacts());
        }

        @Override
        public void onError(String result) {
            Logger.d(TAG, "查询联系人失败");
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        statusBarView.setNetStatus();
        statusBarView.changeSimState();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        statusDataTime.unReceiverTime(mContext);
        CheckVideoPlay.stopPlaybackTimer();
//        MyApp.getInstance().getTtsEngine().destory();//将TTS销毁
        if (timer != null) {
            timer.cancel();
        }
        if (handler != null) {
            handler.removeMessages(BaseConstant.REPORT_DEVICE_INFO);
//            handler.removeMessages(BaseConstant.CHECK_UPDATA);
        }
        unregisterReceiver(wifiIntentReceiver);
        unregisterReceiver(batteryReceiver);
        screenListener.unregisterListener();

    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

}
