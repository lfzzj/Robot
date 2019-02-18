package com.hamitao.kids.camera.util;


import android.content.Context;
import android.util.DisplayMetrics;

public final class DeviceUtil {

    private static DisplayMetrics displayMetrics = null;

    public static int getScreenHeight(Context context) {
        if (displayMetrics == null) {
            setDisplayMetrics(context.getResources().getDisplayMetrics());
        }
        return displayMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        if (displayMetrics == null) {
            setDisplayMetrics(context.getResources().getDisplayMetrics());
        }
        return displayMetrics.widthPixels;
    }

    public static void setDisplayMetrics(DisplayMetrics DisplayMetrics) {
        displayMetrics = DisplayMetrics;
    }

}
