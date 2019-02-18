package com.hamitao.requestframe.view;

import com.hamitao.requestframe.entity.QueryNfcByIdInfo;

/**
 * @data on 2018/7/11 11:02
 * @describe:
 */

public interface QueryNfcByIdView extends View {
    void onSuccess(QueryNfcByIdInfo querynfcbyidInfo);
}
