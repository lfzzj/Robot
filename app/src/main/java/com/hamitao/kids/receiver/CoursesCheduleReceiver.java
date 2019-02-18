package com.hamitao.kids.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.callback.OnCourseScheduleCallBack;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.utils.AlarmManagerUtil;
import com.hamitao.requestframe.entity.RequestCourseScheduleInfo;

import java.util.List;

/**
 * @data on 2018/6/27 19:58
 * @describe:
 */

public class CoursesCheduleReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        long intervalMillis = intent.getLongExtra(Constants.INTERVALMILLIS, 0);

        List<RequestCourseScheduleInfo> scheduleInfos = (List<RequestCourseScheduleInfo>) intent.getSerializableExtra(Constants.COURSES_CHEDULE_INFO_DATA);

        if (intervalMillis != 0) {
            //设置闹钟循环
            AlarmManagerUtil.setAlarmTime(context, System.currentTimeMillis() + intervalMillis,
                    intent);
        }

        if (onCourseScheduleCallBack != null) {
            if (scheduleInfos != null && scheduleInfos.size() > 0) {
                onCourseScheduleCallBack.onPlay(scheduleInfos);
            } else {
            }
        }

    }

    private OnCourseScheduleCallBack onCourseScheduleCallBack;

    public void setOnCourseScheduleListener(OnCourseScheduleCallBack callBack) {
        this.onCourseScheduleCallBack = callBack;
    }
}
