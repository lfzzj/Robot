package com.hamitao.kids.mvp.imodel;

import com.hamitao.requestframe.entity.RequestParseInfo;
import com.hamitao.requestframe.view.ParseChineseView;
import com.hamitao.requestframe.view.QueryRelationView;

/**
 * @data on 2018/5/30 15:02
 * @describe:
 */

public interface IRobotModel {
    /**
     * 服务器数据请求
     *
     * @param info
     * @param parseChineseView
     */
    void parseChinese(RequestParseInfo info, ParseChineseView parseChineseView);



    /**
     * 关系查询
     * @param myid
     * @param queryRelationView
     */
    void queryRelation(String myid, QueryRelationView queryRelationView);

}
