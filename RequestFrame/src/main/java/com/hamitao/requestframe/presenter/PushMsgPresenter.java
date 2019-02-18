package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.PushMsgInfo;
import com.hamitao.requestframe.entity.RequestPushMsgInfo;
import com.hamitao.requestframe.view.PushMsgView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/4/8 18:34
 * @describe:
 */

public class PushMsgPresenter extends BasePresenter<PushMsgView> {
    private PushMsgView mView;
    private PushMsgInfo mPushMsgInfo;


    public PushMsgPresenter(Context mContext, PushMsgView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(String customerid, String vendor,RequestPushMsgInfo requestPushMsgInfo) {
        mCompositeSubscription.add(manager.pushmsg(customerid,vendor,requestPushMsgInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PushMsgInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mPushMsgInfo != null) {
                            mView.onSuccess(mPushMsgInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(PushMsgInfo info) {
                        mPushMsgInfo = info;
                    }
                })
        );
    }
}
