package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.QueryNfcByIdInfo;
import com.hamitao.requestframe.view.QueryNfcByIdView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/7/11 11:02
 * @describe:
 */

public class QueryNfcByIdPresenter extends BasePresenter<QueryNfcByIdView> {
    private QueryNfcByIdView mView;
    private QueryNfcByIdInfo mQueryNfcByIdInfo;

    public QueryNfcByIdPresenter(Context mContext, QueryNfcByIdView view) {
        super(mContext, view);
        this.mView = view;
    }

    public void requestData(String nfcid) {
        mCompositeSubscription.add(manager.querynfcbyid(nfcid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryNfcByIdInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mQueryNfcByIdInfo != null) {
                            mView.onSuccess(mQueryNfcByIdInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(QueryNfcByIdInfo info) {
                        mQueryNfcByIdInfo = info;
                    }
                })
        );
    }

}
