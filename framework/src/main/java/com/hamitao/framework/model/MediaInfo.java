package com.hamitao.framework.model;

import java.io.Serializable;

/**
 * @data on 2018/4/12 13:58
 * @describe: 媒体内容
 */

public class MediaInfo implements Serializable {
    private String mediaContent;//内容
    private String mediaType;//内容格式
    private String mediaTitle;//内容名称


    public MediaInfo(String mediaContent, String mediaType, String mediaTitle) {
        this.mediaContent = mediaContent;
        this.mediaType = mediaType;
        this.mediaTitle = mediaTitle;
    }

    public void setMediaContent(String mediaContent) {
        this.mediaContent = mediaContent;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public String getMediaContent() {

        return mediaContent;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }
}
