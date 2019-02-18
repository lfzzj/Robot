package com.hamitao.kids.model;

import com.hamitao.requestframe.entity.RequestCourseScheduleInfo;

import java.util.List;

/**
 * @data on 2018/6/27 17:05
 * @describe:
 */

public class CourseScheduleBean {
    private String startTime;

    private String id;

    private List<RequestCourseScheduleInfo> requestCourseScheduleInfos;

    public CourseScheduleBean(String startTime, String id, List<RequestCourseScheduleInfo> requestCourseScheduleInfos) {
        this.startTime = startTime;
        this.id = id;
        this.requestCourseScheduleInfos = requestCourseScheduleInfos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public List<RequestCourseScheduleInfo> getRequestCourseScheduleInfos() {
        return requestCourseScheduleInfos;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setRequestCourseScheduleInfos(List<RequestCourseScheduleInfo> requestCourseScheduleInfos) {
        this.requestCourseScheduleInfos = requestCourseScheduleInfos;
    }
}
