package com.hamitao.kids.mvp.view;

import java.util.List;

/**
 * @data on 2018/4/16 11:10
 * @describe:
 */

public interface ICommonView {

    /**
     * ASR识别结果 回调
     *
     * @param list
     */
    void OnAsrResultListener(List<String> list);

    /**
     * 语音识别开始
     */
    void OnStartAsrListener();

    /**
     * 语音识别出错
     */
    void OnAsrErrorListener();

    /**
     * TTS 朗读结束
     */
    void OnSpeakCompletedListener();

    /**
     * txt开始朗诵
     */
    void onTextStartListener();

    /**
     * txt朗诵完毕
     */
    void OnTextCompletedListener();


}
