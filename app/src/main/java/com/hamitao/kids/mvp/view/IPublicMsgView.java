package com.hamitao.kids.mvp.view;

import java.util.List;

/**
 * @data on 2018/5/30 14:44
 * @describe:
 */

public interface IPublicMsgView {
    //定时关机
    void onShutDownCallBack(String time);

    //实现关机的功能
    void onShutDownCallBack();

    void onPausePlayCallBack();

    void onResumePlayCallBack(String sourceChannelid);

    void onTurnOnLightCallBack();

    void onVideoChatCallBack(String joinChannel);

    void onVoiceChatCallBack(String sourceChannelid);

    void onVideoMonitorCallBack(String sourceChannelid);

    void OnPhotoGraphCallBack(String sourceChannelid);

    void onUpdataAlarmClockCallBack(String sourceChannelid);

    void OnPlayContentCallback(List<String> contentList);

    void onReportDeviceInfoCallBack(String channelid);

    void onUpdataCourseSchedule(String customerid);

    void OnPushHabitListener(String habit);

    void onVideoChatHangUpCallBack(String joinChannel);

    void OnReDeviceName(String nickName);

    void OnTimingClose(String time);

    void OnIsOpenEyeProtectListener(boolean isOpen);

    void OnPhoneOpenListener(boolean isOpen);

    void OnLineBusyListener(String sourceChannelid);

    void OnUpdataContactListener();
}
