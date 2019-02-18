package com.hamitao.kids.model;

/**
 * @data on 2018/6/27 13:38
 * @describe:
 */

public class WifiBean {
    private String wifiName;

    private int wifiLevel;

    private boolean isConn;

    private String capabilities;

    private String BSSID;

    public WifiBean(String wifiName, int wifiLevel, boolean isConn, String capabilities, String BSSID) {
        this.wifiName = wifiName;
        this.wifiLevel = wifiLevel;
        this.isConn = isConn;
        this.capabilities = capabilities;
        this.BSSID = BSSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public void setWifiLevel(int wifiLevel) {
        this.wifiLevel = wifiLevel;
    }

    public void setConn(boolean conn) {
        isConn = conn;
    }

    public String getWifiName() {

        return wifiName;
    }

    public int getWifiLevel() {
        return wifiLevel;
    }

    public boolean isConn() {
        return isConn;
    }
}
