package com.hamitao.kids.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.ui.activity.SessionListActivity;

/**
 * 弹窗辅助类
 *
 * @ClassName WindowUtils
 */
public class WindowUtils {
    private static final String LOG_TAG = "WindowUtils";
    private static View mView = null;
    private static WindowManager mWindowManager = null;
    private static Context mContext = null;
    public static Boolean isShown = false;
    public static WindowManager.LayoutParams params;

    public static int msgCount = 0;
    private static TextView tvNum;  //消息数


    /**
     * 初始化IM消息弹窗
     */
    public static void initMsgWindowDialog(Context context) {
        mContext = context.getApplicationContext();
        // 获取WindowManager
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
        mView = setUpView(mContext);
        // 类型
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        // WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        // 设置flag
//        int flags = WindowManager.LayoutParams.FLAG_SPLIT_TOUCH;
        int flags = LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        params.flags = flags;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题
        params.width = LayoutParams.WRAP_CONTENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
    }

    /**
     * 显示弹出框
     */
    public static void showPopupWindow() {
        if (isShown) {
            return;
        }
        isShown = true;
        mWindowManager.addView(mView, params);
    }


    /**
     * 显示消息数
     *
     */
    public static void showPopWindow(int count) {
        String numString = count + "";
        if (count > 99) {
            numString = 99 + "+";
        }
        if (count < 0) return;
        tvNum.setText(numString);
        showPopupWindow();
    }


    /**
     * 隐藏弹出框
     */
    public static void hidePopupWindow() {
        Logger.i(LOG_TAG, "hide " + isShown + ", " + mView);
        if (isShown && null != mView) {
            Logger.i(LOG_TAG, "hidePopupWindow");
            mWindowManager.removeView(mView);
            isShown = false;
        }
    }


    /**
     * 转载视图
     *
     * @param context
     * @return
     */
    private static View setUpView(Context context) {
        Logger.i(LOG_TAG, "setUp view");
        View view = LayoutInflater.from(context).inflate(R.layout.view_unread_msg, null);
        ImageView iv = view.findViewById(com.hamitao.framework.R.id.iv_msg_num_total);
        tvNum = view.findViewById(com.hamitao.framework.R.id.tv_msg_num_total);
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i(LOG_TAG, "ok on click");
//                WindowUtils.hidePopupWindow();
                Intent it = new Intent(context, SessionListActivity.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(it);
            }
        });

        // 点击窗口外部区域可消除
        // 这点的实现主要将悬浮窗设置为全屏大小，外层有个透明背景，中间一部分视为内容区域
        // 所以点击内容区域外部视为点击悬浮窗外部
        final View popupWindowView = view.findViewById(R.id.outer);// 非透明的内容区域
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Logger.i(LOG_TAG, "onTouch");
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();
                popupWindowView.getGlobalVisibleRect(rect);
                if (!rect.contains(x, y)) {
//                    WindowUtils.hidePopupWindow();
                    return true;
                }
                Logger.i(LOG_TAG, "onTouch : " + x + ", " + y + ", rect: " + rect);
                return false;
            }
        });


        // 点击back键可消除
//        view.setOnKeyListener(new OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                switch (keyCode) {
//                    case KeyEvent.KEYCODE_BACK:
//                        WindowUtils.hidePopupWindow();
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//        });
        return view;
    }
}