package com.hamitao.requestframe.entity;

/**
 * @data on 2018/6/11 14:10
 * @describe:
 */

public class RequestAddPhotoInfo {
    private String comment;
    private String ownerid;
    private String photoname;
    private String photourl;

    public RequestAddPhotoInfo(String comment, String ownerid, String photoname, String photourl) {
        this.comment = comment;
        this.ownerid = ownerid;
        this.photoname = photoname;
        this.photourl = photourl;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public void setPhotoname(String photoname) {
        this.photoname = photoname;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getComment() {

        return comment;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public String getPhotoname() {
        return photoname;
    }

    public String getPhotourl() {
        return photourl;
    }
}
