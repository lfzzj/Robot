package com.hamitao.kids.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.framework.network.NetworkStatus;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.WifiUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.view.TitleView;
import com.hamitao.kids.widgets.NetConnWayView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 网络连接
 */
public class NetConnActivity extends BaseMsgActivity {
    private TitleView mTitle;

    @BindView(R.id.view_scan_conn)
    NetConnWayView viewScanConn;//扫码连接
    @BindView(R.id.view_manual_conn)
    NetConnWayView viewManualConn;//手动连接
    @BindView(R.id.view_mobile_network)
    NetConnWayView viewMobileConn;//移动网络

    @BindView(R.id.tv_net_name)
    TextView tvNetName;
    @BindView(R.id.rl_bg)
    RelativeLayout rlBg;
    @BindView(R.id.tv_skip)
    TextView tvSkip;


    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_network_connect);
        if (MyApp.getInstance().getSpManager().isFristBoot()) {
            MyApp.addActivity(this);
        }
    }

    @Override
    public void initData() {

        mTitle = new TitleView(this);
        mTitle.setTitle(getStrByRes(R.string.func_setting_net_conn));

        if (MyApp.getInstance().getSpManager().isFristBoot()) {
            viewMobileConn.setVisibility(View.VISIBLE);
            rlBg.setVisibility(View.VISIBLE);
        }

        setCurNetName();
        viewScanConn.setNetConnWay(R.drawable.icon_scan_conn, getStrByRes(R.string.scan_conn));
        viewManualConn.setNetConnWay(R.drawable.icon_manual_conn, getStrByRes(R.string.manual_conn));
        viewMobileConn.setNetConnWay(R.drawable.icon_mobile_conn, getStrByRes(R.string.mobile_conn));

        if (MyApp.getInstance().getSpManager().isFristBoot()) {
            viewScanConn.setVisibility(View.GONE);
            tvSkip.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 获得当前连接的热点 用上面方法 可能获得的结果为： "0x" 或 "<unknown ssid>"
     * 猜测是因为wifiInfo的问题，因此每次去getSSID（）的时候需要获得最新的wifiInfo对象
     */
    private void setCurNetName() {
        String connWifiSSID = WifiUtil.getInstance().getCurConnWifiName();
        if (!"".equals(connWifiSSID) && !"<unknown ssid>".equals(connWifiSSID) && !"0x".equals(connWifiSSID)) {
            tvNetName.setText(getStrByRes(R.string.connect) + connWifiSSID);
        }else{
            tvNetName.setText(getStrByRes(R.string.hint_no_net) );
        }
    }

    @OnClick({R.id.view_scan_conn, R.id.view_manual_conn, R.id.view_mobile_network, R.id.tv_skip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_scan_conn://扫码连接
                Logger.d(TAG, "扫码连接");
                openActivity(ScanConnActivity.class);
                break;
            case R.id.view_manual_conn://手动连接
                Logger.d(TAG, "手动连接");
                openActivity(WifiActivity.class);
                break;
            case R.id.view_mobile_network://移动网络
                Logger.d(TAG, "移动网络");
                openActivity(SettingActivity.class);
                break;
            case R.id.tv_skip:
                closeFristBoot();
                MyApp.getInstance().getSpManager().putFristBoot();
                finish();
                break;
        }
    }

    @Override
    public void onNetStatusChanged(NetworkStatus currNetStatus) {
        setCurNetName();
    }

    @Override
    public void onBackPressed() {
        if (!MyApp.getInstance().getSpManager().isFristBoot()) {//第一次进来的时候屏蔽掉返回键
            super.onBackPressed();
        }
    }

    @Override
    public void onHomePressed() {
        if (!MyApp.getInstance().getSpManager().isFristBoot()) {//第一次进来的时候屏蔽掉Home键
            super.onHomePressed();
        }
    }


}
