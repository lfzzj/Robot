package com.hamitao.kids.mvp.view;

import android.hardware.Camera;

import com.hamitao.requestframe.entity.CourseScheduleInfo;
import com.hamitao.requestframe.entity.GetP2PByGuidInfo;
import com.hamitao.requestframe.entity.QueryContentByContentIdInfo;
import com.hamitao.requestframe.entity.QueryContentByMediaIdInfo;
import com.hamitao.requestframe.entity.QueryPublishedCourseScheduleInfo;
import com.hamitao.requestframe.entity.QueryTimerAlarmInfo;
import com.hamitao.requestframe.entity.QueryVoiceRecordingByIDInfo;

/**
 * @data on 2018/5/31 15:39
 * @describe:
 */

public interface IPublicView {

    void dealPhotoResults(byte[] data, Camera camera, String sourceChannelid);

    void noticeUIPhotoResults(String tempFile, String sourceChannelid);

    void QueryTimerAlarmSuccess(QueryTimerAlarmInfo info);

    void OnQueryContentByMediaidListener(QueryContentByMediaIdInfo info);

    void OnQueryContentByContentidListener(QueryContentByContentIdInfo info);

    void OnQueryVoiceRecordingByIDListener(QueryVoiceRecordingByIDInfo info);

    void OnQueryPublishedCourseScheduleListener(QueryPublishedCourseScheduleInfo info);

    void onQueryCourseScheduleBroadListener(CourseScheduleInfo info);

}
