package com.hamitao.aispeech.manager;

import android.content.Context;

import com.aispeech.common.Util;
import com.aispeech.export.engines.AICloudSdsEngine;
import com.hamitao.aispeech.listener.AISdsListenerImpl;
import com.hamitao.aispeech.util.AppKey;
import com.hamitao.aispeech.util.SampleConstants;
import com.hamitao.aispeech.view.SdsView;

/**
 * @data on 2018/6/19 19:38
 * @describe: 云端对话 (语义解析)
 */

public class CloudSdsManager {
    private Context mContext;

    private AICloudSdsEngine mEngine;


    public CloudSdsManager(Context context) {
        this.mContext = context;
    }

    public void initCloudSds(SdsView sdsView) {
        mEngine = AICloudSdsEngine.createInstance();
//        mEngine.setResStoragePath("/sdcard/aispeech/");//设置自定义目录放置资源，如果要设置，请预先把相关资源放在该目录下
        mEngine.setRes("aihome");
//        mEngine.setServer("ws://s-test.api.aispeech.com:10000");
        mEngine.setServer("ws://s.api.aispeech.com:1028,ws://s.api.aispeech.com:80");
        mEngine.setVadResource(SampleConstants.vad_res);
//        mEngine.setUserId("AISPEECH"); //填公司名字
        mEngine.init(mContext, new AISdsListenerImpl(sdsView), AppKey.APPKEY, AppKey.SECRETKEY);
        mEngine.setDeviceId(Util.getIMEI(mContext));
    }

    public void startWithRecording() {
        if (mEngine != null) {
            mEngine.startWithRecording();
        }
    }

    public void startWithText(String refText) {
        if (mEngine != null) {
            mEngine.startWithText(refText);
        }
    }

    public void stop() {
        if (mEngine != null) {
            mEngine.stopRecording();
        }
    }

    public void onDestroy() {
        if (mEngine != null) {
            mEngine.destroy();
            mEngine = null;
        }
    }
}
