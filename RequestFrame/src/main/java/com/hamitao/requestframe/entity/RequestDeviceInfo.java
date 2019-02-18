package com.hamitao.requestframe.entity;

/**
 * @data on 2018/3/21 11:35
 * @describe:
 */

public class RequestDeviceInfo {

    public RequestDeviceInfo(String dagcluster, String deviceid, String terminaltype, String vendor) {
        this.dagcluster = dagcluster;
        this.deviceid = deviceid;
        this.terminaltype = terminaltype;
        this.vendor = vendor;
    }

    /**
     * dagcluster : hamitao_kidsrobot
     * deviceid : 111111111
     * terminaltype : kidsrobot
     */


    private String dagcluster;
    private String deviceid;
    private String terminaltype;
    private String vendor;//厂家

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDagcluster() {
        return dagcluster;
    }

    public void setDagcluster(String dagcluster) {
        this.dagcluster = dagcluster;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getTerminaltype() {
        return terminaltype;
    }

    public void setTerminaltype(String terminaltype) {
        this.terminaltype = terminaltype;
    }
}
