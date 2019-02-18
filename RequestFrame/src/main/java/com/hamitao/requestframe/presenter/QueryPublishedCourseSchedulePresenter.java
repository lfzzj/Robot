package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.QueryPublishedCourseScheduleInfo;
import com.hamitao.requestframe.view.QueryPublishedCourseScheduleView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/6/26 18:19
 * @describe:
 */

public class QueryPublishedCourseSchedulePresenter extends BasePresenter<QueryPublishedCourseScheduleView> {
    private QueryPublishedCourseScheduleView mView;
    private QueryPublishedCourseScheduleInfo mQueryPublishedCourseScheduleInfo;

    public QueryPublishedCourseSchedulePresenter(Context mContext, QueryPublishedCourseScheduleView view) {
        super(mContext, view);
        this.mView = view;
    }

    public void requestData(String targetid) {
        mCompositeSubscription.add(manager.queryPublishedCourseSchedule(targetid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryPublishedCourseScheduleInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mQueryPublishedCourseScheduleInfo != null) {
                            mView.onSuccess(mQueryPublishedCourseScheduleInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(QueryPublishedCourseScheduleInfo info) {
                        mQueryPublishedCourseScheduleInfo = info;
                    }
                })
        );
    }
}
