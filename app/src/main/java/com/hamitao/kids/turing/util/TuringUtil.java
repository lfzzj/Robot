package com.hamitao.kids.turing.util;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.model.MediaInfo;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.turing.callback.TuringParserCallBack;
import com.hamitao.kids.turing.constant.TuringConstant;
import com.hamitao.kids.turing.model.AlarmBean;
import com.hamitao.kids.turing.model.TuringParseBean;

import java.util.ArrayList;
import java.util.List;

public class TuringUtil {
    private static final String TAG = "TuringUtil";
    private static TuringParserCallBack mCallBack;

    public static void parseTuringData(String result, TuringParserCallBack callBack) {
        mCallBack = callBack;
        Gson gson = new Gson();
        TuringParseBean turingParseBean = gson.fromJson(result, TuringParseBean.class);
        TuringParseBean.BehaviorsBean behaviorsBean = turingParseBean.getBehaviors().get(0);
        int code = behaviorsBean.getIntent().getCode();
        String values = behaviorsBean.getResults().get(0).getValues().getText();
        TuringParseBean.BehaviorsBean.IntentBean.ParametersBean parametersBean = behaviorsBean.getIntent().getParameters();
        Logger.d(TAG, "values=" + values);
        switch (code) {
            case TuringConstant.CODE_OS_SYS_SONG://歌曲点播
                Logger.d(TAG, "歌曲点播");
                String url = parametersBean.getUrl();
                String name = parametersBean.getName();
                Logger.d(TAG, "url=" + url);
                playUrl(values, url, name);
                break;
            case TuringConstant.CODE_OS_SYS_PHONE://打电话
                Logger.d(TAG, "打电话");
                String peopleName = parametersBean.getPeople_name();
                Logger.d(TAG, "peopleName=" + peopleName);
                callPhone(values, peopleName);
                break;
            case TuringConstant.CODE_OS_SYS_ANIMALSOUNDS://动物叫声
                Logger.d(TAG, "动物叫声");
                String animalUrl = parametersBean.getResources().getUrl();
                String animalName = parametersBean.getName();
                Logger.d(TAG, "animalUrl=" + animalUrl);
                playUrl(values, animalUrl, animalName);
                break;
            case TuringConstant.CODE_OS_SYS_NATURESOUNDS://大自然声音
                Logger.d(TAG, "大自然声音");
                String natureUrl = parametersBean.getResources().getUrl();
                String natureName = parametersBean.getName();
                Logger.d(TAG, "natureUrl=" + natureUrl);
                playUrl(values, natureUrl, natureName);
                break;
            case TuringConstant.CODE_OS_SYS_MUSICINSTRUMENTSOUNDS://乐器声音
                Logger.d(TAG, "乐器声音");
                String musicInstrumentUrl = parametersBean.getResources().getUrl();
                String musicInstrumentName = parametersBean.getName();
                Logger.d(TAG, "musicInstrumentUrl=" + musicInstrumentUrl);
                playUrl(values, musicInstrumentUrl, musicInstrumentName);
                break;
            case TuringConstant.CODE_OS_SYS_SETTING://设置
                Logger.d(TAG, "设置");
                int operateState = behaviorsBean.getIntent().getOperateState();
                setState(values, operateState);
                break;
            case TuringConstant.CODE_OS_SYS_EXIT://休眠
                Logger.d(TAG, "休眠");
                exit();
                break;
            case TuringConstant.CODE_OS_SYS_MEMO://闹钟
                Logger.d(TAG, "闹钟");
                //闹钟类型（0-->单次闹钟  1-->每天循环   2-->星期循环）
                int cycleType = parametersBean.getCycleType();
                String memoContent = parametersBean.getMemoContent();
                String startData = parametersBean.getStartDate();
                String time = parametersBean.getTime();
                time = time.substring(0, time.lastIndexOf(":"));
                setAlarm(values, new AlarmBean(cycleType, memoContent, startData, time));
                break;
            case TuringConstant.CODE_OS_SYS_ACTION://运动控制
                Logger.d(TAG, "运动控制");
                unknownContent();
                break;
            case TuringConstant.CODE_OS_SYS_PHOTOGRAPH://拍照
                Logger.d(TAG, "拍照");
                unknownContent();
                break;
            case TuringConstant.CODE_OS_SYS_ASK://十万个为什么
                Logger.d(TAG, "十万个为什么");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_STORY://故事点播
                Logger.d(TAG, "故事点播");
                String storyUrl = behaviorsBean.getIntent().getParameters().getUrl();
                String storyName = behaviorsBean.getIntent().getParameters().getName();
                Logger.d(TAG, "storyUrl=" + storyUrl);
                playUrl(values, storyUrl, storyName);
                break;
            case TuringConstant.CODE_OS_SYS_WIKI://维基百科
                Logger.d(TAG, "维基百科");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_POEM://诗歌背诵
                Logger.d(TAG, "诗歌背诵");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_TRANSLATE://中英互译
                Logger.d(TAG, "中英互译");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_CALCULATE://四则运算
                Logger.d(TAG, "四则运算");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_ENGLISHCHAT://英文对话
                Logger.d(TAG, "英文对话");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_WEATHER://天气查询
                Logger.d(TAG, "天气查询");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_DATE://时间日期查询
                Logger.d(TAG, "时间日期查询");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_WHOVOICE://谁在叫
                Logger.d(TAG, "谁在叫");
                break;
            case TuringConstant.CODE_OS_SYS_JOKE://讲笑话
                Logger.d(TAG, "讲笑话");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_DOGGEREL://顺口溜
                Logger.d(TAG, "顺口溜");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_TONGUETWISTER://绕口令
                Logger.d(TAG, "绕口令");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_BRAINTWISTER://脑筋急转弯
                Logger.d(TAG, "脑筋急转弯");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_APP://app
                boolean isOpen = behaviorsBean.getIntent().getParameters().isIs_open();
                if (isOpen) {
                    String appName = behaviorsBean.getIntent().getParameters().getApp_name();
                    if (!TextUtils.isEmpty(appName)) {
                        startApp(values, appName);
                        return;
                    }
                }
                unknownContent();
                break;
            case TuringConstant.CODE_OS_SYS_CHAT://闲聊
                Logger.d(TAG, "闲聊");
                readValue(values);
                break;
            case TuringConstant.CODE_OS_SYS_SMARTFAQ:
                readValue(values);
                break;
        }
    }

    /**
     * 设置闹钟
     *
     * @param values
     * @param alarmBean
     */
    private static void setAlarm(String values, AlarmBean alarmBean) {
        if (mCallBack != null) {
            mCallBack.setAlarm(values, alarmBean);
        }
    }

    /**
     * 直接播放结果
     */
    public static void readValue(String values) {
        if (mCallBack != null) {
            mCallBack.readValue(values);
        }
    }

    /**
     * 播放完结果之后播放音频
     *
     * @param value
     * @param url
     */
    public static void playUrl(String value, String url, String title) {
        if (mCallBack != null) {
            mCallBack.playUrl(value, url, title);
        }
    }

    /**
     * 打电话
     *
     * @param value
     * @param name
     */
    public static void callPhone(String value, String name) {
        if (mCallBack != null) {
            mCallBack.callPhone(value, name);
        }
    }

    /**
     * 设置
     *
     * @param values
     * @param operateState
     */
    private static void setState(String values, int operateState) {
        if (mCallBack != null) {
            mCallBack.setState(values, operateState);
        }
    }

    /**
     * 启动
     *
     * @param values
     * @param appName
     */
    private static void startApp(String values, String appName) {
        if (mCallBack != null) {
            mCallBack.startApp(values, appName);
        }
    }

    /**
     * 退出
     */
    public static void exit() {
        if (mCallBack != null) {
            mCallBack.exit();
        }
    }

    /**
     * 未知领域，暂未开发
     */
    public static void unknownContent() {
        if (mCallBack != null) {
            mCallBack.unknownContent();
        }
    }

    /**
     * 根据URL生成一个mediaInfo
     *
     * @param url
     * @return
     */
    public static List<MediaInfo> getMeidaDataByUrl(String url, String title) {
        List<MediaInfo> mediaInfos = new ArrayList<>();
        mediaInfos.add(new MediaInfo(url, BaseConstant.TYPE_MUSIC, title));
        return mediaInfos;
    }
}
