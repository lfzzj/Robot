package com.hamitao.framework.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @data on 2018/4/8 10:45
 * @describe: 包管理
 */

public class PackageUtil {
    public static String TAG = "PackageUtil";

    // 应用版本
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取操作系统的版本号
     * @return
     */
    public static String getOsVersionName() {
        return Build.VERSION.SDK_INT + "";
    }


    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getLocalVersion(Context context) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 检测应用是否需要更新
     *
     * @param context
     * @param versionCode 从网络中获取的版本号
     * @return
     */
    public static boolean isUpdata(Context context, int versionCode) {
        int localVersion = getLocalVersion(context);
        if (localVersion >= versionCode) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 获取应用的名称
     *
     * @param context
     * @return
     */
    public static String loadAppLabel(Context context) {
        return loadAppLabel(context, context.getPackageName());
    }

    /**
     * 通过包名获取应用的名称(label)
     */
    public static String loadAppLabel(Context context, String pkgname) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(pkgname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }

        try {
            String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);

            return applicationName;

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public static String getAppPackageName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = activityManager.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        return componentInfo.getPackageName();
    }

    public static String getRunningActivityName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        String contextActivity = runningActivity.substring(runningActivity.lastIndexOf(".") + 1);
        return contextActivity;
    }

    public static boolean isTopActivity(Activity activity) {
        String packageName = "xxxxx";
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            System.out.println("---------------包名-----------" + tasksInfo.get(0).topActivity.getPackageName());
            //应用程序位于堆栈的顶层
            if (packageName.equals(tasksInfo.get(0).topActivity.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static String getTopActivityClassName(Context context) {
        // 获取activity任务栈
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);

        // 类名 .ui.mobile.activity.WebsiteLoginActivity
        String shortClassName = info.topActivity.getShortClassName();

        // 完整类名 com.haofang.testapp.ui.mobile.activity.WebsiteLoginActivity
        String className = info.topActivity.getClassName();

        // 包名  com.haofang.testapp
//        String packageName = info.topActivity.getPackageName();

        return className;
    }

    public static String getTopActivityName(String shortClassName) {
        int i = shortClassName.lastIndexOf(".");
        return shortClassName.substring(i + 1);
    }

    public static Activity getActivity() {
        Class activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }


}
