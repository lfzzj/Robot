package com.hamitao.requestframe.manager;

import android.content.Context;

import com.hamitao.requestframe.RetrofitHelper;
import com.hamitao.requestframe.RetrofitService;
import com.hamitao.requestframe.entity.CheckUpdataInfo;
import com.hamitao.requestframe.entity.CommonInfo;
import com.hamitao.requestframe.entity.CourseScheduleInfo;
import com.hamitao.requestframe.entity.CreateDeviceInfo;
import com.hamitao.requestframe.entity.DeviceInfo;
import com.hamitao.requestframe.entity.GenHttpURLFromOSSKeyInfo;
import com.hamitao.requestframe.entity.GetContentTreeInfo;
import com.hamitao.requestframe.entity.GetP2PByGuidInfo;
import com.hamitao.requestframe.entity.ParseChineseInfo;
import com.hamitao.requestframe.entity.PushMsgInfo;
import com.hamitao.requestframe.entity.QueryContactInfo;
import com.hamitao.requestframe.entity.QueryContentByContentIdInfo;
import com.hamitao.requestframe.entity.QueryContentByMediaIdInfo;
import com.hamitao.requestframe.entity.QueryContentInfo;
import com.hamitao.requestframe.entity.QueryKeyOnOssInfo;
import com.hamitao.requestframe.entity.QueryNfcByIdInfo;
import com.hamitao.requestframe.entity.QueryPhotoInfo;
import com.hamitao.requestframe.entity.QueryPublishedCourseScheduleInfo;
import com.hamitao.requestframe.entity.QueryRelationInfo;
import com.hamitao.requestframe.entity.QuerySettingInfo;
import com.hamitao.requestframe.entity.QueryTimerAlarmInfo;
import com.hamitao.requestframe.entity.QueryVoiceRecordingByIDInfo;
import com.hamitao.requestframe.entity.ReleaseP2PbyGuidInfo;
import com.hamitao.requestframe.entity.RequestAddPhotoInfo;
import com.hamitao.requestframe.entity.RequestAddTimerAlarm;
import com.hamitao.requestframe.entity.RequestCourseScheduleInfo;
import com.hamitao.requestframe.entity.RequestDeviceInfo;
import com.hamitao.requestframe.entity.RequestParseInfo;
import com.hamitao.requestframe.entity.RequestPushMsgInfo;
import com.hamitao.requestframe.entity.RequestQueryContentInfo;
import com.hamitao.requestframe.entity.UpdateTimerAlarmStatusInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class DataManager {
    private RetrofitService mRetrofitService;

    public DataManager(Context context, boolean isHaMiTao) {
        this.mRetrofitService = RetrofitHelper.getInstance(context, isHaMiTao).getServer();
    }


    //上报设备信息
    public Observable<CommonInfo> reportDeviceInfo(DeviceInfo info) {
        return mRetrofitService.reportDeviceInfo(info);
    }

    //注册设备
    public Observable<CreateDeviceInfo> createDevice(RequestDeviceInfo deviceInfo) {
        return mRetrofitService.createDevice(deviceInfo);
    }

    //查询内容
    public Observable<QueryContentInfo> queryContent(String maxlimitsbyrandom, RequestQueryContentInfo requestQueryContentInfo) {
        return mRetrofitService.queryContent(maxlimitsbyrandom, "320x240", requestQueryContentInfo);
    }

    //语义解析
    public Observable<ParseChineseInfo> parseChinese(RequestParseInfo chinesetxt) {
        return mRetrofitService.parseChinese(chinesetxt);
    }

    //获取OSS Key
    public Observable<QueryKeyOnOssInfo> QueryKeyOnOss() {
        return mRetrofitService.QueryKeyOnOss();
    }

    //检测升级
    public Observable<CheckUpdataInfo> checkUpdata(String os, String osversion, String lang, String appversion, String host, String deviceType) {
        return mRetrofitService.checkUpdata(os, osversion, lang, appversion, host, deviceType);
    }

    //推送消息
    public Observable<PushMsgInfo> pushmsg(String customerid, String vendor, RequestPushMsgInfo info) {
        return mRetrofitService.pushmsg(customerid, vendor, info);
    }

    //关系查询
    public Observable<QueryRelationInfo> queryrelation(String myid) {
        return mRetrofitService.queryrelation(myid);
    }

    public Observable<GenHttpURLFromOSSKeyInfo> genHttpURLFromOSSKey(String osskey) {
        return mRetrofitService.genHttpURLFromOSSKey(osskey);
    }

    //查询家长控制信息
    public Observable<QuerySettingInfo> querySetting(String terminalid) {
        return mRetrofitService.querySetting(terminalid);
    }

    //添加闹钟
    public Observable<CommonInfo> addTimerAlarm(RequestAddTimerAlarm requestAddTimerAlarm) {
        return mRetrofitService.addTimerlarm(requestAddTimerAlarm);
    }

    //删除闹钟
    public Observable<CommonInfo> deleteTimerAlarm(String terminalid, String timerid) {
        return mRetrofitService.deleteTimerAlarm(terminalid, timerid);
    }

    //更新闹钟状态
    public Observable<CommonInfo> updateTimerAlarmStatus(UpdateTimerAlarmStatusInfo updateTimerAlarmStatusInfo) {
        return mRetrofitService.updateTimerAlarmStatus(updateTimerAlarmStatusInfo);
    }

    //查询闹钟
    public Observable<QueryTimerAlarmInfo> queryTimerAlarm(String terminalid) {
        return mRetrofitService.queryTimerAlarm(terminalid);
    }

    //根据mediaid查询内容
    public Observable<QueryContentByMediaIdInfo> queryContentByMediaid(String mediaid) {
        return mRetrofitService.querycontentByMediaid(mediaid, "320x240");
    }

    //根据内容id查询内容()
    public Observable<QueryContentByContentIdInfo> queryContentByContentid(String contentid) {
        return mRetrofitService.querycontentByContentid(contentid, "320x240");
    }

    //根据录音ID查询录音信息
    public Observable<QueryVoiceRecordingByIDInfo> queryVoiceRecordingByID(String id) {
        return mRetrofitService.queryVoiceRecordingByID(id);
    }

    //查询联系人
    public Observable<QueryContactInfo> queryContact(String ownerid) {
        return mRetrofitService.querycontact(ownerid);
    }

    //增加照片
    public Observable<CommonInfo> addPhoto(RequestAddPhotoInfo requestAddPhotoInfo) {
        return mRetrofitService.addPhoto(requestAddPhotoInfo);
    }

    //删除照片
    public Observable<CommonInfo> deletePhoto(String ownerid, String photoid) {
        return mRetrofitService.deletePhoto(ownerid, photoid);
    }

    //查询照片
    public Observable<QueryPhotoInfo> queryPhoto(String ownerid) {
        return mRetrofitService.queryPhoto(ownerid);
    }

    //查询照片
    public Observable<QueryPublishedCourseScheduleInfo> queryPublishedCourseSchedule(String targetid) {
        return mRetrofitService.queryPublishedCourseSchedule(targetid);
    }

    //获取内容树(层级结构)
    public Observable<GetContentTreeInfo> getContentTree(String scenario, String showway) {
        return mRetrofitService.getContentTree(scenario, showway);
    }

    public Observable<CourseScheduleInfo> batchQueryCourseScheduleDetail(List<RequestCourseScheduleInfo> requestCourseScheduleInfo) {
        return mRetrofitService.batchQueryCourseScheduleDetail(requestCourseScheduleInfo);
    }

    public Observable<QueryNfcByIdInfo> querynfcbyid(String nfcid) {
        return mRetrofitService.querynfcbyid(nfcid);
    }

    public Observable<GetP2PByGuidInfo> getP2PByGuid(String guid, String imei, String wifimac, String groupid) {
        return mRetrofitService.getP2PByGuid(guid, imei, wifimac, groupid);
    }

    public Observable<ReleaseP2PbyGuidInfo> releaseP2PbyGuid(String guid, String token) {
        return mRetrofitService.releaseP2PbyGuid(guid, token);
    }


}
