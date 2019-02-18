package com.hamitao.kids.model;

import java.io.Serializable;

/**
 * @data on 2018/6/6 18:54
 * @describe:
 */

public class AlbumInfo implements Serializable {
    private String album;
    private String Photourl;
    private String photoId;
    private boolean isEditState;//是否是编辑状态
    private boolean isDel;//是否选了删除

    public AlbumInfo(String album, String photourl, String photoId, boolean isEditState, boolean isDel) {
        this.album = album;
        Photourl = photourl;
        this.photoId = photoId;
        this.isEditState = isEditState;
        this.isDel = isDel;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setPhotourl(String photourl) {
        Photourl = photourl;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public void setEditState(boolean editState) {
        isEditState = editState;
    }

    public void setDel(boolean del) {
        isDel = del;
    }

    public String getAlbum() {

        return album;
    }

    public String getPhotourl() {
        return Photourl;
    }

    public String getPhotoId() {
        return photoId;
    }

    public boolean isEditState() {
        return isEditState;
    }

    public boolean isDel() {
        return isDel;
    }
}
