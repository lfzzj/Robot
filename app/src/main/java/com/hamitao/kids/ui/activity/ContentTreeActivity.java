package com.hamitao.kids.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.snap.GravityPagerSnapHelper;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.ContentTreeAdapter;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.constant.Constants;
import com.hamitao.framework.enums.ContentTree;
import com.hamitao.framework.widgets.LoadingView;
import com.hamitao.kids.model.ContentTreeInfo;
import com.hamitao.kids.mvp.ipresenter.IDevicePresenter;
import com.hamitao.kids.mvp.presenter.DevicePresenterImpl;
import com.hamitao.kids.view.TitleView;
import com.hamitao.requestframe.entity.GetContentTreeInfo;
import com.hamitao.requestframe.view.GetContentTreeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @data on 2018/7/6 10:40
 * @describe: 动画
 */

public class ContentTreeActivity extends BaseMsgActivity {

    @BindView(R.id.rv_func_anim)
    RecyclerView mRecyclerView;
    @BindView(R.id.view_loading)
    LoadingView viewLoading;

    private IDevicePresenter devicePresenter;
    private ContentTreeAdapter mAdapter;
    private TitleView titleView;
    private List<ContentTreeInfo> contentTreeInfos = new ArrayList<>();

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_content_tree);
    }

    @Override
    public void initData() {
        titleView = new TitleView(mActivity);
        titleView.setTitle(getResources().getString(R.string.func_anim));
        devicePresenter = new DevicePresenterImpl(mContext, mGetContentTreeView);
        devicePresenter.getContentTree(ContentTree.CONTENT_ANIMATION.toString());
        viewLoading.showLoading();
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ContentTreeAdapter(mRecyclerView, mContext);
        GravityPagerSnapHelper snapHelpe = new GravityPagerSnapHelper(Gravity.START);
        snapHelpe.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnContentTreeListener(mOnContentTreeListener);
    }

    private GetContentTreeView mGetContentTreeView = new GetContentTreeView() {
        @Override
        public void onSuccess(GetContentTreeInfo info) {
            viewLoading.hintLoading();
            Logger.d(TAG, "二叉树数据获取成功");
            if (BaseConstant.SUCCESS.equals(info.getResult())) {
                List<GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean> ContentTreeNodesBeans = info.getResponseDataObj().getContentTreeNodes();
                List<GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX> childrenBeanXs = ContentTreeNodesBeans.get(0).getChildren();
                Logger.d(TAG, "childrenBeanXs=" + childrenBeanXs.size());
                for (int i = 0; i < childrenBeanXs.size(); i++) {
                    GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX childrenBeanX = childrenBeanXs.get(i);
                    List<GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean> childrenBeans = childrenBeanX.getChildren();
                    if (childrenBeans != null && childrenBeans.size() != 0) {
                        String nodename = childrenBeanX.getNodename();
                        String nodeheaderimgurl = childrenBeanX.getNodeheaderimgurl();
                        contentTreeInfos.add(new ContentTreeInfo(nodename, nodeheaderimgurl, childrenBeans));
                    }
                }
                Logger.d(TAG, "contentTreeInfos=" + contentTreeInfos.size());
                if (contentTreeInfos.size() != 0) {
                    mAdapter.setData(contentTreeInfos);
                    mAdapter.notifyDataSetChanged();
                }else{
                    toast(getStrByRes(R.string.hint_no_data_refresh));
                    speak(FuncTitle.CONTENT_VOICE_HINT_NO_SEACH_CONTENT.toString());
                }
            }
        }

        @Override
        public void onError(String result) {
            Logger.d(TAG, "二叉树数据获取失败=" + result);
            viewLoading.hintLoading();
            toast(getStrByRes(R.string.hint_no_data_refresh));
            speak(FuncTitle.CONTENT_VOICE_HINT_NO_SEACH_CONTENT.toString());

        }
    };

    private ContentTreeAdapter.OnContentTreeListener mOnContentTreeListener = new ContentTreeAdapter.OnContentTreeListener() {
        @Override
        public void onClick(int position) {
            if (contentTreeInfos.size() > position) {
                ContentTreeInfo contentTreeInfo = contentTreeInfos.get(position);
                List<GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean> childrenBeans = contentTreeInfo.getChildrenBeans();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.FLAG_CONTENT_TREE_TITLE, contentTreeInfo.getNodename());
                bundle.putSerializable(Constants.FLAG_CONTENT_TREE_CHILDREN, (Serializable) childrenBeans);
                openActivity(ContentTreeChildrenActivity.class, bundle);
            }
        }
    };
}
