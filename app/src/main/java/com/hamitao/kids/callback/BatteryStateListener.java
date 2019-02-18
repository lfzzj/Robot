package com.hamitao.kids.callback;

public interface BatteryStateListener {
    void onStateChanged(float power, boolean isCharge);

    void onStateLow();

    void onStateOkay();

    void onStatePowerConnected();

    void onStatePowerDisconnected();
}
