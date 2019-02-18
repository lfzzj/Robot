package com.hamitao.requestframe.view;


import com.hamitao.requestframe.entity.QueryRelationInfo;

/**
 * @data on 2018/4/10 15:54
 * @describe:
 */

public interface QueryRelationView extends View {
    void onSuccess(QueryRelationInfo info);
}
