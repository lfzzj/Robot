package com.hamitao.kids.mvp.model;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.SurfaceView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.callback.BatteryStateListener;
import com.hamitao.kids.callback.OnAlarmCallBack;
import com.hamitao.kids.callback.OnCourseScheduleCallBack;
import com.hamitao.kids.callback.OnCustomCameraListener;
import com.hamitao.kids.callback.OnJPushMsgReceiverCallBack;
import com.hamitao.kids.callback.SIMStateListener;
import com.hamitao.kids.camera.CameraUtil;
import com.hamitao.kids.model.CourseScheduleBean;
import com.hamitao.kids.mvp.imodel.IPublicModel;
import com.hamitao.kids.receiver.AlarmReceiver;
import com.hamitao.kids.receiver.BatteryBroadcastReceiver;
import com.hamitao.kids.receiver.CoursesCheduleReceiver;
import com.hamitao.kids.receiver.MessageReceiver;
import com.hamitao.kids.receiver.SimStateReceiver;
import com.hamitao.kids.utils.AlarmManagerUtil;
import com.hamitao.kids.utils.AlarmUtil;
import com.hamitao.requestframe.entity.QueryTimerAlarmInfo;
import com.hamitao.requestframe.entity.RequestAddTimerAlarm;
import com.hamitao.requestframe.entity.RequestCourseScheduleInfo;
import com.hamitao.requestframe.entity.RequestPushMsgInfo;
import com.hamitao.requestframe.entity.UpdateTimerAlarmStatusInfo;
import com.hamitao.requestframe.presenter.AddTimerAlarmPresenter;
import com.hamitao.requestframe.presenter.CourseSchedulePresenter;
import com.hamitao.requestframe.presenter.DelTimerAlarmPresenter;
import com.hamitao.requestframe.presenter.PushMsgPresenter;
import com.hamitao.requestframe.presenter.QueryContentByContentidPresenter;
import com.hamitao.requestframe.presenter.QueryContentByMediaidPresenter;
import com.hamitao.requestframe.presenter.QueryPublishedCourseSchedulePresenter;
import com.hamitao.requestframe.presenter.QueryTimerAlarmPresenter;
import com.hamitao.requestframe.presenter.QueryVoiceRecordingByIDPreserter;
import com.hamitao.requestframe.presenter.UpdateTimerAlarmStatusPresenter;
import com.hamitao.requestframe.view.CommonInfoView;
import com.hamitao.requestframe.view.CourseScheduleView;
import com.hamitao.requestframe.view.PushMsgView;
import com.hamitao.requestframe.view.QueryContentByContentidView;
import com.hamitao.requestframe.view.QueryContentByMediaidView;
import com.hamitao.requestframe.view.QueryPublishedCourseScheduleView;
import com.hamitao.requestframe.view.QueryTimerAlarmView;
import com.hamitao.requestframe.view.QueryVoiceRecordingByIDView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @data on 2018/5/30 17:40
 * @describe:
 */

public class PublicModelImpl implements IPublicModel {
    private Context mContext;
    private MessageReceiver messageReceiver;
    private BatteryBroadcastReceiver batteryBroadcastReceiver;
    private AlarmReceiver alarmReceiver;
    private CoursesCheduleReceiver coursesCheduleReceiver;
    private SimStateReceiver simStateReceiver;

    public PublicModelImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void pushMsg(String customerid, RequestPushMsgInfo requestPushMsgInfo, PushMsgView pushMsgView) {
        PushMsgPresenter pushMsgPresenter = new PushMsgPresenter(mContext, pushMsgView);
        pushMsgPresenter.requestData(customerid,mContext.getString(R.string.vendor), requestPushMsgInfo);
    }

    @Override
    public void registerMessageReceiver(OnJPushMsgReceiverCallBack onJpushMsg) {
        messageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(BaseConstant.MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(messageReceiver, filter);
        messageReceiver.setOnHandleJPushMsgListener(onJpushMsg);
    }

    @Override
    public void unRegisterMessageReceiver() {
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(messageReceiver);
    }


    @Override
    public void takePhoto(Camera.PictureCallback pictureCallback) {
        Camera camera = CameraUtil.getCameraInstance();
        if (camera != null) {
            SurfaceView dummy = new SurfaceView(mContext);
            try {
                camera.setPreviewDisplay(dummy.getHolder());
            } catch (IOException e) {
                e.printStackTrace();
            }
            camera.startPreview();
            camera.takePicture(null, null, pictureCallback);
        }
    }

    @Override
    public void dealPhotoResults(byte[] data, Camera camera, OnCustomCameraListener onCustomCameraListener) {
        File tempFile = new File("/storage/emulated/0/temp.png");
        if (tempFile.exists()) {
            tempFile.delete();
        }
        try {
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(data);
            fos.close();
            onCustomCameraListener.onCameraSuccess(tempFile.getAbsolutePath());
            camera.release();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void queryTimerAlarm(String sourceChannelid, QueryTimerAlarmView queryTimerAlarmView) {
        QueryTimerAlarmPresenter presenter = new QueryTimerAlarmPresenter(mContext, queryTimerAlarmView);
        presenter.requestData(sourceChannelid);
    }

    @Override
    public void setAlarmClock(List<QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean> timerAlarmsLists) {
        if (timerAlarmsLists != null && timerAlarmsLists.size() != 0) {
            for (int i = 0; i < timerAlarmsLists.size(); i++) {
                QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean timerAlarmsBean = timerAlarmsLists.get(i);
                String status = timerAlarmsBean.getStatus();//闹钟状态
                if (BaseConstant.ENABLE.equals(status)) {//判断闹钟状态是否开启
                    String name = timerAlarmsBean.getName();//闹钟名称
                    int idx = Integer.parseInt(timerAlarmsBean.getIdx());
                    String id = timerAlarmsBean.getId();
                    int ring = 1;
                    List<QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean.TimersBean> timersBeans = timerAlarmsBean.getTimers();
                    if (timersBeans != null && timersBeans.size() != 0) {
                        QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean.TimersBean timersBean = timersBeans.get(0);
                        if (timersBean != null) {
                            String starttime = timersBean.getStarttime();
                            String days = timersBean.getDay();
                            int cycle;
                            if (!TextUtils.isEmpty(days)) {
                                cycle = AlarmUtil.getWeekBinaryNumber(AlarmUtil.getDayLists(days));
                            } else {
                                cycle = -1;
                            }
                            if (!TextUtils.isEmpty(starttime)) {
                                //设置闹钟
                                AlarmUtil.setAlarm(mContext, starttime, cycle, ring, name, idx,id);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void queryContentByMediaid(String mediaid, QueryContentByMediaidView queryContentByMediaidView) {
        QueryContentByMediaidPresenter mPresenter = new QueryContentByMediaidPresenter(mContext, queryContentByMediaidView);
        mPresenter.requestData(mediaid);
    }

    @Override
    public void queryContentBContentid(String contentid, QueryContentByContentidView queryContentByContentidView) {
        QueryContentByContentidPresenter presenter = new QueryContentByContentidPresenter(mContext, queryContentByContentidView);
        presenter.requestData(contentid);
    }

    @Override
    public void queryVoiceRecordingByID(String id, QueryVoiceRecordingByIDView queryVoiceRecordingByIDView) {
        QueryVoiceRecordingByIDPreserter preserter = new QueryVoiceRecordingByIDPreserter(mContext, queryVoiceRecordingByIDView);
        preserter.requestData(id);
    }


    @Override
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    @Override
    public void queryPublishedCourseSchedule(String targetid, QueryPublishedCourseScheduleView queryPublishedCourseScheduleView) {
        QueryPublishedCourseSchedulePresenter presenter = new QueryPublishedCourseSchedulePresenter(mContext, queryPublishedCourseScheduleView);
        presenter.requestData(targetid);
    }

    @Override
    public void setPublishedCourseSchedule(List<CourseScheduleBean> courseScheduleBeans) {
        if (courseScheduleBeans != null && courseScheduleBeans.size() != 0) {
            for (int i = 0; i < courseScheduleBeans.size(); i++) {
                CourseScheduleBean courseScheduleBean = courseScheduleBeans.get(i);
                String startTime = courseScheduleBean.getStartTime();
                int id = Integer.parseInt(courseScheduleBean.getId());
                List<RequestCourseScheduleInfo> requestCourseScheduleInfos = courseScheduleBean.getRequestCourseScheduleInfos();
                if (requestCourseScheduleInfos != null && requestCourseScheduleInfos.size() != 0) {
                    //反转播放顺序
                    Collections.reverse(requestCourseScheduleInfos);
                    AlarmUtil.setCourseSchedule(mContext, startTime, id, requestCourseScheduleInfos);
                }
            }
        }
    }

    @Override
    public void queryCourseScheduleBroad(List<RequestCourseScheduleInfo> infos, CourseScheduleView view) {
        CourseSchedulePresenter presenter = new CourseSchedulePresenter(mContext, view);
        presenter.requestData(infos);
    }

    @Override
    public void registerPowerRecever(BatteryStateListener batteryStateListener) {
        batteryBroadcastReceiver = new BatteryBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        mContext.registerReceiver(batteryBroadcastReceiver, filter);
        batteryBroadcastReceiver.setBatteryStateListener(batteryStateListener);
    }

    @Override
    public void unRegisterPowerRecriver() {
        mContext.unregisterReceiver(batteryBroadcastReceiver);
    }

    @Override
    public void registerAlarmReceiver(OnAlarmCallBack onAlarmCallBack) {
        alarmReceiver = new AlarmReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(AlarmManagerUtil.ALARM_ACTION);
        mContext.registerReceiver(alarmReceiver, filter);
        alarmReceiver.setOnAlarmListener(onAlarmCallBack);
    }

    @Override
    public void unRegisterAlarmReceiver() {
        mContext.unregisterReceiver(alarmReceiver);
    }


    @Override
    public void registerCourseScheduleReceiver(OnCourseScheduleCallBack callBack) {
        coursesCheduleReceiver = new CoursesCheduleReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(AlarmManagerUtil.COURSES_CHEDULE_ACTION);
        mContext.registerReceiver(coursesCheduleReceiver, filter);
        coursesCheduleReceiver.setOnCourseScheduleListener(callBack);
    }

    @Override
    public void unRegisterCourseScheduleReceiver() {
        mContext.unregisterReceiver(coursesCheduleReceiver);
    }

    @Override
    public void registerSimStateReceiver(SIMStateListener mListener) {
        simStateReceiver = new SimStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(simStateReceiver.ACTION_SIM_STATE_CHANGED);
        mContext.registerReceiver(simStateReceiver, filter);
        simStateReceiver.setSIMStateListener(mListener);
    }

    @Override
    public void unRegisterSimStateReceiver() {

    }

    @Override
    public void addTimerAlarm(RequestAddTimerAlarm requestAddTimerAlarm, CommonInfoView commonInfoView) {
        AddTimerAlarmPresenter presenter = new AddTimerAlarmPresenter(mContext, commonInfoView);
        presenter.requestData(requestAddTimerAlarm);
    }

    @Override
    public void delTimerAlarm(String terminalid, String timerid, CommonInfoView commonInfoView) {
        DelTimerAlarmPresenter presenter = new DelTimerAlarmPresenter(mContext, commonInfoView);
        presenter.requestData(terminalid, timerid);
    }

    @Override
    public void UpdateTimerAlarmStatus(UpdateTimerAlarmStatusInfo info, CommonInfoView commonInfoView) {
        UpdateTimerAlarmStatusPresenter presenter = new UpdateTimerAlarmStatusPresenter(mContext, commonInfoView);
        presenter.requestData(info);
    }

}
