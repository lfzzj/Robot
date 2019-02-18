package com.hamitao.requestframe.entity;

import java.util.List;

/**
 * @data on 2018/4/16 17:52
 * @describe:
 */

public class QueryTimerAlarmInfo {


    /**
     * responseDataObj : {"timerAlarms":[{"id":"fbe2fce589154fb1811033b002318f29","idx":"38755395","name":"早上起床","ownerid":"sz10001_kidsrobot_5eb7e864a63a4041b262d5a3f2acfa21","status":"disable","type":"devicedoit","timers":[{"starttime":"12:00","day":"SUN,MON,TUE,WED,THU,FRI,SAT"}]}]}
     * result : success
     */

    private ResponseDataObjBean responseDataObj;
    private String result;

    public ResponseDataObjBean getResponseDataObj() {
        return responseDataObj;
    }

    public void setResponseDataObj(ResponseDataObjBean responseDataObj) {
        this.responseDataObj = responseDataObj;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class ResponseDataObjBean {
        private List<TimerAlarmsBean> timerAlarms;

        public List<TimerAlarmsBean> getTimerAlarms() {
            return timerAlarms;
        }

        public void setTimerAlarms(List<TimerAlarmsBean> timerAlarms) {
            this.timerAlarms = timerAlarms;
        }

        public static class TimerAlarmsBean {
            /**
             * id : fbe2fce589154fb1811033b002318f29
             * idx : 38755395
             * name : 早上起床
             * ownerid : sz10001_kidsrobot_5eb7e864a63a4041b262d5a3f2acfa21
             * status : disable
             * type : devicedoit
             * timers : [{"starttime":"12:00","day":"SUN,MON,TUE,WED,THU,FRI,SAT"}]
             */

            private String id;
            private String idx;
            private String name;
            private String ownerid;
            private String status;
            private String type;
            private List<TimersBean> timers;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIdx() {
                return idx;
            }

            public void setIdx(String idx) {
                this.idx = idx;
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
                 * starttime : 12:00
                 * day : SUN,MON,TUE,WED,THU,FRI,SAT
                 */

                private String starttime;
                private String day;

                public String getStarttime() {
                    return starttime;
                }

                public void setStarttime(String starttime) {
                    this.starttime = starttime;
                }

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }
            }
        }
    }
}
