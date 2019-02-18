package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.QueryPhotoInfo;
import com.hamitao.requestframe.view.QueryPhotoView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/6/11 11:25
 * @describe:
 */

public class QueryPhotoPresenter extends BasePresenter<QueryPhotoView> {
    private QueryPhotoView mView;
    private QueryPhotoInfo mQueryPhotoInfo;

    public QueryPhotoPresenter(Context mContext, QueryPhotoView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(String ownerid) {
        mCompositeSubscription.add(manager.queryPhoto(ownerid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryPhotoInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mQueryPhotoInfo != null) {
                            mView.onSuccess(mQueryPhotoInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(QueryPhotoInfo info) {
                        mQueryPhotoInfo = info;
                    }
                })
        );
    }
}
