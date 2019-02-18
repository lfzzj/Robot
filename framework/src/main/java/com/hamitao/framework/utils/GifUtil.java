package com.hamitao.framework.utils;

import android.content.Context;

import com.hamitao.framework.R;
import com.hamitao.framework.callback.CompleteCallBack;
import com.hamitao.framework.callback.OnCompleteCallBack;
import com.hamitao.framework.callback.PlayCallBack;

import java.io.IOException;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class GifUtil {
    private static GifDrawable gifDrawable;

    public static void loadGif(Context context, int res, GifImageView imageView) {
        gifDrawable = null;

        try {
            if(gifDrawable==null){
                gifDrawable = new GifDrawable(context.getResources(), res);
                gifDrawable.setLoopCount(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageDrawable(gifDrawable);
    }

    //停止播放动画
    public static void stopGifAnim() {
        if (gifDrawable != null && gifDrawable.isPlaying()) {
            gifDrawable.stop();
        }
    }
    //暂停播放动画
    public static void pauseGifAnim() {
        if (gifDrawable != null && gifDrawable.isPlaying()) {
            gifDrawable.pause();
        }
    }

    //继续播放动画
    public static void resumeGifAnim() {
        if (gifDrawable != null) {
            gifDrawable.start();
        }
    }


    public static void loadGif(String res, GifImageView imageView) {
        try {
            GifDrawable gifFromResource = new GifDrawable(res);
            imageView.setImageDrawable(gifFromResource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadGif(String res, GifImageView imageView, final CompleteCallBack callBack) {
        try {
            GifDrawable gifFromResource = new GifDrawable(res);
            gifFromResource.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    if (callBack != null) {
                        callBack.onPlayComplete();
                    }
                }
            });
            imageView.setImageDrawable(gifFromResource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
