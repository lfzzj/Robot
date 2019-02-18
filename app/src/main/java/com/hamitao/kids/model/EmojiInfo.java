package com.hamitao.kids.model;

/**
 * @data on 2018/4/18 14:08
 * @describe: 提示（动画+声音）
 */

public class EmojiInfo {
    private int emojiID;//动画资源

    private int VoiceID;//声音资源

    private String desc;//描述

    public EmojiInfo(int emojiID, int voiceID, String desc) {
        this.emojiID = emojiID;
        VoiceID = voiceID;
        this.desc = desc;
    }

    public void setEmojiID(int emojiID) {
        this.emojiID = emojiID;
    }

    public void setVoiceID(int voiceID) {
        VoiceID = voiceID;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getEmojiID() {

        return emojiID;
    }

    public int getVoiceID() {
        return VoiceID;
    }

    public String getDesc() {
        return desc;
    }
}
