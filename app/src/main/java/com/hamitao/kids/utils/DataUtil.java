package com.hamitao.kids.utils;

import com.hamitao.aispeech.model.AlarmInfo;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.manager.DeviceManager;
import com.hamitao.kids.manager.SPManager;
import com.hamitao.kids.model.RelationInfo;
import com.hamitao.kids.turing.model.AlarmBean;
import com.hamitao.requestframe.entity.QueryRelationInfo;
import com.hamitao.requestframe.entity.RequestAddTimerAlarm;
import com.hamitao.requestframe.entity.UpdateTimerAlarmStatusInfo;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;

public class DataUtil {

    /**
     * 获取单聊关系列表
     */
    public static List<RelationInfo> getSingerChatList(List<QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean> customerInfos) {
        List<RelationInfo> singerInfos = new ArrayList<>();
        for (int i = 0; i < customerInfos.size(); i++) {
            QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean customerInfosBean = customerInfos.get(i);
            singerInfos.add(new RelationInfo(customerInfosBean.getBindname(), customerInfosBean.getLoginname(), true, 0, customerInfosBean.getChannelid_listen_on_pushserver()));
        }
        return singerInfos;
    }

    /**
     * 获取群聊关系列表
     *
     * @param conversations
     */
    public static List<RelationInfo> getIMChatList(List<Conversation> conversations) {
        List<RelationInfo> groupInfos = new ArrayList<>();
        UserInfo userInfo = JMessageClient.getMyInfo(); //自己的个人信息

        for (int i = 0; i < conversations.size(); i++) {
            Conversation conversation = conversations.get(i);
            String targetId = conversation.getTargetId().toString();
            int UnReadMsgCnt = conversation.getUnReadMsgCnt();//未读消息数
            String title = conversation.getTitle();
            boolean isSinger = conversation.getType().equals(ConversationType.single);
            if (!isSinger) {
                GroupInfo groupInfo = (GroupInfo) conversation.getTargetInfo();
                //群聊

                //自己是否在群聊
                boolean isInGroup = false;
                for (int j = 0; j < groupInfo.getGroupMemberInfos().size(); j++) {
                    if (groupInfo.getGroupMemberInfos().get(j).getUserInfo().getUserID() == userInfo.getUserID()) {
                        isInGroup = true;
                        break;
                    }
                }

                if (!isInGroup) {
                    //如果自己不在群里，则删除会话
                    JMessageClient.deleteGroupConversation(groupInfo.getGroupID());
                } else {
                    //添加会话
                    groupInfos.add(new RelationInfo(title, targetId, isSinger, UnReadMsgCnt, ""));
                }
            } else {
                //添加会话
                groupInfos.add(new RelationInfo(title, targetId, isSinger, UnReadMsgCnt, ""));
            }


        }
        return groupInfos;
    }

    /**
     * 检测会话列表中是否存在
     *
     * @param conversations 极光数据
     * @param relationInfos 服务器数据
     */
    public static List<RelationInfo> CheckExit
    (List<Conversation> conversations, List<RelationInfo> relationInfos) {

        List<RelationInfo> relationInfosLast = new ArrayList<>();//最终列表

        List<RelationInfo> relationInfoIMs = getIMChatList(conversations);//将极光列表转化成关系列表

        List<String> userId = new ArrayList<>();

        for (int i = 0; i < relationInfos.size(); i++) {
            userId.add(relationInfos.get(i).getLoginName());
        }


        //极光会话
        for (int i = 0; i < relationInfoIMs.size(); i++) {
            RelationInfo relationInfoJm = relationInfoIMs.get(i);
            String loginNameJm = relationInfoJm.getLoginName();

            if (!userId.contains(loginNameJm)) {
                JMessageClient.deleteSingleConversation(relationInfoIMs.get(i).getLoginName());
            }

            if (relationInfoJm.isSinger()) {
                //遍历单聊会话数
                for (int j = 0; j < relationInfos.size(); j++) {
                    RelationInfo relationInfo = relationInfos.get(j);
                    String loginName = relationInfo.getLoginName();
                    //如果会话用户与绑定用户的一致
                    if (loginNameJm.equals(loginName)) {
                        relationInfo.setMsgNum(relationInfoJm.getMsgNum());
                        relationInfosLast.add(relationInfo);
                        relationInfos.remove(relationInfo);
                    }
                }
            } else {
                //群聊直接加入列表中
                relationInfosLast.add(relationInfoJm);
            }
        }
        relationInfosLast.addAll(relationInfos);
        return relationInfosLast;
    }


    /**
     * 获取两个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        List<String> diff = new ArrayList<String>();
        for (String str : list1) {
            if (!list2.contains(str)) {
                diff.add(str);
            }
        }
        System.out.println("getDiffrent total times " + (System.nanoTime() - st));
        return diff;
    }

    /**
     * 获取闹钟数据
     *
     * @return
     */
    public static RequestAddTimerAlarm getTimerAlarmData(DeviceManager deviceManager, SPManager spManager, AlarmInfo alarmInfo) {
        RequestAddTimerAlarm requestAddTimerAlarm = new RequestAddTimerAlarm();

        requestAddTimerAlarm.setCreator(deviceManager.getDeviceId());
        requestAddTimerAlarm.setName(alarmInfo.getEvent());
        requestAddTimerAlarm.setOwnerid(MyApp.getInstance().getSpManager().getTerminalId());
        requestAddTimerAlarm.setStatus("enable");
        List<RequestAddTimerAlarm.TimersBean> timers = new ArrayList<>();
        RequestAddTimerAlarm.TimersBean timersBean = new RequestAddTimerAlarm.TimersBean();
        timersBean.setDay(alarmInfo.getDate());
        timersBean.setStarttime(alarmInfo.getTime());
        timers.add(timersBean);
        requestAddTimerAlarm.setTimers(timers);
        requestAddTimerAlarm.setType(BaseConstant.devicedoit);
        return requestAddTimerAlarm;
    }

    public static RequestAddTimerAlarm getTimerAlarmData(String deviceId, String terminalId, AlarmBean alarmBean) {
        RequestAddTimerAlarm requestAddTimerAlarm = new RequestAddTimerAlarm();
        requestAddTimerAlarm.setCreator(deviceId);
        requestAddTimerAlarm.setName(alarmBean.getMemoContent());
        requestAddTimerAlarm.setOwnerid(terminalId);
        requestAddTimerAlarm.setStatus("enable");
        List<RequestAddTimerAlarm.TimersBean> timers = new ArrayList<>();
        RequestAddTimerAlarm.TimersBean timersBean = new RequestAddTimerAlarm.TimersBean();
        timersBean.setDay(alarmBean.getStartData());
        timersBean.setStarttime(alarmBean.getTime());
        timers.add(timersBean);
        requestAddTimerAlarm.setTimers(timers);
        requestAddTimerAlarm.setType(BaseConstant.devicedoit);
        return requestAddTimerAlarm;
    }

    /**
     * 获取闹钟对象
     *
     * @param timerid
     * @param isOpen
     * @param event
     * @param terminalId
     * @return
     */
    public static UpdateTimerAlarmStatusInfo getAlarmInfo(String timerid, boolean isOpen, String event, String terminalId) {
        UpdateTimerAlarmStatusInfo updateTimerAlarmStatusInfo = new UpdateTimerAlarmStatusInfo();
        updateTimerAlarmStatusInfo.setId(timerid);
        updateTimerAlarmStatusInfo.setStatus(isOpen ? BaseConstant.ENABLE : BaseConstant.DISABLE);
        updateTimerAlarmStatusInfo.setName(event);
        updateTimerAlarmStatusInfo.setOwnerid(terminalId);
        return updateTimerAlarmStatusInfo;
    }

}
