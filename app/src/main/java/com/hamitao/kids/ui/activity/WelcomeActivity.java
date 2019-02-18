package com.hamitao.kids.ui.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.view.View;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.GifDrawableUtil;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.utils.BrightUtil;
import com.hamitao.kids.utils.PlayGifAnim;
import com.hamitao.kids.utils.PlayUtil;
import com.hamitao.kids.utils.ScreenUtil;
import com.hamitao.kids.utils.VoiceUtil;

import butterknife.BindView;
import pl.droidsonroids.gif.GifImageView;

public class WelcomeActivity extends BaseMsgActivity {
    @BindView(R.id.iv_welcome)
    GifImageView ivWelcomle;

    @BindView(R.id.animation_view)
    LottieAnimationView animationView;

    private BrightUtil brightUtil;
    private VoiceUtil voiceUtil;


    @Override
    public void setActivityView() {
        Logger.d(TAG, "=======================WelcomeActivity======================");
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
            //指定横屏播放开机动画
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        }
        setContentView(R.layout.activity_welcome);
        initSystemData();
    }

    /**
     * 初始化系统数据
     */
    private void initSystemData() {
        brightUtil = new BrightUtil(mActivity);
        voiceUtil = new VoiceUtil(mContext);
        if (MyApp.getInstance().getSpManager().isFristBoot()) {
            brightUtil.setScreenBrightness(255);
            voiceUtil.setStreamVolume(15);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 检测屏幕的方向：纵向或横向
        if (this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            //当前为横屏， 在此处添加额外的处理代码
        } else if (this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT) {
            //当前为竖屏， 在此处添加额外的处理代码
        }
        //检测实体键盘的状态：推出或者合上
        if (newConfig.hardKeyboardHidden
                == Configuration.HARDKEYBOARDHIDDEN_NO) {
            //实体键盘处于推出状态，在此处添加额外的处理代码
        } else if (newConfig.hardKeyboardHidden
                == Configuration.HARDKEYBOARDHIDDEN_YES) {
            //实体键盘处于合上状态，在此处添加额外的处理代码
        }
    }

    private boolean isInitData = false;

    @Override
    public void initData() {
        initVendorInfo();
        if (isInitData) {
            return;
        }
        isInitData = true;
        deviceManager.getLocalMacAddress();
        ScreenUtil.screenOn(mActivity);//点亮屏幕

        if (MyApp.getInstance().getSpManager().isFristBoot()) {
            PlayUtil.playVoice(mContext, R.raw.prompt_message_frist, mCompletionListener);
        } else {
            PlayUtil.playVoice(mContext, R.raw.prompt_message, mCompletionListener);
        }

        if (getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_HAIER)){
            animationView.setVisibility(View.GONE);
            ivWelcomle.setVisibility(View.VISIBLE);
            GifDrawableUtil.playGifAnim(WelcomeActivity.this, R.drawable.anim_welcome_haier, ivWelcomle , null);
        }else{
            PlayGifAnim.play(animationView,getStrByRes(R.string.flag_play_gif_welcome));
        }
    }

    /**
     * 初始化厂商信息以及唤醒词（可在此获取包名进行区别）
     */
    private void initVendorInfo() {
        //设置唤醒词
        MyApp.getInstance().setWakeUpWord(getStrByRes(R.string.wake_up_word));
        MyApp.getInstance().setWakeUpWordChinese(getStrByRes(R.string.wake_up_word_chinese));

        //设置turing的key和秘钥
        MyApp.getInstance().setTuringKey(getStrByRes(R.string.turing_key));
        MyApp.getInstance().setTuringSecret(getStrByRes(R.string.turing_secret));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayGifAnim.cancel(animationView);
    }

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
                MyApp.getInstance().getSpManager().putFristBoot();
                openActivity(MainActivity.class);
            }else{
                if (MyApp.getInstance().getSpManager().isFristBoot()) {
                    openActivity(NetConnActivity.class);
                } else {
                    openActivity(MainActivity.class);
                }
            }
            finish();
        }
    };

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onHomePressed() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }
}
