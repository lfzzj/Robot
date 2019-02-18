package com.hamitao.requestframe.view;

import com.hamitao.requestframe.entity.QueryContentInfo;

/**
 * @data on 2018/3/21 13:59
 * @describe:
 */

public interface QueryContentView extends View {
    void onSuccess(QueryContentInfo info);
}
