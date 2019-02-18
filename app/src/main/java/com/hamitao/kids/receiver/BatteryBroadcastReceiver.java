package com.hamitao.kids.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.ToastUtil;
import com.hamitao.kids.callback.BatteryStateListener;

public class BatteryBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            switch (action) {
                case Intent.ACTION_BATTERY_CHANGED://电量发生改变
                    int status = intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
                    boolean isCharge;
                    if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                        isCharge = true;
                    } else {
                        isCharge = false;
                    }
                    //获取当前电量
                    int level = intent.getIntExtra("level", 0);
                    //电量的总刻度
                    int scale = intent.getIntExtra("scale", 100);

                    float power = (float) level / scale;
                    if (mBatteryStateListener != null) {
                        mBatteryStateListener.onStateChanged(power, isCharge);
                    }
                    break;
                case Intent.ACTION_BATTERY_LOW://电量低
                    if (mBatteryStateListener != null) {
                        mBatteryStateListener.onStateLow();
                    }
                    break;
                case Intent.ACTION_BATTERY_OKAY://电量充满
                    if (mBatteryStateListener != null) {
                        mBatteryStateListener.onStateOkay();
                    }
                    break;
                case Intent.ACTION_POWER_CONNECTED://接通电源
                    if (mBatteryStateListener != null) {
                        mBatteryStateListener.onStatePowerConnected();
                    }
                    break;
                case Intent.ACTION_POWER_DISCONNECTED://拔出电源
                    if (mBatteryStateListener != null) {
                        mBatteryStateListener.onStatePowerDisconnected();
                    }
                    break;
            }
        }
    }

    private BatteryStateListener mBatteryStateListener;

    public void setBatteryStateListener(BatteryStateListener batteryStateListener) {
        this.mBatteryStateListener = batteryStateListener;
    }

}
