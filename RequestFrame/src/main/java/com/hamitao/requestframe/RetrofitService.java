package com.hamitao.requestframe;


import com.hamitao.requestframe.constant.Constant;
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

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 */
public interface RetrofitService {

    @POST(Constant.REPORT_INFO)
    Observable<CommonInfo> reportDeviceInfo(
            @Body DeviceInfo deviceInfo);

    @POST(Constant.CREATE_DEVICE)
    Observable<CreateDeviceInfo> createDevice(
            @Body RequestDeviceInfo requestDeviceInfo);

    @POST(Constant.QUERY_CONTENT)
    Observable<QueryContentInfo> queryContent(
            @Query("maxlimitsbyrandom") String maxlimitsbyrandom,
            @Query("myresolution") String myresolution,
            @Body RequestQueryContentInfo requestQueryContentInfo);

    @POST(Constant.PARSE_CHINESE)
    Observable<ParseChineseInfo> parseChinese(
            @Body RequestParseInfo info);

    @GET(Constant.QUERY_KEY_ON_OSS)
    Observable<QueryKeyOnOssInfo> QueryKeyOnOss();

    @GET(Constant.CHECK_UPDATA)
    Observable<CheckUpdataInfo> checkUpdata(
            @Query("os") String os,
            @Query("osversion") String osversion,
            @Query("lang") String lang,
            @Query("appversion") String appversion,
            @Query("host") String host,
            @Query("devicetype") String devicetype
    );

    @POST(Constant.PUSH_MSG)
    Observable<PushMsgInfo> pushmsg(
            @Query("channelid") String channelid,
            @Query("vendor") String vendor,
            @Body RequestPushMsgInfo requestPushMsgInfo);

    @GET(Constant.QUERY_RELATION)
    Observable<QueryRelationInfo> queryrelation(
            @Query("myid") String myid
    );

    @GET(Constant.GEN_HTTPURL_FROM_OSSKEY)
    Observable<GenHttpURLFromOSSKeyInfo> genHttpURLFromOSSKey(
            @Query("osskey") String osskey
    );

    @GET(Constant.QUERY_SETTING)
    Observable<QuerySettingInfo> querySetting(
            @Query("terminalid") String terminalid
    );

    @POST(Constant.ADD_TIMER_ALARM)
    Observable<CommonInfo> addTimerlarm(
            @Body RequestAddTimerAlarm requestAddTimerAlarm
    );

    @GET(Constant.QUERY_TIMER_ALARM)
    Observable<QueryTimerAlarmInfo> queryTimerAlarm(
            @Query("terminalid") String terminalid
    );

    @GET(Constant.DELETE_TIMER_ALARM)
    Observable<CommonInfo> deleteTimerAlarm(
            @Query("terminalid") String terminalid,
            @Query("timerid") String timerid
    );

    @POST(Constant.UPDATE_TIMER_ALARM_STATUS)
    Observable<CommonInfo> updateTimerAlarmStatus(
            @Body UpdateTimerAlarmStatusInfo updateTimerAlarmStatusInfo
    );


    @GET(Constant.QUERY_CONTENT_BY_MEDIAID)
    Observable<QueryContentByMediaIdInfo> querycontentByMediaid(
            @Query("mediaid") String mediaid
            ,
            @Query("myresolution") String myresolution
    );

    @GET(Constant.QUERY_CONTENT_BY_CONTENTID)
    Observable<QueryContentByContentIdInfo> querycontentByContentid(
            @Query("contentid") String contentid
            ,
            @Query("myresolution") String myresolution
    );

    @GET(Constant.QUERY_VOICE_RECORDING_BY_ID)
    Observable<QueryVoiceRecordingByIDInfo> queryVoiceRecordingByID(
            @Query("id") String id
    );

    @GET(Constant.QUERY_CONTACT)
    Observable<QueryContactInfo> querycontact(
            @Query("ownerid") String ownerid
    );

    @POST(Constant.ADD_PHOTO)
    Observable<CommonInfo> addPhoto(
            @Body RequestAddPhotoInfo requestAddPhotoInfo);


    @GET(Constant.DELETE_PHOTO)
    Observable<CommonInfo> deletePhoto(
            @Query("ownerid") String ownerid,
            @Query("photoid") String photoid);

    @GET(Constant.QUERY_PHOTO)
    Observable<QueryPhotoInfo> queryPhoto(
            @Query("ownerid") String ownerid
    );

    @GET(Constant.QUERY_PUBLISHED_COURSE_SCHEDULE)
    Observable<QueryPublishedCourseScheduleInfo> queryPublishedCourseSchedule(
            @Query("targetid") String targetid
    );

    @GET(Constant.GET_CONTENT_TREE)
    Observable<GetContentTreeInfo> getContentTree(
            @Query("scenario") String scenario,
            @Query("showway") String showway
    );

    @POST(Constant.BATCH_QUERY_COURSE_SCHEDULE_DETAIL)
    Observable<CourseScheduleInfo> batchQueryCourseScheduleDetail(
            @Body List<RequestCourseScheduleInfo> requestCourseScheduleInfo);

    @GET(Constant.QUERY_NFC_BY_ID)
    Observable<QueryNfcByIdInfo> querynfcbyid(
            @Query("nfcid") String nfcid
    );

    @GET(Constant.GET_P2P_BY_GUID)
    Observable<GetP2PByGuidInfo> getP2PByGuid(
            @Query("guid") String guid,
            @Query("imei") String imei,
            @Query("wifimac") String wifimac,
            @Query("groupid") String groupid
    );

    @GET(Constant.RELEASE_P2P_BY_GUID)
    Observable<ReleaseP2PbyGuidInfo> releaseP2PbyGuid(
            @Query("guid") String guid,
            @Query("token") String token
    );

}
