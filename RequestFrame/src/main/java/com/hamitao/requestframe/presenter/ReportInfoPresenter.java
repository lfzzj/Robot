package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.DeviceInfo;
import com.hamitao.requestframe.entity.CommonInfo;
import com.hamitao.requestframe.view.CommonInfoView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/3/21 13:36
 * @describe:
 */

public class ReportInfoPresenter extends BasePresenter<CommonInfoView> {
    private CommonInfoView mView;

    private CommonInfo mReportInfo;

    public ReportInfoPresenter(Context context, CommonInfoView view) {
        super(context, view);
        mView = view;
    }

    public void requestData(DeviceInfo deviceInfo){
        mCompositeSubscription.add(manager.reportDeviceInfo(deviceInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mReportInfo != null) {
                            mView.onSuccess(mReportInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(CommonInfo info) {
                        mReportInfo = info;
                    }
                })
        );
    }
}
