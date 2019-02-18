package com.hamitao.kids.turing.manager;

import com.turing.semantic.SemanticManager;
import com.turing.semantic.entity.AppAndContactsBean;
import com.turing.semantic.listener.OnHttpRequestListener;

/**
 * @data on 2018/3/10 11:45
 * @describe: 语义解析说明(实现智能语音对话)
 */

public class TuringSemantic {

    private static SemanticManager semanticManager = SemanticManager.getInstance();

    /**
     * 请求接口
     *
     * @param content          请求内容
     * @param semanticListener 请求回调
     */
    public static void requestSemantic(String content, OnHttpRequestListener semanticListener) {
        semanticManager.requestSemantic(content, semanticListener);
    }

    /**
     * 打招呼请求
     *
     * @param semanticListener
     */
    public static void requestFirstConversion(OnHttpRequestListener semanticListener) {
        semanticManager.requestFirstConversion(semanticListener);
    }

    /**
     * 主动交互请求
     *
     * @param onHttpRequestListener
     */
    public static void requestAutoConversion(OnHttpRequestListener onHttpRequestListener) {
        semanticManager.requestAutoConversion(onHttpRequestListener);
    }

    /**
     * 上传通讯录与APP列表
     *
     * @param appAndContactsBean
     * @param semanticListener
     */
    public static void uploadAppsAndContacts(AppAndContactsBean appAndContactsBean, OnHttpRequestListener semanticListener) {
        semanticManager.uploadAppsAndContacts(appAndContactsBean, semanticListener);
    }

    /**
     * 设置连接超时时间
     *
     * @param timeout 默认超时时间为10秒,参数单位为毫秒
     */
    public static void setConnectTimeout(long timeout) {
        semanticManager.setConnectTimeout(timeout);
    }

    /**
     * 设置从云端返回数据读取的超时时间
     *
     * @param readTimeout 默认超时时间为10秒,参数单位为毫秒
     */
    public static void setReadTimeout(long readTimeout) {
        semanticManager.setReadTimeout(readTimeout);
    }

    /**
     * 设置发送数据到云端写入的超时时间
     *
     * @param writeTimeout 默认超时时间为10秒,参数单位为毫秒
     */
    public static void setWriteTimeout(long writeTimeout) {
        semanticManager.setWriteTimeout(writeTimeout);
    }

    /**
     * 取消网络请求
     */
    public static void cancelRequest(){
        semanticManager.cancelRequest();
    }
}
