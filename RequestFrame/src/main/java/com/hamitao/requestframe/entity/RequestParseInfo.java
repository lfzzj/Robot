package com.hamitao.requestframe.entity;

/**
 * @data on 2018/3/27 11:17
 * @describe: 语义分析
 */

public class RequestParseInfo {
    /**
     * chinesetxt : 我要听七个小儿人的故事
     */

    private String chinesetxt;

    public RequestParseInfo(String chinesetxt) {
        this.chinesetxt = chinesetxt;
    }

    public String getChinesetxt() {
        return chinesetxt;
    }

    public void setChinesetxt(String chinesetxt) {
        this.chinesetxt = chinesetxt;
    }
}
