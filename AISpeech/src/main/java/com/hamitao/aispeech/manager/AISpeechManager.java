package com.hamitao.aispeech.manager;

import android.content.Context;

import com.aispeech.export.listeners.AIAuthListener;
import com.aispeech.speech.AIAuthEngine;
import com.hamitao.aispeech.util.AppKey;
import com.hamitao.aispeech.view.AISpeechInitView;
import com.hamitao.framework.utils.Logger;

import java.io.FileNotFoundException;

/**
 * @data on 2018/6/19 10:31
 * @describe: 思必驰统一管理
 */

public class AISpeechManager {
    private static String TAG = "AISpeechManager";

    private Context mContext;
    private AIAuthEngine mEngine;

    public AISpeechManager(Context context) {
        this.mContext = context;
    }

    public void initAISpeech(final AISpeechInitView aiSpeechInitView) {
        mEngine = AIAuthEngine.getInstance(mContext);
        try {
            mEngine.init(AppKey.APPKEY, AppKey.SECRETKEY, "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mEngine.setOnAuthListener(new AIAuthListener() {
            @Override
            public void onAuthSuccess() {
                Logger.d(TAG, "onAuthSuccess");
                if (aiSpeechInitView != null) {
                    aiSpeechInitView.onSuccess();
                }
            }

            @Override
            public void onAuthFailed(String s) {
                Logger.d(TAG, "onAuthFailed==="+s);
                if (aiSpeechInitView != null) {
                    aiSpeechInitView.onFailed();
                }
            }
        });

        if (mEngine.isAuthed()) {
            Logger.d(TAG, "思必驰已授权");
            if (aiSpeechInitView != null) {
                aiSpeechInitView.onSuccess();
            }
        } else {
            Logger.d(TAG, "思必驰未授权");
            boolean authRet = mEngine.doAuth();
            if (authRet) {
                Logger.d(TAG, "思必驰授权成功");
                if (aiSpeechInitView != null) {
                    aiSpeechInitView.onSuccess();
                }
            } else {
                Logger.d(TAG, "思必驰授权失败");
                if (aiSpeechInitView != null) {
                    aiSpeechInitView.onFailed();
                }
            }
        }
    }

}
