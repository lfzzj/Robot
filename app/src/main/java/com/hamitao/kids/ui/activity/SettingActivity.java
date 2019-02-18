package com.hamitao.kids.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.factory.SpecialCharSequenceMgr;
import com.hamitao.framework.network.NetworkStatus;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.MobileUtil;
import com.hamitao.framework.utils.PackageUtil;
import com.hamitao.framework.utils.WifiUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.view.CommonFuncLayout;
import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.listener.OnViewClickListener;

import butterknife.BindView;

import static com.hamitao.framework.network.NetworkStatus.NETWORK_MOBILE;

/**
 * @data on 2018/6/5 16:20
 * @describe: 设置
 */

public class SettingActivity extends BaseMsgActivity {
    @BindView(R.id.rl_app_download)
    CommonFuncLayout rlAppDownload;
    @BindView(R.id.rl_operating_video)
    CommonFuncLayout rlOperatingVideo;
    @BindView(R.id.rl_wifi)
    CommonFuncLayout rlWifi;
    @BindView(R.id.rl_setting_mobile)
    CommonFuncLayout rlMobile;
    @BindView(R.id.rl_volume_setting)
    CommonFuncLayout rlVolumeSetting;
    @BindView(R.id.rl_bright_setting)
    CommonFuncLayout rlBrightSetting;
    @BindView(R.id.rl_local_info)
    CommonFuncLayout rlLocalInfo;
    @BindView(R.id.rl_setting_turn_off)
    CommonFuncLayout rlSettingTurnOff;//关机
    @BindView(R.id.rl_setting_time)
    CommonFuncLayout rlSettingTime;//时间
    @BindView(R.id.rl_setting_bellset)
    CommonFuncLayout rlSettingBellset;//本地铃声
    @BindView(R.id.rl_wireless_upgrade)
    CommonFuncLayout rlWirelessUpgrade;//无线升级
    @BindView(R.id.rl_switch_mobile)
    RelativeLayout rlSwitchMobile;//移动网络

    @BindView(R.id.btn_switch_wifi)
    ImageView btnSwitch;
    @BindView(R.id.iv_switch_mobile)
    ImageView btnSwitchMobile;

    @BindView(R.id.tv_conn_wifi_name)
    TextView tvConnWifiName;


    //    private boolean enableWifiState = false;//WiFi连接状态
    private boolean enableMobiletate = false;//移动网络连接状态

    private boolean isEnable = false;

    private boolean isOpenWifi;

    @Override
    protected void onResume() {
        super.onResume();
        checkUnReadCount();
    }

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_setting);
        if (MyApp.getInstance().getSpManager().isFristBoot()) {
            MyApp.addActivity(this);
        }
    }

    @Override
    public void initData() {
        rlWifi.setCommonInfo(R.drawable.icon_setting_wifi, getStrByRes(R.string.func_wifi_conn));
        rlMobile.setCommonInfo(R.drawable.icon_setting_mobile_conn, getStrByRes(R.string.mobile_conn));
        rlBrightSetting.setCommonInfo(R.drawable.icon_setting_brightset, getStrByRes(R.string.func_bright_setting));
        rlVolumeSetting.setCommonInfo(R.drawable.icon_setting_volumeset, getStrByRes(R.string.func_volume_setting));
        rlAppDownload.setCommonInfo(R.drawable.icon_setting_download, getStrByRes(R.string.func_binding_wizard));
        rlLocalInfo.setCommonInfo(R.drawable.icon_setting_machineinfor, getStrByRes(R.string.func_local_info));
        rlOperatingVideo.setCommonInfo(R.drawable.icon_setting_using_wizard, getStrByRes(R.string.func_operating_video));
        rlSettingTime.setCommonInfo(R.drawable.icon_setting_time, getStrByRes(R.string.func_setting_time));
        rlSettingBellset.setCommonInfo(R.drawable.icon_setting_bellset, getStrByRes(R.string.func_setting_bellset));
        rlSettingTurnOff.setCommonInfo(R.drawable.icon_setting_turn_off, getStrByRes(R.string.func_setting_turn_off));
        rlWirelessUpgrade.setCommonInfo(R.drawable.icon_setting_wireless_upgrade, getStrByRes(R.string.func_setting_Wireless_Upgrade));
        if (!getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)) {
            if (MyApp.getInstance().getSpManager().isFristBoot()) {
                rlWifi.setTextNotClickColor();
                rlBrightSetting.setTextNotClickColor();
                rlVolumeSetting.setTextNotClickColor();
                rlAppDownload.setTextNotClickColor();
                rlLocalInfo.setTextNotClickColor();
                rlOperatingVideo.setTextNotClickColor();
                rlSettingTurnOff.setTextNotClickColor();
                rlWirelessUpgrade.setTextNotClickColor();
            }
            rlSettingTime.setVisibility(View.GONE);
            rlSettingBellset.setVisibility(View.GONE);
        }
        tvConnWifiName.setTextColor(getResources().getColor(R.color.common_conn_wifi_color));

        isOpenWifi = WifiUtil.getInstance().isWifiOpen();
        isEnable = isOpenWifi;
        checkNetState(isOpenWifi);
        checkMobileState();
        initClick();

    }


    @Override
    public void onNetStatusChanged(NetworkStatus currNetStatus) {
        Logger.d(TAG, "当前网络类型=" + currNetStatus);
        if (currNetStatus.equals(NETWORK_MOBILE)) {
            speak(FuncTitle.CONTENT_VOICE_HINT_NET_CONNTCT.toString());
        }
        checkNetState(WifiUtil.getInstance().isWifiOpen());
    }


    private void initClick() {
        btnSwitchMobile.setOnClickListener(mOnClickListener);
        rlSwitchMobile.setOnClickListener(mOnClickListener);
        if (!MyApp.getInstance().getSpManager().isFristBoot()) {
            rlAppDownload.setOnClickListener(mOnClickListener);
            rlOperatingVideo.setOnClickListener(mOnClickListener);
            rlWifi.setOnClickListener(mOnClickListener);
            rlVolumeSetting.setOnClickListener(mOnClickListener);
            rlBrightSetting.setOnClickListener(mOnClickListener);
            rlLocalInfo.setOnClickListener(mOnClickListener);
            rlSettingTurnOff.setOnClickListener(mOnClickListener);
            rlSettingTime.setOnClickListener(mOnClickListener);
            rlSettingBellset.setOnClickListener(mOnClickListener);
            btnSwitch.setOnClickListener(mOnClickListener);
            rlWirelessUpgrade.setOnClickListener(mOnClickListener);

        }
    }


    /**
     * 检查网络状态
     *
     * @param isOpen 是否打开wifi
     */
    private void checkNetState(boolean isOpen) {
        if (isOpen) {//wifi已连接
            btnSwitch.setBackgroundResource(R.drawable.icon_turn_on);
            String wifiName = WifiUtil.getInstance().getCurConnWifiName();
            tvConnWifiName.setText("0x".equals(wifiName) ? getStrByRes(R.string.hint_no_net) : wifiName);
        } else {
            btnSwitch.setBackgroundResource(R.drawable.icon_turn_off);
            tvConnWifiName.setText(getResources().getString(R.string.close));
        }
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            switch (v.getId()) {
                case R.id.rl_app_download:
                    Logger.d(TAG, "绑定向导");
                    openActivity(ParentsAppDownloadActivity.class);
                    break;
                case R.id.rl_operating_video:
                    Logger.d(TAG, "使用向导");
                    openActivity(UsingWizardActivity.class);
//                    openActivity(TestActivity.class);
                    break;
                case R.id.rl_wifi:
                    Logger.d(TAG, "网络连接 wifi");
                    if (!WifiUtil.getInstance().isWifiOpen()) {
                        WifiUtil.getInstance().setWifiEnable(true);
                    }
                    if (getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)) {
                        openActivity(WifiActivity.class);
                    } else {
                        openActivity(NetConnActivity.class);
                    }
                    break;
                case R.id.rl_volume_setting://音量
                    bundle.putString(Constants.FLAG_SYSTEM_SETTING, Constants.FLAG_SYSTEM_SETTING_VOICE);
                    openActivity(SystemControlActivity.class, bundle);
                    break;
                case R.id.rl_bright_setting://亮度
                    bundle.putString(Constants.FLAG_SYSTEM_SETTING, Constants.FLAG_SYSTEM_SETTING_BRIGHT);
                    openActivity(SystemControlActivity.class, bundle);
                    break;
                case R.id.rl_local_info://本机信息
                    openActivity(LocalInfoActivity.class);
                    break;
                case R.id.rl_setting_turn_off://关机
                    turnOff();
                    break;
                case R.id.rl_setting_time://时间
                    openActivity(TimeActivity.class);
                    break;
                case R.id.rl_setting_bellset://本地铃声
                    openActivity(LocalBellsetActivity.class);
                    break;
                case R.id.btn_switch_wifi://wifi开关
                    clickWifi();
                    break;
                case R.id.iv_switch_mobile://移动网络
                    clickMobile();
                    break;
                case R.id.rl_switch_mobile://移动网络
                    clickMobile();
                    break;
                case R.id.rl_wireless_upgrade://无线升级
                    enterWirelessUpgrade();
                    break;
            }
        }
    };

    /**
     * 进入无线升级
     */
    private void enterWirelessUpgrade() {
        SpecialCharSequenceMgr.launchApp(mContext, BaseConstant.pgk_fota_upgrade);
    }

    /**
     * 检测移动网络是否连接
     */
    private void checkMobileState() {
        boolean result = MobileUtil.ishasSimCard(mContext);
        if (result) {
            boolean isOpenMobile = MobileUtil.getMobileDataStatus(mContext);

            if (isOpenMobile) {

                btnSwitchMobile.setBackgroundResource(R.drawable.icon_turn_on);
                enableMobiletate = true;
                return;
            }
        }

        btnSwitchMobile.setBackgroundResource(R.drawable.icon_turn_off);
        enableMobiletate = false;

    }

    /**
     * 点击开关移动网络
     */
    private void clickMobile() {
//         boolean result = MobileUtil.setMobileDataStatus(mContext, !enableMobiletate);
        boolean result = MobileUtil.ishasSimCard(mContext);
        MyApp.saveMobieSwitch(true);
        if (result) {
            if (enableMobiletate) {
                MyApp.saveMobieState(false);
                Logger.d(TAG, "关闭移动网络");
                enableMobiletate = false;
                MobileUtil.setMobileData(mContext, enableMobiletate);
                btnSwitchMobile.setBackgroundResource(R.drawable.icon_turn_off);
            } else {
                Logger.d(TAG, "开启移动网络");
                enableMobiletate = true;
                MyApp.saveMobieState(true);
                MobileUtil.setMobileData(mContext, enableMobiletate);
                btnSwitchMobile.setBackgroundResource(R.drawable.icon_turn_on);
                closeFristBoot();
            }

        } else {
            toast(mContext.getResources().getString(R.string.hint_no_use_mobile_net));
            speak(FuncTitle.CONTENT_VOICE_HINT_INSTALL_SIM.toString());
        }
    }

    /**
     * 切换WiFi
     */
    private void clickWifi() {
        if (isEnable) {
            isEnable = false;
            WifiUtil.getInstance().setWifiEnable(false);
            tvConnWifiName.setText(getStrByRes(R.string.close));
        } else {
            isEnable = true;
            WifiUtil.getInstance().setWifiEnable(true);
            tvConnWifiName.setText(getStrByRes(R.string.connecting));
            toast(getStrByRes(R.string.please_later));
        }
        MyApp.saveWifiSwitch(isEnable);
        checkNetState(isEnable);
    }

    private TDialog turnOffDialog;

    //关机
    private void turnOff() {
        turnOffDialog = new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.view_turn_off_dialog)
                .setScreenWidthAspect(PackageUtil.getActivity(), 1f)
                .setScreenHeightAspect(PackageUtil.getActivity(), 1f)
                .setGravity(Gravity.BOTTOM)     //设置弹窗展示位置
                .setTag("DialogTest")   //设置Tag
                .setDimAmount(0.25f)     //设置弹窗背景透明度(0-1f)
                .setCancelableOutside(false)     //弹窗在界面外是否可以点击取消
                .setCancelable(true)    //弹窗是否可以取消
                .addOnClickListener(R.id.btn_cancel, R.id.btn_confirm)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        switch (view.getId()) {
                            case R.id.btn_cancel:
                                turnOffDialog.dismiss();
                                break;
                            case R.id.btn_confirm:
                                turnOffDialog.dismiss();
                                //执行关机操作
//                                speakTTS(InstructUtils.respondTurnOff(), TTSEngine.TTS_FLAG_COMP_TURN_OFF);
                                turnOffBroadcast();
                                break;
                        }
                    }
                })
                .create().show();
    }

    @Override
    public void onHomePressed() {
        if (!MyApp.getInstance().getSpManager().isFristBoot()) {//第一次进来的时候屏蔽掉Home键
            super.onHomePressed();
        }
    }
}
