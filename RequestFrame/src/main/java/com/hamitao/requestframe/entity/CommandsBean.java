package com.hamitao.requestframe.entity;

/**
 * @data on 2018/5/4 11:13
 * @describe:
 */

public class CommandsBean {
    /**
     * commandname : dial_phone
     * params : {"who":"mom"}
     */

    private String commandname;
    private ParamsBean params;

    public String getCommandname() {
        return commandname;
    }

    public void setCommandname(String commandname) {
        this.commandname = commandname;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public static class ParamsBean {
        /**
         * who : mom
         */

        private String who;

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
