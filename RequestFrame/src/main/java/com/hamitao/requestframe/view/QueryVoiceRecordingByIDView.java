package com.hamitao.requestframe.view;

import com.hamitao.requestframe.entity.QueryVoiceRecordingByIDInfo;

/**
 * @data on 2018/6/30 10:47
 * @describe:
 */

public interface QueryVoiceRecordingByIDView extends View {
    void onSuccess(QueryVoiceRecordingByIDInfo info);
}
