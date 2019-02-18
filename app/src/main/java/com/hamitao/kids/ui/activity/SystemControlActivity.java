package com.hamitao.kids.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.utils.BrightUtil;
import com.hamitao.kids.utils.VoiceUtil;
import com.hamitao.kids.view.TitleView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @data on 2018/7/4 17:57
 * @describe: 系统设置（音量/亮度）
 */

public class SystemControlActivity extends BaseMsgActivity {


    @BindView(R.id.seekbar)
    SeekBar seekBar;

    @BindView(R.id.tv_cur_value)
    TextView tvCurValue;

    @BindView(R.id.iv_sys_icon)
    ImageView ivSysIcon;

    @BindView(R.id.rl_sys_icon)
    RelativeLayout rlSysIcon;

    @BindView(R.id.rl_minus)
    RelativeLayout rlMinus;

    @BindView(R.id.rl_plus)
    RelativeLayout rlPlus;

    private VoiceUtil voiceUtil;
    private BrightUtil brightUtil;

    private TitleView mTitle;
    private boolean isVoiceSetting = false;//是否是音量设置

    private int maxBright = 255;
    private int maxVolume;//最大音量

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_system_control);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String flag = intent.getStringExtra(Constants.FLAG_SYSTEM_SETTING);
        mTitle = new TitleView(this);
        seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seek_bar_voice_bg));

        boolean isJgw = getStrByRes(R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI);
        rlSysIcon.setVisibility(isJgw?View.VISIBLE:View.GONE);
        rlMinus.setVisibility(isJgw?View.GONE:View.VISIBLE);
        rlPlus.setVisibility(isJgw?View.GONE:View.VISIBLE);


        if (Constants.FLAG_SYSTEM_SETTING_VOICE.equals(flag)) {
            isVoiceSetting = true;
            mTitle.setTitle(getResources().getString(R.string.func_volume_setting));
            ivSysIcon.setBackgroundResource(R.drawable.icon_voice_home_page);
            initVoice();
        } else {
            isVoiceSetting = false;
            mTitle.setTitle(getResources().getString(R.string.func_bright_setting));
            ivSysIcon.setBackgroundResource(R.drawable.icon_sys_brightness_jgw);
            initBright();
        }
    }

    private void initVoice() {//设置音量
        voiceUtil = new VoiceUtil(mContext);
        //获取系统最大音量
        maxVolume = voiceUtil.getStreamMaxVolume();
        Logger.d(TAG, "maxVolume=" + maxVolume);
        //获取当前音量
        int currentVolume = voiceUtil.getStreamVolume();
        Logger.d(TAG, "currentVolume=" + currentVolume);
        setTvCurValue(currentVolume, maxVolume);
        setSeekBarValue(maxVolume, currentVolume);
        registerReceiver();
    }

    private void initBright() {//设置亮度
        brightUtil = new BrightUtil(mActivity);
        //获取当前亮度
        int bright = brightUtil.getBrightness();
        setTvCurValue(bright, maxBright);
        setSeekBarValue(maxBright, bright);
    }

    //设置tv的值
    public void setTvCurValue(int curValue, int maxValue) {
        int percent = curValue * 100 / maxValue;
        tvCurValue.setText(percent + "%");
    }


    private void setSeekBarValue(int maxValue, int currentVolume) {
        seekBar.setMax(maxValue);
        seekBar.setProgress(currentVolume);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int curValue = 0;
                    if (isVoiceSetting) { //设置系统音量
                        voiceUtil.setStreamVolume(progress);
                        curValue = voiceUtil.getStreamVolume();
                        Logger.d(TAG, "progress==" + progress + "      currentVolume=====" + curValue);
                        seekBar.setProgress(curValue);
                        setTvCurValue(curValue, maxVolume);
                    } else {//设置屏幕亮度
                        brightUtil.setScreenBrightness(progress);
                        setTvCurValue(progress, maxBright);
                    }

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private VolumeReceiver receiver;

    private void registerReceiver() {
        receiver = new VolumeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.media.VOLUME_CHANGED_ACTION");
        registerReceiver(receiver, filter);
    }


    private class VolumeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
                setVoiceValue();
            }
        }
    }

    private void setVoiceValue() {
        int currentVolume = voiceUtil.getStreamVolume();
        setTvCurValue(currentVolume, maxVolume);
        seekBar.setProgress(currentVolume);
    }

    private void setBrightValue() {
        int bright = brightUtil.getBrightness();
        setTvCurValue(bright, maxBright);
        setSeekBarValue(maxBright, bright);
    }

    @OnClick({R.id.rl_minus, R.id.rl_plus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_minus://减
                if (isVoiceSetting) { //设置系统音量
                    voiceUtil.setVoice(false, false);
                    setVoiceValue();
                } else {
                    brightUtil.setBrightness(false, false);
                    setBrightValue();
                }
                break;
            case R.id.rl_plus://加
                if (isVoiceSetting) { //设置系统音量
                    voiceUtil.setVoice(false, true);
                    setVoiceValue();
                } else {//设置系统亮度
                    brightUtil.setBrightness(false, true);
                    setBrightValue();
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }
}
