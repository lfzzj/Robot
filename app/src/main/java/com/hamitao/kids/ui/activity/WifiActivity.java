package com.hamitao.kids.ui.activity;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.hamitao.framework.network.NetworkStatus;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.WifiUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.WifiAdapter;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.model.WifiBean;
import com.hamitao.kids.utils.WifiAdminUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @data on 2018/6/1 13:49
 * @describe: wifi
 */

public class WifiActivity extends BaseMsgActivity {

    @BindView(R.id.rv_wifi_list)
    RecyclerView recyclerView;
    @BindView(R.id.btn_switch_wifi_head)
    ImageView btnSwitchWifi;


    private WifiAdapter wifiAdapter;

    private List<WifiBean> wifiResults = new ArrayList<>();
    private String connWifiSSID;

    private boolean isOpenWifi;

    private boolean isEnable = false;
    // Wifi管理类
    private WifiAdminUtils mWifiAdmin;


    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_wifi);
        if (MyApp.getInstance().getSpManager().isFristBoot()) {
            MyApp.addActivity(this);
        }
    }

    @Override
    public void initData() {
        mWifiAdmin = new WifiAdminUtils(this);
        initRecyclerView();
        initListener();
        initDatas();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        wifiAdapter = new WifiAdapter(recyclerView);
        recyclerView.setAdapter(wifiAdapter);
        wifiAdapter.setWifiItemClickListener(mWifiClickListener);
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        btnSwitchWifi.setOnClickListener(mOnClickListener);
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        isOpenWifi = WifiUtil.getInstance().isWifiOpen();
        isEnable = isOpenWifi;
        checkNetState(isOpenWifi);

        connWifiSSID = WifiUtil.getInstance().getCurConnWifiName();
    }


    /**
     * wifi开关
     */
    private void checkNetState(boolean isOpen) {
        btnSwitchWifi.setBackgroundResource(R.drawable.icon_turn_on);
        if (isOpen) {//wifi已连接
            recyclerView.setVisibility(View.VISIBLE);
            if (WifiUtil.getInstance().isWifiOpen()) {
                notifyData();
            } else {
                notifyDataRest();
            }
        } else {
            btnSwitchWifi.setBackgroundResource(R.drawable.icon_turn_off);
            recyclerView.setVisibility(View.GONE);
        }
    }

    /**
     * 刷新数据
     */
    public void notifyData() {
        List<ScanResult> scanResults = WifiUtil.getInstance().getmWifiList();
        List<WifiBean> newWifiResults = getWifiLists(scanResults);
        wifiResults.clear();
        wifiResults.addAll(newWifiResults);
        wifiAdapter.setData(wifiResults);
        wifiAdapter.notifyDataSetChanged();
    }

    /**
     * 休息一会在刷新数据（防止 WiFi没有立即打开）
     */
    private void notifyDataRest() {
        btnSwitchWifi.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isOpenWifi = WifiUtil.getInstance().isWifiOpen();
                if (isOpenWifi) {
                    notifyData();
                } else {
                    btnSwitchWifi.setBackgroundResource(R.drawable.icon_turn_off);
                }
            }
        }, 4000);
    }

    /**
     * 转换数据列表
     *
     * @param scanResults
     * @return
     */
    private List<WifiBean> getWifiLists(List<ScanResult> scanResults) {
        List<WifiBean> wifiInfos = new ArrayList<>();
        connWifiSSID = WifiUtil.getInstance().getCurConnWifiName();

        for (int i = 0; i < scanResults.size(); i++) {
            String ssid = scanResults.get(i).SSID;
            int level = scanResults.get(i).level;
            String capabilities = scanResults.get(i).capabilities;
            String BSSID = scanResults.get(i).BSSID;
            if (!"".equals(connWifiSSID) && ssid.equals(connWifiSSID) || ("\"" + ssid + "\"").equals(connWifiSSID)) {
                wifiInfos.add(0, new WifiBean(ssid, level, true, capabilities, BSSID));
            } else {
                if (!TextUtils.isEmpty(ssid)) {
                    wifiInfos.add(new WifiBean(ssid, level, false, capabilities, BSSID));
                }
            }
        }
        return wifiInfos;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_switch_wifi_head:
                    if (isEnable) {
                        isEnable = false;
                        WifiUtil.getInstance().setWifiEnable(false);
                    } else {
                        isEnable = true;
                        WifiUtil.getInstance().setWifiEnable(true);
                        toast(getStrByRes(R.string.please_later));
                    }
                    checkNetState(isEnable);
                    break;
            }
        }
    };

    private WifiAdapter.OnWifiClickListener mWifiClickListener = new WifiAdapter.OnWifiClickListener() {
        @Override
        public void onClickListener(int position) {
            Logger.d(TAG, "position=" + position);
            WifiBean wifiBean = wifiResults.get(position);

            String desc = "";
            String descOri = wifiBean.getCapabilities();
            if (descOri.toUpperCase().contains("WPA-PSK")) {
                desc = "WPA";
            }
            if (descOri.toUpperCase().contains("WPA2-PSK")) {
                desc = "WPA2";
            }
            if (descOri.toUpperCase().contains("WPA-PSK")
                    && descOri.toUpperCase().contains("WPA2-PSK")) {
                desc = "WPA/WPA2";
            }

            if (desc.equals("")) {
                isConnectSelf(wifiBean);
                return;
            }
            isConnect(wifiBean);

//            if (!wifiBean.isConn()) {
//                Bundle bundle = new Bundle();
//                bundle.putString(Constants.FLAG_WIFI_NAME, wifiBean.getWifiName());
//                openActivity(WifiConnActivity.class, bundle);
//            }
        }
    };

    /**
     * 有密码验证连接
     *
     * @param wifiBean
     */
    private void isConnect(WifiBean wifiBean) {
        if (mWifiAdmin.isConnect(wifiBean)) {
            // 已连接，显示连接状态对话框
            toast(getStrByRes(R.string.connect));
        } else {
            // 未连接显示连接输入对话框
            Bundle bundle = new Bundle();
            bundle.putString(Constants.FLAG_WIFI_NAME, wifiBean.getWifiName());
            openActivity(WifiConnActivity.class, bundle);
        }
    }

    /**
     * 无密码直连
     *
     * @param scanResult
     */
    private void isConnectSelf(WifiBean scanResult) {
        if (mWifiAdmin.isConnect(scanResult)) {
            // 已连接，显示连接状态对话框
            toast(getStrByRes(R.string.connect));
        } else {
            boolean iswifi = mWifiAdmin.connectSpecificAP(scanResult);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (iswifi) {
                toast(getStrByRes(R.string.connect_success));
            } else {
                toast(getStrByRes(R.string.connect_fail));
            }
        }
    }

    @Override
    public void onNetStatusChanged(NetworkStatus currNetStatus) {
        Logger.d(TAG, "当前网络类型=" + currNetStatus);
        checkNetState(WifiUtil.getInstance().isWifiOpen());
    }


    @Override
    public void onHomePressed() {
        if (!MyApp.getInstance().getSpManager().isFristBoot()) {//第一次进来的时候屏蔽掉Home键
            super.onHomePressed();
        }
    }

}
