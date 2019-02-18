package com.hamitao.kids.manager.play;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.hamitao.aispeech.engine.TTSEngine;
import com.hamitao.framework.callback.CompleteCallBack;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.model.MediaInfo;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.NumUtil;
import com.hamitao.framework.utils.TimeUtil;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.manager.SPManager;
import com.hamitao.kids.manager.play.callback.ControllerCallBack;
import com.hamitao.kids.manager.play.callback.MediaPlayerCallBack;
import com.hamitao.kids.manager.play.callback.OnTextPlayCallBack;
import com.hamitao.kids.model.PlayInfo;
import com.hamitao.kids.utils.PlayHint;

import java.util.List;

public class PlayFactory implements IPlayFactory {
    private static final String TAG = "PlayFactory";
    private MediaPlayer mediaPlayer;
    private ImgPlayer imgPlayer;
    private Controller mController;
    private List<MediaInfo> mediaInfos;

    private Activity mActivity;

    private int curPlayIndex;//当前播放位置

    private String curPlayTypeRes;

    private PlayHint playHint;

    /**
     * 显示系统时间
     */
    private static final int SHOW_SYSTEM_TIME = 0;
    /**
     * 更新播放进度
     */
    private static final int UPDATE_PLAY_PROGRESS = 1;
    /**
     * 隐藏控制面板
     */
    private static final int HIDE_CTRL_LAYOUT = 2;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SHOW_SYSTEM_TIME:
//                    showSystemTime();
                    break;
                case UPDATE_PLAY_PROGRESS:
                    updatePlayProgress();
                    break;
                case HIDE_CTRL_LAYOUT:
                    showOrHideCtrlLayout();
                    break;
            }
        }
    };
    private int curPlayTime;

    public PlayFactory(Activity activity, MediaPlayer mediaPlayer, ImgPlayer imgPlayer, Controller controller) {
        this.mActivity = activity;
        this.mediaPlayer = mediaPlayer;
        this.imgPlayer = imgPlayer;
        this.mController = controller;
        playHint = new PlayHint(activity);
        setCallBack();

    }

    public void setRandomPlayType(String res) {//点播分类（动画/国学诗词等）
        this.curPlayTypeRes = res;
    }

    @Override
    public void setMediaContent(List<MediaInfo> infos) {
        Logger.d(TAG, "设置播放资源");
        curPlayIndex = 0;
        if (mediaInfos != null && mediaInfos.size() != 0) {
            mediaInfos.clear();
        }
        mediaInfos = infos;

        playMediaContents();
    }

    private boolean isCompleteFinish = false;//是否所有的数据都播放完成了

    //播放媒体
    private void playMediaContents() {
        if (curPlayIndex >= mediaInfos.size()) {
            Logger.d(TAG, "所有数据播放完成，结束播放");
            isCompleteFinish = true;
            mActivity.finish();
            return;
        }
        if (curPlayIndex < 0) {
            Logger.d(TAG, "已经是第一条数据了");
//            ToastUtil.showToast(mActivity,"已经是第一条数据了");/
            curPlayIndex = 0;
            return;
        }

        mediaPlayer.hideAnim();
        //播放另一个媒体的时候
        mediaPlayer.showOrHideMask(true);
        MediaInfo mediaInfo = mediaInfos.get(curPlayIndex);
        String mediaTitle = mediaInfo.getMediaTitle();
        if (mediaInfo.getMediaType() != null && mediaInfo.getMediaContent() != null && mediaTitle != null) {
            playMeidaContent(mediaInfo, 0);
//            MyApp.getInstance().getTtsEngine().speak(mediaTitle, TTSEngine.TTS_FLAG_COMP_PLAY_CONTENT_TITLE);
        } else {
            onPlayPrevious();
        }
    }


    private void playMeidaContent(MediaInfo mediaInfo, int msec) {
        int type;
        String mediaType = mediaInfo.getMediaType();//类型
        String mediaTitle = mediaInfo.getMediaTitle();//标题
        String mediaContent = mediaInfo.getMediaContent();
        if (!BaseConstant.TYPE_STREAMINGMEDIA.equals(mediaType) && !BaseConstant.TYPE_MUSIC.equals(mediaType)) {
            Logger.d(TAG, "mediaContent===" + mediaContent);
            mediaContent = MyApp.getInstance().getOssManager().getOssConstrainedObjectURL(mediaContent);//媒体类型
        }
        Logger.d(TAG, "mediaType=" + mediaType + "\n   mediaContent=" + mediaContent);
        if (TextUtils.isEmpty(mediaContent)) {
            //媒体内容为空，直接跳过
            onPlayNext();
            return;
        }
        if (BaseConstant.TYPE_AUDIO.equals(mediaType)) {//音频
            type = Constants.FLAG_PLAY_AUDIO;
        } else if (BaseConstant.TYPE_MUSIC.equals(mediaType)) {
            type = Constants.FLAG_PLAY_AUDIO;
        } else if (BaseConstant.TYPE_VIDEO.equals(mediaType) || BaseConstant.TYPE_STREAMINGMEDIA.equals(mediaType)) {//视频/streamingmedia
            type = Constants.FLAG_PLAY_VIDEO;
            if (!MyApp.getInstance().isAllowPlayVideo()) {
                Logger.d(TAG, "提示现在已经不允许播放视频了");
                onPause();
                hintNotAllowedPlayback();
                mActivity.finish();
                return;
            }
        } else if (BaseConstant.TYPE_IMAGE.equals(mediaType)) {//图片
            type = Constants.FLAG_PLAY_IMAGE;
        } else if (BaseConstant.TYPE_ANIMATION.equals(mediaType)) {//动画（gif）
            type = Constants.FLAG_PLAY_ANIMATION;
        } else if (BaseConstant.TYPE_TEXT.equals(mediaType)) {//文本
            type = Constants.FLAG_PLAY_TEXT;
        } else {//找不到对应的格式直接播放下一个
            type = Constants.FLAG_PLAY_OTHER;
        }
        playMediaForType(type, mediaTitle, mediaContent, msec);
    }

    private void playMediaForType(int type, String title, String mediaContent, int msec) {
        Logger.d(TAG, "title=" + title);
        title = NumUtil.deleteNumber(title);
        MyApp.getInstance().setVideoPlaying(false);
        if (type == Constants.FLAG_PLAY_AUDIO || type == Constants.FLAG_PLAY_VIDEO || type == Constants.FLAG_PLAY_STREAMINGMEDIA) {
            mediaPlayer.setVisibility(View.VISIBLE);
            mediaPlayer.setCurPlayAnimType(curPlayTypeRes);
            mediaPlayer.playMedia(type, title, mediaContent, msec);
        } else if (type == Constants.FLAG_PLAY_ANIMATION) {
            imgPlayer.setAnim(title, mediaContent);
        } else if (type == Constants.FLAG_PLAY_IMAGE) {
            imgPlayer.setImage(title, mediaContent);
        } else if (type == Constants.FLAG_PLAY_TEXT) {//文本
            TextPlayUtil.getTxtByUrl(mediaContent, onTextPlayCallBack);
        }
    }

    public void setCallBack() {
        mediaPlayer.setMediaPlayerCallBack(new MediaPlayerCallBack() {
            @Override
            public void onPrepared(String title) {
                setPlayState(true);
                Logger.d(TAG, "视频准备好了，开始播放=" + title);
                //隐藏背景
                mediaPlayer.showOrHideMask(false);
                //更新播放按钮
                mController.updataPlayBtnBg(false);
                //设置标题
                mController.setMediaTitle(title);
                //设置播放总时长
                mController.setVideoTotalTime(mediaPlayer.getVideoTotalTime());
                mController.setSeekBarMax(mediaPlayer.getSeekBarMaxTime());
                //更新播放总进度
                updatePlayProgress();
            }

            @Override
            public void onCompletion() {
                //播放完成
                mController.setVideoCurTime((String) TimeUtil.formatMillis(0));
                mController.setSeekBarProgress(0);
                onPlayNext();
            }

            @Override
            public void onMediaPlaying(boolean isVideo) {
                if (!isVideo) {
                    mController.showOrHideTitle(true);
                }
            }
        });

        mController.setControllerCallBack(new ControllerCallBack() {
            @Override
            public void onSeekBarChange(int progress) {
                mediaPlayer.setVideoSeekTo(progress);
            }

            @Override
            public void onPrevious() {//上一首
                onPlayPrevious();
            }

            @Override
            public void onNext() {//下一首
                onPlayNext();
            }

            @Override
            public void onPlayPause() {//播放/暂停
                pauseOrResumePlay();
            }

            @Override
            public void onSendHideCtrl() {//发送隐藏控制面板的消息
                sendHideCtrlLayoutMessage();
            }

            @Override
            public void onCancelHideCtrl() {
                cancelHideCtrlLayoutMessage();
            }
        });


        imgPlayer.setPlayCallBack(new CompleteCallBack() {
            @Override
            public void onPlayComplete() {
                onComplete();
            }
        });
    }


    /**
     * 更新播放进度
     */
    private void updatePlayProgress() {
        mController.setVideoCurTime(mediaPlayer.getVideoCurTime());
        curPlayTime = mediaPlayer.getSeekBarCurTime();
        mController.setSeekBarProgress(curPlayTime);
        handler.sendEmptyMessageDelayed(UPDATE_PLAY_PROGRESS, 300);

    }

    /**
     * 提示不允许播放视频了
     */
    private void hintNotAllowedPlayback() {
//        ttsManager.speak(mActivity.getResources().getString(R.string.hint_more_than_video_playback_time));
        playHint.playFuncTitle(FuncTitle.CONTENT_TOO_LONG_REST.toString());
    }


    private OnTextPlayCallBack onTextPlayCallBack = new OnTextPlayCallBack() {
        @Override
        public void OnTextReader(String txt) {
            PlayHint.stopVoice();
            TTSEngine.getSingleton().speak(txt, TTSEngine.TTS_FLAG_COMP_TEXT_READER);
//            TuringEngine.getSingleton().speak(txt, TTSEngine.TTS_FLAG_COMP_TEXT_READER);
        }
    };


    @Override
    public void onPause() {//暂停播放
        if (!mediaPlayer.isVideoPlaying()) {
            return;
        }
        setPlayState(false);
        mediaPlayer.pausePlay();
        mController.updataPlayBtnBg(true);
    }

    @Override
    public void onResume() {//继续播放
        if (mediaPlayer.isVideoPlaying()) {
            return;
        }
        setPlayState(true);
        mediaPlayer.resumePlay();
        mController.updataPlayBtnBg(false);
    }

    @Override
    public void onResumePlay(int index, int playTime, List<MediaInfo> infos) {//继续退出后的播放
        if (mediaInfos != null && mediaInfos.size() != 0) {
            mediaInfos.clear();
        }
        mediaInfos = infos;
        curPlayIndex = index;
        if (curPlayIndex >= mediaInfos.size()) {
            curPlayIndex = mediaInfos.size() - 1;
        }
        MediaInfo mediaInfo = mediaInfos.get(curPlayIndex);
        mediaPlayer.hideAnim();
        playMeidaContent(mediaInfo, playTime);
    }

    @Override
    public void onPlayPrevious() {//上一首
        curPlayIndex--;
        playMediaContents();
    }

    @Override
    public void onPlayNext() {//下一首
        curPlayIndex++;
        playMediaContents();
    }

    @Override
    public void showOrHideCtrlLayout() {
        mController.showOrHideCtrlLayout();
    }

    /**
     * 发送隐藏控制面板的消息（3秒种后执行 ）
     */
    public void sendHideCtrlLayoutMessage() {
        cancelHideCtrlLayoutMessage();
        handler.sendEmptyMessageDelayed(HIDE_CTRL_LAYOUT, 3000);
    }

    /**
     * 取消隐藏控制面板的消息
     */
    public void cancelHideCtrlLayoutMessage() {
        handler.removeMessages(HIDE_CTRL_LAYOUT);
    }

    @Override
    public void changeVolume(float distanceYY, int curVolume) {
        mController.changeVolume(distanceYY, curVolume);
    }

    @Override
    public int getStreamVolume() {
        return mController.getStreamVolume();
    }

    @Override
    public void pauseOrResumePlay() {//播放/暂停-->是否是播放
        boolean isPause;
        if (mediaPlayer.isVideoPlaying()) {
            isPause = true;
            onPause();
        } else {
            onResume();
            isPause = false;
        }
        mController.updataPlayBtnBg(isPause);
    }

    @Override
    public void setPlayState(boolean isPlaying) {
        MyApp.getInstance().setPlayingState(isPlaying);
    }

    @Override
    public void releasePlayer() {
        mediaPlayer.getExoplayer().release();
    }

    private void onComplete() {//播放完成
        curPlayIndex++;
        playMediaContents();
    }


    public void destory(SPManager spManager) {
        savePlayInfo(MyApp.getInstance().getSpManager());
    }

    /**
     * 保存播放信息
     *
     * @param MyApp.getInstance().getSpManager()
     */
    private void savePlayInfo(SPManager spManager) {
//        handler.removeMessages(UPDATE_PLAY_PROGRESS);
        if (!isCompleteFinish) {
            handler.removeCallbacksAndMessages(null);
            Logger.d(TAG, "curPlayTime=" + curPlayTime);
            Logger.d(TAG, "curPlayIndex=" + curPlayIndex);
            if (mediaInfos != null && mediaInfos.size() > 0) {
                Logger.d(TAG, "mediaInfos=" + mediaInfos.size());
            }
            MyApp.getInstance().getSpManager().putPlayInfos(new PlayInfo(curPlayTime, curPlayIndex, mediaInfos));
        } else {
            MyApp.getInstance().getSpManager().putPlayInfos(null);
        }
    }


}
