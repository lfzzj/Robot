package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.QueryContactInfo;
import com.hamitao.requestframe.view.QueryContactView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/5/21 11:54
 * @describe: 查询联系人
 */

public class QueryContactPresenter extends BasePresenter<QueryContactView> {
    private QueryContactView mView;
    private QueryContactInfo mInfo;

    public QueryContactPresenter(Context mContext, QueryContactView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(String ownerid){
        mCompositeSubscription.add(manager.queryContact(ownerid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryContactInfo>() {
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
                    public void onNext(QueryContactInfo info) {
                        mInfo = info;
                    }
                })
        );
    }
}
