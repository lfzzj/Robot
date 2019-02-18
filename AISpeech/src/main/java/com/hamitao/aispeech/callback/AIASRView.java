package com.hamitao.aispeech.callback;

import com.aispeech.AIError;
import com.aispeech.AIResult;

public interface AIASRView {

    //初始化成功
    void onInitSuccess();

    //初始化失败
    void onInitFailure();

    //识别结果(该引擎如果检测到连说（唤醒词+命令词）直接在唤醒引擎的onResult回调中输出结果)
    void onResults(AIResult aiResult);

    //如果检测到不是连说（只有唤醒词），那么会回调onNotOneShot()
    void onNotOneShot();

    //识别出错
    void onError(AIError aiError);
}
