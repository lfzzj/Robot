package com.hamitao.kids.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * @data on 2018/4/16 17:21
 * @describe: 获取Manifest数据
 */

public class ManifestUtil {
    /**
     * 设置极光appkey
     * @param context
     */
    public static void setManifestJPushData(Context context) {
        String mJPushAppKey = "";
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            if (appInfo != null) {
                mJPushAppKey = appInfo.metaData.getString("JPUSH_APPKEY");
                jPushAppKey = mJPushAppKey;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String jPushAppKey;

    /**
     * 获取极光appkey
     * @return
     */
    public static String getJPushAppKey() {
        return jPushAppKey;
    }
}
