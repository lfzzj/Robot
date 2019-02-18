package com.hamitao.kids.manager.wakeup.callback;

/**
 * 硬指令回调
 */
public interface InstructionCallBack {
    void onShutDown();

    void onPausePlay();

    void onResumePlay();

    void onTurnOnLight();

    /**
     * 打开功能区
     * @param flag
     */
    void onOpenFunc(String flag);
}
