package com.hamitao.kids.mvp.view;

import com.hamitao.requestframe.entity.ParseChineseInfo;
import com.hamitao.requestframe.entity.QueryRelationInfo;

import java.util.List;

/**
 * @data on 2018/6/20 19:56
 * @describe:
 */

public interface IRobotView {

    //处理ASR结果
    void handlerAsrResultListener(String result, String aiResult);

    //处理语义解析
    void onSemanticParsing(String result, String aiResult);

    //服务器语义解析成功
    void onParseChineseSuccessListener(ParseChineseInfo info);

    //服务器数据请求失败
    void onParseChineseErrorListener(String result);

    //处理语义解析的结果
    void onSemanticsResultListener(String content);

    //关系查询
    void OnQueryRelationSuccessListener(QueryRelationInfo info);

    //思必驰语义解析
    void onAISpeechSemanticListener(String content);

    //处理服务器解析
    void onNLPParseAnswerListener(List<ParseChineseInfo.ResponseDataObjBean.NlpParseAnswersBean> nlpParseAnswers);
}
