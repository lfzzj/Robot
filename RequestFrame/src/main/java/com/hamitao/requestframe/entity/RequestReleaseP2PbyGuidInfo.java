package com.hamitao.requestframe.entity;

public class RequestReleaseP2PbyGuidInfo {
    private String guid;
    private String token;
    public RequestReleaseP2PbyGuidInfo(String guid, String token) {

        this.guid = guid;
        this.token = token;
    }
    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGuid() {

        return guid;
    }

    public String getToken() {
        return token;
    }


}
