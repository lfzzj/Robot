package com.lf.p2p.capture;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceView;

import com.peergine.android.livemulti.pgLibLiveMultiCapture;
import com.peergine.extvideo.lib.ExtVideo;
import com.peergine.extvideo.lib.OnEvent;
import com.peergine.pgLibView;
import com.peergine.plugin.lib.pgLibJNINode;

import java.util.logging.Logger;

import static com.peergine.pgLibError.PG_ERR_Normal;

/**
 * @data on 2018/5/24 14:12
 * @describe: 采集端 管理类
 */

public class CaptureManager {
    private pgLibLiveMultiCapture mLive;
    private ExtVideo extVideo;

    private Context mContext;
    private pgLibLiveMultiCapture.OnEventListener mOnEvent;
    private OnEvent mExtEvent;
    private String msDevID;

    public CaptureManager(Context context, pgLibLiveMultiCapture.OnEventListener onEvent, OnEvent extEvent, String msDevID) {
        this.mContext = context;
        this.mOnEvent = onEvent;
        this.mExtEvent = extEvent;
        this.msDevID = msDevID;
        init();

    }

    private void init() {
        //创建P2P直播对象
        mLive = new pgLibLiveMultiCapture();

        extVideo = new ExtVideo();

        //注册事件回调接口到P2P直播对象。
        mLive.SetEventListener(mOnEvent);

        extVideo = new ExtVideo();
        extVideo.setOnEvent(mExtEvent);//设置ExtVideo 的回调。
        mLive.SetNodeEventHook((pgLibLiveMultiCapture.NodeEventHook) extVideo.getHook());
    }

    /**
     * P2P直播模块初始化函数
     *
     * @return
     */
    public boolean Initialize(String username,String passWd,String address) {
        int liveInitResult = Initialize(username, passWd, address, "",
                3, "(Debug){1}(SocketInitWnd){3}", mContext);
        if (liveInitResult != PG_ERR_Normal) {
            return false;
        }
        return true;
    }

    /**
     * 采集端初始化
     *
     * @return
     */
    public boolean initialize() {
        String sExtVideoParam = "(Code){3}(Mode){2}(Rate){66}(BitRate){300}(Portrait){1}(CameraNo){0}";
        int iErr = initialize(mLive.GetNode(), mLive.GetSelfPeer(), sExtVideoParam, "");
        if (iErr > PG_ERR_Normal) {
            return false;
        }
        return true;
    }

    public SurfaceView getLiveSurfaceView() {
        return mLive.CameraViewGet();
    }

    public SurfaceView getLiveOtherSurfaceView() {
        return pgLibView.Get("v1");
    }


    public void videoStop(String msObjPeer) {
        extVideo.videoStop(msObjPeer, false);
        extVideo.stop(msObjPeer);
    }

    /**
     * 视频启动
     *
     * @param sObjPeer
     * @return
     */
    public int extVideoStart(String sObjPeer) {
        return extVideo.start(sObjPeer);
    }

    public void showMedias(boolean isMonitor){
        String sVideoParam = "(Code){3}(Mode){2}(Rate){66}(BitRate){500}(Delay){300}";
        mLive.VideoStart(0, sVideoParam, null);
        if (isMonitor){
            mLive.AudioStart(0, "(MuteOutput){1}");
        }else{
            mLive.AudioStart(0, "");
        }
    }

    public int sendGetObjPeerNotify(String sObjPeer, String sParam) {
        return extVideo.sendGetObjPeerNotify(sObjPeer, sParam);
    }


    public int videoHandle(String sObj, int pgErrNormal, int uHandle, String sObjPeer, SurfaceView otherSurfaceView, boolean b) {
        return extVideo.videoHandle(sObj, pgErrNormal, uHandle, sObjPeer, otherSurfaceView, b);
    }

    /**
     * 退出直播模块
     */
    public void liveStop(SurfaceView otherSurfaceView) {
        pgLibView.Release(otherSurfaceView);//释放View
        extVideo.clean();
        mLive.CameraViewRelease();
        mLive.Clean();
        mLive.SetNodeEventHook(null);
        extVideo.setOnEvent(null);
    }


    /**
     * P2P直播模块初始化函数
     * 阻塞方式：非阻塞，立即返回。
     *
     * @param sUser       客户端的登录用户名（或者称为P2P ID）
     * @param sPass       客户端的登录密码
     * @param sSvrAddr    P2P服务器的地址端口，例如：“127.0.0.1:7781”
     * @param sRelayAddr  中继服务器地址端口，P2P无法穿透的情况下通过中继服务器转发。如："127.0.0.1:443"，
     *                    ---如果传空字符串，则使用P2P服务器的IP地址加上443端口构成转发服务器地址。
     * @param iP2PTryTime P2P穿透尝试时间（单位为秒）。
     *                    ---(iP2PTryTime == 0)：使用缺省值，缺省值为6秒。
     *                    ---(iP2PTryTime > 0 && iP2PTryTime <= 3600)：超时值为所传的iP2PTryTime
     *                    --- (iP2PTryTime > 3600)：禁用P2P穿透，直接用转发
     * @param sInitParam  初始化参数
     *                    ---格式为：(VideoInExternal){0}(VideoInExternal){0}(VideoOutExtCmp){0}(AudioInExternal){0}(AudioOutExternal){0}
     *                    -----VideoInExternal: 启用视频输入回调接口。0为禁用，1为启用。
     *                    -----VideoOutExternal: 启用视频解码后输出回调接口。0为禁用，1为启用。
     *                    -----VideoOutExtCmp: 启用视频解码前输出回调接口。0为禁用，1为启用。
     *                    -----AudioInExternal: 启用音频输入回调接口。0为禁用，1为启用。
     * @param oCtx        Application上下文。
     * @return 错误码定义
     */
    private int Initialize(String sUser, String sPass, String sSvrAddr, String sRelayAddr, int iP2PTryTime, String sInitParam, Context oCtx) {
        return mLive.Initialize(sUser, sPass, sSvrAddr, sRelayAddr, iP2PTryTime, sInitParam, oCtx);
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
    private int initialize(pgLibJNINode node, String sObjSelf, String sVideoParam, String sAudioParam) {
        return extVideo.initialize(node, sObjSelf, sVideoParam, sAudioParam);
    }


}
