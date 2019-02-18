package com.hamitao.kids.manager.wakeup.callback;


import com.hamitao.kids.model.RelationInfo;

public interface DeviceCallBack {
    void onQueryContactSuccess(String phoneNum);

    void onQueryContactError(String result);

    void enterVideoChat(String sourceChannelid, String whoName);

    void enterImChat(RelationInfo relationInfo);

    void onQueryRelationError(String s);
}
