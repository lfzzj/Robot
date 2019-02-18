package com.hamitao.kids.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;

import java.io.InputStream;

/**
 * @data on 2018/6/7 9:58
 * @describe: glide加载图片
 */

public class GlideUtil {
    private static String TAG = "GlideUtil";

    public static void loadImage(Context context, String url, ImageView imageView) {
        if (context != null) {
            Glide.with(context)
                    .load(url)
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.icon_image_loading)
                    .error(R.drawable.icon_image_load_fail)
                    .into(imageView);
        } else {
            Logger.d(TAG, "context已经销毁");
        }
    }

    public static void loadImage(Context context, int url, ImageView imageView) {
        if (context != null) {
            Glide.with(context)
                    .load(url)
                    .thumbnail(0.1f)
//                    .placeholder(R.drawable.icon_image_loading)
//                    .error(R.drawable.icon_image_load_fail)
                    .override(200, 150)
                    .into(imageView);
        } else {
            Logger.d(TAG, "context已经销毁");
        }
    }

    public static void loadImages(Context activity, int res, ImageView imageView) {
        if (imageView == null) return;
        Glide.with(activity).load(res).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void loadRes(Context context, ImageView imageView, int resId) {
        if (context != null) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getBackground();
            if (bitmapDrawable != null) {
                Bitmap hisBitmap = bitmapDrawable.getBitmap();
                if (hisBitmap != null && hisBitmap.isRecycled() == false) {
                    imageView.setImageResource(0);
                    hisBitmap.recycle();
                }
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true; // bitmap can be purged to disk
            options.inInputShareable = true;
            Bitmap bm = BitmapFactory.decodeStream(context.getResources().openRawResource(resId));
            Drawable bd = new BitmapDrawable(context.getResources(), bm);
            imageView.setBackgroundDrawable(bd);
        }
//        Bitmap bitmap = decodeImage(context.getResources(), resId, 220, 176);
//        imageView.setImageBitmap(bitmap);
    }

    /**
     * @param res   getResources
     * @param resID R.id.image
     * @param w     期望的宽
     * @param h     期望的高
     */
    public static Bitmap decodeImage(Resources res, int resID, int w, int h) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //第一次解析图片，不分配内存，只得到宽和高
        BitmapFactory.decodeResource(res, resID, options);
        options.inSampleSize = calaculateSampleSize(options, w, h);
        options.inJustDecodeBounds = false;
        //根据得到的宽高再次解析图片
        return BitmapFactory.decodeResource(res, resID, options);

    }

    public static int calaculateSampleSize(BitmapFactory.Options options, int w, int h) {
        //图片原本的宽和高
        int width = options.outWidth;
        int height = options.outHeight;
        int simpleSize = 1;
        if (width > w || height > h) {
            int widthRadio = Math.round((float) width / (float) w);
            int heightRadio = Math.round((float) height / (float) h);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            simpleSize = widthRadio > heightRadio ? heightRadio : widthRadio;
        }
        return simpleSize;
    }

    public static void loadImageCache(Context context, String url, ImageView imageView) {
        if (context != null) {
            Glide.with(context)
                    .load(url)
                    .skipMemoryCache(true)
                    .placeholder(R.drawable.icon_image_loading)
                    .error(R.drawable.icon_image_load_fail)
                    .into(imageView);
        } else {
            Logger.d(TAG, "context已经销毁");
        }
    }


    public static void loadImg(Activity activity, String url, ImageView imageView) {
        if (!activity.isDestroyed()) {
            Glide.with(activity)
                    .load(url)
                    .placeholder(R.drawable.icon_image_loading)
                    .error(R.drawable.icon_image_load_fail)
                    .override(200, 150)
                    .into(new SimpleTarget<GlideDrawable>() { // 加上这段代码 可以解决
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> glideAnimation) {
                            imageView.setImageDrawable(resource); //显示图片
                        }
                    });
        }
    }


    /**
     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
     */
    public static void loadIntoUseFitWidth(Activity activity, final String imageUrl, final ImageView imageView) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getBackground();
        if (bitmapDrawable != null) {
            Bitmap hisBitmap = bitmapDrawable.getBitmap();
            if (hisBitmap != null && hisBitmap.isRecycled() == false) {
                imageView.setImageResource(0);
                hisBitmap.recycle();
            }
        }
        if (!activity.isDestroyed()) {
            Glide.with(activity)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            if (imageView == null) {
                                return false;
                            }
                            if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            }
                            ViewGroup.LayoutParams params = imageView.getLayoutParams();
                            int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                            float scale = (float) vw / (float) resource.getIntrinsicWidth();
                            int vh = Math.round(resource.getIntrinsicHeight() * scale);
                            params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                            imageView.setLayoutParams(params);
                            return false;
                        }
                    })
                    .placeholder(R.drawable.icon_image_loading)
                    .error(R.drawable.icon_image_load_fail)
                    .into(imageView);
        }
    }

    /* -----------------加载gif动画------------------- */


    public final static int FIG_PLAY_SUCCESS = 1;

    public static void loadCycleGif(Context context, int url, ImageView imageView) {
        Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    public static void loadSingleGif(Context context, int url, ImageView imageView, final Handler handler) {
        Glide.with(context)
                .load(url)
                .listener(new RequestListener<Integer, GlideDrawable>() {

                    @Override
                    public boolean onException(Exception arg0, Integer arg1,
                                               Target<GlideDrawable> arg2, boolean arg3) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource,
                                                   Integer model, Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        int duration = 0;
                        // 计算动画时长
                        GifDrawable drawable = (GifDrawable) resource;
                        GifDecoder decoder = drawable.getDecoder();
                        for (int i = 0; i < drawable.getFrameCount(); i++) {
                            duration += decoder.getDelay(i);
                        }
                        //发送延时消息，通知动画结束
                        handler.sendEmptyMessageDelayed(FIG_PLAY_SUCCESS,
                                duration);
                        return false;
                    }
                }) //仅仅加载一次gif动画
                .into(new GlideDrawableImageViewTarget(imageView, 1));
    }
}
