package com.hamitao.kids.mvp.callback;

/**
 * @data on 2018/3/30 10:15
 * @describe: 极光im登陆回调
 */

public interface OnJMLoginCallBack {
    /**
     * 登陆成功
     */
    void onLoginSuccess();

    /**
     * 登陆失败
     */
    void onLoginFail();
}
