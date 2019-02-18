package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.GenHttpURLFromOSSKeyInfo;
import com.hamitao.requestframe.view.GenHttpURLFromOSSKeyView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/4/11 9:31
 * @describe:
 */

public class GenHttpURLFromOSSKeyPresenter extends BasePresenter<GenHttpURLFromOSSKeyView>{
    private GenHttpURLFromOSSKeyView mView;
    private GenHttpURLFromOSSKeyInfo mGenHttpURLFromOSSKeyInfo;

    public GenHttpURLFromOSSKeyPresenter(Context mContext, GenHttpURLFromOSSKeyView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(String osskey){
        mCompositeSubscription.add(manager.genHttpURLFromOSSKey(osskey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GenHttpURLFromOSSKeyInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mGenHttpURLFromOSSKeyInfo != null) {
                            mView.onSuccess(mGenHttpURLFromOSSKeyInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(GenHttpURLFromOSSKeyInfo info) {
                        mGenHttpURLFromOSSKeyInfo = info;
                    }
                })
        );
    }
}
