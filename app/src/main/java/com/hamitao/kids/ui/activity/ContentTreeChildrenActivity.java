package com.hamitao.kids.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.ContentTreeChildrenAdapter;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.constant.Constants;
import com.hamitao.kids.view.TitleView;
import com.hamitao.requestframe.entity.GetContentTreeInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @data on 2018/7/6 16:30
 * @describe: 二叉树的根节点
 */

public class ContentTreeChildrenActivity extends BaseMsgActivity {
    @BindView(R.id.rv_content_tree_children)
    RecyclerView mRecyclerView;

    private ContentTreeChildrenAdapter mAdapter;
    private List<GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean> childrenBeans;
    private List<GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean> newChildrenBeans = new ArrayList<>();

    private TitleView titleView;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_content_tree_children);
    }

    @Override
    public void initData() {
        titleView = new TitleView(mActivity);
        Intent intent = getIntent();
        childrenBeans = (List<GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean>) intent.getSerializableExtra(Constants.FLAG_CONTENT_TREE_CHILDREN);
        String nodeName = intent.getStringExtra(Constants.FLAG_CONTENT_TREE_TITLE);
        titleView.setTitle(nodeName);
        removeEmpty();
        initRecyclerView();
    }

    /**
     * 去空
     */
    private void removeEmpty() {
        for (int i = 0; i < childrenBeans.size(); i++) {
            GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean childrenBean = childrenBeans.get(i);
            if (!"".equals(childrenBean.getContentid())) {
                newChildrenBeans.add(childrenBean);
            }
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ContentTreeChildrenAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnContentTreeChildrenListener(mOnContentTreeChildrenListener);
        mAdapter.setData(newChildrenBeans);

    }

    private ContentTreeChildrenAdapter.OnContentTreeChildrenListener mOnContentTreeChildrenListener = new ContentTreeChildrenAdapter.OnContentTreeChildrenListener() {
        @Override
        public void onClick(int position) {
            GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean childrenBean = newChildrenBeans.get(position);

            String contentid = childrenBean.getContentid();
            Logger.d(TAG, "contentid=" + contentid);
            if (contentid != "") {
              //跳转到播放界面

            } else {
                Logger.d(TAG, "内容id不存在");
            }
        }
    };
}
