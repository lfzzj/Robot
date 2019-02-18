package com.hamitao.kids.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class BitmapManager {
    private static HashMap<String, SoftReference<Bitmap>> cache;   //如果是 int object的键值 对 应该是使用 SparseArray<E>的 但是这里考虑更多的还是加载sd卡的图片!

    static {
        cache = new HashMap<String, SoftReference<Bitmap>>();
    }


    /**
     * 加载图片-可指定显示图片的高宽
     */
    public void loadBitmap(int resId, ImageView imageView , Context context) {
        Bitmap bitmap = getBitmapFromCache(resId + "");

        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            Bitmap bmp = getBitmap(resId , context);
            imageView.setImageBitmap(bmp);
        }
    }

    /**
     * 从缓存中获取图片
     * @param path
     */
    private Bitmap getBitmapFromCache(String path) {
        Bitmap bitmap = null;
        if (cache.containsKey(path)) {
            bitmap = cache.get(path).get();
        }
        return bitmap;
    }
    /*从res中加载图片*/
    private Bitmap getBitmap(int resId , Context context){
        Bitmap bitmap = null;
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        bitmap =BitmapFactory.decodeStream(is,null,opt);
        if (bitmap != null) {
            cache.put(resId + "", new SoftReference<Bitmap>(bitmap));
        }
        return bitmap;
    }

}
