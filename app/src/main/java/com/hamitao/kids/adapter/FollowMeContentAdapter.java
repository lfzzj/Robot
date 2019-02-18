package com.hamitao.kids.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.kids.R;
import com.hamitao.kids.model.FollowMeBean;
import com.hamitao.kids.model.FuncBean;
import com.hamitao.kids.utils.DataFuncUtil;

import java.util.ArrayList;
import java.util.List;

public class FollowMeContentAdapter extends PagerAdapter {
    private Context mContext;
    private Activity mActivity;
    private List<FollowMeBean> mDatas = new ArrayList<>();

    public FollowMeContentAdapter(Context mContext, Activity mActivity, List<FollowMeBean> mDatas) {
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mDatas = mDatas;
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

        View view = LayoutInflater.from(mActivity).inflate(R.layout.adapter_follow_me_content, null);
        ImageView ivContent = view.findViewById(R.id.iv_follow_me_content);
        RelativeLayout rlBg = view.findViewById(R.id.rl_bg);

        DataFuncUtil.setFollowMeContent(mContext, ivContent, rlBg, mDatas.get(position).getChineseName());
        container.addView(view);
        return view;
    }

}
