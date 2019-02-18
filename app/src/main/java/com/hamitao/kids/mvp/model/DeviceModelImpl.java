package com.hamitao.kids.mvp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.FileUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.model.AlbumInfo;
import com.hamitao.kids.mvp.callback.OnJMLoginCallBack;
import com.hamitao.kids.mvp.imodel.IDeviceModel;
import com.hamitao.kids.oss.OSSManager;
import com.hamitao.requestframe.entity.DeviceInfo;
import com.hamitao.requestframe.entity.RequestAddPhotoInfo;
import com.hamitao.requestframe.entity.RequestDeviceInfo;
import com.hamitao.requestframe.entity.RequestQueryContentInfo;
import com.hamitao.requestframe.presenter.AddPhotoPresenter;
import com.hamitao.requestframe.presenter.CreateDevicePresenter;
import com.hamitao.requestframe.presenter.DeletePhotoPresenter;
import com.hamitao.requestframe.presenter.GetContentTreePresenter;
import com.hamitao.requestframe.presenter.GetP2PByGuidPresenter;
import com.hamitao.requestframe.presenter.QueryContactPresenter;
import com.hamitao.requestframe.presenter.QueryContentPresenter;
import com.hamitao.requestframe.presenter.QueryNfcByIdPresenter;
import com.hamitao.requestframe.presenter.QueryPhotoPresenter;
import com.hamitao.requestframe.presenter.QueryRelationPresenter;
import com.hamitao.requestframe.presenter.ReleaseP2PbyGuidPresenter;
import com.hamitao.requestframe.presenter.ReportInfoPresenter;
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

import java.io.File;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * @data on 2018/5/29 17:53
 * @describe: 设备初始化:设备注册,极光注册/登陆
 */

public class DeviceModelImpl implements IDeviceModel {
    private static String TAG = "DeviceModelImpl";
    private Context mContext;

    public DeviceModelImpl(Context context) {
        mContext = context;
    }

    @Override
    public void createDevice(String deviceId, CreateDeviceView view) {
        CreateDevicePresenter presenter = new CreateDevicePresenter(mContext, view);
        presenter.requestData(new RequestDeviceInfo(BaseConstant.DAGCLUSTER, deviceId, BaseConstant.TERMINALTYPE,mContext.getString(R.string.vendor)));
    }

    @Override
    public void setJmAlias(String alias) {
        JPushInterface.setAlias(mContext, 0, alias);
    }

    @Override
    public void jMRegister(String userName, String password, BasicCallback basicCallback) {
        JMessageClient.register(userName, password, basicCallback);
    }

    @Override
    public void jMLogin(String userName, String password, OnJMLoginCallBack jmLoginCallBack) {
        JMessageClient.login(userName, password, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String LoginDesc) {
                if (responseCode == 0) {
                    jmLoginCallBack.onLoginSuccess();
                } else {
                    jmLoginCallBack.onLoginFail();
                }
            }
        });
    }

    @Override
    public void reportDeviceInfo(DeviceInfo deviceInfo, CommonInfoView reportInfoView) {
        ReportInfoPresenter reportInfoPresenter = new ReportInfoPresenter(mContext, reportInfoView);
        reportInfoPresenter.requestData(deviceInfo);
    }

    @Override
    public void queryRelation(String myid, QueryRelationView queryRelationView) {
        QueryRelationPresenter queryRelationPresenter = new QueryRelationPresenter(mContext, queryRelationView);
        queryRelationPresenter.requestData(myid);
    }

    @Override
    public void queryContact(String ownerid, QueryContactView queryContactView) {
        QueryContactPresenter queryContactPresenter = new QueryContactPresenter(mContext, queryContactView);
        queryContactPresenter.requestData(ownerid);

    }

    @Override
    public void queryPhoto(String ownerid, QueryPhotoView queryPhotoView) {
        QueryPhotoPresenter queryPhotoPresenter = new QueryPhotoPresenter(mContext, queryPhotoView);
        queryPhotoPresenter.requestData(ownerid);
    }

    @Override
    public void addPhoto(String ownerid, String photoname, String photoUrl, CommonInfoView commonInfoView) {
        AddPhotoPresenter addPhotoPresenter = new AddPhotoPresenter(mContext, commonInfoView);
        RequestAddPhotoInfo requestAddPhotoInfo = new RequestAddPhotoInfo("comment", ownerid, photoname, photoUrl);
        addPhotoPresenter.requestData(requestAddPhotoInfo);
    }

    @Override
    public void delPhoto(String ownerid, AlbumInfo albumInfo, CommonInfoView commonInfoView) {
        DeletePhotoPresenter deletePhotoPresenter = new DeletePhotoPresenter(mContext, commonInfoView);
        deletePhotoPresenter.requestData(ownerid, albumInfo.getPhotoId());
    }

    @Override
    public void getContentTree(String scenario, GetContentTreeView getContentTreeView) {
        GetContentTreePresenter presenter = new GetContentTreePresenter(mContext, getContentTreeView);
        presenter.requestData(scenario, "tree");
    }

    @Override
    public void queryContent(RequestQueryContentInfo requestQueryContentInfo, QueryContentView queryContentView) {
        QueryContentPresenter presenter = new QueryContentPresenter(mContext, queryContentView);
        presenter.requestData("5", requestQueryContentInfo);
    }

    @Override
    public void updateUserAvatar(FileUtil fileUtil, BasicCallback basicCallback) {
        Bitmap mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.image_head);
        File file = fileUtil.saveBitmapFile(mBitmap, fileUtil.getSdPath(), BaseConstant.path_jmheadimg);
        JMessageClient.updateUserAvatar(file, basicCallback);
    }

    @Override
    public void queryNfcById(String content, QueryNfcByIdView queryNfcByIdView) {
        QueryNfcByIdPresenter presenter = new QueryNfcByIdPresenter(mContext, queryNfcByIdView);
        presenter.requestData(content);
    }

    @Override
    public void getP2PByGuid(String guid, String imei, String wifimac, String groupid, GetP2PByGuidView getP2PByGuidView) {
        GetP2PByGuidPresenter p2PByGuidPresenter = new GetP2PByGuidPresenter(mContext, getP2PByGuidView);
        p2PByGuidPresenter.requestData(guid, imei, wifimac, groupid);
    }

    @Override
    public void releaseP2PByGuid(String guid, String token, ReleaseP2PbyGuidView releaseP2PbyGuidView) {
        ReleaseP2PbyGuidPresenter presenter = new ReleaseP2PbyGuidPresenter(mContext, releaseP2PbyGuidView);
        presenter.requestData(guid, token);
    }

    @Override
    public void setNickName(String deviceNickName, BasicCallback basicCallback) {
        UserInfo userInfo = JMessageClient.getMyInfo();
        userInfo.setNickname(deviceNickName);
        JMessageClient.updateMyInfo(UserInfo.Field.nickname, userInfo, basicCallback);
    }
}
