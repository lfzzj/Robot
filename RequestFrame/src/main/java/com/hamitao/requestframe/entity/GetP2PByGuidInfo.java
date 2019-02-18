package com.hamitao.requestframe.entity;

import java.io.Serializable;

/**
 * @data on 2018/7/11 16:12
 * @describe:
 */

public class GetP2PByGuidInfo implements Serializable {


    /**
     * guid : 359850053494840
     * p2p_id : 00002
     * passwd : 43083849
     * token : 91910872
     * status : 1
     * viptime :
     * address : 139.196.197.168:7781
     */

    private String guid;
    private String p2p_id;
    private String passwd;
    private String token;
    private int status;
    private String viptime;
    private String address;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getP2p_id() {
        return p2p_id;
    }

    public void setP2p_id(String p2p_id) {
        this.p2p_id = p2p_id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getViptime() {
        return viptime;
    }

    public void setViptime(String viptime) {
        this.viptime = viptime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
