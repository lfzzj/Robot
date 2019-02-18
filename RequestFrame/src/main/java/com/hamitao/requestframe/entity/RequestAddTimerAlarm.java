package com.hamitao.requestframe.entity;

import java.util.List;

public class RequestAddTimerAlarm {

    /**
     * creator : 张三
     * name : 早上起床
     * ownerid : sz10001_kidsrobot_5eb7e864a63a4041b262d5a3f2acfa21
     * status : disable
     * type : devicedoit
     * timers : [{"day":"SUN,MON,TUE,WED,THU,FRI,SAT","starttime":"12:00"}]
     */

    private String creator;
    private String name;
    private String ownerid;
    private String status;
    private String type;
    private List<TimersBean> timers;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TimersBean> getTimers() {
        return timers;
    }

    public void setTimers(List<TimersBean> timers) {
        this.timers = timers;
    }

    public static class TimersBean {
        /**
         * day : SUN,MON,TUE,WED,THU,FRI,SAT
         * starttime : 12:00
         */

        private String day;
        private String starttime;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }
    }
}
