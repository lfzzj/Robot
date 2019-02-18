package com.hamitao.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * TIME 2017/9/8 16:46
 * DESCRIBE：
 */

public class SPUtil {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    /**
     * SPUtils构造函数
     */
    public SPUtil(Context context, String spName) {
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.apply();
    }

    /**
     * SP中写入String类型value
     */
    public void putString(String key, String value) {
        editor.putString(key, value).apply();
    }

    /**
     * SP中读取String
     *  @return 存在返回对应值，不存在返回默认值
     */
    public String getString(String key) {
        return getString(key, null);
    }


    /**
     * SP中读取String
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    /**
     * SP中写入int类型value
     */
    public void putInt(String key, int value) {
        editor.putInt(key, value).apply();
    }

    /**
     * SP中读取int
     */
    public int getInt(String key) {
        return getInt(key, -1);
    }

    /**
     * SP中读取int
     *
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }


    /**
     * SP中写入boolean类型value
     */
    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }


    /**
     * SP中读取boolean
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * SP中读取boolean
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

}
