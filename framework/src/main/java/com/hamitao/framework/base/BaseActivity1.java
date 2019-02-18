package com.hamitao.framework.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import butterknife.ButterKnife;

/**
 * @data on 2018/5/29 14:38
 * @describe: 底层Activity
 */

public abstract class BaseActivity1 extends AppCompatActivity {
    public String TAG = this.getClass().getSimpleName();
    public Context mContext;
    public Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉状态栏

        mContext = getApplicationContext();


        mActivity = this;
        setActivityView();

        ButterKnife.bind(this);
    }


    // 绑定布局
    public abstract void setActivityView();


    //自定义绑定控件
    public <T extends View> T $(int viewId) {
        return (T) findViewById(viewId);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    //Activity跳转
    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    //Activity跳转
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        openActivity(pClass, pBundle, null);
    }

    //Activity跳转
    protected void openActivity(Class<?> pClass, Bundle pBundle, Uri uri) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        if (uri != null) {
            intent.setData(uri);
        }
        startActivity(intent);
    }

}
