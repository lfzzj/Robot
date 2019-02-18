 package com.hamitao.kids.ui.activity;

 import android.content.Intent;
 import android.os.SystemClock;
 import android.util.Log;
 import android.view.MotionEvent;
 import android.view.SurfaceView;
 import android.view.View;
 import android.view.WindowManager;
 import android.widget.Chronometer;
 import android.widget.ImageView;
 import android.widget.LinearLayout;
 import android.widget.RelativeLayout;

 import com.hamitao.framework.constant.BaseConstant;
 import com.hamitao.framework.utils.DeviceUtil;
 import com.hamitao.framework.utils.Logger;
 import com.hamitao.framework.utils.WifiUtil;
 import com.hamitao.kids.R;
 import com.hamitao.kids.app.MyApp;
 import com.hamitao.kids.base.BaseMsgActivity;
 import com.hamitao.kids.constant.Constants;
 import com.hamitao.kids.model.AnyEventType;
 import com.hamitao.kids.mvp.ipresenter.IDevicePresenter;
 import com.hamitao.kids.mvp.ipresenter.IPublicPresenter;
 import com.hamitao.kids.mvp.presenter.DevicePresenterImpl;
 import com.hamitao.kids.mvp.presenter.PublicPresenterImpl;
 import com.hamitao.kids.utils.PlayVoice;
 import com.hamitao.kids.utils.ResUtil;
 import com.hamitao.kids.utils.ScreenUtil;
 import com.hamitao.kids.utils.TimerControlView;
 import com.hamitao.kids.utils.Util;
 import com.hamitao.kids.utils.WindowUtils;
 import com.hamitao.requestframe.entity.GetP2PByGuidInfo;
 import com.hamitao.requestframe.view.GetP2PByGuidView;
 import com.lf.p2p.Checker;
 import com.lf.p2p.capture.CaptureManager;
 import com.peergine.android.livemulti.pgLibLiveMultiCapture;
 import com.peergine.extvideo.lib.OnEvent;

 import org.greenrobot.eventbus.EventBus;
 import org.greenrobot.eventbus.Subscribe;
 import org.json.JSONException;
 import org.json.JSONObject;

 import butterknife.BindView;
 import butterknife.OnClick;

 import static com.peergine.android.livemulti.pgLibLiveMultiError.PG_ERR_Normal;

/**
 * @data on 2018/5/28 9:59
 * @describe: 视频聊天（p2p）
 */

public class VideoChatP2PActivity extends BaseMsgActivity {

    private String TAG = "p2p";

    @BindView(R.id.layout_video)
    LinearLayout layoutVideo;//对面的
    @BindView(R.id.layout_prvw)
    LinearLayout layoutPrvw;//我的
    @BindView(R.id.image_head)
    ImageView imageHead;//头像

    private CaptureManager manager;

    private String msObjPeer = "";
    private SurfaceView prvwSurfaceView;
    private SurfaceView otherSurfaceView;


    private String guid = "";
    private String token = "";

    private IPublicPresenter publicPresenter;

    private String sourceChannelid;
    private boolean isCall = true;//是否是来电

    private TimerControlView timerControlView;
//    private TimerControlView timerCancal;//挂断计时器

    @BindView(R.id.timer)
    Chronometer chronometer;//计时器
    @BindView(R.id.rl_hang_up_frame)
    RelativeLayout rlHangUpFrame;

    private IDevicePresenter devicePresenter;

//    private boolean isHangUpFrameShow = false;//挂断布局是否展示
    private boolean isRenderJoin = false;

    private String flagCall;
    private String callName;

    private boolean isVideoMonitor = false;

    @Override
    public void setActivityView() {
        ScreenUtil.screenOn(mActivity);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_video_chat_p2p);
    }

    @Override
    public void initData() {

        //隐藏弹窗
        WindowUtils.hidePopupWindow();

        EventBus.getDefault().post(new AnyEventType(Constants.FLAG_CLOSE_AWAKEN, ""));

        initManager();

        MyApp.getInstance().setVideoChating(true);
        Intent intent = getIntent();
        sourceChannelid = intent.getStringExtra(Constants.isVideoChating_sourceChannelid);
        isCall = intent.getBooleanExtra(Constants.isCallVideoChat, false);
        flagCall = intent.getStringExtra(Constants.CallFlag);
        callName = intent.getStringExtra(Constants.CallName);
        Logger.d(TAG, "sourceChannelid=" + sourceChannelid + "    是否是来电：" + isCall);
        //获取p2p的数据
        getP2PByGuidInfo();
        if (!isCall) {//如果是去电，才开启声音
            timerControlView.startControlTimer();
        }
        checkCamera();
    }

    /**
     * 获取p2p的数据
     */
    private void getP2PByGuidInfo() {
        String imei = DeviceUtil.getImei(mContext);
        String wifimac = WifiUtil.getInstance().getMacAddress();
        devicePresenter.getP2PByGuid(imei, imei, wifimac, deviceManager.getDeviceId());
    }

    private void initManager() {
        publicPresenter = new PublicPresenterImpl(mContext);
        devicePresenter = new DevicePresenterImpl(mContext, mGetP2PByGuidView);

        timerControlView = new TimerControlView(Constants.oneMinuteTime);
        timerControlView.setOnTimerContorlCallBack(mOnTimerContorlCallBack);

//        timerCancal = new TimerControlView(Constants.fiveSecondTime);
//        timerCancal.setOnTimerContorlCallBack(mOnTimerCancalCallBack);

    }

    private GetP2PByGuidView mGetP2PByGuidView = new GetP2PByGuidView() {
        @Override
        public void onSuccess(GetP2PByGuidInfo mGetP2PByGuidInfo) {
            Logger.d(TAG, "获取P2P数据成功，开始初始化");
            initP2PManager(mGetP2PByGuidInfo);
        }

        @Override
        public void onError(String result) {
            Logger.d(TAG, "获取P2P数据失败，退出视频聊天   result=" + result);
            toast(getResources().getString(R.string.hint_video_chat_no_data));

        }
    };

    private void initP2PManager(GetP2PByGuidInfo mGetP2PByGuidInfo) {
        String p2pName = mGetP2PByGuidInfo.getP2p_id();
        String passWd = mGetP2PByGuidInfo.getPasswd();
        String address = mGetP2PByGuidInfo.getAddress();
        guid = mGetP2PByGuidInfo.getGuid();
        token = mGetP2PByGuidInfo.getToken();
        manager = new CaptureManager(mContext, mOnEvent, extEvent, guid);
        if (!manager.Initialize(p2pName, passWd, address)) {
            Log.d(TAG, "Live.Initialize failed!");//直播模块初始化失败
            return;
        }
        Logger.d(TAG, "直播模块初始化 成功");

        if (!manager.initialize()) {
            Log.d(TAG, "Live.Initialize failed!");//采集端初始化失败
            return;
        }
        Logger.d(TAG, "采集模块初始化 成功");

        pushVideoChatMsg();

        prvwSurfaceView = manager.getLiveSurfaceView();
        otherSurfaceView = manager.getLiveOtherSurfaceView();

        layoutPrvw.removeAllViews();
        layoutVideo.removeAllViews();
        layoutPrvw.addView(prvwSurfaceView);

        if (flagCall.equals(BaseConstant.INSTRUCT_ACTION_PHONE_VIDEO_DEVICE)
                || flagCall.equals(BaseConstant.INSTRUCT_ACTION_DEVICE_VIDEO_PHONE)) {
            Logger.d(TAG, "为视频聊天");
            layoutVideo.addView(otherSurfaceView);
            prvwSurfaceView.setZOrderOnTop(true);//将我的置顶
            manager.showMedias(false);
        } else if (flagCall.equals(BaseConstant.INSTRUCT_ACTION_PHONE_VOICE_DEVICE)
                || flagCall.equals(BaseConstant.INSTRUCT_ACTION_DEVICE_VOICE_PHONE)) {
            Logger.d(TAG, "为语音聊天");
            layoutVideo.addView(otherSurfaceView);
            layoutVideo.setVisibility(View.GONE);
            layoutPrvw.setVisibility(View.GONE);
            imageHead.setVisibility(View.VISIBLE);
            ResUtil.setCallheaderByName(callName, imageHead);
            manager.showMedias(false);
        } else if (flagCall.equals(BaseConstant.INSTRUCT_ACTION_PHONE_MONITOR_DEVICE)) {
            isVideoMonitor = true;
            Logger.d(TAG, "为视频监控");
            layoutVideo.setVisibility(View.GONE);
            layoutPrvw.setVisibility(View.INVISIBLE);
            rlHangUpFrame.setVisibility(View.GONE);
            manager.showMedias(true);
        }

        Log.d(TAG, "LiveStart: start.");

    }

    /**
     * 给目标推送视频聊天的消息
     */
    private void pushVideoChatMsg() {
        JSONObject object = new JSONObject();
        try {
            object.put("guid", guid);
            object.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String content = object.toString();
        publicPresenter.pushMsg(sourceChannelid, Util.setRequestPushMsgInfo(flagCall, content,
                MyApp.getInstance().getSpManager().getImNickName(), MyApp.getInstance().getSpManager().getChannelId(), sourceChannelid));

    }

    private TimerControlView.OnTimerContorlCallBack mOnTimerContorlCallBack = new TimerControlView.OnTimerContorlCallBack() {
        @Override
        public void onStart() {//开启计时，播放声音
            Logger.d(TAG, "开启计时，播放声音");
            PlayVoice.playVoice(mContext, R.raw.apple_iphone_6);
        }

        @Override
        public void onEnd() {//时间到了对方未接听
            Logger.d(TAG, "时间到了对方未接听,关闭视频聊天,关闭声音");
            toast(getResources().getString(R.string.hint_call_unanswered));
            finish();

        }
    };

//    private TimerControlView.OnTimerContorlCallBack mOnTimerCancalCallBack = new TimerControlView.OnTimerContorlCallBack() {
//        @Override
//        public void onStart() {
//            setHangUpFrameShow(true);
//
//        }
//
//        @Override
//        public void onEnd() {
//            setHangUpFrameShow(false);
//        }
//    };

    @OnClick({R.id.iv_refuse_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_refuse_phone://挂断
                toast(getResources().getString(R.string.hint_call_canceled));
                if (!isCall) {//如果去去电，
                    if (isRenderJoin) {//说明已建立连接，挂断后推送通话时长
                        EventBus.getDefault().post(new AnyEventType(Constants.FLAG_SEND_TEXT_MSG_DURATION, chronometer.getText().toString()));
                    } else {//未建立连接，直接挂断
                        EventBus.getDefault().post(new AnyEventType(Constants.FLAG_SEND_TEXT_MSG_CANCEL, ""));
                    }
                }
                publicPresenter.pushMsg(sourceChannelid, Util.setRequestPushMsgInfo(BaseConstant.INSTRUCT_ACTION_HANG_UP, "", "", MyApp.getInstance().getSpManager().getChannelId(), sourceChannelid));
                finish();
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                if (!isVideoMonitor) {
                    //按下
                    if (isRenderJoin) {
//                        if (isHangUpFrameShow) {
//                            setHangUpFrameShow(false);
//                        } else {
//                            setHangUpFrameShow(true);
//                            timerCancal.startControlTimer();
//                        }
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                //移动

                break;
            case MotionEvent.ACTION_UP:
                //松开

                break;
        }

        return super.onTouchEvent(event);
    }

//    /**
//     * 设置挂断布局的显示与隐藏
//     *
//     * @param isShow
//     */
//    private void setHangUpFrameShow(boolean isShow) {
//        rlHangUpFrame.setVisibility(isShow ? View.VISIBLE : View.GONE);
//        isHangUpFrameShow = isShow;
//    }

    /**
     * 检查视频音频权限
     */
    private void checkCamera() {
        if (Checker.CameraCheck(mContext)) {
            Log.d(TAG, "打开摄像头成功");
        } else {
            Log.d(TAG, "打开摄像头失败。请检查摄像头权限");
        }
        if (Checker.RecordAudioCheck()) {
            Log.d(TAG, "打开录音设备成功");
        } else {
            Log.d(TAG, "打开录音设备失败。请检查录音设备权限");
        }
    }

    @Override
    public void VideoChatHangUp(String joinChannel) {
        Logger.d(TAG, "接收到视频挂断的消息了--------");
        if (joinChannel != "" && sourceChannelid.equals(joinChannel)) {
            if (!BaseConstant.INSTRUCT_ACTION_PHONE_MONITOR_DEVICE.equals(flagCall)) {
                toast(getResources().getString(R.string.hint_call_hang_up));
            }
            if (!isCall) {
                if (isRenderJoin) {//说明已建立连接，挂断后推送通话时长
                    EventBus.getDefault().post(new AnyEventType(Constants.FLAG_SEND_TEXT_MSG_DURATION, chronometer.getText().toString()));
                } else {//未建立连接，直接挂断
                    EventBus.getDefault().post(new AnyEventType(Constants.FLAG_SEND_TEXT_MSG_REFUSED, ""));
                }
            }
            MyApp.getInstance().setVideoChating(false);
            finish();
        } else {
            Logger.d(TAG, "正在聊天的时候来视频聊天了，给" + joinChannel + "推送视频聊天占线的消息");
            publicPresenter.pushMsg(joinChannel, Util.setRequestPushMsgInfo(BaseConstant.INSTRUCT_ACTION_LINE_BUSY, "", "", MyApp.getInstance().getSpManager().getChannelId(), joinChannel));
        }
    }

    @Override
    public void lineBusy(String sourceChannelid) {//占线
        EventBus.getDefault().post(new AnyEventType(Constants.FLAG_SEND_TEXT_LINE_BUSY, ""));
        finish();
    }

    @Subscribe
    public void getEventBus(AnyEventType anyEventType) {
        String flag = anyEventType.getFlag();
        if (Constants.FLAG_SEND_TEXT_MSG_REFUSED_NOTICE_VIDEO_CHAT.equals(flag)) {
            String joinChannel = anyEventType.getContent();
            VideoChatHangUp(joinChannel);
        }
    }


    private pgLibLiveMultiCapture.OnEventListener mOnEvent = new pgLibLiveMultiCapture.OnEventListener() {
        @Override
        public void event(String sAct, String sData, String sRender) {
            if ("VideoStatus".equals(sAct)) {
                //视频状态报告
            } else if ("Notify".equals(sAct)) {
                Logger.d(TAG, "Receive notify: data=" + sData);
            } else if (sAct.equals("RenderJoin")) {
                RenderJoin(sRender);
                Logger.d(TAG, "Render join: render=" + sRender);
            } else if (sAct.equals("RenderLeave")) {
                RenderLeave(sRender);
                Logger.d(TAG, "Render leave: render=" + sRender);
                if (!"".equals(msObjPeer)) {
                    manager.videoStop(msObjPeer);
                }
            } else if ("Message".equals(sAct)) {//接收到消息
                Logger.d(TAG, "Receive msg: data=" + sData + ", render=" + sRender);
            } else if ("Login".equals(sAct)) {//登陆回调
                if ("0".equals(sData)) {
                    Log.d(TAG, "Login success");
                } else {
                    Log.d(TAG, "Login failed, error=" + sData);
                }
            } else if ("Logout".equals(sAct)) {
                Log.d(TAG, "Logout");
            } else if ("Connect".equals(sAct)) {//连接到采集端
                Log.d(TAG, "Connect to capture");
            } else if ("Disconnect".equals(sAct)) {//断开
                Log.d(TAG, "Diconnect to capture");
            } else if ("Offline".equals(sAct)) {//离线
                Log.d(TAG, "Capture offline");
            } else if ("LanScanResult".equals(sAct)) {
                Log.d(TAG, "Lan scan result: " + sData);
            } else if ("ForwardAllocReply".equals(sAct)) {
                Log.d(TAG, "Forward alloc relpy: error=" + sData);
            } else if ("ForwardFreeReply".equals(sAct)) {
                Log.d(TAG, "Forward free relpy: error=" + sData);
            } else if ("VideoCamera".equals(sAct)) {
                Log.d(TAG, "The picture is save to: " + sData);
            } else if ("SvrNotify".equals(sAct)) {
                Log.d(TAG, "Receive server notify: " + sData);
            }
        }
    };

    private OnEvent extEvent = new OnEvent() {
        @Override
        public void onGetObjPeer(String sObjPeer, String sParam) {
            Log.d(TAG, "sObjPeer=" + sObjPeer);
            msObjPeer = sObjPeer;
            int iErr = manager.extVideoStart(sObjPeer);
            if (iErr > PG_ERR_Normal) {
                Log.d(TAG, "extVideo.start : iErr = " + iErr);
            }
            iErr = manager.sendGetObjPeerNotify(sObjPeer, sParam);
            if (iErr > PG_ERR_Normal) {
                Log.d(TAG, "extVideo.sendGetObjPeerNotify : iErr = " + iErr);
            }
        }

        @Override
        public void onGetObjPeerReply(int uErr, String sParam) {
            if (uErr > PG_ERR_Normal) {
                Log.d(TAG, "ExtVideo.OnEvent onGetObjPeerReply : iErr = " + uErr + " sParam = " + sParam);
            }
        }

        @Override
        public void onGetObjPeerNotify(String sObjPeer, String sParam) {
            Log.d(TAG, "sObjPeer=" + sObjPeer);
            msObjPeer = sObjPeer;
            int iErr = manager.extVideoStart(sObjPeer);
            if (iErr > PG_ERR_Normal) {
                Log.d(TAG, "extVideo.start : iErr = " + iErr);
            }
        }

        @Override
        public int onVideoSync(String sObj, String sAct, String sObjPeer) {
            if ("0".equals(sAct)) {
                Logger.d(TAG, "onVideoSync  0");
            } else if ("1".equals(sAct)) {
                Logger.d(TAG, "onVideoSync  1");
            }
            return 0;
        }

        @Override
        public int onVideoStart(String sObj, int uHandle, String sObjPeer) {
            int iErr = manager.videoHandle(sObj, PG_ERR_Normal, uHandle, sObjPeer, otherSurfaceView, false);
            if (iErr > PG_ERR_Normal) {
                Log.d(TAG, "extVideo.videoHandle : iErr = " + iErr);
            }
            return 0;
        }

        @Override
        public int onVideoStartReply(String sObj, int uErr) {
            if (uErr > PG_ERR_Normal) {
                Log.d(TAG, "ExtVideo.OnEvent onVideoStartReply : sObj = " + sObj + " uErr = " + uErr);
            } else {
                Log.d(TAG, "视频打开成功！");
            }
            return 0;
        }

        @Override
        public int onVideoStop(String sObj, String sObjPeer) {
            Log.d(TAG, "onVideoStop sObj = " + sObj + " sObjPeer = " + sObjPeer);
            return 0;
        }

        @Override
        public void onVideoFrameStat(String sObj, String sData) {
            Log.d(TAG, "onVideoFrameStat sObj = " + sObj + " sData = " + sData);

        }

        @Override
        public int onAudioSync(String sObj, String sAct) {
            return 0;
        }

        @Override
        public int onAudioStart(String sObj, String sObjPeer) {
            return 0;
        }

        @Override
        public int onAudioStartRelay(String sObj, int uErr) {
            return 0;
        }

        @Override
        public int onAudioStop(String sObjPeer) {
            return 0;
        }
    };


    private void RenderLeave(String sPeer) {//离开
        Logger.d(TAG, "离开");
//        if (!isCall) {
//            EventBus.getDefault().post(new AnyEventType(Constants.FLAG_SEND_TEXT_MSG_DURATION, chronometer.getText().toString()));
//        }
        finish();
    }

    private void RenderJoin(String sPeer) {//连接
        Logger.d(TAG, "对方已连接，关掉计时器 关掉声音");
        stopPreAction();
        isRenderJoin = true;
//        if (!isVideoMonitor) {
//            timerCancal.startControlTimer();
//        }
        startTimeChronometer();
    }

    /**
     * 停掉铃声跟计时
     */
    private void stopPreAction() {
        timerControlView.endControlTimer();
        //关掉声音
        PlayVoice.stopVoice();
    }

    /**
     * 停止计时
     */
    public void stopTimeChronometer() {
        chronometer.stop();
    }

    /**
     * 开始计时
     */
    public void startTimeChronometer() {
        chronometer.setVisibility(View.VISIBLE);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    /**
     * 退出直播模块
     */
    private void liveStop() {
        layoutPrvw.removeAllViews();
        layoutVideo.removeAllViews();
        if (otherSurfaceView != null) {
            manager.liveStop(otherSurfaceView);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        stopTimeChronometer();
    }

    @Override
    protected void onDestroy() {
        MyApp.getInstance().setVideoChating(false);
        try{
            stopPreAction();
            liveStop();

            devicePresenter.releaseP2PByGuid(guid, token);
            EventBus.getDefault().post(new AnyEventType(Constants.FLAG_RE_OPEN_AWAKEN, ""));
            EventBus.getDefault().post(new AnyEventType(Constants.FLAG_VIDEOCHAT_ACTIVITY_FINISH, "" ));
        }catch (Exception e){
            Logger.d(TAG,e.toString());
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void onHomePressed() {
    }

    @Override
    public void showReceiveNewMsg(String type, String id) {

    }
}
