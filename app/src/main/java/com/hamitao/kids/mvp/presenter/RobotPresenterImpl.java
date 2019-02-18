package com.hamitao.kids.mvp.presenter;

import android.content.Context;

import com.hamitao.kids.mvp.imodel.IRobotModel;
import com.hamitao.kids.mvp.ipresenter.IRobotPresenter;
import com.hamitao.kids.mvp.model.RobotModelImpl;
import com.hamitao.kids.mvp.view.IRobotView;
import com.hamitao.requestframe.entity.ParseChineseInfo;
import com.hamitao.requestframe.entity.QueryRelationInfo;
import com.hamitao.requestframe.entity.RequestParseInfo;
import com.hamitao.requestframe.view.ParseChineseView;
import com.hamitao.requestframe.view.QueryRelationView;

import java.util.List;

/**
 * @data on 2018/5/30 14:21
 * @describe:
 */

public class RobotPresenterImpl implements IRobotPresenter {
    private Context mContext;
    private IRobotView mRobotView;
    private IRobotModel iRobotModel;



    public RobotPresenterImpl(Context context, IRobotView robotView) {
        iRobotModel = new RobotModelImpl(context);
        this.mContext = context;
        this.mRobotView = robotView;
    }

    @Override
    public void handlerAsrResult(String asrResult,String aiResult) {
        mRobotView.handlerAsrResultListener(asrResult,aiResult);
    }

    @Override
    public void semanticParsing(String result,String aiResult) {
        mRobotView.onSemanticParsing(result,aiResult);
    }

    @Override
    public void parseChinese(RequestParseInfo requestParseInfo) {
        iRobotModel.parseChinese(requestParseInfo, new ParseChineseView() {
            @Override
            public void onSuccess(ParseChineseInfo info) {
                mRobotView.onParseChineseSuccessListener(info);
            }

            @Override
            public void onError(String result) {
                mRobotView.onParseChineseErrorListener(result);
            }
        });
    }

    @Override
    public void handlerSemanticsResult(String content) {
        mRobotView.onSemanticsResultListener(content);
    }

    @Override
    public void handlerNLPParseAnswer(List<ParseChineseInfo.ResponseDataObjBean.NlpParseAnswersBean> nlpParseAnswers) {
        mRobotView.onNLPParseAnswerListener(nlpParseAnswers);
    }

    @Override
    public void handlerAISpeechSemantic(String content) {
        mRobotView.onAISpeechSemanticListener(content);
    }

    @Override
    public void queryRelation(String myid) {
        iRobotModel.queryRelation(myid, new QueryRelationView() {
            @Override
            public void onSuccess(QueryRelationInfo info) {
                mRobotView.OnQueryRelationSuccessListener(info);
            }

            @Override
            public void onError(String result) {

            }
        });
    }


}
