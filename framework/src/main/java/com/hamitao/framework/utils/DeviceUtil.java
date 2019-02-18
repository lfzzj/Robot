package com.hamitao.framework.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

/**
 * @data on 2018/5/23 17:41
 * @describe:
 */

public class DeviceUtil {
    /**
     * 定义信号强度
     *
     * @param level
     * @return
     */
    public static int getSignalIntensity(int level) {
        if (level >= -98) {
            return 1;
        } else if (level >= -106) {
            return 2;
        } else if (level >= -112) {
            return 3;
        } else if (level >= -117) {
            return 4;
        } else {
            return 5;
        }
    }

    /**
     * 定义wifi信号强度
     *
     * @param level
     * @return
     */
    public static int getWifiIntensity(int level) {
        if (level <= 0 && level >= -60) {
            return 1;
        } else if (level < -60 && level >= -70) {
            return 2;
        } else if (level < -70 && level >= -90) {
            return 3;
        } else {
            return 4;
        }
    }

    public static String getProvidersName(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String IMSI = telephonyManager.getSubscriberId();
        if (IMSI == null) {
//            return "unknow";
            return "";
        }
        String ProvidersName = "";
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007") || IMSI.startsWith("46020")) {
            ProvidersName = "中国移动";
        } else if (IMSI.startsWith("46001") || IMSI.startsWith("46006") || IMSI.startsWith("46009")) {
            ProvidersName = "中国联通";
        } else if (IMSI.startsWith("46003") || IMSI.startsWith("46005") || IMSI.startsWith("46011")) {
            ProvidersName = "中国电信";
        }
        return ProvidersName;
    }


//    /**
//     * 获取运营商
//     */
//    public static String getProvidersName(Context context) {
//        String provider = "";
//        try {
//            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            String IMSI = telephonyManager.getSubscriberId();
//            //Log.v("tag", "getProvider.IMSI:" + IMSI);
//            if (IMSI == null) {
//                if (TelephonyManager.SIM_STATE_READY == telephonyManager.getSimState()) {
//                    String operator = telephonyManager.getSimOperator();
//                    Log.v("tag", "getProvider.operator:" + operator);
//                    if (operator != null) {
//                        if (operator.equals("46000")
//                                || operator.equals("46002")
//                                || operator.equals("46007")
//                                || operator.equals("46020")) {
//                            provider = "中国移动";
//                        } else if (operator.equals("46001") || operator.equals("46006") || operator.equals("46009")) {
//                            provider = "中国联通";
//                        } else if (operator.equals("46003") || operator.equals("46005") || operator.equals("46011")) {
//                            provider = "中国电信";
//                        }
//                    }
//                }
//            } else {
//                if (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007") || IMSI.startsWith("46020")) {
//                    provider = "中国移动";
//                } else if (IMSI.startsWith("46001") || IMSI.startsWith("46006") || IMSI.startsWith("46009")) {
//                    provider = "中国联通";
//                } else if (IMSI.startsWith("46003") || IMSI.startsWith("46005") || IMSI.startsWith("46011")) {
//                    provider = "中国电信";
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return provider;
//    }


    // 取得版本号
    public static String GetVersion(Context context) {
        try {
            PackageInfo manager = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return manager.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown";
        }
    }

    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

}
