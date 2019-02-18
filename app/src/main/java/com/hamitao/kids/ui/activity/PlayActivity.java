package com.hamitao.kids.ui.activity;

import android.content.Intent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.hamitao.aispeech.engine.TTSEngine;
import com.hamitao.aispeech.util.InstructUtils;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.model.MediaInfo;
import com.hamitao.framework.network.NetworkStatus;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.utils.PlayHint;
import com.hamitao.framework.utils.StringUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.manager.play.Controller;
import com.hamitao.kids.manager.play.ImgPlayer;
import com.hamitao.kids.manager.play.MediaPlayer;
import com.hamitao.kids.manager.play.PlayFactory;
import com.hamitao.kids.manager.play.PlayManager;
import com.hamitao.kids.manager.play.RefreshView;
import com.hamitao.kids.model.AnyEventType;
import com.hamitao.kids.model.PlayInfo;
import com.hamitao.kids.mvp.ipresenter.IDevicePresenter;
import com.hamitao.kids.mvp.presenter.DevicePresenterImpl;
import com.hamitao.kids.utils.ActivityUtils;
import com.hamitao.kids.utils.BrightUtil;
import com.hamitao.kids.utils.ResUtil;
import com.hamitao.kids.utils.ScreenUtil;
import com.hamitao.kids.utils.Util;
import com.hamitao.kids.utils.WindowUtils;
import com.hamitao.requestframe.entity.QueryContentInfo;
import com.hamitao.requestframe.entity.QueryNfcByIdInfo;
import com.hamitao.requestframe.entity.RequestQueryContentInfo;
import com.hamitao.requestframe.view.QueryContentView;
import com.hamitao.requestframe.view.QueryNfcByIdView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class PlayActivity extends BaseMsgActivity {
    @BindView(R.id.mediaplayer)
    MediaPlayer mMediaPlayer;

    @BindView(R.id.view_refresh)
    RefreshView mRefreshView;

    @BindView(R.id.controller)
    Controller mController;

    @BindView(R.id.imageplayer)
    ImgPlayer imgPlayer;

    @BindView(R.id.rl_bg)
    RelativeLayout rlBg;

    private String refreshContent = "";//用于记录刷新内容
    private String refreshFlag = "";//用于记录刷新类型
    private String curPlayTypeRes;//获取当前点播动画的资源文件

    //查询接口
    private IDevicePresenter devicePresenter;

    private boolean isCancalRequest = false;//数据请求是否取消

    @Override
    public void onHomePressed() {
        super.onHomePressed();
    }

    private PlayFactory mPlayFactory;//播放工厂
    private GestureDetector gestureDetector;
    private BrightUtil brightUtil;
    private int maxBright;
    public boolean isloading;
    private PlayHint playHint;

    @Override
    public void setActivityView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play);
//        getWindow().setBackgroundDrawable(null);
    }

    @Override
    public void initData() {
        ScreenUtil.screenOn(mActivity);
        ActivityUtils.getInstance().addActivity(Constants.PLAY_ACTIVITY, this);
        playHint = new PlayHint(mContext);
        rlBg.setBackgroundResource(R.drawable.bg2);
        init();
        initBright();
        initManager();
        dealIntentData(getIntent());

        //隐藏弹窗
        WindowUtils.hidePopupWindow();

        if (!isNetworkAvailable()) {
            speak(FuncTitle.CONTENT_VOICE_HINT_NO_NET_UPDATA_CONTACT.toString());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        dealIntentData(intent);
    }


    private void init() {
        mRefreshView.setOnRefreshClickListener(mOnRefreshClickListener);
        gestureDetector = new GestureDetector(this, mOnGestureListener);
    }


    /**
     * 初始化亮度
     */
    private void initBright() {
        brightUtil = new BrightUtil(this);
        maxBright = brightUtil.getMaxBright();
    }

    private void initManager() {
        devicePresenter = new DevicePresenterImpl(mContext, mQueryContentView, mQueryNfcByIdView);
        mPlayFactory = new PlayFactory(mActivity, mMediaPlayer, imgPlayer, mController);
    }


    /**
     * 事件处理
     */
    private void dealIntentData(Intent intent) {
        String flagExtra = intent.getStringExtra(Constants.FLAG_ENTER_PLAY_ACTIVITY);

        if (Constants.FLAG_ENTER_PLAY_ACTIVITY_RANDOM_PLAY.equals(flagExtra)) {
            showLoading();
            String funcFlag = intent.getStringExtra(Constants.FLAG_ENTER_PLAY_ACTIVITY_DATA);
            String keyWord = ResUtil.queryCategoryContent(funcFlag);
            queryThreeDataByKey(keyWord);
            refreshContent = keyWord;
            refreshFlag = Constants.FLAG_ENTER_ROBOT_ACTIVITY_MAIN_FUNC_CONTENT_PLAY;
        } else if (Constants.FLAG_ENTER_PLAY_ACTIVITY_NFC_QUERY.equals(flagExtra)) {
            //扫码
            Logger.d(TAG, "扫码-------");
            showLoading();
            String contentId = intent.getStringExtra(Constants.FLAG_ENTER_PLAY_ACTIVITY_DATA);
            devicePresenter.queryNfcById(contentId);
            refreshContent = contentId;
            refreshFlag = Constants.FLAG_ENTER_ROBOT_ACTIVITY_NFC_QUERY;
        } else if (Constants.FLAG_ENTER_PLAY_ACTIVITY_PUSH.equals(flagExtra)) {
            //推送
            Logger.d(TAG, "处理推送-------");
            showLoading();
            String content = intent.getStringExtra(Constants.FLAG_ENTER_PLAY_ACTIVITY_DATA);
            String type = StringUtil.getStringBefore(content);
            String id = StringUtil.getStringAfter(content);
            queryContentByType(type, id);
            refreshContent = content;
            refreshFlag = Constants.FLAG_ENTER_ROBOT_ACTIVITY_PUSH;
        } else if (Constants.FLAG_ENTER_PLAY_ACTIVITY_PLAY_CONTENT.equals(flagExtra)) {
            //处理播放内容
            List<MediaInfo> mediaInfos = (List<MediaInfo>) intent.getSerializableExtra(Constants.FLAG_ENTER_PLAY_ACTIVITY_DATA);
            Logger.d(TAG, "处理播放内容-------    " + mediaInfos.size());
            setPlayContent(mediaInfos);
        } else if (Constants.FLAG_ENTER_PLAY_ACTIVITY_RESUME.equals(flagExtra)) {//继续播放的数据
            String playStr = (String) intent.getSerializableExtra(Constants.FLAG_ENTER_PLAY_ACTIVITY_DATA);
            Logger.d(TAG, "不在PlayActivity接收到继续播放了");
            resumePlay(playStr);
        } else if (Constants.FLAG_ENTER_PLAY_ACTIVITY_WAKE_UP_RESULT.equals(flagExtra)) {//唤醒播放数据
            List<MediaInfo> mediaInfos = (List<MediaInfo>) intent.getSerializableExtra(Constants.FLAG_ENTER_PLAY_ACTIVITY_DATA);
            setPlayContent(mediaInfos);
        }
    }


    @Override
    public void wakeUpPlay(List<MediaInfo> mediaInfos) {
        setPlayContent(mediaInfos);
    }

    /**
     * 继续播放
     */
    private void resumePlay(String playStr) {
        PlayInfo playInfo = MyApp.getInstance().getSpManager().getPlayInfos(playStr);
        if (playInfo != null) {
            int curPlayIndex = playInfo.getCurPlayIndex();
            int curPlayTime = playInfo.getCurPlayTime();
            List<MediaInfo> mediaInfos = playInfo.getMediaInfos();
            Logger.d(TAG, "curPlayIndex=" + curPlayIndex + " \ncurPlatTime=" + curPlayTime + "\nmediaInfos=" + mediaInfos.size());
            if (mediaInfos != null && mediaInfos.size() > 0) {
                initResType();
                mPlayFactory.setRandomPlayType(curPlayTypeRes);
                mPlayFactory.onResumePlay(curPlayIndex, curPlayTime, mediaInfos);
                return;
            }
        } else {
            playHint.playFuncTitle(FuncTitle.CONTENT_NO_CONTENT.toString());
        }
    }

    private void initResType() {
        if (getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
            curPlayTypeRes = PlayManager.getGifAnimTypeJgw(refreshContent);
        }else{
            curPlayTypeRes = PlayManager.getGifAnimType(refreshContent);
        }
    }

    //查询三级分类的数据
    private void queryThreeDataByKey(String keyWork) {
        RequestQueryContentInfo info = new RequestQueryContentInfo();
        List<String> categoryList = new ArrayList<>();
        categoryList.add(keyWork);
        info.setCategoryList(categoryList);
        devicePresenter.queryContent(info);
    }

    private void hintLoading() {
        isloading = false;
        mMediaPlayer.showOrHideLoading(false);
    }

    private void showLoading() {
        isloading = true;
        mMediaPlayer.showOrHideLoading(true);
    }

    //查询内容
    private QueryContentView mQueryContentView = new QueryContentView() {
        @Override
        public void onSuccess(QueryContentInfo info) {
            hintLoading();
            if (isCancalRequest) {
                Logger.d(TAG, "界面退出，不执行以下操作");
                return;
            }
            Logger.d(TAG, "三级功能  数据请求成功");
            List<MediaInfo> mediaInfos = Util.getMediaInfoByContentInfo(info);
            Logger.d(TAG, "mediaInfos=" + mediaInfos.size());

            if (mediaInfos != null && mediaInfos.size() > 0) {
                setPlayContent(mediaInfos);
            } else {
                noDataRemind();
            }
        }

        @Override
        public void onError(String result) {
            Logger.d(TAG, "数据请求失败: " + result);
            if (isCancalRequest) {
                Logger.d(TAG, "界面退出，不执行以下操作");
                return;
            }
            hintLoading();
            noDataRemind();
        }
    };

    //查询nfc
    private QueryNfcByIdView mQueryNfcByIdView = new QueryNfcByIdView() {
        @Override
        public void onSuccess(QueryNfcByIdInfo querynfcbyidInfo) {
            hintLoading();
            Logger.d(TAG, "根据条码查询卡内容 成功 " + querynfcbyidInfo.getResult());
            if (BaseConstant.SUCCESS.equals(querynfcbyidInfo.getResult())) {
                List<QueryNfcByIdInfo.ResponseDataObjBean.NFCCardsBean> nfcCardsBean = querynfcbyidInfo.getResponseDataObj().getNFCCards();
                List<QueryNfcByIdInfo.ResponseDataObjBean.NFCCardsBean.ContentDescBean.MediaListBean> mediaListBeans = Util.getMediaListByNfcId(nfcCardsBean);
                if (mediaListBeans != null && mediaListBeans.size() != 0) {
                    List<MediaInfo> mediaInfos = Util.getMediaInfoByMediaList(mediaListBeans);
                    setPlayContent(mediaInfos);
                } else {
                    if (nfcCardsBean != null && nfcCardsBean.size() != 0) {
                        String infoType = nfcCardsBean.get(0).getInfotype();
                        String info = nfcCardsBean.get(0).getInfo();
                        queryContentByType(infoType, info);
                    } else {
//                        noDataRemind();
                        toast("没有制卡信息");
                        finish();
                    }
                }
            }
        }

        @Override
        public void onError(String result) {
            hintLoading();
            Logger.d(TAG, "根据条码查询卡内容 失败 " + result);
            noDataRemind();
        }
    };


    //未获取到数据提醒
    private void noDataRemind() {
//        ttsManager.speak(getResources().getString(R.string.hint_no_data_refresh));
//        playHint.playFuncTitle(FuncTitle.CONTENT_VOICE_HINT_NO_DATA.toString());
        if (!isNetworkAvailable()) {
            playHint.playFuncTitle(FuncTitle.CONTENT_VOICE_HINT_NET_DISCONNTCT.toString());
        } else {
            speakTTS(getStrByRes(R.string.hint_no_data_refresh), TTSEngine.TTS_FLAG_COMP_NO_RESULT);
        }
        mRefreshView.setVisibility(View.VISIBLE);
    }

    private RefreshView.OnRefreshClickListener mOnRefreshClickListener = new RefreshView.OnRefreshClickListener() {
        @Override
        public void onClick() {
            stopTTS();

            mRefreshView.setVisibility(View.GONE);
            showLoading();
            if (Constants.FLAG_ENTER_ROBOT_ACTIVITY_MAIN_FUNC_CONTENT_PLAY.equals(refreshFlag)) {
                queryThreeDataByKey(refreshContent);
            } else if (Constants.FLAG_ENTER_ROBOT_ACTIVITY_NFC_QUERY.equals(refreshFlag)) {
                devicePresenter.queryNfcById(refreshContent);
            } else if (Constants.FLAG_ENTER_ROBOT_ACTIVITY_PUSH.equals(refreshFlag)) {
                String type = StringUtil.getStringBefore(refreshContent);
                String id = StringUtil.getStringAfter(refreshContent);
                queryContentByType(type, id);
            }
        }
    };

    private void setPlayContent(List<MediaInfo> mediaInfos) {
        initResType();
        mPlayFactory.setRandomPlayType(curPlayTypeRes);
        mPlayFactory.setMediaContent(mediaInfos);
    }

    @Override
    public void dealToMediaPlayContent(String type, String id) {
        queryContentByType(type, id);
    }

    @Override
    public void dealPlayContent(List<MediaInfo> mediaInfos) {//在robotActivity界面重写此方法
        ScreenUtil.screenOn(mActivity);//点亮屏幕
        hintLoading();
        if (isCancalRequest) {
            return;
        }
        if (mediaInfos != null && mediaInfos.size() != 0) {
            setPlayContent(mediaInfos);
        } else {
            Logger.d(TAG, "播放内容为空 ");
//            ttsManager.speak(getStrByRes(R.string.hint_no_data_refresh));
//            playHint.playFuncTitle(FuncTitle.CONTENT_VOICE_HINT_NO_DATA.toString());
            speakTTS(getStrByRes(R.string.hint_no_data_refresh), TTSEngine.TTS_FLAG_COMP_NO_RESULT);
        }
    }

    @Override
    public void timingClosure() {
        ActivityUtils.getInstance().delActivity(Constants.PLAY_ACTIVITY);
        EventBus.getDefault().post(new AnyEventType(Constants.FLAG_COMP_TIMING_CLOSURE, ""));
//        finish();
//        speakTTS(InstructUtils.respondTimingClosure(), TTSEngine.TTS_FLAG_COMP_TIMING_CLOSURE);
    }


    @Override
    public void dealPausePlayEvent() {
        mPlayFactory.onPause();
    }

    @Override
    public void dealResumePlayEvent() {
        mPlayFactory.onResume();
    }

    @Override
    public void dealOnNextEvent() {
        mPlayFactory.onPlayNext();
    }

    @Override
    public void alarmClockRang() {
        mPlayFactory.onPause();
    }

    @Override
    protected void onPause() {
        //暂停播放器
        mPlayFactory.onPause();
        mPlayFactory.destory(MyApp.getInstance().getSpManager());
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        ActivityUtils.getInstance().delActivity(Constants.PLAY_ACTIVITY);
        //释放播放器
        mPlayFactory.releasePlayer();
        isCancalRequest = true;
//        ttsManager.playerStop();
        mPlayFactory.setPlayState(false);
        super.onDestroy();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mPlayFactory.cancelHideCtrlLayoutMessage();
//                break;
//            case MotionEvent.ACTION_UP:
//                mPlayFactory.sendHideCtrlLayoutMessage();
//                break;
//        }
        //把触摸时间传递给onTouch
        return gestureDetector.onTouchEvent(event);
    }

    private GestureDetector.SimpleOnGestureListener mOnGestureListener = new GestureDetector.SimpleOnGestureListener() {

        private boolean isDownLeft;//是否是在屏幕左边按下的
        private int curVolume;
        private int curBright;

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //3、计算在屏幕y方向的滑动距离（e1  - e2）
            float distanceYY = e1.getY() - e2.getY();
            if (isDownLeft) {
                changeBrighness(distanceYY, curBright);
            } else {
                mPlayFactory.changeVolume(distanceYY, curVolume);
            }

            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            curVolume = mPlayFactory.getStreamVolume();
            isDownLeft = e.getX() < ScreenUtil.getScreenWidth(mContext) / 2;
            curBright = getBrightness();
            return super.onDown(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (isloading) {
                return true;
            }
            mPlayFactory.showOrHideCtrlLayout();
            return true;
        }
    };

    /**
     * 改变屏幕亮度
     */
    public void changeBrighness(float distanceY, int curBright) {
        float scale = ((float) maxBright) / ScreenUtil.getScreenHeight(mContext);
        int bright = (int) (distanceY * scale);
        int resultBright = curBright + bright;
        if (resultBright < 0) {
            resultBright = 0;
        } else if (resultBright > maxBright) {
            resultBright = maxBright;
        }
        setCurBright(resultBright);
    }


    /**
     * 获取当前亮度
     *
     * @return
     */
    private int getBrightness() {
        return brightUtil.getBrightness();
    }

    /**
     * 设置当前亮度
     *
     * @param value
     */
    private void setCurBright(int value) {
        brightUtil.setScreenBrightness(value);
    }

    @Subscribe
    public void getEventBus(AnyEventType anyEventType) {
        String flag = anyEventType.getFlag();
        if (Constants.FLAG_BAN_VIDEO_PLAY.equals(flag)) {
            if (mPlayFactory != null) {
                mPlayFactory.onPause();
                playHint.playFuncTitle(FuncTitle.CONTENT_TOO_LONG_REST.toString());
                finish();
            }
        } else if (Constants.FLAG_TIMING_CLOSURE.equals(flag)) {//定时关闭
            timingClosure();
        } else if (TTSEngine.TTS_FLAG_COMP_TEXT_READER.equals(flag)) {
            mPlayFactory.onPlayNext();
        } else if (TTSEngine.TTS_FLAG_COMP_TIMING_CLOSURE.equals(flag)) {
            ActivityUtils.getInstance().delActivity(Constants.PLAY_ACTIVITY);
        }
//        else if (TTSEngine.TTS_FLAG_COMP_PLAY_CONTENT_TITLE.equals(flag)) {
//            mPlayFactory.playContent();
//        }
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }
}
