package com.hamitao.kids.ui.activity;

import android.content.Intent;

import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.model.AnyEventType;
import com.hamitao.kids.zxing.android.CaptureActivity;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.constant.Constants;

import org.greenrobot.eventbus.Subscribe;

/**
 * @data on 2018/7/5 15:48
 * @describe: 扫绘本
 */

public class ScanBookActivity extends BaseMsgActivity {


    private static final int REQUEST_CODE_SCAN = 0x0000;

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";


    @Override
    public void setActivityView() {
        MyApp.addActivity(this);
    }

    @Override
    public void initData() {
        Intent intent = new Intent(ScanBookActivity.this,
                CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);

        if (!isNetworkAvailable()){
            playHint.playFuncTitle(FuncTitle.CONTENT_VOICE_HINT_NET_DISCONNTCT.toString());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Logger.d(TAG, "content=" + content);
                if (content != null) {
                    Logger.d("player","扫码后播放内容");
                    enterPlayActivity(Constants.FLAG_ENTER_PLAY_ACTIVITY_NFC_QUERY, content);
                    finish();
                }
            }
        }
    }

    @Subscribe
    public void getEventBus(AnyEventType anyEventType) {
        String flag = anyEventType.getFlag();
        if (Constants.FLAG_SCAN_BOOK_BACK.equals(flag)) {
            finish();
        }
    }


}
