package com.peergine.extvideo.lib;

/**
 * Copyright (C) 2014-2017, Peergine, All rights reserved.
 * www.peergine.com, www.pptun.com
 * com.peergine.extvideo.lib
 *
 * @author ctkj
 */

public interface OnEvent {
    /**
     * 对端调用sendObjPeer产生的事件
     *
     * @param sObjPeer 对端的节点对象名
     * @param sParam   sendObjPeer 传入的参数，保留参数
     */
    void onGetObjPeer(String sObjPeer, String sParam);

    /**
     * 调用sendObjPeer后产生的服务器回复结果事件
     * @param uErr 错误码
     * @param sParam 自定义参数
     */
    void onGetObjPeerReply(int uErr, String sParam);

    /**
     * 接收sendObjPeerNotify 发送的对端的节点对象的值。
     * @param sObjPeer 节点对象名
     * @param sParam 自定义参数
     */
    void onGetObjPeerNotify(String sObjPeer, String sParam);

    /**
     * Video 对象同步
     * @param sObj 对象名称
     * @param sAct 0 是同步，1是去同步
     * @param sObjPeer 节点对象名称
     * @return 错误码 正常返回PG_ERR_Normal
     */
    int onVideoSync(String sObj, String sAct, String sObjPeer);


    /**
     * 收到视频请求
     * @param sObj 视频对象
     * @param uHandle 视频请求句柄
     * @param sObjPeer 对端的对象名称
     * @return 错误码 @link pgLibError， -1表示异步调用videoHandle 应答。 正常 返回PG_ERR_Normal
     */
    int onVideoStart(String sObj, int uHandle, String sObjPeer);

    /**
     * 收到videoStart视频请求的结果。
     * @param sObj 视频对象名称
     * @param uErr 错误码， PG_ERR_Normal 表示打开成功
     * @return 错误码，正常返回 PG_ERR_Normal
     */
    int onVideoStartReply(String sObj, int uErr);

    /**
     * 收到视频结束通知
     * @param sObj 视频对象名称
     * @param sObjPeer 对端节点对象名称
     * @return 错误码 ，正常返回 PG_ERR_Normal
     */
    int onVideoStop(String sObj, String sObjPeer);

    /**
     * 视频传输状态
     * @param sObj 对象名称
     * @param sData 进度状态信息
     */
    void onVideoFrameStat(String sObj, String sData);

    /**
     * 音频对象同步
     * @param sObj 对象名称
     * @param sAct 1同步，0 去同步
     */
    int onAudioSync(String sObj, String sAct);


    /**
     * 音频对象 请求打开音频
     * @param sObj 音频对象名称
     * @param sObjPeer 对端对象名称
     * @return 错误码
     */
    int onAudioStart(String sObj, String sObjPeer);

    /**
     * 音频打开请求结果
     * @param sObj 音频对象名称
     * @param uErr 错误码
     * @return 返回错误码
     */
    int onAudioStartRelay(String sObj, int uErr);

    /**
     * 音频已经被关闭
     * @param sObjPeer 节点对象名称
     * @return 错误码
     */
    int onAudioStop(String sObjPeer);
}
