package com.hamitao.kids.manager.play;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.GifDrawableUtil;
import com.hamitao.framework.utils.GifUtil;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.TimeUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.manager.play.callback.MediaPlayerCallBack;
import com.hamitao.kids.utils.PlayGifAnim;


/**
 * 媒体播放器
 */
public class MediaPlayer extends RelativeLayout implements IMediaPlayer {
    private static final String TAG = "MediaPlayer";
    private Context mContext;
    private LottieAnimationView gifImageView;
    private LottieAnimationView gifLoading;
    private RelativeLayout rlPlayAnim;
    private ProgressBar progressLoading;
    private ImageView ivMaskBg;

    private String curPlayAnimType = PlayGifAnim.FLAG_PLAY_GIF_ANIM_CHINESE_POETRY;//当前播放的动画类型
    private String curTitle;
    private int curType;


    private PlayerView playerView;
    SimpleExoPlayer player;
    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
    TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
    TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
    LoadControl loadControl = new DefaultLoadControl(new DefaultAllocator(true, C.DEFAULT_BUFFER_SEGMENT_SIZE),
            360000, 600000, 1000, 5000,
            C.LENGTH_UNSET, false);


    public MediaPlayer(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public MediaPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public MediaPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }


    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_media_play_new, this);

        //音频动画
        gifImageView = view.findViewById(R.id.iv_audio_play_anim);
        //动画播放背景
        rlPlayAnim = view.findViewById(R.id.rl_audio_play_anim);
        //背景
        ivMaskBg = view.findViewById(R.id.iv_mask_bg);
        //gif加载
        gifLoading = view.findViewById(R.id.gif_loading);
        //金国威加载
        progressLoading = view.findViewById(R.id.progress_loading_play);
        //视频播放
        playerView = view.findViewById(R.id.videoplayer);
        //初始化播放器
        player = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector, loadControl);
        playerView.hideController();
        //播放器绑定视图
        playerView.setPlayer(player);

        //播放器监听
        player.addVideoListener(new VideoListener() {
            @Override
            public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
                //视频尺寸变化
            }

            @Override
            public void onRenderedFirstFrame() {

            }
        });


        player.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                Log.d(TAG, "playerReady:" + playWhenReady + "    state:" + playbackState);

                if (playbackState == Player.STATE_IDLE) {

                } else if (playbackState == Player.STATE_BUFFERING) {

                } else if (playbackState == Player.STATE_READY) {
                    if (!playWhenReady) {
                        return;
                    }
                    if (playerCallBack != null) {
                        playerCallBack.onPrepared(curTitle);
                    }
                    if (curType == Constants.FLAG_PLAY_VIDEO || curType == Constants.FLAG_PLAY_STREAMINGMEDIA) {//视频类
                        MyApp.getInstance().setVideoPlaying(true);
                        if (playerCallBack != null) {
                            playerCallBack.onMediaPlaying(true);
                        }
                    } else if (curType == Constants.FLAG_PLAY_AUDIO) {
                        //音频则播放动画
                        if (player.getPlayWhenReady()) {
                            playAnim(curPlayAnimType);
                        }
                        if (playerCallBack != null) {
                            playerCallBack.onMediaPlaying(false);
                        }
                    }
                    showOrHideLoading(false);

                } else if (playbackState == Player.STATE_ENDED) {
                    playerCallBack.onCompletion();
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });
    }

    public SimpleExoPlayer getExoplayer() {
        return player;
    }

    public void setCurPlayAnimType(String res) {
        this.curPlayAnimType = res;
    }

    public void playMedia(int type, String title, String content, int desc) {
        this.curTitle = title;
        this.curType = type;
        Logger.d(TAG, "设置媒体内容：type=" + type + "\n    title=" + title + "\n   content=" + content);
        showOrHideLoading(true);

        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mContext,
                Util.getUserAgent(mContext, "xiaotaotongxue"), bandwidthMeter);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        Uri uri = Uri.parse(content);
        MediaSource videoSource = new ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory, null, null);
        //设置数据源
        player.prepare(videoSource);
        player.seekTo(desc);
        //开始播放
        player.setPlayWhenReady(true);
    }


    private MediaPlayerCallBack playerCallBack;

    public void setMediaPlayerCallBack(MediaPlayerCallBack callBack) {
        this.playerCallBack = callBack;
    }


    @Override
    public String getVideoTotalTime() {
        return (String) TimeUtil.formatMillis(getSeekBarMaxTime());
    }

    @Override
    public String getVideoCurTime() {//获取当前播放位置
        return (String) TimeUtil.formatMillis(getSeekBarCurTime());
    }

    @Override
    public int getSeekBarMaxTime() {
        return (int) player.getDuration();
    }

    @Override
    public int getSeekBarCurTime() {
        return (int) player.getCurrentPosition();
    }

    @Override
    public void setVideoSeekTo(int progress) {
        player.seekTo(progress);
    }

    @Override
    public boolean isVideoPlaying() {
        return player.getPlayWhenReady();
    }

    @Override
    public void pausePlay() {
//        GifDrawableUtil.pauseGifAnim();
        PlayGifAnim.pause(gifImageView);
        player.setPlayWhenReady(false);
    }

    @Override
    public void resumePlay() {
//        GifDrawableUtil.resumeGifAnim();
        PlayGifAnim.resume(gifImageView);
        player.setPlayWhenReady(true);
    }

    @Override
    public void playAnim(String res) {
        rlPlayAnim.setVisibility(View.VISIBLE);
//        GifDrawableUtil.playGifAnim(mContext, res, gifImageView);
        PlayGifAnim.play(gifImageView, res);

    }

    @Override
    public void hideAnim() {
        rlPlayAnim.setVisibility(View.GONE);
    }

    @Override
    public void showOrHideLoading(boolean isShow) {
        if (isShow) {
            if (mContext.getResources().getString(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
                progressLoading.setVisibility(View.VISIBLE);
            }else{
                gifLoading.setVisibility(View.VISIBLE);
                PlayGifAnim.play(gifLoading, PlayGifAnim.FLAG_PLAY_GIF_LOADING);
            }
        } else {
            if (mContext.getResources().getString(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
                progressLoading.setVisibility(View.GONE);
            }else{
                gifLoading.setVisibility(View.GONE);
                PlayGifAnim.cancel(gifLoading);
            }
        }
    }

    @Override
    public void showOrHideMask(boolean isShow) {
        ivMaskBg.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }


}
