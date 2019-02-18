package com.hamitao.kids.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.GifDrawableUtil;
import com.hamitao.framework.widgets.MarqueTextView;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.utils.PlayGifAnim;
import com.hamitao.kids.utils.PlayVoice;
import com.hamitao.kids.utils.WindowUtils;

import pl.droidsonroids.gif.GifImageView;

public class AlarmDialog extends Dialog {
    private Context context;
    private LottieAnimationView animationView;
    private MarqueTextView mTvTitle;


    public AlarmDialog(@NonNull Context context) {
        super(context, R.style.remind_dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_alarm_ring);
//        mIvAlarm = findViewById(R.id.iv_alarm);
        animationView = findViewById(R.id.animation_view_alarm);
        mTvTitle = findViewById(R.id.tv_title);
        animationView.setOnClickListener(mOnClickListener);
        initData();
    }

    private void initData() {
        WindowUtils.hidePopupWindow();
    }

    //设置闹钟动画
    public void openAlarmRing() {
//        GifDrawableUtil.playGifAnim(context, res, mIvAlarm);
        PlayGifAnim.play(animationView, context.getString(R.string.flag_play_gif_alarm));
        PlayVoice.playVoice(context, R.raw.alarm_clock);
    }

    public void closeAlarm() {
        PlayVoice.stopVoice();
    }

    //设置标签
    public void setAlarmTitle(String msg) {
        mTvTitle.setText(msg);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.animation_view_alarm:
                    if (onClickListener != null) {
                        onClickListener.onClick();
                    }
                    break;
            }
        }
    };

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick();
    }

    @Override
    public void onBackPressed() {
        if (onClickListener != null) {
            onClickListener.onClick();
        }
    }
}
