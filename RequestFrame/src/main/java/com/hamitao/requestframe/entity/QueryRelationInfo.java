package com.hamitao.requestframe.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @data on 2018/4/10 15:55
 * @describe:
 */

public class QueryRelationInfo {

    /**
     * responseDataObj : {"relation":{"circleInfos":[],"customerInfos":[{"bindname":"爸爸","channelid_listen_on_pushserver":"ffb992d8834f423bac605ceb1b3d7008","customerid":"sz00002_customer_ffb992d8834f423bac605ceb1b3d7008","loginname":"13211112222","updatetime":"2018-04-11 10:54:18.596000"}],"terminalInfos":[]}}
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
         * relation : {"circleInfos":[],"customerInfos":[{"bindname":"爸爸","channelid_listen_on_pushserver":"ffb992d8834f423bac605ceb1b3d7008","customerid":"sz00002_customer_ffb992d8834f423bac605ceb1b3d7008","loginname":"13211112222","updatetime":"2018-04-11 10:54:18.596000"}],"terminalInfos":[]}
         */

        private RelationBean relation;

        public RelationBean getRelation() {
            return relation;
        }

        public void setRelation(RelationBean relation) {
            this.relation = relation;
        }

        public static class RelationBean {
            private List<?> circleInfos;
            private List<CustomerInfosBean> customerInfos;
            private List<?> terminalInfos;

            public List<?> getCircleInfos() {
                return circleInfos;
            }

            public void setCircleInfos(List<?> circleInfos) {
                this.circleInfos = circleInfos;
            }

            public List<CustomerInfosBean> getCustomerInfos() {
                return customerInfos;
            }

            public void setCustomerInfos(List<CustomerInfosBean> customerInfos) {
                this.customerInfos = customerInfos;
            }

            public List<?> getTerminalInfos() {
                return terminalInfos;
            }

            public void setTerminalInfos(List<?> terminalInfos) {
                this.terminalInfos = terminalInfos;
            }

            public static class CustomerInfosBean implements Serializable {
                /**
                 * bindname : 爸爸
                 * channelid_listen_on_pushserver : ffb992d8834f423bac605ceb1b3d7008
                 * customerid : sz00002_customer_ffb992d8834f423bac605ceb1b3d7008
                 * loginname : 13211112222
                 * updatetime : 2018-04-11 10:54:18.596000
                 */

                private String bindname;
                private String channelid_listen_on_pushserver;
                private String customerid;
                private String loginname;
                private String updatetime;

                public String getBindname() {
                    return bindname;
                }

                public void setBindname(String bindname) {
                    this.bindname = bindname;
                }

                public String getChannelid_listen_on_pushserver() {
                    return channelid_listen_on_pushserver;
                }

                public void setChannelid_listen_on_pushserver(String channelid_listen_on_pushserver) {
                    this.channelid_listen_on_pushserver = channelid_listen_on_pushserver;
                }

                public String getCustomerid() {
                    return customerid;
                }

                public void setCustomerid(String customerid) {
                    this.customerid = customerid;
                }

                public String getLoginname() {
                    return loginname;
                }

                public void setLoginname(String loginname) {
                    this.loginname = loginname;
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
}

