package com.peergine.extvideo.lib;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceView;

import com.peergine.android.livemulti.pgLibLive;
import com.peergine.android.livemulti.pgLibLiveMultiCapture;
import com.peergine.android.livemulti.pgLibLiveMultiError;
import com.peergine.android.livemulti.pgLibLiveMultiRender;
import com.peergine.plugin.lib.pgLibJNINode;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.peergine.pgLibError.*;
import static com.peergine.pgLibNode.*;
import static com.peergine.pgLibView.GetNodeByView;

/**
 * @author ctkj
 */

public class ExtVideo {

    private static final String LIB_VER = "v2.4";

    /**
     * 呼叫信号
     */
    public static final String GET_OBJPEER = "VCMD_GetObjPeer:";
    /**
     * 呼叫信号
     */
    public static final String GET_OBJPEER_NOTIFY = "VCMD_GetObjPeerNotify:";

    /**
     * 中间件方法，打开视频
     */
    private static final int PG_METH_VIDEO_START = 32;
    /**
     * 中间件方法，关闭视频
     */
    private static final int PG_METH_VIDEO_STOP = 33;


    /**
     *
     */
    private static final String PARAM_EXT_SDK_VIDEO_OPEN = "EXT_SDK_VIDEO_OPEN";
    /**
     *
     */
    private static final String PARAM_EXT_SDK_AUDIO_OPEN = "EXT_SDK_AUDIO_OPEN";
    /**
     * P2P服务器对象名
     */
    private final static String mObjSvr = "pgConnectSvr";
    /**
     * 预览对象名
     */
    private final static String mObjPrvw = "VPrvw";


    private boolean isStateInit = false;
    private boolean isStatePrvw = false;


    /**
     * 当前初始化了的Node
     */
    private pgLibJNINode mNode = null;

    /**
     * 视频对象
     */
    private String sObjVSelfHash = "";

    /**
     *
     */
    private OnEvent mOnEvent = null;

    /**
     * 随机数产生对象
     */
    private Random mRandom = new Random();
    private String mObjSelf = "";
    private String mVideoParam = "";
    private String mAudioParam = "";


    private String sObjASelfHash = "";

    /**
     * 获取版本号
     *
     * @return Version
     */
    public String version() {
        return LIB_VER;
    }

    /**
     * 获取Ext模块的钩子函数
     *
     * @return 钩子对象
     */
    public Object getHook() {
        return mHook;
    }

    /**
     * 设置Event
     */
    public void setOnEvent(OnEvent event) {
        mOnEvent = event;
    }


    /**
     * 采集端初始化
     *
     * @param node        node对象
     * @param sObjSelf    本节点对象名
     * @param sVideoParam 视频参数
     * @param sAudioParam 音频参数
     * @return View对象，采集端返回播放端视频播放View，播放端返回预览View
     */
    public int initialize(pgLibJNINode node, String sObjSelf, String sVideoParam, String sAudioParam) {
        if (node == null || sObjSelf == null || sObjSelf.length() <= 5) {
            outString("initialize param error , live = " + node + " sObjSelf = " + sObjSelf);
            return PG_ERR_BadParam;
        }
        if (!isStateInit) {
            mNode = node;
            mObjSelf = sObjSelf;
            mVideoParam = sVideoParam;
            mAudioParam = sAudioParam;
            String sPeer = ((sObjSelf.length() > 100) ? sObjSelf.substring(5, 100) : sObjSelf.substring(5));
            int iFlagSelfV = (0x10000 | 0x4 | 0x10 | 0x20);
            int iFlagSelfA = (0x10000 | 0x4 );
            if (!_TimerInit()) {
                outString(" timer init false !");
                mNode = null;
                return PG_ERR_System;
            }

            sObjVSelfHash = "EXT_VIDEO_" + sPeer;
            if (!mNode.ObjectAdd(sObjVSelfHash, "PG_CLASS_Video", "", iFlagSelfV)) {
                Log.d("pgExtVideo", "Ext Video Object Add failed");
                return PG_ERR_System;
            }
            sObjASelfHash = "EXT_AUDIO_" + sPeer;
            if (!mNode.ObjectAdd(sObjASelfHash, "PG_CLASS_Audio", "", iFlagSelfA)) {
                Log.d("pgExtVideo", "Ext Video Object Add failed");
                return PG_ERR_System;
            }

            mListObjPeer = "";
            _ObjPeerListAdd(sObjSelf, sObjVSelfHash, sObjASelfHash);
            _VideoOption(sVideoParam);
        }
        return PG_ERR_Normal;
    }

    /**
     * 清理退出
     */
    public void clean() {

        if (mNode != null) {
            int i = 0;
            while (true) {
                String sEle = mNode.omlGetEle(mListObjPeer, "", 1, i);
                if (sEle.length() <= 0) {
                    break;
                }
                String sObjPeer = mNode.omlGetName(sEle, "");
                String sOpenV = _ObjPeerListGet(sObjPeer, "OpenV");
                if ("1".equals(sOpenV)) {
                    videoStop(sObjPeer, isStatePrvw);
                }

                String sOpenA = _ObjPeerListGet(sObjPeer, "OpenA");
                if ("1".equals(sOpenA)) {
                    audioStop(sObjPeer);
                }

                mNode.ObjectSetGroup(sObjVSelfHash, "");
                mNode.ObjectSetGroup(sObjASelfHash, "");


                String sObjVPeerHash = _ObjPeerListGet(sObjPeer, "ObjV");
                String sObjAPeerHash = _ObjPeerListGet(sObjPeer, "ObjA");
                mNode.ObjectDelete(sObjAPeerHash);
                mNode.ObjectDelete(sObjVPeerHash);
                i++;
            }

            mListObjPeer = "";
        }

        _TimerClean();

        mNode = null;
        isStateInit = false;
    }

    /**
     * 获取预览Surface ，某些模块自身没有预览，比如直播模块的播放端，就需要通过这个获取预览。
     *
     * @param iW 宽
     * @param iH 高
     * @return 预览Surface
     */
    public SurfaceView getPrewview(int iW, int iH) {
        if (mNode != null) {
            return (SurfaceView) mNode.WndNew(0, 0, iW, iH);
        }
        return null;
    }

    /**
     * 删除预览
     */
    public void delPrewview() {
        if (mNode != null) {
            mNode.WndDelete();
        }
    }

    /**
     * 发送获取ID的完整对象名的消息
     *
     * @param sPeer  对端ID 或者节点对象objpeer
     * @param sParam 自定义参数
     * @return 错误码
     */
    public int sendGetObjPeer(String sPeer, String sParam) {
        String sMsg = "(CMD){" + GET_OBJPEER + "}(Param){" + sParam + "}(ObjPeer){" + mObjSelf + "}";
        String sData = "Forward?(User){" + mNode.omlEncode(sPeer) + "}(Msg){" + mNode.omlEncode(sMsg) + "}";

        return svrRequest(sData, sParam);
    }

    /**
     * 发送自身对象名称到ID所示的节点
     *
     * @param sPeer  对端ID 或者节点对象objpeer
     * @param sParam 自定义参数
     * @return 错误码
     */
    public int sendGetObjPeerNotify(String sPeer, String sParam) {
        String sMsg = "(CMD){" + GET_OBJPEER_NOTIFY + "}(Param){" + sParam + "}(ObjPeer){" + mObjSelf + "}";
        String sData = "Forward?(User){" + mNode.omlEncode(sPeer) + "}(Msg){" + mNode.omlEncode(sMsg) + "}";

        return svrRequest(sData, sParam);
    }

    /**
     * 播放端有效，和播放端的 API Start同时调用。
     *
     * @param sObjPeer 采集端ID
     * @return 错误码
     */
    public int start(String sObjPeer) {
        if (sObjPeer.length() > 5) {
            int iFlagPeerV = (0x10000 | 0x8 | 0x10 | 0x20);
            int iFlagPeerA = (0x10000 | 0x8 );
            if (!_ObjPeerListExist(sObjPeer)) {
                String sClass = mNode.ObjectGetClass(sObjPeer);
                if (!"PG_CLASS_Peer".equals(sClass)) {
                    if (!mNode.ObjectAdd(sObjPeer, "PG_CLASS_Peer", "", 0x10000)) {
                        Log.d("pgExtVideo", "Peer Add failed");
                        return PG_ERR_System;
                    }
                }

                mNode.ObjectSetGroup(sObjVSelfHash, sObjPeer);
                mNode.ObjectSetGroup(sObjASelfHash, sObjPeer);

                String sPeer = ((sObjPeer.length() > 100) ? sObjPeer.substring(5, 100) : sObjPeer.substring(5));

                String sObjVPeerHash = "EXT_VIDEO_" + sPeer;
                if (!mNode.ObjectAdd(sObjVPeerHash, "PG_CLASS_Video", sObjPeer, iFlagPeerV)) {
                    Log.d("pgExtVideo", "Peer Add failed");
                    return PG_ERR_System;
                }


                String sObjAPeerHash = "EXT_AUDIO_" + sPeer;
                if (!mNode.ObjectAdd(sObjAPeerHash, "PG_CLASS_Audio", sObjPeer, iFlagPeerA)) {
                    Log.d("pgExtVideo", "Peer Add failed");
                    return PG_ERR_System;
                }
                _ObjPeerListAdd(sObjPeer, sObjVPeerHash, sObjAPeerHash);
            }

            return PG_ERR_Normal;
        }
        return PG_ERR_BadParam;
    }

    /**
     * 停止
     */
    public void stop(String sObjPeer) {

        if (_ObjPeerListExist(sObjPeer)) {

            String sOpenV = _ObjPeerListGet(sObjPeer, "OpenV");
            if ("1".equals(sOpenV)) {
                videoStop(sObjPeer, isStatePrvw);
            }

            String sOpenA = _ObjPeerListGet(sObjPeer, "OpenA");
            if ("1".equals(sOpenA)) {
                audioStop(sObjPeer);
            }

            mNode.ObjectSetGroup(sObjVSelfHash, "");
            mNode.ObjectSetGroup(sObjASelfHash, "");


            String sObjVPeerHash = _ObjPeerListGet(sObjPeer, "ObjV");
            String sObjAPeerHash = _ObjPeerListGet(sObjPeer, "ObjA");

            mNode.ObjectDelete(sObjAPeerHash);
            mNode.ObjectDelete(sObjVPeerHash);

            _ObjPeerListDelete(sObjPeer);
        }

        //mNode.ObjectDelete(mListObjPeer);
    }

    private int _VideoOption( String sParam) {
        if("".equals(sParam)) {
            return PG_ERR_BadParam;
        }

        if(!this.mNode.ObjectAdd("_vTemp", "PG_CLASS_Video", "", 0)) {
            return 1;
        } else {
            int iPortrait = ParseInt(this.mNode.omlGetContent(sParam, "Portrait"), 0);
            if(iPortrait != 0) {
                this.mNode.ObjectRequest("_vTemp", 2, "(Item){2}(Value){90}", "");
            }

            int iCameraNo = ParseInt(this.mNode.omlGetContent(sParam, "CameraNo"), -1);
            if(iCameraNo >= 0) {
                String sData = "(Item){0}(Value){" + iCameraNo + "}";
                this.mNode.ObjectRequest("_vTemp", 2, sData, "VideoOption");
            }

            this.mNode.ObjectDelete("_vTemp");
            return 0;
        }
    }
    /**
     * 打开预览
     *
     * @return 错误码
     */
    private int prvwStart() {


        if (!isStatePrvw) {
            if (!mNode.ObjectAdd(mObjPrvw, "PG_CLASS_Video", "", 0x2)) {
                this.outString("Add 'Prvw' obj failed");
                return 1;
            }
            int iMode = ParseInt(mNode.omlGetContent(mVideoParam, "Mode"), -1);
            if (iMode < 0 || iMode > 11) {
                return PG_ERR_BadParam;
            }
            int iRate = ParseInt(mNode.omlGetContent(mVideoParam, "Rate"), 0);
            if (iRate < 0) {
                return PG_ERR_BadParam;
            }
            String sWndPrvw = "";
            String sWndRect = "(Code){0}(Mode){" + iMode + "}(Rate){" + iRate + "}(Wnd){" + sWndPrvw + "}";
            int uErr = mNode.ObjectRequest(mObjPrvw, PG_METH_VIDEO_START, sWndRect, "EXT_SDK_PrvwStart");
            if (uErr > 0) {
                this.outString("EXT_SDK,PrvwStart: Prvw, uErr=" + uErr);
                this.mNode.ObjectDelete(mObjPrvw);
                return uErr;
            }
            isStatePrvw = true;
        }

        return PG_ERR_Normal;
    }

    /**
     * 关闭预览
     */
    private void prvwStop() {
        if (this.isStatePrvw) {
            this.mNode.ObjectRequest(mObjPrvw, PG_METH_VIDEO_STOP, "", "EXT_SDK_PrvwStop");
            this.mNode.ObjectDelete(mObjPrvw);
        }
        this.isStatePrvw = false;

    }

    /**
     * 打开视频
     *
     * @param sObjPeer 视频发送端节点名称
     * @param peerView 显示对端视频的SurfaceView
     * @param bPrvw    是否打开预览
     * @return 错误码
     */
    public int videoStart(String sObjPeer, SurfaceView peerView, boolean bPrvw) {
        if (sObjPeer == null || sObjPeer.length() < 5 || peerView == null) {
            OutString("Param error : sObjPeer = " + sObjPeer + " peerView = " + peerView);

            return PG_ERR_BadParam;
        }

        if (_ObjPeerListExist(sObjPeer)) {

            pgLibJNINode node = GetNodeByView(peerView);
            if (node == null) {
                OutString("EXT_SDK : videoStart : peerView Node = null");
                return PG_ERR_BadParam;
            }

            String sObjV = _ObjPeerListGet(sObjPeer, "ObjV");

            int iCode = ParseInt(mNode.omlGetContent(mVideoParam, "Code"), -1);
            if (iCode < 0 || iCode > 4) {
                return PG_ERR_BadParam;
            }
            int iMode = ParseInt(mNode.omlGetContent(mVideoParam, "Mode"), -1);
            if (iMode < 0 || iMode > 11) {
                return PG_ERR_BadParam;
            }
            int iRate = ParseInt(mNode.omlGetContent(mVideoParam, "Rate"), 0);
            if (iRate < 0) {
                return PG_ERR_BadParam;
            }

            int iBitRate = ParseInt(mNode.omlGetContent(mVideoParam, "BitRate"), 0);
            if (iBitRate > 0) {
                String sEncodeData = "(Code){"+iCode+"}(Mode){"+iMode+"}(BitRate){"+iBitRate+"}(FrmRate){0}(KeyFrmRate){0}(LossAllow){0}";
                String sDateBtiRate = "(Item){5}(Value){" + mNode.omlEncode(sEncodeData) +"}";
                mNode.ObjectRequest(sObjV,2,sDateBtiRate,"ParamCodeMode");
            }

            String sSetData = "(Item){4}(Value){" + iRate + "}";
            mNode.ObjectRequest(sObjV, 2, sSetData, "SetFrmRate");

            if (bPrvw) {
                prvwStart();
            }

            String sWndRect = "(Code){" + iCode + "}" +
                    "(Mode){" + iMode + "}" +
                    "(Rate){" + iRate + "}" +
                    "(Wnd){" + node.utilGetWndRect() + "}";
            int uErr = mNode.ObjectRequest(sObjV, PG_METH_VIDEO_START, sWndRect, PARAM_EXT_SDK_VIDEO_OPEN);
            if (uErr > 0) {
                OutString("EXT_SDK,StartVideo, Video, uErr=" + uErr);
            } else {
                _ObjPeerListSet(sObjPeer, "OpenV", "1");
            }
            return uErr;
        }
        return PG_ERR_NoExist;
    }

    /**
     * 回复视频请求
     *
     * @param sObjV    节点对象名，
     * @param uErrCode 0 同意，>0 错误码。
     * @param uHandle  视频句柄
     * @param sObjPeer 节点名称，传入onVideoStart 回调的sObjPeer的值
     * @param peerView 显示对端的视频的View
     * @param bPrvw    是否打开预览
     * @return 错误码
     */
    public int videoHandle(String sObjV, int uErrCode, int uHandle, String sObjPeer, SurfaceView peerView, boolean bPrvw) {

        if ("".equals(sObjV) || uHandle <= 0) {
            OutString("EXT_SDK : videoHandle : sObj = " + sObjV + " uHandle = " + uHandle);
            return PG_ERR_BadParam;
        }

        if (_ObjPeerListExist(sObjPeer)) {
            String sWndRect = "";
            if (uErrCode <= PG_ERR_Normal) {

                pgLibJNINode node = GetNodeByView(peerView);
                if (node == null) {
                    OutString("EXT_SDK : videoStart : peerView Node = null");
                    return PG_ERR_BadParam;
                }

                int iCode = ParseInt(mNode.omlGetContent(mVideoParam, "Code"), -1);
                if (iCode < 0 || iCode > 4) {
                    return PG_ERR_BadParam;
                }
                int iMode = ParseInt(mNode.omlGetContent(mVideoParam, "Mode"), -1);
                if (iMode < 0 || iMode > 11) {
                    return PG_ERR_BadParam;
                }
                int iRate = ParseInt(mNode.omlGetContent(mVideoParam, "Rate"), 0);
                if (iRate < 0) {
                    return PG_ERR_BadParam;
                }

                int iBitRate = ParseInt(mNode.omlGetContent(mVideoParam, "BitRate"), 0);
                if (iBitRate > 0) {
                    String sEncodeData = "(Code){"+iCode+"}(Mode){"+iMode+"}(BitRate){"+iBitRate+"}(FrmRate){0}(KeyFrmRate){0}(LossAllow){0}";
                    String sDateBtiRate = "(Item){5}(Value){" + mNode.omlEncode(sEncodeData) +"}";
                    mNode.ObjectRequest(sObjV,2,sDateBtiRate,"ParamCodeMode");
                }

                if (bPrvw) {
                    prvwStart();
                }

                sWndRect = "(Code){" + iCode + "}" +
                        "(Mode){" + iMode + "}" +
                        "(Rate){" + iRate + "}" +
                        "(Wnd){" + node.utilGetWndRect() + "}";
            }
            int iErr = mNode.ObjectExtReply(sObjV, uErrCode, sWndRect, uHandle);
            if (iErr > 0) {
                outString("EXT_SDK : VideoHandle : ObjectExtReply iErr = " + iErr);
            } else {
                if (uErrCode <= PG_ERR_Normal) {
                    _ObjPeerListSet(sObjPeer, "OpenV", "1");
                }
            }
            return iErr;
        }
        return PG_ERR_NoExist;
    }

    /**
     * 关闭视频。
     */
    public void videoStop(String sObjPeer, boolean bPrvw) {

        if (_ObjPeerListExist(sObjPeer)) {

            String sObjV = _ObjPeerListGet(sObjPeer, "ObjV");
            mNode.ObjectRequest(sObjV, PG_METH_VIDEO_STOP, "", "EXT_SDK_VIDEO_CLOSE");

            if (bPrvw) {
                prvwStop();
            }
            _ObjPeerListSet(sObjPeer, "OpenV", "0");
        }
    }


    /**
     * 打开音频
     *
     * @param sObjPeer 音频采集端 节点对象名
     * @return 错误码
     */
    public int audioStart(String sObjPeer) {
        if (sObjPeer == null || "".equals(sObjPeer)) {
            OutString("Param error : sObjPeer = " + sObjPeer);

            return PG_ERR_BadParam;
        }

        if (_ObjPeerListExist(sObjPeer)) {

            String sObjA = _ObjPeerListGet(sObjPeer, "ObjA");
            int iCode = ParseInt(mNode.omlGetContent(mAudioParam, "Code"), 0);
            if (iCode < 0 || iCode > 3) {
                iCode = 0;
            }
            int iMode = ParseInt(mNode.omlGetContent(mAudioParam, "Code"), 0);
            if (iMode < 0 || iCode > 1) {
                iMode = 0;
            }

            String sWndRect = "(Code){" + iCode + "}" +
                    "(Mode){" + iMode + "}";
            int uErr = mNode.ObjectRequest(sObjA, PG_METH_VIDEO_START, sWndRect, PARAM_EXT_SDK_AUDIO_OPEN);
            if (uErr > 0) {
                OutString("EXT_SDK,audioOpen, Video, uErr=" + uErr);
            } else {
                _ObjPeerListSet(sObjPeer, "OpenA", "1");
            }
            return uErr;
        }
        return PG_ERR_NoExist;
    }

    /**
     * 关闭音频。
     */
    public void audioStop(String sObjPeer) {
        if (_ObjPeerListExist(sObjPeer)) {
            String sObjA = _ObjPeerListGet(sObjPeer, "ObjA");
            mNode.ObjectRequest(sObjA, PG_METH_VIDEO_STOP, "", "EXT_SDK_AUDIO_CLOSE");
            _ObjPeerListSet(sObjPeer, "OpenA", "0");
        }
    }

    /**
     * 发送消息到服务器
     *
     * @param sData  内容
     * @param sParam 自定义标记
     * @return 错误码
     */
    private int svrRequest(String sData, String sParam) {
        String sDataReq = ("1024:" + sData);
        String sParamReq = ("EXT_SVR_REQ:" + sParam);
        int iErr = mNode.ObjectRequest(mObjSvr, 35, sDataReq, sParamReq);
        if (iErr > pgLibLiveMultiError.PG_ERR_Normal) {
            outString("pgLibLiveMultiRender.SvrRequest: iErr=" + iErr);
        }
        return iErr;
    }

    //-------------------------------------------------------------------------
    private void _OnTimeout(String sParam) {
        outString(sParam);
        String sAct = mNode.omlGetContent(sParam, "Act");

    }

    private int onSvrMessage(String sData, String sPeer) {

        String sCmd = "";
        String sParam = "";
        int iInd = sData.indexOf('?');
        if (iInd > 0) {
            sCmd = sData.substring(0, iInd);
            sParam = sData.substring(iInd + 1);
        } else {
            sParam = sData;
        }

        if ("UserExtend".equals(sCmd)) {
            String sDevID = mNode.omlGetContent(sParam, "User");
            String sMsg = mNode.omlGetContent(sParam, "Msg");

            String sMsgEle = mNode.omlDecode(sMsg);

            String sCmdRemote = mNode.omlGetContent(sMsgEle, "CMD");
            String sObjPeer = mNode.omlGetContent(sMsgEle, "ObjPeer");
            String sParamRemote = mNode.omlGetContent(sMsgEle, "Param");
            if (sCmdRemote.equals(GET_OBJPEER)) {
                mOnEvent.onGetObjPeer(sObjPeer, sParamRemote);
                return PG_ERR_Normal;
            } else if (sCmdRemote.equals(GET_OBJPEER_NOTIFY)) {
                mOnEvent.onGetObjPeerNotify(sObjPeer, sParam);
                return PG_ERR_Normal;
            }

            return PG_ERR_Unknown;
        }
        return PG_ERR_Unknown;
    }

    /**
     * 直播模块钩子
     */
    class Hook implements pgLibLive.NodeEventHook,pgLibLiveMultiCapture.NodeEventHook,pgLibLiveMultiRender.NodeEventHook{

        @Override
        public int OnExtRequest(String s, int i, String s1, int i1, String s2) {
            return 0;
        }

        @Override
        public int OnReply(String s, int i, String s1, String s2) {
            return 0;
        }
    }

    private final Object mHook = new Hook() {
        @Override
        public int OnExtRequest(String sObj, int uMeth, String sData, int uHandle, String sObjPeer) {

            return _OnExtRequest(sObj,uMeth,sData,uHandle,sObjPeer);
        }

        @Override
        public int OnReply(String sObj, int uErr, String sData, String sParam) {

            return _OnReply(sObj,uErr,sData,sParam);
        }
    };

    ///////////////////////////////////////////////////////////////////////////


    private String mListObjPeer = "";

    private String _ObjPeerListSearch(String sObjPeer) {
        String sPath = "\n*" + sObjPeer;
        return this.mNode.omlGetEle(this.mListObjPeer, sPath, 1, 0);
    }

    private void _ObjPeerListAdd(String sObjPeer, String sObjV, String sObjA) {
        String sCapture = this._ObjPeerListSearch(sObjPeer);
        if ("".equals(sCapture)) {
            this.mListObjPeer = this.mListObjPeer + "(" + this.mNode.omlEncode(sObjPeer) + "){" +
                    "(ObjV){" + this.mNode.omlEncode(sObjV) + "}" +
                    "(OpenV){0}" +
                    "(ObjA){" + this.mNode.omlEncode(sObjA) + "}" +
                    "(OpenA){0}" +
                    "}";
        }

    }

    private void _ObjPeerListDelete(String sObjPeer) {
        String sCapture = this._ObjPeerListSearch(sObjPeer);
        if (!"".equals(sCapture)) {
            String sPath = "\n*" + sObjPeer;
            this.mListObjPeer = this.mNode.omlDeleteEle(this.mListObjPeer, sPath, 1, 0);
        }

    }

    private boolean _ObjPeerListSet(String sObjPeer, String sItem, String sValue) {
        String sCapture = this._ObjPeerListSearch(sObjPeer);
        if (!"".equals(sCapture)) {
            String sPath = "\n*" + sObjPeer + "*" + sItem;
            this.mListObjPeer = this.mNode.omlSetContent(this.mListObjPeer, sPath, sValue);
            return true;
        } else {
            return false;
        }
    }

    private String _ObjPeerListGet(String sObjPeer, String sItem) {
        String sPath = "\n*" + sObjPeer + "*" + sItem;
        return this.mNode.omlGetContent(this.mListObjPeer, sPath);
    }

    private boolean _ObjPeerListExist(String sObjPeer) {
        String sCapture = this._ObjPeerListSearch(sObjPeer);
        return !"".equals(sCapture);
    }

    private int _OnExtRequest(String sObj, int uMeth, String sData, int uHandle, String sObjPeer) {
        if (mOnEvent != null) {
            if (sObj.indexOf("EXT_VIDEO_") == 0) {
                if (uMeth == 0) {
                    String sAct = mNode.omlGetContent(sData, "Action");
                    return mOnEvent.onVideoSync(sObj, sAct, sObjPeer);
                }

                if (uMeth == PG_METH_VIDEO_START) {
                    // Start Video request
                    return mOnEvent.onVideoStart(sObj, uHandle, sObjPeer);

                }
                if (uMeth == PG_METH_VIDEO_STOP) {
                    // Stop Video request
                    if (_ObjPeerListExist(sObjPeer)) {
                        _ObjPeerListSet(sObjPeer, "OpenV", "0");
                    }

                    prvwStop();
                    return mOnEvent.onVideoStop(sObj, sObjPeer);

                }
                if (uMeth == 40) {
                    mOnEvent.onVideoFrameStat(sObj, sData);
                    return PG_ERR_Normal;
                }
                return PG_ERR_Normal;
            }

            if (sObj.indexOf("EXT_AUDIO_") == 0) {
                if (uMeth == 0) {
                    String sAct = mNode.omlGetContent(sData, "Action");
                    return mOnEvent.onAudioSync(sObj, sAct);
                }

                if (uMeth == PG_METH_VIDEO_START) {
                    // Start Audio request
                    return mOnEvent.onAudioStart(sObj, sObjPeer);

                }
                if (uMeth == PG_METH_VIDEO_STOP) {
                    // Stop Audio request

                    if (_ObjPeerListExist(sObjPeer)) {
                        _ObjPeerListSet(sObjPeer, "OpenA", "0");
                    }
                    return mOnEvent.onAudioStop(sObj);

                }
            }

            if (mObjSelf.equals(sObj)) {
                if (uMeth == 36) {
                    if (mObjSvr.equals(sObjPeer)) {
                        return onSvrMessage(sData, sObjPeer);
                    }
                }
            }
        }
        return PG_ERR_Unknown;
    }


    private int _OnReply(String sObj, int uErr, String sData, String sParam) {
        if (mOnEvent != null) {

            if (PARAM_EXT_SDK_VIDEO_OPEN.equals(sParam)) {
                return mOnEvent.onVideoStartReply(sObj, uErr);
            }
            if (PARAM_EXT_SDK_AUDIO_OPEN.equals(sParam)) {
                return mOnEvent.onAudioStartRelay(sObj, uErr);
            }

            if (sParam.indexOf("EXT_SVR_REQ:") == 0) {
                mOnEvent.onGetObjPeerReply(uErr, sParam.substring(12));
                return 1;
            }


        }
        return -1;
    }
    ///------------------------------------------------------------------------
    // Timer handler

    private void outString(String sOut) {
        System.out.println("ExtVideo : " + sOut);
    }

    public class WorkThread extends Thread {

        private Runnable target;
        private AtomicInteger counter;

        WorkThread(Runnable target, AtomicInteger counter) {
            this.target = target;
            this.counter = counter;
        }

        @Override
        public void run() {
            try {
                target.run();
            } finally {
                int c = counter.getAndDecrement();
                System.out.println("terminate no " + c + " Threads");
            }
        }
    }

    void test() {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                int c = count.incrementAndGet();
                System.out.println("create no " + c + " Threads");
                return new WorkThread(r, count);
            }
        });
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                outString("test  scheduleAtFixedRate run ");
            }
        }, 0, 1000, TimeUnit.HOURS);
    }

    // Timer list.
    class TimerItem {
        public String sParam = "";

        public int iCookie = 0;
        public ScheduledExecutorService timer = null;
        public pgTimerTask timerTask = null;

        public TimerItem(String sParam1) {
            sParam = sParam1;
        }
    }

    private Handler m_TimerHandler = null;
    private ArrayList<TimerItem> m_TimeList = new ArrayList<TimerItem>();

    // Timer class.
    class pgTimerTask extends TimerTask {
        int m_iTimeID = -1;

        public pgTimerTask(int iTimerID) {
            super();
            m_iTimeID = iTimerID;
        }

        @Override
        public void run() {
            try {
                if (m_TimerHandler != null) {
                    Message oMsg = m_TimerHandler.obtainMessage(0, Integer.valueOf(m_iTimeID));
                    m_TimerHandler.sendMessage(oMsg);
                }
            } catch (Exception ex) {
                outString("pgLibLiveMultiRender.pgTimerTask.run, ex=" + ex.toString());
            }
        }
    }

    // Create Timer message handler.
    @SuppressLint("HandlerLeak")
    public boolean _TimerInit() {
        try {
            m_TimerHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {

                    int iTimerID = (Integer) msg.obj;
                    int iCookie = iTimerID & 0xffff;
                    int iItem = (iTimerID >> 16) & 0xffff;

                    if (iItem >= m_TimeList.size()
                            || m_TimeList.get(iItem).iCookie != iCookie) {
                        return;
                    }

                    TimerItem item = m_TimeList.get(iItem);
                    String sParam = item.sParam;

                    // Timer clean.
                    if (item.timer != null) {
                        item.timer.shutdown();
                    }
                    if (item.timerTask != null) {
                        item.timerTask.cancel();
                    }
                    item.sParam = "";
                    item.timerTask = null;
                    item.timer = null;
                    item.iCookie = 0;

                    // Timer callback.
                    _OnTimeout(sParam);
                }
            };

            return true;
        } catch (Exception ex) {
            outString("pgLibLiveMultiRender.TimerInit: ex=" + ex.toString());
            return false;
        }
    }

    public void _TimerClean() {
        if (m_TimerHandler != null) {
            for (int i = 0; i < m_TimeList.size(); i++) {
                try {
                    if (m_TimeList.get(i).timer != null) {
                        m_TimeList.get(i).timer.shutdown();
                    }
                    if (m_TimeList.get(i).timerTask != null) {
                        m_TimeList.get(i).timerTask.cancel();
                    }

                    m_TimeList.get(i).sParam = "";
                    m_TimeList.get(i).timerTask = null;
                    m_TimeList.get(i).timer = null;
                    m_TimeList.get(i).iCookie = 0;
                } catch (Exception ex) {
                    outString("pgLibLiveMultiRender.TimerClean, ex=" + ex.toString());
                }
            }
            m_TimerHandler = null;
        }
    }

    public int _TimerStart(String sParam, int iTimeout) {

        try {
            int iItem = -1;
            for (int i = 0; i < m_TimeList.size(); i++) {
                if (m_TimeList.get(i).timer == null) {
                    iItem = i;
                    break;
                }
            }
            if (iItem < 0) {
                m_TimeList.add(new TimerItem(sParam));
                iItem = m_TimeList.size() - 1;
            }

            int iCookie = (mRandom.nextInt() & 0xffff);
            int iTimerID = (((iItem << 16) & 0xffff0000) | iCookie);

            ScheduledExecutorService timer = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
                private AtomicInteger count = new AtomicInteger();

                @Override
                public Thread newThread(Runnable r) {
                    int c = count.incrementAndGet();
                    System.out.println("create no " + c + " Threads");
                    return new WorkThread(r, count);
                }
            });
            pgTimerTask timerTask = new pgTimerTask(iTimerID);

            TimerItem item = m_TimeList.get(iItem);
            item.sParam = sParam;

            item.timer = timer;
            item.timerTask = timerTask;
            item.iCookie = iCookie;
            timer.schedule(timerTask, (iTimeout * 1000), TimeUnit.MILLISECONDS);

            return iTimerID;
        } catch (Exception ex) {
            outString("pgLibLiveMultiRender.Add, ex=" + ex.toString());
            return -1;
        }
    }

    public void _TimerStop(int iTimerID) {

        int iCookie = iTimerID & 0xffff;
        int iItem = (iTimerID >> 16) & 0xffff;
        if (iItem >= m_TimeList.size()
                || m_TimeList.get(iItem).iCookie != iCookie) {
            return;
        }

        try {
            if (m_TimeList.get(iItem).timer != null) {
                m_TimeList.get(iItem).timer.shutdown();
            }
            if (m_TimeList.get(iItem).timerTask != null) {
                m_TimeList.get(iItem).timerTask.cancel();
            }

            m_TimeList.get(iItem).sParam = "";
            m_TimeList.get(iItem).timerTask = null;
            m_TimeList.get(iItem).timer = null;
            m_TimeList.get(iItem).iCookie = 0;
        } catch (Exception ex) {
            outString("pgLibLiveMultiRender.TimerStop, ex=" + ex.toString());
        }
    }
}
