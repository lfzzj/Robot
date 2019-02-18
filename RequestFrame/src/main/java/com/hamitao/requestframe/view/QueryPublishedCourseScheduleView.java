package com.hamitao.requestframe.view;

import com.hamitao.requestframe.entity.QueryPublishedCourseScheduleInfo;

/**
 * @data on 2018/6/26 18:19
 * @describe:
 */

public interface QueryPublishedCourseScheduleView extends View {
    void onSuccess(QueryPublishedCourseScheduleInfo info);
}