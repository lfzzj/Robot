package com.hamitao.framework.utils;

import java.util.List;

/**
 * @data on 2018/5/23 17:41
 * @describe:
 */

public class Util {
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

}
