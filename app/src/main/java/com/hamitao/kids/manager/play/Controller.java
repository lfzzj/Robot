package com.hamitao.kids.manager.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.manager.play.callback.ControllerCallBack;
import com.hamitao.kids.utils.ScreenUtil;
import com.hamitao.kids.utils.VoiceUtil;

/**
 * 控制器
 */
public class Controller extends RelativeLayout implements IController {

    private Context mContext;
    private TextView tvTitle;

    private ImageButton ivMediaPrevious;//上一首
    private ImageButton ivMediaNext;//下一首
    private ImageButton ivMediaPause;//暂停/播放
    private ImageButton iBtnVoice;

    private TextView tvCurTime;
    private SeekBar sbVideo;
    private TextView tvTotalTime;

    private RelativeLayout rlCtrl;

    private VoiceUtil voiceUtil;//音量设置
    private int maxVolume;
    private View view;
    private SeekBar sbVoice;
    private int curVolume;

    public Controller(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public Controller(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public Controller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_controller, this);
        tvTitle = view.findViewById(R.id.tv_media_title);

        ivMediaPrevious = view.findViewById(R.id.ib_media_previous);
        ivMediaNext = view.findViewById(R.id.ib_media_next);
        ivMediaPause = view.findViewById(R.id.ib_media_pause);
        sbVoice = view.findViewById(R.id.sb_voice);
        iBtnVoice = view.findViewById(R.id.ibtn_voice);
        tvCurTime = view.findViewById(R.id.tv_cur_time);
        tvTotalTime = view.findViewById(R.id.tv_total_time);
        sbVideo = view.findViewById(R.id.sb_video);
        rlCtrl = view.findViewById(R.id.rl_controller);

        initVocie();
        initListener();
        initctrl();
    }

    /**
     * 初始化控制
     */
    private void initctrl() {
        rlCtrl.setVisibility(View.GONE);
    }


    @Override
    public void setMediaTitle(String title) {
        tvTitle.setText(title);
        if (MyApp.getInstance().isVideoPlaying()) {
            showOrHideTitle(false);
        }
    }

    @Override
    public void initListener() {
        sbVoice.setOnSeekBarChangeListener(mOnVoiceSeekBarChangeListener);
        sbVideo.setOnSeekBarChangeListener(mOnVideoSeekBarChangeListener);
        iBtnVoice.setOnClickListener(mOnClickListener);
        ivMediaPrevious.setOnClickListener(mOnClickListener);
        ivMediaNext.setOnClickListener(mOnClickListener);
        ivMediaPause.setOnClickListener(mOnClickListener);
    }

    @Override
    public void initVocie() {
        voiceUtil = new VoiceUtil(mContext);
        maxVolume = voiceUtil.getStreamMaxVolume();
        int currentVolume = getStreamVolume();
        sbVoice.setMax(maxVolume);
        sbVoice.setProgress(currentVolume);
    }


    @Override
    public void setVideoTotalTime(String totalTime) {//设置总时长
        tvTotalTime.setText(totalTime);
    }

    @Override
    public void setVideoCurTime(String curTime) {
        tvCurTime.setText(curTime);
    }

    @Override
    public void setSeekBarMax(int max) {
        sbVideo.setMax(max);
    }


    @Override
    public void setSeekBarProgress(int progress) {
        sbVideo.setProgress(progress);
    }

    @Override
    public void updataPlayBtnBg(boolean isPause) {
        ivMediaPause.setBackgroundResource(isPause ? R.drawable.icon_pause : R.drawable.icon_play);
    }

    @Override
    public void showOrHideCtrlLayout() {//显示/隐藏控制面板
        if (rlCtrl.getVisibility() == View.VISIBLE) {
            rlCtrl.setVisibility(View.GONE);
            if (MyApp.getInstance().isVideoPlaying()) {
                showOrHideTitle(false);
            }
            if (controllerCallBack != null) {
                controllerCallBack.onCancelHideCtrl();
            }

        } else {
            rlCtrl.setVisibility(View.VISIBLE);
            showOrHideTitle(true);
            if (controllerCallBack != null) {
                controllerCallBack.onSendHideCtrl();
            }
        }
    }

    @Override
    public void showOrHideTitle(boolean isShow) {//显示/隐藏title
        tvTitle.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }


    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (controllerCallBack != null) {
                controllerCallBack.onCancelHideCtrl();
            }
            switch (view.getId()) {
                case R.id.ibtn_voice:
                    mute();
                    break;
                case R.id.ib_media_previous://上一首
                    if (controllerCallBack != null) {
                        controllerCallBack.onPrevious();
                    }
                    break;
                case R.id.ib_media_next://下一首
                    if (controllerCallBack != null) {
                        controllerCallBack.onNext();
                    }
                    break;
                case R.id.ib_media_pause://暂停/播放
                    if (controllerCallBack != null) {
                        controllerCallBack.onPlayPause();
                    }
                    break;
            }
            if (controllerCallBack != null) {
                controllerCallBack.onSendHideCtrl();
            }
        }
    };

    /**
     * 静音或者恢复音量
     */
    private void mute() {
        if (getStreamVolume() > 0) {
            curVolume = getStreamVolume();
            setStreamVolume(0);
            sbVoice.setProgress(0);
        } else {
            setStreamVolume(curVolume);
            sbVoice.setProgress(curVolume);

        }
    }

    /**
     * 音量调节
     */
    private SeekBar.OnSeekBarChangeListener mOnVoiceSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {//进度改变 fromUser-->是否是用户触发
            if (fromUser) {
                setStreamVolume(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {//开始拖动
            if (controllerCallBack != null) {
                controllerCallBack.onCancelHideCtrl();
            }
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {//停止拖动
            if (controllerCallBack != null) {
                controllerCallBack.onSendHideCtrl();
            }
        }
    };

    private SeekBar.OnSeekBarChangeListener mOnVideoSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                if (controllerCallBack != null) {
                    controllerCallBack.onSeekBarChange(progress);
                }
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (controllerCallBack != null) {
                controllerCallBack.onCancelHideCtrl();
            }
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (controllerCallBack != null) {
                controllerCallBack.onSendHideCtrl();
            }
        }
    };

    /**
     * 获取当前音量
     *
     * @return
     */
    public int getStreamVolume() {
        return voiceUtil.getStreamVolume();
    }

    /**
     * 设置音量
     *
     * @param value
     */
    private void setStreamVolume(int value) {
        voiceUtil.setStreamVolume(value,true);
    }

    private ControllerCallBack controllerCallBack;

    public void setControllerCallBack(ControllerCallBack controllerCallBack) {
        this.controllerCallBack = controllerCallBack;
    }

    /**
     * 根据移动屏幕的移动距离  改变音量值
     *
     * @param distanceY
     */
    public void changeVolume(float distanceY, int curVolume) {
        //4、计算滑动的距离等于多少对应的音量值
        //      a)计算音量最大值与屏幕高的比例
        float scale = ((float) maxVolume) / ScreenUtil.getScreenHeight(mContext);
        //      b)计算滑动的距离等于多少对应的音量值：移动距离 x 比例
        int moveVolume = (int) (distanceY * scale);
        //5、在原来音量的基础上加上计算出来的对应音量值
        int resultVolue = curVolume + moveVolume;
        //预防超出范围
        if (resultVolue < 0) {
            resultVolue = 0;
        } else if (resultVolue > maxVolume) {
            resultVolue = maxVolume;
        }
        setStreamVolume(resultVolue);
        sbVoice.setProgress(resultVolue);
    }
}
