package com.hamitao.requestframe.view;

import com.hamitao.requestframe.entity.QueryTimerAlarmInfo;

/**
 * @data on 2018/4/16 17:57
 * @describe:
 */

public interface QueryTimerAlarmView extends View {
    void onSuccess(QueryTimerAlarmInfo info);
}
