package com.hamitao.requestframe.presenter;


import android.content.Context;

import com.hamitao.requestframe.entity.QuerySettingInfo;
import com.hamitao.requestframe.view.QuerySettingView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/4/16 15:17
 * @describe:
 */

public class QuerySettingPresenter extends BasePresenter<QuerySettingView> {
    private QuerySettingView mView;
    private QuerySettingInfo mQuerySettingInfo;

    public QuerySettingPresenter(Context mContext, QuerySettingView view) {
        super(mContext, view);
        mView = view;
    }
    public void requestData(String terminalid){
        mCompositeSubscription.add(manager.querySetting(terminalid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QuerySettingInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mQuerySettingInfo != null) {
                            mView.onSuccess(mQuerySettingInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(QuerySettingInfo info) {
                        mQuerySettingInfo = info;
                    }
                })
        );
    }
}
