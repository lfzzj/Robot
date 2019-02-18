package com.hamitao.kids.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.R;
import com.hamitao.kids.adapter.FollowMeAdapter;
import com.hamitao.kids.base.BaseMsgActivity;
import com.hamitao.kids.model.FollowMeBean;
import com.hamitao.kids.utils.DataFuncUtil;
import com.hamitao.kids.view.ViewPagerCustomDuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 跟我学列表界面
 */
public class EnglishStudyFollowMeListActivity extends BaseMsgActivity {
    @BindView(R.id.view_pager)
    ViewPagerCustomDuration viewPager;

    private List<FollowMeBean> mDatas = new ArrayList<>();
    private FollowMeAdapter pageAdapter;


    @Override
    public void setActivityView() {
        setContentView(R.layout.activity_func);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String followMeTitle = intent.getStringExtra(BaseConstant.BUNDLE_FOLLOW_ME);
        Logger.d(TAG, "followMeTitle=" + followMeTitle);
        if (getStrByRes(R.string.func_know_animal).equals(followMeTitle)) {//认识动物
            mDatas = DataFuncUtil.getKnowAnimalDatas(mContext);
        } else if (getStrByRes(R.string.func_know_num).equals(followMeTitle)) {//认识数字
            mDatas = DataFuncUtil.getKnowNumDatas(mContext);
        } else if (getStrByRes(R.string.func_know_fruit).equals(followMeTitle)) {//认识水果
            mDatas = DataFuncUtil.getKnowFruitDatas(mContext);
        }

        if (mDatas != null && mDatas.size() > 0) {
            viewPager.setScrollDurationFactor(0.8);
            pageAdapter = new FollowMeAdapter(this, mContext, mDatas);
            viewPager.setAdapter(pageAdapter);
            pageAdapter.setOnFollowMeClickListener(mOnFollowMeClickListener);
        }
    }

    private FollowMeAdapter.OnFollowMeClickListener mOnFollowMeClickListener = new FollowMeAdapter.OnFollowMeClickListener() {
        @Override
        public void OnClick(int position) {
            Logger.d(TAG, "你点击了：" + position);

            Bundle bundle =new Bundle();
            bundle.putInt(BaseConstant.BUNDLE_FOLLOW_ME_POSITION,position);
            bundle.putSerializable(BaseConstant.BUNDLE_FOLLOW_ME_DATAS, (Serializable) mDatas);
            openActivity(FollowMeContentActivity.class,bundle);
        }
    };
}
