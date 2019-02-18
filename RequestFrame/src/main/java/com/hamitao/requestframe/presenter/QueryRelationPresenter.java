package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.QueryRelationInfo;
import com.hamitao.requestframe.view.QueryRelationView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/4/10 15:52
 * @describe: 关系查询
 */

public class QueryRelationPresenter extends BasePresenter<QueryRelationView> {
    private QueryRelationView mView;
    private QueryRelationInfo mQueryRelationInfo;

    public QueryRelationPresenter(Context mContext, QueryRelationView view) {
        super(mContext, view);
        mView = view;
    }


    public void requestData(String myid){
        mCompositeSubscription.add(manager.queryrelation(myid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryRelationInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mQueryRelationInfo != null) {
                            mView.onSuccess(mQueryRelationInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(QueryRelationInfo info) {
                        mQueryRelationInfo = info;
                    }
                })
        );
    }
}
