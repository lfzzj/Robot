package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.CheckUpdataInfo;
import com.hamitao.requestframe.view.CheckUpdataView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/4/8 11:33
 * @describe:
 */

public class CheckUpdataPresenter extends BasePresenter<CheckUpdataView> {
    private CheckUpdataView mView;
    private CheckUpdataInfo mCheckUpdataInfo;

    public CheckUpdataPresenter(Context mContext, CheckUpdataView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(String os, String osversion, String lang, String appversion, String host, String deviceType) {
        mCompositeSubscription.add(manager.checkUpdata(os, osversion, lang, appversion, host, deviceType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckUpdataInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mCheckUpdataInfo != null) {
                            mView.onSuccess(mCheckUpdataInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(CheckUpdataInfo info) {
                        mCheckUpdataInfo = info;
                    }
                })
        );
    }
}
