package com.hamitao.kids.manager.wakeup.callback;

import com.hamitao.framework.model.MediaInfo;
import com.hamitao.requestframe.entity.ParseChineseInfo;

import java.util.List;

public interface ServerCallBack {
    /**
     * 内容
     * @param info
     */
    void onPlayContent(List<MediaInfo> info);

    /**
     * IM 聊天
     * @param whoName
     */
    void onImChat(String whoName);

    /**
     * 视频聊天
     * @param whoName
     */
    void onVideoChat(String whoName);
}
