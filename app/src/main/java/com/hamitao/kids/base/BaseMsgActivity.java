package com.hamitao.kids.base;

import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.hamitao.aispeech.engine.TTSEngine;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.model.MediaInfo;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.PackageUtil;
import com.hamitao.framework.utils.TimeUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.callback.BatteryStateListener;
import com.hamitao.kids.callback.OnAlarmCallBack;
import com.hamitao.kids.callback.OnCourseScheduleCallBack;
import com.hamitao.kids.callback.SIMStateListener;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.dialog.AlarmDialog;
import com.hamitao.kids.dialog.PhoneCallRemindDialog;
import com.hamitao.kids.dialog.PowerConnectedDialog;
import com.hamitao.kids.dialog.ReceiveNewMsgDialog;
import com.hamitao.kids.manager.SystemContacts;
import com.hamitao.kids.model.AnyEventType;
import com.hamitao.kids.model.CourseScheduleBean;
import com.hamitao.kids.mvp.ipresenter.IDevicePresenter;
import com.hamitao.kids.mvp.ipresenter.IPublicPresenter;
import com.hamitao.kids.mvp.presenter.DevicePresenterImpl;
import com.hamitao.kids.mvp.presenter.PublicPresenterImpl;
import com.hamitao.kids.mvp.view.IPublicMsgView;
import com.hamitao.kids.mvp.view.IPublicView;
import com.hamitao.kids.ui.activity.MainActivity;
import com.hamitao.kids.ui.activity.WakeUpActivity;
import com.hamitao.kids.utils.AlarmUtil;
import com.hamitao.kids.utils.CheckVideoPlay;
import com.hamitao.kids.utils.DataUtil;
import com.hamitao.kids.utils.PlayVoice;
import com.hamitao.kids.utils.ScreenUtil;
import com.hamitao.kids.utils.TimerControlView;
import com.hamitao.kids.utils.Util;
import com.hamitao.kids.utils.WindowUtils;
import com.hamitao.requestframe.entity.CourseScheduleInfo;
import com.hamitao.requestframe.entity.QueryContactInfo;
import com.hamitao.requestframe.entity.QueryContentByContentIdInfo;
import com.hamitao.requestframe.entity.QueryContentByMediaIdInfo;
import com.hamitao.requestframe.entity.QueryPublishedCourseScheduleInfo;
import com.hamitao.requestframe.entity.QueryRelationInfo;
import com.hamitao.requestframe.entity.QueryTimerAlarmInfo;
import com.hamitao.requestframe.entity.QueryVoiceRecordingByIDInfo;
import com.hamitao.requestframe.entity.RequestCourseScheduleInfo;
import com.hamitao.requestframe.view.QueryContactView;
import com.hamitao.requestframe.view.QueryRelationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * @data on 2018/5/29 16:03
 * @describe: 推送消息处理
 */

public abstract class BaseMsgActivity extends BaseKeyActivity {
    public IPublicPresenter publicPresenter;
    public IDevicePresenter devicePresenter;

    public Timer timer = new Timer(true);

    private PowerConnectedDialog powerConnectedDialog;//电源
    private PhoneCallRemindDialog videoRemindDialog;//视频聊天

    private TimerControlView timerControlViewAlarm;//闹钟时间控制器
    private TimerControlView timerControlViewTimingClose;//定时关闭计时器
    private AlarmDialog alarmDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);


//        checkUnReadCount();
        publicPresenter = new PublicPresenterImpl(mContext, mIPublicMsgView, mIPublicView);

        devicePresenter = new DevicePresenterImpl(mContext, queryContactView);

        initData();//等以上功能初始化完成之后,在主界面更新数据

        //注册消息广播
        publicPresenter.registerMessageReceiver();

        //注册电量广播
        publicPresenter.registerPowerRecever(mPowerView);

        //设置闹钟广播回调
        publicPresenter.registerAlarmReceiver(mAlarmCallBack);

        //注册课程表广播回调
        publicPresenter.registerCourseScheduleReceiver(mCourseScheduleCallBack);

        publicPresenter.registerSimStateReceiver(mSIMStateListener);

    }


    private float powerBatteryValue;
    private boolean isSpeak = false;

    private BatteryStateListener mPowerView = new BatteryStateListener() {
        @Override
        public void onStateChanged(float power, boolean isCharging) {//电量发生改变
            powerBatteryValue = power;
            onBatteryStateChanged(power, isCharging);
        }

        @Override
        public void onStateLow() {//电量低
            Logger.d(TAG, "onStateLow");
        }

        @Override
        public void onStateOkay() {//电量充满
            Logger.d(TAG, "onStateOkay");
        }

        @Override
        public void onStatePowerConnected() {//接通电源
            if (isCurActivity()) {
                Logger.d(TAG, "onStatePowerConnected");
//                speakTTS(InstructUtils.respondPowerConnected(), TTSEngine.TTS_FLAG_COMP_NO_RESULT);
                speak(FuncTitle.CONTENT_VOICE_HINT_CHARGING.toString());
                if (!isChatP2PActivity()) {
                    showPowerConnectedDialog(powerBatteryValue);
                }
            }
        }

        @Override
        public void onStatePowerDisconnected() {//拔出电源
            if (isCurActivity()) {
                Logger.d(TAG, "onStatePowerDisconnected");
                if (powerConnectedDialog != null) {
                    powerConnectedDialog.dismiss();
                }
            }
        }
    };

    //电量发生改变 是否正在充电
    public void onBatteryStateChanged(float power, boolean isCharging) {
        if (isCurActivity()) {
//            if (!isCharging) {
//                if (power == 0.2 || power == 0.1 || power == 0.05) {
//                    speakTTS(InstructUtils.respondLowPowerReminder(), TTSEngine.TTS_FLAG_COMP_NO_RESULT);
//                } else if (power == 1.0) {
//                    speakTTS(InstructUtils.respondFullPowerReminder(), TTSEngine.TTS_FLAG_COMP_NO_RESULT);
//                }
//            }
            if (powerConnectedDialog != null) {
                powerConnectedDialog.refreshPower(power);
            }
        }
    }

    //闹钟广播实现
    private OnAlarmCallBack mAlarmCallBack = new OnAlarmCallBack() {
        @Override
        public void onRing(String msg) {
            if (isCurActivity()) {
                Logger.d(TAG, "闹钟响了==  " + msg);
                setAlarmDialog(msg);
            }
        }

        @Override
        public void onUpdataAlarm(String msg, String id) {
            publicPresenter.UpdateTimerAlarmStatus(DataUtil.getAlarmInfo(id, false, msg, MyApp.getInstance().getSpManager().getTerminalId()));
        }
    };

    //课程表广播实现
    private OnCourseScheduleCallBack mCourseScheduleCallBack = new OnCourseScheduleCallBack() {
        @Override
        public void onPlay(List<RequestCourseScheduleInfo> scheduleInfos) {
            Logger.d(TAG, "播放内容=" + scheduleInfos.size());
            publicPresenter.queryCourseScheduleBroad(scheduleInfos);
        }
    };

    public SIMStateListener mSIMStateListener = new SIMStateListener() {
        @Override
        public void onState(int simState) {
            if (isCurActivity()) {
                onSIMStateChanged(simState);
            }
        }
    };

    public void onSIMStateChanged(int simState) {
    }

    //定时关机
    private TimerTask shotDownTask = new TimerTask() {
        public void run() {
            Message msg = new Message();
            msg.what = BaseConstant.TIMING_SHOTDOWN;
            handler.sendMessage(msg);
        }
    };

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BaseConstant.TIMING_SHOTDOWN:
                    publicPresenter.shutDown();
                    break;
            }
        }
    };


    public IPublicMsgView mIPublicMsgView = new IPublicMsgView() {
        @Override
        public void onShutDownCallBack(String time) {//执行关机操作
            if (isCurActivity()) {
                Logger.d(TAG, "==定时关机==");
                if (!"".equals(time)) {
                    int Timing = Integer.parseInt(time);
                    timer.schedule(shotDownTask, Timing * 60 * 1000, 0);//timing时间后执行关机操作
                } else {
                    publicPresenter.shutDown();
                }
            }
        }

        @Override
        public void onShutDownCallBack() {
            if (isCurActivity()) {
                Logger.d(TAG, "==实现关机==");
            }
        }

        @Override
        public void onPausePlayCallBack() {
            if (isCurActivity()) {
                Logger.d(TAG, "==暂停==");
                dealPausePlayEvent();
            }
        }

        @Override
        public void onResumePlayCallBack(String sourceChannelid) {
            if (isCurActivity()) {
                Logger.d(TAG, "==继续==");
                ScreenUtil.screenOn(mActivity);
                dismissAlarmDialog();
                dealResumePlayEvent();
            }
        }

        @Override
        public void onTurnOnLightCallBack() {
            if (isCurActivity()) {
                Logger.d(TAG, "==关灯==");

            }
        }

        @Override
        public void onVideoChatCallBack(String sourceChannelid) {//手机找设备视频聊天
            dealWithChat(sourceChannelid, BaseConstant.INSTRUCT_ACTION_PHONE_VIDEO_DEVICE);
        }

        @Override
        public void onVoiceChatCallBack(String sourceChannelid) {//手机找设备语音聊天
            dealWithChat(sourceChannelid, BaseConstant.INSTRUCT_ACTION_PHONE_VOICE_DEVICE);
        }

        @Override
        public void onVideoMonitorCallBack(String sourceChannelid) {
            Logger.d(TAG, "==监控==");
            if (isCurActivity()) {
                if (!MyApp.getInstance().isVideoChating()) {//是否已经在 聊天
                    enterVideoChatActivity(sourceChannelid, true, BaseConstant.INSTRUCT_ACTION_PHONE_MONITOR_DEVICE, "");
                } else {
                    Logger.d(TAG, "已经在 聊天了 告诉对方占线  " + sourceChannelid);
                    publicPresenter.pushMsg(sourceChannelid, Util.setRequestPushMsgInfo(BaseConstant.INSTRUCT_ACTION_LINE_BUSY, "", "", MyApp.getInstance().getSpManager().getChannelId(), sourceChannelid));
                }
            }
        }

        @Override
        public void OnPhotoGraphCallBack(String sourceChannelid) {
            if (isCurActivity()) {
                Logger.d(TAG, "==拍照上传==");
//            publicPresenter.takePhoto(sourceChannelid);
            }
        }

        @Override
        public void onUpdataAlarmClockCallBack(String sourceChannelid) {
            if (isCurActivity()) {
                Logger.d(TAG, "==更新闹钟==");
                publicPresenter.queryTimerAlarm(MyApp.getInstance().getSpManager().getTerminalId());
            }
        }

        @Override
        public void OnPlayContentCallback(List<String> contentList) {
            if (isCurActivity()) {
                Logger.d(TAG, "==播放投送内容==");
                String type = contentList.get(0);
                String id = contentList.get(1);
                showReceiveNewMsg(type, id);

            }
        }

        @Override
        public void onReportDeviceInfoCallBack(String sourceChannelid) {
            if (isCurActivity()) {
                Logger.d(TAG, "==上报设备信息==");
                publicPresenter.pushMsg(sourceChannelid, Util.setRequestPushMsgInfo(BaseConstant.INSTRUCT_ACTION_DEVICE_INFO,
                        deviceManager.reportDeviceInfo(), "", MyApp.getInstance().getSpManager().getChannelId(), sourceChannelid));
            }
        }

        @Override
        public void onUpdataCourseSchedule(String customerid) {
            if (isCurActivity()) {
                Logger.d(TAG, "==更新课程表==");
                publicPresenter.queryPublishedCourseSchedule(MyApp.getInstance().getSpManager().getTerminalId());
            }
        }

        @Override
        public void OnPushHabitListener(String habit) {
            if (isCurActivity()) {
                ScreenUtil.screenOn(mActivity);
                Logger.d(TAG, "==习惯培养==" + habit);
                dealPausePlayEvent();
//                ttsManager.speak(habit);
                speakTTS(habit, TTSEngine.TTS_FLAG_COMP_NO_RESULT);
            }
        }

        @Override
        public void onVideoChatHangUpCallBack(String sourceChannelid) {
            if (isCurActivity()) {
                Logger.d(TAG, "==视频挂断==       " + sourceChannelid);
                VideoChatHangUp(sourceChannelid);
            }
        }

        @Override
        public void OnReDeviceName(String nickName) {
            if (isCurActivity()) {
                Logger.d(TAG, "==修改极光IM聊天的昵称==");
                UserInfo userInfo = JMessageClient.getMyInfo();
                userInfo.setNickname(nickName);
                JMessageClient.updateMyInfo(UserInfo.Field.nickname, userInfo, new BasicCallback() {
                    @Override
                    public void gotResult(int state, String result) {
                        Logger.d(TAG, "state=" + state + "   result=" + result);
                        if (result.equalsIgnoreCase(BaseConstant.SUCCESS)) {
                            MyApp.getInstance().getSpManager().putImNickName(nickName);
                        }
                    }
                });
            }
        }

        @Override
        public void OnTimingClose(String time) {
            if (isCurActivity()) {
                Logger.d(TAG, "==定时关闭==" + time);
                timerControlViewTimingClose = new TimerControlView(Integer.parseInt(time) * Constants.oneMinuteTime);
                timerControlViewTimingClose.setOnTimerContorlCallBack(new TimerControlView.OnTimerContorlCallBack() {
                    @Override
                    public void onStart() {
                        Logger.d(TAG, "定时关闭 开始计时了");
                    }

                    @Override
                    public void onEnd() {
                        Logger.d(TAG, "定时关闭 结束计时了");
                        timerControlViewTimingClose.endControlTimer();
                        if (isPlayActivity()) {
                            timingClosure();
                        }
                    }
                });
                timerControlViewTimingClose.startControlTimer();
            }
        }

        @Override
        public void OnIsOpenEyeProtectListener(boolean isOpen) {
            if (isCurActivity()) {
                Logger.d(TAG, "==护眼模式是否开启==" + isOpen);
                MyApp.getInstance().getSpManager().putOpenEyeProtect(isOpen);
                CheckVideoPlay.clearPlayingNum();
                if (!isOpen) {//护眼模式如果关闭了，就允许观看视频了
                    MyApp.getInstance().setAllowPlayVideo(true);
                }
            }
        }

        @Override
        public void OnPhoneOpenListener(boolean isOpen) {
            if (isCurActivity()) {
                Logger.d(TAG, "==电话接听管理 是否开启==" + isOpen);
                MyApp.getInstance().getSpManager().putPhoneManager(isOpen);
            }
        }

        @Override
        public void OnLineBusyListener(String sourceChannelid) {
            if (isCurActivity()) {
                Logger.d(TAG, "==占线忙==" + sourceChannelid);
                EventBus.getDefault().post(new AnyEventType(Constants.FLAG_SEND_TEXT_LINE_BUSY, sourceChannelid));
                lineBusy(sourceChannelid);
            }
        }

        @Override
        public void OnUpdataContactListener() {
            devicePresenter.queryContact(MyApp.getInstance().getSpManager().getTerminalId());
        }
    };

    public void lineBusy(String sourceChannelid) {
    }

    //视频聊天挂断通知
    public void VideoChatHangUp(String joinChannel) {
        if (videoRemindDialog != null) {
            Logger.d(TAG, "不在视频聊天界面，挂断视频，关掉dialog");
            toast(getResources().getString(R.string.hint_call_hang_up));
            videoRemindDialog.dismiss();
            PlayVoice.stopVoice();
            MyApp.getInstance().setVideoChating(false);
        } else {
            EventBus.getDefault().post(new AnyEventType(Constants.FLAG_SEND_TEXT_MSG_REFUSED_NOTICE_VIDEO_CHAT, joinChannel));
        }
    }


    public void queryContentByType(String type, String id) {
        if (BaseConstant.PLAY_CONTENT_TYPE_MEDIAID.equals(type)) {
            publicPresenter.queryContentByMediaid(id);
        } else if (BaseConstant.PLAY_CONTENT_TYPE_CONTENTID.equals(type)) {
            publicPresenter.queryContentBContentid(id);
        } else if (BaseConstant.PLAY_CONTENT_TYPE_RECORDID.equals(type)) {
            publicPresenter.queryVoiceRecordingByID(id);
        } else {
            Logger.d(TAG, "数据类型出错");
        }
    }

    public IPublicView mIPublicView = new IPublicView() {
        @Override
        public void dealPhotoResults(byte[] data, Camera camera, String sourceChannelid) {
            publicPresenter.dealPhotoResults(data, camera, sourceChannelid);
        }

        @Override
        public void noticeUIPhotoResults(String filePath, String sourceChannelid) {
            //设置阿里云保存路径
            String ossPath = BaseConstant.CAMERA_PHOTO_ADDRESS + BaseConstant.CAMERA_DEVICE + deviceManager.getDeviceId() + "/" + TimeUtil.getCurrentTime();
            //将照片上传到阿里云
            MyApp.getInstance().getOssManager().photographUpload(ossPath, filePath);
            //生成Url
            String objectURL = MyApp.getInstance().getOssManager().getOssConstrainedObjectURL(ossPath);//媒体内容
            Logger.d(TAG, "生成阿里云Url=" + objectURL);
            publicPresenter.pushMsg(sourceChannelid, Util.setRequestPushMsgInfo(BaseConstant.INSTRUCT_NOTICE_TAKEPHOTO, "", objectURL, MyApp.getInstance().getSpManager().getChannelId(), sourceChannelid));
        }

        @Override
        public void QueryTimerAlarmSuccess(QueryTimerAlarmInfo info) {
            Logger.d(TAG, "闹钟查询  " + info.getResult());
            if (BaseConstant.SUCCESS.equals(info.getResult())) {
                //删除之前设置的所有闹钟
                List<Map<String, String>> oldTimerAlarms = MyApp.getInstance().getSpManager().getAlarmClockInfo();
                AlarmUtil.cancelAllAlarmClock(mContext, oldTimerAlarms);
                //保存新获取到的闹钟
                List<QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean> timerAlarmsBeans = info.getResponseDataObj().getTimerAlarms();
                MyApp.getInstance().getSpManager().putAlarmClockInfo(timerAlarmsBeans);
                checkAlarmClockData(timerAlarmsBeans);
                if (timerAlarmsBeans != null && timerAlarmsBeans.size() != 0) {
                    publicPresenter.setAlarmClock(timerAlarmsBeans);
                }
            }
        }

        @Override
        public void OnQueryContentByMediaidListener(QueryContentByMediaIdInfo info) {
            if (info != null && BaseConstant.SUCCESS.equals(info.getResult())) {
                if (info.getResponseDataObj() != null && info.getResponseDataObj().getContents() != null
                        && info.getResponseDataObj().getContents().size() != 0) {
                    QueryContentByMediaIdInfo.ResponseDataObjBean.ContentsBean contentsBean = info.getResponseDataObj().getContents().get(0);
                    Logger.d(TAG, "QueryContentByMediaid success");
                    List<MediaInfo> mediaInfos = Util.getContentInfoByMedia(contentsBean);
                    dealPlayContent(mediaInfos);
                } else {
                    Logger.d(TAG, "QueryContentByMediaid null");
                    dealPlayContent(null);
                }
            } else {
                Logger.d(TAG, "QueryContentByMediaid error");
            }
        }

        @Override
        public void OnQueryContentByContentidListener(QueryContentByContentIdInfo info) {
            if (info != null && BaseConstant.SUCCESS.equals(info.getResult())) {
                if (info.getResponseDataObj() != null && info.getResponseDataObj().getContents() != null
                        && info.getResponseDataObj().getContents().size() != 0) {
                    QueryContentByContentIdInfo.ResponseDataObjBean.ContentsBean contentsBean = info.getResponseDataObj().getContents().get(0);
                    Logger.d(TAG, "QueryContentByContentid success");
                    List<MediaInfo> mediaInfos = Util.getContentInfoByContent(contentsBean);
                    dealPlayContent(mediaInfos);
                }
            } else {
                Logger.d(TAG, "QueryContentByContentid error");
            }
        }

        @Override
        public void OnQueryVoiceRecordingByIDListener(QueryVoiceRecordingByIDInfo info) {
            Logger.d(TAG, "查询录音=" + info.getResult());
            if (BaseConstant.SUCCESS.equals(info.getResult())) {
                if (info.getResponseDataObj().getVoiceRecordings() != null && info.getResponseDataObj().getVoiceRecordings().size() != 0) {
                    QueryVoiceRecordingByIDInfo.ResponseDataObjBean.VoiceRecordingsBean voiceRecordingsBean = info.getResponseDataObj().getVoiceRecordings().get(0);
                    List<MediaInfo> mediaInfos = Util.getVoiceRecordings(voiceRecordingsBean);
                    dealPlayContent(mediaInfos);
                }
            }
        }

        @Override
        public void OnQueryPublishedCourseScheduleListener(QueryPublishedCourseScheduleInfo queryPublishedCourseScheduleInfo) {
            Logger.d(TAG, "课程表查询  " + queryPublishedCourseScheduleInfo.getResult() + "       课程表：" + queryPublishedCourseScheduleInfo.getResponseDataObj().getCourseScheduleList().size());
            if (BaseConstant.SUCCESS.equals(queryPublishedCourseScheduleInfo.getResult())) {
                //删除之前所有的课程表
                String getCourseScheduleId = MyApp.getInstance().getSpManager().getCourseScheduleId();
                AlarmUtil.cancelAllCourseScheduleClock(mContext, getCourseScheduleId);

                List<QueryPublishedCourseScheduleInfo.ResponseDataObjBean.CourseScheduleListBean> courseScheduleList = queryPublishedCourseScheduleInfo.getResponseDataObj().getCourseScheduleList();
                List<CourseScheduleBean> courseScheduleBeans = Util.getCourseScheduleList(courseScheduleList, MyApp.getInstance().getSpManager());
                if (courseScheduleBeans != null && courseScheduleBeans.size() > 0) {
                    publicPresenter.setPublishedCourseSchedule(courseScheduleBeans);
                }
            }
        }

        @Override
        public void onQueryCourseScheduleBroadListener(CourseScheduleInfo info) {
            dealPlayContent(Util.getMediaInfoByCourseSchedule(info));
        }
    };

    //闹钟数据回调
    public void checkAlarmClockData(List<QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean> timerAlarmsBeans) {
    }

    //处理聊天
    private void dealWithChat(String sourceChannelid, String chatType) {
        dealPausePlayEvent();
        if (isCurActivity()) {
            Logger.d(TAG, "==视频/语音聊天==" + MyApp.getInstance().isVideoChating());
            if (!MyApp.getInstance().isVideoChating()) {//是否已经在 聊天
                MyApp.getInstance().setVideoChating(true);
                DevicePresenterImpl devicePresenter = new DevicePresenterImpl(mContext, new QueryRelationView() {
                    @Override
                    public void onError(String result) {
                        Logger.d(TAG, "关系查询失败=" + result);
                    }

                    @Override
                    public void onSuccess(QueryRelationInfo info) {
                        List<QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean> customerInfos = info.getResponseDataObj().getRelation().getCustomerInfos();
                        String callName = Util.getBindNameByRelation(customerInfos, sourceChannelid);
                        Logger.d(TAG, "关系查询成功" + callName);
                        showCallPhoneRemindDialog(true, "".equals(callName) ? getResources().getString(R.string.stranger) : callName, sourceChannelid, chatType);
                    }
                });
                devicePresenter.queryRelation(MyApp.getInstance().getSpManager().getTerminalId());
            } else {
                Logger.d(TAG, "已经在 聊天了 告诉对方占线  " + sourceChannelid);
                publicPresenter.pushMsg(sourceChannelid, Util.setRequestPushMsgInfo(BaseConstant.INSTRUCT_ACTION_LINE_BUSY, "", "", MyApp.getInstance().getSpManager().getChannelId(), sourceChannelid));
            }
        }
    }

    /**
     * 定时关闭
     */
    public void timingClosure() {
        Logger.d(TAG, "父类  定时关闭===");
        EventBus.getDefault().post(new AnyEventType(Constants.FLAG_TIMING_CLOSURE, ""));
    }

    public void dealPausePlayEvent() {//暂停播放
    }

    public void dealResumePlayEvent() {//继续播放
        if (!isPlayActivity()) {
            String playStr = MyApp.getInstance().getSpManager().getPlayInfosStr();
            if (!TextUtils.isEmpty(playStr)) {

                Logger.d("player", "继续播放");
                enterPlayActivity(Constants.FLAG_ENTER_PLAY_ACTIVITY_RESUME, playStr);
            } else {
                playHint.playFuncTitle(FuncTitle.CONTENT_NO_CONTENT.toString());
            }

        }
    }

    public void dealOnNextEvent() {//下一首
    }

    //处理播放内容（推送/课程表）
    public void dealPlayContent(List<MediaInfo> mediaInfos) {
        if (!isPlayActivity()) {
            ScreenUtil.screenOn(mActivity);
            enterPlayActivity(Constants.FLAG_ENTER_PLAY_ACTIVITY_PLAY_CONTENT, mediaInfos);
        }
    }

    //处理投送内容 --此方法是为了接收到推送消息之后快速进入播放界面
    public void dealToMediaPlayContent(String type, String id) {
        if (!isPlayActivity()) {
            Logger.d("player", "播放投送内容");
            enterPlayActivity(Constants.FLAG_ENTER_PLAY_ACTIVITY_PUSH, type + "," + id);
        }
    }


    public boolean isCurWelcomeActivity() {
        return PackageUtil.getRunningActivityName(mContext).equals(Constants.WELCOME_ACTIVITY);
    }

    //当前Activity是否是PlayActivity
    public boolean isPlayActivity() {
        return PackageUtil.getRunningActivityName(mContext).equals(Constants.PLAY_ACTIVITY);
    }


    /**
     * 按Home之后 进入唤醒（必须进入）
     */
    @Override
    public void onHomePressed() {
        startWakeUpActivity(false, true);
        if (MyApp.getInstance().getSpManager().isFristBoot()) {
            MyApp.getInstance().getSpManager().putFristBoot();
            MyApp.clearActivity();
        }
    }

    /**
     * 第一次 连接网络成功之后 进入唤醒
     */
    public void closeFristBoot() {
        if (MyApp.getInstance().getSpManager().isFristBoot()) {//如果不是第一次就不操作
//            MyApp.getInstance().getSpManager().putFristBoot();
            openActivity(MainActivity.class);
            MyApp.clearActivity();
        }

    }

    /**
     * 思必驰被唤醒
     *
     * @param isWakeUp
     */
    public void startWakeUpActivity(boolean isWakeUp) {
        startWakeUpActivity(isWakeUp, false);
    }

    public void startWakeUpActivity(boolean isWakeUp, boolean isClickHome) {
        startWakeUpActivity(isWakeUp, isClickHome, false);
    }

    /**
     * 思必驰被唤醒
     *
     * @param isWakeUp    设备是被唤醒进入唤醒界面
     * @param isClickHome 是否点击了home键进入的
     * @param isOpen      是否是刚开机
     */
    public void startWakeUpActivity(boolean isWakeUp, boolean isClickHome, boolean isOpen) {
        dismissAlarmDialog();
        Logger.d(TAG, "isWakeUp=" + isWakeUp);
        if (isNetworkAvailable()) {
            if (!isWakeUpActivity()) {
                ScreenUtil.screenOn(mActivity);
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constants.FLAG_START_WAKE_UP_IS_WAKE_UP, isWakeUp);
                bundle.putBoolean(Constants.FLAG_START_WAKE_UP_IS_CLICK_HOME, isClickHome);
                bundle.putBoolean(Constants.FLAG_START_WAKE_UP_IS_OPEN, isOpen);
                openActivity(WakeUpActivity.class, bundle);
            } else {
                EventBus.getDefault().post(new AnyEventType(Constants.FLAG_START_WAKE_UP_NOT_ONE_SHOT, ""));
            }
        } else {
            toast(getStrByRes(R.string.hint_net_unconnect));
            speak(FuncTitle.CONTENT_VOICE_HINT_NET_DISCONNTCT.toString());
        }
    }

    //弹出接收到推送消息弹框
    public void showReceiveNewMsg(String type, String id) {
        dismissAlarmDialog();
        ScreenUtil.screenOn(mActivity);
        ReceiveNewMsgDialog receiveNewMsgDialog = new ReceiveNewMsgDialog(mActivity);
        receiveNewMsgDialog.show();
        //设置全屏
        setFullScreen(receiveNewMsgDialog.getWindow());

        receiveNewMsgDialog.setReceiveNewMsgImageView();
        receiveNewMsgDialog.setOnDialogClickListener(new ReceiveNewMsgDialog.OnClicklistener() {
            @Override
            public void onReceiveNewMsg() {
//                queryContentByType(type, id);
                receiveNewMsgDialog.dismiss();
                dealToMediaPlayContent(type, id);

            }
        });
    }

    //设置视频聊天提醒
    private void showCallPhoneRemindDialog(boolean isCall, String callName, String sourceChannelid, String chatType) {
        if (isCurActivity()) {
            stopTTS();
            ScreenUtil.screenOn(mActivity);
            videoRemindDialog = new PhoneCallRemindDialog(mActivity);
            videoRemindDialog.show();
            PlayVoice.playVoice(mContext, R.raw.apple_iphone_6);
            //设置全屏
            setFullScreen(videoRemindDialog.getWindow());

            videoRemindDialog.setPhoneCallRemindInfo(callName, chatType);
            videoRemindDialog.setOnDialogClickListener(new PhoneCallRemindDialog.OnCallDialogClicklistener() {
                @Override
                public void onRefuse() {//挂断
                    Logger.d(TAG, "挂断");
                    videoRemindDialog.dismiss();
                    PlayVoice.stopVoice();
                    toast(getResources().getString(R.string.hint_call_canceled));
                    publicPresenter.pushMsg(sourceChannelid, Util.setRequestPushMsgInfo(BaseConstant.INSTRUCT_ACTION_HANG_UP, "", "", MyApp.getInstance().getSpManager().getChannelId(), sourceChannelid));
                    MyApp.getInstance().setVideoChating(false);
                }

                @Override
                public void onAnswer() {//接听
                    videoRemindDialog.dismiss();
                    PlayVoice.stopVoice();
                    Logger.d(TAG, callName + "接听    " + sourceChannelid);
                    //进入到视频聊天界面
                    enterVideoChatActivity(sourceChannelid, isCall, chatType, callName);
                }
            });
        }
    }

    public void toChatTurnOffTTS() {//来聊天了关闭TTS播放

    }


    //插入电源弹框
    public void showPowerConnectedDialog(float power) {
        powerConnectedDialog = new PowerConnectedDialog(mActivity);
        powerConnectedDialog.show();
        //设置全屏
        setFullScreen(powerConnectedDialog.getWindow());

        powerConnectedDialog.setBatteryValue(power);
        powerConnectedDialog.setBatteryViewClickListener(new PowerConnectedDialog.OnBatteryViewListener() {
            @Override
            public void onClick() {
                powerConnectedDialog.dismiss();
            }
        });
    }

    /**
     * 设置闹钟响了
     *
     * @param msg
     */
    public void setAlarmDialog(String msg) {
        alarmClockRang();
        ScreenUtil.screenOn(mActivity);
        alarmDialog = new AlarmDialog(mActivity);
        alarmDialog.show();

        //设置全屏
        setFullScreen(alarmDialog.getWindow());

        alarmDialog.setAlarmTitle(msg);

        alarmDialog.setOnClickListener(new AlarmDialog.OnClickListener() {
            @Override
            public void onClick() {
                dismissAlarmDialog();
                timerControlViewAlarm.endControlTimer();
            }
        });
        timerControlViewAlarm = new TimerControlView(Constants.threeMinuteTime);
        timerControlViewAlarm.setOnTimerContorlCallBack(new TimerControlView.OnTimerContorlCallBack() {
            @Override
            public void onStart() {
                Logger.d(TAG, "闹钟响了，开始计时了");
                alarmDialog.openAlarmRing();
            }

            @Override
            public void onEnd() {
                dismissAlarmDialog();
                timerControlViewAlarm.endControlTimer();

            }
        });
        timerControlViewAlarm.startControlTimer();
    }

    /**
     * 关闭闹钟
     */
    public void dismissAlarmDialog() {
        if (alarmDialog != null) {
            alarmDialog.closeAlarm();
            alarmDialog.dismiss();
        }
    }

    /**
     * 通知外部----闹钟响了
     */
    public void alarmClockRang() {
    }

    private QueryContactView queryContactView = new QueryContactView() {
        @Override
        public void onSuccess(QueryContactInfo info) {
            Logger.d(TAG, "查询联系人成功");
            SystemContacts.refreshContacts(mContext, info.getResponseDataObj().getContacts());
        }

        @Override
        public void onError(String result) {

        }
    };


    public void checkUnReadCount() {
        int count = JMessageClient.getAllUnReadMsgCount();
//        //未读消息数不为空且(不在聊天界面或者强制显示)
        boolean iShow = count > 0;
        if (iShow) {
            WindowUtils.showPopWindow(count);
            return;
        }
        WindowUtils.hidePopupWindow();
    }


    //接收到消息回调
    public void onEventMainThread(MessageEvent event) {
        ContentType type = event.getMessage().getContentType();
        if (type.equals(ContentType.eventNotification)) {//如果是通知

        } else {
            receiveMessage();
        }
    }

    //接收到消息了
    public void receiveMessage() {
        ScreenUtil.screenOn(mActivity);
        if (isCurActivity()) {//所有界面都提示来新消息了
            playHint.playFuncTitle(FuncTitle.CONTENT_RECEIVE_NEW_INFO.toString());
        }
        if (!isChatActivity()) {
            WindowUtils.showPopWindow(JMessageClient.getAllUnReadMsgCount());
        } else {
            WindowUtils.hidePopupWindow();
        }
    }

    public boolean isChatActivity() {
        String topActivity = PackageUtil.getTopActivityName(PackageUtil.getTopActivityClassName(mContext));
        if (topActivity.equals(Constants.SESSION_LIST_ACTIVITY)
                || topActivity.equals(Constants.CHAT_ACTIVITY)
                || topActivity.equals(Constants.CAMERA_ACTIVITY)
                || topActivity.equals(Constants.VIDEO_CHAT_P2P_ACTIVITY)
                || topActivity.equals(Constants.WAKE_UP_ACTIVITY)
                || topActivity.equals(Constants.WELCOME_ACTIVITY)
                || topActivity.equals(Constants.PLAY_ACTIVITY)) {
            return true;
        }
        return false;
    }

    //判断栈顶Activity是不是唤醒activity
    public boolean isWakeUpActivity() {
        String topActivity = PackageUtil.getTopActivityName(PackageUtil.getTopActivityClassName(mContext));
        if (topActivity.equals(Constants.WAKE_UP_ACTIVITY)) {
            return true;
        }
        return false;
    }

    public boolean isChatP2PActivity() {
        String topActivity = PackageUtil.getTopActivityName(PackageUtil.getTopActivityClassName(mContext));
        if (topActivity.equals(Constants.VIDEO_CHAT_P2P_ACTIVITY) || topActivity.equals(Constants.PLAY_ACTIVITY)) {
            return true;
        }
        return false;
    }

    //设置dialog全屏
    public void setFullScreen(Window window) {
        //设置全屏
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = window.getAttributes();  //获取对话框当前的参数值
        p.width = (int) (d.getWidth());    //宽度设置为全屏
        window.setAttributes(p);     //设置生效
    }

    //任意写一个方法，给这个方法一个@Subscribe注解，参数类型可以自定义，但是一定要与你发出的类型相同
    @Subscribe
    public void getEventBus(AnyEventType anyEventType) {
        String flag = anyEventType.getFlag();
        if (Constants.FLAG_UN_READCOUNT.equals(flag)) {
//            checkUnReadCount();
        } else if (Constants.FLAG_COMP_TIMING_CLOSURE.equals(flag)) {
            toast("定时关闭的时间到了");
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
//        ttsManager.playerPause();
        if (powerConnectedDialog != null) {
            powerConnectedDialog.dismiss();
        }
        dismissAlarmDialog();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        publicPresenter.unRegisterMessageReceiver();
        publicPresenter.unRegisterPowerRecriver();
        publicPresenter.unRegisterAlarmReceiver();
        publicPresenter.unRegisterCourseScheduleReceiver();
    }


}
