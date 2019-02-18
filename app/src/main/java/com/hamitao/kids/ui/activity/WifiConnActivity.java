package com.hamitao.kids.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.utils.EditUtil;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.WifiUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.dialog.KeyboardDialog;
import com.hamitao.kids.utils.WindowUtils;
import com.hamitao.kids.view.TitleView;

import butterknife.BindView;

/**
 * @data on 2018/6/4 10:08
 * @describe: 连接WiFi
 */

public class WifiConnActivity extends BaseMsgActivity {
    @BindView(R.id.et_wifi_pass)
    EditText etWifiPass;
    @BindView(R.id.tv_conn_wifi_name)
    TextView tvConnWifiName;
    @BindView(R.id.tv_wifi_conn_cancel)
    Button tvCanael;
    @BindView(R.id.tv_wifi_conn_comfirm)
    Button tvComfirm;
    private TitleView mTitle;

    private String SSID;//wifi名称
    private boolean isConn = false;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_wifi_conn);
        if (MyApp.getInstance().getSpManager().isFristBoot()) {
            MyApp.addActivity(this);
        }
    }

    @Override
    public void initData() {
        setEdit(R.drawable.line);

        mTitle = new TitleView(this);
        mTitle.setTitle(getStrByRes(R.string.input_psw));

        Intent intent = getIntent();
        SSID = intent.getStringExtra(Constants.FLAG_WIFI_NAME);

        tvConnWifiName.setText(SSID);

        tvCanael.setOnClickListener(mOnClickListener);
        tvComfirm.setOnClickListener(mOnClickListener);

        registerBroadcast();
    }

    /**
     * 编辑框的设置
     */
    private void setEdit(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        etWifiPass.setCompoundDrawables(null, null, null, drawable);

        EditUtil.setEditInputMode(mActivity, etWifiPass);

        etWifiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d(TAG, "点击了编辑框");
                WindowUtils.hidePopupWindow();
                KeyboardDialog dialog = new KeyboardDialog(mActivity);
                dialog.show();

                dialog.setEditValue(etWifiPass.getText().toString());
                dialog.setOnClickListener(new KeyboardDialog.OnKeyBoardClickListener() {
                    @Override
                    public void onBackPressed(String keyValue) {
                        checkUnReadCount();
                        etWifiPass.setText(keyValue);
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
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
                            speak(FuncTitle.CONTENT_VOICE_HINT_NET_CONNTCT.toString());
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
                    speak(FuncTitle.CONTENT_VOICE_HINT_NET_CONNTCT_FAIL.toString());
                    toast(getStrByRes(R.string.net) + SSID + getStrByRes(R.string.psd_mistake));
                    tvComfirm.setClickable(true);
                    tvComfirm.setTextColor(getResources().getColor(R.color.white));
                    tvCanael.setClickable(true);
                    tvCanael.setTextColor(getResources().getColor(R.color.white));
                }
            }
        }
    };

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_wifi_conn_comfirm:
                    String pwd = etWifiPass.getText().toString();
                    Logger.d(TAG, "账号=" + SSID + "     密码=" + pwd);
                    if (SSID != null) {
                        if (pwd != null && pwd.length() >= 8) {
                            toast("正在连接……");
                            isConn = true;
                            WifiUtil.getInstance().addNetWork(WifiUtil.getInstance().createWifiInfo(SSID, pwd, pwd.length() == 0 ? 1 : 3));
                            tvComfirm.setClickable(false);
                            tvComfirm.setTextColor(getResources().getColor(R.color.gray));
                            tvCanael.setClickable(false);
                            tvCanael.setTextColor(getResources().getColor(R.color.gray));
                        }else{
                            toast(getStrByRes(R.string.hint_psw_less_than_eight));
                        }
                    }
                    break;
                case R.id.tv_wifi_conn_cancel:
                    finish();
                    break;
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

    @Override
    protected void onResume() {
        super.onResume();

    }
}
