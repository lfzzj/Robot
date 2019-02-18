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
import com.hamitao.kids.model.FollowMeBean;
import com.hamitao.kids.model.FuncBean;
import com.hamitao.kids.utils.NoDoubleItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class FollowMeAdapter  extends PagerAdapter {
    private Context mContext;
    private Activity mActivity;
    private List<FollowMeBean> mDatas = new ArrayList<>();

    public FollowMeAdapter(Activity activity, Context context, List<FollowMeBean> funcList) {
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
        FollowMeBean bean = mDatas.get(position);

        BitmapManager bitmapManager = new BitmapManager();
        bitmapManager.loadBitmap(bean.getIcon(), imageView, mContext);

        container.addView(view);

        imageView.setOnClickListener(new NoDoubleItemClickListener() {

            @Override
            protected void onNoDoubleClick(View v) {
                if (mOnFollowMeClickListener != null) {
                    mOnFollowMeClickListener.OnClick(position);
                }
            }
        });
        return view;
    }
    public interface OnFollowMeClickListener {
        void OnClick(int position);
    }

    private OnFollowMeClickListener mOnFollowMeClickListener;

    public void setOnFollowMeClickListener(OnFollowMeClickListener onFollowMeClickListener) {
        mOnFollowMeClickListener = onFollowMeClickListener;
    }

}
