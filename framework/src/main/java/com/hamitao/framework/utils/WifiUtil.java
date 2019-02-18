package com.hamitao.framework.utils;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;

import static android.content.Context.WIFI_SERVICE;

/**
 * @data on 2018/4/4 17:07
 * @describe:
 */

public class WifiUtil {
    public WifiManager mWifiManager;

    private WifiInfo mWifiInfo;

    private List<ScanResult> mWifiList;

    private List<WifiConfiguration> mWificonfiguration;


    private WifiUtil(){}

    private static WifiUtil Instance =  null;

    public static WifiUtil getInstance() {
        if(Instance == null){
            Instance = new WifiUtil();
        }
        return Instance;
    }

    public void init(Context context) {
        mWifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        mWifiInfo = mWifiManager.getConnectionInfo();
    }

    public int getWifiLevel() {
        mWifiInfo = mWifiManager.getConnectionInfo();
        int level = mWifiInfo.getRssi();
        if (level <= 0 && level >= -50) {
            return 1;
        } else if (level <= -50 && level >= -70) {
            return 2;
        } else if (level < -70 && level >= -80) {
            return 3;
        } else {
            return 4;
        }
    }

    public WifiInfo getConnectionInfo() {
        return mWifiManager.getConnectionInfo();
    }

    public String getCurConnWifiName() {
        return StringUtil.getWifiName(mWifiManager.getConnectionInfo().getSSID());
    }

    public void setWifiEnable(boolean state) {
        mWifiManager.setWifiEnabled(state);
    }

    public boolean isWifiOpen() {
        int state = checkState();
        if (state == WifiManager.WIFI_STATE_ENABLED) {
            return true;
        }
        return false;
    }

    public int checkState() {
        return mWifiManager.getWifiState();
    }


    public void startScan() {
        mWifiManager.startScan();
        mWifiList = mWifiManager.getScanResults();
        mWificonfiguration = mWifiManager.getConfiguredNetworks();
    }

    public List<ScanResult> getmWifiList() {
        startScan();
        return mWifiList;
    }

    public String getMacAddress() {
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
    }


    public boolean addNetWork(WifiConfiguration wifiConfiguration) {
        int wcgID = mWifiManager.addNetwork(wifiConfiguration);
        Log.e("wcgID", wcgID + "true");
        mWifiManager.enableNetwork(wcgID, true);
        mWifiManager.saveConfiguration();
        mWifiManager.reconnect();
        return true;

    }

    public WifiConfiguration createWifiInfo(String SSID, String Password, int Type) {
        WifiConfiguration configuration = new WifiConfiguration();
        configuration.allowedAuthAlgorithms.clear();
        configuration.allowedGroupCiphers.clear();
        configuration.allowedKeyManagement.clear();
        configuration.allowedPairwiseCiphers.clear();
        configuration.allowedProtocols.clear();
        configuration.SSID = "\"" + SSID + "\"";

        WifiConfiguration tempConfig = this.isExsits(SSID);
        if (tempConfig != null) {
            mWifiManager.removeNetwork(tempConfig.networkId);
        }

        switch (Type) {
            case 1:
                configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
//                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
//                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
//                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
//                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
//                configuration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
//                configuration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
//                configuration.status = WifiConfiguration.Status.ENABLED;
                break;
            case 2:
                configuration.hiddenSSID = false;
                configuration.wepKeys[0] = "\"" + Password + "\"";
                configuration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
                configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);

                break;
            case 3:
                configuration.preSharedKey = "\"" + Password + "\"";
                configuration.hiddenSSID = false;
                // configuration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
                configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                configuration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                configuration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                configuration.status = WifiConfiguration.Status.ENABLED;
                break;
        }
        return configuration;
    }

    private WifiConfiguration isExsits(String SSID) {
        List<WifiConfiguration> existingConfigs = mWifiManager.getConfiguredNetworks();
        if (existingConfigs != null && existingConfigs.size() > 0) {
            for (WifiConfiguration existingConfig : existingConfigs) {
                if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                    return existingConfig;
                }

            }
        }
        return null;
    }
}