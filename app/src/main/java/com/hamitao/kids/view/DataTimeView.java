package com.hamitao.kids.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.factory.SpecialCharSequenceMgr;

import com.hamitao.framework.utils.TimeUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;

/**
 * @data on 2018/7/10 9:55
 * @describe: 日期/时间/星期
 */

public class DataTimeView extends RelativeLayout {

    private TextView stateTime;
    private TextView stateData;
    private TextView stateWeek;
    private ImageView sysVoice;
    private RelativeLayout rlVoice;

    final static int COUNTS = 5;//点击次数
    final static long DURATION = 3 * 1000;//规定有效时间
    long[] mHits = new long[COUNTS];

    private Context mContext;

    public DataTimeView(Context context) {
        super(context);
    }

    public DataTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(context);
    }

    public DataTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_status_data_time, this);
        stateTime = view.findViewById(R.id.tv_state_time);
        stateData = view.findViewById(R.id.tv_state_data);
        stateWeek = view.findViewById(R.id.tv_state_week);
        sysVoice = view.findViewById(R.id.iv_voice);
        rlVoice = view.findViewById(R.id.rl_time);

        rlVoice.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
                    checkEnterFactoryMode("*#*#83789#*#*");
                }
            }
        });
        initData();
        sysVoice.setOnClickListener(mOnClickListener);
        setInfo(context);
    }

    /**
     * 检测进入工厂模式
     *
     * @param phoneNum
     */
    private void checkEnterFactoryMode(String phoneNum) {
        SpecialCharSequenceMgr.handleChars(mContext, phoneNum);
    }


    private void initData() {
        sysVoice.setBackgroundResource(R.drawable.icon_voice_home_page);
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (onVoiceClickListener != null) {
                onVoiceClickListener.onClick();
            }
        }
    };


    public void setOnVoiceClickListener(OnVoiceClickListener onVoiceClickListener) {
        this.onVoiceClickListener = onVoiceClickListener;
    }

    private OnVoiceClickListener onVoiceClickListener;

    public interface OnVoiceClickListener {
        void onClick();
    }

    public void receiverTime(Context context) {
        /*日期时间*/
        IntentFilter timeFilter = new IntentFilter();
        timeFilter.addAction(Intent.ACTION_TIME_TICK);
        timeFilter.addAction(Intent.ACTION_TIME_CHANGED);
        timeFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        context.registerReceiver(timeReceiver, timeFilter);
    }

    public void unReceiverTime(Context context) {
        context.unregisterReceiver(timeReceiver);
    }

    private BroadcastReceiver timeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setInfo(context);
        }
    };

    private void setInfo(Context context) {
        handleTimeInfo(context);
        handleTimeFormat(context);
        handleDataInfo();
        handleWeekInfo();
    }

    //当前星期
    private void handleWeekInfo() {
        stateWeek.setText(TimeUtil.getWeek());
    }

    //当前日期
    private void handleDataInfo() {
        stateData.setText(TimeUtil.getCurDataInfo());
    }

    //当前时间
    private void handleTimeInfo(Context context) {
        stateTime.setText(TimeUtil.getCurTimeInfo(context));
    }

    //时间格式
    private void handleTimeFormat(Context context) {
        if (TimeUtil.is24HourFormat(context)) {

        } else {
            if (TimeUtil.isAM()) {

            } else {

            }
        }
    }


}
