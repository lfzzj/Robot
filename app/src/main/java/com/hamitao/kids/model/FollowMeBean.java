package com.hamitao.kids.model;

import java.io.Serializable;

/**
 * @data on 2018/5/29 9:46
 * @describe: 功能
 */

public class FollowMeBean implements Serializable {
    private String chineseName;
    private String englishName;
    private int icon;//icon

    public FollowMeBean(String chineseName, String englishName, int icon) {
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.icon = icon;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
