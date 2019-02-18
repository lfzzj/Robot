package com.hamitao.kids.callback;

import java.util.List;

/**
 * @data on 2018/4/21 16:43
 * @describe: 接收到极光推送过来的消息回调
 */

public interface OnJPushMsgReceiverCallBack {

    /**
     * 开机
     */
    void OnOpenListener();

    /**
     * 定时关机
     */
    void OnCloseListener(String time);

    /**
     * 播放内容
     *
     * @param contentList
     */
    void OnPlayContentListener(List<String> contentList);


    /**
     * 开始播放
     */
    void OnStartPlay(String sourceChannelid);

    /**
     * 暂停播放
     */
    void OnPausePlay();

    /**
     * 闹钟改变通知回调
     */
    void OnAlarmClockChange(String sourceChannelid);

    /**
     * 拍照上传
     *
     * @param sourceChannelid
     */
    void OnPhotoGraphUploadListener(String sourceChannelid);

    /**
     * 是否开启护眼模式
     *
     * @param isOpen
     */
    void OnIsOpenEyeProtect(boolean isOpen);

    /**
     * 视频聊天
     *
     * @param joinChannel
     */
    void OnVideoChatListener(String joinChannel);

    /**
     * 对方挂断视频聊天
     *
     * @return
     */
    void OnVideoChatHangUpListener(String joinChannel);

    /**
     * 语音聊天
     *
     * @param sourceChannelid
     */
    void OnVoiceChatListener(String sourceChannelid);

    /**
     * 视频监控
     *
     * @param sourceChannelid
     */
    void OnVideoMonitorListener(String sourceChannelid);

    /**
     * 上报设备信息
     *
     * @param channelid
     */
    void OnReportDeviceInfo(String channelid);

    /**
     * 查询发布的课程表
     */
    void OnQueryPublishedCourseSchedule(String customerid);

    /**
     * 习惯培养
     *
     * @param habit
     */
    void OnPushHabitListener(String habit);

    /**
     * 电话接听管理开启
     *
     * @param b
     */
    void OnPhoneOpenListener(boolean b);

    /**
     * 修改极光im的NickName
     *
     * @param nickName
     */
    void OnReDeviceName(String nickName);

    /**
     * 定时关闭
     * @param s
     */
    void OnTimingCloseListener(String s);

    /**
     * 占线
     * @param sourceChannelid
     */
    void OnLineBusyListener(String sourceChannelid);

    /**
     * 更新联系人
     */
    void OnUpdataContactListener();
}
