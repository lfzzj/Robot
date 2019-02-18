package com.hamitao.kids.model;

/**
 * @data on 2018/4/13 15:10
 * @describe:
 */

public class ReportDeviceInfo {
    private boolean deviceState;//设备
    private int GSMIntensity;
    private int battery;
    private String deviceid;
    private String wifiId;
    private boolean isEyeProtect ;//护眼模式是否开启

    public ReportDeviceInfo(boolean deviceState, int GSMIntensity, int battery, String deviceid, String wifiId,boolean isEyeProtect) {
        this.deviceState = deviceState;
        this.GSMIntensity = GSMIntensity;
        this.battery = battery;
        this.deviceid = deviceid;
        this.wifiId = wifiId;
        this.isEyeProtect = isEyeProtect;
    }

    public void setDeviceState(boolean deviceState) {
        this.deviceState = deviceState;
    }

    public void setGSMIntensity(int GSMIntensity) {
        this.GSMIntensity = GSMIntensity;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public void setWifiId(String wifiId) {
        this.wifiId = wifiId;
    }

    public boolean isDeviceState() {

        return deviceState;
    }

    public int getGSMIntensity() {
        return GSMIntensity;
    }

    public int getBattery() {
        return battery;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public String getWifiId() {
        return wifiId;
    }

    public void setEyeProtect(boolean eyeProtect) {
        isEyeProtect = eyeProtect;
    }

    public boolean isEyeProtect() {

        return isEyeProtect;
    }
}
