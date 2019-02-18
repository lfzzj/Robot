package com.hamitao.kids.utils;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.os.PowerManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.hamitao.framework.utils.DensityUtil;

import java.util.List;

public class ScreenUtil {

    //亮屏
    public static void screenOn(Activity activity) {
        // turn on screen
        PowerManager mPowerManager = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        mWakeLock.acquire();
        mWakeLock.release();
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        return screenWidth;
    }

    /**
     * 获取屏幕宽高
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenHeight = windowManager.getDefaultDisplay().getHeight();
        return screenHeight;
    }

    /**
     * 获取屏幕的宽高比
     *
     * @param context
     * @return
     */
    public static float getScreenWhScale(Context context) {
        return (float) getScreenWidth(context) / (float) getScreenHeight(context);
    }

    //minWidth 最小起的尺寸
    public static Camera.Size getPreviewSize(List<Camera.Size> list, int minWidth, float scale) {

        for (int i=0;i<list.size();i++) {
            Camera.Size size = list.get(i);
            if ((size.width > minWidth) && equalRate(size,scale)) {
                return size;
            }
        }
        //有可能出现找不到合适的分辨率
        return null;
    }

    public static boolean equalRate(Camera.Size s, float rate) {
        float r = (float) (s.width) / (float) (s.height);
        if (Math.abs(r - rate) <= 0.2) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取宽高中较短的那一个
     * @param context
     * @return
     */
    public static int getScreenValue(Context context) {
        int width = getScreenWidth(context);
        int height = getScreenHeight(context);

        if (width > height) {
            return height;
        } else {
            return width;
        }
    }


    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public static Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) w / h;
        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        // Try to find an size match aspect ratio and size
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        // Cannot find the one match the aspect ratio, ignore the requirement
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }


}
