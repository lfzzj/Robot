package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.QueryContentInfo;
import com.hamitao.requestframe.entity.RequestQueryContentInfo;
import com.hamitao.requestframe.view.QueryContentView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/3/21 13:59
 * @describe:
 */

public class QueryContentPresenter extends BasePresenter<QueryContentView> {
    private QueryContentView mView;

    private QueryContentInfo mQueryContentInfo;

    public QueryContentPresenter(Context mContext, QueryContentView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(String maxlimitsbyrandom, RequestQueryContentInfo requestQueryContentInfo) {
        mCompositeSubscription.add(manager.queryContent(maxlimitsbyrandom,requestQueryContentInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryContentInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mQueryContentInfo != null) {
                            mView.onSuccess(mQueryContentInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(QueryContentInfo info) {
                        mQueryContentInfo = info;
                    }
                })
        );
    }
}
