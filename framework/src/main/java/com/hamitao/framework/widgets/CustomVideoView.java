package com.hamitao.framework.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @data on 2018/3/29 10:22
 * @describe: 自定义VideoView播放器
 */

public class CustomVideoView extends VideoView {

    private PlayPauseListener mListener;

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setPlayPauseListener(PlayPauseListener listener) {
        mListener = listener;
    }

    @Override
    public void pause() {
        super.pause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    public void resume() {
        super.resume();
        if (mListener!=null){
            mListener.onResume();
        }
    }

    @Override
    public void start() {
        super.start();
        if (mListener != null) {
            mListener.onPlay();
        }
    }

    public interface PlayPauseListener {
        void onPlay();
        void onPause();
        void onResume();
    }

}