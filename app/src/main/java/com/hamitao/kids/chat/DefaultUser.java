package com.hamitao.kids.chat;

import cn.jiguang.imui.commons.models.IUser;

/**
 * @data on 2018/6/11 9:10
 * @describe:
 */

public class DefaultUser implements IUser {
    private String id;//用户名
    private String displayName;//昵称
    private String avatar;//头像

    public DefaultUser(String id, String displayName, String avatar) {
        this.id = id;
        this.displayName = displayName;
        this.avatar = avatar;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getAvatarFilePath() {
        return avatar;
    }
}
