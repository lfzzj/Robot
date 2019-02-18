package com.hamitao.framework.factory;

import android.telephony.TelephonyManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Ming Xiao on 2017/12/13.
 */

public class TelephonyManagerInvoke {

    public static int getPhoneCountInvoke(){
        Class<?> aClass = TelephonyManager.class;
        int phoneCount = 0;
        try {
            Method method = aClass.getMethod("getPhoneCount");
            phoneCount = (int) method.invoke(aClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return phoneCount;
    }

    public static String getServiceNameInvoke(String defaultServiceName, int phoneId){
        Class<?> aClass = TelephonyManager.class;
        try {
            Method method = aClass.getMethod("getPhoneCount");
            return (String) method.invoke(aClass, defaultServiceName, phoneId);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
