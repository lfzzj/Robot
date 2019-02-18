package com.hamitao.kids.manager.wakeup;

import com.hamitao.requestframe.entity.ParseChineseInfo;

public interface WakeUpView {
    /**
     * 处理ASR结果
     *
     * @param result
     */
    void handlerAsrResultListener(String result);

    /**
     * 服务器数据请求成功
     *
     * @param info
     */
    void handlerParsingByServerSuccess(ParseChineseInfo info);

    /**
     * 服务器数据请求失败
     *
     * @param result
     */
    void handlerParsingByServerError(String result);


    //解析Turing数据
    void handlerParsingByTuringSuccess(String result);


//    /**
//     * 思必驰数据请求结果
//     */
//    void handlerParsingByAISpeech();
}
