package com.hamitao.kids.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;
import android.view.WindowManager;

import com.hamitao.kids.constant.Constants;

/**
 * @data on 2018/7/5 11:40
 * @describe: 亮度设置
 */

public class BrightUtil {
    private Activity activity;
    private int maxBright = 255;

    public BrightUtil(Activity activity) {
        this.activity = activity;
    }

    public int getMaxBright() {
        return maxBright;
    }

    // 获取当前系统亮度值
    public int getBrightness() {
        int brightValue = 0;
        ContentResolver contentResolver = activity.getContentResolver();
        try {
            brightValue = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brightValue;
    }

    /**
     * 增加屏幕亮度
     */
    public void addBright(){
        setBrightness(false,true);
    }

    /**
     * 减小屏幕亮度
     */
    public void reduceBright(){
        setBrightness(false,false);
    }

    public void setBrightness(boolean maximum, boolean isAdd) {
        if (maximum) {//是否设置成最值
            if (isAdd) {
                setScreenBrightness(maxBright);
            } else {
                setScreenBrightness(0);
            }
        } else {//不是设置成最值，则一次增大/减少10
            int curBrightness = getBrightness();//获取当前亮度
            if (isAdd) {
                curBrightness = curBrightness + 30;
                setScreenBrightness(curBrightness > maxBright ? maxBright : curBrightness);
            } else {//减小音量
                curBrightness = curBrightness - 30;
                setScreenBrightness(curBrightness < 0 ? 0 : curBrightness);
            }
        }
    }
    private boolean isMaximum = false;
    private boolean isAdd = false;

    public void setBrightInfo (String value){
        if (Constants.COMMAND_VALUE_PLUS.equals(value)) {
            isMaximum = false;
            isAdd = true;
        } else if (Constants.COMMAND_VALUE_REDUCE.equals(value)) {
            isMaximum = false;
            isAdd = false;
        } else if (Constants.COMMAND_VALUE_MAX.equals(value)) {
            isMaximum = true;
            isAdd = true;
        } else if (Constants.COMMAND_VALUE_MIN.equals(value)) {
            isMaximum = true;
            isAdd = false;
        }
        setBrightness(isMaximum,isAdd);
    }

    /**
     * 设置屏幕的亮度
     */
    public void setScreenBrightness(int process) {

        //设置当前窗口的亮度值.这种方法需要权限android.permission.WRITE_EXTERNAL_STORAGE
        WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
        float f = process / 255.0F;
        localLayoutParams.screenBrightness = f;
        activity.getWindow().setAttributes(localLayoutParams);
        //修改系统的亮度值,以至于退出应用程序亮度保持
        saveBrightness(activity.getContentResolver(), process);
    }

    public void saveBrightness(ContentResolver resolver, int brightness) {
        //设置为手动调节模式
        Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        //保存到系统中
        Uri uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
        Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
        resolver.notifyChange(uri, null);
    }
}
