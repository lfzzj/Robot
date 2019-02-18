package com.hamitao.kids.callback;

import com.hamitao.requestframe.entity.RequestCourseScheduleInfo;

import java.util.List;

/**
 * 课程表的回调
 */
public interface OnCourseScheduleCallBack {
    void onPlay(List<RequestCourseScheduleInfo> scheduleInfos);
}
