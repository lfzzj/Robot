package com.hamitao.kids.mvp.presenter;

import android.content.Context;
import android.hardware.Camera;

import com.hamitao.aispeech.model.AlarmInfo;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.callback.BatteryStateListener;
import com.hamitao.kids.callback.OnAlarmCallBack;
import com.hamitao.kids.callback.OnCourseScheduleCallBack;
import com.hamitao.kids.callback.OnCustomCameraListener;
import com.hamitao.kids.callback.OnJPushMsgReceiverCallBack;
import com.hamitao.kids.callback.SIMStateListener;
import com.hamitao.kids.model.CourseScheduleBean;
import com.hamitao.kids.mvp.imodel.IPublicModel;
import com.hamitao.kids.mvp.ipresenter.IPublicPresenter;
import com.hamitao.kids.mvp.model.PublicModelImpl;
import com.hamitao.kids.mvp.view.IPublicMsgView;
import com.hamitao.kids.mvp.view.IPublicView;
import com.hamitao.requestframe.entity.CommonInfo;
import com.hamitao.requestframe.entity.CourseScheduleInfo;
import com.hamitao.requestframe.entity.PushMsgInfo;
import com.hamitao.requestframe.entity.QueryContentByContentIdInfo;
import com.hamitao.requestframe.entity.QueryContentByMediaIdInfo;
import com.hamitao.requestframe.entity.QueryPublishedCourseScheduleInfo;
import com.hamitao.requestframe.entity.QueryTimerAlarmInfo;
import com.hamitao.requestframe.entity.QueryVoiceRecordingByIDInfo;
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
 * @data on 2018/5/30 14:44
 * @describe:
 */

public class PublicPresenterImpl implements IPublicPresenter {
    private IPublicModel iPublicModel;
    private IPublicMsgView mMsgView;
    private IPublicView mView;

    public PublicPresenterImpl(Context context, IPublicMsgView iPublicView, IPublicView mView) {
        iPublicModel = new PublicModelImpl(context);
        this.mMsgView = iPublicView;
        this.mView = mView;
    }

    public PublicPresenterImpl(Context context) {
        iPublicModel = new PublicModelImpl(context);
    }

    @Override
    public void shutDown() {
        mMsgView.onShutDownCallBack();
    }

    @Override
    public void onPausePlay() {
        mMsgView.onPausePlayCallBack();
    }

    @Override
    public void onResumePlay(String sourceChannelid) {
        mMsgView.onResumePlayCallBack(sourceChannelid);
    }

    @Override
    public void onTurnOnLight() {
        mMsgView.onTurnOnLightCallBack();
    }

    @Override
    public void pushMsg(String customerid, RequestPushMsgInfo requestPushMsgInfo) {
        iPublicModel.pushMsg(customerid, requestPushMsgInfo, new PushMsgView() {
            @Override
            public void onSuccess(PushMsgInfo info) {
            }

            @Override
            public void onError(String result) {
            }
        });
    }

    @Override
    public void registerMessageReceiver() {
        iPublicModel.registerMessageReceiver(new OnJPushMsgReceiverCallBack() {
            @Override
            public void OnOpenListener() {//开机

            }

            @Override
            public void OnCloseListener(String time) {//定时关机
                mMsgView.onShutDownCallBack(time);
            }

            @Override
            public void OnPlayContentListener(List<String> contentList) {
                mMsgView.OnPlayContentCallback(contentList);
            }

            @Override
            public void OnStartPlay(String sourceChannelid) {
                mMsgView.onResumePlayCallBack(sourceChannelid);
            }

            @Override
            public void OnPausePlay() {
                mMsgView.onPausePlayCallBack();
            }

            @Override
            public void OnAlarmClockChange(String sourceChannelid) {
                mMsgView.onUpdataAlarmClockCallBack(sourceChannelid);
            }

            @Override
            public void OnPhotoGraphUploadListener(String sourceChannelid) {
                mMsgView.OnPhotoGraphCallBack(sourceChannelid);
            }

            @Override
            public void OnIsOpenEyeProtect(boolean isOpen) {
                mMsgView.OnIsOpenEyeProtectListener(isOpen);
            }

            @Override
            public void OnVideoChatListener(String sourceChannelid) {
                mMsgView.onVideoChatCallBack(sourceChannelid);
            }

            @Override
            public void OnVideoChatHangUpListener(String joinChannel) {
                mMsgView.onVideoChatHangUpCallBack(joinChannel);
            }

            @Override
            public void OnVoiceChatListener(String sourceChannelid) {
                mMsgView.onVoiceChatCallBack(sourceChannelid);
            }

            @Override
            public void OnVideoMonitorListener(String sourceChannelid) {
                mMsgView.onVideoMonitorCallBack(sourceChannelid);
            }

            @Override
            public void OnReportDeviceInfo(String channelid) {
                mMsgView.onReportDeviceInfoCallBack(channelid);
            }

            @Override
            public void OnQueryPublishedCourseSchedule(String customerid) {
                mMsgView.onUpdataCourseSchedule(customerid);
            }

            @Override
            public void OnPushHabitListener(String habit) {
                mMsgView.OnPushHabitListener(habit);
            }

            @Override
            public void OnPhoneOpenListener(boolean isOpen) {
                mMsgView.OnPhoneOpenListener(isOpen);
            }

            @Override
            public void OnReDeviceName(String nickName) {
                mMsgView.OnReDeviceName(nickName);
            }

            @Override
            public void OnTimingCloseListener(String time) {
                MyApp.getInstance().setTimingCloseTime(Integer.parseInt(time));
                mMsgView.OnTimingClose(time);
            }

            @Override
            public void OnLineBusyListener(String sourceChannelid) {
                mMsgView.OnLineBusyListener(sourceChannelid);
            }

            @Override
            public void OnUpdataContactListener() {
                mMsgView.OnUpdataContactListener();
            }
        });
    }

    @Override
    public void unRegisterMessageReceiver() {
        iPublicModel.unRegisterMessageReceiver();
    }


    @Override
    public void takePhoto(String sourceChannelid) {
        iPublicModel.takePhoto(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                mView.dealPhotoResults(data, camera, sourceChannelid);
            }
        });
    }

    @Override
    public void dealPhotoResults(byte[] data, Camera camera, String sourceChannelid) {
        iPublicModel.dealPhotoResults(data, camera, new OnCustomCameraListener() {
            @Override
            public void onCameraSuccess(String tempFile) {
                mView.noticeUIPhotoResults(tempFile, sourceChannelid);
            }
        });
    }

    @Override
    public void queryTimerAlarm(String sourceChannelid) {
        iPublicModel.queryTimerAlarm(sourceChannelid, new QueryTimerAlarmView() {
            @Override
            public void onSuccess(QueryTimerAlarmInfo info) {
                mView.QueryTimerAlarmSuccess(info);
            }

            @Override
            public void onError(String result) {

            }
        });
    }

    @Override
    public void setAlarmClock(List<QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean> timerAlarmsBeans) {
        iPublicModel.setAlarmClock(timerAlarmsBeans);
    }

    @Override
    public void queryContentByMediaid(String mediaid) {
        iPublicModel.queryContentByMediaid(mediaid, new QueryContentByMediaidView() {
            @Override
            public void onSuccess(QueryContentByMediaIdInfo info) {
                if (mView != null) {
                    mView.OnQueryContentByMediaidListener(info);
                }

            }

            @Override
            public void onError(String result) {
                if (mView != null) {
                    mView.OnQueryContentByMediaidListener(null);
                }

            }
        });
    }

    @Override
    public void queryContentBContentid(String contentid) {
        iPublicModel.queryContentBContentid(contentid, new QueryContentByContentidView() {
            @Override
            public void onSuccess(QueryContentByContentIdInfo info) {
                mView.OnQueryContentByContentidListener(info);
            }

            @Override
            public void onError(String result) {
                mView.OnQueryContentByContentidListener(null);
            }
        });
    }

    @Override
    public void queryVoiceRecordingByID(String id) {
        iPublicModel.queryVoiceRecordingByID(id, new QueryVoiceRecordingByIDView() {
            @Override
            public void onSuccess(QueryVoiceRecordingByIDInfo info) {
                if (mView != null) {
                    mView.OnQueryVoiceRecordingByIDListener(info);
                }
            }

            @Override
            public void onError(String result) {
                if (mView != null) {
                    mView.OnQueryVoiceRecordingByIDListener(null);
                }
            }
        });
    }

    @Override
    public void callPhone(String phoneNum) {
        iPublicModel.callPhone(phoneNum);
    }

    @Override
    public void queryPublishedCourseSchedule(String targetid) {
        iPublicModel.queryPublishedCourseSchedule(targetid, new QueryPublishedCourseScheduleView() {
            @Override
            public void onSuccess(QueryPublishedCourseScheduleInfo info) {
                mView.OnQueryPublishedCourseScheduleListener(info);
            }

            @Override
            public void onError(String result) {

            }
        });
    }

    @Override
    public void setPublishedCourseSchedule(List<CourseScheduleBean> courseScheduleBeans) {//设置课程表
        iPublicModel.setPublishedCourseSchedule(courseScheduleBeans);
    }

    @Override
    public void queryCourseScheduleBroad(List<RequestCourseScheduleInfo> infos) {
        iPublicModel.queryCourseScheduleBroad(infos, new CourseScheduleView() {
            @Override
            public void onSuccess(CourseScheduleInfo info) {
                mView.onQueryCourseScheduleBroadListener(info);
            }

            @Override
            public void onError(String result) {
            }
        });
    }

    @Override
    public void registerPowerRecever(BatteryStateListener mPowerView) {
        iPublicModel.registerPowerRecever(mPowerView);
    }

    @Override
    public void unRegisterPowerRecriver() {
        iPublicModel.unRegisterPowerRecriver();
    }

    @Override
    public void registerSimStateReceiver(SIMStateListener mListener) {
        iPublicModel.registerSimStateReceiver(mListener);
    }

    @Override
    public void unRegisterSimStateReceiver() {
        iPublicModel.unRegisterSimStateReceiver();
    }

    @Override
    public void registerAlarmReceiver(OnAlarmCallBack callBack) {
        iPublicModel.registerAlarmReceiver(callBack);
    }

    @Override
    public void unRegisterAlarmReceiver() {
        iPublicModel.unRegisterAlarmReceiver();
    }

    @Override
    public void registerCourseScheduleReceiver(OnCourseScheduleCallBack callBack) {
        iPublicModel.registerCourseScheduleReceiver(callBack);
    }

    @Override
    public void unRegisterCourseScheduleReceiver() {
        iPublicModel.unRegisterCourseScheduleReceiver();
    }

    @Override
    public void addTimerAlarm(RequestAddTimerAlarm requestAddTimerAlarm, CommonInfoView view) {
        iPublicModel.addTimerAlarm(requestAddTimerAlarm, new CommonInfoView() {
            @Override
            public void onError(String result) {
            }

            @Override
            public void onSuccess(CommonInfo info) {
                view.onSuccess(info);
            }
        });
    }

    @Override
    public void delTimerAlarm(String terminalid, String timerid) {
        iPublicModel.delTimerAlarm(terminalid, timerid, new CommonInfoView() {
            @Override
            public void onSuccess(CommonInfo info) {
            }

            @Override
            public void onError(String result) {
            }
        });
    }

    @Override
    public void UpdateTimerAlarmStatus(UpdateTimerAlarmStatusInfo info) {
        iPublicModel.UpdateTimerAlarmStatus(info, new CommonInfoView() {
            @Override
            public void onSuccess(CommonInfo info) {
                Logger.d("way", "UpdateTimerAlarmStatus  onSuccess");
            }

            @Override
            public void onError(String result) {
                Logger.d("way", "UpdateTimerAlarmStatus  onError");

            }
        });
    }
}
