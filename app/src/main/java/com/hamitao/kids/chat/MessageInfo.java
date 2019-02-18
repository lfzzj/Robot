package com.hamitao.kids.chat;

import java.util.HashMap;
import java.util.UUID;

import cn.jiguang.imui.commons.models.IMessage;
import cn.jiguang.imui.commons.models.IUser;
import cn.jpush.im.android.api.model.Message;

/**
 * @data on 2018/6/9 16:27
 * @describe:
 */

public class MessageInfo implements IMessage {

    private long id;
    private String text;
    private String timeString;
    private int type;
    private IUser user;
    private String mediaFilePath;
    private long duration;
    private String progress;
    private Message message;
    private MessageStatus mMsgStatus = MessageStatus.CREATED;

    public MessageInfo(String text, int type) {
        this.text = text;
        this.type = type;
        this.id = UUID.randomUUID().getLeastSignificantBits();
    }


    public MessageInfo(String text, int type, Message message) {
        this.text = text;
        this.type = type;
        this.message = message;
        this.id = UUID.randomUUID().getLeastSignificantBits();
    }


    @Override
    public String getMsgId() {
        return String.valueOf(id);
    }

    public long getId() {
        return this.id;
    }

    @Override
    public IUser getFromUser() {
        if (user == null) {
            return new DefaultUser("0", "user1", null);
        }
        return user;
    }

    public void setUserInfo(IUser user) {
        this.user = user;
    }

    public void setMediaFilePath(String path) {
        this.mediaFilePath = path;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {

        return message;
    }

    @Override
    public long getDuration() {
        return duration;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public String getProgress() {
        return progress;
    }

    @Override
    public HashMap<String, String> getExtras() {
        return null;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    @Override
    public String getTimeString() {
        return timeString;
    }

    public void setType(int type) {
        if (type >= 0 && type <= 12) {
            throw new IllegalArgumentException("Message type should not take the value between 0 and 12");
        }
        this.type = type;
    }

    @Override
    public int getType() {
        return type;
    }

    /**
     * Set Message status. After sending Message, change the status so that the progress bar will dismiss.
     *
     * @param messageStatus
     */
    public void setMessageStatus(MessageStatus messageStatus) {
        this.mMsgStatus = messageStatus;
    }

    @Override
    public MessageStatus getMessageStatus() {
        return this.mMsgStatus;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getMediaFilePath() {
        return mediaFilePath;
    }
}
