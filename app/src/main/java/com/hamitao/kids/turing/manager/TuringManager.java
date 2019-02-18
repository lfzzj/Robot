package com.hamitao.kids.turing.manager;

import android.content.Context;

import com.turing.authority.authentication.AuthenticationListener;
import com.turing.authority.authentication.SdkInitializer;

/**
 * @data on 2018/3/10 10:55
 * @describe: turing初始化
 */

public class TuringManager {

    /**
     * 初始化鉴权(此方法需在AndroidManifest.xml配置)
     *
     * @param context
     * @param authenticationListener
     */
    public static void init(Context context, AuthenticationListener authenticationListener) {
        SdkInitializer.init(context, authenticationListener);
    }

    /**
     * 初始化鉴权
     *
     * @param context
     * @param turingApiKey           图灵授权的apikey
     * @param turingSecret           图灵授权的secret
     * @param authenticationListener 鉴权回调
     */
    public static void init(Context context, String turingApiKey, String turingSecret,
                            AuthenticationListener authenticationListener) {
        SdkInitializer.init(context, turingApiKey, turingSecret, authenticationListener);
    }

    /**
     * @param context
     * @param turingApiKey
     * @param turingSecret
     * @param uniqueType             唯一的设备类型
     *                               SDKConstatnt.TYPE_IMEI
     *                               SDKConstatnt.TYPE_MAC
     *                               SDKConstatnt.TYPE_UUID
     *                               SDKConstatnt.TYPE_ANDROID_ID
     *                               SDKConstatnt.TYPE_SERIAL_NUMBER
     * @param authenticationListener
     */
    public static void init(Context context, String turingApiKey, String turingSecret,
                            String uniqueType, AuthenticationListener authenticationListener) {
        SdkInitializer.init(context, turingApiKey, turingSecret,  authenticationListener);
    }

    /**
     * @param turingApiKey
     * @param turingSecret
     * @param customId               设备的唯一id
     * @param context
     * @param authenticationListener
     */
    public static void init(String turingApiKey, String turingSecret, String customId, Context context,
                            AuthenticationListener authenticationListener) {
        SdkInitializer.init(turingApiKey, turingSecret, customId, context, authenticationListener);
    }
}
