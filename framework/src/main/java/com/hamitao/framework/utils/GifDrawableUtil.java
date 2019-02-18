package com.hamitao.framework.utils;

import android.content.Context;
import android.widget.ImageView;


import com.hamitao.framework.callback.OnCompleteCallBack;

import java.io.IOException;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class GifDrawableUtil {
    private static GifDrawable gifFromResource;

    public static void playGifAnim(Context context, int animId, GifImageView imageView) {
        playGifAnim(context, animId, imageView,null);
    }

    public static void playGifAnim(Context context, int animId, final ImageView imageView, final OnCompleteCallBack onCompleteCallBack) {
        try {
            if (gifFromResource != null && gifFromResource.isPlaying()) {
                gifFromResource.stop();
            }
            gifFromResource = new GifDrawable(context.getResources(), animId);

            imageView.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageDrawable(gifFromResource);
                }
            });


            gifFromResource.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    if (onCompleteCallBack != null) {
                        onCompleteCallBack.onComplete();
                    } else {
                        gifFromResource.reset();
                    }
                }
            });





        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //暂停播放动画
    public static void pauseGifAnim() {
        if (gifFromResource != null && gifFromResource.isPlaying()) {
            gifFromResource.pause();
        }
    }

    //继续播放动画
    public static void resumeGifAnim() {
        if (gifFromResource != null) {
            gifFromResource.start();
        }
    }

    //重置播放动画
    public static void resetGifAnim() {
        if (gifFromResource != null && gifFromResource.isPlaying()) {
            gifFromResource.reset();
        }
    }


}
