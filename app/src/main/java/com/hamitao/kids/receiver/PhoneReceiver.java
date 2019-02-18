package com.hamitao.kids.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.SystemClock;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;

import com.android.internal.telephony.ITelephony;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.utils.TimerControlView;

import java.lang.reflect.Method;

/**
 * @data on 2018/6/6 15:56
 * @describe:
 */

public class PhoneReceiver extends BroadcastReceiver {
    private String TAG = "PhoneReceiver";
    private TimerControlView timerControlView;

    private ITelephony mTelephonyService;
    private Context context;

    public static final String PHONE_ACTION = "com.phone.action";

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        if (MyApp.getInstance().getSpManager().isPhoneManager()) {
            timerControlView = new TimerControlView(Constants.twoSecondTime);
            timerControlView.setOnTimerContorlCallBack(mOnListener);
        }
        try {
            TelephonyManager telephony = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            telephony.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
            Class<?> c = Class.forName(telephony.getClass().getName());

            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);

            mTelephonyService = (ITelephony) m.invoke(telephony);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private TimerControlView.OnTimerContorlCallBack mOnListener = new TimerControlView.OnTimerContorlCallBack() {
        @Override
        public void onStart() {
            Logger.d(TAG, "开始计时了 ");

        }

        @Override
        public void onEnd() {
            Logger.d(TAG, "时间到了");
            if (mTelephonyService != null) {
                try {
                    Logger.d(TAG, "接听电话");
//                    mTelephonyService.answerRingingCall();

                    /* 模拟耳机插入动作,用于接听电话 */
                    AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                    long eventTime = SystemClock.uptimeMillis() - 1;
                    KeyEvent eventDown = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_HEADSETHOOK);
                    KeyEvent eventUp = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK);
                    audioManager.dispatchMediaKeyEvent(eventDown);
                    audioManager.dispatchMediaKeyEvent(eventUp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    };

    class MyPhoneStateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    Logger.d(TAG, "CALL_STATE_RINGING");
                    if (MyApp.getInstance().getSpManager().isPhoneManager() && timerControlView != null) {//开启了电话管理
                        timerControlView.startControlTimer();
                    }
                    Intent intent = new Intent(PHONE_ACTION);
                    context.sendBroadcast(intent);

                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    Logger.d(TAG, "CALL_STATE_IDLE");
                    if (MyApp.getInstance().getSpManager().isPhoneManager() && timerControlView != null) {//开启了电话管理
                        timerControlView.endControlTimer();
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Logger.d(TAG, "电话状态  拨打");
                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }

    public interface OnPhoneListener {
        void onRinging();//来电了
    }

    private OnPhoneListener onPhoneListener;

    public void setPhoneListener(OnPhoneListener onPhoneListener) {
        this.onPhoneListener = onPhoneListener;
    }
}