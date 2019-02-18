package com.hamitao.kids.manager.wakeup;

import com.hamitao.aispeech.util.AISpeechParserManager;
import com.hamitao.kids.turing.callback.TuringParserCallBack;
import com.hamitao.requestframe.entity.ParseChineseInfo;

public interface IWakeUpFactory {

    /**
     * 处理Asr请求结果
     */
    void handlerAsrResult(String result);

    /**
     * 处理硬指令
     *
     * @param result
     */
    boolean handlerInstruction(String result);

    /**
     * 处理服务器返回结果
     *
     * @param result
     */
    void handlerParsingByServer(String result);

    /**
     * 处理turing数据
     * @param result
     */
    void handlerParsingByTuring(String result);

    /**
     * 处理思必驰数据
     *
     */
    void handlerParsingByAISpeech();

    /**
     * 处理服务器返回的数据
     *
     * @param result
     */
    void handlerDataByServer(ParseChineseInfo result);

    /**
     * 处理Turing解析数据
     * @param result
     */
    void handlerDataByTuring(String result, TuringParserCallBack callBack);

    /**
     * 处理思必驰返回的数据
     *
     * @param result
     */
    void handlerDataByAISpeech(String result);

    /**
     * 查询电话本
     * @param terminalId
     * @param contact
     */
    void queryTelephoneContact(String terminalId, String contact);

    /**
     * 查询聊天联系人
     * @param terminalId
     * @param whoName
     * @param isVideoChat 是否是视频聊天
     */
    void queryImChatRelation(String terminalId, String whoName, boolean isVideoChat);
}
