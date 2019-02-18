package com.hamitao.kids.mvp.ipresenter;

import com.hamitao.kids.model.AlbumInfo;
import com.hamitao.kids.oss.OSSManager;
import com.hamitao.requestframe.entity.DeviceInfo;
import com.hamitao.requestframe.entity.RequestQueryContentInfo;

/**
 * @data on 2018/5/29 17:56
 * @describe: 设备相关
 */

public interface IDevicePresenter {

    void createDevice(String deviceId);

    void setJmAlias(String alias);

    void jMRegister(String userName, String password);

    void jManagerLogin(String userName, String password);

    void updateUserAvatar();

    void reportDeviceInfo(DeviceInfo deviceInfo);

    void queryRelation(String myid);

    void queryContact(String ownerid);

    void queryPhoto(String ownerid);

    void addPhoto(String ownerid, String photoname, String photoUrl);

    void delPhoto(String ownerid, AlbumInfo albumInfo);

    void getContentTree(String scenario);

    void queryContent(RequestQueryContentInfo requestQueryContentInfo);

    void queryNfcById(String content);

    void getP2PByGuid(String guid, String imei, String wifimac, String groupid);

    void releaseP2PByGuid(String guid, String token);

    void setNickName(String deviceNickName);
}
