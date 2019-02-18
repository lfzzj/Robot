package com.hamitao.requestframe.view;

import com.hamitao.requestframe.entity.PushMsgInfo;

/**
 * @data on 2018/4/8 18:34
 * @describe:
 */

public interface PushMsgView extends View{
    void onSuccess(PushMsgInfo info);
}
