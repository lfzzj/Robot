package com.hamitao.kids.mvp.view;

import com.hamitao.requestframe.entity.QueryRelationInfo;

/**
 * @data on 2018/6/6 11:00
 * @describe:
 */

public interface IQueryRelationView {
    void OnSuccessListener(QueryRelationInfo info);

    void OnErrorListener(String result);
}
