package com.hamitao.framework.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;

/**
 * @data on 2018/6/26 20:30
 * @describe:
 */

public class NetChangeBroadCastReciver extends BroadcastReceiver {

    private OnNetChangedListener listener;

    public NetChangeBroadCastReciver() {
        super();
    }

    private Handler handler;

    public interface OnNetChangedListener {
        void onNetChanged(NetworkStatus currNetStatus);
    }

    public void setListener(OnNetChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            //wifi由开而关，先不通知，5000ms后检测到依然无网络，此时通知。原因是:移动网络打开时，BroadCastReciver会接到2次消息，第一次检测网络是不通的，其实只是在切换中，第二次检测网络为移动网络了。
            if (intent.getIntExtra(ConnectivityManager.EXTRA_NETWORK_TYPE, -1) == ConnectivityManager.TYPE_WIFI && intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)) {
                if (handler == null)
                    handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        NetworkStatus networkStatus = NetUtil.getNetWorkState(context);
                        if (networkStatus == NetworkStatus.NETWORK_NONE)
                            if (listener != null) {
                                listener.onNetChanged(networkStatus);
                            }
                    }
                }, 5000);
            } else {
                if (listener != null) {
                    listener.onNetChanged(NetUtil.getNetWorkState(context));
                }
            }
        }
    }
}
