package com.hamitao.kids.model;

import java.io.Serializable;

/**
 * 关系列表
 */
public class RelationInfo implements Serializable {
    private String nickName;
    private String loginName;
    private boolean isSinger;
    private int msgNum;
    private String sourceChannelid;

    public RelationInfo(String nickName, String loginName, boolean isSinger, int msgNum, String sourceChannelid) {
        this.nickName = nickName;
        this.loginName = loginName;
        this.isSinger = isSinger;
        this.msgNum = msgNum;
        this.sourceChannelid = sourceChannelid;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setSinger(boolean singer) {
        isSinger = singer;
    }

    public void setMsgNum(int msgNum) {
        this.msgNum = msgNum;
    }

    public void setSourceChannelid(String sourceChannelid) {
        this.sourceChannelid = sourceChannelid;
    }

    public String getNickName() {

        return nickName;
    }

    public String getLoginName() {
        return loginName;
    }

    public boolean isSinger() {
        return isSinger;
    }

    public int getMsgNum() {
        return msgNum;
    }

    public String getSourceChannelid() {
        return sourceChannelid;
    }
}
