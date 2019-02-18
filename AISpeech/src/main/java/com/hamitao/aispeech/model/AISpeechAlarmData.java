package com.hamitao.aispeech.model;

import java.util.List;

public class AISpeechAlarmData {

    private List<DbdataBean> dbdata;

    public List<DbdataBean> getDbdata() {
        return dbdata;
    }

    public void setDbdata(List<DbdataBean> dbdata) {
        this.dbdata = dbdata;
    }

    public static class DbdataBean {
        /**
         * time : 19:00:00
         * repeat : EVERYDAY
         * object : 日程
         * date : EVERYDAY
         * period : 晚上
         * event : 起床
         */

        private String time;
        private String repeat;
        private String object;
        private String date;
        private String period;
        private String event;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getRepeat() {
            return repeat;
        }

        public void setRepeat(String repeat) {
            this.repeat = repeat;
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }
    }
}
