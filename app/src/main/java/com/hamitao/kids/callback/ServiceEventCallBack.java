package com.hamitao.kids.callback;

import com.hamitao.requestframe.entity.ParseChineseInfo.ResponseDataObjBean.NlpParseAnswersBean;

/**
 * @data on 2018/5/30 15:33
 * @describe: 服务器事件的回调
 */

public interface ServiceEventCallBack {
    void onServicePlayContentListener(NlpParseAnswersBean info);

    //聊天
    void onServiceImChatListener(String who);

    //视频聊天
    void onServiceVideoChatListener(String who);
}
