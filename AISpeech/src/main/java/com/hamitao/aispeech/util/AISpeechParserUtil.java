package com.hamitao.aispeech.util;

import android.text.TextUtils;

import com.aispeech.common.JSONResultParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hamitao.aispeech.model.AISpeechAlarmData;
import com.hamitao.aispeech.model.AISpeechNetfmData;
import com.hamitao.aispeech.model.AISpeechNewsData;
import com.hamitao.aispeech.model.AISpeechParserDbData;
import com.hamitao.aispeech.model.AlarmInfo;
import com.hamitao.aispeech.model.TranslationInfo;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.model.MediaInfo;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @data on 2018/6/21 14:09
 * @describe: 思必驰解析工具类
 */

public class AISpeechParserUtil {
    private static String TAG = "AISpeechParserUtil";
    public static final String FLAG_PARSER_SDS = "sds";
    public static final String FLAG_PARSER_DOMAIN = "domain";
    public static final String FLAG_PARSER_DST = "dst";
    public static final String FLAG_PARSER_REQUEST = "request";
    public static final String FLAG_PARSER_PARAM = "param";
    public static final String FLAG_PARSER_SEMANTICS = "semantics";
    public static final String FLAG_PARSER_OUTPUT = "output";
    public static final String FLAG_PARSER_DATA = "data";
    public static final String FLAG_PARSER_DBDATA = "dbdata";
    public static final String FLAG_PARSER_NLU = "nlu";
    public static final String FLAG_PARSER_NLU_OBJECT = "object";
    public static final String FLAG_PARSER_NLU_OPERATION = "operation";
    public static final String FLAG_PARSER_NLU_BRIGHTNESS = "brightness";
    public static final String FLAG_PARSER_CONTEXTID = "contextId";


    public static final String FLAG_PARSER_EXIT = "退出";
    public static final String FLAG_PARSER_TURN_OFF = "关机";

    public static final String FLAG_PARSER_CONTACT = "联系人";


    public static final String DOMAIN_MUSIC = "music";
    public static final String DOMAIN_CHAT = "chat";
    public static final String DOMAIN_WEATHER = "weather";
    public static final String DOMAIN_CALENDAR = "calendar";
    public static final String DOMAIN_COMMAND = "command";
    public static final String DOMAIN_NETFM = "netfm";
    public static final String DOMAIN_REMINDER = "reminder";
    public static final String DOMAIN_STOCK = "stock";
    public static final String DOMAIN_POETRY = "poetry";
    public static final String DOMAIN_TRANSLATION = "translation";
    public static final String DOMAIN_CALCULATOR = "calculator";//计算器
    public static final String DOMAIN_NEWS = "news";//新闻

    public static final String DOMAIN_REQUEST_DOMAIN_CALL = "打电话";//打电话
    public static final String DOMAIN_REQUEST_DOMAIN_FILM = "影视";//打电话


    public static JSONResultParser getJSONResultParser(String result) {
        return new JSONResultParser(result);
    }

    //根据对于的值来获取数据
    public static String getParserValue(JSONResultParser result, String flag) {
        try {
            return result.getResult().get(flag).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getJSONValue(JSONResultParser parser, String flag) {
        try {
            if (parser.toString().contains(flag)) {
                return parser.getJSON().get(flag).toString();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 思必驰语义解析数据
     *
     * @param content
     */
    public static void getAiSpeechSemanticParserData(String content, AISpeechParserManager manager) {
        JSONResultParser parser = getJSONResultParser(content);
        if (parser != null) {
            JSONObject result = parser.getResult();
            try {
                String sds = result.get(FLAG_PARSER_SDS).toString();
                JSONResultParser sdsParser = getJSONResultParser(sds);
                String domain = getJSONValue(sdsParser, FLAG_PARSER_DOMAIN);
                String contextId = getJSONValue(sdsParser, FLAG_PARSER_CONTEXTID);
                manager.passDorenDialog(domain, contextId);

                if (!TextUtils.isEmpty(domain)) {
                    if (DOMAIN_MUSIC.equals(domain)) {//音乐
                        Logger.d(TAG, "音乐");
                        String data = getJSONValue(sdsParser, FLAG_PARSER_DATA);
                        String output = getJSONValue(sdsParser, FLAG_PARSER_OUTPUT);
                        manager.parserMusic(output, data);
                    } else if (DOMAIN_CHAT.equals(domain)) {//闲聊
                        Logger.d(TAG, "闲聊");
                        String output = getJSONValue(sdsParser, FLAG_PARSER_OUTPUT);
                        manager.parserChat(output);
                    } else if (DOMAIN_WEATHER.equals(domain)) {//天气
                        Logger.d(TAG, "天气");
                        String output = getJSONValue(sdsParser, FLAG_PARSER_OUTPUT);
                        manager.parserWeather(output);
                    } else if (DOMAIN_CALENDAR.equals(domain)) {//日历
                        Logger.d(TAG, "日历");
                        String output = getJSONValue(sdsParser, FLAG_PARSER_OUTPUT);
                        manager.parserCalendar(output);
                    } else if (DOMAIN_COMMAND.equals(domain)) {//控制类
                        Logger.d(TAG, "控制类");
                        String nlu = getJSONValue(sdsParser, FLAG_PARSER_NLU);
                        manager.parserCommand(nlu);
                    } else if (DOMAIN_NETFM.equals(domain)) {//电台
                        Logger.d(TAG, "电台");
                        String data = getJSONValue(sdsParser, FLAG_PARSER_DATA);
                        String output = getJSONValue(sdsParser, FLAG_PARSER_OUTPUT);
                        manager.parserNetfm(output, data);
                    } else if (DOMAIN_REMINDER.equals(domain)) {//提醒
                        Logger.d(TAG, "提醒");
                        String data = getJSONValue(sdsParser, FLAG_PARSER_DATA);
                        String output = getJSONValue(sdsParser, FLAG_PARSER_OUTPUT);
                        manager.parserReminder(output, data);
                    } else if (DOMAIN_STOCK.equals(domain)) {//沪深港股票查询
                        Logger.d(TAG, "沪深港股票查询");
                        String output = getJSONValue(sdsParser, FLAG_PARSER_OUTPUT);
                        manager.parserStock(output);
                    } else if (DOMAIN_POETRY.equals(domain)) {//古诗对答和全诗朗诵
                        Logger.d(TAG, "古诗对答和全诗朗诵");
                        String output = getJSONValue(sdsParser, FLAG_PARSER_OUTPUT);
                        manager.parserPoetry(output);
                    } else if (DOMAIN_TRANSLATION.equals(domain)) {
                        Logger.d(TAG, "中英互译");
                        String output = getJSONValue(sdsParser, FLAG_PARSER_OUTPUT);
                        manager.parserTranslation(output);
                    } else if (DOMAIN_CALCULATOR.equals(domain)) {
                        Logger.d(TAG, "计算器");
                        String output = getJSONValue(sdsParser, FLAG_PARSER_OUTPUT);
                        manager.parserCalculator(output);
                    } else if (DOMAIN_NEWS.equals(domain)) {
                        Logger.d(TAG, "新闻");
                        String data = getJSONValue(sdsParser, FLAG_PARSER_DATA);
                        String output = getJSONValue(sdsParser, FLAG_PARSER_OUTPUT);
                        manager.parserNews(output, data);
                    } else {
                        manager.parserDoMainIsNull();
                    }
                } else {
                    String semantics = result.get(FLAG_PARSER_SEMANTICS).toString();
                    if (TextUtils.isEmpty(semantics)) {
                        manager.parserDoMainIsNull();
                        return;
                    }
                    JSONResultParser semanticsParser = getJSONResultParser(semantics);
                    String request = getJSONValue(semanticsParser, FLAG_PARSER_REQUEST);
                    JSONResultParser parserRequest = getJSONResultParser(request);
                    String domainRequest = getJSONValue(parserRequest, FLAG_PARSER_DOMAIN);
                    if (DOMAIN_REQUEST_DOMAIN_CALL.equals(domainRequest)) {
                        Logger.d(TAG, "打电话");
                        String param = getJSONValue(parserRequest, FLAG_PARSER_PARAM);
                        JSONResultParser parserParam = getJSONResultParser(param);
                        String contact = getJSONValue(parserParam, FLAG_PARSER_CONTACT);
                        if (!TextUtils.isEmpty(contact)) {
                            manager.parserMakeCall(contact);
                            Logger.d(TAG, "电话号码：" + contact);
                        }
                    } else if (DOMAIN_REQUEST_DOMAIN_FILM.equals(domainRequest)) {//影视
                        manager.parserFilmTel();
                    } else {
                        manager.parserDoMainIsNull();
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 获取翻译数据
     *
     * @param content
     */
    public static String getTranslationParserData(String content) {
        JSONResultParser parser = getJSONResultParser(content);
        if (parser != null) {
            JSONObject result = parser.getJSON();
            try {
                String domain = result.get(FLAG_PARSER_DOMAIN).toString();
                if (DOMAIN_TRANSLATION.equals(domain)) {
                    String data = result.get(FLAG_PARSER_DATA).toString();
                    JSONResultParser parserData = getJSONResultParser(data);
                    String dbdata = getJSONValue(parserData, FLAG_PARSER_DBDATA);
                    if (!TextUtils.isEmpty(dbdata) && !dbdata.equals("{}")) {
                        Gson gson = new Gson();
                        List<TranslationInfo> translationInfo = gson.fromJson(dbdata, new TypeToken<List<TranslationInfo>>() {
                        }.getType());
                        return translationInfo.get(0).getDst();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    //提取出解析的数据
    public static List<MediaInfo> getParserDbdata(String data) {
        List<AISpeechParserDbData.DbdataBean> dbdataBeans = new ArrayList<>();
        List<MediaInfo> mediaInfos = new ArrayList<>();

        if (!TextUtils.isEmpty(data)) {
            JSONResultParser parser = getJSONResultParser(data);
            if (parser != null) {
                String dbdata = getJSONValue(parser, FLAG_PARSER_DBDATA);
                Logger.d(TAG, "dbdata==" + dbdata);
                if (!TextUtils.isEmpty(dbdata)) {
                    dbdataBeans = getListByGson(dbdata);
                }
                if (dbdataBeans != null && dbdataBeans.size() != 0) {
                    for (int i = 0; i < dbdataBeans.size(); i++) {
                        AISpeechParserDbData.DbdataBean  dbdataBean = dbdataBeans.get(i);
                        mediaInfos.add(new MediaInfo(dbdataBean.getUrl(), BaseConstant.TYPE_MUSIC,
                                StringUtil.cheakTitle(dbdataBean.getTitle())));
                    }
                }
            }
        }
        return mediaInfos;
    }

    /**
     * 提取电台返回的数据
     *
     * @return
     */
    public static List<MediaInfo> getParserNetfmData(String data) {
        List<AISpeechNetfmData.DbdataBean> dbdataBeans = new ArrayList<>();
        List<MediaInfo> mediaInfos = new ArrayList<>();
        if (!TextUtils.isEmpty(data)) {
            JSONResultParser parser = getJSONResultParser(data);
            if (parser != null) {
                String dbdata = getJSONValue(parser, FLAG_PARSER_DBDATA);
                Logger.d(TAG, "dbdata==" + dbdata);
                if (!TextUtils.isEmpty(dbdata)) {
                    dbdataBeans = getNetfmByGson(dbdata);
                }
                if (dbdataBeans != null && dbdataBeans.size() != 0) {
                    for (int i = 0; i < dbdataBeans.size(); i++) {
                        AISpeechNetfmData.DbdataBean dbdataBean = dbdataBeans.get(i);
                        mediaInfos.add(new MediaInfo(dbdataBean.getPlayUrl64(), BaseConstant.TYPE_MUSIC,
                                StringUtil.cheakTitle(dbdataBean.getTrack())));
                    }
                }
            }
        }
        return mediaInfos;
    }

    /**
     * 获取新闻的 数据
     *
     * @param data
     * @return
     */
    public static List<MediaInfo> getParserNewsData(String data) {
        List<AISpeechNewsData.DbdataBean> dbdataBeans = new ArrayList<>();
        List<MediaInfo> mediaInfos = new ArrayList<>();
        if (!TextUtils.isEmpty(data)) {
            JSONResultParser parser = getJSONResultParser(data);
            if (parser != null) {
                String dbdata = getJSONValue(parser, FLAG_PARSER_DBDATA);
                Logger.d(TAG, "dbdata==" + dbdata);
                if (!TextUtils.isEmpty(dbdata)) {
                    dbdataBeans = getNewsByGson(dbdata);
                }
                if (dbdataBeans != null && dbdataBeans.size() != 0) {
                    for (int i = 0; i < dbdataBeans.size(); i++) {
                        AISpeechNewsData.DbdataBean dbdataBean = dbdataBeans.get(i);
                        mediaInfos.add(new MediaInfo(dbdataBean.getMp3PlayUrl64(), BaseConstant.TYPE_MUSIC,
                                StringUtil.cheakTitle(dbdataBean.getAudioName())));
                    }
                }
            }
        }
        return mediaInfos;
    }

    public static List<AISpeechParserDbData.DbdataBean> getListByGson(String data) {
        if (!TextUtils.isEmpty(data) && !data.equals("{}")) {
            Gson gson = new Gson();
            List<AISpeechParserDbData.DbdataBean> DbdataBeans = gson.fromJson(data, new TypeToken<List<AISpeechParserDbData.DbdataBean>>() {
            }.getType());
            return DbdataBeans;
        }
        return null;
    }


    public static List<AISpeechNetfmData.DbdataBean> getNetfmByGson(String data) {
        if (!TextUtils.isEmpty(data) && !data.equals("{}")) {
            Gson gson = new Gson();
            List<AISpeechNetfmData.DbdataBean> DbdataBeans = gson.fromJson(data, new TypeToken<List<AISpeechNetfmData.DbdataBean>>() {
            }.getType());
            return DbdataBeans;
        }
        return null;
    }

    public static List<AISpeechNewsData.DbdataBean> getNewsByGson(String data) {
        if (!TextUtils.isEmpty(data) && !data.equals("{}")) {
            Gson gson = new Gson();
            List<AISpeechNewsData.DbdataBean> DbdataBeans = gson.fromJson(data, new TypeToken<List<AISpeechNewsData.DbdataBean>>() {
            }.getType());
            return DbdataBeans;
        }
        return null;
    }

    public static List<AISpeechAlarmData.DbdataBean> getAlarmByGson(String data) {
        if (!TextUtils.isEmpty(data) && !data.equals("{}")) {
            Gson gson = new Gson();
            List<AISpeechAlarmData.DbdataBean> DbdataBeans = gson.fromJson(data, new TypeToken<List<AISpeechAlarmData.DbdataBean>>() {
            }.getType());
            return DbdataBeans;
        }
        return null;
    }

    /**
     * 获取提醒数据(添加到服务器数据)
     *
     * @param data
     */
    public static AlarmInfo getParserReminderDbdata(String data) {
        AlarmInfo alarmInfo = null;
        List<AISpeechAlarmData.DbdataBean> dbdataBeans = new ArrayList<>();
        if (!TextUtils.isEmpty(data)) {
            JSONResultParser parser = getJSONResultParser(data);
            if (parser != null) {
                String dbdata = getJSONValue(parser, FLAG_PARSER_DBDATA);
                Logger.d(TAG, "dbdata==" + dbdata);
                if (!TextUtils.isEmpty(dbdata)) {
                    dbdataBeans = getAlarmByGson(dbdata);
                }
                if (dbdataBeans != null && dbdataBeans.size() != 0) {
                    AISpeechAlarmData.DbdataBean dbdataBean = dbdataBeans.get(0);
                    //timerId服务器会自动生成
                    alarmInfo = new AlarmInfo(dbdataBean.getTime(), dbdataBean.getDate(), dbdataBean.getEvent(),
                            dbdataBean.getRepeat(), "", BaseConstant.ENABLE);
                }
            }
        }
        return alarmInfo;
    }

}
