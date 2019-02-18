package com.hamitao.kids.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.hamitao.kids.R;
import com.hamitao.kids.model.AlbumInfo;
import com.hamitao.kids.model.FollowMeBean;
import com.hamitao.kids.oss.OSSManager;
import com.hamitao.kids.utils.DataFuncUtil;
import com.hamitao.kids.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

public class AlbumViewerAdapter extends PagerAdapter {
    private Context mContext;
    private Activity mActivity;
    private List<AlbumInfo> mDatas = new ArrayList<>();

    private OSSManager ossManager;

    public AlbumViewerAdapter(Context mContext, Activity mActivity, List<AlbumInfo> mDatas, OSSManager ossManager) {
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mDatas = mDatas;
        this.ossManager = ossManager;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//添加View

        View view = LayoutInflater.from(mActivity).inflate(R.layout.adapter_album_viewer, null);
        ImageView ivPicViewer = view.findViewById(R.id.iv_pic_viewer);

        ossManager.getOssConstrainedObjectURL(mDatas.get(position).getPhotourl());
        Glide.with(mActivity)
                .load(mDatas.get(position).getAlbum())
                .placeholder(R.drawable.icon_image_loading)
                .error(R.drawable.icon_image_load_fail)
                .into(ivPicViewer);

        container.addView(view);
        return view;
    }

}
