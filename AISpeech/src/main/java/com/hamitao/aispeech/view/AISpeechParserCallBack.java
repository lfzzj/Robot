package com.hamitao.aispeech.view;

/**
 * @data on 2018/6/21 14:41
 * @describe: 思必驰解析回调
 */

public interface AISpeechParserCallBack {
    void onChatListener(String output);

    void onWeatherListener(String output);

    void onCalendarListener(String output);

    void onStockListener(String output);

    void onPoetryListener(String output);

    void onMusicListener(String output, String data);

    void onCommandListener(  String nlu);

    void onTranslationListener(String output);

    void onCalculatorListener(String output);

    void onDoMainIsNullListener();

    void onMakeCallListener(String contact);

    void onFilmTelListener();

    void onNetfmListener(String output, String data);

    void onReminderListener(String output, String data);

    void onPassDorenDialog(String domain, String contextId);

    void onNewsListener(String output, String data);
}
