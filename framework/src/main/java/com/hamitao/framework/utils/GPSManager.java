package com.hamitao.framework.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * @data on 2018/3/19 10:20
 * @describe:
 */

public class GPSManager {
    private static String TAG = "GPSManager";

    private static String lngAndLat = "";//当前经纬度

    private static AMapLocationClient mLocationClient;


    public static void openAMap(Context context) {
        //声明AMapLocationClient类对象
        mLocationClient = new AMapLocationClient(context);
        //参数设置
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setInterval(1000 * 60 * 3);
        mLocationClient.setLocationOption(option);
        //开始定位
        mLocationClient.startLocation();
        Logger.d(TAG, "设置定位回调监听--->");
        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        double latitude = aMapLocation.getLatitude();
                        double longitude = aMapLocation.getLongitude();
                        lngAndLat = longitude + "," + latitude;
                        Logger.d(TAG, "当前位置--->" + lngAndLat);
                    }
                }
            }
        });
    }


    public static boolean isStartLocation() {
        return mLocationClient.isStarted();
    }

    public static void setLocationState(boolean isEnable) {

        if (mLocationClient == null) {
            return;
        }

        if (isEnable) {
            if (!mLocationClient.isStarted()) return;
            mLocationClient.startLocation();
        } else {
            if (mLocationClient.isStarted()) return;
            mLocationClient.stopLocation();
        }
    }

    /**
     * 截取经度
     */
    public static float getLatitude() {
        String LngAndLat = lngAndLat;
        if (!TextUtils.isEmpty(LngAndLat)) {
            String longitude = LngAndLat.substring(0, LngAndLat.indexOf(","));
            return Float.parseFloat(longitude);
        } else {
            return 0;
        }
    }

    public static float getLongitude() {
        String LngAndLat = lngAndLat;
        if (!TextUtils.isEmpty(LngAndLat)) {
            String latitude = LngAndLat.substring(LngAndLat.indexOf(",") + 1);
            return Float.parseFloat(latitude);
        } else {
            return 0;
        }
    }

    //从网络获取经纬度
    public static String getLngAndLatWithNetwork(Context context) {
        double latitude = 0.0;
        double longitude = 0.0;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        return longitude + "," + latitude;
    }


    private static LocationListener locationListener = new LocationListener() {

        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {

        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {

        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
        }
    };

    /**
     * 获取电池电量
     *
     * @return
     */
    public static int getBatteryLevel(Context context) {
        Intent batteryInfoIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        return batteryInfoIntent.getIntExtra("level", 0);//电量（0-100）
    }
}
