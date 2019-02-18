package com.hamitao.kids.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.WindowManager;

import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.callback.OnAlarmCallBack;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.turing.model.AlarmBean;
import com.hamitao.kids.utils.AlarmManagerUtil;

/**
 * @data on 2018/4/16 18:51
 * @describe: 闹钟
 */

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        long intervalMillis = intent.getLongExtra(Constants.INTERVALMILLIS, 0);
        Logger.d(TAG, "闹钟响了需要循环的时间为：" + intervalMillis);
        String msg = intent.getStringExtra(Constants.ALARM_MSG);
        int idx = intent.getIntExtra(Constants.ALARM_ID, 0);
        String id = intent.getStringExtra(Constants.ALARM_SERVICE_ID);
        int soundOrVibrator = intent.getIntExtra(Constants.SOUNDORVIBRATOR, 0);
        String time = intent.getStringExtra(Constants.ALARM_TIME);
        if (intervalMillis != 0) {
            //设置闹钟循环
            AlarmManagerUtil.setAlarmTime(context, System.currentTimeMillis() + intervalMillis,
                    intent);
        } else {
            if (onAlarmCallBack != null) {
                onAlarmCallBack.onUpdataAlarm(msg, id);
            }
        }
        //展现闹钟
        if (onAlarmCallBack != null) {
            onAlarmCallBack.onRing(msg);
        }
    }

    private OnAlarmCallBack onAlarmCallBack;

    public void setOnAlarmListener(OnAlarmCallBack callBack) {
        this.onAlarmCallBack = callBack;
    }

}
