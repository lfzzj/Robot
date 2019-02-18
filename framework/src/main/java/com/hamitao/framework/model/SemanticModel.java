package com.hamitao.framework.model;

import java.util.List;

/**
 * @data on 2018/3/13 11:54
 * @describe: 智能语音对话
 */

public class SemanticModel {


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
         * intent : {"appKey":"os.sys.song","code":200101,"operateState":1000,"parameters":{"song":"","duration":"","singer":"","originalSinger":"","originalSong":"体面","name":"","text_spare":"对不起，这个我真的不会唱呢","id":"","url":""},"type":"function"}
         * results : [{"resultType":"text","values":{"text":"好的，接下来我给你唱首歌"}}]
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
             * parameters : {"song":"","duration":"","singer":"","originalSinger":"","originalSong":"体面","name":"","text_spare":"对不起，这个我真的不会唱呢","id":"","url":""}
             * type : function
             */

            private String appKey;
            private int code;
            private int operateState;
            private Object parameters;
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

            public Object getParameters() {
                return parameters;
            }

            public void setParameters(Object parameters) {
                this.parameters = parameters;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class ResultsBean {
            /**
             * resultType : text
             * values : {"text":"好的，接下来我给你唱首歌"}
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
                 * text : 好的，接下来我给你唱首歌
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
            private String text;

            private String service;

            public void setText(String text) {
                this.text = text;
            }

            public String getText() {

                return text;
            }

            public String getService() {
                return service;
            }

            public void setService(String service) {
                this.service = service;
            }
        }
    }
}
