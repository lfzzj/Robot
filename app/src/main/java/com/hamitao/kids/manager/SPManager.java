package com.hamitao.kids.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.SPUtil;
import com.hamitao.kids.model.PlayInfo;
import com.hamitao.requestframe.entity.QueryTimerAlarmInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @data on 2018/4/10 17:11
 * @describe:
 */

public class SPManager {

    private Context mContext;
    private SPUtil sp;


    private static SPManager INSTANCE = null;

    private SPManager(){}

    public static SPManager getInstance(){
        if (null == INSTANCE) {
            INSTANCE = new SPManager();
        }
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context;
        sp = new SPUtil(mContext, BaseConstant.SP_CHILD_DEVICE);
    }

    public boolean isFristBoot() {
        return sp.getBoolean(BaseConstant.SP_IS_FRIST_BOOT, true);
    }

    public void putFristBoot() {
        sp.putBoolean(BaseConstant.SP_IS_FRIST_BOOT, false);
    }

    /**
     * jm是否注册过
     */
    public boolean isJmRegister() {
        return sp.getBoolean(BaseConstant.SP_IS_JM_REGISTER, false);
    }

    public void putJmRegister() {
        sp.putBoolean(BaseConstant.SP_IS_JM_REGISTER, true);
    }

    /**
     * 护眼模式是否开启
     *
     * @return
     */
    public boolean isOpenEyeProtect() {
        return sp.getBoolean(BaseConstant.SP_OPEN_EYE_PROTECT, false);
    }

    public void putOpenEyeProtect(boolean isOpen) {
        sp.putBoolean(BaseConstant.SP_OPEN_EYE_PROTECT, isOpen);
    }

    /**
     * 电话接听管理
     *
     * @return
     */
    public boolean isPhoneManager() {
        return sp.getBoolean(BaseConstant.SP_PHONE_MANAGER, false);
    }

    public void putPhoneManager(boolean isOpen) {
        sp.putBoolean(BaseConstant.SP_PHONE_MANAGER, isOpen);
    }

    //获取自动日期和时间
    public void putAutoDataTime(boolean switchAuto) {
        sp.putBoolean(BaseConstant.SP_AUTO_DATA_TIME, switchAuto);
    }

    public boolean getAutoDataTime() {
        return sp.getBoolean(BaseConstant.SP_AUTO_DATA_TIME);
    }


    /**
     * 保存TerminalId
     */
    public String getTerminalId() {
        return sp.getString(BaseConstant.SP_TERMINAL_ID);
    }

    public void putTerminalId(String terminaid) {
        sp.putString(BaseConstant.SP_TERMINAL_ID, terminaid);
    }

    /**
     * 保存channelid
     */
    public String getChannelId() {
        return sp.getString(BaseConstant.SP_CHANNEL_ID);
    }

    public void putChannelId(String channelid) {
        sp.putString(BaseConstant.SP_CHANNEL_ID, channelid);
    }


    /**
     * 保存闹钟设置
     */
    public void putAlarmClockInfo(List<QueryTimerAlarmInfo.ResponseDataObjBean.TimerAlarmsBean> timerAlarmsBeans) {
        setDataList(BaseConstant.SP_ALARM_CLOCK, timerAlarmsBeans);
    }

    /**
     * 获取闹钟设置
     *
     * @return
     */
    public List<Map<String, String>> getAlarmClockInfo() {
        return getDataList(BaseConstant.SP_ALARM_CLOCK);
    }

    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0) {
            sp.putString(tag, "");
            return;
        }
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        sp.putString(tag, strJson);
    }

    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public <T> List<T> getDataList(String tag) {
        List<T> datalist = new ArrayList<T>();
        String strJson = sp.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;
    }

    //获取电量
    public void putPower(String power) {
        sp.putString(BaseConstant.SP_POWER, power);
    }

    public String getPower() {
        return sp.getString(BaseConstant.SP_POWER);
    }

    //是否正在充电
    public void putIsCharging(boolean result) {
        sp.putBoolean(BaseConstant.SP_IS_CHARGING, result);
    }

    public boolean getIsCharging() {
        return sp.getBoolean(BaseConstant.SP_IS_CHARGING);
    }


    //要说话的对象
    public void putBindName(String objectName) {
        sp.putString(BaseConstant.SP_BIND_NAME, objectName);
    }

    public String getBindName() {
        return sp.getString(BaseConstant.SP_BIND_NAME);
    }

    //课程表的id
    public String getCourseScheduleId() {
        return sp.getString(BaseConstant.SP_COURSE_SCHEDULE);
    }

    public void putCourseScheduleId(String objectName) {
        sp.putString(BaseConstant.SP_COURSE_SCHEDULE, objectName);
    }

    public void putImNickName(String nickName) {
        sp.putString(BaseConstant.SP_IM_NICKNAME, nickName);
    }

    public String getImNickName() {
        return sp.getString(BaseConstant.SP_IM_NICKNAME);
    }

    public void putPlayInfos(PlayInfo playInfo) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(playInfo); //将对象转换成Json
        sp.putString(BaseConstant.SP_PLAY_INFOS, jsonStr);
    }

    public String getPlayInfosStr() {
        return sp.getString(BaseConstant.SP_PLAY_INFOS, "");
    }

    public PlayInfo getPlayInfos(String peopleJson) {
        if (peopleJson != "") {
        }
        Gson gson = new Gson();
        return gson.fromJson(peopleJson, PlayInfo.class);
    }


    /**
     * 设置wifi开关
     *
     * @param isOpen
     * @return
     */
    public void setWifiState(boolean isOpen) {
        sp.putBoolean(BaseConstant.SP_WIFI_SWITCH, isOpen);
    }

    public boolean getWifiState() {
        return sp.getBoolean(BaseConstant.SP_WIFI_SWITCH, false);
    }


    public void saveMobieState(boolean mobileEnable){
        sp.putBoolean(BaseConstant.SP_MOBIE_STATE,mobileEnable);
    }

    public boolean getMobieState(){
       return sp.getBoolean(BaseConstant.SP_MOBIE_STATE);
    }

    /**
     * 设置mobie开关
     */
    public void setMobieSwitch(boolean isOpen) {
        sp.putBoolean(BaseConstant.SP_MOBIE_SWITCH, isOpen);
    }

    public boolean getMobieSwitch() {
        return sp.getBoolean(BaseConstant.SP_MOBIE_SWITCH,false);
    }

}
