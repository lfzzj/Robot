package com.hamitao.requestframe.presenter;

import android.content.Context;
import android.content.Intent;

import com.hamitao.framework.utils.Logger;
import com.hamitao.requestframe.manager.DataManager;
import com.hamitao.requestframe.view.GetP2PByGuidView;
import com.hamitao.requestframe.view.View;

import rx.subscriptions.CompositeSubscription;

/**
 * @data on 2018/3/21 11:53
 * @describe:
 */

public abstract class BasePresenter<V extends View> implements Presenter {
    public DataManager manager;
    public CompositeSubscription mCompositeSubscription;
    private V mView;
    private Context mContext;
    private boolean isHaMiTao = true;


    public BasePresenter(Context mContext, View view) {
        this.mContext = mContext;
        attachView(view);
        onCreate();
    }

    public BasePresenter(Context mContext, View view, boolean isHaMiTao) {
        this.mContext = mContext;
        attachView(view);
        this.isHaMiTao = isHaMiTao;
        onCreate();
    }


    @Override
    public void onCreate() {
        manager = new DataManager(mContext, isHaMiTao);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mView = (V) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

}
