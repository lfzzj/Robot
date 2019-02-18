package com.hamitao.kids.camera;

import android.hardware.Camera;

/**
 * @data on 2018/5/3 9:21
 * @describe: 相机工具类
 */

public class CameraUtil {
    /**
     * 获取camera
     *
     * @return
     */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

}
