package com.hamitao.kids.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hamitao.kids.R;
import com.hamitao.kids.callback.OnFuncClickListener;
import com.hamitao.kids.manager.BitmapManager;
import com.hamitao.kids.model.FuncBean;
import com.hamitao.kids.utils.GlideUtil;
import com.hamitao.kids.utils.NoDoubleItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @data on 2018/5/29 9:45
 * @describe:
 */

public class FuncPageAdapter extends PagerAdapter {
    private Context mContext;
    private Activity mActivity;
    private List<FuncBean> mDatas = new ArrayList<>();

    public FuncPageAdapter(Activity activity, Context context, List<FuncBean> funcList) {
        this.mActivity = activity;
        this.mContext = context;
        this.mDatas = funcList;

    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {//添加View
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_item_func, null);
        ImageView imageView = view.findViewById(R.id.img_func_icon);
        FuncBean funcBean = mDatas.get(position);

//        GlideUtil.loadRes(mContext, imageView, funcBean.getFuncIcon());
        BitmapManager bitmapManager = new BitmapManager();
        bitmapManager.loadBitmap(funcBean.getFuncIcon(), imageView, mContext);

        container.addView(view);

        imageView.setOnClickListener(new NoDoubleItemClickListener() {

            @Override
            protected void onNoDoubleClick(View v) {
                if (mOnFuncClickListener != null) {
                    mOnFuncClickListener.OnClick(funcBean.getFuncName());
                }
            }
        });
        return view;
    }


    private OnFuncClickListener mOnFuncClickListener;

    public void setOnFuncClickListener(OnFuncClickListener onFuncClickListener) {
        mOnFuncClickListener = onFuncClickListener;
    }

}
