package com.hamitao.kids.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.hamitao.framework.utils.GPSManager;
import com.hamitao.framework.utils.GsonUtil;
import com.hamitao.framework.utils.NumUtil;
import com.hamitao.framework.utils.TimeUtil;
import com.hamitao.framework.utils.ToastUtil;
import com.hamitao.framework.utils.WifiUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.utils.NetWorkUtil;
import com.hamitao.requestframe.entity.DeviceInfo;


/**
 * @data on 2018/3/21 14:15
 * @describe: 设备信息管理工具类
 */
@SuppressLint("WrongConstant")
public class DeviceManager {
    private Context mContext;


    public DeviceManager(Context context) {
        mContext = context;
    }

    /**
     * 获取设备信息
     */
    public DeviceInfo getDeviceInfo() {
//        return new DeviceInfo(getDeviceId(), getGPSGeom(), getTriggeredby(), getDeviceBattery());
        MyApp myApp = MyApp.getInstance();
        String devicecondition = "deviceState=true" +
                ";isPlaying=" + myApp.isPlayingState()
                + ";timingCloseTime=" + myApp.getTimingCloseTime()
                + ";isOpenEyeProtect=" + MyApp.getInstance().getSpManager().isOpenEyeProtect()
                + ";isPhoneManager=" + MyApp.getInstance().getSpManager().isPhoneManager();
        return new DeviceInfo(devicecondition, getDeviceSwitchState()
                , myApp.getGSMIntensity()
                , getDeviceBattery()
                , getDeviceId()
                , getWifiId()
                , MyApp.getInstance().getSpManager().isOpenEyeProtect()
                , MyApp.getInstance().getSpManager().isPhoneManager()
                , getGPSGeom()
                , myApp.isPlayingState()
                , myApp.getTimingCloseTime());
    }


    /**
     * 上报设备信息
     *
     * @return
     */
    public String reportDeviceInfo() {
        DeviceInfo deviceInfo = getDeviceInfo();
        return GsonUtil.GsonToString(deviceInfo);
    }

    /**
     * 获取当前WiFi名称
     *
     * @return
     */
    public String getWifiId() {
        String netName = mContext.getResources().getString(R.string.hint_no_net);
        int netWorkStatus = NetWorkUtil.getNetWorkStatus(mContext);
        switch (netWorkStatus) {
            case NetWorkUtil.NETWORK_CLASS_UNKNOWN:
                netName = mContext.getResources().getString(R.string.hint_no_net);
                break;
            case NetWorkUtil.NETWORK_WIFI://wifi
                netName = WifiUtil.getInstance().getConnectionInfo().getSSID();
                break;
            case NetWorkUtil.NETWORK_CLASS_2_G://2g
                netName = "2G";
                break;
            case NetWorkUtil.NETWORK_CLASS_3_G://3g
                netName = "3G";
                break;
            case NetWorkUtil.NETWORK_CLASS_4_G://4g
                netName = "4G";
                break;
        }
        return netName;
    }

    public boolean getDeviceSwitchState() {
        return true;
    }


    /**
     * 获取设备电量
     *
     * @return
     */
    public int getDeviceBattery() {
        Intent batteryInfoIntent = mContext.registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        return batteryInfoIntent.getIntExtra("level", 0);//电量（0-100）
    }

    /**
     * 获取设备id
     *
     * @return
     */
    public String getDeviceId() {
//        SPManager MyApp.getInstance().getSpManager() = new SPManager(mContext);
//        String deviceId = MyApp.getInstance().getSpManager().getDeviceId();
//        if (TextUtils.isEmpty(deviceId)) {
//            deviceId = getRandomDeviceId();
//            MyApp.getInstance().getSpManager().putDeviceId(deviceId);
//        }
        String deviceId = getMacAddress();
        return deviceId;
    }

    /**
     * 获取随机设备Id
     *
     * @return
     */
    public String getRandomDeviceId() {
        String timeFormat = TimeUtil.getDeviceIdFormat();
        int randdomNum = NumUtil.getForeRandomNum();
        return timeFormat + randdomNum;
    }

    /**
     * 获取mac地址-->作为deviceId
     *
     * @return
     */
    private String getMacAddress() {
        String wifimac = getLocalMacAddress();
        return wifimac.replace(":", "");
    }

    /**
     * 获取MAC地址
     *
     * @return
     */
    public String getLocalMacAddress() {
        String macAddress = WifiUtil.getInstance().getMacAddress();
        if (TextUtils.isEmpty(macAddress) || macAddress.contains("4045")) {
            ToastUtil.showToast(mContext, "Mac地址未写入");
        }
        return macAddress;
    }


    /**
     * GPS位置数据(longitude--> 经度,latitude-->纬度，altitude-->海拔)
     */
    public DeviceInfo.GeomBean getGPSGeom() {
        float altitude = 0;
        float longitude = GPSManager.getLatitude();
        float latitude = GPSManager.getLongitude();
        return new DeviceInfo.GeomBean(altitude, latitude, longitude);
    }

    /**
     * 触发上报因素
     */
    public String getTriggeredby() {
        return "autoreport";
    }

    /**
     * 获取手机IMEI号
     * <p>
     * 需要动态权限: android.permission.READ_PHONE_STATE
     */
    public String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(mContext.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }
}
