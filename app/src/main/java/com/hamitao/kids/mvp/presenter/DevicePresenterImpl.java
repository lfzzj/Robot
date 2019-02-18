package com.hamitao.kids.mvp.presenter;

import android.content.Context;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.FileUtil;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.model.AlbumInfo;
import com.hamitao.kids.mvp.callback.OnJMLoginCallBack;
import com.hamitao.kids.mvp.imodel.IDeviceModel;
import com.hamitao.kids.mvp.ipresenter.IDevicePresenter;
import com.hamitao.kids.mvp.model.DeviceModelImpl;
import com.hamitao.kids.mvp.view.IDeviceView;
import com.hamitao.kids.oss.OSSManager;
import com.hamitao.requestframe.entity.CommonInfo;
import com.hamitao.requestframe.entity.CreateDeviceInfo;
import com.hamitao.requestframe.entity.DeviceInfo;
import com.hamitao.requestframe.entity.GetContentTreeInfo;
import com.hamitao.requestframe.entity.GetP2PByGuidInfo;
import com.hamitao.requestframe.entity.QueryContactInfo;
import com.hamitao.requestframe.entity.QueryContentInfo;
import com.hamitao.requestframe.entity.QueryNfcByIdInfo;
import com.hamitao.requestframe.entity.QueryPhotoInfo;
import com.hamitao.requestframe.entity.QueryRelationInfo;
import com.hamitao.requestframe.entity.RequestQueryContentInfo;
import com.hamitao.requestframe.view.CommonInfoView;
import com.hamitao.requestframe.view.CreateDeviceView;
import com.hamitao.requestframe.view.DelPhotoView;
import com.hamitao.requestframe.view.GetContentTreeView;
import com.hamitao.requestframe.view.GetP2PByGuidView;
import com.hamitao.requestframe.view.QueryContactView;
import com.hamitao.requestframe.view.QueryContentView;
import com.hamitao.requestframe.view.QueryNfcByIdView;
import com.hamitao.requestframe.view.QueryPhotoView;
import com.hamitao.requestframe.view.QueryRelationView;
import com.hamitao.requestframe.view.ReleaseP2PbyGuidView;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * @data on 2018/5/29 17:56
 * @describe:
 */

public class DevicePresenterImpl implements IDevicePresenter {
    private static String TAG = "DecicePresenterImpl";
    private IDeviceView mDeviceView;
    private QueryRelationView queryRelationView;
    private QueryContactView queryContactView;
    private QueryPhotoView queryPhotoView;
    private GetContentTreeView getContentTreeView;
    private QueryContentView queryContentView;
    private QueryNfcByIdView queryNfcByIdView;
    private GetP2PByGuidView getP2PByGuidView;

    private DelPhotoView delPhotoView;

    private IDeviceModel deviceModel;

    private Context mContext;

    public DevicePresenterImpl(Context context, IDeviceView iMainView, QueryContactView queryContactView) {
        deviceModel = new DeviceModelImpl(context);
        this.mDeviceView = iMainView;
        this.queryContactView = queryContactView;
    }

    public DevicePresenterImpl(Context context, QueryContactView queryContactView) {
        deviceModel = new DeviceModelImpl(context);
        this.queryContactView = queryContactView;
    }

    //关系查询
    public DevicePresenterImpl(Context context, QueryRelationView queryRelationView) {
        deviceModel = new DeviceModelImpl(context);
        this.queryRelationView = queryRelationView;
    }

    public DevicePresenterImpl(Context context, QueryPhotoView queryPhotoView, DelPhotoView delPhotoView) {
        deviceModel = new DeviceModelImpl(context);
        this.queryPhotoView = queryPhotoView;
        this.delPhotoView = delPhotoView;
    }

    public DevicePresenterImpl(Context context) {
        this.mContext = context;
        deviceModel = new DeviceModelImpl(context);
    }

    public DevicePresenterImpl(Context context, QueryContentView queryContentView, QueryNfcByIdView queryNfcByIdView) {
        this.mContext = context;
        deviceModel = new DeviceModelImpl(context);
        this.queryContentView = queryContentView;
        this.queryNfcByIdView = queryNfcByIdView;
    }

    public DevicePresenterImpl(Context context, GetContentTreeView getContentTreeView) {
        deviceModel = new DeviceModelImpl(context);
        this.getContentTreeView = getContentTreeView;
    }

    public DevicePresenterImpl(Context context, QueryContentView queryContentView, QueryNfcByIdView queryNfcByIdView, QueryContactView queryContactView) {
        deviceModel = new DeviceModelImpl(context);
        this.queryContentView = queryContentView;
        this.queryContactView = queryContactView;
        this.queryNfcByIdView = queryNfcByIdView;
    }

    public DevicePresenterImpl(Context context, GetP2PByGuidView getP2PByGuidView) {
        deviceModel = new DeviceModelImpl(context);
        this.getP2PByGuidView = getP2PByGuidView;
    }


    @Override
    public void createDevice(String deviceId) {
        deviceModel.createDevice(deviceId, new CreateDeviceView() {
            @Override
            public void onSuccess(CreateDeviceInfo info) {
                Logger.d(TAG, "设备注册结果 ：" + info.getResult());
                if (BaseConstant.SUCCESS.equals(info.getResult())) {
                    if (mDeviceView != null) {
                        mDeviceView.OnCreateDeviceSuccessListener(info);
                    }
                }
            }

            @Override
            public void onError(String result) {
                Logger.d(TAG, "createDevice result=" + result);
                if (mDeviceView != null) {
                    mDeviceView.OnCreateDeviceErrorListener();
                }
            }
        });
    }

    @Override
    public void setJmAlias(String alias) {
        deviceModel.setJmAlias(alias);
    }

    @Override
    public void jMRegister(String userName, String password) {
        deviceModel.jMRegister(userName, password, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String registerDesc) {
                Logger.d(TAG, "responseCode = " + responseCode + "    registerDesc=" + registerDesc);
                mDeviceView.OnJMRegisterResultListener(responseCode == 0 ? true : false, userName, password);
            }
        });
    }

    @Override
    public void jManagerLogin(String userName, String password) {
        deviceModel.jMLogin(userName, password, new OnJMLoginCallBack() {
            @Override
            public void onLoginSuccess() {
                //登陆成功  iMainView通知activity进行相应的操作
                UserInfo userInfo = JMessageClient.getMyInfo();
                mDeviceView.onLoginResult(true);
            }

            @Override
            public void onLoginFail() {
                //登陆失败
                mDeviceView.onLoginResult(false);

            }
        });
    }

    @Override
    public void updateUserAvatar() {
        FileUtil fileUtil = new FileUtil();

        deviceModel.updateUserAvatar(fileUtil, new BasicCallback() {
            @Override
            public void gotResult(int i, String result) {
                Logger.d(TAG, "jm头像上传结果   i==" + i + "    s=" + result);
                if (BaseConstant.SUCCESS.equalsIgnoreCase(result)) {
                    fileUtil.deleteFile(BaseConstant.path_jmheadimg);
                }
            }
        });
    }

    @Override
    public void reportDeviceInfo(DeviceInfo deviceInfo) {
        deviceModel.reportDeviceInfo(deviceInfo, new CommonInfoView() {
            @Override
            public void onSuccess(CommonInfo info) {
                Logger.d(TAG, "上报成功 info=" + info.getResult());

            }

            @Override
            public void onError(String result) {
                Logger.d(TAG, "上报失败");
            }
        });
    }

    @Override
    public void queryRelation(String myid) {
        deviceModel.queryRelation(myid, new QueryRelationView() {
            @Override
            public void onSuccess(QueryRelationInfo info) {
                if (queryRelationView != null) {
                    queryRelationView.onSuccess(info);
                }
            }

            @Override
            public void onError(String result) {
                if (queryRelationView != null) {
                    queryRelationView.onError(result);
                }
            }
        });
    }

    @Override
    public void queryContact(String ownerid) {
        deviceModel.queryContact(ownerid, new QueryContactView() {
            @Override
            public void onSuccess(QueryContactInfo info) {
                if (queryContactView != null) {
                    queryContactView.onSuccess(info);
                }
            }

            @Override
            public void onError(String result) {
                if (queryContactView != null) {
                    queryContactView.onError(result);
                }
            }
        });
    }

    @Override
    public void queryPhoto(String ownerid) {
        deviceModel.queryPhoto(ownerid, new QueryPhotoView() {
            @Override
            public void onSuccess(QueryPhotoInfo info) {
                queryPhotoView.onSuccess(info);
            }

            @Override
            public void onError(String result) {

            }
        });
    }

    @Override
    public void addPhoto(String ownerid, String photoname, String photoUrl) {
        deviceModel.addPhoto(ownerid, photoname, photoUrl, new CommonInfoView() {
            @Override
            public void onSuccess(CommonInfo info) {
                Logger.d(TAG, "添加成功");
            }

            @Override
            public void onError(String result) {
                Logger.d(TAG, "添加失败");
            }
        });
    }

    @Override
    public void delPhoto(String ownerid, AlbumInfo albumInfo) {
        deviceModel.delPhoto(ownerid, albumInfo, new CommonInfoView() {
            @Override
            public void onSuccess(CommonInfo info) {
                if (delPhotoView != null) {
                    delPhotoView.onSuccess(albumInfo.getPhotourl());
                }
            }

            @Override
            public void onError(String result) {
                if (delPhotoView != null) {
                    delPhotoView.onError(result);
                }
            }
        });
    }

    @Override
    public void getContentTree(String scenario) {
        deviceModel.getContentTree(scenario, new GetContentTreeView() {
            @Override
            public void onSuccess(GetContentTreeInfo info) {
                if (getContentTreeView != null) {
                    getContentTreeView.onSuccess(info);
                }
            }

            @Override
            public void onError(String result) {
                getContentTreeView.onError(result);
            }
        });
    }

    @Override
    public void queryContent(RequestQueryContentInfo requestQueryContentInfo) {
        deviceModel.queryContent(requestQueryContentInfo, new QueryContentView() {
            @Override
            public void onSuccess(QueryContentInfo info) {
                if (queryContentView != null) {
                    queryContentView.onSuccess(info);
                }
            }

            @Override
            public void onError(String result) {
                if (queryContentView != null) {
                    queryContentView.onError(result);
                }
            }
        });
    }

    @Override
    public void queryNfcById(String content) {
        deviceModel.queryNfcById(content, new QueryNfcByIdView() {
            @Override
            public void onSuccess(QueryNfcByIdInfo querynfcbyidInfo) {
                if (queryNfcByIdView != null) {
                    queryNfcByIdView.onSuccess(querynfcbyidInfo);
                }
            }

            @Override
            public void onError(String result) {
                if (queryNfcByIdView != null) {
                    queryNfcByIdView.onError(result);
                }
            }
        });
    }

    @Override
    public void getP2PByGuid(String guid, String imei, String wifimac, String groupid) {
        deviceModel.getP2PByGuid(guid, imei, wifimac, groupid, new GetP2PByGuidView() {
            @Override
            public void onSuccess(GetP2PByGuidInfo mGetP2PByGuidInfo) {
                if (getP2PByGuidView != null) {
                    getP2PByGuidView.onSuccess(mGetP2PByGuidInfo);
                }
            }

            @Override
            public void onError(String result) {
                if (getP2PByGuidView != null) {
                    getP2PByGuidView.onError(result);
                }
            }
        });
    }

    @Override
    public void releaseP2PByGuid(String guid, String token) {
        deviceModel.releaseP2PByGuid(guid, token, new ReleaseP2PbyGuidView() {
            @Override
            public void onSuccess() {
                Logger.d(TAG, "视频聊天资源释放成功");
            }

            @Override
            public void onError(String result) {
                Logger.d(TAG, "视频聊天资源释放失败");
            }
        });
    }

    @Override
    public void setNickName(String deviceNickName) {
        deviceModel.setNickName(deviceNickName, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (0 == i) {
                    Logger.d(TAG, "设备昵称设置成功");
                } else {
                    Logger.d(TAG, "设备昵称设置失败");
                }
            }
        });
    }

}
