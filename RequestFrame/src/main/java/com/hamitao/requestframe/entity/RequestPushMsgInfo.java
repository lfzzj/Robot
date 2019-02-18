package com.hamitao.requestframe.entity;

/**
 * @data on 2018/4/8 18:31
 * @describe:
 */

public class RequestPushMsgInfo {
    /**
     * responseDataObj : {"channelMsg":{"actionResult":{"action":"TAKE_PHOTO","httpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/aa/bbb/ccc?Expires=1523182770&OSSAccessKeyId=STS.ELB1bccS3W7gjKKqPXRJde2wg&Signature=myzIc6q3vic7ckFn5pAaTg9gT0c%3D&security-token=CAISjAJ1q6Ft5B2yfSjIpI%2F3etjXjowS4PWMaG36lVANXsVIivfchTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPc8RbrUiF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABDUT7Td2T07XzHrmTi9J6e%2FduJkk5z0IaiD8jh1tvq1aHjAWWk4XKJBoppSyJTfnfd2SxI5kZ4JsZN6sshGYuAag1LHJF16ubYE3KA7aWpw0pE8Q4DPCLHWpr4yp72EWvQD47QT%2FPb3pgiVC2OJKXFFtYGnUvEuqon5X98IYOvd4%3D","contents":"aa/bbb/ccc","urltype":"keyonoss"},"source_channelid":"terminalid","target_channelid":"customerid"}}
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
        /**
         * channelMsg : {"actionResult":{"action":"TAKE_PHOTO","httpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/aa/bbb/ccc?Expires=1523182770&OSSAccessKeyId=STS.ELB1bccS3W7gjKKqPXRJde2wg&Signature=myzIc6q3vic7ckFn5pAaTg9gT0c%3D&security-token=CAISjAJ1q6Ft5B2yfSjIpI%2F3etjXjowS4PWMaG36lVANXsVIivfchTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPc8RbrUiF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABDUT7Td2T07XzHrmTi9J6e%2FduJkk5z0IaiD8jh1tvq1aHjAWWk4XKJBoppSyJTfnfd2SxI5kZ4JsZN6sshGYuAag1LHJF16ubYE3KA7aWpw0pE8Q4DPCLHWpr4yp72EWvQD47QT%2FPb3pgiVC2OJKXFFtYGnUvEuqon5X98IYOvd4%3D","contents":"aa/bbb/ccc","urltype":"keyonoss"},"source_channelid":"terminalid","target_channelid":"customerid"}
         */

        private ChannelMsgBean channelMsg;

        public ChannelMsgBean getChannelMsg() {
            return channelMsg;
        }

        public void setChannelMsg(ChannelMsgBean channelMsg) {
            this.channelMsg = channelMsg;
        }

        public static class ChannelMsgBean {
            /**
             * actionResult : {"action":"TAKE_PHOTO","httpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/aa/bbb/ccc?Expires=1523182770&OSSAccessKeyId=STS.ELB1bccS3W7gjKKqPXRJde2wg&Signature=myzIc6q3vic7ckFn5pAaTg9gT0c%3D&security-token=CAISjAJ1q6Ft5B2yfSjIpI%2F3etjXjowS4PWMaG36lVANXsVIivfchTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPc8RbrUiF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABDUT7Td2T07XzHrmTi9J6e%2FduJkk5z0IaiD8jh1tvq1aHjAWWk4XKJBoppSyJTfnfd2SxI5kZ4JsZN6sshGYuAag1LHJF16ubYE3KA7aWpw0pE8Q4DPCLHWpr4yp72EWvQD47QT%2FPb3pgiVC2OJKXFFtYGnUvEuqon5X98IYOvd4%3D","contents":"aa/bbb/ccc","urltype":"keyonoss"}
             * source_channelid : terminalid
             * target_channelid : customerid
             */

            private ActionResultBean actionResult;
            private String source_channelid;
            private String target_channelid;

            public ActionResultBean getActionResult() {
                return actionResult;
            }

            public void setActionResult(ActionResultBean actionResult) {
                this.actionResult = actionResult;
            }

            public String getSource_channelid() {
                return source_channelid;
            }

            public void setSource_channelid(String source_channelid) {
                this.source_channelid = source_channelid;
            }

            public String getTarget_channelid() {
                return target_channelid;
            }

            public void setTarget_channelid(String target_channelid) {
                this.target_channelid = target_channelid;
            }

            public static class ActionResultBean {
                /**
                 * action : TAKE_PHOTO
                 * httpURL : http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/aa/bbb/ccc?Expires=1523182770&OSSAccessKeyId=STS.ELB1bccS3W7gjKKqPXRJde2wg&Signature=myzIc6q3vic7ckFn5pAaTg9gT0c%3D&security-token=CAISjAJ1q6Ft5B2yfSjIpI%2F3etjXjowS4PWMaG36lVANXsVIivfchTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPc8RbrUiF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABDUT7Td2T07XzHrmTi9J6e%2FduJkk5z0IaiD8jh1tvq1aHjAWWk4XKJBoppSyJTfnfd2SxI5kZ4JsZN6sshGYuAag1LHJF16ubYE3KA7aWpw0pE8Q4DPCLHWpr4yp72EWvQD47QT%2FPb3pgiVC2OJKXFFtYGnUvEuqon5X98IYOvd4%3D
                 * contents : aa/bbb/ccc
                 * urltype : keyonoss
                 */

                private String action;
                private String httpURL;
                private String contents;
                private String urltype;

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public String getHttpURL() {
                    return httpURL;
                }

                public void setHttpURL(String httpURL) {
                    this.httpURL = httpURL;
                }

                public String getContents() {
                    return contents;
                }

                public void setContents(String contents) {
                    this.contents = contents;
                }

                public String getUrltype() {
                    return urltype;
                }

                public void setUrltype(String urltype) {
                    this.urltype = urltype;
                }
            }
        }
    }
}
