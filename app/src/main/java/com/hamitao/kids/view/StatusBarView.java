package com.hamitao.kids.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.framework.utils.DeviceUtil;
import com.hamitao.framework.utils.WifiUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.utils.NetWorkUtil;
import com.hamitao.kids.utils.ResUtil;
import com.hamitao.kids.widgets.BatteryViewSelfMain;

/**
 * @data on 2018/7/6 11:45
 * @describe: 自定义状态栏 (信号/WiFi信号/电量)
 */

public class StatusBarView extends RelativeLayout {

    private ImageView stateSignal;
    private TextView providersName;//运营商
    private BatteryViewSelfMain batteryView;
    private ImageView ivStateWifi;
    private TextView tvStateWifi;
    private TextView stateBattery;
    private ImageView ivBatteryLightning;

    private Context context;


    public StatusBarView(Context context) {
        super(context, null);
    }

    public StatusBarView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.context = context;
        initView();
    }

    public StatusBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_status_bar, this);
        //信号
        stateSignal = view.findViewById(R.id.iv_state_signal);
        providersName = view.findViewById(R.id.tv_providers_name);
        ivStateWifi = view.findViewById(R.id.iv_state_wifi);
        tvStateWifi = view.findViewById(R.id.tv_state_wifi);
        batteryView = view.findViewById(R.id.battery_view_main);
        stateBattery = view.findViewById(R.id.tv_state_battery);
        ivBatteryLightning = view.findViewById(R.id.iv_battery_lightning);
        initData();
        setSIMProvider();


    }

    /**
     * 设置sim卡运营商
     */
    private void setSIMProvider() {
        String providers = DeviceUtil.getProvidersName(context);
        if (!TextUtils.isEmpty(providers)) {
            providersName.setText(providers);
            setGsmStateSignal(MyApp.getInstance().getGSMIntensity());
        } else {
            providersName.setText(context.getResources().getString(R.string.no_sim_card));
        }
    }


    /**
     * 设置蜂窝信号值
     */
    public void setGsmStateSignal(int gsmIntensity) {
        ResUtil.setGsmIntensity(gsmIntensity, stateSignal);
    }

    private void initData() {
        batteryView.setBatteryBackgroudRes(R.drawable.bg_battery_power_main);
    }

    public void setBatteryStatus(float power, boolean isCharge) {
        batteryView.setCurPower(power);
        stateBattery.setText((int) (power * 100) + "%");
        ivBatteryLightning.setVisibility(isCharge ? View.VISIBLE : View.GONE);
    }

    //设置网络状态
    public void setNetStatus() {
        tvStateWifi.setVisibility(View.GONE);
        ivStateWifi.setVisibility(View.GONE);
        int netWorkStatus = NetWorkUtil.getNetWorkStatus(context);
        switch (netWorkStatus) {
            case NetWorkUtil.NETWORK_CLASS_UNKNOWN:
                tvStateWifi.setVisibility(View.VISIBLE);
                tvStateWifi.setText(context.getResources().getString(R.string.hint_no_net));
                break;
            case NetWorkUtil.NETWORK_WIFI://wifi
                ivStateWifi.setVisibility(View.VISIBLE);
                setWifiState();
                break;
            case NetWorkUtil.NETWORK_CLASS_2_G://2g
                tvStateWifi.setVisibility(View.VISIBLE);
                tvStateWifi.setText("2G");
                break;
            case NetWorkUtil.NETWORK_CLASS_3_G://3g
                tvStateWifi.setVisibility(View.VISIBLE);
                tvStateWifi.setText("3G");
                break;
            case NetWorkUtil.NETWORK_CLASS_4_G://4g
                tvStateWifi.setVisibility(View.VISIBLE);
                tvStateWifi.setText("4G");
                break;
        }
    }

    private void setWifiState() {
        int wifiLevel = WifiUtil.getInstance().getWifiLevel();//WiFi信号
        ResUtil.setWifiLevel(wifiLevel, ivStateWifi);
    }

    public void changeSimState() {
        setSIMProvider();
    }

    public void changeNetState() {
        setWifiState();
    }


}