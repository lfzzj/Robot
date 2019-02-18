package com.hamitao.framework.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @data on 2018/7/5 14:32
 * @describe:
 */

public class BluetoothUtil {
    private BluetoothAdapter mBluetoothAdapter;
    private final static int SEARCH_CODE = 0x123;
    private Activity activity;

    public BluetoothUtil(Activity activity) {
        this.activity = activity;
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        init();
    }

    /**
     * 判断蓝牙是否开启
     */
    private void init() {
        // 判断手机是否支持蓝牙
        if (mBluetoothAdapter == null) {
            return;
        }
        // 判断是否打开蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            //弹出对话框提示用户是后打开
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(intent, SEARCH_CODE);
        } else {
            // 不做提示，强行打开
            mBluetoothAdapter.enable();
        }
    }

    /**
     * 获取本机蓝牙地址
     */
    public String getBluetoothAddress() {
        try {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            Field field = bluetoothAdapter.getClass().getDeclaredField("mService");
            // 参数值为true，禁用访问控制检查
            field.setAccessible(true);
            Object bluetoothManagerService = field.get(bluetoothAdapter);
            if (bluetoothManagerService == null) {
                return null;
            }
            Method method = bluetoothManagerService.getClass().getMethod("getAddress");
            Object address = method.invoke(bluetoothManagerService);
            if (address != null && address instanceof String) {
                return (String) address;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
