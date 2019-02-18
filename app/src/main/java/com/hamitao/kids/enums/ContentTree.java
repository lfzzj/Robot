package com.hamitao.kids.enums;

/**
 * @data on 2018/6/29 15:58
 * @describe: 内容树(层级结构)
 */

public enum  ContentTree {
    CONTENT_CHILDREN_SONG("儿歌"),
    CONTENT_ANIMATION("动画"),
    CONTENT_COUNTRY_STUDY("国学"),
    CONTENT_SAFETY("安全"),
    CONTENT_STORY("故事"),
    CONTENT_MATH("数学"),
    CONTENT_ENCYCLOPEDIAS("百科"),
    CONTENT_PICTURE_BOOK("绘本"),
    CONTENT_CHINESE("语文"),
    CONTENT_ENGLISH_STUDY("英语"),
    CONTENT_HABITS_EMOTIONS("习惯与情绪"),
    CONTENT_OTHER("其他");

    private final String text;

    ContentTree(final String text){
        this.text=text;
    }
    @Override
    public String toString(){
        return text;
    }
}
