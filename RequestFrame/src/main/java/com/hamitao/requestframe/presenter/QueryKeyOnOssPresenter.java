package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.QueryKeyOnOssInfo;
import com.hamitao.requestframe.view.QueryKeyOnOssView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/3/28 14:18
 * @describe:
 */

public class QueryKeyOnOssPresenter extends BasePresenter<QueryKeyOnOssView>{
    private QueryKeyOnOssView mView;
    private QueryKeyOnOssInfo mQueryKeyOnOssInfo;

    public QueryKeyOnOssPresenter(Context mContext, QueryKeyOnOssView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(){
        mCompositeSubscription.add(manager.QueryKeyOnOss()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryKeyOnOssInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mQueryKeyOnOssInfo != null) {
                            mView.onSuccess(mQueryKeyOnOssInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(QueryKeyOnOssInfo info) {
                        mQueryKeyOnOssInfo = info;
                    }
                })
        );
    }
}
