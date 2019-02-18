package com.hamitao.kids.mvp.model;

import android.content.Context;

import com.hamitao.kids.mvp.imodel.IRobotModel;
import com.hamitao.requestframe.entity.RequestParseInfo;
import com.hamitao.requestframe.presenter.ParseChinesePresenter;
import com.hamitao.requestframe.presenter.QueryRelationPresenter;
import com.hamitao.requestframe.view.ParseChineseView;
import com.hamitao.requestframe.view.QueryRelationView;

/**
 * @data on 2018/5/30 15:02
 * @describe:
 */

public class RobotModelImpl implements IRobotModel {
    private Context mContext;

    public RobotModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void parseChinese(RequestParseInfo info, ParseChineseView parseChineseView) {
        ParseChinesePresenter parseChinesePresenter = new ParseChinesePresenter(mContext, parseChineseView);
        parseChinesePresenter.requestData(info);
    }


    @Override
    public void queryRelation(String myid, QueryRelationView queryRelationView) {
        QueryRelationPresenter queryRelationPresenter = new QueryRelationPresenter(mContext, queryRelationView);
        queryRelationPresenter.requestData(myid);
    }

}
