package com.hamitao.kids.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

import com.hamitao.framework.utils.DeviceUtil;
import com.hamitao.kids.callback.TelephonyCallBack;

public class NetWorkUtil {
    /**
     * Unknown network class
     */
    public static final int NETWORK_CLASS_UNKNOWN = 0;

    /**
     * wifi net work
     */
    public static final int NETWORK_WIFI = 1;

    /**
     * "2G" networks
     */
    public static final int NETWORK_CLASS_2_G = 2;

    /**
     * "3G" networks
     */
    public static final int NETWORK_CLASS_3_G = 3;

    /**
     * "4G" networks
     */
    public static final int NETWORK_CLASS_4_G = 4;

    /**
     * 获取手机网络类型（2G/3G/4G）
     * 4G为LTE，联通的3G为UMTS或HSDPA，电信的3G为EVDO，移动和联通的2G为GPRS或EGDE，电信的2G为CDMA。
     *
     * @param context
     * @return
     */
    public static int getNetWorkClass(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_CLASS_2_G;

            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NETWORK_CLASS_3_G;

            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_CLASS_4_G;

            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }

    /**
     * 获取手机连接的网络类型（是WIFI还是手机网络[2G/3G/4G]）
     *
     * @param context
     * @return //
     */
    public static int getNetWorkStatus(Context context) {
        int netWorkType = NETWORK_CLASS_UNKNOWN;
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();

            if (type == ConnectivityManager.TYPE_WIFI) {
                netWorkType = NETWORK_WIFI;
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                netWorkType = getNetWorkClass(context);
            }
        }
        return netWorkType;
    }

    /**
     * 得到当前的手机网络类型
     *
     * @param context
     * @return
     */
//    public static int getNetWorkStatus(Context context) {
//        int netWorkType = NETWORK_CLASS_UNKNOWN;
//        ConnectivityManager cm = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo info = cm.getActiveNetworkInfo();
//        if (info == null) {
//            netWorkType = NETWORK_CLASS_UNKNOWN;
//        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
//            netWorkType = NETWORK_WIFI;
//        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
//            int subType = info.getSubtype();
//            if (subType == TelephonyManager.NETWORK_TYPE_CDMA
//                    || subType == TelephonyManager.NETWORK_TYPE_GPRS
//                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
//                netWorkType = NETWORK_CLASS_2_G;
//            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
//                    || subType == TelephonyManager.NETWORK_TYPE_HSDPA
//                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
//                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
//                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
//                netWorkType = NETWORK_CLASS_3_G;
//            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
//                netWorkType = NETWORK_CLASS_4_G;
//            }
//        }
//        return netWorkType;
//    }


    /**
     * 得到当前的手机蜂窝网络信号强度
     * 获取LTE网络和3G/2G网络的信号强度的方式有一点不同，
     * LTE网络强度是通过解析字符串获取的，
     * 3G/2G网络信号强度是通过API接口函数完成的。
     * asu 与 dbm 之间的换算关系是 dbm=-113 + 2*asu
     */
    public static void getCurrentNetDBM(Context context, TelephonyCallBack callBack) {
        final TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        //手机状态监听器
        PhoneStateListener mylistener = new PhoneStateListener() {
            @Override
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);
                String signalInfo = signalStrength.toString();
                String[] params = signalInfo.split(" ");

                if (tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_LTE) {
                    //4G网络 最佳范围   >-90dBm 越大越好
                    int Itedbm = Integer.parseInt(params[9]);
                    callBack.onDBMCallBack(Itedbm);

                } else if (
                        tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSDPA ||
                                tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSPA ||
                                tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSUPA ||
                                tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS) {
                    //3G网络最佳范围  >-90dBm  越大越好  ps:中国移动3G获取不到  返回的无效dbm值是正数（85dbm）
                    //在这个范围的已经确定是3G，但不同运营商的3G有不同的获取方法，故在此需做判断 判断运营商与网络类型的工具类在最下方
                    String yys = DeviceUtil.getProvidersName(context);//获取当前运营商
                    if (yys == "中国移动") {
                        //中国移动3G不可获取，故在此返回0
                        callBack.onDBMCallBack(0);
                    } else if (yys == "中国联通") {
                        int cdmaDbm = signalStrength.getCdmaDbm();
                        callBack.onDBMCallBack(cdmaDbm);
                    } else if (yys == "中国电信") {
                        int evdoDbm = signalStrength.getEvdoDbm();
                        callBack.onDBMCallBack(evdoDbm);
                    }
                } else {
                    //2G网络最佳范围>-90dBm 越大越好
                    int asu = signalStrength.getGsmSignalStrength();
                    int dbm = -113 + 2 * asu;
                    callBack.onDBMCallBack(dbm);
                }


            }
        };
        //开始监听
        tm.listen(mylistener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }

    /**
     * 得到当前的手机蜂窝网络信号强度
     * 获取LTE网络和3G/2G网络的信号强度的方式有一点不同，
     * LTE网络强度是通过解析字符串获取的，
     * 3G/2G网络信号强度是通过API接口函数完成的。
     * asu 与 dbm 之间的换算关系是 dbm=-113 + 2*asu
     */
    public static void getCurrentNetAsu(Context context, TelephonyCallBack callBack) {
        final TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        PhoneStateListener mylistener = new PhoneStateListener() {
            @Override
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);
                String signalInfo = signalStrength.toString();
                String[] params = signalInfo.split(" ");
                if (tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_LTE) {
                    //4G网络 最佳范围   >-90dBm 越大越好
                    int Itedbm = Integer.parseInt(params[9]);
                    callBack.onDBMCallBack(Itedbm);
                } else if (tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSDPA ||
                        tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSPA ||
                        tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSUPA ||
                        tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS) {
                    //3G网络最佳范围  >-90dBm  越大越好  ps:中国移动3G获取不到  返回的无效dbm值是正数（85dbm）
                    //在这个范围的已经确定是3G，但不同运营商的3G有不同的获取方法，故在此需做判断 判断运营商与网络类型的工具类在最下方
                    String yys = DeviceUtil.getProvidersName(context);//获取当前运营商
                    if (yys == "中国移动") {
                        //中国移动3G不可获取，故在此返回0
                        callBack.onDBMCallBack(0);
                    } else if (yys == "中国联通") {
                        int cdmaDbm = signalStrength.getCdmaDbm();
                        callBack.onDBMCallBack(cdmaDbm);
                    } else if (yys == "中国电信") {
                        int evdoDbm = signalStrength.getEvdoDbm();
                        callBack.onDBMCallBack(evdoDbm);
                    }
                } else {
                    //2G网络最佳范围>-90dBm 越大越好
                    int asu = signalStrength.getGsmSignalStrength();
                    int dbm = -113 + 2 * asu;
                    callBack.onDBMCallBack(dbm);
                }

            }
        };
        //开始监听
        tm.listen(mylistener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }
}
