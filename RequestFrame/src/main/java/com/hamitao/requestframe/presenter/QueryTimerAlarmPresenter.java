package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.QueryTimerAlarmInfo;
import com.hamitao.requestframe.view.QueryTimerAlarmView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/4/16 17:56
 * @describe: 查询闹钟
 */

public class QueryTimerAlarmPresenter extends BasePresenter<QueryTimerAlarmView> {
    private QueryTimerAlarmView mView;
    private QueryTimerAlarmInfo mQueryTimerAlarmInfo;

    public QueryTimerAlarmPresenter(Context mContext, QueryTimerAlarmView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(String terminalid) {
        mCompositeSubscription.add(manager.queryTimerAlarm(terminalid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryTimerAlarmInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mQueryTimerAlarmInfo != null) {
                            mView.onSuccess(mQueryTimerAlarmInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(QueryTimerAlarmInfo info) {
                        mQueryTimerAlarmInfo = info;
                    }
                })
        );
    }
}
