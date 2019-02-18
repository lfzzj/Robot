package com.hamitao.requestframe.view;

import com.hamitao.requestframe.entity.CreateDeviceInfo;

/**
 * @data on 2018/3/21 11:37
 * @describe:
 */

public interface CreateDeviceView extends View{
    void onSuccess(CreateDeviceInfo info);
}