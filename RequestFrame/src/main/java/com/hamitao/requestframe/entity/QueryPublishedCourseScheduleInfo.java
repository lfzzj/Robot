package com.hamitao.requestframe.entity;

import java.util.List;

/**
 * @data on 2018/6/26 18:17
 * @describe:
 */

public class QueryPublishedCourseScheduleInfo {

    /**
     * responseDataObj : {"courseScheduleList":[{"authorid":"花花妈customerid","authorname":"花花妈","coursescheduleid":"coursescheduleid_041256fe51384e30b26869cf536e8373","description":"早教系列","duration":0,"name":"name","scheduleContents":[{"duration":0,"info":"recordid_xxxx_xxxx","infotype":"recordid","schedule":{"day":"MON,FEB","endtime":"","index":"yyyy","starttime":"11:00"}},{"duration":0,"info":"xxxxxxxxx","infotype":"contentid","schedule":{"day":"MON,FEB","endtime":"","index":"xxxx","starttime":"12:00"}}],"scheduleplan":{"day":"MON,FEB","endtime":"","starttime":"12:00"},"source":"selfmade","status":"enable"}]}
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
        private List<CourseScheduleListBean> courseScheduleList;

        public List<CourseScheduleListBean> getCourseScheduleList() {
            return courseScheduleList;
        }

        public void setCourseScheduleList(List<CourseScheduleListBean> courseScheduleList) {
            this.courseScheduleList = courseScheduleList;
        }

        public static class CourseScheduleListBean {
            /**
             * authorid : 花花妈customerid
             * authorname : 花花妈
             * coursescheduleid : coursescheduleid_041256fe51384e30b26869cf536e8373
             * description : 早教系列
             * duration : 0
             * name : name
             * scheduleContents : [{"duration":0,"info":"recordid_xxxx_xxxx","infotype":"recordid","schedule":{"day":"MON,FEB","endtime":"","index":"yyyy","starttime":"11:00"}},{"duration":0,"info":"xxxxxxxxx","infotype":"contentid","schedule":{"day":"MON,FEB","endtime":"","index":"xxxx","starttime":"12:00"}}]
             * scheduleplan : {"day":"MON,FEB","endtime":"","starttime":"12:00"}
             * source : selfmade
             * status : enable
             */

            private String authorid;
            private String authorname;
            private String coursescheduleid;
            private String description;
            private int duration;
            private String name;
            private ScheduleplanBean scheduleplan;
            private String source;
            private String status;
            private List<ScheduleContentsBean> scheduleContents;

            public String getAuthorid() {
                return authorid;
            }

            public void setAuthorid(String authorid) {
                this.authorid = authorid;
            }

            public String getAuthorname() {
                return authorname;
            }

            public void setAuthorname(String authorname) {
                this.authorname = authorname;
            }

            public String getCoursescheduleid() {
                return coursescheduleid;
            }

            public void setCoursescheduleid(String coursescheduleid) {
                this.coursescheduleid = coursescheduleid;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ScheduleplanBean getScheduleplan() {
                return scheduleplan;
            }

            public void setScheduleplan(ScheduleplanBean scheduleplan) {
                this.scheduleplan = scheduleplan;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<ScheduleContentsBean> getScheduleContents() {
                return scheduleContents;
            }

            public void setScheduleContents(List<ScheduleContentsBean> scheduleContents) {
                this.scheduleContents = scheduleContents;
            }

            public static class ScheduleplanBean {
                /**
                 * day : MON,FEB
                 * endtime :
                 * starttime : 12:00
                 */

                private String day;
                private String endtime;
                private String starttime;

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }

                public String getEndtime() {
                    return endtime;
                }

                public void setEndtime(String endtime) {
                    this.endtime = endtime;
                }

                public String getStarttime() {
                    return starttime;
                }

                public void setStarttime(String starttime) {
                    this.starttime = starttime;
                }
            }

            public static class ScheduleContentsBean {
                /**
                 * duration : 0
                 * info : recordid_xxxx_xxxx
                 * infotype : recordid
                 * schedule : {"day":"MON,FEB","endtime":"","index":"yyyy","starttime":"11:00"}
                 */

                private int duration;
                private String info;
                private String infotype;
                private ScheduleBean schedule;

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

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

                public ScheduleBean getSchedule() {
                    return schedule;
                }

                public void setSchedule(ScheduleBean schedule) {
                    this.schedule = schedule;
                }

                public static class ScheduleBean {
                    /**
                     * day : MON,FEB
                     * endtime :
                     * index : yyyy
                     * starttime : 11:00
                     */

                    private String day;
                    private String endtime;
                    private String index;
                    private String starttime;

                    public String getDay() {
                        return day;
                    }

                    public void setDay(String day) {
                        this.day = day;
                    }

                    public String getEndtime() {
                        return endtime;
                    }

                    public void setEndtime(String endtime) {
                        this.endtime = endtime;
                    }

                    public String getIndex() {
                        return index;
                    }

                    public void setIndex(String index) {
                        this.index = index;
                    }

                    public String getStarttime() {
                        return starttime;
                    }

                    public void setStarttime(String starttime) {
                        this.starttime = starttime;
                    }
                }
            }
        }
    }
}
