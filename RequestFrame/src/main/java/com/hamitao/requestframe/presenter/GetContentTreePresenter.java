package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.GetContentTreeInfo;
import com.hamitao.requestframe.view.GetContentTreeView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/6/29 16:47
 * @describe:
 */

public class GetContentTreePresenter extends BasePresenter<GetContentTreeView> {
    private GetContentTreeView mView;
    private GetContentTreeInfo mGetContentTreeInfo;

    public GetContentTreePresenter(Context mContext, GetContentTreeView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(String scenario, String showway) {
        mCompositeSubscription.add(manager.getContentTree(scenario,showway)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetContentTreeInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mGetContentTreeInfo != null) {
                            mView.onSuccess(mGetContentTreeInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(GetContentTreeInfo info) {
                        mGetContentTreeInfo = info;
                    }
                })
        );
    }
}
