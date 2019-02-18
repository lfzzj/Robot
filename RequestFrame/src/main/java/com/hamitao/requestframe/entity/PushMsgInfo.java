package com.hamitao.requestframe.entity;

/**
 * @data on 2018/4/9 15:26
 * @describe:
 */

public class PushMsgInfo {

    /**
     * msg_id : 2206615675
     * error : {"message":"cannot find user by this audience","code":1011}
     */

    private long msg_id;
    private ErrorBean error;

    public long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(long msg_id) {
        this.msg_id = msg_id;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public static class ErrorBean {
        /**
         * message : cannot find user by this audience
         * code : 1011
         */

        private String message;
        private int code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
