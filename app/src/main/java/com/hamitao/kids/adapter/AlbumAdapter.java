package com.hamitao.kids.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.R;
import com.hamitao.kids.model.AlbumInfo;
import com.hamitao.kids.utils.GlideUtil;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * @data on 2018/6/6 18:53
 * @describe:
 */

public class AlbumAdapter extends BGARecyclerViewAdapter<AlbumInfo> {
    private Activity mActivity;

    public AlbumAdapter(RecyclerView recyclerView, Activity activity ) {
        super(recyclerView, R.layout.item_album);
        this.mActivity = activity;
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, AlbumInfo model) {
//        GlideUtil.loadImg(mActivity, model.getAlbum(), helper.getView(R.id.iv_album));
        GlideUtil.loadIntoUseFitWidth(mActivity, model.getAlbum(), helper.getView(R.id.iv_album));
        if (model.isEditState()) {//处于编辑状态
            helper.getView(R.id.iv_del).setVisibility(View.VISIBLE);
            helper.getView(R.id.iv_del).setBackgroundResource(model.isDel() ? R.drawable.icon_tick : R.drawable.icon_untick);
        } else {
            helper.getView(R.id.iv_del).setVisibility(View.INVISIBLE);
        }

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnAlbumListener != null) {
                    mOnAlbumListener.onClickListener(position);
                }
            }
        });
    }

    //  删除数据
    public void removeData(int position) {
        super.getData().remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    private OnAlbumListener mOnAlbumListener;

    public void setOnAlbumListener(OnAlbumListener onAlbumListener) {
        this.mOnAlbumListener = onAlbumListener;
    }

    public interface OnAlbumListener {
        void onClickListener(int position);

    }

}
