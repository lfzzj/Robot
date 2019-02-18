package com.hamitao.kids.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.R;
import com.hamitao.kids.widgets.BatteryViewSelf;

/**
 * 充电提醒
 */
public class PowerConnectedDialog extends Dialog {
    private Context context;

    private BatteryViewSelf batteryViewSelf;
    private TextView tvBatteryValue;
    private RelativeLayout rlBatteryBg;


    public PowerConnectedDialog(@NonNull Context context) {
        super(context, R.style.remind_dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.dialog_power_connect_view);
        batteryViewSelf = findViewById(R.id.battery_value);
        batteryViewSelf.setBatteryBackgroudRes(R.drawable.bg_battery_power);

        tvBatteryValue = findViewById(R.id.tv_battery_value);
        rlBatteryBg = findViewById(R.id.rl_battery_bg);
        rlBatteryBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick();
                }
            }
        });
//        setBattery();
    }

    /**
     * 设置初始值
     *
     * @param power
     */
    public void setBatteryValue(float power) {
        tvBatteryValue.setText((int) (power * 100) + "%");

        batteryViewSelf.setCurPower(power);
        batteryViewSelf.setInitialPower();
        batteryViewSelf.startTimer();
    }

    /**
     * 刷新电量
     */
    public void refreshPower(float power) {
        tvBatteryValue.setText((int) (power * 100) + "%");
        batteryViewSelf.setCurPower(power);

    }

    private OnBatteryViewListener mOnClickListener;

    public interface OnBatteryViewListener {
        void onClick();
    }

    public void setBatteryViewClickListener(OnBatteryViewListener onBatteryViewListener) {
        this.mOnClickListener = onBatteryViewListener;
    }

    @Override
    public void onBackPressed() {
        if (mOnClickListener != null) {
            mOnClickListener.onClick();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        batteryViewSelf.stopTimer();
//        unRegisterPower();
    }
}
