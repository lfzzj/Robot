package com.hamitao.requestframe.view;

import com.hamitao.requestframe.entity.CourseScheduleInfo;

/**
 * @data on 2018/7/3 9:39
 * @describe:
 */

public interface CourseScheduleView extends View {
    void onSuccess(CourseScheduleInfo info);
}
