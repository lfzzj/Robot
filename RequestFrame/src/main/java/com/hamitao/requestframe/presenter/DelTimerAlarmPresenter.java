package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.CommonInfo;
import com.hamitao.requestframe.entity.RequestAddTimerAlarm;
import com.hamitao.requestframe.view.CommonInfoView;
import com.hamitao.requestframe.view.QueryTimerAlarmView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/4/16 17:56
 * @describe: 删除闹钟
 */

public class DelTimerAlarmPresenter extends BasePresenter<QueryTimerAlarmView> {
    private CommonInfoView mView;
    private CommonInfo mCommonInfo;

    public DelTimerAlarmPresenter(Context mContext, CommonInfoView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(String terminalid, String timerid) {
        mCompositeSubscription.add(manager.deleteTimerAlarm(terminalid,timerid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mCommonInfo != null) {
                            mView.onSuccess(mCommonInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(CommonInfo info) {
                        mCommonInfo = info;
                    }
                })
        );
    }
}
