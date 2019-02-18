package com.hamitao.kids.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.callback.OnJPushMsgReceiverCallBack;
import com.hamitao.kids.model.ReceiveMsgInfo;
import com.hamitao.kids.utils.Util;

import java.util.List;

/**
 * @data on 2018/4/2 17:53
 * @describe:
 */

public class MessageReceiver extends BroadcastReceiver {
    private String TAG = "MessageReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (BaseConstant.MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {

                String message = intent.getStringExtra(BaseConstant.KEY_MESSAGE);
                ReceiveMsgInfo receiveMsgInfo = Util.getReceiveMsgInfo(message);
                String sourceChannelid = receiveMsgInfo.getSource_channelid();//获取source Channelid
                String terminalid = receiveMsgInfo.getTarget_channelid();
                ReceiveMsgInfo.RemoteControlCommandsBean remoteControlCommandsBean = receiveMsgInfo.getRemoteControlCommands();
                String action = remoteControlCommandsBean.getAction();//动作
                Logger.d(TAG, "action===" + action);
                if (BaseConstant.INSTRUCT_NOTICE_TAKEPHOTO.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnPhotoGraphUploadListener(sourceChannelid);
                    }
                } else if (BaseConstant.INSTRUCT_NOTICE_DEVICE_OPEN.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnOpenListener();
                    }
                } else if (BaseConstant.INSTRUCT_NOTICE_DEVICE_CLOSE.equals(action)) {
                    String time = remoteControlCommandsBean.getContents().getContentid().get(0);
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnCloseListener(time);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_PLAY_CONTENT.equals(action)) {
                    List<String> contentList = remoteControlCommandsBean.getContents().getContentid();
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnPlayContentListener(contentList);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_PHONE_VIDEO_DEVICE.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnVideoChatListener(sourceChannelid);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_HANG_UP.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnVideoChatHangUpListener(sourceChannelid);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_LINE_BUSY.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnLineBusyListener(sourceChannelid);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_PHONE_VOICE_DEVICE.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnVoiceChatListener(sourceChannelid);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_PHONE_MONITOR_DEVICE.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnVideoMonitorListener(sourceChannelid);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_DEVICE_INFO.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnReportDeviceInfo(sourceChannelid);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_DEVICE_RENAME.equals(action)) {
                    if (mOnJpushMsg != null) {
                        List<String> contentList = remoteControlCommandsBean.getContents().getContentid();
                        mOnJpushMsg.OnReDeviceName(contentList.get(0));
                    }
                } else if (BaseConstant.INSTRUCT_NOTICE_EYE_PROTECT.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnIsOpenEyeProtect(true);
                    }
                } else if (BaseConstant.INSTRUCT_NOTICE_EYE_UNPROTECT.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnIsOpenEyeProtect(false);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_START_PLAY.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnStartPlay(sourceChannelid);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_PAUSE_PLAY.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnPausePlay();
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_UPDATE_ALARM.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnAlarmClockChange(terminalid);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_UPDATE_COURSE_SCHEDULE.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnQueryPublishedCourseSchedule(terminalid);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_PUSH_HABIT.equals(action)) {
                    if (mOnJpushMsg != null) {
                        List<String> pushHabitList = remoteControlCommandsBean.getContents().getContentid();
                        String pushHabit = pushHabitList.get(1);
                        mOnJpushMsg.OnPushHabitListener(pushHabit);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_PHONE_OPEN.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnPhoneOpenListener(true);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_PHONE_CLOSE.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnPhoneOpenListener(false);
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_TIMER.equals(action)) {
                    if (mOnJpushMsg != null) {
                        List<String> contentList = remoteControlCommandsBean.getContents().getContentid();
                        mOnJpushMsg.OnTimingCloseListener(contentList.get(0));
                    }
                } else if (BaseConstant.INSTRUCT_ACTION_UPDATA_CONTACT.equals(action)) {
                    if (mOnJpushMsg != null) {
                        mOnJpushMsg.OnUpdataContactListener();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private OnJPushMsgReceiverCallBack mOnJpushMsg;

    public void setOnHandleJPushMsgListener(OnJPushMsgReceiverCallBack onJpushMsg) {
        this.mOnJpushMsg = onJpushMsg;
    }

}
