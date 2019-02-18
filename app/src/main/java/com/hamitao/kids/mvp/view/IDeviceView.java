package com.hamitao.kids.mvp.view;

import com.hamitao.requestframe.entity.CreateDeviceInfo;

/**
 * @data on 2018/5/29 17:57
 * @describe:
 */

public interface IDeviceView {
    void OnCreateDeviceSuccessListener(CreateDeviceInfo info);

    void OnCreateDeviceErrorListener();

    void OnJMRegisterResultListener(boolean isSuccess, String userName, String password);

    void onLoginResult(boolean isSuccess);

}
