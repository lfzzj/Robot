package com.hamitao.requestframe.entity;

/**
 * @data on 2018/4/16 15:16
 * @describe:
 */

public class QuerySettingInfo {

    /**
     * responseDataObj : {"deviceSetting":{"parentControlSettingInfo":{"videoduration":"30"},"terminalid":"sz10001_kidsrobot_5eb7e864a63a4041b262d5a3f2acfa21"}}
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
         * deviceSetting : {"parentControlSettingInfo":{"videoduration":"30"},"terminalid":"sz10001_kidsrobot_5eb7e864a63a4041b262d5a3f2acfa21"}
         */

        private DeviceSettingBean deviceSetting;

        public DeviceSettingBean getDeviceSetting() {
            return deviceSetting;
        }

        public void setDeviceSetting(DeviceSettingBean deviceSetting) {
            this.deviceSetting = deviceSetting;
        }

        public static class DeviceSettingBean {
            /**
             * parentControlSettingInfo : {"videoduration":"30"}
             * terminalid : sz10001_kidsrobot_5eb7e864a63a4041b262d5a3f2acfa21
             */

            private ParentControlSettingInfoBean parentControlSettingInfo;
            private String terminalid;

            public ParentControlSettingInfoBean getParentControlSettingInfo() {
                return parentControlSettingInfo;
            }

            public void setParentControlSettingInfo(ParentControlSettingInfoBean parentControlSettingInfo) {
                this.parentControlSettingInfo = parentControlSettingInfo;
            }

            public String getTerminalid() {
                return terminalid;
            }

            public void setTerminalid(String terminalid) {
                this.terminalid = terminalid;
            }

            public static class ParentControlSettingInfoBean {
                /**
                 * videoduration : 30
                 */

                private String videoduration;

                public String getVideoduration() {
                    return videoduration;
                }

                public void setVideoduration(String videoduration) {
                    this.videoduration = videoduration;
                }
            }
        }
    }
}
