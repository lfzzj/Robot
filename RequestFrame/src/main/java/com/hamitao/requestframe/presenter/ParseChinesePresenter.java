package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.ParseChineseInfo;
import com.hamitao.requestframe.entity.RequestParseInfo;
import com.hamitao.requestframe.view.ParseChineseView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/3/27 10:42
 * @describe:
 */

public class ParseChinesePresenter extends BasePresenter<ParseChineseView> {
    private ParseChineseView mView;
    private ParseChineseInfo mParseChineseInfo;

    public ParseChinesePresenter(Context mContext, ParseChineseView view) {
        super(mContext, view);
        mView = view;
    }

    public void requestData(RequestParseInfo chinesetxt){
        mCompositeSubscription.add(manager.parseChinese(chinesetxt)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ParseChineseInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mParseChineseInfo != null) {
                            mView.onSuccess(mParseChineseInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(ParseChineseInfo info) {
                        mParseChineseInfo = info;
                    }
                })
        );
    }

}
