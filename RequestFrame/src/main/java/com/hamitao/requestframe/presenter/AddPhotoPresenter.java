package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.CommonInfo;
import com.hamitao.requestframe.entity.RequestAddPhotoInfo;
import com.hamitao.requestframe.view.CommonInfoView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/6/11 11:25
 * @describe:
 */

public class AddPhotoPresenter extends BasePresenter<CommonInfoView> {
    private CommonInfoView mView;
    private CommonInfo mReportInfo;

    public AddPhotoPresenter(Context mContext, CommonInfoView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(RequestAddPhotoInfo requestAddPhotoInfo) {
        mCompositeSubscription.add(manager.addPhoto(requestAddPhotoInfo)
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
