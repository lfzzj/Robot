package com.hamitao.kids.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hamitao.kids.R;
import com.hamitao.kids.model.ContentTreeInfo;
import com.hamitao.kids.utils.GlideUtil;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * @data on 2018/7/6 14:33
 * @describe:
 */

public class ContentTreeAdapter extends BGARecyclerViewAdapter<ContentTreeInfo> {
    private Context mContext;

    public ContentTreeAdapter(RecyclerView recyclerView, Context context) {
        super(recyclerView, R.layout.item_content_tree);
        this.mContext = context;
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ContentTreeInfo model) {
        String headUrl = model.getNodeheaderimgurl();
        GlideUtil.loadImage(mContext, headUrl, helper.getImageView(R.id.iv_content_icon));

        helper.getTextView(R.id.tv_content_name).setText(model.getNodename());
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onContentTreeListener != null) {
                    onContentTreeListener.onClick(position);
                }
            }
        });
    }

    public interface OnContentTreeListener {
        void onClick(int position);

    }

    private OnContentTreeListener onContentTreeListener;

    public void setOnContentTreeListener(OnContentTreeListener onContentTreeListener) {
        this.onContentTreeListener = onContentTreeListener;
    }
}

