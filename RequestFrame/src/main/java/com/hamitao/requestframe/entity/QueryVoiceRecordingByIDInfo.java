package com.hamitao.requestframe.entity;

import java.util.List;

/**
 * @data on 2018/6/30 10:44
 * @describe:
 */

public class QueryVoiceRecordingByIDInfo {

    /**
     * responseDataObj : {"voiceRecordings":[{"description":"我的录音描述","httpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/appstorage/myvoicerecording/11111.mp3?Expires=1524051925&OSSAccessKeyId=STS.HCs4zQSJSXvM29c2ouwKo7W6P&Signature=pPHHfVMnMDSxrbZkfZp81U727zM%3D&security-token=CAISjAJ1q6Ft5B2yfSjIqYDGf8DlvpVy77SmMB%2FS1m8ge8RD2JKdsjz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPaN0Cz0%2BF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABUSA06B%2B5eJ%2B%2Ba0nIGHw3NhHHrNaHxKC4z33%2BIHJnF%2FLb7%2Fceh94piiqljQKHb%2FdSS%2F30cWcMSPdPlCBpG8qVWk3VWjsv7V3lNzO2hdDYQpVFcMG3TW0FJj6JB8q%2BpQkI%2BYiKdw5YZprV9ACpHGaBpSRSBV6gjTa9mMBms5YxERE%3D","id":"voicerecord_53e3af56412d4d4faca9a11755151a12","name":"修改后的名字","ownerid":"mycustomerid","ownername":"","selectStatus":"no","source":"DEVICE","url":"appstorage/myvoicerecording/11111.mp3"}]}
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
        private List<VoiceRecordingsBean> voiceRecordings;

        public List<VoiceRecordingsBean> getVoiceRecordings() {
            return voiceRecordings;
        }

        public void setVoiceRecordings(List<VoiceRecordingsBean> voiceRecordings) {
            this.voiceRecordings = voiceRecordings;
        }

        public static class VoiceRecordingsBean {
            /**
             * description : 我的录音描述
             * httpURL : http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/appstorage/myvoicerecording/11111.mp3?Expires=1524051925&OSSAccessKeyId=STS.HCs4zQSJSXvM29c2ouwKo7W6P&Signature=pPHHfVMnMDSxrbZkfZp81U727zM%3D&security-token=CAISjAJ1q6Ft5B2yfSjIqYDGf8DlvpVy77SmMB%2FS1m8ge8RD2JKdsjz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPaN0Cz0%2BF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABUSA06B%2B5eJ%2B%2Ba0nIGHw3NhHHrNaHxKC4z33%2BIHJnF%2FLb7%2Fceh94piiqljQKHb%2FdSS%2F30cWcMSPdPlCBpG8qVWk3VWjsv7V3lNzO2hdDYQpVFcMG3TW0FJj6JB8q%2BpQkI%2BYiKdw5YZprV9ACpHGaBpSRSBV6gjTa9mMBms5YxERE%3D
             * id : voicerecord_53e3af56412d4d4faca9a11755151a12
             * name : 修改后的名字
             * ownerid : mycustomerid
             * ownername :
             * selectStatus : no
             * source : DEVICE
             * url : appstorage/myvoicerecording/11111.mp3
             */

            private String description;
            private String httpURL;
            private String id;
            private String name;
            private String ownerid;
            private String ownername;
            private String selectStatus;
            private String source;
            private String url;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getHttpURL() {
                return httpURL;
            }

            public void setHttpURL(String httpURL) {
                this.httpURL = httpURL;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getOwnername() {
                return ownername;
            }

            public void setOwnername(String ownername) {
                this.ownername = ownername;
            }

            public String getSelectStatus() {
                return selectStatus;
            }

            public void setSelectStatus(String selectStatus) {
                this.selectStatus = selectStatus;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
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
