package com.hamitao.requestframe.entity;

import java.io.Serializable;

/**
 * @data on 2018/7/3 9:32
 * @describe:
 */

public class RequestCourseScheduleInfo implements Serializable {

    public RequestCourseScheduleInfo(String info, String infotype) {
        this.info = info;
        this.infotype = infotype;
    }

    /**
     * info : contentid
     * infotype : contentid
     */

    private String info;
    private String infotype;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfotype() {
        return infotype;
    }

    public void setInfotype(String infotype) {
        this.infotype = infotype;
    }
}
