package com.hamitao.kids.model;

/**
 * @data on 2018/4/17 17:06
 * @describe:
 */

public class StudyContentInfo {
    private String chineseContent;
    private String englishContent;

    public StudyContentInfo(String chineseContent, String englishContent) {
        this.chineseContent = chineseContent;
        this.englishContent = englishContent;
    }

    public void setChineseContent(String chineseContent) {
        this.chineseContent = chineseContent;
    }

    public void setEnglishContent(String englishContent) {
        this.englishContent = englishContent;
    }

    public String getChineseContent() {

        return chineseContent;
    }

    public String getEnglishContent() {
        return englishContent;
    }
}
