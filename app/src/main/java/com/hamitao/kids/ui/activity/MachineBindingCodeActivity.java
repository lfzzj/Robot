package com.hamitao.kids.ui.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.QRCodeUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.view.TitleView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 机器码绑定
 */
public class MachineBindingCodeActivity extends BaseMsgActivity {
    @BindView(R.id.iv_parents_app_download_code)
    ImageView ivCode;
    @BindView(R.id.tv_parents_app_download)
    TextView tvHint;
    @BindView(R.id.tv_parents_app_download_next)
    TextView tvBtn;

    private TitleView mTitle;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_parents_app_download);
        MyApp.addActivity(this);
    }

    @Override
    public void initData() {
        mTitle = new TitleView(this);
        mTitle.setTitle(getStrByRes(R.string.tv_machine_binding_code));
        tvHint.setText(getStrByRes(R.string.tv_binding_robot_hint));
        tvBtn.setText(getStrByRes(R.string.tv_finish));

        if (MyApp.getInstance().isDeviceCreateSuccess()) {
            String vendor = getStrByRes(R.string.vendor);
            String vendorStr = "";
            if (!getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_HAMITAO)) {
                vendorStr = "," + vendor;
            }
            String content = "deviceid=" + deviceManager.getDeviceId() + vendorStr;
            Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(content, 480, 480);
            ivCode.setImageBitmap(mBitmap);
        } else {
            toast(getStrByRes(R.string.hint_net_unconnect));
        }
    }

    @OnClick({R.id.rl_parents_app_download_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_parents_app_download_next:
                if (MyApp.getInstance().getSpManager().isFristBoot()) {
                    MyApp.getInstance().getSpManager().putFristBoot();
                    finish();
                } else {
                    MyApp.clearActivity();
                }
                break;
        }
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
