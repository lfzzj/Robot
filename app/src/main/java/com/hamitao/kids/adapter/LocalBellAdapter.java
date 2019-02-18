package com.hamitao.kids.adapter;

import android.support.v7.widget.RecyclerView;

import com.hamitao.kids.R;
import com.hamitao.kids.model.LocalBellInfo;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * @data on 2018/6/6 18:01
 * @describe: 电话本
 */

public class LocalBellAdapter extends BGARecyclerViewAdapter<LocalBellInfo> {

    public LocalBellAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_local_bell);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, LocalBellInfo model) {
        helper.getTextView(R.id.tv_local_bell).setText(model.getBellName());

    }

    private OnLocalBellListener mOnLocalBellListener;

    public void setOnLocalBellListener(OnLocalBellListener onLocalBellListener) {
        this.mOnLocalBellListener = onLocalBellListener;
    }

    public interface OnLocalBellListener {
        void onClick(int position);

    }

}
