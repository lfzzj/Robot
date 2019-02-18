package com.hamitao.kids.ui.activity;

import android.widget.TextView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.DeviceUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.view.TitleView;

import butterknife.BindView;

/**
 * @data on 2018/7/5 11:21
 * @describe: 本机信息
 */

public class LocalInfoActivity extends BaseMsgActivity {
    @BindView(R.id.tv_local_info)
    TextView tvLocalInfo;

    private TitleView mTitle;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_local_info);
    }

    @Override
    public void initData() {
        mTitle = new TitleView(this);
        mTitle.setTitle(getResources().getString(R.string.func_local_info));
        String manufacturers = null;
        String macAddress = deviceManager.getLocalMacAddress();
        String imei = deviceManager.getIMEI();
        if (getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
            manufacturers = "\n   " + getStrByRes(R.string.jinguowei);
            macAddress = "\n   " + macAddress;
            imei = "\n   " + imei;
        }else if (getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_HAIER)){
            manufacturers = "\n" + getStrByRes(R.string.haier) + "\n" + getStrByRes(R.string.haier_info);
        }else{
            manufacturers = "\n    " + getStrByRes(R.string.data)
                    + "\n    " + getStrByRes(R.string.data_info);
        }
        tvLocalInfo.setText(getStrByRes(R.string.manufacturers) + manufacturers + "\n\n" +
                getStrByRes(R.string.version_num) + DeviceUtil.GetVersion(mContext) + "\n\n" +
                getStrByRes(R.string.operating_system) + "Android" + "\n\n" +
                getStrByRes(R.string.mac_address) + macAddress + "\n\n" +
                getStrByRes(R.string.imei_num) + imei + "\n\n"
        );


//        bluetoothUtil = new BluetoothUtil(LocalInfoActivity.this);
//
//        String bluetooth = bluetoothUtil.getBluetoothAddress();
//        String mac = deviceManager.getLocalMacAddress();
//
//        String content = "bluetooth=" + bluetooth + "&mac=" + mac + "&deviceid=" + deviceManager.getDeviceId();
//
//        Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(content, 480, 480);
//        ivLocalInfo.setImageBitmap(mBitmap);


    }


}
