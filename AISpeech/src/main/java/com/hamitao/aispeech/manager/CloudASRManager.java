package com.hamitao.aispeech.manager;

import android.content.Context;

import com.aispeech.common.Util;
import com.aispeech.export.engines.AICloudASREngine;
import com.hamitao.aispeech.listenerimpl.AICloudASRListenerImpl;
import com.hamitao.aispeech.util.AppKey;
import com.hamitao.aispeech.util.SampleConstants;
import com.hamitao.aispeech.view.ASRView;

/**
 * @data on 2018/6/19 19:00
 * @describe: 云端语义识别
 */

public class CloudASRManager {

    private Context mContext;

    private AICloudASREngine mEngine;
    private ASRView mView;

    public CloudASRManager(Context context, ASRView asrView) {
        this.mContext = context;
        this.mView = asrView;
    }

    public void initCloudASR() {
        if (mEngine != null) {
            mEngine.destroy();
        }
        mEngine = AICloudASREngine.createInstance();
//        mEngine.setResStoragePath("/sdcard/aispeech/");//设置自定义目录放置资源，如果要设置，请预先把相关资源放在该目录下
        mEngine.setRes("aihome");
        mEngine.setVadResource(SampleConstants.vad_res);
        mEngine.setDeviceId(Util.getIMEI(mContext));
        mEngine.setHttpTransferTimeout(10);
        mEngine.init(mContext, new AICloudASRListenerImpl(mView), AppKey.APPKEY, AppKey.SECRETKEY);
        mEngine.setNoSpeechTimeOut(0);

    }

    public void start() {
        if (mEngine != null) {
            mEngine.start();
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
