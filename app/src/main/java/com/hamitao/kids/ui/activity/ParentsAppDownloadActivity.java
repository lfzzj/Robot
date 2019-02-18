package com.hamitao.kids.ui.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
 * 手机app下载
 */
public class ParentsAppDownloadActivity extends BaseMsgActivity {
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
        mTitle.setTitle(getStrByRes(R.string.func_app_download));
        tvHint.setText(getStrByRes(R.string.tv_parents_app_download_hint));
        tvBtn.setText(getStrByRes(R.string.tv_next));

        Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(getStrByRes(R.string.app_download_url), 480, 480);
        ivCode.setImageBitmap(mBitmap);
    }

    @OnClick({R.id.rl_parents_app_download_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_parents_app_download_next:
                openActivity(MachineBindingCodeActivity.class);
                break;
        }
    }
}
