package com.hamitao.kids.ui.activity;

import android.os.Bundle;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.FuncPageAdapter;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.callback.OnFuncClickListener;
import com.hamitao.kids.model.FuncBean;
import com.hamitao.kids.utils.DataFuncUtil;
import com.hamitao.kids.view.ViewPagerCustomDuration;

import java.util.List;

import butterknife.BindView;

/**
 * 英语学习——跟我学
 */
public class EnglishStudyFollowMeActivity extends BaseMsgActivity {
    @BindView(R.id.view_pager)
    ViewPagerCustomDuration viewPager;

    private List<FuncBean> mFuncDatas;
    private FuncPageAdapter pageAdapter;

    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_func);
    }

    @Override
    public void initData() {
        mFuncDatas = DataFuncUtil.getFollowMeDatas(mContext);

        viewPager.setScrollDurationFactor(0.8);
        pageAdapter = new FuncPageAdapter(this, mContext, mFuncDatas);
        viewPager.setAdapter(pageAdapter);
        pageAdapter.setOnFuncClickListener(mOnFuncClickListener);
    }

    private OnFuncClickListener mOnFuncClickListener = new OnFuncClickListener() {
        @Override
        public void OnClick(String funcName) {
            Bundle bundle = new Bundle();
            bundle.putString(BaseConstant.BUNDLE_FOLLOW_ME,funcName);
            openActivity(EnglishStudyFollowMeListActivity.class,bundle);
        }
    };

}
