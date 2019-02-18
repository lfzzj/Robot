package com.hamitao.requestframe.view;

import com.hamitao.requestframe.entity.QuerySettingInfo;

/**
 * @data on 2018/4/16 15:18
 * @describe:
 */

public interface QuerySettingView extends View {
    void onSuccess(QuerySettingInfo info);
}
