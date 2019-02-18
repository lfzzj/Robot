package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.ReleaseP2PbyGuidInfo;
import com.hamitao.requestframe.view.ReleaseP2PbyGuidView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReleaseP2PbyGuidPresenter extends BasePresenter<ReleaseP2PbyGuidView> {
    private ReleaseP2PbyGuidView mView;
    private Context mContext;

    public ReleaseP2PbyGuidPresenter(Context mContext, ReleaseP2PbyGuidView view) {
        super(mContext, view, false);
        this.mView = view;
        this.mContext = mContext;
    }

    public void requestData(String guid, String token) {

        mCompositeSubscription.add(manager.releaseP2PbyGuid(guid, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReleaseP2PbyGuidInfo> () {
                    @Override
                    public void onCompleted() {
                        mView.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(ReleaseP2PbyGuidInfo o) {
                    }


                })
        );
    }
}
