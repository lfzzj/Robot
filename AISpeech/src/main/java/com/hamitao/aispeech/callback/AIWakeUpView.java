package com.hamitao.aispeech.callback;

public interface AIWakeUpView {
    //唤醒引擎初始化成功
    void onInitSuccess();

    //唤醒引擎初始化失败
    void onInitFailure();

    /**
     * 唤醒
     * 这里启动带有oneshot功能的识别引擎，
     * 如果检测到连说（唤醒词+命令词），直接在唤醒引擎的onResult回调中输出结果，
     * 如果检测到不是连说（只有唤醒词），回调onNotOneShot()
     */
    void onWakeup();
}
