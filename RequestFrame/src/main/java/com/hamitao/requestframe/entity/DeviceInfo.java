package com.hamitao.requestframe.entity;

/**
 * @data on 2018/3/21 10:15
 * @describe: 设备信息
 */

public class DeviceInfo {

    public DeviceInfo(String devicecondition,
            boolean deviceState, int GSMIntensity, int battery, String deviceid, String wifiid,
                      boolean isOpenEyeProtect, boolean isPhoneManager, GeomBean geom, boolean isPlaying,
                      int timingCloseTime ) {
        this.deviceState = deviceState;
        this.GSMIntensity = GSMIntensity;
        this.battery = battery;
        this.deviceid = deviceid;
        this.wifiid = wifiid;
        this.isOpenEyeProtect = isOpenEyeProtect;
        this.geom = geom;
        this.isPlaying = isPlaying;
        this.timingCloseTime = timingCloseTime;
        this.isPhoneManager = isPhoneManager;
        this.devicecondition = devicecondition;
    }

    public void setPhoneManager(boolean phoneManager) {
        isPhoneManager = phoneManager;
    }

    public boolean isPhoneManager() {

        return isPhoneManager;
    }

    public void setOpenEyeProtect(boolean openEyeProtect) {
        isOpenEyeProtect = openEyeProtect;
    }

    public boolean isOpenEyeProtect() {
        return isOpenEyeProtect;
    }

    /**
     * GSMIntensity : 67
     * alarmcondition : alarm condtion
     * battery : 68
     * devicecondition : device condition
     * deviceid : 11111111111fortest
     * direction : 66
     * geom : {"altitude":0,"latitude":23,"longitude":29}
     * gpslocatestatus : 69
     * gpssatellitetime : 1999-09-09 12:01:02
     * gpssatellitetimeUTC : 1999-09-09 12:01:02
     * gsmtime : 1999-09-09 12:01:01
     * gsmtimeUTC : 1999-09-09 12:01:02
     * gsmtowergeom : {"altitude":0,"latitude":29,"longitude":27}
     * gsmtowerid : dddd
     * hitwordbyscale : no use
     * horizonalaccuracy : 70
     * mileage : 72
     * possourcetype : GPS
     * precision : 65
     * satellitenum : 64
     * sensorcondition : sensor condition
     * seqid : nouse
     * speed : 63
     * statusword : sssss
     * terminalid :
     * triggeredby : autoreport
     * verticalaccuracy : 71
     * wifigeom : {"altitude":0,"latitude":25,"longitude":24}
     * wifiid : eeee
     * wifitime : 1999-09-09 12:01:02
     * wifitimeUTC : 1999-09-09 12:01:02
     */

    private boolean isPlaying;//播放状态

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public int getTimingCloseTime() {
        return timingCloseTime;
    }

    public void setTimingCloseTime(int timingCloseTime) {
        this.timingCloseTime = timingCloseTime;
    }

    private int timingCloseTime;//定时关闭

    private boolean isPhoneManager;
    private boolean isOpenEyeProtect;
    private boolean deviceState;//设备状态
    private int GSMIntensity;
    private String alarmcondition;
    private int battery;
    private String devicecondition;//设备运行状况(没有的参数都往里加)
    private String deviceid;
    private int direction;
    private GeomBean geom;
    private int gpslocatestatus;
    private String gpssatellitetime;
    private String gpssatellitetimeUTC;
    private String gsmtime;
    private String gsmtimeUTC;
    private GsmtowergeomBean gsmtowergeom;
    private String gsmtowerid;
    private String hitwordbyscale;
    private int horizonalaccuracy;
    private int mileage;
    private String possourcetype;
    private int precision;
    private int satellitenum;
    private String sensorcondition;
    private String seqid;
    private int speed;
    private String statusword;
    private String terminalid;
    private String triggeredby;
    private int verticalaccuracy;
    private WifigeomBean wifigeom;
    private String wifiid;
    private String wifitime;
    private String wifitimeUTC;

    public void setDeviceState(boolean deviceState) {
        this.deviceState = deviceState;
    }

    public boolean isDeviceState() {
        return deviceState;
    }

    public int getGSMIntensity() {
        return GSMIntensity;
    }

    public void setGSMIntensity(int GSMIntensity) {
        this.GSMIntensity = GSMIntensity;
    }

    public String getAlarmcondition() {
        return alarmcondition;
    }

    public void setAlarmcondition(String alarmcondition) {
        this.alarmcondition = alarmcondition;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public String getDevicecondition() {
        return devicecondition;
    }

    public void setDevicecondition(String devicecondition) {
        this.devicecondition = devicecondition;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public GeomBean getGeom() {
        return geom;
    }

    public void setGeom(GeomBean geom) {
        this.geom = geom;
    }

    public int getGpslocatestatus() {
        return gpslocatestatus;
    }

    public void setGpslocatestatus(int gpslocatestatus) {
        this.gpslocatestatus = gpslocatestatus;
    }

    public String getGpssatellitetime() {
        return gpssatellitetime;
    }

    public void setGpssatellitetime(String gpssatellitetime) {
        this.gpssatellitetime = gpssatellitetime;
    }

    public String getGpssatellitetimeUTC() {
        return gpssatellitetimeUTC;
    }

    public void setGpssatellitetimeUTC(String gpssatellitetimeUTC) {
        this.gpssatellitetimeUTC = gpssatellitetimeUTC;
    }

    public String getGsmtime() {
        return gsmtime;
    }

    public void setGsmtime(String gsmtime) {
        this.gsmtime = gsmtime;
    }

    public String getGsmtimeUTC() {
        return gsmtimeUTC;
    }

    public void setGsmtimeUTC(String gsmtimeUTC) {
        this.gsmtimeUTC = gsmtimeUTC;
    }

    public GsmtowergeomBean getGsmtowergeom() {
        return gsmtowergeom;
    }

    public void setGsmtowergeom(GsmtowergeomBean gsmtowergeom) {
        this.gsmtowergeom = gsmtowergeom;
    }

    public String getGsmtowerid() {
        return gsmtowerid;
    }

    public void setGsmtowerid(String gsmtowerid) {
        this.gsmtowerid = gsmtowerid;
    }

    public String getHitwordbyscale() {
        return hitwordbyscale;
    }

    public void setHitwordbyscale(String hitwordbyscale) {
        this.hitwordbyscale = hitwordbyscale;
    }

    public int getHorizonalaccuracy() {
        return horizonalaccuracy;
    }

    public void setHorizonalaccuracy(int horizonalaccuracy) {
        this.horizonalaccuracy = horizonalaccuracy;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getPossourcetype() {
        return possourcetype;
    }

    public void setPossourcetype(String possourcetype) {
        this.possourcetype = possourcetype;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getSatellitenum() {
        return satellitenum;
    }

    public void setSatellitenum(int satellitenum) {
        this.satellitenum = satellitenum;
    }

    public String getSensorcondition() {
        return sensorcondition;
    }

    public void setSensorcondition(String sensorcondition) {
        this.sensorcondition = sensorcondition;
    }

    public String getSeqid() {
        return seqid;
    }

    public void setSeqid(String seqid) {
        this.seqid = seqid;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getStatusword() {
        return statusword;
    }

    public void setStatusword(String statusword) {
        this.statusword = statusword;
    }

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getTriggeredby() {
        return triggeredby;
    }

    public void setTriggeredby(String triggeredby) {
        this.triggeredby = triggeredby;
    }

    public int getVerticalaccuracy() {
        return verticalaccuracy;
    }

    public void setVerticalaccuracy(int verticalaccuracy) {
        this.verticalaccuracy = verticalaccuracy;
    }

    public WifigeomBean getWifigeom() {
        return wifigeom;
    }

    public void setWifigeom(WifigeomBean wifigeom) {
        this.wifigeom = wifigeom;
    }

    public String getWifiid() {
        return wifiid;
    }

    public void setWifiid(String wifiid) {
        this.wifiid = wifiid;
    }

    public String getWifitime() {
        return wifitime;
    }

    public void setWifitime(String wifitime) {
        this.wifitime = wifitime;
    }

    public String getWifitimeUTC() {
        return wifitimeUTC;
    }

    public void setWifitimeUTC(String wifitimeUTC) {
        this.wifitimeUTC = wifitimeUTC;
    }

    public static class GeomBean {
        /**
         * altitude : 0
         * latitude : 23
         * longitude : 29
         */

        private float altitude;
        private double latitude;
        private double longitude;

        public GeomBean(float altitude, double latitude, double longitude) {
            this.altitude = altitude;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public float getAltitude() {
            return altitude;
        }

        public void setAltitude(float altitude) {
            this.altitude = altitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(float latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(float longitude) {
            this.longitude = longitude;
        }
    }

    public static class GsmtowergeomBean {
        /**
         * altitude : 0
         * latitude : 29
         * longitude : 27
         */

        private int altitude;
        private int latitude;
        private int longitude;

        public int getAltitude() {
            return altitude;
        }

        public void setAltitude(int altitude) {
            this.altitude = altitude;
        }

        public int getLatitude() {
            return latitude;
        }

        public void setLatitude(int latitude) {
            this.latitude = latitude;
        }

        public int getLongitude() {
            return longitude;
        }

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }
    }

    public static class WifigeomBean {
        /**
         * altitude : 0
         * latitude : 25
         * longitude : 24
         */

        private int altitude;
        private int latitude;
        private int longitude;

        public int getAltitude() {
            return altitude;
        }

        public void setAltitude(int altitude) {
            this.altitude = altitude;
        }

        public int getLatitude() {
            return latitude;
        }

        public void setLatitude(int latitude) {
            this.latitude = latitude;
        }

        public int getLongitude() {
            return longitude;
        }

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }
    }
}
