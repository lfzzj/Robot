package com.hamitao.framework.factory;

import java.lang.reflect.Method;

public class OperatorUtils {
    private static final String PROPERTY_NAME = "ro.operator";

    private static final String NAME_CMCC = "cmcc";
    private static final String NAME_CUCC = "cucc";
    private static final String NAME_CTCC = "ctcc";

    private static final String PROPERTY_VALUE = getProperty(PROPERTY_NAME, "unknown");

    /** China Mobile Communications Corporation */
    public static final boolean IS_CMCC = NAME_CMCC.equals(PROPERTY_VALUE);

    /** China Unicom **/
    public static final boolean IS_CUCC = NAME_CUCC.equals(PROPERTY_VALUE);

    /** China Telecom **/
    public static final boolean IS_CTCC = NAME_CTCC.equals(PROPERTY_VALUE);



    // should add key as the follow

    /** Whether the device is support vt **/
    public static boolean ENABLE_VT = IS_CMCC ? true : false;

    /** Whether should insert duration time into database **/
    public static boolean ENABLR_CALLLOG_DURATION = IS_CMCC ? false : true;

    /** Whether go to call log after disconnection **/
    /** Default value as false **/
    public static boolean GOTO_CALLLOG_AFTER_DISCONECTION = false/*IS_CMCC ? false : true*/;


    /* Ming Xiao 2017/12/13   */
    public static String getProperty(String key, String defaultValue) {
        String value = "";
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            value = (String)(get.invoke(c, key, defaultValue));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return value;
        }
    }
}
