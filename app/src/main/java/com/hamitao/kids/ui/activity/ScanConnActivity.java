package com.hamitao.kids.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.StringUtil;
import com.hamitao.framework.utils.WifiUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.view.TitleView;
import com.hamitao.kids.zxing.android.CaptureActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ScanConnActivity extends BaseMsgActivity {
    private TitleView mTitle;

    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.btn_scan)
    Button btnScan;


    private static final int REQUEST_CODE_SCAN_CONN = 0x0001;


    private boolean isConn = false;//正在连接中
    private String SSID;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_scan_conn);
        if (MyApp.getInstance().getSpManager().isFristBoot()) {
            MyApp.addActivity(this);
        }
    }

    @Override
    public void initData() {
        tvDesc.setText(Html.fromHtml(getResources().getString(R.string.scan_conn_desc)));

        mTitle = new TitleView(this);
        mTitle.setTitle(getStrByRes(R.string.scan_conn));

        registerBroadcast();
    }

    @OnClick({R.id.btn_scan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_scan:
                Logger.d(TAG, "扫一扫");
                Intent intent = new Intent(ScanConnActivity.this,
                        CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN_CONN);
                break;
        }
    }

    private static final String DECODED_CONTENT_KEY = "codedContent";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN_CONN && resultCode == RESULT_OK) {
            btnScan.setClickable(false);
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Logger.d(TAG, "content=" + content);
                if (content != null) {
                    isConn = true;
                    SSID = StringUtil.getStringBefore(content);
                    String pwd = StringUtil.getStringAfter(content);
                    Logger.d(TAG, "SSID=" + SSID + "   pwd=" + pwd);
                    toast(getStrByRes(R.string.connected));
                    WifiUtil.getInstance().addNetWork(WifiUtil.getInstance().createWifiInfo(SSID, pwd, pwd.length() == 0 ? 1 : 3));
                }
            }
        }
    }

    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        registerReceiver(mReceiver, filter);
    }

    /**
     * 广播接收，监听网络
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            // wifi已成功扫描到可用wifi。
            if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (null != parcelableExtra) {
                    NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                    NetworkInfo.State state = networkInfo.getState();
                    boolean isConnected = state == NetworkInfo.State.CONNECTED;
                    if (isConnected) {
                        Logger.d(TAG, "WIFI已连接");
                        if (isConn) {
                            toast(getStrByRes(R.string.net) + SSID + getStrByRes(R.string.connect_success));
                            closeFristBoot();
                            finish();
                        }
                    }
                }
            } else if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                int linkWifiResult = intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, 123);
                if (linkWifiResult == WifiManager.ERROR_AUTHENTICATING) {
                    isConn = false;
                    Logger.d(TAG, "密码错误");
                    toast(getStrByRes(R.string.net) + SSID + getStrByRes(R.string.psd_mistake));
                    btnScan.setClickable(true);
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }


    @Override
    public void onHomePressed() {
        if (!MyApp.getInstance().getSpManager().isFristBoot()) {//第一次进来的时候屏蔽掉Home键
            super.onHomePressed();
        }
    }
}
