package com.hamitao.requestframe.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @data on 2018/6/7 14:58
 * @describe: 查询联系人
 */

public class QueryContactInfo {

    /**
     * responseDataObj : {"contacts":[{"contactid":"d824fe72a64246e8a8c26155eb3102c3","contactname":"张三","contacttype":"tel","contactway":"contactwaywww","creator":"bbb","name":"张三","ownerid":"sz10001_kidsrobot_5eb7e864a63a4041b262d5a3f2acfa21","phonenum":"1832222222","shortnum":"9876","source":"aaa","updatetime":"2018-04-12 18:36:39.072"}]}
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
        private List<ContactsBean> contacts;

        public List<ContactsBean> getContacts() {
            return contacts;
        }

        public void setContacts(List<ContactsBean> contacts) {
            this.contacts = contacts;
        }

        public static class ContactsBean implements Serializable{
            /**
             * contactid : d824fe72a64246e8a8c26155eb3102c3
             * contactname : 张三
             * contacttype : tel
             * contactway : contactwaywww
             * creator : bbb
             * name : 张三
             * ownerid : sz10001_kidsrobot_5eb7e864a63a4041b262d5a3f2acfa21
             * phonenum : 1832222222
             * shortnum : 9876
             * source : aaa
             * updatetime : 2018-04-12 18:36:39.072
             */

            private String contactid;
            private String contactname;
            private String contacttype;
            private String contactway;
            private String creator;
            private String name;
            private String ownerid;
            private String phonenum;
            private String shortnum;
            private String source;
            private String updatetime;

            public String getContactid() {
                return contactid;
            }

            public void setContactid(String contactid) {
                this.contactid = contactid;
            }

            public String getContactname() {
                return contactname;
            }

            public void setContactname(String contactname) {
                this.contactname = contactname;
            }

            public String getContacttype() {
                return contacttype;
            }

            public void setContacttype(String contacttype) {
                this.contacttype = contacttype;
            }

            public String getContactway() {
                return contactway;
            }

            public void setContactway(String contactway) {
                this.contactway = contactway;
            }

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
                this.creator = creator;
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

            public String getPhonenum() {
                return phonenum;
            }

            public void setPhonenum(String phonenum) {
                this.phonenum = phonenum;
            }

            public String getShortnum() {
                return shortnum;
            }

            public void setShortnum(String shortnum) {
                this.shortnum = shortnum;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
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
