package com.hamitao.kids.turing.model;


import java.util.List;

public class TuringParseBean {

    private List<BehaviorsBean> behaviors;

    public List<BehaviorsBean> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(List<BehaviorsBean> behaviors) {
        this.behaviors = behaviors;
    }

    public static class BehaviorsBean {
        /**
         * emotion : {"answerEmotionId":0}
         * exception : 糟糕，这首歌唱不出来了。给你换首
         * intent : {"appKey":"os.sys.song","code":200101,"operateState":1000,"parameters":{"song":"冰雨","duration":"0","singer":"刘德华","originalSinger":"刘德华","originalSong":"冰雨","name":"冰雨","text_spare":"啊，这个我还没学过，以后我会好好学的","id":"53715","url":"http://appfile.tuling123.com/media/audio/20180524/51904d9c922a45ea9c9f4c96fd1e909a.mp3","people_name":"妈妈","resources":{"animalName":"狗","url":"http://turing-iot.oss.tuling123.com/func/animal/gou/gou.mp3"},"setting_level":1,"memoContent":"起床","cycleType":0,"alarmType":"remind","endDate":"2018-06-28","timeLen":"","action":"add","alarmTag":"alarmClock","time":"07:00:00","startDate":"2018-06-28","app_name":"冰箱","is_open":true},"type":"function"}
         * results : [{"resultType":"text","values":{"text":"我要开始唱冰雨了"}}]
         * sequences : [{"service":"intent"}]
         */

        private EmotionBean emotion;
        private String exception;
        private IntentBean intent;
        private List<ResultsBean> results;
        private List<SequencesBean> sequences;

        public EmotionBean getEmotion() {
            return emotion;
        }

        public void setEmotion(EmotionBean emotion) {
            this.emotion = emotion;
        }

        public String getException() {
            return exception;
        }

        public void setException(String exception) {
            this.exception = exception;
        }

        public IntentBean getIntent() {
            return intent;
        }

        public void setIntent(IntentBean intent) {
            this.intent = intent;
        }

        public List<ResultsBean> getResults() {
            return results;
        }

        public void setResults(List<ResultsBean> results) {
            this.results = results;
        }

        public List<SequencesBean> getSequences() {
            return sequences;
        }

        public void setSequences(List<SequencesBean> sequences) {
            this.sequences = sequences;
        }

        public static class EmotionBean {
            /**
             * answerEmotionId : 0
             */

            private int answerEmotionId;

            public int getAnswerEmotionId() {
                return answerEmotionId;
            }

            public void setAnswerEmotionId(int answerEmotionId) {
                this.answerEmotionId = answerEmotionId;
            }
        }

        public static class IntentBean {
            /**
             * appKey : os.sys.song
             * code : 200101
             * operateState : 1000
             * parameters : {"song":"冰雨","duration":"0","singer":"刘德华","originalSinger":"刘德华","originalSong":"冰雨","name":"冰雨","text_spare":"啊，这个我还没学过，以后我会好好学的","id":"53715","url":"http://appfile.tuling123.com/media/audio/20180524/51904d9c922a45ea9c9f4c96fd1e909a.mp3","people_name":"妈妈","resources":{"animalName":"狗","url":"http://turing-iot.oss.tuling123.com/func/animal/gou/gou.mp3"},"setting_level":1,"memoContent":"起床","cycleType":0,"alarmType":"remind","endDate":"2018-06-28","timeLen":"","action":"add","alarmTag":"alarmClock","time":"07:00:00","startDate":"2018-06-28","app_name":"冰箱","is_open":true}
             * type : function
             */

            private String appKey;
            private int code;
            private int operateState;
            private ParametersBean parameters;
            private String type;

            public String getAppKey() {
                return appKey;
            }

            public void setAppKey(String appKey) {
                this.appKey = appKey;
            }

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public int getOperateState() {
                return operateState;
            }

            public void setOperateState(int operateState) {
                this.operateState = operateState;
            }

            public ParametersBean getParameters() {
                return parameters;
            }

            public void setParameters(ParametersBean parameters) {
                this.parameters = parameters;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class ParametersBean {
                /**
                 * song : 冰雨
                 * duration : 0
                 * singer : 刘德华
                 * originalSinger : 刘德华
                 * originalSong : 冰雨
                 * name : 冰雨
                 * text_spare : 啊，这个我还没学过，以后我会好好学的
                 * id : 53715
                 * url : http://appfile.tuling123.com/media/audio/20180524/51904d9c922a45ea9c9f4c96fd1e909a.mp3
                 * people_name : 妈妈
                 * resources : {"animalName":"狗","url":"http://turing-iot.oss.tuling123.com/func/animal/gou/gou.mp3"}
                 * setting_level : 1
                 * memoContent : 起床
                 * cycleType : 0
                 * alarmType : remind
                 * endDate : 2018-06-28
                 * timeLen :
                 * action : add
                 * alarmTag : alarmClock
                 * time : 07:00:00
                 * startDate : 2018-06-28
                 * app_name : 冰箱
                 * is_open : true
                 */

                private String song;
                private String duration;
                private String singer;
                private String originalSinger;
                private String originalSong;
                private String name;
                private String text_spare;
                private String id;
                private String url;
                private String people_name;
                private ResourcesBean resources;
                private int setting_level;
                private String memoContent;
                private int cycleType;
                private String alarmType;
                private String endDate;
                private String timeLen;
                private String action;
                private String alarmTag;
                private String time;
                private String startDate;
                private String app_name;
                private boolean is_open;

                public String getSong() {
                    return song;
                }

                public void setSong(String song) {
                    this.song = song;
                }

                public String getDuration() {
                    return duration;
                }

                public void setDuration(String duration) {
                    this.duration = duration;
                }

                public String getSinger() {
                    return singer;
                }

                public void setSinger(String singer) {
                    this.singer = singer;
                }

                public String getOriginalSinger() {
                    return originalSinger;
                }

                public void setOriginalSinger(String originalSinger) {
                    this.originalSinger = originalSinger;
                }

                public String getOriginalSong() {
                    return originalSong;
                }

                public void setOriginalSong(String originalSong) {
                    this.originalSong = originalSong;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getText_spare() {
                    return text_spare;
                }

                public void setText_spare(String text_spare) {
                    this.text_spare = text_spare;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getPeople_name() {
                    return people_name;
                }

                public void setPeople_name(String people_name) {
                    this.people_name = people_name;
                }

                public ResourcesBean getResources() {
                    return resources;
                }

                public void setResources(ResourcesBean resources) {
                    this.resources = resources;
                }

                public int getSetting_level() {
                    return setting_level;
                }

                public void setSetting_level(int setting_level) {
                    this.setting_level = setting_level;
                }

                public String getMemoContent() {
                    return memoContent;
                }

                public void setMemoContent(String memoContent) {
                    this.memoContent = memoContent;
                }

                public int getCycleType() {
                    return cycleType;
                }

                public void setCycleType(int cycleType) {
                    this.cycleType = cycleType;
                }

                public String getAlarmType() {
                    return alarmType;
                }

                public void setAlarmType(String alarmType) {
                    this.alarmType = alarmType;
                }

                public String getEndDate() {
                    return endDate;
                }

                public void setEndDate(String endDate) {
                    this.endDate = endDate;
                }

                public String getTimeLen() {
                    return timeLen;
                }

                public void setTimeLen(String timeLen) {
                    this.timeLen = timeLen;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getAlarmTag() {
                    return alarmTag;
                }

                public void setAlarmTag(String alarmTag) {
                    this.alarmTag = alarmTag;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getStartDate() {
                    return startDate;
                }

                public void setStartDate(String startDate) {
                    this.startDate = startDate;
                }

                public String getApp_name() {
                    return app_name;
                }

                public void setApp_name(String app_name) {
                    this.app_name = app_name;
                }

                public boolean isIs_open() {
                    return is_open;
                }

                public void setIs_open(boolean is_open) {
                    this.is_open = is_open;
                }

                public static class ResourcesBean {
                    /**
                     * animalName : 狗
                     * url : http://turing-iot.oss.tuling123.com/func/animal/gou/gou.mp3
                     */

                    private String animalName;
                    private String url;

                    public String getAnimalName() {
                        return animalName;
                    }

                    public void setAnimalName(String animalName) {
                        this.animalName = animalName;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }
                }
            }
        }

        public static class ResultsBean {
            /**
             * resultType : text
             * values : {"text":"我要开始唱冰雨了"}
             */

            private String resultType;
            private ValuesBean values;

            public String getResultType() {
                return resultType;
            }

            public void setResultType(String resultType) {
                this.resultType = resultType;
            }

            public ValuesBean getValues() {
                return values;
            }

            public void setValues(ValuesBean values) {
                this.values = values;
            }

            public static class ValuesBean {
                /**
                 * text : 我要开始唱冰雨了
                 */

                private String text;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }
        }

        public static class SequencesBean {
            /**
             * service : intent
             */

            private String service;

            public String getService() {
                return service;
            }

            public void setService(String service) {
                this.service = service;
            }
        }
    }
}
