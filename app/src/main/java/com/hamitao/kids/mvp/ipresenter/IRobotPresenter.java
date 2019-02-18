package com.hamitao.kids.mvp.ipresenter;

import com.hamitao.requestframe.entity.ParseChineseInfo;
import com.hamitao.requestframe.entity.RequestParseInfo;

import java.util.List;

/**
 * @data on 2018/5/30 14:20
 * @describe:
 */

public interface IRobotPresenter {

    /**
     * 处理ASR结果
     *
     * @param asrResult
     * @param aiResult 思必驰语义解析结果
     */
    void handlerAsrResult(String asrResult, String aiResult);

    /**
     * 语义解析
     *
     * @param result
     */
    void semanticParsing(String result, String aiResult);

    /**
     * 服务器数据请求
     *
     * @param requestParseInfo
     */
    void parseChinese(RequestParseInfo requestParseInfo);


    /**
     * 处理语义解析结果
     *
     * @param content 搜索结果
     */
    void handlerSemanticsResult(final String content);

    /**
     * 处理服务器返回的数据
     *
     * @param nlpParseAnswers
     */
    void handlerNLPParseAnswer(List<ParseChineseInfo.ResponseDataObjBean.NlpParseAnswersBean> nlpParseAnswers);


    /**
     * 处理思必驰语义解析返回的结果
     * @param content
     */
    void handlerAISpeechSemantic(String content);


    /**
     * 关系查询
     *
     * @param myid
     */
    void queryRelation(String myid);


}
