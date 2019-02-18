package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.CreateDeviceInfo;
import com.hamitao.requestframe.entity.RequestDeviceInfo;
import com.hamitao.requestframe.view.CreateDeviceView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/3/21 11:39
 * @describe:
 */

public class CreateDevicePresenter extends BasePresenter<CreateDeviceView> {

    private CreateDeviceView mView;
    private CreateDeviceInfo mCreateDeviceInfo;
    public CreateDevicePresenter(Context mContext, CreateDeviceView view) {
        super(mContext, view);
        this.mView = view;
    }

    public void requestData(RequestDeviceInfo deviceInfo){
        mCompositeSubscription.add(manager.createDevice(deviceInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreateDeviceInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mCreateDeviceInfo != null) {
                            mView.onSuccess(mCreateDeviceInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(CreateDeviceInfo info) {
                        mCreateDeviceInfo = info;
                    }
                })
        );
    }
}
