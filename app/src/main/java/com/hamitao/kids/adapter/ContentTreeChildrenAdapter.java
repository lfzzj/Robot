package com.hamitao.kids.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hamitao.kids.R;
import com.hamitao.requestframe.entity.GetContentTreeInfo;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * @data on 2018/7/6 17:08
 * @describe:
 */

public class ContentTreeChildrenAdapter extends BGARecyclerViewAdapter<GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean> {
    public ContentTreeChildrenAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_item_content_tree_children);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean model) {
        helper.getTextView(R.id.tv_tree_children_name).setText(model.getNodename());

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onContentTreeChildrenListener!=null){
                    onContentTreeChildrenListener.onClick(position);
                }
            }
        });
    }

    public interface OnContentTreeChildrenListener {
        void onClick(int position);
    }
    private   OnContentTreeChildrenListener onContentTreeChildrenListener;

    public void setOnContentTreeChildrenListener( OnContentTreeChildrenListener onContentTreeChildrenListener) {
        this.onContentTreeChildrenListener = onContentTreeChildrenListener;
    }

}
