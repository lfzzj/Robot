package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.QueryContentByContentIdInfo;
import com.hamitao.requestframe.view.QueryContentByContentidView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/5/21 11:54
 * @describe:
 */

public class QueryContentByContentidPresenter extends BasePresenter<QueryContentByContentidView> {
    private QueryContentByContentidView mView;
    private QueryContentByContentIdInfo mInfo;

    public QueryContentByContentidPresenter(Context mContext, QueryContentByContentidView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(String contentid){
        mCompositeSubscription.add(manager.queryContentByContentid(contentid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryContentByContentIdInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mInfo != null) {
                            mView.onSuccess(mInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(QueryContentByContentIdInfo info) {
                        mInfo = info;
                    }
                })
        );
    }
}
