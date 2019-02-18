package com.hamitao.kids.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hamitao.kids.R;
import com.hamitao.kids.callback.OnFuncClickListener;
import com.hamitao.kids.camera.util.ImageUtils;
import com.hamitao.kids.manager.BitmapManager;
import com.hamitao.kids.model.FuncBean;
import com.hamitao.kids.utils.GlideUtil;
import com.hamitao.kids.utils.NoDoubleItemClickListener;
import com.hamitao.kids.widgets.LoopVPAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class ImgAdapter extends LoopVPAdapter<FuncBean> {

    public ImgAdapter(Context context, List<FuncBean> datas, ViewPager viewPager) {
        super(context, datas, viewPager);
    }

    private ViewGroup.LayoutParams layoutParams;

    @Override
    protected View getItemView(FuncBean data) {

        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        BitmapManager  bitmapManager = new BitmapManager();
        bitmapManager.loadBitmap(data.getFuncIcon(),imageView,mContext);
        imageView.setOnClickListener(new NoDoubleItemClickListener() {

            @Override
            protected void onNoDoubleClick(View v) {
                if (onClick != null) {
                    onClick.onClick(data.getFuncName());
                }
            }
        });
//        GlideUtil.loadImages(mContext, data.getFuncIcon(), imageView);
        return imageView;
    }
    private OnImgClickListener onClick;

    public void setOnFuncClickListener(OnImgClickListener onImgClickListener) {
        onClick = onImgClickListener;
    }

    public interface OnImgClickListener {
        void onClick(String funcName);
    }


}
