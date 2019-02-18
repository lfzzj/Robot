package com.hamitao.requestframe.entity;

/**
 * @data on 2018/3/21 11:33
 * @describe:
 */

public class CreateDeviceInfo {
    /**
     * responseDataObj : {"terminalInfo":{"channelid_listen_on_pushserver":"sz10001_kidsrobot_5a7862688d6349d2bce06467a1bcf624","comment":"","dagcluster":"hamitao_kidsrobot","deviceid":"333333","hosttype":"","mygatewayserver":"","mygatewayserver1":"","mygatewayserverhttpport":"8080","mygatewayserverhttpport1":"8080","nickname":"","socketport":"","terminalid":"sz10001_kidsrobot_5a7862688d6349d2bce06467a1bcf624","terminaltype":"kidsrobot","token":"2A0A02490C0B051D1D1613405CDC8FB75407071949070F4C5444491B1F115D5954422B4B4F3A565849474C347B5A07174C445D435A5401D98FB819055C06431259425C165D0E4881E3E41670515806101D00521611450F1A061A45495A5145184346565F554B595754154B","updatetime":"2018-03-09 09:09:35.701"}}
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
         * terminalInfo : {"channelid_listen_on_pushserver":"sz10001_kidsrobot_5a7862688d6349d2bce06467a1bcf624","comment":"","dagcluster":"hamitao_kidsrobot","deviceid":"333333","hosttype":"","mygatewayserver":"","mygatewayserver1":"","mygatewayserverhttpport":"8080","mygatewayserverhttpport1":"8080","nickname":"","socketport":"","terminalid":"sz10001_kidsrobot_5a7862688d6349d2bce06467a1bcf624","terminaltype":"kidsrobot","token":"2A0A02490C0B051D1D1613405CDC8FB75407071949070F4C5444491B1F115D5954422B4B4F3A565849474C347B5A07174C445D435A5401D98FB819055C06431259425C165D0E4881E3E41670515806101D00521611450F1A061A45495A5145184346565F554B595754154B","updatetime":"2018-03-09 09:09:35.701"}
         */

        private TerminalInfoBean terminalInfo;

        public TerminalInfoBean getTerminalInfo() {
            return terminalInfo;
        }

        public void setTerminalInfo(TerminalInfoBean terminalInfo) {
            this.terminalInfo = terminalInfo;
        }

        public static class TerminalInfoBean {
            /**
             * channelid_listen_on_pushserver : sz10001_kidsrobot_5a7862688d6349d2bce06467a1bcf624
             * comment :
             * dagcluster : hamitao_kidsrobot
             * deviceid : 333333
             * hosttype :
             * mygatewayserver :
             * mygatewayserver1 :
             * mygatewayserverhttpport : 8080
             * mygatewayserverhttpport1 : 8080
             * nickname :
             * socketport :
             * terminalid : sz10001_kidsrobot_5a7862688d6349d2bce06467a1bcf624
             * terminaltype : kidsrobot
             * token : 2A0A02490C0B051D1D1613405CDC8FB75407071949070F4C5444491B1F115D5954422B4B4F3A565849474C347B5A07174C445D435A5401D98FB819055C06431259425C165D0E4881E3E41670515806101D00521611450F1A061A45495A5145184346565F554B595754154B
             * updatetime : 2018-03-09 09:09:35.701
             */

            private String channelid_listen_on_pushserver;
            private String comment;
            private String dagcluster;
            private String deviceid;
            private String hosttype;
            private String mygatewayserver;
            private String mygatewayserver1;
            private String mygatewayserverhttpport;
            private String mygatewayserverhttpport1;
            private String nickname;
            private String socketport;
            private String terminalid;
            private String terminaltype;
            private String token;
            private String updatetime;

            public String getChannelid_listen_on_pushserver() {
                return channelid_listen_on_pushserver;
            }

            public void setChannelid_listen_on_pushserver(String channelid_listen_on_pushserver) {
                this.channelid_listen_on_pushserver = channelid_listen_on_pushserver;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getDagcluster() {
                return dagcluster;
            }

            public void setDagcluster(String dagcluster) {
                this.dagcluster = dagcluster;
            }

            public String getDeviceid() {
                return deviceid;
            }

            public void setDeviceid(String deviceid) {
                this.deviceid = deviceid;
            }

            public String getHosttype() {
                return hosttype;
            }

            public void setHosttype(String hosttype) {
                this.hosttype = hosttype;
            }

            public String getMygatewayserver() {
                return mygatewayserver;
            }

            public void setMygatewayserver(String mygatewayserver) {
                this.mygatewayserver = mygatewayserver;
            }

            public String getMygatewayserver1() {
                return mygatewayserver1;
            }

            public void setMygatewayserver1(String mygatewayserver1) {
                this.mygatewayserver1 = mygatewayserver1;
            }

            public String getMygatewayserverhttpport() {
                return mygatewayserverhttpport;
            }

            public void setMygatewayserverhttpport(String mygatewayserverhttpport) {
                this.mygatewayserverhttpport = mygatewayserverhttpport;
            }

            public String getMygatewayserverhttpport1() {
                return mygatewayserverhttpport1;
            }

            public void setMygatewayserverhttpport1(String mygatewayserverhttpport1) {
                this.mygatewayserverhttpport1 = mygatewayserverhttpport1;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSocketport() {
                return socketport;
            }

            public void setSocketport(String socketport) {
                this.socketport = socketport;
            }

            public String getTerminalid() {
                return terminalid;
            }

            public void setTerminalid(String terminalid) {
                this.terminalid = terminalid;
            }

            public String getTerminaltype() {
                return terminaltype;
            }

            public void setTerminaltype(String terminaltype) {
                this.terminaltype = terminaltype;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
