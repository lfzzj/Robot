package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.QueryContentByMediaIdInfo;
import com.hamitao.requestframe.view.QueryContentByMediaidView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/5/21 9:51
 * @describe:
 */

public class QueryContentByMediaidPresenter extends BasePresenter<QueryContentByMediaidView> {
    private QueryContentByMediaidView mView;
    private QueryContentByMediaIdInfo mInfo;

    public QueryContentByMediaidPresenter(Context mContext, QueryContentByMediaidView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(String mediaid){
        mCompositeSubscription.add(manager.queryContentByMediaid(mediaid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryContentByMediaIdInfo>() {
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
                    public void onNext(QueryContentByMediaIdInfo info) {
                        mInfo = info;
                    }
                })
        );
    }
}
