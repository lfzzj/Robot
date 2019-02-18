package com.hamitao.kids.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hamitao.framework.network.NetStatusWatch;
import com.hamitao.framework.network.NetworkStatus;

/**
 * @data on 2018/5/29 14:46
 * @describe: 网络
 */

public abstract class BaseNetActivity extends BaseActivity  implements NetStatusWatch.OnNetStatusChangedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNetReceiver();
    }

    private void setNetReceiver() {
        NetStatusWatch.getInstance().regisiterListener(this);
//        Log.d(TAG,"Net  Start : "+NetStatusWatch.getInstance().getCurrNetStatus().name());
    }

    @Override
    public void onNetStatusChanged(NetworkStatus currNetStatus) {
        Log.d(TAG, "currNetStatus : " + currNetStatus.name());
    }


    //检查当前网络是否可用
    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetStatusWatch.getInstance().unRegisiterListener(this);
    }
}
