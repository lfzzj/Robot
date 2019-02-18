package com.hamitao.kids.mvp.imodel;

import com.hamitao.framework.utils.FileUtil;
import com.hamitao.kids.model.AlbumInfo;
import com.hamitao.kids.mvp.callback.OnJMLoginCallBack;
import com.hamitao.kids.oss.OSSManager;
import com.hamitao.requestframe.entity.DeviceInfo;
import com.hamitao.requestframe.entity.RequestQueryContentInfo;
import com.hamitao.requestframe.view.CommonInfoView;
import com.hamitao.requestframe.view.CreateDeviceView;
import com.hamitao.requestframe.view.GetContentTreeView;
import com.hamitao.requestframe.view.GetP2PByGuidView;
import com.hamitao.requestframe.view.QueryContactView;
import com.hamitao.requestframe.view.QueryContentView;
import com.hamitao.requestframe.view.QueryNfcByIdView;
import com.hamitao.requestframe.view.QueryPhotoView;
import com.hamitao.requestframe.view.QueryRelationView;
import com.hamitao.requestframe.view.ReleaseP2PbyGuidView;

import cn.jpush.im.api.BasicCallback;

/**
 * @data on 2018/5/29 17:46
 * @describe: 设备信息
 */

public interface IDeviceModel {
    /**
     * 设备注册
     */
    void createDevice(String deviceId, CreateDeviceView view);

    /**
     * 设置极光推送别名
     *
     * @param alias
     */
    void setJmAlias(String alias);

    /**
     * 极光IM注册
     *
     * @param userName
     * @param password
     */
    void jMRegister(String userName, String password, BasicCallback basicCallback);

    /**
     * 极光IM登陆
     */
    void jMLogin(String userName, String password, OnJMLoginCallBack jmLoginCallBack);

    /**
     * 上报设备信息
     */
    void reportDeviceInfo(DeviceInfo deviceInfo, CommonInfoView reportInfoView);


    /**
     * 关系查询
     *
     * @param myid
     * @param queryRelationView
     */
    void queryRelation(String myid, QueryRelationView queryRelationView);

    /**
     * 联系人查询
     *
     * @param ownerid
     * @param queryContactView
     */
    void queryContact(String ownerid, QueryContactView queryContactView);

    /**
     * 查询照片
     *
     * @param ownerid
     * @param queryPhotoView
     */
    void queryPhoto(String ownerid, QueryPhotoView queryPhotoView);

    /**
     * 添加照片
     *
     * @param ownerid
     * @param photoname
     * @param photoUrl
     * @param commonInfoView
     */
    void addPhoto(String ownerid, String photoname, String photoUrl, CommonInfoView commonInfoView);

    /**
     * 删除照片
     *
     * @param ownerid
     * @param albumInfo
     * @param commonInfoView
     */
    void delPhoto(String ownerid, AlbumInfo albumInfo, CommonInfoView commonInfoView);

    /**
     * 获取内容树(层级结构)
     *
     * @param scenario
     * @param getContentTreeView
     */
    void getContentTree(String scenario, GetContentTreeView getContentTreeView);

    /**
     * 查询内容
     *
     * @param requestQueryContentInfo
     * @param queryContentView
     */
    void queryContent(RequestQueryContentInfo requestQueryContentInfo, QueryContentView queryContentView);

    /**
     * 创建头像
     *
     * @param basicCallback
     */
    void updateUserAvatar(FileUtil fileUtil, BasicCallback basicCallback);

    /**
     * 根据条码查询卡的内容
     *
     * @param content
     * @param queryNfcByIdView
     */
    void queryNfcById(String content, QueryNfcByIdView queryNfcByIdView);

    /**
     * 获取p2p的guid
     *
     * @param guid
     * @param imei
     * @param wifimac
     * @param groupid
     * @param getP2PByGuidView
     */
    void getP2PByGuid(String guid, String imei, String wifimac, String groupid, GetP2PByGuidView getP2PByGuidView);

    /**
     * 释放
     *
     * @param guid
     * @param token
     */
    void releaseP2PByGuid(String guid, String token, ReleaseP2PbyGuidView releaseP2PbyGuidView);

    /**
     * 设置极光昵称
     * @param deviceNickName
     * @param basicCallback
     */
    void setNickName(String deviceNickName, BasicCallback basicCallback);
}
