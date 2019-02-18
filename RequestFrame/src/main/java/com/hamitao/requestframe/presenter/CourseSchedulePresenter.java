package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.CourseScheduleInfo;
import com.hamitao.requestframe.entity.RequestCourseScheduleInfo;
import com.hamitao.requestframe.view.CourseScheduleView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/7/3 9:37
 * @describe:
 */

public class CourseSchedulePresenter extends BasePresenter<CourseScheduleView> {
    private CourseScheduleView mView;
    private CourseScheduleInfo mCourseScheduleInfo;


    public CourseSchedulePresenter(Context mContext, CourseScheduleView view) {
        super(mContext, view);
        this.mView = view;
    }

    public void requestData(List<RequestCourseScheduleInfo> infos) {
        mCompositeSubscription.add(manager.batchQueryCourseScheduleDetail(infos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CourseScheduleInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mCourseScheduleInfo != null) {
                            mView.onSuccess(mCourseScheduleInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(CourseScheduleInfo info) {
                        mCourseScheduleInfo = info;
                    }
                })
        );
    }

}
