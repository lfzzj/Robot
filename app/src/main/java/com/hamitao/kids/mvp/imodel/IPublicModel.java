package com.hamitao.kids.mvp.imodel;


import android.content.BroadcastReceiver;
import android.hardware.Camera;

import com.hamitao.aispeech.model.AlarmInfo;
import com.hamitao.kids.callback.BatteryStateListener;
import com.hamitao.kids.callback.OnAlarmCallBack;
import com.hamitao.kids.callback.OnCourseScheduleCallBack;
import com.hamitao.kids.callback.OnCustomCameraListener;
import com.hamitao.kids.callback.OnJPushMsgReceiverCallBack;
import com.hamitao.kids.callback.SIMStateListener;
import com.hamitao.kids.model.CourseScheduleBean;
import com.hamitao.requestframe.entity.QueryTimerAlarmInfo;
import com.hamitao.requestframe.entity.RequestAddTimerAlarm;
import com.hamitao.requestframe.entity.RequestCourseScheduleInfo;
import com.hamitao.requestframe.entity.RequestPushMsgInfo;
import com.hamitao.requestframe.entity.UpdateTimerAlarmStatusInfo;
import com.hamitao.requestframe.view.CommonInfoView;
import com.hamitao.requestframe.view.CourseScheduleView;
import com.hamitao.requestframe.view.PushMsgView;
import com.hamitao.requestframe.view.QueryContentByContentidView;
import com.hamitao.requestframe.view.QueryContentByMediaidView;
import com.hamitao.requestframe.view.QueryPublishedCourseScheduleView;
import com.hamitao.requestframe.view.QueryTimerAlarmView;
import com.hamitao.requestframe.view.QueryVoiceRecordingByIDView;

import java.util.List;

/**
 * @data on 2018/5/30 17:39
 * @describe:
 */

public interface IPublicModel {
    void pushMsg(String customerid, RequestPushMsgInfo requestPushMsgInfo, PushMsgView pushMsgView);

    /**
     * 极光接受消息的广播
     */
    void registerMessageReceiver(OnJPushMsgReceiverCallBack onJpushMsg);

    /**
     * 注销广播
     */
    void unRegisterMessageReceiver();

    /**
     * 拍照
     *
     * @param pictureCallback
     */
    void takePhoto(Camera.PictureCallback pictureCallback);

    /**
     * 处理拍照结果
     *
     * @param data
     * @param camera
     * @param onCustomCameraListener
     */
    void dealPhotoResults(byte[] data, Camera camera, OnCustomCameraListener onCustomCameraListener);

    /**
     * 查询闹钟
     *
     * @param sourceChannelid
     * @param queryTimerAlarmView
     */
    void queryTimerAlarm(String sourceChannelid, QueryTimerAlarmView queryTimerAlarmView);

    /**
     * 设置闹钟
     *
     * @param timerAlarmsBeans
     */
    void setAlarmClock(List<QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean> timerAlarmsBeans);

    /**
     * 根据mediaid查询内容
     *
     * @param mediaid
     * @param queryContentByMediaidView
     */
    void queryContentByMediaid(String mediaid, QueryContentByMediaidView queryContentByMediaidView);

    /**
     * 根据contentid查询内容
     *
     * @param contentid
     * @param queryContentByContentidView
     */
    void queryContentBContentid(String contentid, QueryContentByContentidView queryContentByContentidView);

    /**
     * 根据录音ID查询录音信息
     *
     * @param id
     * @param queryVoiceRecordingByIDView
     */
    void queryVoiceRecordingByID(String id, QueryVoiceRecordingByIDView queryVoiceRecordingByIDView);

    /**
     * 打电话
     *
     * @param phoneNum
     */
    void callPhone(String phoneNum);

    /**
     * 查询发布的课程表
     *
     * @param targetid
     */
    void queryPublishedCourseSchedule(String targetid, QueryPublishedCourseScheduleView queryPublishedCourseScheduleView);

    /**
     * 设置课程表
     *
     * @param courseScheduleBean
     */
    void setPublishedCourseSchedule(List<CourseScheduleBean> courseScheduleBean);

    /**
     * 批量查询课程表内容(录音+内容)
     *
     * @param info
     */
    void queryCourseScheduleBroad(List<RequestCourseScheduleInfo> info, CourseScheduleView view);

    /**
     * 注册电源广播
     *
     * @param batteryStateListener
     */
    void registerPowerRecever(BatteryStateListener batteryStateListener);

    void unRegisterPowerRecriver();

    /**
     * 注册闹钟广播
     *
     * @param onAlarmCallBack
     */
    void registerAlarmReceiver(OnAlarmCallBack onAlarmCallBack);

    void unRegisterAlarmReceiver();

    /**
     * 注册课程表广播
     * @param callBack
     */
    void registerCourseScheduleReceiver(OnCourseScheduleCallBack callBack);

    void unRegisterCourseScheduleReceiver();

    /**
     * sim状态的监听
     * @param mListener
     */
    void registerSimStateReceiver(SIMStateListener mListener);

    void unRegisterSimStateReceiver();

    /**
     * 添加闹钟
     * @param requestAddTimerAlarm
     * @param commonInfoView
     */
    void addTimerAlarm(RequestAddTimerAlarm requestAddTimerAlarm, CommonInfoView commonInfoView);

    /**
     * 删除闹钟
     * @param terminalid
     * @param timerid
     * @param commonInfoView
     */
    void delTimerAlarm(String terminalid, String timerid , CommonInfoView commonInfoView);

    /**
     * 更新闹钟的状态
     * @param info
     */
    void UpdateTimerAlarmStatus(UpdateTimerAlarmStatusInfo info, CommonInfoView commonInfoView);


}
