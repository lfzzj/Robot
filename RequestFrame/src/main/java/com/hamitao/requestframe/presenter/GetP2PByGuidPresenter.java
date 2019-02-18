package com.hamitao.requestframe.presenter;

import android.content.Context;

import com.hamitao.requestframe.entity.GetP2PByGuidInfo;
import com.hamitao.requestframe.view.GetP2PByGuidView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @data on 2018/7/11 16:10
 * @describe: 获取p2p的guid
 */

public class GetP2PByGuidPresenter extends BasePresenter<GetP2PByGuidView> {
    private GetP2PByGuidView mView;
    private GetP2PByGuidInfo mGetP2PByGuidInfo;
    private Context mContext;

    public GetP2PByGuidPresenter(Context mContext, GetP2PByGuidView view) {
        super(mContext, view, false);
        this.mView = view;
        this.mContext = mContext;
    }

    public void requestData(String guid, String imei, String wifimac, String groupid) {
        mCompositeSubscription.add(manager.getP2PByGuid(guid, imei, wifimac, groupid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetP2PByGuidInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mGetP2PByGuidInfo != null) {
                            mView.onSuccess(mGetP2PByGuidInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(GetP2PByGuidInfo info) {
                        mGetP2PByGuidInfo = info;
                    }
                })
        );
    }
}
