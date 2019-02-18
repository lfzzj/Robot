package com.hamitao.kids.mvp.ipresenter;

import android.content.BroadcastReceiver;
import android.hardware.Camera;

import com.hamitao.aispeech.model.AlarmInfo;
import com.hamitao.kids.callback.BatteryStateListener;
import com.hamitao.kids.callback.OnAlarmCallBack;
import com.hamitao.kids.callback.OnCourseScheduleCallBack;
import com.hamitao.kids.callback.SIMStateListener;
import com.hamitao.kids.model.CourseScheduleBean;
import com.hamitao.requestframe.entity.QueryTimerAlarmInfo;
import com.hamitao.requestframe.entity.RequestAddTimerAlarm;
import com.hamitao.requestframe.entity.RequestCourseScheduleInfo;
import com.hamitao.requestframe.entity.RequestPushMsgInfo;
import com.hamitao.requestframe.entity.UpdateTimerAlarmStatusInfo;
import com.hamitao.requestframe.view.CommonInfoView;

import java.util.List;

/**
 * @data on 2018/5/30 14:43
 * @describe: 公共功能
 */

public interface IPublicPresenter {

    void shutDown();//关机

    void onPausePlay();//暂停播放

    void onResumePlay(String sourceChannelid);//继续播放

    void onTurnOnLight();//开灯

    void pushMsg(String customerid, RequestPushMsgInfo requestPushMsgInfo);//推送消息

    void registerMessageReceiver();//注册广播

    void unRegisterMessageReceiver();//注销广播

    void takePhoto(String sourceChannelid);//拍照

    void dealPhotoResults(byte[] data, Camera camera, String sourceChannelid);//处理拍照结果

    void queryTimerAlarm(String sourceChannelid);//查询闹钟

    void setAlarmClock(List<QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean> timerAlarmsBeans);//设置闹钟

    void queryContentByMediaid(String id);//根据mediaid查询内容

    void queryContentBContentid(String id);//根据contenrid查询内容

    void queryVoiceRecordingByID(String id);//根据录音ID查询录音信息

    void callPhone(String phoneNum);//打电话

    void queryPublishedCourseSchedule(String targetid);//查询发布的课程表

    void setPublishedCourseSchedule(List<CourseScheduleBean> courseScheduleBean);//设置课程表

    void queryCourseScheduleBroad(List<RequestCourseScheduleInfo> infos);//批量查询课程表内容（录音+内容）

    void registerPowerRecever(BatteryStateListener mPowerView);//充电广播注册

    void unRegisterPowerRecriver();//充电广播注销

    void registerSimStateReceiver(SIMStateListener mListener);//sim卡状态监听

    void unRegisterSimStateReceiver();

    void registerAlarmReceiver(OnAlarmCallBack callBack);//闹钟广播注册

    void unRegisterAlarmReceiver();//闹钟广播注销

    void registerCourseScheduleReceiver(OnCourseScheduleCallBack callBack);//课程表广播注册

    void unRegisterCourseScheduleReceiver();//课程表广播注销

    void addTimerAlarm(RequestAddTimerAlarm requestAddTimerAlarm,CommonInfoView view);//添加闹钟

    void delTimerAlarm(String terminalid, String timerid);//添加闹钟

    void UpdateTimerAlarmStatus(UpdateTimerAlarmStatusInfo info);//更新闹钟

}
