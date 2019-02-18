package com.hamitao.kids.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;

import com.hamitao.aispeech.engine.TTSEngine;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.model.MediaInfo;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.PackageUtil;
import com.hamitao.kids.camera.core.CameraManager;
import com.hamitao.kids.camera.util.ViewUtil;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.manager.DeviceManager;
import com.hamitao.kids.model.AnyEventType;
import com.hamitao.kids.model.FuncBean;
import com.hamitao.kids.model.RelationInfo;
import com.hamitao.kids.ui.activity.ChatActivity;
import com.hamitao.kids.ui.activity.FuncThreeActivity;
import com.hamitao.kids.ui.activity.FuncTwoActivity;
import com.hamitao.kids.ui.activity.PlayActivity;
import com.hamitao.kids.ui.activity.VideoChatP2PActivity;
import com.hamitao.kids.utils.PlayHint;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.event.ContactNotifyEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

/**
 * @data on 2018/5/29 15:12
 * @describe: 处理 公共 事件
 */

public abstract class BaseCommonActivity extends BaseNetActivity {


    //设备信息管理类
    public DeviceManager deviceManager;

    private ViewUtil mViewUtil;

    public PlayHint playHint;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        deviceManager = new DeviceManager(mContext);

        CameraManager.getInstance().addActivity(this);
        mViewUtil = new ViewUtil(this);

        JMessageClient.registerEventReceiver(this);

        playHint = new PlayHint(mContext);
    }

    /**
     * 播放 提示音
     *
     * @param res
     */
    public void speak(String res) {
        playHint.playFuncTitle(res);
    }

    /**
     * 执行关机操作
     */
    public void turnOffBroadcast() {
        speak(FuncTitle.CONTENT_VOICE_HINT_TURN_OFF.toString());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(BaseConstant.ACTION_SHUTDOWN));
            }
        }, 3000);
    }


    public void onEvent(NotificationClickEvent event) {
        Message msg = event.getMessage();
        MessageContent content = msg.getContent();
        switch (msg.getContentType()) {
            case text:

                break;
            case voice:
                final VoiceContent voiceContent = (VoiceContent) msg.getContent();
                final int duration = voiceContent.getDuration();
                final String format = voiceContent.getFormat();
                voiceContent.downloadVoiceFile(msg, new DownloadCompletionCallback() {
                    @Override
                    public void onComplete(int i, String s, File file) {
                        if (i == 0) {
                            String path = file.getPath();
                            Logger.d(TAG, "接收到的语音文件：" + path);
                        }
                    }
                });
                break;
            case image:
                break;
        }
    }

    public void onEvent(ContactNotifyEvent event) {
        Logger.d(TAG, "ContactNotifyEvent");
        if (isCurActivity()) {
            String fromUsername = event.getFromUsername();
            String appkey = event.getfromUserAppKey();
            ContactManager.acceptInvitation(fromUsername, appkey, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    if (i == 0) {
                        Logger.d(TAG, "同意添加好友成功");
                    } else {
                        Logger.d(TAG, "同意添加好友失败");
                    }
                }
            });
        }
    }

    public void onEvent(MessageEvent event) {
        Message msg = event.getMessage();
        MessageContent content = msg.getContent();
        String userName = msg.getFromUser().getUserName();
        String userNickName = msg.getFromUser().getNickname();
        switch (msg.getContentType()) {
            case voice:
                final VoiceContent voiceContent = (VoiceContent) content;
                final int duration = voiceContent.getDuration();
                final String format = voiceContent.getFormat();
                voiceContent.downloadVoiceFile(msg, new DownloadCompletionCallback() {
                    @Override
                    public void onComplete(int i, String s, File file) {
                        if (i == 0) {
                            Logger.d(TAG, "下载成功=" + file.getPath());
//                            PlayManager.playSound(file.getPath(), displayerManager);
                        } else {
                            Logger.d(TAG, "下载失败");
                        }
                    }
                });
                break;

            case text:
                TextContent textContent = (TextContent) content;
                String contentText = textContent.getText();
                Logger.d(TAG, "contentText=" + contentText);
                break;
            case image:

                break;
        }
    }

    // 初始化数据
    public abstract void initData();


    /**
     * 根据id 获取资源
     *
     * @param res
     * @return
     */
    public String getStrByRes(int res) {
        return getResources().getString(res);
    }

    public boolean isCurActivity() {
        String topActivity = PackageUtil.getTopActivityName(PackageUtil.getTopActivityClassName(mContext));
        String curActivity = PackageUtil.getTopActivityName(BaseCommonActivity.this.getLocalClassName());
        return topActivity.equals(curActivity) ? true : false;
    }


    /**
     * 进入二级功能界面
     *
     * @param mFuncDatas
     */
    public void enterTwoFuncActivity(List<FuncBean> mFuncDatas) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.FLAG_TWO_FUNC_DATA, (Serializable) mFuncDatas);
        openActivity(FuncTwoActivity.class, bundle);
    }


    public void enterPlayActivity(String data) {
        enterPlayActivity(Constants.FLAG_ENTER_PLAY_ACTIVITY_RANDOM_PLAY, data);
    }

    /**
     * 进入播放界面————
     *
     * @param data
     */
    public void enterPlayActivity(String flag, String data) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FLAG_ENTER_PLAY_ACTIVITY, flag);
        bundle.putString(Constants.FLAG_ENTER_PLAY_ACTIVITY_DATA, data);
        openActivity(PlayActivity.class, bundle);
    }

    /**
     * 进入播放界面————根据媒体内容
     *
     * @param flag
     * @param mediaInfos
     */
    public void enterPlayActivity(String flag, List<MediaInfo> mediaInfos) {
        // 说明系统中不存在这个activity
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FLAG_ENTER_PLAY_ACTIVITY, flag);
        bundle.putSerializable(Constants.FLAG_ENTER_PLAY_ACTIVITY_DATA, (Serializable) mediaInfos);
        openActivity(PlayActivity.class, bundle);
    }

    public void wakeUpPlay(List<MediaInfo> mediaInfos) {

    }


    /**
     * 进入三级公共界面
     */
    public void enterFuncThreeActivity(List<FuncBean> mFuncDatas) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.FLAG_ENTER_FUNC_THREE_ACTIVITY, (Serializable) mFuncDatas);
        openActivity(FuncThreeActivity.class, bundle);
    }


    /**
     * 进入到视频聊天界面
     *
     * @param sourceChannelid 目标id
     * @param isCall          是否是来电 true -->来电  false-->去电
     */
    public void enterVideoChatActivity(String sourceChannelid, boolean isCall, String flagCall, String CallName) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.isVideoChating_sourceChannelid, sourceChannelid);
        bundle.putBoolean(Constants.isCallVideoChat, isCall);
        bundle.putString(Constants.CallFlag, flagCall);
        bundle.putString(Constants.CallName, CallName);
        openActivity(VideoChatP2PActivity.class, bundle);
    }


    public void enterImChatActivity(int position, RelationInfo relationInfo, List<RelationInfo> bindRelationInfo) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FLAG_CUSTOMERS_INFO_POSIOTION, position + "");
        bundle.putSerializable(Constants.FLAG_CUSTOMERS_INFO, relationInfo);
        bundle.putSerializable(Constants.FLAG_CUSTOMERS_INFO_ALL, (Serializable) bindRelationInfo);
        openActivity(ChatActivity.class, bundle);
    }


    public void toast(String msg) {
        mViewUtil.toast(msg);
    }

    public void toast(int msg) {
        mViewUtil.showShort(mContext, msg);
    }

    public void showProgressDialog(String msg) {
        mViewUtil.showProgressDialog(msg);
    }

    public void dismissProgressDialog() {
        mViewUtil.dismissProgressDialog();
    }

    /**
     * 震动
     *
     * @time 震动时长
     */
    public void vibrate(long time) {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(time);
    }

    /**
     * EventBus 传递数据
     *
     * @param flag
     * @param content
     */
    public void EventBusPost(String flag, String content) {
        EventBus.getDefault().post(new AnyEventType(flag, content));
    }

    /**
     * 播放tts
     *
     * @param content
     * @param flag
     */
    public void speakTTS(String content, String flag) {
        PlayHint.stopVoice();
        TTSEngine.getSingleton().speak(content, flag);
    }

    /**
     * 停止播放TTS
     */
    public void stopTTS() {

        TTSEngine.getSingleton().stop();
//        TuringEngine.getSingleton().stop();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTTS();
        CameraManager.getInstance().removeActivity(this);
        JMessageClient.unRegisterEventReceiver(this);
    }
}
