package com.hamitao.aispeech.util;

import com.hamitao.aispeech.view.AISpeechParserCallBack;

/**
 * @data on 2018/6/21 14:39
 * @describe: 思必驰解析管理
 */

public class AISpeechParserManager {
    private AISpeechParserCallBack callback;

    public AISpeechParserManager(AISpeechParserCallBack parserBack) {
        this.callback = parserBack;
    }

    //处理聊天
    public void parserChat(String output) {
        if (callback != null) {
            callback.onChatListener(output);
        }
    }

    //处理天气
    public void parserWeather(String output) {
        if (callback != null) {
            callback.onWeatherListener(output);
        }
    }

    //处理日历
    public void parserCalendar(String output) {
        if (callback != null) {
            callback.onCalendarListener(output);
        }
    }

    //处理沪深港股票查询
    public void parserStock(String output) {
        if (callback != null) {
            callback.onStockListener(output);
        }
    }

    //处理古诗
    public void parserPoetry(String output) {
        if (callback != null) {
            callback.onPoetryListener(output);
        }
    }

    //处理音乐
    public void parserMusic(String output, String data) {
        if (callback != null) {
            callback.onMusicListener(output, data);
        }
    }

    //处理电台
    public void parserNetfm(String output, String data) {
        if (callback != null) {
            callback.onNetfmListener(output, data);
        }
    }

    //控制类
    public void parserCommand(String nlu) {
        if (callback != null) {
            callback.onCommandListener(nlu);
        }
    }

    //中英互译
    public void parserTranslation(String output) {
        if (callback != null) {
            callback.onTranslationListener(output);
        }
    }

    //计算器
    public void parserCalculator(String output) {
        if (callback != null) {
            callback.onCalculatorListener(output);
        }
    }

    //打电话
    public void parserMakeCall(String contact) {
        if (callback!=null){
            callback.onMakeCallListener(contact);
        }
    }


    //影视
    public void parserFilmTel() {
        if (callback != null) {
            callback.onFilmTelListener();
        }
    }

    /**
     * 新闻
     * @param output
     * @param data
     */
    public void parserNews(String output, String data) {
        if (callback != null) {
            callback.onNewsListener(output,data);
        }
    }

    //domain为空的处理
    public void parserDoMainIsNull() {
        if (callback != null) {
            callback.onDoMainIsNullListener();
        }
    }

    //处理提醒
    public void parserReminder(String output, String data) {
        if (callback != null) {
            callback.onReminderListener(output,data);
        }
    }

    /**
     * 传递上轮对话的数据
     * @param domain
     * @param contextId
     */
    public void passDorenDialog(String domain, String contextId) {
        if (callback != null) {
            callback.onPassDorenDialog(domain,contextId);
        }
    }



}
