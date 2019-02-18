package com.hamitao.kids.keyboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hamitao.framework.R;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class KeyboardAdapter extends BGARecyclerViewAdapter<String> {
    public KeyboardAdapter(RecyclerView recyclerView, int layout) {
        super(recyclerView, layout);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, final int position, String model) {
        helper.getTextView(R.id.tv_value).setText(model);
        helper.getView(R.id.rl_value).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onClick(position);
                }
            }
        });
    }

    private OnClickListener mListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position);
    }
}
