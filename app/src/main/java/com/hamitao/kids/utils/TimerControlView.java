package com.hamitao.kids.utils;

import android.os.Handler;
import android.os.Message;

/**
 * 计时器
 */
public class TimerControlView {
    public final static int MSG_TIMER = 0x01;
    private ControlHandler mControlHandler;
    private long time = 0;//相隔多长时间执行操作

    public TimerControlView(long time) {
        mControlHandler = new ControlHandler();
        this.time = time;
    }

    public class ControlHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_TIMER://到点了执行操作
                    if (callback != null) {
                        callback.onEnd();
                    }
                    break;
            }
        }
    }

    private Runnable controlRunable = new Runnable() {
        @Override
        public void run() {
            mControlHandler.obtainMessage(MSG_TIMER).sendToTarget();
        }
    };

    /**
     * 开启计时器
     */
    public void startControlTimer() {
        mControlHandler.removeCallbacks(controlRunable);
        if (callback != null) {
            callback.onStart();
        }
        mControlHandler.postDelayed(controlRunable, time);
    }

    /**
     * 关闭计时器（不再计时）
     */
    public void endControlTimer() {
        mControlHandler.removeCallbacks(controlRunable);
    }


    /**
     * 重置计时器
     */
    public void resetControlTimer() {//重置计时
        mControlHandler.removeCallbacks(controlRunable);
        mControlHandler.postDelayed(controlRunable, time);
    }

    private OnTimerContorlCallBack callback;

    public void setOnTimerContorlCallBack(OnTimerContorlCallBack onHideHandlerNoticeUi) {
        this.callback = onHideHandlerNoticeUi;
    }

    public interface OnTimerContorlCallBack {
        void onStart();//开始

        void onEnd();//满足条件了 结束
    }
}
