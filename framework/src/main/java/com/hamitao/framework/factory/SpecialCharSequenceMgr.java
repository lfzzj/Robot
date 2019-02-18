/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hamitao.framework.factory;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.widget.EditText;

import com.hamitao.framework.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Helper class to listen for some magic character sequences
 * that are handled specially by the dialer.
 * <p>
 * Note the Phone app also handles these sequences too (in a couple of
 * relatively obscure places in the UI), so there's a separate version of
 * this class under apps/Phone.
 * <p>
 * TODO: there's lots of duplicated code between this class and the
 * corresponding class under apps/Phone.  Let's figure out a way to
 * unify these two classes (in the framework? in a common shared library?)
 */
public class SpecialCharSequenceMgr {
    private static final String TAG = "SpecialCharSequenceMgr";

    private static final String TAG_SELECT_ACCT_FRAGMENT = "tag_select_acct_fragment";

    private static final String SECRET_CODE_ACTION = "android.provider.Telephony.SECRET_CODE";
    private static final String MMI_IMEI_DISPLAY = "*#06#";
    private static final String MMI_VERSION_DISPLAY = "*#1030#";   //FOR HAIER
    private static final String MMI_REGULATORY_INFO_DISPLAY = "*#07#";

    //外部查版本指令：*#*#8111#*#*，显示：  OL01E-SF02-0101-CT-V0101
    private static final String QUERY_VERSION_DISPLAY = "*#*#8111#*#*";
    private static final String VERSION_KEY = "ro.product.ctver";
    private static final String VERSION_DEFAULT = "OL01E-SF02-0101-CT-V0101";
    //gpsTest app
    private static final String GPS_TEST_PACKAGE_NAME = "com.nick.ad.lbs"; //com.chartcross.gpstestplus
    //launch gpsTest app
    private static final String LAUNCH_GPS_TEST_APP = "*#*#6633#*#*";



    /**
     * This class is never instantiated.
     */
    private SpecialCharSequenceMgr() {
    }

    public static boolean handleChars(Context context, String input) {
        //get rid of the separators so that the string gets parsed correctly
        String dialString = PhoneNumberUtils.stripSeparators(input);

//        if (handleDeviceIdDisplay(context, dialString)//*#06#  直接在Context中弹出对话框,显示IMEI信息
//                || handleRegulatoryInfoDisplay(context, dialString)//*#07# 直接通过隐式intent启动相关应用
//                || handleSoftwareVersionCode(context, dialString)
//                || handlePinEntry(context, dialString)
//                || handleSecretCode(context, dialString)) {//*#*#<code>#*#*
//            return true;
//        }

        if (handleDeviceIdDisplay(context, dialString) || handleSecretCode(context, dialString)) {
            return true;
        }

        return false;
    }


    // TODO: Use TelephonyCapabilities.getDeviceIdLabel() to get the device id label instead of a
    // hard-coded string.
   public static boolean handleDeviceIdDisplay(Context context, String input) {
        TelephonyManager telephonyManager =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if (Build.VERSION.SDK_INT >= 23) {
            if (telephonyManager != null && input.equals(MMI_IMEI_DISPLAY)) {
                int labelResId = (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) ?
                        R.string.imei : R.string.meid;

                List<String> deviceIds = new ArrayList<String>();

                /* Ming Xiao 2017/12/13 replace */
                /* SPRD: add for IMEI plugIn @{ */
//                int displayIMEICount = 2;//DialerPluginsHelper.getInstance(context).displayIMEICount();
//                /* @} */
//                for (int slot = 0; slot < displayIMEICount; slot++) {
//                    if (slot == 0)
//                        deviceIds.add("IMEI1:" + telephonyManager.getDeviceId(slot));
//                    else
//                        deviceIds.add("IMEI2:" + telephonyManager.getDeviceId(slot));
//                }

                int displayIMEICount = 1;//single card
                /* @} */
                for (int slot = 0; slot < displayIMEICount; slot++) {
                    if (slot == 0)
                        deviceIds.add("IMEI:" + telephonyManager.getDeviceId(slot));
                }
                /* @} */

                new AlertDialog.Builder(context)
                        .setTitle(labelResId)
                        .setItems(deviceIds.toArray(new String[deviceIds.size()]), null)
                        .setPositiveButton(android.R.string.ok, null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        } else {
            if (telephonyManager != null && input.equals(MMI_IMEI_DISPLAY)) {
                int phoneType = telephonyManager.getPhoneType();
                if (phoneType == TelephonyManager.PHONE_TYPE_GSM) {
                    showIMEIPanel(context, telephonyManager);
                    return true;
                } else if (phoneType == TelephonyManager.PHONE_TYPE_CDMA) {
                    showMEIDPanel(context, telephonyManager);
                    return true;
                }
            }
        }


        return false;
    }


    /**
     * Handles secret codes to launch arbitrary activities in the form of *#*#<code>#*#*.
     * If a secret code is encountered an Intent is started with the android_secret_code://<code>
     * URI.
     *
     * @param context the context to use
     * @param input   the text to check for a secret code in
     * @return true if a secret code was encountered
     */
    static boolean handleSecretCode(Context context, String input) {
        //
        if(input.equals(QUERY_VERSION_DISPLAY)){
            showVersion(context);
            return true;
        }
        //启动gpsTest app
        if(input.equals(LAUNCH_GPS_TEST_APP)){
            launchApp(context,GPS_TEST_PACKAGE_NAME);
            return true;
        }
        //

        // Secret codes are in the form *#*#<code>#*#*
        int len = input.length();
        if (len > 8 && input.startsWith("*#*#") && input.endsWith("#*#*")) {
            final Intent intent = new Intent(SECRET_CODE_ACTION,
                    Uri.parse("android_secret_code://" + input.substring(4, len - 4)));
            intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);//SPCSS00192131
            context.sendBroadcast(intent);
            return true;
        }

        return false;
    }

    /**
     * SPRD: Modify this method for display IMEI for multi-sim mode. @{
     */
    // TODO: Combine showIMEIPanel() and showMEIDPanel() into a single
    // generic "showDeviceIdPanel()" method, like in the apps/Phone
    // version of SpecialCharSequenceMgr.java.  (This will require moving
    // the phone app's TelephonyCapabilities.getDeviceIdLabel() method
    // into the telephony framework, though.)
    private static void showIMEIPanel(Context context,
                                      TelephonyManager telephonyManager) {
        StringBuffer imeiBuffer = new StringBuffer();
        if (OperatorUtils.IS_CMCC) {
            imeiBuffer.append(telephonyManager.getDeviceId());
        } else {
            int phoneCnt = TelephonyManagerInvoke.getPhoneCountInvoke();
            if (phoneCnt == 1) {
                imeiBuffer.append(telephonyManager.getDeviceId());
            } else {
                for (int i = 0; i < phoneCnt; i++) {
                    if (i != 0) {
                        imeiBuffer.append("\n");
                    }
                    imeiBuffer.append("IMEI");
                    imeiBuffer.append((i + 1));
                    imeiBuffer.append("\n");
                    imeiBuffer.append(((TelephonyManager) context.getSystemService(TelephonyManagerInvoke
                            .getServiceNameInvoke(Context.TELEPHONY_SERVICE, i))).getDeviceId());
                }
            }
        }
        String imeiStr = imeiBuffer.toString();
        /* SPRD: modify title for cmcc @{
         *      AlertDialog alert = new AlertDialog.Builder(context)
                .setTitle(R.string.imei)
                .setMessage(imeiStr)
                .setPositiveButton(android.R.string.ok, null)
                .setCancelable(false)
                .show();
         * */
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setMessage(imeiStr)
                .setPositiveButton(android.R.string.ok, null)
                .setCancelable(false);
        if (OperatorUtils.IS_CMCC) {
            alert.setTitle("IMEI")
                    .show();
        } else {
            alert.setTitle(R.string.imei)
                    .show();
        }
        /** @} */

    }

    /**
     * @}
     */


    private static void showMEIDPanel(Context context,
                                      TelephonyManager telephonyManager) {
        String meidStr = telephonyManager.getDeviceId();

        new AlertDialog.Builder(context)
                .setTitle(R.string.meid)
                .setMessage(meidStr)
                .setPositiveButton(android.R.string.ok, null)
                .setCancelable(false)
                .show();
    }

    private static void showVersion(Context context){
        new AlertDialog.Builder(context)
                .setTitle(R.string.firmware_version)
                .setMessage(OperatorUtils.getProperty(VERSION_KEY,VERSION_DEFAULT))
                .setPositiveButton(android.R.string.ok, null)
                .setCancelable(false)
                .show();
    }


    /**
     * 检测某个应用是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 启动App
     * @param context
     */
    public static void launchApp(Context context, String packageName) {
        if (isAppInstalled(context, packageName)) {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
        }
    }

}
