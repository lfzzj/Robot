package com.hamitao.kids.model;

/**
 * EventBus传值
 */
public class AnyEventType {
    private String flag;
    private String content;

    public AnyEventType(String flag, String content) {
        this.flag = flag;
        this.content = content;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFlag() {

        return flag;
    }

    public String getContent() {
        return content;
    }
}
