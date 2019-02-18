package com.hamitao.kids.manager.wakeup;

import android.content.Context;
import android.text.TextUtils;

import com.hamitao.aispeech.util.AISpeechParserManager;
import com.hamitao.aispeech.util.AISpeechParserUtil;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.Logger;
import com.hamitao.aispeech.util.InstructUtils;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.manager.wakeup.callback.DeviceCallBack;
import com.hamitao.kids.manager.wakeup.callback.InstructionCallBack;
import com.hamitao.kids.manager.wakeup.callback.ServerCallBack;
import com.hamitao.kids.model.RelationInfo;
import com.hamitao.kids.turing.callback.TuringParserCallBack;
import com.hamitao.kids.turing.util.TuringUtil;
import com.hamitao.kids.turing.callback.SemanticCallBack;
import com.hamitao.kids.turing.engine.TuringEngine;
import com.hamitao.kids.utils.Util;
import com.hamitao.requestframe.entity.CommandsBean;
import com.hamitao.requestframe.entity.ParseChineseInfo;
import com.hamitao.requestframe.entity.QueryContactInfo;
import com.hamitao.requestframe.entity.QueryRelationInfo;
import com.hamitao.requestframe.entity.RequestParseInfo;
import com.hamitao.requestframe.presenter.ParseChinesePresenter;
import com.hamitao.requestframe.presenter.QueryContactPresenter;
import com.hamitao.requestframe.presenter.QueryRelationPresenter;
import com.hamitao.requestframe.view.ParseChineseView;
import com.hamitao.requestframe.view.QueryContactView;
import com.hamitao.requestframe.view.QueryRelationView;

import java.util.List;

public class WakeUpFactory implements IWakeUpFactory {
    private static final String TAG = "WakeUpFactory";

    private Context mContext;
    private WakeUpView wakeUpView;
    private InstructionCallBack instructionCallBack;//硬指令回调
    private ServerCallBack serverCallBack;//服务器内容回调
    private DeviceCallBack deviceCallBack;
//    private AISpeechParserManager aiSpeechParserManager;

    public WakeUpFactory(Context context, WakeUpView wakeUpView, InstructionCallBack instructionCallBack,
                         ServerCallBack serverCallBack, DeviceCallBack deviceCallBack
    ) {
        this.mContext = context;
        this.wakeUpView = wakeUpView;
        this.instructionCallBack = instructionCallBack;
        this.serverCallBack = serverCallBack;
        this.deviceCallBack = deviceCallBack;
    }

    @Override
    public void handlerAsrResult(String result) {
        wakeUpView.handlerAsrResultListener(result);
    }

    @Override
    public boolean handlerInstruction(String result) {
        String instrut = InstructUtils.checkHardInstruction(result);
        if (BaseConstant.INSTRUCTION_SHUTDOWN.equals(instrut)) {//关机
            if (instructionCallBack != null) {
                instructionCallBack.onShutDown();
                return true;
            }
        } else if (BaseConstant.INSTRUCTION_PAUSE.equals(instrut)) {//暂停播放
            if (instructionCallBack != null) {
                instructionCallBack.onPausePlay();
                return true;
            }
        } else if (BaseConstant.INSTRUCTION_RESUME.equals(instrut)) {//继续播放
            if (instructionCallBack != null) {
                instructionCallBack.onResumePlay();
                return true;
            }
        }
        else if (BaseConstant.INSTRUCTION_OPEN_CHINESE_POETRY.equals(instrut)){
            if (instructionCallBack != null) {
                instructionCallBack.onOpenFunc(Constants.FLAG_THREE_POETRY_RECITE);
                return true;
            }
        }
        else if (BaseConstant.INSTRUCTION_OPEN_ENGLISH_STUDY.equals(instrut)){
            if (instructionCallBack != null) {
                instructionCallBack.onOpenFunc(Constants.FLAG_THREE_ENGLISH_STUDY);
                return true;
            }
        }
        else if (BaseConstant.INSTRUCTION_OPEN_SAFETY_EDUCATION.equals(instrut)){
            if (instructionCallBack != null) {
                instructionCallBack.onOpenFunc(Constants.FLAG_THREE_SAFETY_EDUCATION);
                return true;
            }
        }
        else if (BaseConstant.INSTRUCTION_OPEN_READ_BOOK.equals(instrut)){
            if (instructionCallBack != null) {
                instructionCallBack.onOpenFunc(Constants.FLAG_THREE_READ_PICTURE_BOOK);
                return true;
            }
        }
        else if (BaseConstant.INSTRUCTION_OPEN_CHILDREN_SONG.equals(instrut)){
            if (instructionCallBack != null) {
                instructionCallBack.onOpenFunc(Constants.FLAG_THREE_NURSERY_RHYMES);
                return true;
            }
        }
        else if (BaseConstant.INSTRUCTION_OPEN_STORY.equals(instrut)){
            if (instructionCallBack != null) {
                instructionCallBack.onOpenFunc(Constants.FLAG_THREE_STORY);
                return true;
            }
        }
        else if (BaseConstant.INSTRUCTION_OPEN_ANIM.equals(instrut)){
            if (instructionCallBack != null) {
                instructionCallBack.onOpenFunc(Constants.FLAG_THREE_ANIM);
                return true;
            }
        }
        return false;
    }

    @Override
    public void handlerParsingByServer(String result) {
        ParseChinesePresenter parseChinesePresenter = new ParseChinesePresenter(mContext, new ParseChineseView() {
            @Override
            public void onSuccess(ParseChineseInfo info) {
                wakeUpView.handlerParsingByServerSuccess(info);
            }

            @Override
            public void onError(String result) {
                wakeUpView.handlerParsingByServerError(result);
            }
        });
        parseChinesePresenter.requestData(new RequestParseInfo(result));
    }

    @Override
    public void handlerParsingByTuring(String result) {
        TuringEngine.getSingleton().semanticData(result, new SemanticCallBack() {
            @Override
            public void onResult(String result) {
                Logger.d(TAG, "semanticData   请求成功==" + result);
                wakeUpView.handlerParsingByTuringSuccess(result);
            }

            @Override
            public void onError(String s) {
                Logger.d(TAG, "semanticData   请求失败==" + s);
                wakeUpView.handlerParsingByTuringSuccess("");
            }
        });
    }

    @Override
    public void handlerParsingByAISpeech() {
//        wakeUpView.handlerParsingByAISpeech();
    }

    @Override
    public void handlerDataByServer(ParseChineseInfo result) {
        List<ParseChineseInfo.ResponseDataObjBean.NlpParseAnswersBean> list = result.getResponseDataObj().getNlpParseAnswers();
        ParseChineseInfo.ResponseDataObjBean.NlpParseAnswersBean info = list.get(0);
        String scenario = info.getScenario();//得到的类型
        if (BaseConstant.SCENARIO_PLAY_CONTENT.equals(scenario)) {//内容
            if (serverCallBack != null) {
                serverCallBack.onPlayContent(Util.getMediaInfoByNetSeach1(info));
            }
        } else if (BaseConstant.SCENARIO_COMMAND.equals(scenario)) {
            CommandsBean commandBean = info.getCommands().get(0);
            String commandname = commandBean.getCommandname();//指令
            String who = commandBean.getParams().getWho();//指令对象
            if (BaseConstant.SCENARIO_IM_CHAT.equals(commandname)) {//im聊天 发消息
                if (serverCallBack != null) {
                    serverCallBack.onImChat(who);
                }
            } else if (BaseConstant.SCENARIO_VIDEO_CHAT.equals(commandname)) {//视频聊天
                if (serverCallBack != null) {
                    serverCallBack.onVideoChat(who);
                }
            }
        }
    }

    @Override
    public void handlerDataByTuring(String result, TuringParserCallBack callBack) {
        TuringUtil.parseTuringData(result, callBack);
    }

    @Override
    public void handlerDataByAISpeech(String result) {
//        AISpeechParserUtil.getAiSpeechSemanticParserData(result, aiSpeechParserManager);
    }

    @Override
    public void queryTelephoneContact(String terminalId, String contact) {
        QueryContactPresenter queryContactPresenter = new QueryContactPresenter(mContext, new QueryContactView() {
            @Override
            public void onSuccess(QueryContactInfo info) {
                String phoneNum = Util.getBindNameByContact(info.getResponseDataObj().getContacts(), contact);
                if (!TextUtils.isEmpty(phoneNum)) {
                    deviceCallBack.onQueryContactSuccess(phoneNum);
                } else {
                    deviceCallBack.onQueryContactError("");
                }
            }

            @Override
            public void onError(String result) {
                deviceCallBack.onQueryContactError(result);
            }
        });
        queryContactPresenter.requestData(terminalId);
    }

    @Override
    public void queryImChatRelation(String terminalId, String whoName, boolean isVideoChat) {
        QueryRelationPresenter queryRelationPresenter = new QueryRelationPresenter(mContext, new QueryRelationView() {
            @Override
            public void onSuccess(QueryRelationInfo info) {
                List<QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean> customerInfos = info.getResponseDataObj().getRelation().getCustomerInfos();
                String sourceChannelid = Util.getChannelidByRelation(customerInfos, whoName);
                if (!TextUtils.isEmpty(sourceChannelid)) {
                    if (isVideoChat) {
                        deviceCallBack.enterVideoChat(sourceChannelid, whoName);
                    } else {
                        RelationInfo relationInfo = Util.getRelationByBindName(customerInfos, whoName);
                        deviceCallBack.enterImChat(relationInfo);
                    }
                } else {
                    deviceCallBack.onQueryRelationError("");
                }
            }

            @Override
            public void onError(String result) {
                deviceCallBack.onQueryRelationError(result);
            }
        });
        queryRelationPresenter.requestData(terminalId);
    }
}
