package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.CommonInfo;
import com.hamitao.requestframe.entity.QueryTimerAlarmInfo;
import com.hamitao.requestframe.entity.UpdateTimerAlarmStatusInfo;
import com.hamitao.requestframe.view.CommonInfoView;
import com.hamitao.requestframe.view.QueryTimerAlarmView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpdateTimerAlarmStatusPresenter extends BasePresenter<QueryTimerAlarmView> {
    private CommonInfoView mView;
    private CommonInfo mInfo;

    public UpdateTimerAlarmStatusPresenter(Context mContext, CommonInfoView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(UpdateTimerAlarmStatusInfo updateTimerAlarmStatusInfo) {
        mCompositeSubscription.add(manager.updateTimerAlarmStatus(updateTimerAlarmStatusInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonInfo>() {
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
                    public void onNext(CommonInfo info) {
                        mInfo = info;
                    }
                })
        );
    }
}
