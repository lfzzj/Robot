package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.QueryVoiceRecordingByIDInfo;
import com.hamitao.requestframe.view.QueryVoiceRecordingByIDView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/6/30 10:46
 * @describe:
 */

public class QueryVoiceRecordingByIDPreserter extends BasePresenter<QueryVoiceRecordingByIDView> {
    private QueryVoiceRecordingByIDView mView;
    private QueryVoiceRecordingByIDInfo mInfo;

    public QueryVoiceRecordingByIDPreserter(Context mContext, QueryVoiceRecordingByIDView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(String id) {
        mCompositeSubscription.add(manager.queryVoiceRecordingByID(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryVoiceRecordingByIDInfo>() {
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
                    public void onNext(QueryVoiceRecordingByIDInfo info) {
                        mInfo = info;
                    }
                })
        );
    }
}
