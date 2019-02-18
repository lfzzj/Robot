package com.hamitao.kids.receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.hamitao.kids.callback.SIMStateListener;

public class SimStateReceiver extends BroadcastReceiver {
    public final static String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";
    private final static int SIM_VALID = 0;
    private final static int SIM_INVALID = 1;
    private int simState = SIM_INVALID;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_SIM_STATE_CHANGED)) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            int state = tm.getSimState();
            switch (state) {
                case TelephonyManager.SIM_STATE_READY:
                    simState = SIM_VALID;
                    if (mSIMStateListener!=null){
                        mSIMStateListener.onState(simState);
                    }
                    break;
                case TelephonyManager.SIM_STATE_UNKNOWN:
                case TelephonyManager.SIM_STATE_ABSENT:
                case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                default:
                    simState = SIM_INVALID;
                    if (mSIMStateListener!=null){
                        mSIMStateListener.onState(simState);
                    }
                    break;
            }
        }
    }

    private SIMStateListener mSIMStateListener;

    public void setSIMStateListener(SIMStateListener SIMStateListener) {
        this.mSIMStateListener = SIMStateListener;
    }
}
